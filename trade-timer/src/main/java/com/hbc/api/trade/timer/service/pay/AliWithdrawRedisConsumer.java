/*
 * COPYRIGHT (C) 2015-2016,LUOSHUAI. ALL RIGHTS RESERVED.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files
 * (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, 
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions: 
 *
 *   a).The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software!
 *   b).Any individual or entity would be granted by LUOSHUAI before using this Software!
 *  
 * Please contact through email luoshuai@live.com if you need additional informations OR have any questions.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * Author: Luoshuai 
 * Revision: 1.0
 * 
 *  
 */
package com.hbc.api.trade.timer.service.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.fund.biz.service.AliFundWithdrawService;
import com.hbc.api.gateway.alizhifu.req.AliWithDrawNotifyParm;
import com.hbc.api.trade.bdata.common.redis.RedisDao;
import com.hbc.api.trade.bdata.common.util.TConfigLoader;

/**
 * @author LuoShuai
 *
 */
@Component
public class AliWithdrawRedisConsumer {

	private Logger log = LoggerFactory.getLogger(AliWithdrawRedisConsumer.class);

	@Autowired
	private RedisDao redisDao;

	@Autowired
	private AliFundWithdrawService aliFundWithdrawService;

	public void start() {
		String payReisKey = TConfigLoader.getProperty("ali.withdraw.redistopic", "withdrawtopic");
		String message = redisDao.brpop(payReisKey);
		log.info("start ali-withdraw redis cusomer .......");
		while (true) {
			try {
				AliWithDrawNotifyParm payParam = JSON.parseObject(message, AliWithDrawNotifyParm.class);
				log.info("receive redis message : " + payParam);
				aliFundWithdrawService.handleAliWithdrawCallback(payParam);
			} catch (Exception e) {
				log.error("", e);
				redisDao.rpush(payReisKey + "error", message);
			}
			message = redisDao.brpop(payReisKey);
		}
	}

}
