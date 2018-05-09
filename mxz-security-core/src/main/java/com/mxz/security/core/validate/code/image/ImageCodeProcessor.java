package com.mxz.security.core.validate.code.image;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.mxz.security.core.validate.code.impl.AbstractValidateCodeProcessor;

/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 下午8:58:34
 **/
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode>{
	
	/**
	 * 发送验证码，将其写在响应中
	 */
	@Override
	protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
		ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
	}

}
