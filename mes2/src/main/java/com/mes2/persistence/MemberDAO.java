package com.mes2.persistence;

import java.util.List;

import com.mes2.system.domain.MemberDTO;

public interface MemberDAO {
	
	

	
	//로그인처리
	public MemberDTO loginMember(MemberDTO dto);
	
	
	//사원정보조회
	public MemberDTO getMember(String emp_id);
	
	
	
	//사원정보수정
	public void updateMember(MemberDTO dto);
	
	
	
	//사원정보삭제
	public int deleteMember(MemberDTO dto);
	
		
	
	//사원등록하기
	public void insertMember(MemberDTO dto);
	
	
	//사원리스트출력하기
	public List<MemberDTO> getMemberList(MemberDTO dto);
	
	
	
	
	
}
