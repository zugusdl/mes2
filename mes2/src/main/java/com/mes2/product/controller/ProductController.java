package com.mes2.product.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mes2.product.persistence.ProductDAO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
	
	private final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Inject
	private ProductDAO productDAO;
	
	
	@GetMapping("/test")
	public String sqlTest() {
		
		String msg = productDAO.getTime().toString();
		
		log.info(msg);
		return msg;
	}
}
