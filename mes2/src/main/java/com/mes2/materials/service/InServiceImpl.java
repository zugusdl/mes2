package com.mes2.materials.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.materials.domain.InDTO;
import com.mes2.materials.persistence.InDAO;

@Service
public class InServiceImpl implements InService {

	private static final Logger logger = LoggerFactory.getLogger(InServiceImpl.class);

	@Inject
	private InDAO idao;

	@Override
	public List<InDTO> inSelect() throws Exception {

		return idao.inSelect();
	}

	@Override
	public List<InDTO> detailList(String in_code) throws Exception {
		return idao.detailSelect();
	}

	
}
