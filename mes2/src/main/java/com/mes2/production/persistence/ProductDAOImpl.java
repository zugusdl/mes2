package com.mes2.production.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mes2.production.domain.ProductDTO;
import com.mes2.production.etc.SearchParam;

@Repository
public class ProductDAOImpl implements ProductDAO{

	private final String NAMESPACE = "com.mes2.mapper.ProductMapper";
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SqlSession sqlSession;
	//테스트용 
	
	@Override
	public String getTime() {
		
		return sqlSession.selectOne(NAMESPACE+".getTime");
	}

	@Override
	public List<ProductDTO> selectBySearch(SearchParam searchParam){
		
		
		log.debug("ProductDAO : 입력받은 서치 시작 날짜 : " + searchParam.getStartDate());
		log.debug("ProductDAO : 입력받은 서치 마지막 날짜 : " + searchParam.getEndDate());
		log.debug("ProductDAO : 입력받은 서치 mdp_code : " + searchParam.getName());
		
		return sqlSession.selectList(NAMESPACE+".selectBySearch", searchParam);
	}

	@Override
	public ProductDTO selectByLot(String Lot) {
		return sqlSession.selectOne(NAMESPACE+".selectByLot", Lot);
	}
	
	
	
	
	
}