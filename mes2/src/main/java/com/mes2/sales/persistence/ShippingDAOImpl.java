package com.mes2.sales.persistence;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.PlanRegisterDTO;
import com.mes2.sales.domain.SearchDTO;
import com.mes2.sales.domain.ShippingDTO;

@Repository
public class ShippingDAOImpl implements ShippingDAO {

	private static final Logger logger = LoggerFactory.getLogger(ShippingDAOImpl.class);

	@Inject
	private SqlSession sqlSession;
	

	private static final String NAMESPACE ="com.mes2.mapper.shippingMapper";
	
	@Override
	public List<ShippingDTO> getShippingList() {
		
		logger.debug(" DAO : getShippingList(ShippingDTO sdt) ");
		return sqlSession.selectList(NAMESPACE+".getShippingList");
	}
	
	@Override
	public List<String> productStatusCnt(String order_code) {
		logger.debug(" DAO : productStatusCnt(ShippingDTO sdto) ");
		return sqlSession.selectList(NAMESPACE+".productStatusCnt", order_code);
	}
	
	@Override
	public void updateShipStatus(ShippingDTO sdto) {
		logger.debug(" DAO : updateShipStatus(ShippingDTO sdto) ");
		sqlSession.update(NAMESPACE+".updateShipStatus", sdto);
		
	}
	
	@Override
	public List<ShippingDTO> getPlanContent(String order_code) {
		logger.debug(" DAO : getPlanContent(String order_code)");
		return sqlSession.selectList(NAMESPACE+".getPlanContent", order_code);
	}

	@Override
	public List<ShippingDTO> planSearch(SearchDTO sed) {
		logger.debug(" DAO : planSearch(SearchDTO sed)");
		return sqlSession.selectList(NAMESPACE+".planSearch", sed);
	}
	
	@Override
	public ShippingDTO getId(String order_code) {
		
		return sqlSession.selectOne(NAMESPACE+".getId", order_code);
	}
	
	@Override
	public String checkUpdatePw(String user_id) {
		return sqlSession.selectOne(NAMESPACE+".checkUpdatePw", user_id);
		
	}
	
	@Override
	public List<String> getScheduleDate(Date schedule_date) {
		
		return sqlSession.selectList(NAMESPACE+".getScheduleDate", schedule_date);
	}
	
	@Override
	public void updateSchedule(ShippingDTO sdto) {
		sqlSession.update(NAMESPACE+".updateSchedule",sdto );
		
	}
	
	@Override
	public ShippingDTO checkOrderDate(String order_code) {
		
		return sqlSession.selectOne(NAMESPACE+".checkOrderDate", order_code);
	}
	
	@Override
	public AcceptSaveDTO getOrderInfo(String order_code) {
		
		return sqlSession.selectOne(NAMESPACE+".getOrderInfo", order_code);
	}
	
	@Override
	public int countShipStatus(String ship_status) {
		
		return sqlSession.selectOne(NAMESPACE+".countShipStatus",ship_status );
	}
	
	@Override
	public List<ShippingDTO> getStatusList(String ship_status) {
		
		return sqlSession.selectList(NAMESPACE+".getStatusList",ship_status );
	}
	
	@Override
	public void updateShipDate(ShippingDTO sdto) {
		sqlSession.update(NAMESPACE+".updateShipDate",sdto );
		
	}
	
	@Override
	public void makeShipCode(ShippingDTO sdto) {
		sqlSession.update(NAMESPACE+".makeShipCode",sdto );
		
	}
	@Override
	public ShippingDTO getShipDate(String order_code) {
		
		return sqlSession.selectOne(NAMESPACE+".getShipDate",order_code);
	}
	
	@Override
	public List<ShippingDTO> getUserShipPlanList(String user_id) {
		
		return sqlSession.selectList(NAMESPACE+".getUserShipPlanList",user_id);
	}
	
	@Override
	public List<ShippingDTO> instructionList() {
		
		return sqlSession.selectList(NAMESPACE+".instructionList");
	}
	
	@Override
	public int countShipProgressing(String progress_status) {
		
		return sqlSession.selectOne(NAMESPACE+".countShipProgressing", progress_status);
	}
	
	@Override
	public List<ShippingDTO> GetprogressList(String progress_status) {
		
		return sqlSession.selectList(NAMESPACE+".GetprogressList", progress_status);
	}
	
	@Override
	public void updateShipProgressing(ShippingDTO sdto) {
		
		sqlSession.update(NAMESPACE+".updateShipProgressing", sdto);
		
	}
	
	@Override
	public List<ShippingDTO> getshipContent(String order_code) {
		
		return sqlSession.selectList(NAMESPACE+".getshipContent", order_code);
	}
	
	@Override
	public List<ShippingDTO> userInstructionList(String user_id) {
		return sqlSession.selectList(NAMESPACE+".userInstructionList", user_id);
	}
	
	@Override
	public List<ShippingDTO> shippingSearch(SearchDTO sed) {
		
		return sqlSession.selectList(NAMESPACE+".shippingSearch", sed);
	}
	
	@Override
	public void udpateSaleStatus(ShippingDTO sdto) {
		
		sqlSession.update(NAMESPACE+".udpateSaleStatus", sdto);
	}
}
