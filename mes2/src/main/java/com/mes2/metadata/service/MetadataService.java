package com.mes2.metadata.service;


import java.sql.Date;
import java.util.List;

import com.mes2.metadata.domain.md_productDTO;

public interface MetadataService {
	
	public List<md_productDTO> productListAll() throws Exception;
	
	public List<md_productDTO> productdatefilter(Date start, Date end, String search) throws Exception;
	
	public int productinsert(md_productDTO dto) throws Exception;
}
