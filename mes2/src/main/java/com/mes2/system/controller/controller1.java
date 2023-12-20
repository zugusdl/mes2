package com.mes2.system.controller;

import java.io.IOException;
import java.util.Collections;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes2.system.domain.CommonCodeDTO;
import com.mes2.system.domain.MemberDTO;
import com.mes2.system.service.CommonCodeService;
import com.mes2.system.service.MemberService;

@Controller
@RequestMapping("/system/*")
public class controller1 {

	private static final Logger logger = LoggerFactory.getLogger(controller1.class);

	@Inject
	private MemberService mService;

	@Inject
	private CommonCodeService cService;

	// http://localhost:8088/system/login
	// 로그인 페이지호출
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(Model model, @CookieValue(value = "rememberedId", required = false) String rememberedId) {
		model.addAttribute("rememberedId", rememberedId);
		logger.debug("loginget() 호출!");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberDTO dto, HttpSession session, HttpServletResponse response,
			@RequestParam(value = "remember", required = false) String remember) {
		logger.debug("loginpost() 호출!");
		logger.debug("전달정보 :" + dto);

		MemberDTO resultDTO = mService.memberLogin(dto);

		if (resultDTO != null) {

			session.setAttribute("id", resultDTO.getUser_id());
			session.setAttribute("pw", resultDTO.getUser_pw());
			session.setAttribute("name", resultDTO.getUser_name());
			session.setAttribute("jumin", resultDTO.getUser_jumin());
			session.setAttribute("position", resultDTO.getUser_position());
			session.setAttribute("tel", resultDTO.getUser_tel());

			if (remember != null && remember.equals("chk")) {
				// 쿠키 7 일간 보존
				Cookie cookie = new Cookie("rememberedId", resultDTO.getUser_id());
				cookie.setMaxAge(604800);
				response.addCookie(cookie);
			}

			return "redirect:/system/main";
		}

		else {

			return "redirect:/system/login";
		}

	}

	// 로그아웃 하기
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		logger.info("logout 메서드 선언");
		HttpSession session = request.getSession();

		session.invalidate();

		logger.info("logout 완료!!");

