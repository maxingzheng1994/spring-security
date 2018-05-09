package com.mxz.exception;
/*作者：马兴争
 *日期: 2018年4月10日
 *时间： 上午12:26:48
 **/
public class UserNotExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String id;
	public UserNotExistException(String id) {
		super("user not exist");
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
