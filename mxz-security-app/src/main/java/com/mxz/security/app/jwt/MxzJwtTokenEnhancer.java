package com.mxz.security.app.jwt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/*作者：马兴争
 *日期: 2018年5月27日
 *时间： 下午7:37:09
 **/
public class MxzJwtTokenEnhancer implements TokenEnhancer{

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<>();
		info.put("company", "mxz");
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
		return  accessToken;
	}

}
