package com.mes2.production.controller;

import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mes2.platform.domain.MdbDTO;
import com.mes2.platform.domain.MdpDTO;
import com.mes2.platform.domain.SoiDTO;
import com.mes2.platform.domain.SopDTO;
import com.mes2.platform.etc.Criteria;
import com.mes2.platform.etc.ModifyPwDTO;
import com.mes2.platform.etc.OrderRequestDTO;
import com.mes2.platform.etc.PageVO;
import com.mes2.platform.etc.SearchDTO;
import com.mes2.platform.service.PlatformService;
import com.mes2.platform.service.PlatformServiceImpl;

// http://localhost:8088/quality/
@Controller
@RequestMapping(value="/quality/*")
public class QualityController {
	
	private static final Logger logger = LoggerFactory.getLogger(QualityController.class);
	
	@Inject
	private PlatformService pService;
	
	// 품질 검수 대기 목록 조회
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGET(@ModelAttribute("result") String result) throws Exception {
		logger.debug("platform.loginGET() 호출");
	}
}
