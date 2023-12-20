package com.mes2.platform.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.platform.domain.mdbDTO;
import com.mes2.platform.domain.mdpDTO;
import com.mes2.platform.persistence.PlatformDAO;

@Service
public class PlatformServiceImpl implements PlatformService {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformServiceImpl.class);
	
	@Inject
	private PlatformDAO pdao;
	
	@Override
	public mdbDTO customerLogin(mdbDTO mdto) throws Exception {
		logger.debug("customerLogin() 호출");
		return pdao.customerLogin(mdto);
	}

	@Override
	public List<mdpDTO> inqueryProduct(String searchType, String search) throws Exception {
		logger.debug("inqueryProduct() 호출");
		return pdao.inqueryProduct(searchType, search);
	}
	
}
