package com.mxz.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/*作者：马兴争
 *日期: 2018年4月14日
 *时间： 下午4:23:58
 **/
@ConfigurationProperties(prefix = "mxz.security")
public class SecurityProperties {

	private BrowserProperties browser = new BrowserProperties();
	
	private ValidateCodeProperties code = new ValidateCodeProperties();
	
	private SocialProperties social = new SocialProperties();
	
	public SocialProperties getSocial() {
		return social;
	}

	public void setSocial(SocialProperties social) {
		this.social = social;
	}

	public ValidateCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

	public BrowserProperties getBrowser() {
		return browser;
	}
}
