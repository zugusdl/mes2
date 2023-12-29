package com.mes2.metadata.service;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.metadata.domain.Criteria;
import com.mes2.metadata.domain.md_productDTO;
import com.mes2.metadata.persistence.MetadataDAO;

@Service
public class MetadataServiceImpl implements MetadataService {
	
	private static final Logger logger = LoggerFactory.getLogger(MetadataServiceImpl.class);
	
	@Inject
	private MetadataDAO mdao;

	
	@Override
	public List<md_productDTO> productListAll() throws Exception {
		logger.debug("S : productListAll()");
		return mdao.getproductListAll();
	}

	@Override
	public List<md_productDTO> productdatefilter(Date start, Date end, String search) throws Exception {
		logger.debug("S : productdatefilter()");
		return mdao.getproductdatefilter(start, end, search);
	}

	@Override
	public int productinsert(md_productDTO dto) throws Exception {
		
		return mdao.productinsert(dto);
	}

	@Override
	public int productupdate(md_productDTO dto) throws Exception {
		
		return mdao.productupdate(dto);
	}
	
	@Override
	public int productdelete(md_productDTO dto) throws Exception {
		
		return mdao.productdelete(dto);
	}

	@Override
	public List<md_productDTO> boardListPage(Criteria cri) throws Exception {
		
		return mdao.getBoardListPage(cri);
	}

	@Override
	public int totalBoardCount() throws Exception {
		logger.debug(" S : totalBoardCount()  ");
		return mdao.getBoardCount();
	}

}
