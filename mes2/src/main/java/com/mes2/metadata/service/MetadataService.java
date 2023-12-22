package com.mes2.metadata.service;

import java.util.List;

import com.mes2.metadata.domain.productDTO;

public interface MetadataService {
	
	public List<productDTO> productListAll() throws Exception;
	
}
