package com.mes2.materials.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.InDTO;

@Repository
public class InDAOImpl implements InDAO {

	private static final Logger logger = LoggerFactory.getLogger(InDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE ="com.mes2.mapper.MaterialsMapper";


	
	@Override
	public void registerInbound(InDTO idto) throws Exception {
		 logger.debug(" DAO : 입고 신청 registerInbound(InDTO idto) ");
		sqlSession.insert(NAMESPACE + ".addIn" , idto);
		
	}

	

	@Override
	public List<InDTO> getAllInboundInfo(InDTO idto) throws Exception {
		logger.debug(" DAO -  입고 전체 리스트 listIn(InDTO idto) ");   
		return sqlSession.selectList(NAMESPACE + ".getInList");
	}
	
	

	@Override
    public void updateQuantity(String product_code, int quantity, String category) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("product_code", product_code);
        paramMap.put("quantity", quantity);
        paramMap.put("category", category);

        sqlSession.update(NAMESPACE + ".InupdateQuantity", paramMap);
    }



	@Override
	public List<InDTO> getInListPage(int page) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".InlistPage",page);
	}



	@Override
	public List<InDTO> getInListPage(Criteria cri) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".InlistPage",cri);
	}



	@Override
	public int getInCount() throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".InCount");
	}
	
	

}
