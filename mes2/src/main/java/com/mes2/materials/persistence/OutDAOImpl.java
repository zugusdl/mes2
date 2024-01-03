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
	public void registerOutbound(OutDTO odto) throws Exception {
		 logger.debug(" DAO : 출고 신청 registerOutbound(OutDTO odto) ");
			sqlSession.insert(NAMESPACE + ".addOut" , odto);
		
	}




	@Override
	public List<OutDTO> getAllOutboundInfo(OutDTO odto) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".getOutList");
	}




	@Override
	public void updateQuantity(String product_code, int quantity, String category) throws Exception {
		  Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("product_code", product_code);
	        paramMap.put("quantity", quantity);
	        paramMap.put("category", category);

	        sqlSession.update(NAMESPACE + ".OutupdateQuantity", paramMap);
		
	}

	
	
	
}
