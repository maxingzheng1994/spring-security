package com.mxz.security.core.validate.code.sms;
/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 下午8:01:19
 **/
public interface SmsCodeSender {
	
	void send(String mobile, String code);
}
