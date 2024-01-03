package com.mes2.materials.service;

import java.util.List;

import com.mes2.materials.domain.OutDTO;

public interface OutService {

	// 출고 등록 
	public void registerOutcomingStock(OutDTO odto) throws Exception;
	
	// 출고 조회 
	public List<OutDTO> getOutcomingStockInfo(OutDTO odto) throws Exception;
	
	// 출고 업데이트 
	public void updateQuantity(String product_code, int quantity, String category) throws Exception;
		  
}
