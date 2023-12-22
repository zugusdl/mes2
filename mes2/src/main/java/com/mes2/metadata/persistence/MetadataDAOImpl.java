package com.mes2.metadata.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.metadata.domain.productDTO;
import com.mes2.platform.persistence.PlatformDAOImpl;

@Repository
public class MetadataDAOImpl implements MetadataDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformDAOImpl.class);
	
	private static final String NAMESPACE ="com.mes2.mapper.metadataMapper";
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<productDTO> getproductListAll() throws Exception {
		logger.debug(" DAO : getproductListAll() ");
		return sqlSession.selectList(NAMESPACE + ".listALL");
	}
	
	
}
