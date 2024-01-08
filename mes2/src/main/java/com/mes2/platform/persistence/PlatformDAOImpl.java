package com.mes2.platform.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.platform.domain.mdbDTO;
import com.mes2.platform.domain.mdpDTO;

@Repository
public class PlatformDAOImpl implements PlatformDAO {

	private static final Logger logger = LoggerFactory.getLogger(PlatformDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.mes2.mapper.platformMapper";

	@Override
	public mdbDTO customerLogin(mdbDTO mdto) throws Exception {
		logger.debug("DAO: customerLogin() 호출");
		return sqlSession.selectOne(NAMESPACE + ".login", mdto);
	}

	@Override
	public List<mdpDTO> inqueryProduct(String searchType, String search) throws Exception {
		logger.debug("DAO: inqueryProduct() 호출");
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchType", searchType);
		searchMap.put("search", search);
		
		logger.debug("DAO: inqueryProduct() 종료");
		logger.debug("DAO DAO DAO : "+sqlSession.selectList(NAMESPACE + ".inqueryProduct", searchMap).getClass());
		return sqlSession.selectList(NAMESPACE + ".inqueryProduct", searchMap);
	}

}
