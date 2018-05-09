package com.mxz.security.core.validate.code;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.mxz.security.core.properties.SecurityConstants;

/*作者：马兴争
 *日期: 2018年4月15日
 *时间： 上午12:11:21
 **/
@RestController
public class ValidateController {

	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;

	
	@GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type, String mobile) throws Exception {
		validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
	}
	
}
