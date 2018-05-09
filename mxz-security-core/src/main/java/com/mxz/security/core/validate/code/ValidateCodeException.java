package com.mxz.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 上午12:50:24
 **/
public class ValidateCodeException extends AuthenticationException{

	private static final long serialVersionUID = -3591891280642773141L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
