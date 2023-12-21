package com.mes2.platform.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.platform.domain.MdbDTO;
import com.mes2.platform.domain.MdpDTO;
import com.mes2.platform.persistence.PlatformDAO;

@Service
public class PlatformServiceImpl implements PlatformService {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformServiceImpl.class);
	
	@Inject
	private PlatformDAO pdao;
	
	@Override
	public MdbDTO customerLogin(MdbDTO mdto) throws Exception {
		logger.debug("S: customerLogin() 호출");
		return pdao.customerLogin(mdto);
	}

	@Override
	public List<MdpDTO> inqueryProduct(String searchType, String search) throws Exception {
		logger.debug("S: inqueryProduct() 호출");
		return pdao.inqueryProduct(searchType, search);
	}

	@Override
	public MdpDTO registProduct(String product_code) throws Exception {
		logger.debug("S: registProduct() 호출");
		return pdao.registProduct(product_code);
	}
	
}
