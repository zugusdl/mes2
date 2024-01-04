package com.mes2.metadata.persistence;


import java.sql.Date;
import java.util.List;

import com.mes2.metadata.domain.Criteria;
import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.md_productDTO;

public interface MetadataDAO {
	
	//public List<md_productDTO> getproductListAll() throws Exception;
	
	//public List<md_productDTO> getproductdatefilter(Date start, Date end, String search) throws Exception;
	
	public int productinsert(md_productDTO dto) throws Exception;
	
	public int productupdate(md_productDTO dto) throws Exception;
	
	public int productdelete(md_productDTO dto) throws Exception;
	
	public int gettotalcount(alllistDTO dto) throws Exception;

	public List<md_productDTO> getlist(alllistDTO dto) throws Exception;
}
