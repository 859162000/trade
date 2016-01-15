package com.hbc.api.gateway.controller.aop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.hbc.api.trade.bdata.common.exception.BaseException;
import com.hbc.api.trade.bdata.common.rsp.RspStatus;

@Component
public class GAPIServiceExceptionHandler implements HandlerExceptionResolver {
	private static final String STATUS = "status";
	private static final String ERROR_MSG = "message";
	private Logger log = LoggerFactory.getLogger(GAPIServiceExceptionHandler.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exp) {
		Map<String, Object> errorMap = new HashMap<>(2);
		if (exp instanceof BaseException) {
			BaseException serviceExp = BaseException.class.cast(exp);
			errorMap.put(STATUS, serviceExp.getReturnCode());
			errorMap.put(ERROR_MSG, serviceExp.getMessage());
			log.error("", serviceExp);
			return new ModelAndView(new MappingJackson2JsonView(), errorMap);
		}
		log.error("", exp);
		errorMap.put(STATUS, RspStatus.ERROR.toString());
		errorMap.put(ERROR_MSG, exp.toString());
		
		return new ModelAndView(new MappingJackson2JsonView(), errorMap);
	}

}