		return "redirect:/system/login";
	}

	// 사원정보조회 하기
	@GetMapping(value = "/info")
	public void memberInfoGET(HttpSession session, Model model) {
		logger.debug("/members/info 호출 -> memberInfoGET()실행");

		// ID정보를 받아오기(세션영역)
		String id = (String) session.getAttribute("id");
		logger.debug(" 아이디 정보 : " + id);
		// 서비스 -> id를 사용해서 회원정보 모두 조회 동작
		MemberDTO dto = mService.memberInfo(id);
		logger.debug(" dto : " + dto);
		// DB에서 조회된 결과를 view페이지로 전달 => Model 객체 생성
		model.addAttribute("dto", dto);

		// 이름이 없는경우 전달되는 데이터 클래스타입의 첫글자를 소문자로 바꿔서 이름으로 사용
		model.addAttribute(mService.memberInfo(id));

		// 페이지 이동(/login/info.jsp)
	}

	// 사원정보수정 하기 - GET
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void getmemberUpdateGET(HttpSession session, Model model) {
		logger.debug("/members/update 호출!");

		// 아이디 정보저장 (세션영역)
		String id = (String) session.getAttribute("id");
		// 서비스 -> 아이디에 해당하는 회원정보 조회
		// 연결된 뷰페이지(/login/update.jsp)에 정보전달
		model.addAttribute(mService.memberInfo(id));

	}

	// 사원정보수정 하기 -POST
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String memberUpdatePOST(MemberDTO dto, HttpServletRequest request) {
		logger.debug("/members/update -> memberUpdatePOST()");
		// 한글처리(인코딩 - 필터에서 처리함!)
		// 전달정보 저장(폼태그 - 파라미터)
		logger.debug("수정할 정보 :" + dto);

		String tel = request.getParameter("user_tel1") + "-" + request.getParameter("user_tel2") + "-"
				+ request.getParameter("user_tel3");
		dto.setUser_tel(tel);

		// 서비스 - 회원정보 수정하는 동작
		mService.memberUpdate(dto);

		// 메인페이지로 이동
		return "redirect:/system/main";
	}

	// 사원목록리스트 출력- GET

	@RequestMapping(value = "/memberlist", method = RequestMethod.GET)
	public String memberlistGET(MemberDTO dto, Model model) {
		logger.debug("/members/memberlist 호출!");

		List<MemberDTO> resultDTO = mService.getMemberList(dto);
		model.addAttribute("memberlist", resultDTO);

		logger.debug("memberlist.jsp 이동!");

		return "/system/memberlist";
	}

	// 메인페이지 GET
	// http://localhost:8088/login/main

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String mainGET() {
		logger.debug(" /main/login 호출 -> mainGET() 실행");

		logger.debug("연결된 뷰페이지(/views/login/main.jsp) 이동 ");
		return "/system/main";
	}

	// 사원등록하기-GET
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void memberJoinGET() {
		logger.debug("memberJoinGET() 호출");
		// 연결된 뷰페이지로 이동
		logger.debug("/views/login/join.jsp");
	}

	// 사원등록하기-POST
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String memberJoinActionPOST(MemberDTO dto, HttpServletRequest request) {
		logger.debug("memberJoinPOST() 호출");

		// 주민번호,전화번호는 jumin,tel 으로 받아서 따로 dto 에 세팅 ( ex.앞자리(user_jumin) -
		// 뒷자리(user_jumin2) )

		String jumin = request.getParameter("user_jumin") + "-" + request.getParameter("user_jumin2");
		String tel = request.getParameter("user_tel1") + "-" + request.getParameter("user_tel2") + "-"
				+ request.getParameter("user_tel3");
		dto.setUser_jumin(jumin);
		dto.setUser_tel(tel);

		// 전달정보 저장
		logger.debug("dto :" + dto);

		// DB에 정보를 저장 ( 기존 JSP => new MemberDAO().method() 호출; / 지금 Service 객체사용 )
		logger.debug("서비스 사원등록 동작을 호출 ! - 시작");
		mService.memberJoin(dto);

		logger.debug("서비스 사원등록 동작을 호출 ! - 끝");

		// 페이지 이동 (로그인-/members/login)
		return "redirect:/system/main";
	}

	// admin 사원정보 수정하기 GET

	@RequestMapping(value = "/adminupdate", method = RequestMethod.GET)
	public void adminUpdateGET(MemberDTO dto, Model model, HttpServletRequest request) {
		logger.debug("/members/adminupdate -> adminUpdateGET()");
		logger.debug("/members/update 호출!");

		// 아이디 정보저장 (파라미터로 받아온값-> memberlist의 form에 명시! )
		String id = request.getParameter("user_id");
		// 서비스 -> 아이디에 해당하는 회원정보 조회
		// 연결된 뷰페이지(/members/adminupdate.jsp)에 정보전달
		model.addAttribute(mService.memberInfo(id));

		logger.debug("세션 값 : " + id);

		logger.debug("/members/adminupdate.jsp 페이지이동");

	}

	// admin 사원정보 수정하기 POST

	@RequestMapping(value = "/adminupdate", method = RequestMethod.POST)
	public String adminUpdatePOST(MemberDTO dto, HttpServletRequest request, Model model) {
		logger.debug("/members/update -> memberUpdatePOST()");
		// 한글처리(인코딩 - 필터에서 처리함!)
		// 전달정보 저장(폼태그 - 파라미터)
		logger.debug("수정할 정보 :" + dto);

		String tel = request.getParameter("user_tel1") + "-" + request.getParameter("user_tel2") + "-"
				+ request.getParameter("user_tel3");
		dto.setUser_tel(tel);

		logger.debug("넘어온 url : " + request.getPathInfo());

		// 서비스 - 회원정보 수정하는 동작
		mService.memberUpdate(dto);

		// 메인페이지로 이동
		return "redirect:/system/memberlist";
	}

	// admin 사원정보 삭제하기 GET

	@RequestMapping(value = "/admindelete", method = RequestMethod.GET)
	public void adminDeleteGET(HttpSession session, Model model, HttpServletRequest request) {
		logger.debug("/login/admindelete -> memberDeleteGET()");
		logger.debug("/login/admindelect.jsp 페이지이동");

		String id = request.getParameter("user_id");
		model.addAttribute(mService.memberInfo(id));

	}

	// admin 사원정보 삭제하기 POST

	@RequestMapping(value = "/admindelete", method = RequestMethod.POST)
	public String adminDeletePOST(MemberDTO dto, HttpServletRequest request, Model model) {
		logger.debug("/login/delete -> memberDeletePOST()");
		// 한글처리(인코딩 - 필터에서 처리함!)
		// 전달정보 저장(폼태그 - 파라미터)
		logger.debug("수정할 정보 :" + dto);

		logger.debug("넘어온 url : " + request.getPathInfo());

		// 서비스 - 회원정보 삭제하는 동작
		mService.delete(dto);

		// 메인페이지로 이동
		return "redirect:/system/memberlist";
	}

	// 공통코드리스트 출력하기 GET

	@RequestMapping(value = "/commoncodelist", method = RequestMethod.GET)
	public String commoncodelistGET(CommonCodeDTO dto, Model model) {
		logger.debug("/login/commoncodelist 호출!");

		List<CommonCodeDTO> resultDTO = cService.getCommoncodeList(dto);
		model.addAttribute("commoncodelist", resultDTO);

		logger.debug("commoncodelist.jsp 이동!");

		return "/system/commoncodelist";
	}

	// 공통코드정보 수정하기 GET

	@RequestMapping(value = "/commoncodeupdate", method = RequestMethod.GET)
	public void commoncodeUpdateGET(CommonCodeDTO dto, Model model, HttpServletRequest request) {
		logger.debug("/login/commoncodeupdate -> commoncodeUpdateGET()");
		logger.debug("/login/update 호출!");

		// 아이디 정보저장 (세션영역)
		int code_index = Integer.parseInt(request.getParameter("code_index"));

		// 서비스 -> 아이디에 해당하는 회원정보 조회
		// 연결된 뷰페이지(/members/adminupdate.jsp)에 정보전달
		model.addAttribute(cService.CommoncodeInfo(code_index));

		logger.debug("세션 값 : " + code_index);

		logger.debug("/login/commoncodeupdate.jsp 페이지이동");

	}

	// 공통코드 수정하기 POST

	@RequestMapping(value = "/commoncodeupdate", method = RequestMethod.POST)
	public String commonCodeUpdatePOST(CommonCodeDTO dto, HttpServletRequest request, Model model) {
		logger.debug("/login/commoncodeupdate -> commoncodeUpdatePOST()");
		// 한글처리(인코딩 - 필터에서 처리함!)
		// 전달정보 저장(폼태그 - 파라미터)
		logger.debug("수정할 정보 :" + dto);

		logger.debug("넘어온 url : " + request.getPathInfo());

		// 서비스 - 회원정보 수정하는 동작
		cService.commoncodeUpdate(dto);

		// 메인페이지로 이동
		return "redirect:/system/commoncodelist";
	}

	// 공통코드등록하기-GET
	@RequestMapping(value = "/commoncodejoin", method = RequestMethod.GET)
	public void commonCodeJoinGET() {
		logger.debug("commonCodeJoinGET() 호출");
		// 연결된 뷰페이지로 이동
		logger.debug("/views/login/commoncodejoin.jsp");
	}

	// 공통코드등록하기-POST
	@RequestMapping(value = "/commoncodejoin", method = RequestMethod.POST)
	public String commonCodeJoinPOST(CommonCodeDTO dto) {
		logger.debug("memberJoinPOST() 호출");

		// 한글처리(인코딩 설정) => 필터사용

		// 전달정보 저장
		logger.debug("dto :" + dto);

		// DB에 정보를 저장 ( 기존 JSP => new MemberDAO().method() 호출; / 지금 Service 객체사용 )
		logger.debug("서비스 사원등록 동작을 호출 ! - 시작");
		cService.insertCommonCode(dto);

		logger.debug("서비스 사원등록 동작을 호출 ! - 끝");

		// 페이지 이동 (로그인-/members/login)
		return "redirect:/system/main";
	}

	// 공통코드삭제하기 GET

	@RequestMapping(value = "/commoncodedelete", method = RequestMethod.GET)
	public void commoncodeDeleteGET(CommonCodeDTO dto, Model model, HttpServletRequest request) {
		logger.debug("/login/commoncodedelete -> commoncodeDeleteGET()");
		logger.debug("/login/delete 호출!");

		// 아이디 정보저장 (세션영역)
		int code_index = Integer.parseInt(request.getParameter("code_index"));

		// 서비스 -> 아이디에 해당하는 회원정보 조회
		// 연결된 뷰페이지(/members/adminupdate.jsp)에 정보전달
		model.addAttribute(cService.CommoncodeInfo(code_index));

		logger.debug("세션 값 : " + code_index);

		logger.debug("/login/commoncodeupdate.jsp 페이지이동");

	}

	// 공통코드삭제하기 POST

	@RequestMapping(value = "/commoncodedelete", method = RequestMethod.POST)
	public String commonCodeDeletePOST(CommonCodeDTO dto, HttpServletRequest request, Model model) {
		logger.debug("/login/commoncodeupdate -> commoncodeUpdatePOST()");
		// 한글처리(인코딩 - 필터에서 처리함!)
		// 전달정보 저장(폼태그 - 파라미터)
		logger.debug("수정할 정보 :" + dto);

		logger.debug("넘어온 url : " + request.getPathInfo());

		// 서비스 - 회원정보 수정하는 동작
		cService.deleteCommonCode(dto);

		// 메인페이지로 이동
		return "redirect:/system/commoncodelist";
	}

	// 아이디중복체크 GET
	@ResponseBody
	@RequestMapping(value = "/duplicateid", method = RequestMethod.GET)
	public boolean duplicateId(@RequestParam("user_id") String test, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// String user_id = request.getParameter("user_id");

		logger.debug(" ajax 호출");
		// logger.debug("넘겨받은 user_id : " + user_id);
		logger.debug("넘겨받은 test : " + test);

		
		  boolean result;
		  
		  if(mService.isDuplicateId(test).isEmpty()) {
			  result= false;
		  }else {
			  result= true;
		  }
		 
		 
		 
		return result;

	}

	// http://localhost:8088/system/login

}
