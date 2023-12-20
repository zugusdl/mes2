package com.mes2.materials.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InDAOImpl implements InDAO {

	private static final Logger logger = LoggerFactory.getLogger(InDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE ="com.mes2.mapper.MaterialsMapper";
}
