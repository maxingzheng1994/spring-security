package com.mxz.web.async;

import java.util.Random;
import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/*作者：马兴争
 *日期: 2018年4月13日
 *时间： 上午1:03:46
 **/
@RestController
public class AsyncController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/order")
	public String order() throws Exception {
		logger.info("主线程开始");
		Thread.sleep(1000);
		return "success";
	}
	
	@RequestMapping("/order2")
	public Callable<String> order2() throws Exception {
		logger.info("主线程开始");
		Callable<String> result = new Callable<String>() {
			@Override
			public String call() throws Exception {
				logger.info("副线程开启 ");
				Thread.sleep(1000);
				logger.info("副线程关闭 ");
				return "success";
			}
		};
		logger.info("主线程结束");
		return result;   
		/*主线程开始
		主线程结束
		副线程开始
		副线程结束*/
	}
	
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder holder;
	
	@RequestMapping("/orderssss1")
	public DeferredResult<String> orderss1() throws Exception {
		logger.info("主线程开始");
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		DeferredResult<String> result = new DeferredResult<>();
		holder.getMap().put(orderNumber, result);
		logger.info("主线程结束");
		return result;   
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
