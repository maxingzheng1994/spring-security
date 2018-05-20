package com.mxz.security.app.social.openid;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.SpringSecurityCoreVersion;

/*作者：马兴争
 *日期: 2018年5月17日
 *时间： 下午11:07:18
 **/
public class OpenIdAuthenticationToken extends AbstractAuthenticationToken{

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
	private final Object principal;
	private String providerId;
	
	
	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public OpenIdAuthenticationToken(Object principal,
			String providerId) {
		super(null);
		this.principal = principal;
		this.providerId = providerId;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

}
