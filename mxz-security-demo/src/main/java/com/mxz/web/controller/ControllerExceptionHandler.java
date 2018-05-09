package com.mxz.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mxz.exception.UserNotExistException;

/*作者：马兴争
 *日期: 2018年4月10日
 *时间： 上午12:29:23
 **/
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleUserNotExistException(UserNotExistException exception) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", exception.getId());
		result.put("message", exception.getMessage());
		return result;
	}
}
