package com.mes2.materials.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.materials.domain.OutDTO;

@Repository
public class OutDAOImpl implements OutDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(OutDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE ="com.mes2.mapper.MaterialsMapper";

	@Override
	public List<OutDTO> getSelect() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".getOutList");
	}

	@Override
	public List<OutDTO> detailSelect(String product_code) throws Exception {
		
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("product_code", product_code);
		
		return sqlSession.selectList(NAMESPACE + ".detailOutList", paramMap);
	}
	
	
	
	
}
