package com.mes2.materials.persistence;

import java.util.List;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.PurchaseDTO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.domain.productDTO;

/**
 * DAO : 데이터 처리 객체 (디비 동작(Mybatis)을 제어)
 *
 */
public interface PurchaseDAO {

	// 발주 신청
	public void insertPurchase(PurchaseDTO pdto) throws Exception;

	// 발주 전체 리스트
	public List<PurchaseDTO> listPurchase(String searchType, String keyword, Criteria cri, SearchDTO sdto) throws Exception;

	// 발주 상태 업데이트
	public int updateOrderStatus(String status, int orders_index) throws Exception;

	// stock 수량 업데이트
	public void updateQuantity(String product_code, int quantity, String category) throws Exception;

	// in_warehouse 수량 업데이트
	public void MaterialReceipt(String product_code, int quantity) throws Exception;

	// 품목코드 리스트
	public productDTO getProductByCategory(String product_code) throws Exception;

	// 품목코드, 카테고리 리스트
	public List<productDTO> selectMaterialCategoryList(String category) throws Exception;

	// 개수 카운트
	public int getPurchaseCount(Criteria cri, String searchType, String keyword) throws Exception;
	 
	// 검색 
	public List<PurchaseDTO> searchMaterial(String searchType, String keyword, Criteria cri) throws Exception;

}
