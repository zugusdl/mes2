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
import com.mes2.production.etc.RequestMaterialsDTO;

import lombok.RequiredArgsConstructor;

@Repository
public class InstructionsDAOImpl implements InstructionsDAO {

	
	private final Logger log = LoggerFactory.getLogger(InstructionsDAOImpl.class);
	private final String NAMESAPCE="com.mes2.mapper.InstructionsMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public int insert(InstructionsDTO instructionsDTO) {
		log.debug("InstructionsDAO : insert 호출");
		log.debug("입력된  instructionsDTO의 값 : " + instructionsDTO.getCode());
		log.debug("입력된  instructionsDTO의 값 : " + instructionsDTO.getLine());
		log.debug("입력된  instructionsDTO의 값 : " + instructionsDTO.getMdpCode());
		log.debug("입력된  instructionsDTO의 값 : " + instructionsDTO.getSopCode());
		log.debug("입력된  instructionsDTO의 값 : " + instructionsDTO.getQuantity());
		
		return sqlSession.insert(NAMESAPCE+".insertInstructionForStandBy", instructionsDTO);

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
	public List<InstructionsDTO> selectByParamCode(InstructionsSearchParam param) {

		log.debug("Param : " +param.getStartTime());
		log.debug("@@@@@@@@@@@@@@@@@@@@@@@@Param : " +param.getEndTime());
		log.debug("@@@@@@@@@@@@@@@@@@@@@@@@Param 상태값 : " +param.getState());
		
		if(sqlSession.selectList(NAMESAPCE+".selectBySearchParamCode", param)==null) {
			log.debug("안에 아무것도 없음 -ㅅ-");
		}
		return sqlSession.selectList(NAMESAPCE+".selectBySearchParamCode", param);
	}



	@Override
	public List<InstructionsDTO> selectByParamSoiCode(InstructionsSearchParam param) {

		log.debug("Param : " +param.getStartTime());
		log.debug("@@@@@@@@@@@@@@@@@@@@@@@@Param : " +param.getEndTime());
		return sqlSession.selectList(NAMESAPCE+".selectBySearchParamSoiCode", param);
	}

	


	@Override
	public List<InstructionsDTO> selectByStateAndDate(InstructionsSearchParam param) {
		log.debug("Is : selectByStateAndDate 호출");
		return sqlSession.selectList(NAMESAPCE+".selectByStateAndDueTime", param);
	}



	@Override
	public List<InstructionsDTO> selectByParamMdpCode(InstructionsSearchParam param) {
		
		log.debug("Param : " +param.getStartTime());
		log.debug("@@@@@@@@@@@@@@@@@@@@@@@@Param : " +param.getEndTime());
		return sqlSession.selectList(NAMESAPCE+".selectBySearchParamMdpCode", param);
	}



	@Override
	public String searchLastIsCode(String searchIsCode) {
		String lastIsCode = sqlSession.selectOne(NAMESAPCE+".getLastInstructionsCode",searchIsCode);
		log.debug("InstructionDAO : 검색된 LastIsCode 날짜 : "+ lastIsCode);
		return lastIsCode;
	}



	@Override
	public int updateComplete(InstructionsDTO instructionsDTO) {
		log.debug("InstructionDAO : 업데이트");
		return sqlSession.update(NAMESAPCE+".updateComplete", instructionsDTO);
	}



	@Override
	public int updateState(InstructionsDTO instructionsDTO) {
		
		return sqlSession.update(NAMESAPCE+".updateState" , instructionsDTO);
	}



	@Override
	public RequestMaterialsDTO selectBySopCodeForMaterials(String sopCode) {
		log.debug("instructionsDAO : selectBySopCodeForMaterials 호출");
		RequestMaterialsDTO rqml = sqlSession.selectOne(NAMESAPCE+".selectByisCodeForMaterials", sopCode);
		log.debug(""+rqml.toString());
		return rqml;
	}



	@Override
	public int updateAccept(InstructionsDTO instructionsDTO) {
		log.debug("instructionsDAO : updateAccept 호출");
		int result = sqlSession.update(NAMESAPCE+".updateAccept",instructionsDTO);
		log.debug("@@@@@@@@@@@@Update 결과값 : "+ result);
		
		return result;
	}



	@Override
	public InstructionsDTO selectBySopCode(String sopCode, String state) {
		Map<String, String> paramMap = new HashMap();
		paramMap.put("sopCode", sopCode);
		paramMap.put("state", state);
		
		return sqlSession.selectOne(NAMESAPCE+".selectBySopCode",paramMap);
	}



	@Override
	public List<InstructionsDTO> selectByState(String state) {
		return sqlSession.selectList(NAMESAPCE+".selectByState", state);
	}
	
	
	
	
	
	
	

	
}
