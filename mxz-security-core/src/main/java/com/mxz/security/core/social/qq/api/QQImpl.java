package com.mxz.security.core.social.qq.api;
/*作者：马兴争
 *日期: 2018年4月17日
 *时间： 上午12:47:25
 **/

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ{
	
	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
	
	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
	
	private String appId;
	
	private String openId;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public QQImpl(String accessToken, String appId) {
		//将accessToken 放到parameter上。而不是头上
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;
		
		String url = String.format(URL_GET_OPENID, accessToken);
		//callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
		String result = getRestTemplate().getForObject(url, String.class);
		System.out.println(result);
		
		this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
	}


	@Override
	public QQUserInfo getUserInfo() {
		//accessToken 已在前面加入请求参数了
		String url = String.format(URL_GET_USERINFO, appId, openId);
		
		String result = getRestTemplate().getForObject(url, String.class);
		System.out.println(result);
		QQUserInfo userInfo = null;
		try {
			userInfo = objectMapper.readValue(result, QQUserInfo.class);
			userInfo.setOpenId(openId);
			return userInfo;
		} catch (IOException e) {
			throw new RuntimeException("获取用户信息失败", e);
		}
	}

}
