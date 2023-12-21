package com.mes2.production.persistence;

import java.util.List;

import com.mes2.production.domain.ProductDTO;
import com.mes2.production.etc.ProductSearchParam;

public interface ProductDAO {
	public String getTime();

	public List<ProductDTO> selectBySearch(ProductSearchParam productSearchParam);
	
	public ProductDTO selectByLot(String Lot);
	
	public int deleteByLot(List<String> lotList);
}
