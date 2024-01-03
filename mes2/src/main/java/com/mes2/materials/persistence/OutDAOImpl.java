package com.mes2.materials.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.materials.domain.OutDTO;

@Repository
public class OutDAOImpl implements OutDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(OutDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE ="com.mes2.mapper.outMapper";

	// 출고 목록 조회
	@Override
	public List<OutDTO> getOutList() throws Exception {
		logger.debug("DAO: getOutList() 호출");
		return sqlSession.selectList(NAMESPACE + ".getOutList");
	}
	
	// 출고 상세 조회
	@Override
	public OutDTO getOutDetail(String out_index) throws Exception {
		logger.debug("DAO: getOutDetail() 호출");
		return sqlSession.selectOne(NAMESPACE + ".getOutDetail", out_index);
	}
	
}
