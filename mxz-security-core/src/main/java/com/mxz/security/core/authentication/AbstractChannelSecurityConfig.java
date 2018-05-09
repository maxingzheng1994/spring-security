package com.mxz.security.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.mxz.security.core.properties.SecurityConstants;

/*作者：马兴争
 *日期: 2018年4月16日
 *时间： 上午12:09:33
 **/
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	protected AuthenticationSuccessHandler mxzAuthenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler mxzAuthenticationFailureHandler;
	
	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
			.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
			.successHandler(mxzAuthenticationSuccessHandler)
			.failureHandler(mxzAuthenticationFailureHandler);
	}
}
