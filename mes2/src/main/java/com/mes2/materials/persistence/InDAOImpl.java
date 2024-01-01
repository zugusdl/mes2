package com.mes2.materials.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.materials.domain.InDTO;

@Repository
public class InDAOImpl implements InDAO {

	private static final Logger logger = LoggerFactory.getLogger(InDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE ="com.mes2.mapper.MaterialsMapper";


	
	@Override
	public void insertIn(InDTO idto) throws Exception {
		 logger.debug(" DAO : 입고 신청 insertIn(InDTO idto) ");
		sqlSession.insert(NAMESPACE + ".addIn" , idto);
		
	}

	
	
	
	
	
	@Override
	public List<InDTO> listIn(InDTO idto) throws Exception {
		logger.debug(" DAO -  입고 전체 리스트 listIn(InDTO idto) ");   
		return sqlSession.selectList(NAMESPACE + ".getInList");
	}
	
	

	@Override
	public List<InDTO> detailSelect(String product_code) throws Exception {

		Map<String, String> paramMap = new HashMap<>();
		 paramMap.put("product_code", product_code); 
		 
		 return sqlSession.selectList(NAMESPACE + ".detailInList", paramMap);
		
	}


	
	
}
