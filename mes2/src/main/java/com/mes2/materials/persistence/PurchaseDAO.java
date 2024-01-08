package com.mes2.materials.persistence;

import java.util.List;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.PurchaseDTO;

/**
 *	DAO : 데이터 처리 객체 (디비 동작(Mybatis)을 제어) 
 *
 */
public interface PurchaseDAO {

	// 발주 신청 
	public void insertPurchase(PurchaseDTO pdto) throws Exception; 
	
	// 발주 전체 리스트
	public List<PurchaseDTO> listPurchase(PurchaseDTO pdto) throws Exception;
	
	// 상세 조회 리스트 
	public List<PurchaseDTO> detailPurchase(PurchaseDTO pdto) throws Exception; 
	
	// 발주 상태 업데이트 
	public int updateOrderStatus(String status, String product_code) throws Exception;
	
	// 업데이트 된 발주 상태 리스트 
	public List<PurchaseDTO> getUpdateStatus(String product_code) throws Exception;
	
	// 발주 수량 업데이트
	public void updateQuantity(String product_code, int quantity, String category) throws Exception;

	// 페이징 처리 
	public List<PurchaseDTO> getPurchaseListPage(int page) throws Exception;
	public List<PurchaseDTO> getPurchaseListPage(Criteria cri) throws Exception;

	public int getPurchaseCount() throws Exception;
	
}

