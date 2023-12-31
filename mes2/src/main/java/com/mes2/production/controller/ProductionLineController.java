package com.mes2.production.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.mes2.production.domain.ProductionLineDTO;
import com.mes2.production.service.ProductionLineService;

@Controller
public class ProductionLineController {

	@Inject
	private ProductionLineService productionLineService;
	
	@GetMapping("/productionLine/search")
	public String productionLineSearchGET(@RequestParam(value="searchStartDate", required = false) Date searchStartDate,
			@RequestParam(value="searchEndDate", required=false)Date searchEndDate, Model model) {
			
		
		if(searchStartDate==null) {
		searchStartDate = Date.valueOf(LocalDate.now());
		}
		if(searchEndDate==null) {
		searchEndDate = Date.valueOf(LocalDate.now().plusWeeks(1));
		}
		
		List<ProductionLineDTO> findProductionLineList = productionLineService.findByDate(searchStartDate, searchEndDate);
		model.addAttribute("productionLineList",findProductionLineList);
		model.addAttribute("searchStartDate", searchStartDate);
		model.addAttribute("searchEndDate", searchEndDate);
		return "/productionLine/productionLineList";
	}
	
}
