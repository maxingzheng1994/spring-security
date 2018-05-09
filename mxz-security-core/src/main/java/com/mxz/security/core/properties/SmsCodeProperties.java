 package com.mxz.security.core.properties;
/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 上午3:03:08
 **/
public class SmsCodeProperties {
	
	private int length = 6;
	private int expireIn = 60;
	// 用来过滤imagecode的url
	private String url = "";
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}
	
}
