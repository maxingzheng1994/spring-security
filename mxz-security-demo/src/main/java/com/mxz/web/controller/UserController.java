package com.mxz.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.mxz.dto.User;
import com.mxz.dto.UserQueryCondition;
import com.mxz.security.app.social.impl.AppSignUpUtils;
import com.mxz.security.core.properties.SecurityProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/*作者：马兴争
 *日期: 2018年4月9日
 *时间： 下午11:44:28
 **/
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	@Autowired
	private AppSignUpUtils appSignUpUtils;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@PostMapping("/regist")
	public void regist(User user, HttpServletRequest request) {
		//注册用户
		//不管是注册用户还是绑定用户，都会拿到一个用户唯一标识
		String userId = user.getUsername();
		
		appSignUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
		
	}
	
	@GetMapping("/me")
	public Object getCurrentuser(Authentication details, HttpServletRequest request) throws Exception{//@AuthenticationPrincipal UserDetails details) {
		// Authentication  不能获取自己加入 jwt  enhancer的 其他自定义信息 
		String header = request.getHeader("Authorization");
		String token = StringUtils.substringAfter(header, "bearer ");
		Claims cliams = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
					.parseClaimsJws(token).getBody();
		String company = (String)cliams.get("company");
		System.out.println(company);
		return details;
	}
/*	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user, BindingResult errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> {
				System.out.println(error.getDefaultMessage());
			});
		}
		User user2 = new User();
		return user;
	}*/
	@GetMapping
	@ApiOperation(value = "用户查询服务")
	public List<User> query(UserQueryCondition condition) {
		return new ArrayList<User>();
	}
	
	@GetMapping("/{id:\\d+}")
	@ResponseBody
	public User getInfo(@ApiParam("用户id") @PathVariable String id) {
		return new User();
	}
}
