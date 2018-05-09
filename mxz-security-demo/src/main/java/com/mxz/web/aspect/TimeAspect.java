package com.mxz.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/*作者：马兴争
 *日期: 2018年4月13日
 *时间： 上午12:17:29
 **/
@Aspect
public class TimeAspect {
	
	@Around("execution(* com.mxz.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("time aspect start");
		Object[] args = joinPoint.getArgs();
		for (Object object : args) {
			System.out.println(args);
		}
		System.out.println();
		Object obj = joinPoint.proceed();
		System.out.println("time aspect end");
		return null;
	}
	
}
