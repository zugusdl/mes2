package com.mes2.platform.persistence;

import com.mes2.platform.domain.mdbDTO;

public interface PlatformDAO {
	public mdbDTO customerLogin(mdbDTO mdto) throws Exception;
}
