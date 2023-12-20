package com.mes2.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.system.domain.CommonCodeDTO;


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

	
	
	
	
	
	//공통코드조회

	@Override
	public CommonCodeDTO getCommoncode(int code_index) {
		logger.debug("DAO - 공통코드 조회 하기!");
	
		return sqlSession.selectOne(NAMESPACE+".getCommoncode",code_index);
	}





	//공통코드 수정
	@Override
	public void updateCommonCode(CommonCodeDTO dto) {
		logger.debug("DAO - 공통코드수정실행!");
		sqlSession.update(NAMESPACE+".updateCommonCode",dto);
		
	}





	// 공통코드 등록
	@Override
	public void insertCommonCode(CommonCodeDTO dto) {
		logger.debug("DAO - 공통코드등록실행!");
		sqlSession.insert(NAMESPACE+".insertCommoncode",dto);
		
	}




	
	// 공통코드 삭제
	@Override
	public int deleteCommonCode(CommonCodeDTO dto) {
		logger.debug("DAO - 공통코드삭제실행!");
		return sqlSession.delete(NAMESPACE+".deleteCommonCode",dto);
	}

	
	
	
	
	
	
	
	
	
	
}
