package com.mxz.security.core.properties;
/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 上午3:03:08
 **/
public class ImageCodeProperties extends SmsCodeProperties{
	
	public ImageCodeProperties() {
		setLength(4);
	}
	
	private int width = 67;
	private int height = 23;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
