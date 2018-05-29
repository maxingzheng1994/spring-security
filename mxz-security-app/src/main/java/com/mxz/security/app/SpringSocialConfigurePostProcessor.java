package com.mxz.security.app;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.mxz.security.core.social.MxzSpringSocialConfigurer;

/*作者：马兴争
 *日期: 2018年5月21日
 *时间： 下午10:05:19
 **/
//bean初始化之前和之后做的事情
@Component
public class SpringSocialConfigurePostProcessor implements BeanPostProcessor {

	@Override   
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(StringUtils.equals( beanName, "mxzSocialSecurityConfig")) {
			MxzSpringSocialConfigurer configurer = (MxzSpringSocialConfigurer) bean;
			configurer.signupUrl("/social/signUp");
			return configurer;
		}
		return bean;
	}

}
