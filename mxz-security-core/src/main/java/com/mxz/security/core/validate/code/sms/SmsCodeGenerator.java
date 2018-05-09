package com.mxz.security.core.validate.code.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.mxz.security.core.properties.SecurityProperties;
import com.mxz.security.core.validate.code.ValidateCode;
import com.mxz.security.core.validate.code.ValidateCodeGenerator;

/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 上午11:42:10
 **/
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator{
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}

}
