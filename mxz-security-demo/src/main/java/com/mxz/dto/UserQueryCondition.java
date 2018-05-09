package com.mxz.dto;

import io.swagger.annotations.ApiModelProperty;

/*作者：马兴争
 *日期: 2018年4月14日
 *时间： 下午12:29:43
 **/
public class UserQueryCondition {

	private String username;
	
	@ApiModelProperty("用户年龄起始值")
	private int age;
	@ApiModelProperty("用户年龄终止值")
	private int ageTo;
	
	private String xxx;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAgeTo() {
		return ageTo;
	}

	public void setAgeTo(int ageTo) {
		this.ageTo = ageTo;
	}

	public String getXxx() {
		return xxx;
	}

	public void setXxx(String xxx) {
		this.xxx = xxx;
	}
	
}
