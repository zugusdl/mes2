package com.mes2.materials.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mes2.materials.domain.StockDTO;
import com.mes2.materials.service.OutService;

@RestController
@RequestMapping("/restOut/*")
public class OutRestController {
	private static final Logger logger = LoggerFactory.getLogger(OutRestController.class);
	
	@Inject
	OutService oService;
	
//	@PostMapping("/registProduct")
//	public StockDTO registProductPOST(@RequestBody List<StockDTO> stockDTO) throws Exception {
//		logger.debug("registProductPOST() 호출");
//		logger.debug("stockDTO: " + stockDTO);
//		oService.registProduct(stockDTO);
//		return null;
//	}
	
}
