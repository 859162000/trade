/**
 * @Author lukangle
 * @2015年11月29日@下午4:49:10
 */
package com.hbc.api.gateway.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.gateway.alizhifu.config.AlipayConfig;
import com.hbc.api.gateway.alizhifu.util.AlipayCallBackValidator;
import com.hbc.api.gateway.alizhifu.util.AlipayCore;
import com.hbc.api.gateway.alizhifu.util.AlipayNotify;
import com.hbc.api.gateway.enums.GatewayReturnCodeEnum;
import com.hbc.api.gateway.exception.GatewayException;

@Component
public class AliSignVerService {
	private final static Logger log = LoggerFactory.getLogger(AliSignVerService.class);
	@Autowired
	AlipayConfig alipayConfig;

	@Autowired
	AlipayCore alipayCore;
	@Autowired
	AlipayNotify alipayNotify;

	@Autowired
	private AlipayCallBackValidator validator;

	public void versigReq(HttpServletRequest request) throws UnsupportedEncodingException {
		Set<String>  keyset = request.getParameterMap().keySet();
		Map<String,String> pmap = new HashMap<String,String>();
		for (String key : keyset) {  
            String[] values = request.getParameterValues(key);  
//            for (int i = 0; i < values.length; i++) {  
//                String value = values[i];  
//            }  
            if(values!=null && values.length>0){
            	  pmap.put(key, values[0]);
            }
        } 
		alipayNotify.verify(pmap);
		//1. 验证签名
//		if (!validator.signValidate(request)) {
//			throw new GatewayException(GatewayReturnCodeEnum.SIGN_EXP);
//		}
//
//		//2. 验证是否是支付宝发来的通知
//		if (!validator.identifyValidate(request)) {
//
//		}
//
//		//3. 数据有效验证
//		if (!validator.dataValidate(request)) {
//
//		}

	}
}
