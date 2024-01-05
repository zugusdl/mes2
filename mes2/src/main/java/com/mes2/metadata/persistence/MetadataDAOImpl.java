package com.mes2.metadata.persistence;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.metadata.domain.Criteria;
import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.md_productDTO;
import com.mes2.platform.persistence.PlatformDAOImpl;

@Repository
public class MetadataDAOImpl implements MetadataDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformDAOImpl.class);
	
	private static final String NAMESPACE ="com.mes2.mapper.metadataMapper";
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public int productinsert(md_productDTO dto) throws Exception {
		logger.debug(" DAO : productinsert() " + dto);
		return sqlSession.insert(NAMESPACE + ".insert", dto);
	}


	@Override
	public int productupdate(md_productDTO dto) throws Exception {
		logger.debug(" DAO : productupdate() " + dto);
		return sqlSession.update(NAMESPACE + ".update", dto);
	}


	@Override
	public int productdelete(md_productDTO dto) throws Exception {
		logger.debug(" DAO : productdelete() " + dto);
		return sqlSession.update(NAMESPACE + ".delete", dto);
	}


	@Override
	public int gettotalcount(alllistDTO aDTO) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE + ".count", aDTO);
	}


	@Override
	public List<md_productDTO> getlist(alllistDTO aDTO) throws Exception {
		
		return sqlSession.selectList(NAMESPACE + ".list", aDTO);
	}


	@Override
	public String commoncode(String category) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE + ".common", category);
	}


	@Override
	public String number(String commoncode) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE + ".number", commoncode);
	}
	
	
	

}
