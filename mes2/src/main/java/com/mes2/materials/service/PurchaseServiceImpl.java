package com.mes2.materials.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.PurchaseDTO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.persistence.PurchaseDAO;


@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);
	
	@Inject
	private PurchaseDAO pdao;


	
	@Override
	public void purchaseOrder(PurchaseDTO pdto) throws Exception {
		logger.debug(" @@@@@@@@@@@@@@@@@@@@@@@@@@Service : purchaseOrder(PurchaseDTO pdto) ");
		pdao.insertPurchase(pdto);
		
	}


	@Override
	public List<PurchaseDTO> PurchaseInfo(PurchaseDTO pdto, Criteria cri, SearchDTO sdto) throws Exception {
		logger.debug(" Service - PurchaserInfo(PurchaseDTO pdto) ");
		return pdao.listPurchase(pdto , cri, sdto);
	}

	
	@Override
	public int updateOrderStatus(String status, String product_code) throws Exception {
		logger.debug("(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧"+ product_code);
		return pdao.updateOrderStatus(status, product_code);
	}


	@Override
	public List<PurchaseDTO> getUpdateStatus(String product_code) throws Exception {
		logger.debug("(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧"+ product_code);
		return pdao.getUpdateStatus(product_code);
	}


	@Override
	public void updateQuantity(String product_code, int quantity, String category) throws Exception {
		pdao.updateQuantity(product_code, quantity, category);
		
	}


	@Override
	public List<PurchaseDTO> purchaseListPage(Criteria cri, SearchDTO sdto) throws Exception {
		return pdao.getPurchaseListPage(cri, sdto);
	}


	@Override
	public int totalPurchaseCount(SearchDTO sdto) throws Exception {
		return pdao.getPurchaseCount(sdto);
	}


	@Override
	public void MaterialReceipt(String product_code, int quantity) throws Exception {
		pdao.MaterialReceipt(product_code, quantity);
		
	}


	@Override
	public List<PurchaseDTO> searchMaterial(String searchType, String keyword) throws Exception {
		return pdao.searchMaterial(searchType, keyword);
		
	}



	




	
}
