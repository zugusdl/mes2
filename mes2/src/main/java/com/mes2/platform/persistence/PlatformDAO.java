package com.mes2.platform.persistence;

import java.util.List;

import com.mes2.platform.domain.MdbDTO;
import com.mes2.platform.domain.MdpDTO;
import com.mes2.platform.domain.SoiDTO;
import com.mes2.platform.domain.SopDTO;
import com.mes2.platform.domain.OrderDetailDTO;

public interface PlatformDAO {
	// 로그인
	public MdbDTO customerLogin(MdbDTO mdto) throws Exception;
	
	// 발주 신청 시 품목 목록 조회
	public List<MdpDTO> inqueryProduct(String searchType, String search) throws Exception;
	
	// 품목 하나 등록
	public MdpDTO registProduct(String product_code) throws Exception;
	
	// 발주 신청
	public void insertOrder(SoiDTO soiDTO, List<SopDTO> sopList);
	
	// 금일 마지막 주문번호
	public String countTodayOrder(String todayDate) throws Exception;
	
	// 주문 목록 조회
	public List<SoiDTO> getOrderList(String company_code) throws Exception;
	
	// 주문 상세 조회
	public List<OrderDetailDTO> getOrderDetail(String order_code) throws Exception;
}
