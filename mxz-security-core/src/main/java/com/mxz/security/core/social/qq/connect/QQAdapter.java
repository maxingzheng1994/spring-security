package com.mxz.security.core.social.qq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.mxz.security.core.social.qq.api.QQ;
import com.mxz.security.core.social.qq.api.QQUserInfo;

/*作者：马兴争
 *日期: 2018年4月17日
 *时间： 下午11:27:26
 **/
public class QQAdapter implements ApiAdapter<QQ>{

	@Override
	public boolean test(QQ api) {
		//测试api是否可用
		return true;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();
		
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getFigureurl_qq_1());
		//个人主页
		values.setProfileUrl(null);
		values.setProviderUserId(userInfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		//微博可以更新
		// do nothing
	}

}
