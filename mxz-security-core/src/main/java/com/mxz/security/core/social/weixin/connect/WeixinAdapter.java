package com.mxz.security.core.social.weixin.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.mxz.security.core.social.weixin.api.Weixin;
import com.mxz.security.core.social.weixin.api.WeixinUserInfo;

/*作者：马兴争
 *日期: 2018年4月20日
 *时间： 下午10:51:26
 **/
public class WeixinAdapter implements ApiAdapter<Weixin>{

	private String openId;
	public WeixinAdapter() {
	}
	public WeixinAdapter(String openId) {
		this.openId = openId;
	}


	@Override
	public boolean test(Weixin api) {
		return true;
	}

	@Override
	public void setConnectionValues(Weixin api, ConnectionValues values) {
		WeixinUserInfo userInfo = api.getUserInfo(openId);
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getHeadimgurl());
		values.setProviderUserId(userInfo.getOpenid());
	}

	@Override
	public UserProfile fetchUserProfile(Weixin api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(Weixin api, String message) {
		
	}

}
