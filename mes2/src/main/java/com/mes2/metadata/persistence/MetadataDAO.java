package com.mes2.metadata.persistence;

import java.util.List;

import com.mes2.metadata.domain.productDTO;

public interface MetadataDAO {
	
	public List<productDTO> getproductListAll() throws Exception;
	
}
