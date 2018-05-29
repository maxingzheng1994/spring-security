package com.mxz.security.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.mxz.security.core.properties.Oauth2ClientProperties;
import com.mxz.security.core.properties.SecurityProperties;

/*作者：马兴争
 *日期: 2018年5月9日
 *时间： 上午1:22:09
 **/
@Configuration
@EnableAuthorizationServer  // 实现认证服务器
public class MxzAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired(required = false)
	private JwtAccessTokenConverter jwtAccessTokenConverter; 
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired(required = false)
	private TokenEnhancer jwtTokenEnhancer;
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.tokenStore(tokenStore)
		.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService);
		
		if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
			TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
			List<TokenEnhancer> enhancers = new ArrayList<>();
			enhancers.add(jwtTokenEnhancer);
			enhancers.add(jwtAccessTokenConverter);
			enhancerChain.setTokenEnhancers(enhancers);
			
			endpoints
				.tokenEnhancer(enhancerChain)
				.accessTokenConverter(jwtAccessTokenConverter);
		}
	}
	
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		InMemoryClientDetailsServiceBuilder  builder = clients.inMemory();
		if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClient())) {
			for (Oauth2ClientProperties config : securityProperties.getOauth2().getClient()) {
				builder.withClient(config.getClientId())
					.secret(config.getClientSecret())
					.accessTokenValiditySeconds(config.getAccessTokenValiditySeconds())
					.authorizedGrantTypes("refresh_token", "password", "custom", "authorization_code")
					.refreshTokenValiditySeconds(2592000)
					.scopes("all", "read", "write");
			}
		}
	}
}
  