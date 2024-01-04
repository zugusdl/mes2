package com.mes2.materials.service;

import java.util.List;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.InDTO;

public interface InService {
	
	// 입고 등록 
	public void registerIncomingStock(InDTO idto) throws Exception;
	
	// 입고 조회 
	public List<InDTO> getIncomingStockInfo(InDTO idto, Criteria cri) throws Exception;
	
	// 입고 수량 업데이트 
	public void updateQuantity(String product_code, int quantity, String category) throws Exception;
	  
	// 페이징 처리 
	public List<InDTO> InListPage(Criteria cri) throws Exception;
	public int totalInCount() throws Exception;
}
