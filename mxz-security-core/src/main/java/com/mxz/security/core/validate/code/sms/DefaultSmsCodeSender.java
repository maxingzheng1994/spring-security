package com.mxz.security.core.validate.code.sms;
/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 下午8:02:16
 **/
public class DefaultSmsCodeSender implements SmsCodeSender{

	@Override
	public void send(String mobile, String code) {
		//TODO  写发送短信验证的服务
		System.out.println("向手机"+mobile+"发送短信验证码"+code);
	}

}
