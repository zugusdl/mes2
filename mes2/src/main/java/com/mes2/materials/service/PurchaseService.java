package com.mes2.materials.service;

import java.util.List;

import com.mes2.materials.domain.PurchaseDTO;

public interface PurchaseService {

	// 발주 신청 
	public void purchaseOrder(PurchaseDTO pdto) throws Exception;

	
	// 발주 조회 
	public List<PurchaseDTO> PurchaseInfo(PurchaseDTO pdto) throws Exception;

	
	
	
	
	
	
	
	
	
	
	
	// 발주 상세 조회 
	public List<PurchaseDTO> PurchaseDetailInfo(PurchaseDTO pdto) throws Exception;

	
	// 발주 상태 업데이트
	public int updateOrderStatus(String status, String product_code) throws Exception;
	
	
	
	// 상태 업데이트 된 발주 리스트
	public List<PurchaseDTO> getUpdateStatus(String product_code) throws Exception;
	
	
	// 카테고리별 품목명으로 조회 
	public List<PurchaseDTO> getMaterialDataByName(String category, String name) throws Exception;
	
}
