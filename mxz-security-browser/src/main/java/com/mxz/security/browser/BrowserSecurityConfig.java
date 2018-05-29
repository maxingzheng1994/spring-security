package com.mxz.security.browser;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import com.mxz.security.browser.session.MxzExpiredSessionStrategy;
import com.mxz.security.core.authentication.AbstractChannelSecurityConfig;
import com.mxz.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.mxz.security.core.authorize.AuthorizeConfigManager;
import com.mxz.security.core.properties.SecurityConstants;
import com.mxz.security.core.properties.SecurityProperties;
import com.mxz.security.core.validate.code.ValidateCodeSecurityConfig;

/*作者：马兴争
 *日期: 2018年4月14日
 *时间： 下午2:34:38
 **/
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig{
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SpringSocialConfigurer mxzSocialSecurityConfig;
	

	@Autowired
	private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
	
	@Autowired
	private InvalidSessionStrategy invalidSessionStrategy;
		
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	private AuthorizeConfigManager authorizeConfigManager; 
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		//创建token 表
		//tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}; 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		List<String> premits =  Arrays.asList(StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getBrowser().getPermit(), ";"));
		premits.stream().forEach( url -> {
			try {
				http.authorizeRequests().antMatchers(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		applyPasswordAuthenticationConfig(http);
		
		http.apply(validateCodeSecurityConfig)
				.and()
			.apply(smsCodeAuthenticationSecurityConfig)
				.and()
			.apply(mxzSocialSecurityConfig)
				.and()
			.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
				.and()
			.sessionManagement()
				.invalidSessionStrategy(invalidSessionStrategy)
				//最大的登陆数量
				.maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
				//阻止后登陆的
				.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
					//踢掉后的页面
				.expiredSessionStrategy(sessionInformationExpiredStrategy)
				.and()
				.and()
			.logout()
				.logoutUrl("/signOut")
				//.logoutSuccessUrl("/demo-logout.html")
				.logoutSuccessHandler(logoutSuccessHandler)
				.deleteCookies("JSESSIONID")
				.and()
			.authorizeRequests()
				.antMatchers(
						securityProperties.getBrowser().getLoginPage(),
						securityProperties.getBrowser().getSignUpPage(),
						SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
						SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
						SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
						"/user/regist",
						"/social/user", 					
						securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".json",
						securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".html"
						,securityProperties.getBrowser().getLogOutUrl())
				  		.permitAll()
				  .antMatchers(HttpMethod.GET,"/user/*").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
		.csrf().disable();
		
		authorizeConfigManager.config(http.authorizeRequests());
	}
	
}
