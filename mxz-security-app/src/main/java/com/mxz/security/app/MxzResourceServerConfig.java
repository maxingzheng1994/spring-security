package com.mxz.security.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import com.mxz.security.app.social.openid.OpenIdAuthenticationSecurityConfig;
import com.mxz.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.mxz.security.core.properties.SecurityConstants;
import com.mxz.security.core.properties.SecurityProperties;
import com.mxz.security.core.validate.code.ValidateCodeSecurityConfig;

/*作者：马兴争
 *日期: 2018年5月9日
 *时间： 上午1:22:09
 **/
@Configuration
@EnableResourceServer
public class MxzResourceServerConfig extends ResourceServerConfigurerAdapter{
	

	@Autowired
	protected AuthenticationSuccessHandler mxzAuthenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler mxzAuthenticationFailureHandler;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;
	
	@Autowired
	private SpringSocialConfigurer mxzSocialSecurityConfig;
		
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin()
		.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
		.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
		.successHandler(mxzAuthenticationSuccessHandler)
		.failureHandler(mxzAuthenticationFailureHandler);
		http.apply(validateCodeSecurityConfig)
				.and()
			.apply(smsCodeAuthenticationSecurityConfig)
				.and()
			.apply(mxzSocialSecurityConfig)
				.and()
			.apply(openIdAuthenticationSecurityConfig)
				.and()
			.authorizeRequests()
				.antMatchers(
						securityProperties.getBrowser().getLoginPage(),
						securityProperties.getBrowser().getSignUpPage(),
						SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
						SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
						SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID,
						SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
						"/user/regist",
						"/social/user", 					
						securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".json",
						securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".html"
						,securityProperties.getBrowser().getLogOutUrl())
				  		.permitAll()
			.anyRequest()
			.authenticated()
			.and()
		.csrf().disable();
	}
}
 