package com.mxz.security.core.social.qq.connect;

import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/*作者：马兴争
 *日期: 2018年4月20日
 *时间： 上午12:17:09
 **/
/**
 * 返回code后请求accessToken  返回的为text/html  却没有处理  参考OAuth2Template.createRestTemplate  发送请求令牌返回值处理出错
 * @author mxz
 *
 */
public class QQOAuth2Template extends OAuth2Template{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		//设定为true  ， 传clientId
		setUseParametersForClientAuthentication(true);
	}
	
	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
		logger.info("获取accessToken的响应：" + responseStr);
		
		String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");
		
		String accessToken = StringUtils.substringAfterLast(items[0], "=");
		Long expiresIn = new Long(StringUtils.substringAfterLast(items[2], "="));
		String refreshToken = StringUtils.substringAfterLast(items[3], "=");
		return new AccessGrant(accessToken, null, refreshToken, expiresIn);
	}
	
	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = super.getRestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return super.createRestTemplate();
	}

}
