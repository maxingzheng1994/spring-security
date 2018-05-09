package com.mxz.security.core.social.qq.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.mxz.security.core.social.qq.api.QQ;
import com.mxz.security.core.social.qq.api.QQImpl;

/*作者：马兴争
 *日期: 2018年4月17日
 *时间： 上午1:19:43
 **/
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ>{
	private String appId;
	
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
	
	public QQServiceProvider(String appId, String appSecret) {
		super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId = appId;
	}

	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}

}
