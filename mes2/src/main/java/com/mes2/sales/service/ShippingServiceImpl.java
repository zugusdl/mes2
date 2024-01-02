package com.mes2.sales.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.platform.service.PlatformServiceImpl;
import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.PlanRegisterDTO;
import com.mes2.sales.domain.SearchDTO;
import com.mes2.sales.domain.ShippingDTO;
import com.mes2.sales.persistence.ShippingDAO;

@Service
public class ShippingServiceImpl implements ShippingService {

	private static final Logger logger = LoggerFactory.getLogger(ShippingServiceImpl.class);
	
	@Inject
	private ShippingDAO sdao;
	
	@Override
	public List<ShippingDTO> shippingList() {
		logger.debug(" S : shippingList(ShippingDTO sdt) ");
		
		List<ShippingDTO> list = sdao.getShippingList();
		
		for(ShippingDTO sdto : list) {
			
			ShippingDTO sd = new ShippingDTO();
			String order_code = sdto.getOrder_code();
			sd.setOrder_code(order_code);
			List<String> cntCheck = productStatusCnt(order_code);
			int count =0;
			
			for(String cnt : cntCheck) {
				String product_status = cnt;
				if(product_status.equals("complete")) {
					count ++;
				}
				
			}
			logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@~~~~~"+cntCheck.size());
			logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@~~~~~"+count);
			
			if(count == cntCheck.size()) {
				// 전부다 product_status가 complete인 경우
				// 그 order_code의 shipping에서 ship_status를  instruction으로 변경
				sd.setShip_status("instruction");
				// 위에서order_code를 가지고 와서 shipping에 update해주기 
				updateShipStatus(sd);
			}  else if(count < cntCheck.size() && count >0) {
				// 전부 product_status가 complete인 경우는 아니지만 하나라도 완료인 경우
				// 그 order_code의 shipping에서 ship_status를  waiting으로 변경 
				sd.setShip_status("waiting");
				// 위에서order_code를 가지고 와서 shipping에 update해주기 
				updateShipStatus(sd);
			} else if(count == 0) {
				sd.setShip_status("plan");
				updateShipStatus(sd);
			}
			
			
		}
		
		List<ShippingDTO> ShippingPlanlist = sdao.getShippingList();

		return ShippingPlanlist;
	}
	
	@Override
	public List<String> productStatusCnt(String order_code) {
		logger.debug(" S :  productStatusCnt(ShippingDTO sdto) ");
		
		return sdao.productStatusCnt(order_code);
	}
	
	@Override
	public void updateShipStatus(ShippingDTO sdto) {
		logger.debug(" S :updateShipStatus(ShippingDTO sdto) ");
		sdao.updateShipStatus(sdto);
		
	}
	
	@Override
	public List<ShippingDTO> planContent(String order_code) {
		logger.debug(" S :planContent(String order_code)");
		return sdao.getPlanContent(order_code);
	}
	
	@Override
	public List<ShippingDTO> planSearch(SearchDTO sed) {
		logger.debug(" S : planSearch(SearchDTO sedto)");
		return sdao.planSearch(sed);
	}
	 
	@Override
		public ShippingDTO getId(String order_code) {
			
			return sdao.getId(order_code);
		}
	
	@Override
	public ShippingDTO checkUpdatePw(String user_id, String user_pw, String order_code) {
     String dbPw = sdao.checkUpdatePw(user_id);
		String check = "";
		if(dbPw.equals(user_pw)) {
			check = "true";
		}else {
			check = "false";
		}
		
		ShippingDTO sdt = sdao.checkOrderDate(order_code);
		sdt.setCheck(check);
		
		return sdt;
	
	}
	
	@Override
	public int getScheduleDate(Date schedule_date) {
		
		int count =0;
		List<String>order_code = sdao.getScheduleDate(schedule_date);
		count = order_code.size();
		return count;
	}
	
	@Override
	public void updateSchedule(Date scheduled_date, String order_code) {
		ShippingDTO sdto = new ShippingDTO();
		sdto.setOrder_code(order_code);
		sdto.setScheduled_date(scheduled_date);
		
		sdao.updateSchedule(sdto);
				
	}
	
	@Override
	public AcceptSaveDTO getOrderInfo(String order_code) {
		
		return sdao.getOrderInfo(order_code);
	}
	
	@Override
	public ShippingDTO countShipStatus() {
		
		ShippingDTO sdto = new ShippingDTO();
		
		sdto.setPlanCnt(sdao.countShipStatus("plan"));
		sdto.setWaitingCnt(sdao.countShipStatus("waiting"));
		sdto.setInstructionCnt(sdao.countShipStatus("instruction"));
		
		return sdto;
	}
	
	@Override
	public List<ShippingDTO> statusList(String ship_status) {
		
		return sdao.getStatusList(ship_status);
	}
	
	@Override
	public String shipRegister(String order_code) {
		
		ShippingDTO sd = sdao.getShipDate(order_code);
		sd.setOrder_code(order_code);
		
	    // 지시일 업로드
		sdao.updateShipDate(sd);
		// 출하코드 
		sdao.makeShipCode(sd);
		
		return order_code;
		
	}
	
	@Override
	public List<ShippingDTO> UserShipPlanList(String user_id) {
		
		return sdao.getUserShipPlanList(user_id);
	}
	
	@Override
	public List<ShippingDTO> instructionList() {
		List<ShippingDTO> list = sdao.instructionList();
		
		
		Date today = new Date();

		for(ShippingDTO sdt : list) {
			Date shipd = sdt.getShip_date();
			Calendar shipCal = Calendar.getInstance();
			shipCal.setTime(shipd);
			
			Calendar calendarToday = Calendar.getInstance();
	        calendarToday.setTime(today);
	        
	        int result =shipCal.compareTo(calendarToday);
	        
	        if (result > 0) {
	            //출하지시일이 오늘 이후인 경우 
	            sdt.setProgress_status("waiting");
	            
	        } else if (result < 0) {
	            System.out.println("A 저장된 값은 오늘 이전입니다.");
	            // 출하지시일이 오늘 이전인 경우
	            sdt.setProgress_status("shipping");
	        } else {
	            //출하지시일이 오늘인 경우
	            sdt.setProgress_status("progressing");
	        }
	        sdao.updateShipProgressing(sdt);
			
		}
		
		
		return sdao.instructionList();
	}
	

	@Override
	public ShippingDTO countShipProgressing() {
		ShippingDTO sdto = new ShippingDTO();
		sdto.setPlanCnt(sdao.countShipProgressing("progressing"));
		sdto.setWaitingCnt(sdao.countShipProgressing("waiting"));
		sdto.setInstructionCnt(sdao.countShipProgressing("shipping"));
		
		return sdto;
	}
	
	@Override
	public List<ShippingDTO> progressList(String progress_status) {
		
		return sdao.GetprogressList(progress_status);
	}
	
	@Override
	public List<ShippingDTO> shipContent(String order_code) {
		
		return sdao.getshipContent(order_code);
	}
	
	@Override
	public void updateShipDate(Date scheduled_date, String order_code) {
		ShippingDTO sdto = new ShippingDTO();
		sdto.setScheduled_date(scheduled_date);
		sdto.setOrder_code(order_code);
		sdao.updateShipDate(sdto);
		
	}
	
	@Override
	public List<ShippingDTO> userInstructionList(String user_id) {
		
		return sdao.userInstructionList(user_id);
	}
	
	@Override
	public List<ShippingDTO> shippingSearch(SearchDTO sed) {
		
		return sdao.shippingSearch(sed);
	}
}
