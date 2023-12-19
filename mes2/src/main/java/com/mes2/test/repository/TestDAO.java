package com.mes2.test.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.production.controller.ProductController;
import com.mes2.test.domain.DateTest;

@Repository
public class TestDAO {

	
	private final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	private final String NAMESPACE="com.mes2.mapper.DateTestMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	public void insertDate(DateTest dateTest) {
		log.debug("날려받은 dateTest1 의 값" + dateTest.getDate1());
		log.debug("날려받은 dateTest2 의 값" + dateTest.getDate2());
		log.debug("날려받은 dateTest 의 값" + dateTest.getDate1().getClass());
		log.debug("날려받은 dateTest 의 값" + dateTest.getDate2().getClass());
		
		sqlSession.insert(NAMESPACE+".insertDateTest", dateTest);
	}
	
	public List<DateTest> selectDate(DateTest dateTest){
		return sqlSession.selectList(NAMESPACE+".selectDate",dateTest);
	}
}
