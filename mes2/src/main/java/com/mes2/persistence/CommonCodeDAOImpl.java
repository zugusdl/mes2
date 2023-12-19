package com.mes2.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.domain.CommonCodeDTO;


@Repository
public class CommonCodeDAOImpl implements CommonCodeDAO {
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(CommonCodeDAOImpl.class);
	
	
	//DB 에 접근할 객체주입
	@Inject
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.mes2.mapper.CommoncodeMapper";
	
	
	
	//공통코드리스트 출력
	@Override
	public List<CommonCodeDTO> getCommoncodeList(CommonCodeDTO dto) {
		logger.debug("DAO - 공통코드리스트출력!");
		
		List<CommonCodeDTO> resultDTO = sqlSession.selectList(NAMESPACE+".getCommoncodeList",dto);
		
		return resultDTO;
	}

	
	
	
	
	
	
	
	
	
	
}
