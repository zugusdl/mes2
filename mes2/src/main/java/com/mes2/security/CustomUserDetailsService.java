package com.mes2.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mes2.platform.domain.MdbDTO;
import com.mes2.platform.mapper.PlatformMapper;
import com.mes2.security.domain.CustomUser;

import lombok.Setter;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Setter(onMethod_ = {@Autowired})
	private PlatformMapper platformMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.debug("UserDetails - loadUserByUsername() 호출");
		logger.debug("@@@ username: " + userName);
		
		// userName means company_code
		MdbDTO mdbDTO = platformMapper.read(userName);
		
		logger.debug("@@@@mdbDTO: " + mdbDTO);
		return mdbDTO == null ? null : new CustomUser(mdbDTO);
	}

}
