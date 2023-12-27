package com.mes2.platform.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.mes2.platform.domain.MdbDTO;
import com.mes2.platform.domain.MdpDTO;
import com.mes2.platform.domain.SoiDTO;
import com.mes2.platform.domain.SopDTO;
import com.mes2.platform.domain.OrderRequestDTO;

public interface PlatformService {
	// 로그인
	public MdbDTO customerLogin(MdbDTO mdto) throws Exception;
	
	// 품목 목록 조회
	public List<MdpDTO> inqueryProduct(String searchType, String search) throws Exception;
	
	// 품목 하나 선택
	public MdpDTO registProduct(String product_code) throws Exception;
	
	// 발주 신청
	public void insertOrder(OrderRequestDTO orDTO, HttpSession session) throws Exception;
	
	// 주문 목록 조회
	public List<SoiDTO> getOrderList(String company_code) throws Exception;
	
	// 주문 상세 조회
	public List<SoiDTO> getOrderDetail(String order_code) throws Exception;
	
	// 주문 수정
	public void modifyOrder(List<SopDTO> sopList) throws Exception;
	
	// 주문 삭제
	public void deleteOrder(String order_code) throws Exception;
}
