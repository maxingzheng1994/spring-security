package com.mxz.security.core.properties;
/*作者：马兴争
 *日期: 2018年4月19日
 *时间： 上午12:11:53
 **/
public class SocialProperties {
	private String filterProcessesUrl = "/auth";
	
	private QQProperties qq = new QQProperties();

	private WeixinProperties weixin = new WeixinProperties();

	
	public WeixinProperties getWeixin() {
		return weixin;
	}

	public void setWeixin(WeixinProperties weixin) {
		this.weixin = weixin;
	}

	public String getFilterProcessesUrl() {
		return filterProcessesUrl;
	}

	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}
	public QQProperties getQq() {
		return qq;
	}

	public void setQq(QQProperties qq) {
		this.qq = qq;
	}
}
