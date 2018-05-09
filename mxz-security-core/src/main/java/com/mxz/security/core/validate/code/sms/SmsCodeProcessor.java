package com.mxz.security.core.validate.code.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.mxz.security.core.validate.code.ValidateCode;
import com.mxz.security.core.validate.code.impl.AbstractValidateCodeProcessor;

/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 下午9:01:47
 **/
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode>{
	
	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private SmsCodeSender smsCodeSender;
	@Override
	protected void send(ServletWebRequest request, ValidateCode smsCode) throws Exception {
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
		smsCodeSender.send(mobile, smsCode.getCode());
	}
}
