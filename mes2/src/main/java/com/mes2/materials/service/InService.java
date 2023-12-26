package com.mes2.materials.service;

import java.util.List;

import com.mes2.materials.domain.InDTO;

public interface InService {
	
	public List<InDTO> inSelect() throws Exception;
	
	public List<InDTO> detailList(String in_code) throws Exception;
}
