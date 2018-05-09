package com.mxz.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mxz.web.filter.TimeFilter;
import com.mxz.web.interceptor.TimeInterceptor;

/*作者：马兴争
 *日期: 2018年4月11日
 *时间： 下午11:57:23
 **/
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	private final int order = 1;
	
//	@Bean
//	public FilterRegistrationBean timeFilter() {
//		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//		filterRegistrationBean.setFilter(new TimeFilter());
//		filterRegistrationBean.setOrder(order);
//		
//		List<String> urls = new ArrayList<String>();
//		urls.add("/*");
//		filterRegistrationBean.setUrlPatterns(urls);
//		return filterRegistrationBean;
//	}
	
	@Autowired
	private TimeInterceptor timeInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(timeInterceptor);
	}
}
