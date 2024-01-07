package com.mes2.materials.persistence;

import java.util.List;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.InDTO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.domain.productDTO;

public interface InDAO {

	// 입고 등록 버튼시 입고코드 부여
	public int updateIncomingRequest(String in_code, String pd_lot, String user_id) throws Exception;
	
	// 입고 전체 리스트
	public List<InDTO> getAllInboundInfo(String searchType, String keyword, Criteria cri, SearchDTO sdto) throws Exception;
	
	// 완제품 수량 등록
	public void insertStock(int quantity, String product_code, String category) throws Exception;
	// 완제품 수량 업데이트
	public void updateStockOnIncoming(int quantity, String product_code) throws Exception;
	// stock 품목코드 리스트
	public List<InDTO> selectStock(String product_code) throws Exception;
	
	
	// 입고 수량 업데이트
	//public void InupdateQuantity(String product_code, int quantity, String category) throws Exception;
		
	// 로트번호 하나 불러오는 리스트 
	public InDTO listIncomingProductCodes(String pd_lot) throws Exception;

	// 상태 완료된 품목 리스트 
	public List<InDTO> InDetailCompletedWarehouse(String searchType, String keyword, Criteria cri, SearchDTO sdto) throws Exception;
	
	// 상태 완료된 품목 리스트 개수 카운트 
	public int inDetailCount(Criteria cri, String searchType, String keyword) throws Exception;
	
	
	// 원재료 코드 
	public String selectMaxMaterialsLot(String pd_lot) throws Exception;
	
	// 개수 카운트
	public int getInCount(Criteria cri, String searchType, String keyword) throws Exception;

	// 검색 
	public List<InDTO> searchIn(String searchType, String keyword, Criteria cri) throws Exception;
	
	
}
