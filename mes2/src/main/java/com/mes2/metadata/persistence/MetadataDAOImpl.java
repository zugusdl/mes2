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

import com.mes2.metadata.domain.md_productDTO;
import com.mes2.platform.persistence.PlatformDAOImpl;

@Repository
public class MetadataDAOImpl implements MetadataDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformDAOImpl.class);
	
	private static final String NAMESPACE ="com.mes2.mapper.metadataMapper";
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<md_productDTO> getproductListAll() throws Exception {
		logger.debug(" DAO : getproductListAll() ");
		return sqlSession.selectList(NAMESPACE + ".listALL");
	}

	@Override
	public List<md_productDTO> getproductdatefilter(Date start, Date end) throws Exception {
		logger.debug(" DAO : getproductdatefilter() ");
		logger.debug("날짜확인" + start);
		
		Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("start", start);
        paramMap.put("end", end);

        return sqlSession.selectList(NAMESPACE + ".listDATE", paramMap);
	}
	
	
	
	
}
