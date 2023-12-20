package com.mes2.service;

import java.util.List;

import com.mes2.system.domain.CommonCodeDTO;

public interface CommonCodeService {
	
	//공통코드리스트 출력
	public List<CommonCodeDTO> getCommoncodeList(CommonCodeDTO dto);
	
	
	//공통코드수정 실행
	public void commoncodeUpdate(CommonCodeDTO dto);
	
	//공통코드조회 실행
	public CommonCodeDTO CommoncodeInfo(int code_index);
	
	
	//공통코드등록 실행
	public void insertCommonCode(CommonCodeDTO dto);
	
	//공통코드삭제 실행
	public int deleteCommonCode(CommonCodeDTO dto);
	
	
	

}
