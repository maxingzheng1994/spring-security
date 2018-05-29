package com.mxz.security.rbac;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/*作者：马兴争
 *日期: 2018年5月30日
 *时间： 上午12:39:18
 **/
@Component("rbacService")
public class RbacServiceImpl implements RbacService {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public boolean hasPremission(HttpServletRequest request, Authentication authentication) {
		Object principle = authentication.getPrincipal();
		boolean hasPremission = false;
		if (principle instanceof UserDetails) {
			String userName = ((UserDetails) principle).getUsername();

			// 读取用户所拥有权限的所有Url
			Set<String> urls = new HashSet<>();

			for (String url : urls) {
				if (antPathMatcher.match(url, request.getRequestURI())) {
					hasPremission = true;
					break;
				}
			}
		}
		return hasPremission;
	}

}
