package com.mxz.security.browser.validate.code.impl;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.mxz.security.core.validate.code.ValidateCode;
import com.mxz.security.core.validate.code.ValidateCodeRepository;
import com.mxz.security.core.validate.code.ValidateCodeType;

/*作者：马兴争
 *日期: 2018年5月17日
 *时间： 上午12:16:38
 **/
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository{

	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Override
	public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType codeType) {
		sessionStrategy.setAttribute(request, getsessionKey(request, codeType), code);
	}

	private String getsessionKey(ServletWebRequest request, ValidateCodeType codeType) {
		return SESSION_KEY_PREFIX + codeType.toString().toUpperCase();
	}

	@Override
	public ValidateCode get(ServletWebRequest request, ValidateCodeType codeType) {
		return (ValidateCode) sessionStrategy.getAttribute(request, getsessionKey(request, codeType));
	}

	@Override
	public void remove(ServletWebRequest request, ValidateCodeType codeType) {
		sessionStrategy.removeAttribute(request, getsessionKey(request, codeType));
	}

}
