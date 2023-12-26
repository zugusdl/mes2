package com.mes2.metadata.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mes2.metadata.domain.productDTO;
import com.mes2.metadata.service.MetadataService;

@Controller
@RequestMapping(value = "/meta_data/*")
public class  MetadataController{
	
	private static final Logger logger = LoggerFactory.getLogger(MetadataController.class);
	
	@Inject
	private MetadataService mService;
	
	
	// http://localhost:8088/meta_data/productdata/productinfo
	// 품목관리 페이지, 품목정보리스트 호출
	@RequestMapping(value="/productdata/productinfo", method=RequestMethod.GET)
	public String productdata(Model model) throws Exception{
		logger.debug("productinfo 페이지 호출!");
		
		List<productDTO> productList = mService.productListAll();
		logger.debug("@@@" + productList);
		
		model.addAttribute("productList_all", productList);
		
		return "/meta_data/productdata/productinfo";
		
		
	}
	

	
}
