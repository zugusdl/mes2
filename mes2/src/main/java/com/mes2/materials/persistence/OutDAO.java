package com.mes2.materials.persistence;

import java.util.List;

import com.mes2.materials.domain.OutDTO;

public interface OutDAO {

	// 출고 등록 
	public void registerOutbound(OutDTO odto) throws Exception;
			
	// 출고 전체 리스트 조회
	public List<OutDTO> getAllOutboundInfo(OutDTO odto) throws Exception;
	
	// 출고 수량 업데이트
	public void updateQuantity(String product_code, int quantity, String category) throws Exception;
}
