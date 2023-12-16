package com.mes2.platform.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.platform.domain.mdbDTO;

@Repository
public class PlatformDAOImpl implements PlatformDAO {

	private static final Logger logger = LoggerFactory.getLogger(PlatformDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "매퍼설정";

	@Override
	public mdbDTO customerLogin(mdbDTO mdto) throws Exception {

		return null;
	}

}
