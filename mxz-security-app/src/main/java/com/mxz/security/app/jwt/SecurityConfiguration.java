package com.mxz.security.app.jwt;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.InMemoryTraceRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.social.security.SpringSocialConfigurer;

import com.mxz.security.app.social.openid.OpenIdAuthenticationSecurityConfig;
import com.mxz.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.mxz.security.core.properties.SecurityConstants;
import com.mxz.security.core.validate.code.ValidateCodeSecurityConfig;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		
		// 清除认证?
		auth.eraseCredentials(false);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
		.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
		.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
		.successHandler(mxzAuthenticationSuccessHandler)
		.failureHandler(mxzAuthenticationFailureHandler);
		http.authorizeRequests()
			.antMatchers("/image/**").permitAll()
			.anyRequest().authenticated()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
			.and().exceptionHandling().accessDeniedPage("/deny")
			.and().rememberMe().tokenValiditySeconds(86400).tokenRepository(new JdbcTokenRepositoryImpl());
	}
}
