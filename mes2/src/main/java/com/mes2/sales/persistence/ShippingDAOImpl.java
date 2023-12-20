package com.mes2.sales.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShippingDAOImpl implements ShippingDAO {

	private static final Logger logger = LoggerFactory.getLogger(ShippingDAOImpl.class);

	@Inject
	private SqlSession sqlSession;
	

	private static final String NAMESPACE ="com.mes2.mapper.SalesMapper";

}
