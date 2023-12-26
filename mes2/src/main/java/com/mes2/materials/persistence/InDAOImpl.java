package com.mes2.materials.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.materials.domain.InDTO;

@Repository
public class InDAOImpl implements InDAO {

	private static final Logger logger = LoggerFactory.getLogger(InDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE ="com.mes2.mapper.MaterialsMapper";

	@Override
	public List<InDTO> inSelect() throws Exception {
		
		return sqlSession.selectList(NAMESPACE + ".inList");
	}

	@Override
	public List<InDTO> detailSelect() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".detailList");
	}


	
	
}
