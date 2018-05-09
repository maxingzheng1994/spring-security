package com.mxz.security.browser.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mxz.security.core.support.SimpleResponse;

/*作者：马兴争
 *日期: 2018年4月25日
 *时间： 下午4:49:27
 **/
public class MxzLogoutSuccessHandler implements LogoutSuccessHandler{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public MxzLogoutSuccessHandler(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}
	private String logoutUrl;
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		logger.info("退出成功");
		if (StringUtils.isBlank(logoutUrl)) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功")));
		} else {
			response.sendRedirect(logoutUrl);
		}
		
	}

}
