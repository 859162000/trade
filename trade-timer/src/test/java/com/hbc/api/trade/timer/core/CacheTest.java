/**
 * @Author lukangle
 * @2015年11月17日@下午1:36:03
 */
package com.hbc.api.trade.timer.core;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.cache.CacheFinal;
import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.order.enums.third.CarClassEnum;
import com.hbc.api.trade.order.enums.third.CarTypeEnum;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.api.trade.third.MisGuideService;
import com.hbc.api.trade.timer.util.LogBackInit;

public class CacheTest {
	public static void main(String[] args) throws IOException {
		String baseConfigDir = "";
		if (args != null && args.length > 0) {
			baseConfigDir = args[0];
		} else {
			baseConfigDir = Class.class.getClass().getResource("/").getPath();
		}

		LogBackInit.initLogBack("D:\\dev\\hbc\\svn\\java\\trade\\trunk\\trade-timer\\target\\classes\\" + File.separator + "/logback.xml");

		Logger log = LoggerFactory.getLogger(RedisQueueStarter.class);
		log.info("baseConfigDir:" + baseConfigDir + File.separator + "conf");
		TConfigLoader.loadProperties(baseConfigDir + File.separator + "conf");
		String springFileXml = "classpath:conf/trade-timer.xml";
		log.info(springFileXml);
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		EhCacheCacheManager ehCacheCacheManager = applicationContext.getBean(EhCacheCacheManager.class);
		
		MisGuideService misGuideService = applicationContext.getBean(MisGuideService.class);
		OrderQueryService orderQueryService = applicationContext.getBean(OrderQueryService.class);
		LControllerService ctest = applicationContext.getBean(LControllerService.class);
		OrderBean orderBean = orderQueryService.getOrderByNo("J11826416911");
		
//		ctest.getGuidByCity(204,10,0);
		CarTypeEnum carTypeEnum = CarTypeEnum.getType(orderBean.getCarTypeId());
		CarClassEnum carClassEnum = CarClassEnum.getType(orderBean.getCarSeatNum());
//		misGuideService.getGuidByCityGuideLevel( carTypeEnum, carClassEnum, 1, 10);
//		ctest.getGuidByCityGuideLevel( carTypeEnum, carClassEnum, 1, 10);
//		misGuideService.getGuidByCityGuideLevels(carTypeEnum, carClassEnum, 1, 10);
//		
//		
//		misGuideService.getGuidByCityGuideLevels(carTypeEnum, carClassEnum, 1, 10);
		Cache xcache = ehCacheCacheManager.getCache(CacheFinal.guideCacheName);
		System.out.println(xcache);
	}
}
