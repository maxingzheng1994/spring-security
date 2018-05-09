package com.mxz.security.core.properties;
/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 上午3:04:39
 **/
public class ValidateCodeProperties {
	
	private ImageCodeProperties image = new ImageCodeProperties();

	private SmsCodeProperties sms = new SmsCodeProperties();
	
	public SmsCodeProperties getSms() {
		return sms;
	}

	public void setSms(SmsCodeProperties sms) {
		this.sms = sms;
	}

	public ImageCodeProperties getImage() {
		return image;
	}

	public void setImage(ImageCodeProperties image) {
		this.image = image;
	}
	
	
}
