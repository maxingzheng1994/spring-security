package com.mxz.security.core.properties;
/*作者：马兴争
 *日期: 2018年5月27日
 *时间： 下午5:40:27
 **/
public class Oauth2Properties {
	
	private String jwtSigningKey = "mxz";
	private Oauth2ClientProperties[] client = {};
	
	
	public String getJwtSigningKey() {
		return jwtSigningKey;
	}

	public void setJwtSigningKey(String jwtSigningKey) {
		this.jwtSigningKey = jwtSigningKey;
	}

	public Oauth2ClientProperties[] getClient() {
		return client;
	}

	public void setClient(Oauth2ClientProperties[] client) {
		this.client = client;
	}
	
}
