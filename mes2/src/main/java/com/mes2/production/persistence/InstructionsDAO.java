package com.mes2.production.persistence;

import java.util.List;

import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.etc.InstructionsSearchParam;
import com.mes2.production.etc.RequestMaterialsDTO;

public interface InstructionsDAO {

	public int insert(InstructionsDTO instructionsDTO);
	
	public int updateStart(InstructionsDTO instructionsDTO);
	
	public InstructionsDTO selectByCode(String code);
	
	public List<InstructionsDTO> selectByParamCode(InstructionsSearchParam param);
	public List<InstructionsDTO> selectByParamSoiCode(InstructionsSearchParam param);
	public List<InstructionsDTO> selectByParamMdpCode(InstructionsSearchParam param);
	
	public List<InstructionsDTO> selectByStateAndDate(InstructionsSearchParam param);
	
	//작업지시용 최신작업지시코드 호출
	public String searchLastIsCode(String searchIsCode);
	
	public int updateComplete(InstructionsDTO instructionsDTO);
	
	public int updateState(InstructionsDTO instructionsDTO);

	public RequestMaterialsDTO selectBySopCodeForMaterials(String sopCode);
	
	public int updateAccept(InstructionsDTO instructionsDTO);
	
	public InstructionsDTO selectBySopCode(String sopCode, String state);
	
	public List<InstructionsDTO> selectByState(String state);
}
