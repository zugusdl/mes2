package com.mes2.materials.persistence;

import java.util.List;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.InDTO;
import com.mes2.materials.domain.SearchDTO;

public interface InDAO {

	// 입고 등록 
	public void registerInbound(InDTO idto) throws Exception;
	
	// 입고 전체 리스트
	public List<InDTO> getAllInboundInfo(InDTO idto, Criteria cri, SearchDTO sdto, String status) throws Exception;
	
	// 입고 수량 업데이트
	public void InupdateQuantity(String product_code, int quantity, String category) throws Exception;
		
	// 페이징 처리 
	public List<InDTO> getInListPage(int page) throws Exception;
	public List<InDTO> getInListPage(Criteria cri) throws Exception;

	public int getInCount() throws Exception;

}
