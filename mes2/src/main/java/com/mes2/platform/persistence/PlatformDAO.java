package com.mes2.platform.persistence;

import java.util.List;

import com.mes2.platform.domain.mdbDTO;
import com.mes2.platform.domain.mdpDTO;

public interface PlatformDAO {
	public mdbDTO customerLogin(mdbDTO mdto) throws Exception;
	public List<mdpDTO> inqueryProduct(String searchType, String search) throws Exception;
}
