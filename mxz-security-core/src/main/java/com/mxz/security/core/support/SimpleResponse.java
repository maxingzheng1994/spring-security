package com.mxz.security.core.support;
/*作者：马兴争
 *日期: 2018年4月14日
 *时间： 下午4:17:34
 **/
public class SimpleResponse {
	
	public SimpleResponse(Object content) {
		this.content = content;
	}

	private Object content;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
}
