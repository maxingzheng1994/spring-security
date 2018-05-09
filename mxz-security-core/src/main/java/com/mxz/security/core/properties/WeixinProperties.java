package com.mxz.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/*作者：马兴争
 *日期: 2018年4月19日
 *时间： 上午12:09:32
 **/
public class WeixinProperties extends SocialProperties{
	/**
	 * 第三方ID ，用来决定发起第三方登陆的url,默认是weixin
	 */
	private String providerId = "weixin";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
}
