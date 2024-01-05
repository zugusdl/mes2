package com.mes2.materials.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.InDTO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.persistence.InDAO;

@Service
public class InServiceImpl implements InService {

	private static final Logger logger = LoggerFactory.getLogger(InServiceImpl.class);

	@Inject
	private InDAO idao;

	@Override
	public void registerIncomingStock(InDTO idto) throws Exception {
		idao.registerInbound(idto);

	}

	@Override
	public List<InDTO> getIncomingStockInfo(InDTO idto, Criteria cri, SearchDTO sdto, String status) throws Exception {
		return idao.getAllInboundInfo(idto, cri, sdto, status);
	}

	
	@Override
	public void InupdateQuantity(String product_code, int quantity, String category) throws Exception {
		idao.InupdateQuantity(product_code, quantity, category);
	}

	@Override
	public List<InDTO> InListPage(Criteria cri) throws Exception {
		return idao.getInListPage(cri);
	}

	@Override
	public int totalInCount() throws Exception {
		return idao.getInCount();
	}


	
	
}
