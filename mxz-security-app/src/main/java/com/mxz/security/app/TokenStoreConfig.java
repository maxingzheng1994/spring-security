package com.mxz.security.app;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.mxz.security.app.jwt.MxzJwtTokenEnhancer;
import com.mxz.security.core.properties.SecurityProperties;

/*作者：马兴争
 *日期: 2018年5月27日
 *时间： 下午5:58:04
 **/
@Configuration
public class TokenStoreConfig {

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	@Bean
	@ConditionalOnProperty(prefix = "mxz.security.oauth2", name = "storeType", havingValue = "redis")
	public TokenStore redisTokenStore() {
		return new RedisTokenStore(redisConnectionFactory);
	}
	
	@Configuration
	//验证设置 mxz.security.oauth2。storeType  默认是生效的  
	@ConditionalOnProperty(prefix = "mxz.security.oauth2", name = "storeType", havingValue = "jwt", matchIfMissing = true)
	public static class JwtTokenConfig {
		@Bean
		public TokenStore jwtTokenStore() {
			return new JwtTokenStore(jwtAccessTokenConverter());
		}
		@Autowired
		private SecurityProperties securityProperties;
		
		@Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter() {
			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//			KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "abc123".toCharArray())
//									.getKeyPair("tycoonclient");
//			converter.setKeyPair(keyPair);
			converter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
			return converter;
		}
		@Bean
		@ConditionalOnMissingBean(name = "jwtTokenEnhancer")
		public TokenEnhancer jwtTokenEnhancer() {
			return new MxzJwtTokenEnhancer();
		}
	}
}
