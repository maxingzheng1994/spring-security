package com.mxz.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/*作者：马兴争
 *日期: 2018年4月20日
 *时间： 上午1:53:50
 **/
@Component
public class DemoConnectionSignUp implements ConnectionSignUp{

	@Override
	public String execute(Connection<?> connection) {
		// 根据 社交用户信息创建用户并返回唯一标识
		return connection.getDisplayName();
	}

}
