package com.mxz.security.core.authorize;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/*作者：马兴争
 *日期: 2018年5月29日
 *时间： 下午11:57:46
 **/
@Component
public class MxzAuthorizeConfigManager implements AuthorizeConfigManager{

	// 授权的应该又顺序否则后面的会覆盖前面的请求
	@Autowired
	private List<AuthorizeConfigProvider> authorizeConfigProviders; 
	
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
			authorizeConfigProvider.config(config);
		}
		config.anyRequest().authenticated();
	}

}
