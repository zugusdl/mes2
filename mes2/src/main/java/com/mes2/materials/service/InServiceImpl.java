package com.mes2.materials.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.materials.domain.InDTO;
import com.mes2.materials.persistence.InDAO;

@Service
public class InServiceImpl implements InService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(InServiceImpl.class);
	
	@Inject
	private InDAO idao;
	
	@Override
	public void registerStock(InDTO idto) throws Exception {
		idao.insertIn(idto);
		
	}

	

	@Override
	public List<InDTO> InInfo(InDTO idto) throws Exception {
		logger.debug(" Service - InInfo(InDTO idto) ");
		return idao.listIn(idto);
	}


	
	
	

	@Override
	public List<InDTO> detailList(String product_code) throws Exception {
		return idao.detailSelect(product_code);
	}

	
}
