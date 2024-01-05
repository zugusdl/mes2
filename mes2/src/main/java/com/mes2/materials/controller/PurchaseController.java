package com.mes2.materials.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.PageVO;
import com.mes2.materials.domain.PurchaseDTO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.domain.productDTO;
import com.mes2.materials.service.PurchaseService;

@Controller
@RequestMapping(value = "/materials/*")
public class PurchaseController {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseController.class);

	@Inject
	private PurchaseService pService;

	private int quantity;
	private String category;
	private String product_code;
	private String orders_code;
	private String searchType;
	private String search;
	
	// http://localhost:8080/materials/purchaselist

	// 발주 정보 입력 - GET
	@GetMapping(value = "/purchase")
	public void insertPurchaseGET() throws Exception {
		logger.debug("/purchase/insertPurchase -> insertPurchaseGET() 호출 ");
		logger.debug("/purchase/insertPurchase.jsp 뷰페이지로 이동");
	}

	
	// 발주 정보 처리 - POST
	@RequestMapping(value = "/purchase", method = RequestMethod.POST)
	public String insertPurchasePOST(PurchaseDTO pdto, Model model) throws Exception {

		// 한글인코딩 (필터)
		// 전달정보 저장
		logger.debug(" ppto : " + pdto);

		// 서비스 - DB에 글쓰기(insert) 동작 호출
		pService.purchaseOrder(pdto);
		model.addAttribute("orders_code", orders_code);
		
		// 서비스 - stock 수량 업데이트 호출
		pService.updateQuantity(product_code, quantity, category);

		// 서비스 - in_warehouse 수량 업데이트 호출
		pService.MaterialReceipt(product_code, quantity);

		logger.debug(" /materials/purchaselist 이동 ");

		return "redirect:/materials/purchaselist";
	}

	// 발주 리스트 - GET
	@GetMapping(value = "/purchaselist")
	public void listAllGET(Model model, SearchDTO sDTO, Criteria cri, @RequestParam("search") String search) throws Exception {
		
		
		// 서비스 - 디비에 저장된 글 가져오기
		List<PurchaseDTO> purchaselist = pService.PurchaseInfo(searchType, search, cri);
		
		// 페이지 블럭 정보 준비 -> view 페이지 전달
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(pService.totalPurchaseCount(sDTO));
		model.addAttribute("pageVO", pageVO);

		// 데이터를 연결된 뷰페이지로 전달(Model)
		model.addAttribute("purchaselist", purchaselist);

		
		logger.debug("@@@@@@@@@@@@@@@@@ 확인 :" + purchaselist);
	
	}

	// 상태 변경
	@PostMapping(value = "/updateStatus")
	@ResponseBody
	public int updateStatus(@RequestParam("status") String status, @RequestParam("product_code") String product_code)
			throws Exception {
		int purchaselist = pService.updateOrderStatus(status, product_code);

		return purchaselist;

	}

	@GetMapping(value = "/getOrderStatus")
	@ResponseBody
	public List<PurchaseDTO> getOrderStatus(@RequestParam("product_code") String product_code) throws Exception {
		List<PurchaseDTO> purchaselist = pService.getUpdateStatus(product_code);
		return purchaselist;
	}

	
	// http://localhost:8080/materials/test?product_code=RP002&category=
	@ResponseBody
	@GetMapping(value="/test")
	public String testGET(@RequestParam("product_code") String product_code, @RequestParam("category") String category) throws Exception {
		productDTO product = new productDTO();
		
		category="원재료";
		PurchaseDTO purchaselist = pService.getProductByCategory(product_code, category);
		

		  
		return purchaselist.toString();
	}
	
	
	
}