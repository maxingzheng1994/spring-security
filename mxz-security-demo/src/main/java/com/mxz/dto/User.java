package com.mxz.dto;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/*作者：马兴争
 *日期: 2018年4月9日
 *时间： 下午11:51:58
 **/
public class User {
	private String id;
	private String username;
	@NotBlank(message = "密码不能为空")
	private String password;
	private Date birthday;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
