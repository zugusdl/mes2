package com.mes2.materials.persistence;

import java.util.List;

import com.mes2.materials.domain.InDTO;

public interface InDAO {

	// 입고 등록 
	public void insertIn(InDTO idto) throws Exception;
	
	// 입고 전체 리스트 
	public List<InDTO> listIn(InDTO idto) throws Exception;

	public List<InDTO> detailSelect(String product_code) throws Exception;
}
