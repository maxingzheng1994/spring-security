package com.mxz.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 上午11:41:01
 **/
public interface ValidateCodeGenerator {
	ValidateCode generate(ServletWebRequest request);
}
