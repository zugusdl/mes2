package com.mes2.production.persistence;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.etc.InstructionsSearchParam;

import lombok.RequiredArgsConstructor;

@Repository
public class InstructionsDAOImpl implements InstructionsDAO {

	
	private final Logger log = LoggerFactory.getLogger(InstructionsDAOImpl.class);
	private final String NAMESAPCE="com.mes2.mapper.InstructionsMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void insert(InstructionsDTO instructionsDTO) {
		log.debug("InstructionsDAO : insert 호출");
		log.debug("입력된  instructionsDTO의 값 : " + instructionsDTO.getCode());
		log.debug("입력된  instructionsDTO의 값 : " + instructionsDTO.getLine());
		log.debug("입력된  instructionsDTO의 값 : " + instructionsDTO.getMdpCode());
		log.debug("입력된  instructionsDTO의 값 : " + instructionsDTO.getSoiCode());
		log.debug("입력된  instructionsDTO의 값 : " + instructionsDTO.getQuantity());
		
		instructionsDTO.setStartTime(Date.valueOf(LocalDate.now()));
		
		sqlSession.insert(NAMESAPCE+".insertInstructionForStandBy", instructionsDTO);

	}
	
	

	@Override
	public InstructionsDTO selectByCode(String code) {
		return sqlSession.selectOne(NAMESAPCE+".selectByCode", code);
		
	}

	


	@Override
	public int updateStart(InstructionsDTO instructionsDTO) {
		
		
		
		return sqlSession.update(NAMESAPCE+".updateStart");
	}



	@Override
	public List<InstructionsDTO> selectByParam(InstructionsSearchParam param) {
		return null;
	}
	
	//
	
	

	
}
