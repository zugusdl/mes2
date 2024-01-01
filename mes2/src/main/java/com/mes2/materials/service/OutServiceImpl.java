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
	public List<OutDTO> getSelect() throws Exception {
		return odao.getSelect();
	}

	@Override
	public List<OutDTO> detailList(String product_code) throws Exception {
		logger.debug("");
		return odao.detailSelect(product_code);
	}

	
	
}
