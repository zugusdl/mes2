package com.mes2.service;

import java.util.List;


import com.mes2.domain.MemberDTO;

public interface MemberService {
	
	// 로그인처리
	public MemberDTO memberLogin(MemberDTO dto);
	
	//사원정보조회 동작
	public MemberDTO memberInfo(String emp_id);
	
	//사원정보수정 동작
	public void memberUpdate(MemberDTO dto);
	
	//사원정보삭제 동작
	public int delete(MemberDTO dto);

	//사원등록 동작
	public void memberJoin(MemberDTO dto);
	
	//사원리스트 출력
	public List<MemberDTO> getMemberList(MemberDTO dto);
	
	
}
