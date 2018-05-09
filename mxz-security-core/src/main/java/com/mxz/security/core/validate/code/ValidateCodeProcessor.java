package com.mxz.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 下午8:42:13
 **/
/**
 * 校验码处理器，封装不同校验码的处理逻辑
 * @author mxz
 *
 */
public interface ValidateCodeProcessor {
	/**
	 * 验证码放入session时的前缀
	 */
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
	
	/**
	 * 创建校验码
	 * @param request
	 * @throws Exception
	 */
	void create(ServletWebRequest request) throws Exception;
	
	/**
	 * 校验验证码
	 * 
	 * @param servletWebRequest
	 * @throws Exception
	 */
	void validate(ServletWebRequest servletWebRequest);
}
