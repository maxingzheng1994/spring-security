package com.mxz.security.core.social.qq.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import com.mxz.security.core.properties.QQProperties;
import com.mxz.security.core.properties.SecurityProperties;
import com.mxz.security.core.social.qq.connect.QQConnectionFactory;

/*作者：马兴争
 *日期: 2018年4月19日
 *时间： 上午12:15:24
 **/
@Configuration
@ConditionalOnProperty(prefix = "mxz.security.social.qq", name = "app-id")  // 在配置propperties配置后此配置才生效
public class QQAutoConfig extends SocialAutoConfigurerAdapter{

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private SpringSocialConfigurer mxzSocialSecurityConfig;
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQProperties qqConfig = securityProperties.getSocial().getQq();
		return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
	}
	@Autowired
	private DataSource dataSource;
	
	
	@Autowired(required = false)
	private ConnectionSignUp connectionSignUp;
	
}
