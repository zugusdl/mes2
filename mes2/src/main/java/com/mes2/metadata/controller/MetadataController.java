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


//http://localhost:8088/meta_data/firstpage
@Controller
@RequestMapping(value = "/meta_data/*")
public class  MetadataController{
	
	private static final Logger logger = LoggerFactory.getLogger(MetadataController.class);
	
	@Inject
	private MetadataService mService;
	
	
	
	// 품목관리 페이지, 모든 품목정보리스트 호출
	@RequestMapping(value="/firstpage", method=RequestMethod.GET)
	public String productdataGET(Model model) throws Exception{
		logger.debug("모2222222222222222222222222");
		
		
		
		List<md_productDTO> productList = mService.productListAll();
		logger.debug("@@@" + productList);
		logger.debug("모든품목정보 출력 컨트롤러 실행 성공");
		model.addAttribute("productList", productList);
		
		return "/meta_data/productdata/productinfo";
		
		
	}
	
	
	
	// 품목관리 페이지, 날짜필터 품목정보리스트 호출
	@PostMapping("/filter")
	public String productdataPOST(Model model,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("search") String search) throws Exception{
	
		
		
		Date start;
		Date end;
		
		if(startDate.equals("")) {
			  start = null;
		}else {
			start = Date.valueOf(startDate);
		}
		
		if(endDate.equals("")) {
			  end = null;
		}else {
			end = Date.valueOf(endDate);
		}
		
		//logger.debug(""+search.getClass());
		//logger.debug(""+start.getClass());
		//logger.debug(""+end.getClass());
		//logger.debug("날짜필터 컨트롤러 실행 성공");
		//logger.debug("확인" + startDate + endDate + search);

		List<md_productDTO> productList = mService.productdatefilter(start, end, search);
		logger.debug("@@@" + productList);
		
		model.addAttribute("productList", productList);
		
		return "/meta_data/productdata/productinfo";
		
		
	}
	
	
	// 품목 추가 하는 곳
	@RequestMapping(value="/insertproduct", method=RequestMethod.POST)
	public String productinsertPOST(md_productDTO dto) throws Exception{
		logger.debug("추가까지왔다2");
		
		mService.productinsert(dto);
		
		
		logger.debug(" dto : " + dto);
		logger.debug("55555555555555555555555555");
		
		return "redirect:/meta_data/firstpage";
		
		
	}
	
	// 품목 수정 하는 곳
		@RequestMapping(value="/updateproduct", method=RequestMethod.POST)
		public String productupdatePOST(md_productDTO dto) throws Exception{
			
			
			mService.productupdate(dto);
			logger.debug("왜 안되니~~~~~~~~~~~~~~~~~~~~~~~~");
			logger.debug("왜 안되" + dto);
			
			return "redirect:/meta_data/firstpage";
			
			
		}

}
