package com.mes2.materials.service;

import java.util.List;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.PurchaseDTO;
import com.mes2.materials.domain.SearchDTO;

public interface PurchaseService {

	// 발주 신청 
	public void purchaseOrder(PurchaseDTO pdto) throws Exception;

	// 발주 조회 
	public List<PurchaseDTO> PurchaseInfo(PurchaseDTO pdto, Criteria cri, SearchDTO sdto) throws Exception;
	
	// 발주 상태 업데이트
	public int updateOrderStatus(String status, String product_code) throws Exception;
	
	// 상태 업데이트 된 발주 리스트
	public List<PurchaseDTO> getUpdateStatus(String product_code) throws Exception;
	
	
	// stock 입고 수량 업데이트 
	public void updateQuantity(String product_code, int quantity, String category) throws Exception;
	
	// in_warehouse 입고 수량 업데이트 
	public void MaterialReceipt(String product_code, int quantity) throws Exception;
	
	// 페이징 처리
	public List<PurchaseDTO> purchaseListPage(Criteria cri, SearchDTO sdto) throws Exception;
	public int totalPurchaseCount(SearchDTO sdto) throws Exception;
	
	// 검색 
	public List<PurchaseDTO> searchMaterial(String searchType, String keyword) throws Exception;
}
