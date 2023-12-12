package com.mes2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// http://localhost:8088/login
@Controller
public class controller1 {
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void login(){
		
	}
	
}
