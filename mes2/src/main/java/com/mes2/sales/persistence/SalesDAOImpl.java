package com.mes2.sales.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.sales.domain.SalesDTO;

@Repository
public class SalesDAOImpl implements SalesDAO {

	private static final Logger logger = LoggerFactory.getLogger(SalesDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE ="com.mes2.mapper.SalesMapper";
	
	@Override
	public List<SalesDTO> getPlanSalesList() {
		logger.debug(" DAO : getPlanSalesList() ");
		return sqlSession.selectList(NAMESPACE+".getPlanSalesList");
	}
	
	@Override
	public List<SalesDTO> getPlanContent(String order_code) {
		logger.debug(" DAO : getPlanContent() ");
		return sqlSession.selectList(NAMESPACE+".getPlanContent",order_code);
	}
	
	@Override
	public void rejectSales(String order_code) {
		logger.debug(" DAO : rejectSales(String order_code) ");
		sqlSession.update(NAMESPACE+".rejectSales", order_code);
		
	}
}
