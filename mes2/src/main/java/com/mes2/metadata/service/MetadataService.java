package com.mes2.metadata.service;

import java.util.List;

import com.mes2.metadata.domain.productDTO;

public interface MetadataService {
	
	// 발주 조회 
	public List<productDTO> productListAll() throws Exception;
	
}
