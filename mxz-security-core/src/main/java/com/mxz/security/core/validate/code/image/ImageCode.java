package com.mxz.security.core.validate.code.image;
/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 上午12:07:22
 **/

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.mxz.security.core.validate.code.ValidateCode;

public class ImageCode extends ValidateCode{

	private static final long serialVersionUID = 8713607605519551098L;
	private BufferedImage image;
	
	public ImageCode(BufferedImage image, String code, int expireIn) {
		super(code, expireIn);
		this.image = image;
	}
	
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
		super(code, expireTime);
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
