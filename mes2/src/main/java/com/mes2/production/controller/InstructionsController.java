package com.mes2.production.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.domain.ProductionLineDTO;
import com.mes2.production.etc.InstructionResultParam;
import com.mes2.production.etc.InstructionsSearchParam;
import com.mes2.production.exception.ValidationValueErrorException;
import com.mes2.production.service.InstructionsService;
import com.mes2.production.service.ProductionLineService;
import com.mes2.production.vo.InstructionsState;

@Controller
@RequestMapping("/instructions")
public class InstructionsController {

	@Inject
	private InstructionsService instructionsService;
	
	@Inject
	private ProductionLineService productionLineService;
	
	private final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	
	@GetMapping("/start")
	public String startGET() {
		
		InstructionsDTO instructsDto = new InstructionsDTO();
		instructsDto.setCode("20231219A0001");
		instructsDto.setLine(1);
		instructsDto.setMdpCode("A1111");
		instructsDto.setType("F");
		instructsDto.setSopCode("S1111");
		instructsDto.setState("S");
		
		try {
			instructionsService.saveInstructions(instructsDto);
		} catch (ValidationValueErrorException e) {
			
			e.printStackTrace();
		}
		
		return "ok";
	}
	
	//http://localhost:8088/instructions/detail/20231219A0001
	@GetMapping("/detail/{code}")
	public String detailGET(@PathVariable("code")String code) {
		
		//
		return null;
	}
	
	@GetMapping("/search")
	public String searchGET(Model model,@ModelAttribute(value = "searchStartDate") String searchStartDate,
			@ModelAttribute(value="searchEndDate") String searchEndDate, 
			@ModelAttribute(value = "searchCode") String searchCode,
			@ModelAttribute(value="searchType") String searchType
			/*@ModelAttribute(value="state") InstructionsState state */) {
		
		log.debug("isController : 넘겨받은 startDate : " + searchStartDate);
		log.debug("isController : 넘겨받은 endDate : " + searchEndDate);
		log.debug("isController : 넘겨받은 searchType : " + searchType);
		log.debug("isController : 넘겨받은 code : " + searchCode);
		
		
		
		String defaultTime = " 00:00:00";
		
		//SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		
		InstructionsSearchParam param = new InstructionsSearchParam();
		
	
		
		if(searchStartDate==null || searchStartDate.equals("")) {
			  param.setStartTime(null);
		}else {
			searchStartDate += defaultTime;
			
			log.debug("instruction Controller : 변환전 startDate의 값 : " +  searchStartDate);
			
			param.setStartTime(Timestamp.valueOf(searchStartDate));
		}
		
		if(searchEndDate==null || searchEndDate.equals("")) {
			 param.setEndTime(null);
		}else {
			searchEndDate += defaultTime;
			
			log.debug("instruction Controller : 변환전 endDate의 값 : " + searchEndDate);
			
			param.setEndTime(Timestamp.valueOf(searchEndDate));
		}
		
		log.debug("instruction Controller : startDate의 값 : " + param.getStartTime());
		log.debug("instruction Controller : endDate의 값 : " + param.getEndTime());
		
		param.setCode(searchCode);
		//param.setState(state);
		param.setSearchType(searchType);
		
		List<InstructionsDTO> instructions =   instructionsService.findBySearchParam(param);
		
		for(InstructionsDTO dto : instructions) {
			log.debug("" + dto.toString());
		}
		
		
		//log.debug("입력받은 state"+state.getClass());
		
		model.addAttribute("instructions", instructions);
		
		return "/instructions/instructionList";
	}
	
	@GetMapping("/save")
	public String saveGET() {
		
		return "/instructions/save";
	}
	
	@PostMapping("/save")
	public String savePOST(@ModelAttribute("instructionsDTO")InstructionsDTO instructionsDTO) {
		
		log.debug("InstructionsController : " + instructionsDTO.toString());
		
		try {
			instructionsService.saveInstructions(instructionsDTO);
		} catch (ValidationValueErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/instructions/close";
	}
	
	@GetMapping("/close")
	public String closeGET(){
		
		return "/instructions/close";
	}

	
	//생산요청 확인 페이지
	//http://localhost:8088/instructions/request
	@GetMapping("/request")
	public String requestGET(
					@RequestParam(value ="searchStartDate" , required = false) Date searchStartDate,
					@RequestParam(value= "searchEndDate" , required = false) Date searchEndDate, 
					Model model) {
		if(searchStartDate==null) {
			searchStartDate = Date.valueOf(LocalDate.now());
		}
		if(searchEndDate==null) {
			searchEndDate = Date.valueOf(LocalDate.now().plusWeeks(1));
		}
		
		InstructionsSearchParam searchParam = new InstructionsSearchParam();
		searchParam.setStartDate(searchStartDate);
		searchParam.setEndDate(searchEndDate);
		searchParam.setState(InstructionsState.REQUESTED);
		
		model.addAttribute("instructions" , instructionsService.findByStateAndDate(searchParam));
		
		model.addAttribute("searchStartDate", searchStartDate);
		model.addAttribute("searchEndDate", searchEndDate);

		return "/instructions/request";
	}
	
	@PostMapping("/accept")
	public String acceptPost(@RequestParam(value = "isCode") String isCode, HttpServletResponse response) {
		
		//수락 누를시 productionLine, instructions 전부 상태 변환 적용
		instructionsService.acceptRequestedInstructions(isCode);
		
		try {
			response.setContentType("text/html; charset=utf-8");
			String msg = "<script>alert('작업요청이 수락되었습니다');</script>";
			msg+="<script>location.href='/instructions/request';";
			PrintWriter writer = response.getWriter();
			
			writer.print(msg);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/instructions/request";
		
		
	}
	
	@GetMapping("/resultInfo/{isCode}")
	public String resultGet(@PathVariable("isCode")String isCode, Model model) {
		InstructionsDTO findInstruction = instructionsService.findByCode(isCode);
		log.debug("찾아낸 작업지시 내용 "+findInstruction);
		model.addAttribute("instruction" , findInstruction);
		return "/instructions/result";
	}
	
	@PostMapping("/result")
	public String resultPOST(@RequestParam("isCode") String isCode, @RequestParam("quantity") int quantity,
			@RequestParam("fault") int fault,
			HttpServletResponse response) {
		
		instructionsService.completeInstructions(isCode,quantity, fault);
		
		try {
			response.setContentType("text/html; charset=utf-8"); 
			PrintWriter pw = response.getWriter();
			String msg = "<script>alert('입력이 완료되었습니다.');</script>";
			msg += "<script>location.href='/instructions/close';</script>";
			pw.print(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
