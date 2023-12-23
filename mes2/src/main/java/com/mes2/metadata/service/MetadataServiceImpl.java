package com.mes2.metadata.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.metadata.domain.productDTO;
import com.mes2.metadata.persistence.MetadataDAO;

@Service
public class MetadataServiceImpl implements MetadataService {
	
	private static final Logger logger = LoggerFactory.getLogger(MetadataServiceImpl.class);
	
	@Inject
	private MetadataDAO mdao;

	@Override
	public List<productDTO> productListAll() throws Exception {
		logger.debug("S : productListAll()");
		return mdao.getproductListAll();
	}
	
	



}
