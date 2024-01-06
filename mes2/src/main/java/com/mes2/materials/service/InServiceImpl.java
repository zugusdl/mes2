package com.mes2.materials.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.InDTO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.domain.productDTO;
import com.mes2.materials.persistence.InDAO;

@Service
public class InServiceImpl implements InService {

	private static final Logger logger = LoggerFactory.getLogger(InServiceImpl.class);

	@Inject
	private InDAO idao;


	@Override
	public void insertIncomingRequest(InDTO idto) throws Exception {
		idao.insertIncomingRequest(idto);
	}


	@Override
	public List<InDTO> getIncomingStockInfo(String searchType, String keyword, Criteria cri, SearchDTO sdto) throws Exception {
		return idao.getAllInboundInfo(searchType, keyword, cri, sdto);
	}

	
	@Override
	public void InupdateQuantity(String product_code, int quantity, String category) throws Exception {
		idao.InupdateQuantity(product_code, quantity, category);
	}

	@Override
	public int totalInCount(Criteria cri, String searchType, String keyword) throws Exception {
		return idao.getInCount(cri, searchType, keyword);
	}

	@Override
	public List<InDTO> searchIn(String searchType, String keyword, Criteria cri) throws Exception {
		return idao.searchIn(searchType, keyword, cri);
	}

	@Override
	public int updateInStatus(String status, int in_index) throws Exception {
		return idao.updateInStatus(status, in_index);
	}


	@Override
	public productDTO listIncomingProductCodes(String product_code) throws Exception {
		return idao.listIncomingProductCodes(product_code);
	}


	@Override
	public List<productDTO> getIncomingProductCodesByCategory(String category) throws Exception {
		return idao.getIncomingProductCodesByCategory(category);
	}


	
	
}
