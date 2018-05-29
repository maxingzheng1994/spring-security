package com.mxz.security.core.properties;
/*作者：马兴争
 *日期: 2018年5月27日
 *时间： 下午5:40:47
 **/
public class Oauth2ClientProperties {
	
	private String clientId;
	
	private String clientSecret;
	
	private int accessTokenValiditySeconds;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public int getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}
	
}
