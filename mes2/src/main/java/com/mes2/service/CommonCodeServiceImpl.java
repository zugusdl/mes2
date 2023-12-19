package com.mes2.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.domain.CommonCodeDTO;
import com.mes2.persistence.CommonCodeDAO;




@Service
public class CommonCodeServiceImpl implements CommonCodeService {

	
	
	private static final Logger logger = LoggerFactory.getLogger(CommonCodeServiceImpl.class);
	
	
	
	@Inject
	private CommonCodeDAO cdao;


	//공통코드리스트
	@Override
	public List<CommonCodeDTO> getCommoncodeList(CommonCodeDTO dto) {
		logger.debug("S : 공통코드 리스트 DAO 메서드 호출!");
		
		List<CommonCodeDTO> resultDTO = cdao.getCommoncodeList(dto);
		
		return resultDTO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
