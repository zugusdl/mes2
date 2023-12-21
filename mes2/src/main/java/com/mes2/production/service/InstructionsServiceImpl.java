package com.mes2.production.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.etc.InstructionsSearchParam;
import com.mes2.production.persistence.InstructionsDAO;
import com.mes2.production.vo.InstructionsState;

@Service
public class InstructionsServiceImpl implements InstructionsService {

	@Inject
	private InstructionsDAO InstructionsDAO;
	
	@Override
	public void saveInstructions(InstructionsDTO isInstructionsDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public InstructionsDTO findByCode(String code) {
		
		return null;
	}

	@Override
	public InstructionsState transType(String enumType) {
		InstructionsState state= null;
		if(enumType.equals("REQUESTED")) {
			state = InstructionsState.REQUESTED;
		}else if(enumType.equals("WAITING")){
			state = InstructionsState.WAITING;
		}else if(enumType.equals("PROGRESSING")){
			state = InstructionsState.PROGRESSING;
		}else if(enumType.equals("COMPLETE")) {
			state = InstructionsState.COMPLETE;
		}
		
		return state;
	}
	//

}
