package com.mes2.production.service;

import java.util.List;

import com.mes2.production.domain.ProductDTO;
import com.mes2.production.etc.SearchParam;

public interface ProductService {

	public String getTime();

	public List<ProductDTO> selectBySearch(SearchParam searchParam);
	
	public ProductDTO selectByLot(String Lot);
}
