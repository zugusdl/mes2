package com.mes2.metadata.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mes2.metadata.service.MetadataService;

@Controller
@RequestMapping(value = "/meta_data/*")
public class  MetadataController{
	
	private static final Logger logger = LoggerFactory.getLogger(MetadataController.class);
	
	@Inject
	private MetadataService mService;
	
	
	
	
	
	// http://localhost:8088/meta_data/productdata
	// 품목관리 페이지 호출
	@RequestMapping(value="/productdata", method=RequestMethod.GET)
	public void productdata() {
		logger.debug("productinfo 페이지 호출!");
	}
}
