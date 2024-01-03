package com.mes2.materials.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.PurchaseDTO;

@Repository
public class PurchaseDAOImpl implements PurchaseDAO {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseDAOImpl.class);

	// 디비에 접근할 객체
	@Inject
	private SqlSession sqlSession;

	private static final String NAMESPACE = "com.mes2.mapper.MaterialsMapper";

	@Override
	public void insertPurchase(PurchaseDTO pdto) throws Exception {
		
	
	 logger.debug(" DAO : 발주 신청 insertPurchase(PurchaseDTO pdto) ");
	 sqlSession.insert(NAMESPACE + ".addPurchase", pdto);
	 }


	@Override
	public List<PurchaseDTO> listPurchase(PurchaseDTO pdto) throws Exception {
		logger.debug(" DAO -  발주 전체 리스트 listPurchase(PurchaseDTO pdto) ");

		return sqlSession.selectList(NAMESPACE + ".listPurchase", pdto);
	}

	@Override
	public List<PurchaseDTO> detailPurchase(PurchaseDTO pdto) throws Exception {
		logger.debug(" DAO - 상세 조회 리스트  detailPurchase(PurchaseDTO pdto) ");

		return sqlSession.selectList(NAMESPACE + ".datailPurchase", pdto);
	}

	// 전 상태변경 
	/*
	 * @Override public int updateOrderStatus(String status, String product_code)
	 * throws Exception { logger.
	 * debug(" DAO - 상태 변경  updateStatus(String status, String product_code) ");
	 * Map<String, Object> params = new HashMap<>(); params.put("status", status);
	 * params.put("product_code", product_code);
	 * 
	 * return sqlSession.update(NAMESPACE + ".updateOrderStatus", params); }
	 */
	
	// 후 상태변경 
	@Override
	public int updateOrderStatus(String status, String product_code) throws Exception {
		logger.debug(" DAO - 상태 변경  updateStatus(String status, String product_code) ");
		Map<String, Object> params = new HashMap<>();
		params.put("status", status);
		params.put("product_code", product_code);

		return sqlSession.update(NAMESPACE + ".updateOrderStatus", params);
	}



	@Override
	public List<PurchaseDTO> getUpdateStatus(String product_code) throws Exception {	
		return sqlSession.selectList(NAMESPACE + ".getStatus" , product_code);
	
	}


	@Override
	public void updateQuantity(String product_code, int quantity, String category) throws Exception {
	    Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("product_code", product_code);
        paramMap.put("quantity", quantity);
        paramMap.put("category", category);
        
        sqlSession.update(NAMESPACE + ".PurchaseupdateQuantity", paramMap);
	}


	@Override
	public List<PurchaseDTO> getPurchaseListPage(int page) throws Exception {
		logger.debug(" DAO : getPurchaseListPage() ");
		
		// 페이징처리 계산
		// page 1 => 1~10  page 2 => 11~20 ... page 3 => 21-30
		//  => limit 0,10   =>  limit  10,10    => limit 20,10
		
		page = (page - 1) * 10;
		
		return sqlSession.selectList(NAMESPACE + ".PurchaselistPage",page);
	}


	@Override
	public List<PurchaseDTO> getPurchaseListPage(Criteria cri) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".PurchaselistPage", cri);
	}


	@Override
	public int getPurchaseCount() throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".PurchaseCount");
	}


	
	
	
}
