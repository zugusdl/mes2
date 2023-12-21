package com.mes2.production.service;



import org.springframework.stereotype.Service;

import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.persistence.InstructionsDAO;
import com.mes2.production.vo.InstructionsState;

public interface InstructionsService {

	public void saveInstructions(InstructionsDTO isInstructionsDTO);
	
	public InstructionsDTO findByCode(String code);
	
	public InstructionsState transType(String enumType);
	//
	
}
