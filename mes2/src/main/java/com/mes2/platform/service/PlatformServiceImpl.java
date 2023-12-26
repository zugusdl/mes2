package com.mes2.platform.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.platform.domain.MdbDTO;
import com.mes2.platform.domain.MdpDTO;
import com.mes2.platform.domain.OrderDetailDTO;
import com.mes2.platform.domain.SoiDTO;
import com.mes2.platform.domain.SopDTO;
import com.mes2.platform.domain.OrderRequestDTO;
import com.mes2.platform.persistence.PlatformDAO;

@Service
public class PlatformServiceImpl implements PlatformService {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformServiceImpl.class);
	
	@Inject
	private PlatformDAO pdao;
	
	// 로그인
	@Override
	public MdbDTO customerLogin(MdbDTO mdto) throws Exception {
		logger.debug("S: customerLogin() 호출");
		return pdao.customerLogin(mdto);
	}

	// 품목 목록 조회
	@Override
	public List<MdpDTO> inqueryProduct(String searchType, String search) throws Exception {
		logger.debug("S: inqueryProduct() 호출");
		return pdao.inqueryProduct(searchType, search);
	}

	// 품목 하나 선택
	@Override
	public MdpDTO registProduct(String product_code) throws Exception {
		logger.debug("S: registProduct() 호출");
		return pdao.registProduct(product_code);
	}

	// 발주 신청
	@Override
	public void insertOrder(OrderRequestDTO orDTO, HttpSession session) throws Exception {
		String order_code = makeOrderCode(session);
		Date order_date = Date.valueOf(orDTO.getOrder_date());
		
		SoiDTO soiDTO = new SoiDTO();
		soiDTO.setOrder_date(order_date);
		soiDTO.setOrder_code(order_code);
		soiDTO.setCompany_code((String)session.getAttribute("company_code"));
		
		List<SopDTO> sopList = orDTO.getSopList();
		
		for(SopDTO sopDTO : sopList) {
			sopDTO.setOrder_code(order_code);
		}
		pdao.insertOrder(soiDTO, sopList);
	}
	
	// 주문코드 생성
	private String makeOrderCode(HttpSession session) throws Exception {
		// 날짜 계산
		LocalDate today = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		String dtfToday = today.format(dtf);
		
		// 금일 마지막 주문번호
		String lastOrder_code = pdao.countTodayOrder(dtfToday);
		
		// 마지막 주문번호 인덱스 계산
		int index = Integer.parseInt(lastOrder_code.substring(lastOrder_code.lastIndexOf("-")+1)) + 1;
		
		String order_code = "OD-" + dtfToday + "-" + session.getAttribute("company_code") + "-" + index;
		return order_code;
	}

	// 주문 목록 조회
	@Override
	public List<SoiDTO> getOrderList(String company_code) throws Exception {
		logger.debug("S: getOrderList() 호출");
		return pdao.getOrderList(company_code);
	}

	// 주문 상세 조회
	@Override
	public List<SopDTO> getOrderDetail(String order_code) throws Exception {
		logger.debug("S: getOrderDetail() 호출");
		return pdao.getOrderDetail(order_code);
	}


	
	
}
