package com.mxz.dto;
/*作者：马兴争
 *日期: 2018年4月13日
 *时间： 上午12:38:54
 **/
public class FileInfo {
	
	public FileInfo(String path) {
		this.path = path;
	}

	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
