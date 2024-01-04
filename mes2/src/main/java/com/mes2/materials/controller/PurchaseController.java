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
	
	// http://localhost:8080/materials/purchaselist

	// 발주 정보 입력 - GET
	@GetMapping(value = "/purchase")
	public void insertPurchaseGET() throws Exception {
		logger.debug("/purchase/insertPurchase -> insertPurchaseGET() 호출 ");
		logger.debug("/purchase/insertPurchase.jsp 뷰페이지로 이동");
	}

	// 발주 정보 처리 - POST
	@RequestMapping(value = "/purchase", method = RequestMethod.POST)
	public String insertPurchasePOST(PurchaseDTO pdto, @RequestParam("product_code") String product_code,
			@RequestParam("orders_code") String orders_code,
            @RequestParam("quantity") int quantity,
            @RequestParam("category") String category, Model model) throws Exception {
		
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
	public void listAllGET(Model model, PurchaseDTO pdto, Criteria cri, SearchDTO sdto) throws Exception {
		logger.debug("/purchase/purchaselist -> listAllGET() 호출 ");
		logger.debug("/purchase/purchaselist  뷰페이지로 이동");

	
		// 서비스 - 디비에 저장된 글 가져오기
		List<PurchaseDTO> purchaselist = pService.PurchaseInfo(pdto, cri, sdto);
		List<PurchaseDTO> purchaselist2 = pService.purchaseListPage(cri, sdto); 
	
	
		for(PurchaseDTO pDTO : purchaselist2) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + pDTO.toString());
		
		}
		
		// 페이지 블럭 정보 준비 -> view 페이지 전달
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);		
		pageVO.setTotalCount(pService.totalPurchaseCount(sdto));
		model.addAttribute("pageVO", pageVO);
		
		logger.debug(" 확인 :"+pageVO);
		
		// 데이터를 연결된 뷰페이지로 전달(Model)
		model.addAttribute("purchaselist", purchaselist);
		model.addAttribute("purchaselist2", purchaselist2);

	}

	// 상태 변경
		  @PostMapping(value="/updateStatus")
		  @ResponseBody public int updateStatus(@RequestParam("status") String
		  status, @RequestParam("product_code") String product_code) throws Exception {
		  int purchaselist = pService.updateOrderStatus(status, product_code);
		  
		  return purchaselist;
		  
		 }
	
	  
	
			
			@GetMapping(value = "/getOrderStatus")
			@ResponseBody
			public List<PurchaseDTO> getOrderStatus(@RequestParam("product_code") String product_code) throws Exception {
				List<PurchaseDTO> purchaselist = pService.getUpdateStatus(product_code);
				return purchaselist;
			}

	
	 @GetMapping(value ="/searchMaterials")
	    public List<PurchaseDTO> searchMaterials(@RequestParam String searchType, @RequestParam String keyword, Model model) throws Exception {
		    List<PurchaseDTO> purchaselist = pService.searchMaterial(searchType, keyword);
			
			for(PurchaseDTO pudto : purchaselist ) {
				System.out.println("(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧" + pudto.toString());
			}
		    model.addAttribute("purchaselist", purchaselist);

	        return purchaselist; 

	
	 }

//	 @GetMapping("/materials/getNameList")
//	 @ResponseBody
//	 public List<String> getNameList() {
//	     // 데이터베이스에서 모든 name 목록을 조회하여 반환
//	     return pService.getAllNames();
//	 }

	/*
	 * @GetMapping(value = "/getOrderStatus")
	 * 
	 * @ResponseBody public List<PurchaseDTO>
	 * getOrderStatus(@RequestParam("product_code") String product_code) throws
	 * Exception { // product_code에 해당하는 품목의 상태를 데이터베이스에서 조회합니다. return
	 * pService.getUpdateStatus(product_code); }
	 */

}

//		@RequestMapping(value = "/purchase")
//		public String purchase() {	
//			return "/materials/purchase";
//		}
//		
//		
//		@GetMapping(value = "/purchaseList")
//		@ResponseBody
//		public List<PurchaseDTO> purchaseList(Model model, String product_code) throws Exception {
//		    List<PurchaseDTO> list = pService.PurchaseInfo(product_code);
//		    model.addAttribute("list", list);
//		    
//		    List<PurchaseDTO> purchaseList = pService.PurchaseDetailInfo(product_code);
//		    model.addAttribute("detailList", purchaseList);
//		    
//		    return list;
//		}
//
//		
//		@PostMapping(value = "/purchaseList")
//		@ResponseBody
//		public List<PurchaseDTO> purchaseDetailList(Model model, String product_code) throws Exception {
//		    List<PurchaseDTO> purchaseList = pService.PurchaseDetailInfo(product_code);
//		    model.addAttribute("purchaseList", purchaseList);
//		    
//		    return purchaseList;
//		}

/*
 * @PostMapping(value = "/updateStatus")
 * 
 * @ResponseBody public List<PurchaseDTO> updateStatus(Model
 * model, @RequestParam("stauts") String stauts,
 * 
 * @RequestParam("product_code") String product_code) throws Exception {
 * List<PurchaseDTO> updateStatus = pService.updateOrderStatus(stauts,
 * product_code); model.addAttribute("updateStatus", updateStatus);
 * 
 * 
 * return updateStatus;
 * 
 * 
 * }
 */
