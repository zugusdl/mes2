package com.mes2.production.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes2.production.domain.ProductDTO;
import com.mes2.production.etc.ProductSearchParam;
import com.mes2.production.service.ProductService;
import com.mes2.test.domain.DateTest;
import com.mes2.test.repository.TestDAO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
	
	private final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Inject
	private ProductService productService;
	
	@Inject
	private TestDAO testDAO;
	
	
	@ResponseBody
	@GetMapping("/test23")
	public String sqlTest() {
		
		String msg = productService.selectByLot("20231217B0001").getPd_quantity();
		
		log.info(msg);
		return msg;
	}

	// http://localhost:8088/product/header
	@GetMapping("/header")
	public String header() {
		log.info("헤더 호출");
		return "/header/header";
	}
	
	// http://localhost:8088/product/search
	@GetMapping("/search")
	public String searchGet(Model model,@RequestParam(value = "startDate", required = false) String startDate
			,@RequestParam(value="endDate", required = false) String endDate, @RequestParam(value = "name", required = false)String name) {

		//@ModelAttribute("searchParam") SearchParam searchParam
		
		ProductSearchParam productSearchParam = new ProductSearchParam();
		
		if(startDate==null || startDate.equals("")) {
			  productSearchParam.setStartDate(null);
		}else {
			productSearchParam.setStartDate(Date.valueOf(startDate));
		}
		
		if(endDate==null || endDate.equals("")) {
			  productSearchParam.setStartDate(null);
		}else {
			productSearchParam.setEndDate(Date.valueOf(endDate));
		}
		
		productSearchParam.setName(name);
		
		//
		
		
		log.debug("입력받은 시작날짜 : "+ productSearchParam.getStartDate());
		log.debug("입력받은 시작날짜 : "+ productSearchParam.getEndDate());
		log.debug("입력받은 검색어 : "+ productSearchParam.getName());
		
		
		List<ProductDTO> productDTOList = productService.selectBySearch(productSearchParam);
		log.info("ProductController : 출려된 제품 크기 " + productDTOList.size());
		
		for(ProductDTO product : productDTOList) {
			log.debug("==============================");
			log.debug("product LOT 번호 : " +product.getPd_lot());
			log.debug("product 생산품 번호 : " +product.getPd_mdp_code());
			log.debug("product 수주 번호 : " +product.getPd_soi_id());	
			log.debug("product 생산날짜 : " +product.getPd_date().getClass());	
			log.debug("product 생산날짜 : " +product.getPd_date());	
			
			
			log.debug("==============================");
		}
		
		model.addAttribute("productList", productDTOList);
		model.addAttribute("startDate" , productSearchParam.getStartDate());
		model.addAttribute("endDate" , productSearchParam.getEndDate());
		
		return "/production/product/main";
	}

	
	// http://localhost:8088/product/delete
	@GetMapping("/delete")
	public String deleteGET(@RequestParam("lot") List<String> lotList){
				
		//log.debug(msg);
		return null;
	}
	
	@GetMapping("/save")
	public String saveGet() {
		
		return "/production/product/save";
	}
	
	@PostMapping("/test")
	public String testGet(@RequestParam("tt") String tt, HttpServletResponse response) {
		
		log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		log.debug("/product/test 호출완료");
		log.debug("전달받은 값 : "+ tt);
		
		response.setContentType("text/html; charset=utf-8");
		try {
			PrintWriter pw = response.getWriter();
			String msg = "입력 완료";
			pw.print(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "/production/close";
	}

	
	
	@PostMapping("/dateTest")
	public String testDatePOST(@RequestParam("date1") Date date1,
			@RequestParam("date2") Date date2) {
		
		DateTest testDTO = new DateTest();
		
		log.debug("입력받은 날짜 : " + date1);
		log.debug("입력받은 날짜 : " + date2);
		log.debug("입력받은 타입 : " + date1.getClass());
		log.debug("입력받은 타입 : " + date2.getClass());
		
		testDTO.setDate1(date1);
		testDTO.setDate2(date2);
		
		
		testDAO.insertDate(testDTO);
		
		List<DateTest> dateList = testDAO.selectDate(testDTO);
		
		for(DateTest dateTest : dateList) {
			log.debug("입력받은 번호 : "+dateTest.getNum() );
			log.debug("입력받은 날짜 : "+dateTest.getDate1() );
			log.debug("입력받은 호우 : "+dateTest.getDate2() );
		}
		return "/test/dateTest";
	}
	
	@ResponseBody
	@GetMapping("/dateTest2")
	public String testDate2(@RequestParam("selectDate") Date selectDate) {
		
		log.debug("입력받은 날짜 : " + selectDate);
	
		
		
		
		return null;
	}
}
