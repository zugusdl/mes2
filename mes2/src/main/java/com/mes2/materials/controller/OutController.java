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

import com.mes2.materials.domain.OutDTO;
import com.mes2.materials.service.OutService;

@Controller
@RequestMapping(value = "/materials/*")
public class OutController {
	
	private static final Logger logger = LoggerFactory.getLogger(OutController.class);

	@Inject
	private OutService oService;

	// http://localhost:8080/materials/outlist
	
	// 출고 정보 입력 - GET
	@GetMapping(value = "/out")
	public void goToOutboundInputPageGET() throws Exception {
	}
	
	// 출고 정보 처리 - POST
		@RequestMapping(value = "/out", method = RequestMethod.POST)
		public String goToOutboundInputPagePOST(OutDTO odto, @RequestParam("product_code") String product_code,
	            @RequestParam("quantity") int quantity,
	            @RequestParam("category") String category) throws Exception {
		
			// 한글인코딩 (필터)
			// 전달정보 저장
			logger.debug(" odto : " + odto);

			// 서비스 - DB에 글쓰기(insert) 동작 호출
			oService.registerOutcomingStock(odto);
			
		    // 서비스 - meta_data_product 수량 업데이트 호출
		    oService.updateQuantity(product_code, quantity, category);
			
			logger.debug(" /materials/outlist 이동 ");

			return "redirect:/materials/outlist";
		}
		
		// 출고 리스트 - GET
				@GetMapping(value = "/outlist")
				public void listAllGET(Model model, OutDTO odto) throws Exception {
					logger.debug("/purchase/outlist -> listAllGET() 호출 ");
					logger.debug("/purchase/outlist  뷰페이지로 이동");

					// 서비스 - 디비에 저장된 글 가져오기
					List<OutDTO> outlist = oService.getOutcomingStockInfo(odto);

					model.addAttribute("outlist", outlist);
				}
			

}

