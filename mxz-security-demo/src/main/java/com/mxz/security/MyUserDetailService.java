package com.mxz.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/*作者：马兴争
 *日期: 2018年4月14日
 *时间： 下午3:09:45
 **/
@Service
public class MyUserDetailService implements UserDetailsService, SocialUserDetailsService{
	
	@Autowired
	private PasswordEncoder encoder;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("登陆用户名"+username);
		//根据用户名 查找用户信息  判断用户是否被冻结
		return buildUser(username);
	}
	
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		return buildUser(userId);
	}

	private SocialUserDetails buildUser(String userId) {
		logger.info("社交登陆用户Id"+userId);
		
		String password = encoder.encode("123456");
		logger.info("登陆用户名"+password);
		return new SocialUser(userId, password, true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER"));
	}

}
