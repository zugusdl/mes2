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
	public List<md_productDTO> getproductdatefilter(Date start, Date end, String search) throws Exception {
		logger.debug(" DAO : getproductdatefilter() ");
		//logger.debug("날짜확인" + start);
		//logger.debug("이름확인" + searchName);
		
		Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("start", start);
        paramMap.put("end", end);
        paramMap.put("searchName", search);

        return sqlSession.selectList(NAMESPACE + ".listDATE", paramMap);
	}


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
	public List<md_productDTO> getBoardListPage(int page) throws Exception {
		logger.debug(" DAO : getBoardListPage() ");
		
		// 페이징처리 계산
		// page 1 => 1~10  page 2 => 11~20 ... page 3 => 21-30
		//  => limit 0,10   =>  limit  10,10    => limit 20,10
		
		page = (page - 1) * 10;
		
		return sqlSession.selectList(NAMESPACE + ".listPage",page);
	}
	
	@Override
	public List<md_productDTO> getBoardListPage(Criteria cri) throws Exception {
		logger.debug(" DAO : getBoardListPage(Criteria cri)");
		return sqlSession.selectList(NAMESPACE + ".listPage", cri);
	}
	
	
	@Override
	public int getBoardCount() throws Exception {
		logger.debug(" DAO : getBoardCount() ");
		return sqlSession.selectOne(NAMESPACE + ".countBoard");
	}
	
	
	
	
	
}
