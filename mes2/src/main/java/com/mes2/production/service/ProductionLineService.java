package com.mes2.production.service;

import java.sql.Date;
import java.util.List;

import com.mes2.production.domain.ProductionLineDTO;

public interface ProductionLineService {

	
	
	public void save(ProductionLineDTO productionLineDTO);
	
	public List<ProductionLineDTO> findByDate(Date paramDate);
	
	public ProductionLineDTO findByIsCode(String isCode);
}
