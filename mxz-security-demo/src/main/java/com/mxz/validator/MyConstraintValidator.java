package com.mxz.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mxz.service.HelloService;

/*作者：马兴争
 *日期: 2018年4月10日
 *时间： 上午12:03:54
 **/
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object>{

	@Autowired
	private HelloService helloService;
	
	@Override
	public void initialize(MyConstraint constraint) {
		System.out.println("My validator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		//调用注入 的 服务 
		return false;
	}

}
