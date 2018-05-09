package com.mxz.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mxz.security.core.properties.SecurityProperties;
import com.mxz.security.core.validate.code.image.ImageCodeGenerator;
import com.mxz.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.mxz.security.core.validate.code.sms.SmsCodeSender;

/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 上午11:48:56
 **/
@Configuration
public class ValidateCodeBeanConfig {
	
	@Bean
	//增量式开发，加入自定义图片验证码生成器
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator(SecurityProperties securityProperties) {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}
}
