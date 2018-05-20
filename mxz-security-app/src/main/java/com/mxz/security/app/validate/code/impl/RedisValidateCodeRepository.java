package com.mxz.security.app.validate.code.impl;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.mxz.security.core.validate.code.ValidateCode;
import com.mxz.security.core.validate.code.ValidateCodeException;
import com.mxz.security.core.validate.code.ValidateCodeRepository;
import com.mxz.security.core.validate.code.ValidateCodeType;

/*作者：马兴争
 *日期: 2018年5月17日
 *时间： 上午12:30:40
 **/
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository{

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Override
	public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType codeType) {
		redisTemplate.opsForValue().set(buildkey(request, codeType), code, 30, TimeUnit.MINUTES);
	}

	private Object buildkey(ServletWebRequest request, ValidateCodeType codeType) {
		String deviceId = request.getHeader("deviceId");
		if (StringUtils.isBlank(deviceId)) {
			throw new ValidateCodeException("请在请求头中携带deviceId参数");
		}
		return "code:" + codeType.toString().toLowerCase() + ":" + deviceId;
	}

	@Override
	public ValidateCode get(ServletWebRequest request, ValidateCodeType codeType) {
		return (ValidateCode) redisTemplate.opsForValue().get(buildkey(request, codeType));
	}

	@Override 
	public void remove(ServletWebRequest request, ValidateCodeType codeType) {
		redisTemplate.delete(buildkey(request, codeType));
	}

}
