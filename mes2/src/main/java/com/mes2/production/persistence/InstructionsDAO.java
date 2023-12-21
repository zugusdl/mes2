package com.mes2.production.persistence;

import java.util.List;

import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.etc.InstructionsSearchParam;

public interface InstructionsDAO {

	public void insert(InstructionsDTO instructionsDTO);
	
	public int updateStart(InstructionsDTO instructionsDTO);
	
	public InstructionsDTO selectByCode(String code);
	
	public List<InstructionsDTO> selectByParam(InstructionsSearchParam param);
	//
}
