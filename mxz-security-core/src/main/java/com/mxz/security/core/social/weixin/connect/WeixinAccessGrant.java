package com.mxz.security.core.social.weixin.connect;

import org.springframework.social.oauth2.AccessGrant;

/*作者：马兴争
 *日期: 2018年4月20日
 *时间： 下午10:37:23
 **/
public class WeixinAccessGrant extends AccessGrant{

	private static final long serialVersionUID = -6889217501109309289L;

	private String openId;
	public WeixinAccessGrant() {
		super("");
	}
	public WeixinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
		super(accessToken, scope, refreshToken, expiresIn);
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	


}
