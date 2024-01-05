package com.mes2.materials.persistence;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.PurchaseDTO;
import com.mes2.materials.domain.SearchDTO;

/**
 *	DAO : 데이터 처리 객체 (디비 동작(Mybatis)을 제어) 
 *
 */
public interface PurchaseDAO {

	// 발주 신청 
	public void insertPurchase(PurchaseDTO pdto) throws Exception; 
	
	
	// 발주 전체 리스트
	public List<PurchaseDTO> listPurchase(String searchType, String search, Criteria cri) throws Exception;

	
	// 발주 상태 업데이트 
	public int updateOrderStatus(String status, String product_code) throws Exception;
	
	
	// 업데이트 된 발주 상태 리스트 
	public List<PurchaseDTO> getUpdateStatus(String product_code) throws Exception;
	
	
	// stock 수량 업데이트
	public void updateQuantity(String product_code, int quantity, String category) throws Exception;
	
	
	// in_warehouse 수량 업데이트
	public void MaterialReceipt(String product_code, int quantity) throws Exception;

	
	// 카테고리, 품목명 불러오는 리스트
	public PurchaseDTO getProductByCategory(String product_code, String category) throws Exception;
	

	// 페이징 처리 
	public List<PurchaseDTO> getPurchaseListPage(int page) throws Exception;
	public List<PurchaseDTO> getPurchaseListPage(Criteria cri, SearchDTO sdto) throws Exception;

	public int getPurchaseCount(SearchDTO sdto) throws Exception; 
	
	// 검색
	public List<PurchaseDTO> searchMaterial(String searchType, String keyword) throws Exception;
}

