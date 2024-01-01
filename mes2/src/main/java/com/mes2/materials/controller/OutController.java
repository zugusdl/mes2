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
import org.springframework.web.bind.annotation.RequestParam;

import com.mes2.materials.domain.OutDTO;
import com.mes2.materials.service.OutService;

@Controller
@RequestMapping(value = "/materials/*")
public class OutController {
	
	private static final Logger logger = LoggerFactory.getLogger(OutController.class);

	@Inject
	private OutService oService;

	// http://localhost:8080/materials/out
		@GetMapping(value = "/out")
		public String getSelectGET(Model model, @RequestParam(name = "product_code", required = false) String product_code) throws Exception {
			List<OutDTO> OutList = oService.getSelect();
			model.addAttribute("OutList", OutList);

			  List<OutDTO> detailList = oService.detailList(product_code);
			  model.addAttribute("detailList", detailList);
			
			return "/materials/out";
		}
		
		@PostMapping(value = "/out")
		public String getSelectPOST(@RequestParam(name = "product_code", required = false) String product_code) throws Exception {
			

			return "/materials/out";
		}

}

