package com.mes2.materials.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.materials.domain.PurchaseDTO;
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
	public List<PurchaseDTO> PurchaseInfo(PurchaseDTO pdto) throws Exception {
		logger.debug(" Service - PurchaserInfo(PurchaseDTO pdto) ");
		return pdao.listPurchase(pdto);
	}




	@Override
	public List<PurchaseDTO> PurchaseDetailInfo(PurchaseDTO pdto) throws Exception {
		return pdao.detailPurchase(pdto);
	}


	
	@Override
	public int updateOrderStatus(String status, String product_code) throws Exception {
		return pdao.updateOrderStatus(status, product_code);
	}


	@Override
	public List<PurchaseDTO> getUpdateStatus(String product_code) throws Exception {
		return pdao.getUpdateStatus(product_code);
	}


	@Override
	public List<PurchaseDTO> getMaterialDataByName(String category, String name) throws Exception {
		return pdao.getSelectName(category, name);
	}

	

	
}
