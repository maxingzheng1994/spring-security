package com.mxz.security.core.validate.code;
/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 上午12:07:22
 **/

import java.io.Serializable;
import java.time.LocalDateTime;

public class ValidateCode implements Serializable{

	
	private static final long serialVersionUID = -6100027765242236872L;

	private String code;
	
	private LocalDateTime expireTime;

	public ValidateCode(String code, int expireIn) {
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}
	
	public ValidateCode(String code, LocalDateTime expireTime) {
		this.code = code;
		this.expireTime = expireTime;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	} 
}
