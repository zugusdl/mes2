package com.mes2.materials.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.materials.domain.OutDTO;
import com.mes2.materials.domain.StockDTO;
import com.mes2.materials.persistence.OutDAO;

@Service
public class OutServiceImpl implements OutService {

	private static final Logger logger = LoggerFactory.getLogger(OutServiceImpl.class);
	
	@Inject
	private OutDAO odao;

	// 출고 목록 조회
	@Override
	public List<OutDTO> getOutList() throws Exception {
		logger.debug("S: getOutList() 호출");
		return odao.getOutList();
	}
	
	// 출고 상세 조회
	@Override
	public OutDTO getOutDetail(String out_index) throws Exception {
		logger.debug("S: getOutDetail() 호출");
		return odao.getOutDetail(out_index);
	}
	
	// 출고 품목 재고 조회
	@Override
	public List<StockDTO> getStockList(String product_code) throws Exception {
		return odao.getStockList(product_code);
	}
	
}
