package com.mxz.security.rbac;

import java.net.Authenticator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

/*作者：马兴争
 *日期: 2018年5月30日
 *时间： 上午12:37:59
 **/
public interface RbacService {
	
	boolean hasPremission(HttpServletRequest request, Authentication authentication);
}
