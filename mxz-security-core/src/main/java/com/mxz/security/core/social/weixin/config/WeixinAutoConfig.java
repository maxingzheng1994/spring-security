package com.mxz.security.core.social.weixin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.web.servlet.View;

import com.mxz.security.core.properties.SecurityProperties;
import com.mxz.security.core.properties.WeixinProperties;
import com.mxz.security.core.social.MxzConnectView;
import com.mxz.security.core.social.weixin.connect.WeixinConnectionFactory;

/*作者：马兴争
 *日期: 2018年4月20日
 *时间： 下午11:09:51
 **/
@Configuration
@ConditionalOnProperty(prefix = "mxz.security.social.weixin", name = "app-id")
public class WeixinAutoConfig extends SocialAutoConfigurerAdapter{

	@Autowired
	private SecurityProperties securityProperties;
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		WeixinProperties weixinConfig = securityProperties.getSocial().getWeixin();
		return new WeixinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
				weixinConfig.getAppSecret());
	}
	/**
	 * 加上这个防止 配置中的bean冲突  @EnableSocial  引入的 SocialConfiguration会遍历所有Adapter 并检查 注入UsersConnectionRepository
	 * refer to 
	 */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		return null;
	}
	//参考connectController
	//   解绑                                         绑定
	@Bean({"connect/weixinConnect", "connect/weixinConnected"})
	@ConditionalOnMissingBean(name = "weixinConnectedView")
	public View weixinConnectedView() {
		return new MxzConnectView();
	}
}
