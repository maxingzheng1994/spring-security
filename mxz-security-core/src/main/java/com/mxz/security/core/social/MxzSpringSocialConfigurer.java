package com.mxz.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/*作者：马兴争
 *日期: 2018年4月19日
 *时间： 上午1:01:30
 **/
public class MxzSpringSocialConfigurer extends SpringSocialConfigurer{
	
	private String filterProcessUrl;
	
	public MxzSpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessUrl = filterProcessesUrl;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessUrl);
		return (T) filter;
	}
}
