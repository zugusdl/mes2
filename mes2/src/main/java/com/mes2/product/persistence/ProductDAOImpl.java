package com.mes2.product.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl implements ProductDAO{

	private final String NAMESPACE = "com.mes2.mapper.ProductMapper";
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SqlSession sqlSession;
	//테스트용 
	
	@Override
	public String getTime() {
		return sqlSession.selectOne(NAMESPACE+".getTime");
	}
	
}
