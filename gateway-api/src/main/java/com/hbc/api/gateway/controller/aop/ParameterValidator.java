package com.hbc.api.gateway.controller.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.hbc.api.trade.bdata.common.exception.enums.CommonReturnCodeEnum;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;

@Component
@Aspect
public class ParameterValidator {
	private Logger log = LoggerFactory.getLogger(ParameterValidator.class);
    @Around(value="@annotation(org.springframework.web.bind.annotation.RequestMapping)",argNames="jp")
	public Object basicParameterCheck(ProceedingJoinPoint jp) throws Throwable {
    	Object[] args = jp.getArgs();
		for (Object arg : args) {
			if (arg instanceof BindingResult) {
				ReturnResult rs = new ReturnResult();
				rs.setStatus(CommonReturnCodeEnum.PARAM_ERROR.getCode());
				rs.setMessage(CommonReturnCodeEnum.PARAM_ERROR.getMessage());
				BindingResult result = BindingResult.class.cast(arg);
				if (result.hasErrors()) {
					Map<String, String> errMsgMap = new HashMap<>(result.getErrorCount());
					for (FieldError error : result.getFieldErrors()) {
						errMsgMap.put(error.getField(), error.getDefaultMessage());
					}
					rs.setData(errMsgMap);
					log.error(errMsgMap.toString());
					return rs.toString();
				}
				break;
			}
		}
		
		return jp.proceed();
	}
}
