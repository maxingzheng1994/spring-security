package com.mxz.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/*作者：马兴争
 *日期: 2018年5月29日
 *时间： 上午12:58:10
 **/
public interface AuthorizeConfigManager {
	
	void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
