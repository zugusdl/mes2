package com.mes2.metadata.controller;


import java.sql.Date;
import java.util.List;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mes2.metadata.domain.md_productDTO;
import com.mes2.metadata.service.MetadataService;

@Controller
@RequestMapping(value = "/meta_data/*")
public class  MetadataController{
	
	private static final Logger logger = LoggerFactory.getLogger(MetadataController.class);
	
	@Inject
	private MetadataService mService;
	
	
	// http://localhost:8088/meta_data/productdata/productinfo
	// 품목관리 페이지, 모든 품목정보리스트 호출
	@RequestMapping(value="/productdata/productinfo", method=RequestMethod.GET)
	public String productdataGET(Model model) throws Exception{
		logger.debug("모든품목정보 출력 컨트롤러 실행 성공");
		
		List<md_productDTO> productList = mService.productListAll();
		logger.debug("@@@" + productList);
		
		model.addAttribute("productList", productList);
		
		return "/meta_data/productdata/productinfo";
		
		
	}
	
	
	
	// 품목관리 페이지, 날짜필터 품목정보리스트 호출
	@PostMapping("/productdata/productinfo")
	public String productdataPOST(Model model,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) throws Exception{
		
		
		
		Date start = Date.valueOf(startDate);
		Date end = Date.valueOf(endDate);
	   
		logger.debug(""+start.getClass());
		logger.debug("날짜필터 컨트롤러 실행 성공");
		logger.debug("확인" + startDate + endDate);

		List<md_productDTO> productList = mService.productdatefilter(start, end);
		logger.debug("@@@" + productList);
		
		model.addAttribute("productList", productList);
		
		return "/meta_data/productdata/productinfo";
		
		
	}
	

	
}
