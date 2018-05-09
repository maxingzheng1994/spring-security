package com.mxz.security.core.properties;

/*作者：马兴争
 *日期: 2018年4月14日
 *时间： 下午4:23:58
 **/
public class BrowserProperties {
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

	private String signUpPage = SecurityConstants.DEFAULT_SINGUP_PAGE_URL;

	private String logOutUrl = "demo-logout.html";

	private LoginResponseType loginType = LoginResponseType.JSON;
	
	private String permit = "";

	private int rememberMeSeconds = 3600;

	private SessionProperties session = new SessionProperties();


	public String getLogOutUrl() {
		return logOutUrl;
	}

	public void setLogOutUrl(String logOutUrl) {
		this.logOutUrl = logOutUrl;
	}

	public SessionProperties getSession() {
		return session;
	}

	public void setSession(SessionProperties session) {
		this.session = session;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public LoginResponseType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginResponseType loginType) {
		this.loginType = loginType;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public String getSignUpPage() {
		return signUpPage;
	}

	public void setSignUpPage(String signUpPage) {
		this.signUpPage = signUpPage;
	}

	public String getPermit() {
		return permit;
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}
}
