package com.mes2.production.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mes2.production.domain.ProductDTO;
import com.mes2.production.etc.SearchParam;
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
	public List<ProductDTO> selectBySearch(SearchParam searchParam) {
		return productDAO.selectBySearch(searchParam);
	}

	@Override
	public ProductDTO selectByLot(String Lot) {
		return productDAO.selectByLot(Lot);
	}

	
	
}
