package com.mxz.security.app.social.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.mxz.security.app.authentication.MxzAuthenticationSuccessHandler;
import com.mxz.security.core.social.SocialAuthenticationFilterPostProcessor;

/*作者：马兴争
 *日期: 2018年5月21日
 *时间： 下午9:28:51
 **/
@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor{
	
	@Autowired
	private MxzAuthenticationSuccessHandler mxzAuthenticationSuccessHandler;

	@Override
	public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
		socialAuthenticationFilter.setAuthenticationSuccessHandler(mxzAuthenticationSuccessHandler);
	}

}
