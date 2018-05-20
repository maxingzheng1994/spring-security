package com.mxz.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/*作者：马兴争
 *日期: 2018年5月17日
 *时间： 上午12:06:31
 **/
public interface ValidateCodeRepository {
	/**
	 * 保存验证码
	 * @param request
	 * @param code
	 * @param codeType
	 */
	void save(ServletWebRequest request, ValidateCode code, ValidateCodeType codeType);
	
	/**
	 * 获取验证码
	 * @param request
	 * @param codeType
	 * @return
	 */
	ValidateCode get(ServletWebRequest request, ValidateCodeType codeType);
	/**
	 * 移除
	 * @param request
	 * @param codeType
	 */
	void remove(ServletWebRequest request, ValidateCodeType codeType);
}
