package com.mes2.materials.persistence;

import java.util.List;

import com.mes2.materials.domain.OutDTO;
import com.mes2.materials.domain.StockDTO;

public interface OutDAO {
	
	// 출고 목록 조회
	public List<OutDTO> getOutList() throws Exception;
	
	// 출고 상세 조회(출고코드 O)
	public OutDTO getOutDetail(String out_code) throws Exception;
	
	// 출고 상세 조회(출고코드 X)
	public OutDTO getOutInfo(String out_index) throws Exception;

	// 출고 품목 재고 조회
	public List<StockDTO> getStockList(String product_code) throws Exception;
	
	// 출고 품목 재고 등록
	public StockDTO registProduct(int stock_index) throws Exception;
	
	// 출고 품목 입력 시 Stock 테이블 출고 예정 수량에 입력
//	public void updatePlannedQuantity(List<StockDTO> stockDTO) throws Exception;
}
