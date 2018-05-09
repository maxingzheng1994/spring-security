package com.mxz.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.mxz.security.browser.logout.MxzLogoutSuccessHandler;
import com.mxz.security.browser.session.MxzExpiredSessionStrategy;
import com.mxz.security.browser.session.MxzInvalidSessionStrategy;
import com.mxz.security.core.properties.SecurityProperties;

/*作者：马兴争
 *日期: 2018年4月24日
 *时间： 上午12:58:44
 **/
@Configuration
public class BrowserSecurityBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(InvalidSessionStrategy.class)
	public InvalidSessionStrategy invalidSessionStrategy(){
		return new MxzInvalidSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
	}
	
	@Bean
	@ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
		return new MxzExpiredSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
	}
	
	@Bean
	@ConditionalOnMissingBean(LogoutSuccessHandler.class)
	public LogoutSuccessHandler LogoutSuccessHandler(){
		return new MxzLogoutSuccessHandler(securityProperties.getBrowser().getLogOutUrl());
	}
}
