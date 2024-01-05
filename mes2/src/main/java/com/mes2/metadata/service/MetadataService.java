package com.mes2.metadata.service;


import java.sql.Date;
import java.util.List;

import com.mes2.metadata.domain.Criteria;
import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.common_DTO;
import com.mes2.metadata.domain.md_productDTO;

public interface MetadataService {
	
	public int productinsert(md_productDTO dto) throws Exception;
	
	public int productupdate(md_productDTO dto) throws Exception;
	
	public int productdelete(md_productDTO dto) throws Exception;
	
	public int gettotalcount(alllistDTO aDTO) throws Exception;
	
	public List<md_productDTO> getlist(alllistDTO aDTO) throws Exception;
	
	public List<common_DTO> selectbox() throws Exception;
	
	public List<common_DTO> selectbox2() throws Exception;

}
