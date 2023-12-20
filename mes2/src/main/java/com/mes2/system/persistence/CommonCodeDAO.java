package com.mes2.system.persistence;

import java.util.List;

import com.mes2.system.domain.CommonCodeDTO;

public interface CommonCodeDAO {
	
	
	
	// 공통코드 리스트 출력
	public List<CommonCodeDTO> getCommoncodeList(CommonCodeDTO dto);
	
	
	//공통코드 조회
	public CommonCodeDTO getCommoncode(int code_index); 
	
	
	//공통코드 수정
	public void updateCommonCode(CommonCodeDTO dto);
	
	
	//공통코드 등록
	public void insertCommonCode(CommonCodeDTO dto);

	//공통코드 삭제
	public int deleteCommonCode(CommonCodeDTO dto);
	
	
}
