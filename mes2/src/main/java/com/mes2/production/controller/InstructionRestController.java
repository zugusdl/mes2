package com.mes2.production.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mes2.production.service.InstructionsService;

@RestController
@RequestMapping("/instruction")
public class InstructionRestController {

	private InstructionsService instructionService;
	
	
	
}
