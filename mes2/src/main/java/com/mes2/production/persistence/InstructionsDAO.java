package com.mes2.production.persistence;

import com.mes2.production.domain.InstructionsDTO;

public interface InstructionsDAO {

	public void insert(InstructionsDTO instructionsDTO);
	
	public int updateStart(InstructionsDTO instructionsDTO);
	
	public InstructionsDTO selectByCode(String code);
}
