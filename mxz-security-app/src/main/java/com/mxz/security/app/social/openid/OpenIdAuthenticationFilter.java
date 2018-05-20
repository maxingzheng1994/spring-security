package com.mxz.security.app.social.openid;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.mxz.security.core.properties.SecurityConstants;

/*作者：马兴争
 *日期: 2018年5月17日
 *时间： 下午11:11:27
 **/

public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
	private String openIdParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_OPENID;
	private String providerIdParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_PROVIDERID;
	private boolean postOnly = true;
	
	public OpenIdAuthenticationFilter() {
		super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID, "POST"));
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported:" + request.getMethod());
		}
		
		String openid = obtainOpenId(request);
		String providerId = obtainProviderId(request);
		
		if (openid == null) {
			openid = StringUtils.EMPTY;
		}
		if (providerId == null) {
			providerId = StringUtils.EMPTY;
		}
		
		openid = openid.trim();
		providerId = providerId.trim();
		
		OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(openid, providerId);
		
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	private void setDetails(HttpServletRequest request, OpenIdAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	private String obtainProviderId(HttpServletRequest request) {
		return request.getParameter(providerIdParameter);
	}

	private String obtainOpenId(HttpServletRequest request) {
		return request.getParameter(openIdParameter);
	}
}
