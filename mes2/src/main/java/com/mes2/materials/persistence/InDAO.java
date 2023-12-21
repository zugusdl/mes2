package com.mes2.materials.persistence;

import java.util.List;
import com.mes2.materials.domain.InDTO;

public interface InDAO {


	public List<InDTO> inSelect(InDTO in) throws Exception;
	
}
