package com.mes2.materials.service;

import java.util.List;

import com.mes2.materials.domain.OutDTO;

public interface OutService {

	// 출고 목록 조회
	public List<OutDTO> getOutList() throws Exception;
	
	// 출고 상세 조회
	public OutDTO getOutDetail(String out_index) throws Exception; 
		  
}
