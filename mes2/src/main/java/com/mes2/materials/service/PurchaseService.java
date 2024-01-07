package com.mes2.materials.service;

import java.util.List;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.PurchaseDTO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.domain.productDTO;

public interface PurchaseService {

	// 발주 신청 
	public void purchaseOrder(PurchaseDTO pdto) throws Exception;

	// 발주 전체 조회 
	public List<PurchaseDTO> PurchaseInfo(String searchType, String keyword, Criteria cri, SearchDTO sdto) throws Exception;

	// 품목코드 리스트
	public productDTO getProductByCategory(String product_code) throws Exception;
	
	// 카테고리 리스트 
	public List<productDTO> selectMaterialCategoryList(String category) throws Exception;
	
	// 발주 상태 업데이트
	public int updateOrderStatus(String status, int orders_index) throws Exception;
	
	// stock 입고 수량 업데이트 
	public void updateQuantity(String product_code, int quantity, String category) throws Exception;
	
	// in_warehouse 입고 수량 업데이트 
	public void MaterialReceipt(String product_code, int quantity , String pd_lot) throws Exception;
	
	// 개수 카운트
	public int totalPurchaseCount(Criteria cri, String searchType, String keyword) throws Exception;
	  
	// 검색 
	public List<PurchaseDTO> searchMaterial(String searchType, String keyword, Criteria cri) throws Exception;

	
}
