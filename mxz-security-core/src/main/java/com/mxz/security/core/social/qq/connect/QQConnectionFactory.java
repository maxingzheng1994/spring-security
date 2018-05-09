package com.mxz.security.core.social.qq.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.mxz.security.core.social.qq.api.QQ;

/*作者：马兴争
 *日期: 2018年4月17日
 *时间： 下午11:36:29
 **/
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ>{

	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
