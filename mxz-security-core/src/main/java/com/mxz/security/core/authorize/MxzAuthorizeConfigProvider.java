package com.mxz.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.mxz.security.core.properties.SecurityConstants;
import com.mxz.security.core.properties.SecurityProperties;

/*作者：马兴争
 *日期: 2018年5月29日
 *时间： 上午12:52:34
 **/
@Component
@Order(Integer.MIN_VALUE)
public class MxzAuthorizeConfigProvider implements AuthorizeConfigProvider{

	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers(
				securityProperties.getBrowser().getLoginPage(),
				securityProperties.getBrowser().getSignUpPage(),
				SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
				SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
				SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
				"/user/regist",
				"/social/user", 					
				securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".json",
				securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".html"
				,securityProperties.getBrowser().getLogOutUrl())
		  		.permitAll();
	}

}
