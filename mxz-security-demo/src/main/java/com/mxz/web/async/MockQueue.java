package com.mxz.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*作者：马兴争
 *日期: 2018年4月13日
 *时间： 上午1:21:28
 **/
@Component
public class MockQueue {
	
	private String placeOrder;
	
	private String completeOrder;

	public String getPlaceOrder() {
		return placeOrder;
	}
	private Logger logger = LoggerFactory.getLogger(getClass());
	public void setPlaceOrder(String placeOrder) throws Exception {
		new Thread(() -> {
			logger.info("接到下单请求"+placeOrder);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.completeOrder = placeOrder;
			logger.info("下单请求处理完毕"+placeOrder);
		}).start();
	}

	public String getCompleteOrder() {
		return completeOrder;
	}

	public void setCompleteOrder(String completeOrder) {
		this.completeOrder = completeOrder;
	}
}
