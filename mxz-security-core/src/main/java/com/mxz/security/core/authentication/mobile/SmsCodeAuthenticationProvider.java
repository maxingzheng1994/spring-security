package com.mxz.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 下午10:51:27
 **/
public class SmsCodeAuthenticationProvider implements AuthenticationProvider{
	
	private UserDetailsService userDetailService;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
		UserDetails user = userDetailService.loadUserByUsername((String) authentication.getPrincipal());
		
		if (user == null) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
		
		authenticationResult.setDetails(authenticationToken.getDetails());
		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailService() {
		return userDetailService;
	}

	public void setUserDetailService(UserDetailsService userDetailService) {
		this.userDetailService = userDetailService;
	}
	
}
