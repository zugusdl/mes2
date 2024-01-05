package com.mes2.metadata.service;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.metadata.domain.Criteria;
import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.md_productDTO;
import com.mes2.metadata.persistence.MetadataDAO;

@Service
public class MetadataServiceImpl implements MetadataService {
	
	private static final Logger logger = LoggerFactory.getLogger(MetadataServiceImpl.class);
	
	@Inject
	private MetadataDAO mdao;
	

	@Override
	public int productinsert(md_productDTO dto) throws Exception {
		
		//공통코드가 뭔지 가져오기
		String commoncode = mdao.commoncode(dto.getCategory());
		logger.debug("공통코드다!" + commoncode);
		
		// 공통코드가 몇자리인 알아보기
		int leng = commoncode.length();
		
		//품목테이블에 상품코드 가져오기
		String code = mdao.number(commoncode);
		
		//숫자만 빼서 +1 해주기
		int number = Integer.parseInt(code.substring(leng));
		number++;
		
		String.valueOf(number);
		
		//공통코드와 숫자 합치기
		String code2 = commoncode + number;
		logger.debug("이제 끝!" + code2);
		
		dto.setProduct_code(code2);
		
		
		return mdao.productinsert(dto);
	}

	@Override
	public int productupdate(md_productDTO dto) throws Exception {
		
		return mdao.productupdate(dto);
	}
	
	@Override
	public int productdelete(md_productDTO dto) throws Exception {
		
		return mdao.productdelete(dto);
	}

	@Override
	public int gettotalcount(alllistDTO aDTO) throws Exception {
		
		return mdao.gettotalcount(aDTO);
	}

	@Override
	public List<md_productDTO> getlist(alllistDTO aDTO) throws Exception {
		
		return mdao.getlist(aDTO);
	}

	
	

}
