package com.mes2.platform.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.platform.domain.MdbDTO;
import com.mes2.platform.domain.MdpDTO;

@Repository
public class PlatformDAOImpl implements PlatformDAO {

	private static final Logger logger = LoggerFactory.getLogger(PlatformDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.mes2.mapper.platformMapper";

	// 로그인
	@Override
	public MdbDTO customerLogin(MdbDTO mdto) throws Exception {
		logger.debug("DAO: customerLogin() 호출");
		return sqlSession.selectOne(NAMESPACE + ".login", mdto);
	}

	// 발주 신청 시 품목 목록 조회
	@Override
	public List<MdpDTO> inqueryProduct(String searchType, String search) throws Exception {
		logger.debug("DAO: inqueryProduct() 호출");
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchType", searchType);
		searchMap.put("search", search);
		
		logger.debug("DAO: inqueryProduct() 종료");
		return sqlSession.selectList(NAMESPACE + ".inqueryProduct", searchMap);
	}

	// 품목 하나 등록
	@Override
	public MdpDTO registProduct(String product_code) throws Exception {
		logger.debug("DAO: registProduct() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectProduct", product_code);
	}

	// 금일 주문건 개수
	@Override
	public int countTodayOrder(String todayDate) throws Exception {
		logger.debug("DAO: countTodayOrder() 호출");
		return sqlSession.selectOne(NAMESPACE + ".countTodayOrder", todayDate);
	}

}
