package com.hbc.api.trade.ota.aop;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.rsp.RspStatus;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.ota.common.ChannelSet;
import com.hbc.api.trade.ota.common.ReturnResultKey.CTripResultKey;
import com.hbc.api.trade.ota.common.ReturnResultKey.QunarResultKey;
import com.hbc.api.trade.ota.common.ReturnResultKey.StandardResultKey;
import com.hbc.api.trade.ota.enums.CTripErrorCode;
import com.hbc.api.trade.ota.enums.QunarErrorCode;
import com.hbc.api.trade.ota.validator.Channel;

@Component
public class GAPIServiceExceptionHandler implements HandlerExceptionResolver {
	private Logger log = LoggerFactory.getLogger(GAPIServiceExceptionHandler.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,	Exception exp) {
		
		Map<String, Object> errorMap = new HashMap<>(2);
		if(handler instanceof HandlerMethod){
		    HandlerMethod handlerMethod = (HandlerMethod)handler;
		    MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
		    if(methodParameters != null && methodParameters.length > 0) {
		    	MethodParameter methodParameter = methodParameters[0];
		    	Class<?> controllerClass = methodParameter.getContainingClass();
		    	if(controllerClass.isAnnotationPresent(Channel.class)) {
				    String channel = controllerClass.getAnnotation(Channel.class).value();
					if(ChannelSet.QUNAR.equals(channel)) {
						log.error("QUNAR异常处理：", exp);
						if(exp instanceof SQLException) {
							errorMap.put(QunarResultKey.errorCode.name(), QunarErrorCode.ERR_SQL.code());
							errorMap.put(QunarResultKey.errorMsg.name(), QunarErrorCode.ERR_SQL.value());
						} if(exp instanceof TradeException) {
							TradeException ex = (TradeException) exp;
							errorMap.put(QunarResultKey.errorCode.name(), ex.getReturnCode());
							errorMap.put(QunarResultKey.errorMsg.name(), ex.getReturnMessage());
						} else {
							errorMap.put(QunarResultKey.errorCode.name(), QunarErrorCode.ERR_PARAM.code());
							errorMap.put(QunarResultKey.errorMsg.name(), QunarErrorCode.ERR_PARAM.value());
						}
						log.error("去哪儿返回异常：" + JSON.toJSONString(errorMap));
						return new ModelAndView(new MappingJackson2JsonView(), errorMap);
					} else if(ChannelSet.CTRIP.equals(channel)) {
						log.error("CTRIP异常处理：", exp);
						if(exp instanceof TradeException) {
							TradeException ex = (TradeException) exp;
							errorMap.put(CTripResultKey.MsgCode.name(), ex.getReturnCode());
							errorMap.put(CTripResultKey.Message.name(), ex.getReturnMessage());
						} else {
							errorMap.put(CTripResultKey.MsgCode.name(), CTripErrorCode.ERROR.name());
							errorMap.put(CTripResultKey.Message.name(), CTripErrorCode.ERROR.value);
						}
						log.error("携程返回异常：" + JSON.toJSONString(errorMap));
						return new ModelAndView(new MappingJackson2JsonView(), errorMap);
					}
		    	}
		    }
		} 
		
		// do as default
		log.error("HBC标准异常处理：", exp);
		errorMap.put(StandardResultKey.status.name(), Integer.valueOf(RspStatus.ERROR.toString()));
		errorMap.put(StandardResultKey.message.name(), exp.toString());
		return new ModelAndView(new MappingJackson2JsonView(), errorMap);
	}

}
