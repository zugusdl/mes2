package com.mes2.production.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mes2.materials.domain.OutDTO;
import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.domain.ProductionLineDTO;
import com.mes2.production.etc.InstructionsSearchParam;
import com.mes2.production.etc.RequestMaterialDTO;
import com.mes2.production.etc.RequestMaterialInfo;
import com.mes2.production.etc.RequestMaterialsDTO;
import com.mes2.production.persistence.InstructionsDAO;
import com.mes2.production.persistence.ProductionLineDAO;
import com.mes2.production.service.InstructionsService;

@RestController
@RequestMapping("/restInstruction")
public class InstructionRestController {
	
	private final Logger log  = LoggerFactory.getLogger(InstructionRestController.class);

	@Inject
	private InstructionsService instructionsService;
	
	@Inject
	private ProductionLineDAO productionLineDAO;
	
	@Inject
	private InstructionsDAO instructionsDAO;

	
	@GetMapping("/getInstructions")
	public List<InstructionsDTO> getInstructions(@ModelAttribute("instructionsSearchParam") InstructionsSearchParam isParam) {
		
		List<InstructionsDTO> instructions = instructionsService.findBySearchParam(isParam);
		return instructions;
	}
	
	@GetMapping("/getProduction")
	public String getProductionGET () {
		
		//Date inputDate,int line , String mdpCode
		
		Date test = Date.valueOf(LocalDate.now());
		int line = 1;
		String mdpCode = "PS10001";
		
		return instructionsService.createLotCode(test, line, mdpCode);
		
	}
	//http://localhost:8088/restInstruction/getMaterials
	@PostMapping(value="/getMaterials" , produces ="application/json; charset=utf-8")
	public RequestMaterialsDTO getMaterials(@RequestBody RequestMaterialInfo info) {
		//String sopCode="ACP-bsp002-ORD-20231231-bsp002-1-100";
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+info.getSopCode());
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+info.getSalesQuantity());
		
		
		RequestMaterialsDTO rqml = instructionsDAO.selectBySopCodeForMaterials(info.getSopCode());
		
		int precessStatus = 0;
		
		//전체 비교를 위한 Map 객체 소환
		Map<Integer, String> requestStatusMap = new HashMap();
		int count = 1;
		for(RequestMaterialDTO dto : rqml.getMaterialList()) {
			dto.setTotalAmount(dto.getAmount()*info.getSalesQuantity());
			
			//조회해서 상태코드를 보고 상태값 입력해줘야함
			if(instructionsService.findBySopCodeForOutDTO(info.getSopCode())==null) {
				log.debug("(_ _ ) instructionService [getMaterials] : 조회 결과 : NULL");
			}else if(instructionsService.findBySopCodeForOutDTO(info.getSopCode())!=null) {
				OutDTO outDTO = instructionsService.findBySopCodeForOutDTO(info.getSopCode());
				if(outDTO.getStatus().equals("waiting")) {
					requestStatusMap.put(count, "waiting");
				}else if(outDTO.getStatus().equals("complete")) {
					requestStatusMap.put(count, "complete");
				}
				count ++;
			}
		}
		
		if(requestStatusMap.isEmpty()) {
			rqml.setStatus("empty");
			log.debug("@@@@@@@@@@@@@@ EMPTY @@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
		else if(!requestStatusMap.containsValue("waiting") && requestStatusMap.containsValue("complete")){
            log.debug(" +complete 상태");
            rqml.setStatus("complete");
            
        }else if(requestStatusMap.containsValue("waiting") && !requestStatusMap.containsValue("complete")){
        	log.debug(" waiting 상태");
        	rqml.setStatus("waiting");
            
        }else if(requestStatusMap.containsValue("waiting") && requestStatusMap.containsValue("complete")){
        	log.debug("일부만 완료된 준비중인 상태");
        	rqml.setStatus("progressing");
        	
        }
		
		
		rqml.setSopCode(info.getSopCode());
		rqml.setSalesQuantity(info.getSalesQuantity());
		
		return rqml;
	}
	
	@PostMapping("/materials")
	public String materialsPOST() {
		
		
		return null;
	}
	
	@PostMapping("/updateProgressing")
	public String updateProgressing(@RequestParam("isCode") String isCode) {
		
		log.debug("@@@@@@@@@@@@@@@/updateProgressing : 호출@@@@@@@@@@@@@@@@@@@@@");
		log.debug("전달받은 isCode : "+isCode);
		
		InstructionsDTO findInstruction = instructionsDAO.selectByCode(isCode);
		ProductionLineDTO findProductionLine = productionLineDAO.selectByIsCode(isCode);
		
		findInstruction.setState("PROGRESSING");
		findProductionLine.setStatus("PROGRESSING");
		
		instructionsDAO.updateState(findInstruction);
		productionLineDAO.updateState(findProductionLine);
		//instructionsDAO.updateSopByIsCode(isCode, "PROGRESSING");
		
		return null;
	}
	
	@PostMapping("/requestMaterials")
	public String requestMaterials(@RequestBody RequestMaterialInfo info) {
		
		log.debug("@@@@@@@@@@@넘겨받은 정보 : "+ info.getSopCode());
		
		RequestMaterialsDTO rqml = instructionsDAO.selectBySopCodeForMaterials(info.getSopCode());
		//추가 수량
		int addQuantity = info.getSalesQuantity()/10;
		for(RequestMaterialDTO dto : rqml.getMaterialList()) {
			dto.setTotalAmount(dto.getAmount()*(info.getSalesQuantity()+addQuantity));
			
			instructionsDAO.insertOutWarehouseForMaterials(info.getSopCode(),dto.getMaterialCode() , dto.getTotalAmount());
			
		}
		
		
		return "OK";
	}
	
}
