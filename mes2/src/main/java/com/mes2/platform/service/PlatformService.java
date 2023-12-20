package com.mes2.platform.service;

import java.util.List;

import com.mes2.platform.domain.mdbDTO;
import com.mes2.platform.domain.mdpDTO;

public interface PlatformService {
	public mdbDTO customerLogin(mdbDTO mdto) throws Exception;
	public List<mdpDTO> inqueryProduct(String searchType, String search) throws Exception;
}
