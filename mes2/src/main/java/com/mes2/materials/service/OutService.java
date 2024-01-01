package com.mes2.materials.service;

import java.util.List;

import com.mes2.materials.domain.OutDTO;

public interface OutService {

	public List<OutDTO> getSelect() throws Exception;
	
	public List<OutDTO> detailList(String product_code) throws Exception;
	
}
