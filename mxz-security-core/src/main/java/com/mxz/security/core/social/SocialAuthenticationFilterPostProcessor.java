package com.mxz.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/*作者：马兴争
 *日期: 2018年5月21日
 *时间： 下午9:20:53
 **/
public interface SocialAuthenticationFilterPostProcessor {

	void process(SocialAuthenticationFilter authenticationFilter);
}
