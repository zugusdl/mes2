package com.mes2.materials.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.materials.domain.OutDTO;
import com.mes2.materials.persistence.OutDAO;

@Service
public class OutServiceImpl implements OutService {

	private static final Logger logger = LoggerFactory.getLogger(OutServiceImpl.class);
	
	@Inject
	private OutDAO odao;

	@Override
	public void registerOutcomingStock(OutDTO odto) throws Exception {
		odao.registerOutbound(odto);
		
	}

	@Override
	public List<OutDTO> getOutcomingStockInfo(OutDTO odto) throws Exception {
		return odao.getAllOutboundInfo(odto);
	}
	
	@Override
	public void updateQuantity(String product_code, int quantity, String category) throws Exception {
		odao.updateQuantity(product_code, quantity, category);
	}
	
	
}
