package com.mxz.security.core.social;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.fasterxml.jackson.databind.ObjectMapper;

/*作者：马兴争
 *日期: 2018年4月21日
 *时间： 上午1:26:51
 **/
//ConnectController的方法跳转会找ci视图
@Component("connect/status")
public class MxzConnectionStatusView extends AbstractView{

	@Autowired
	private ObjectMapper objectMapper;
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, List<Connection<?>>> connections = (Map<String, List<Connection<?>>>) model.get("connectionMap");
		Map<String, Boolean>result = new HashMap<String, Boolean>();
		connections.forEach((key, value) -> {
			result.put(key, CollectionUtils.isNotEmpty(value));
		});
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(result));
	}

}
