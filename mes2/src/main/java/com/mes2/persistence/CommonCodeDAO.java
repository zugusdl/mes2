package com.mes2.persistence;

import java.util.List;

import com.mes2.domain.CommonCodeDTO;

public interface CommonCodeDAO {
	
	
	
	// 공통코드 리스트 출력
	public List<CommonCodeDTO> getCommoncodeList(CommonCodeDTO dto);
	
	
	
	
}
