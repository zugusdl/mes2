package com.mes2.service;

import java.util.List;



import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.domain.MemberDTO;
import com.mes2.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	
	@Inject
	private MemberDAO mdao;
	
	
	//로그인처리
	@Override
	public MemberDTO memberLogin(MemberDTO dto) {
		logger.debug("Service 로그인처리 ");
		
		
		return mdao.loginMember(dto);
	}

	
	
	
	//사원정보조회
	@Override
	public MemberDTO memberInfo(String emp_id) {
		logger.debug("Service 조회처리");

		return mdao.getMember(emp_id);
	}



	//사원정보수정
	@Override
	public void memberUpdate(MemberDTO dto) {
		logger.debug("Service 수정처리");
		mdao.updateMember(dto);
		
	}



	//사원정보삭제
	@Override
	public int delete(MemberDTO dto) {
		logger.debug("Service 삭제처리");

		return mdao.deleteMember(dto);
	}



	//사원등록
	@Override
	public void memberJoin(MemberDTO dto) {
		logger.debug("DAO 사원등록 메서드호출 - 시작");
		mdao.insertMember(dto);
		logger.debug("DAO 사원등록 메서드호출 - 끝");

		
	}



	//사원리스트
	
	@Override
	public List<MemberDTO> getMemberList(MemberDTO dto) {
		logger.debug("DAO 사원리스트 메서드호출");
		
		List<MemberDTO> resultDTO =  mdao.getMemberList(dto);
		
		
		return resultDTO;
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
