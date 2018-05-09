package com.mxz.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.annotation.JsonView;
import com.mxz.dto.User;
import com.mxz.dto.UserQueryCondition;

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
	
	@PostMapping("/regist")
	public void regist(User user, HttpServletRequest request) {
		//注册用户
		//不管是注册用户还是绑定用户，都会拿到一个用户唯一标识
		String userId = user.getUsername();
		
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
		
	}
	
	@GetMapping("/me")
	public Object getCurrentuser(@AuthenticationPrincipal UserDetails details) {
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
