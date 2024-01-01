package com.mes2.materials.persistence;

import java.util.List;

import com.mes2.materials.domain.OutDTO;

public interface OutDAO {

	// 출고 정보 리스트 조회 
	public List<OutDTO> getSelect() throws Exception;
	

	public List<OutDTO> detailSelect(String product_code) throws Exception;
}
