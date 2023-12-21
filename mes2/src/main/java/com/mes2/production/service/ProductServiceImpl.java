package com.mes2.production.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mes2.production.domain.ProductDTO;
import com.mes2.production.etc.ProductSearchParam;
import com.mes2.production.persistence.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService{

	@Inject
	private ProductDAO productDAO;
	
	@Override
	public String getTime() {
		return productDAO.getTime();
	}

	@Override
	public List<ProductDTO> selectBySearch(ProductSearchParam productSearchParam) {
		return productDAO.selectBySearch(productSearchParam);
	}

	@Override
	public ProductDTO selectByLot(String Lot) {
		return productDAO.selectByLot(Lot);
	}

	@Override
	public int deleteByLot(List<String> lotList) {
		return productDAO.deleteByLot(lotList);
	}

	//
	
	
}
