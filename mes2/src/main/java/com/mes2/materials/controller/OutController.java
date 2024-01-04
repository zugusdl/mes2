package com.mes2.materials.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mes2.materials.domain.OutDTO;
import com.mes2.materials.domain.StockDTO;
import com.mes2.materials.service.OutService;

@Controller
@RequestMapping(value = "/materials/*")
public class OutController {

	private static final Logger logger = LoggerFactory.getLogger(OutController.class);

	@Inject
	private OutService oService;

	// http://localhost:8088/materials/outList

	// 출고 목록 리스트 - GET
	@GetMapping(value = "/outList")
	public void outListGET(Model model, OutDTO odto) throws Exception {
		logger.debug("outListGET() 호출 ");

		model.addAttribute("oList", oService.getOutList());
	}

	// 출고 상세 리스트 - GET
	@GetMapping(value = "/outDetail")
	public String outDetailGET(@RequestParam("out_index") String out_index, @RequestParam("out_code") String out_code, Model model) throws Exception {
		logger.debug("outDetailGET() 호출");

		OutDTO outDTO = null;
		if (out_code == "") {
			outDTO = oService.getOutInfo(out_index);
			model.addAttribute("outDTO", outDTO);
			return "/materials/insertOut";
		} else {
			outDTO = oService.getOutDetail(out_code);
			model.addAttribute("outDTO", outDTO);
			return "/materials/outDetail";
		}
	}
	
	// 출고 품목 재고 조회 - GET
	@GetMapping(value = "/stockList")
	public void stockListGET(@RequestParam("product_code") String product_code, Model model) throws Exception {
		logger.debug("stockListGET() 호출");
		List<StockDTO> stockList = oService.getStockList(product_code);
		model.addAttribute("stockList", stockList);
	}
	
	// 출고 등록 - POST
	@PostMapping(value = "/insertOut")
	public String insertOutPOST(@RequestBody List<StockDTO> stockList) throws Exception {
		logger.debug("stockList: " + stockList);
		return "";
	}

}
