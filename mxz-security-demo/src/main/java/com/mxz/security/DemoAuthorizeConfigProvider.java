package com.mxz.security;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.mxz.security.core.authorize.AuthorizeConfigProvider;

/*作者：马兴争
 *日期: 2018年5月30日
 *时间： 上午12:05:45
 **/
@Component
@Order(Integer.MAX_VALUE)
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider{

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		//config.antMatchers(method)
		config.anyRequest().access("@rbacService.hasPermission(request, authentication)"); 
	}

}
