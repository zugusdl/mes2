package com.mes2.materials.persistence;

import java.util.List;

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
	
	// 카테고리별 품목명 조회 
	public List<PurchaseDTO> getSelectName(String category, String name) throws Exception;
		

}
