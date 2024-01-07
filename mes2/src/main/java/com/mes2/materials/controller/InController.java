package com.mes2.materials.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.InDTO;
import com.mes2.materials.domain.PageVO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.service.InService;

@Controller
@RequestMapping(value = "/materials/*")
public class InController {

	private static final Logger logger = LoggerFactory.getLogger(InController.class);

	@Inject
	private InService iService;

	// http://localhost:8080/materials/inlist
	
	// 입고 정보 입력 - GET
	@GetMapping(value = "/in")
	public void insertInGET() throws Exception {
	}

	
	// 입고 정보 처리 - POST
		@RequestMapping(value = "/updateInStatus", method = RequestMethod.POST)
		public String insertInPOST(@RequestParam("in_pd_lot") String pd_lot, Model model) throws Exception {
		
			
			InDTO idto = iService.listIncomingProductCodes(pd_lot);
			
			// 한글인코딩 (필터)
			// 전달정보 저장
			logger.debug(" idto : " + idto);
			
			
			
			idto.setPd_lot(iService.selectMaxMaterialsLot(pd_lot));
			
			String in_code = ("IN-" + pd_lot);
			idto.setIn_code(in_code);
			iService.updateIncomingRequest(in_code, pd_lot, idto.getUser_id());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + in_code);
			model.addAttribute("in_code", in_code);
			
			
			// 서비스 - DB에 글쓰기(insert) 동작 호출
			
			if(iService.selectStock(idto.getProduct_code()).isEmpty()) { 
				
				iService.insertStock(idto.getQuantity(), idto.getProduct_code(), idto.getCategory());
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@22222222222222222");
				
			} else { 
				iService.updateStockOnIncoming(idto.getQuantity(), idto.getProduct_code());
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@3333333333333333333");
			}
			
			
			logger.debug(" /materials/inlist 이동 ");

			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@4444444444444444444");
			return "redirect:/materials/inDetailList";
		}
		
		// 입고 리스트 - GET
		@GetMapping(value = "/inlist")
		public void listAllGET(Model model, SearchDTO sDTO, Criteria cri, 
				@RequestParam(value="searchType", required = false) String searchType,
				@RequestParam(value="keyword", required = false) String keyword) throws Exception {
		
			// 서비스 - 디비에 저장된 글 가져오기
			// 페이지 블럭 정보 준비 -> view 페이지 전달
			PageVO pageVO = new PageVO();
			pageVO.setCri(cri);
			pageVO.setTotalCount(iService.totalInCount(cri, searchType, keyword));
			List<InDTO> inlist = iService.searchIn(searchType, keyword, cri);
			
			model.addAttribute("pageVO", pageVO);
			
			// 데이터를 연결된 뷰페이지로 전달(Model)
			model.addAttribute("inlist", inlist);
		
		}
		
		// http://localhost:8080/materials/inDetailList
		// 입고 완료된 리스트 
		@GetMapping(value="/inDetailList")
		public void inDetailListGET(Model model, SearchDTO sdto, Criteria cri, 
				@RequestParam(value="searchType", required = false) String searchType,
				@RequestParam(value="keyword", required = false) String keyword)throws Exception{
				
			
				// 서비스 - 디비에 저장된 글 가져오기
				// 페이지 블럭 정보 준비 -> view 페이지 전달
				PageVO pageVO = new PageVO();
				pageVO.setCri(cri);
				pageVO.setTotalCount(iService.inDetailCount(cri, searchType, keyword));
				List<InDTO> inDetailList = iService.InDetailCompletedWarehouse(searchType, keyword, cri, sdto);
				
				model.addAttribute("pageVO", pageVO);
				
				System.out.println("왕갈비탕"+inDetailList.size());
				
				// 데이터를 연결된 뷰페이지로 전달(Model)
				model.addAttribute("inDetailList", inDetailList);
				
				
				
		}
		
	/*
	 * // 상태 변경
	 * 
	 * @PostMapping(value = "/updateInStatus") public String
	 * updateStatus(@RequestParam(value="in_index", required = false) int in_index)
	 * throws Exception { int inlist = iService.updateInStatus("complete",
	 * in_index);
	 * 
	 * return "redirect:/materials/inlist";
	 * 
	 * }
	 */
}
