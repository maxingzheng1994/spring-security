package com.mxz.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/*作者：马兴争
 *日期: 2018年4月13日
 *时间： 上午1:35:31
 **/
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder holder;

	private Logger logger = LoggerFactory.getLogger(getClass()); 
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		new Thread(() -> {
			while (true) {
				if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
					String orderNumber = mockQueue.getCompleteOrder();
					logger.info("返回订单处理结果"+orderNumber);
					holder.getMap().get(orderNumber).setResult("plcae ordersuccess ");
					mockQueue.setCompleteOrder(null);
				} else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}
	
}
