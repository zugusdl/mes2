package com.mes2.production.service;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mes2.production.domain.ProductionLineDTO;
import com.mes2.production.persistence.ProductionLineDAO;

@Service
public class ProductionLineServiceImpl implements ProductionLineService{

	@Inject
	private ProductionLineDAO productionalLineDAO;
	
	@Override
	public void save(ProductionLineDTO productionLineDTO) {
		productionalLineDAO.insertProductionLine(productionLineDTO);
		
	}

	@Override
	public List<ProductionLineDTO> findByDate(Date startDate, Date endDate){
		return productionalLineDAO.selectByDate(startDate, endDate);
	}
	
	private String createLotCode(Date date, String mdpCode) {
		
		return null;
	}

	@Override
	public ProductionLineDTO findByIsCode(String isCode) {
		return productionalLineDAO.selectByIsCode(isCode);
	}
	
	
	
	

}
