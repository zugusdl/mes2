package com.mes2.materials.service;

import java.util.List;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.InDTO;
import com.mes2.materials.domain.PurchaseDTO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.domain.productDTO;

public interface InService {
	
	// 입고 등록
	public void insertIncomingRequest(InDTO idto) throws Exception; 
	
	// 입고 전체 조회 
	public List<InDTO> getIncomingStockInfo(String searchType, String keyword, Criteria cri, SearchDTO sdto) throws Exception;
	
	// 입고 상태 업데이트
	public int updateInStatus(String status, int in_index) throws Exception;  
	
	// 품목코드 리스트
	public productDTO listIncomingProductCodes(String product_code) throws Exception;
	
	// 카테고리 리스트 
	public List<productDTO> getIncomingProductCodesByCategory(String category) throws Exception;
	
	// stock 입고 수량 업데이트 
	public void InupdateQuantity(String product_code, int quantity, String category) throws Exception;
	
	
	
	
	// 개수 카운트
	public int totalInCount(Criteria cri, String searchType, String keyword) throws Exception;

	// 검색 
	public List<InDTO> searchIn(String searchType, String keyword, Criteria cri) throws Exception;

}
