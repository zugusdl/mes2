package com.mes2.metadata.controller;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mes2.metadata.domain.Criteria;
import com.mes2.metadata.domain.PageVO;
import com.mes2.metadata.domain.md_productDTO;
import com.mes2.metadata.service.MetadataService;


//http://localhost:8088/meta_data/firstpage
@Controller
@RequestMapping(value = "/meta_data/*")
public class  MetadataController{
	
	private static final Logger logger = LoggerFactory.getLogger(MetadataController.class);
	
	@Inject
	private MetadataService mService;
	
	
	
	
	// 품목관리 페이지, 모든 품목정보리스트 호출
	@RequestMapping(value="/firstpage", method=RequestMethod.GET)
	public String productdataGET(Model model, Criteria cri) throws Exception{
		
		//서비스 - 디비에 저장된 글을 가져오기
		List<md_productDTO> productList = mService.boardListPage(cri);
		logger.debug(" @@@ " + productList);
		
		
		// 페이지 블럭 정보 준비 -> view 페이지 전달
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(mService.totalBoardCount());
		
		logger.debug(" 확인 :"+pageVO);
		model.addAttribute("pageVO", pageVO);
		
		
		
		//List<md_productDTO> productList = mService.productListAll();
		logger.debug("@@@" + productList);
		logger.debug("모든품목정보 출력 컨트롤러 실행 성공");
		model.addAttribute("productList", productList);
		
		return "/meta_data/productdata/productinfo";
		
		
	}
	
	
	
	// 품목관리 페이지, 날짜필터 품목정보리스트 호출
	@PostMapping("/filter")
	public String productdataPOST(Model model,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("search") String search) throws Exception{
	
		
		
		Date start;
		Date end;
		
		if(startDate.equals("")) {
			  start = null;
		}else {
			start = Date.valueOf(startDate);
		}
		
		if(endDate.equals("")) {
			  end = null;
		}else {
			end = Date.valueOf(endDate);
		}
		
		//logger.debug(""+search.getClass());
		//logger.debug(""+start.getClass());
		//logger.debug(""+end.getClass());
		//logger.debug("날짜필터 컨트롤러 실행 성공");
		//logger.debug("확인" + startDate + endDate + search);

		List<md_productDTO> productList = mService.productdatefilter(start, end, search);
		logger.debug("@@@" + productList);
		
		model.addAttribute("productList", productList);
		
		return "/meta_data/productdata/productinfo";
		
		
	}
	
	//파일 추가 코드
	// 파일정보(이름)을 저장, 파일업로드 처리
	
	private String fileProcess(MultipartHttpServletRequest multiRequest) throws Exception{
					
		String ofileName = null;			
		// 폼태그에서 전달된 파일의 정보를 받아오기
		//  (input태그 file의 이름을 모두 가져오기)
		Iterator<String> fileNames = multiRequest.getFileNames();
			while(fileNames.hasNext()) {
				// 파라메터 이름을 저장
				String fileName =  fileNames.next();
				logger.debug(" fileName : "+fileName);				
				// 전달된 파일이름에 해당하는 MultipartFile정보 저장
				MultipartFile mFile = multiRequest.getFile(fileName);
				ofileName = mFile.getOriginalFilename();
				logger.debug(" oFileName : "+ofileName);
						
	
				//추가 일때
				
	
	
				// 실제 폴더 생성
				File file = new File("C:\\Users\\qhtjd\\git\\mes2\\mes2\\src\\main\\webapp\\resources\\img\\metadata\\"+ofileName);
				// 파일업로드
				if(mFile.getSize() != 0) { //첨부파일이 있을때
					if(!file.exists()) { // 파일,디렉터리(폴더)가 존재하는지 체크
						if(file.getParentFile().mkdirs()) {
							file.createNewFile();
						}
					}// exists
					mFile.transferTo(file);
				}// getSize
			}//while
					
			return ofileName;
		}
	
	// 품목 추가 하는 곳
	@RequestMapping(value="/insertproduct", method=RequestMethod.POST)
	public String productinsertPOST(md_productDTO dto, MultipartHttpServletRequest multiRequest,
            Model model) throws Exception{
		
		logger.debug("추가까지왔다2");
		logger.debug("fileUploadPOST()-파일업로드 처리 ");
		logger.debug("넘어오니?"+multiRequest);
		
		// 파일 정보를 저장
		String ofileName = fileProcess(multiRequest);
		dto.setOfileName(ofileName);
		mService.productinsert(dto);
		
		logger.debug("사진 이름!" + ofileName);
		logger.debug("최종디티오" + dto);
		
		
		
		return "redirect:/meta_data/firstpage";
		
		
	}
	
	// 품목 수정
		@RequestMapping(value="/updateproduct", method=RequestMethod.POST)
		public String productupdatePOST(md_productDTO dto, MultipartHttpServletRequest multiRequest, Model model) throws Exception{
			
			logger.debug("왜 안되2" + dto);
			String ofileName = fileProcess(multiRequest);
			dto.setOfileName(ofileName);
			mService.productupdate(dto);
			logger.debug("왜 안되니~~~~~~~~~~~~~~~~~~~~~~~~");
			logger.debug("왜 안되" + dto);
			
			return "redirect:/meta_data/firstpage";			
		}
		
		
	// 품목 삭제
	@RequestMapping(value="/deleteproduct", method=RequestMethod.POST)
	public String productdeletePOST(md_productDTO dto) throws Exception{
		
		
		mService.productdelete(dto);
		
		return "redirect:/meta_data/firstpage";			
		}
		


}
