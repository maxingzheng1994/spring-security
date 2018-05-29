package com.mxz.security.app.social.impl;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import com.mxz.security.app.AppSecretException;

/*作者：马兴争
 *日期: 2018年5月21日
 *时间： 下午9:47:11
 **/
@Component
public class AppSignUpUtils {
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private UsersConnectionRepository userConnectionRepository;
	
	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;
	
	public void saveConnectionData(WebRequest request, ConnectionData connectionData) {
		redisTemplate.opsForValue().set(getKey(request), connectionData, 10, TimeUnit.MINUTES);
	}
	
	public void doPostSignUp(WebRequest request, String userId) {
		String key = getKey(request);
		if (redisTemplate.hasKey(key)) {
			throw new AppSecretException("无法找到缓存的第三方信息");
		}
		ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
		Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId())
			.createConnection(connectionData);
		userConnectionRepository.createConnectionRepository(userId).addConnection(connection);
		
		redisTemplate.delete(key);
	}

	private String getKey(WebRequest request) {
		String deviceId = request.getHeader("deviceId");
		if (StringUtils.isBlank(deviceId)) {
			throw new AppSecretException("设备参数不能为空");
		}
		return "mxz:security:social.connect."+deviceId;
	}
}
