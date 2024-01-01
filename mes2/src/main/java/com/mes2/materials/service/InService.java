package com.mes2.materials.service;

import java.util.List;

import com.mes2.materials.domain.InDTO;

public interface InService {
	
	// 입고 등록 
	public void registerStock(InDTO idto) throws Exception;
	
	// 입고 조회 
	public List<InDTO> InInfo(InDTO idto) throws Exception;
	
	
	
	
	public List<InDTO> detailList(String product_code) throws Exception;

	
}
