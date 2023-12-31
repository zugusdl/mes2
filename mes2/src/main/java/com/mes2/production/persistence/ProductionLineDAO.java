package com.mes2.production.persistence;

import java.sql.Date;
import java.util.List;

import com.mes2.production.domain.ProductionLineDTO;

public interface ProductionLineDAO {
	
	public int insertProductionLine (ProductionLineDTO lineDTO);
	
	public List<ProductionLineDTO> selectByDate(Date startDate, Date endDate);
	
	public ProductionLineDTO selectByIsCode(String isCode);
	
	public int updateState(ProductionLineDTO productionLineDTO);
	
	public int updateComplete(ProductionLineDTO productionLineDTO);

}
