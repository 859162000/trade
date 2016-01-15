package com.hbc.api.trade.ota.aop;

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
import com.hbc.api.trade.ota.common.ChannelSet;
import com.hbc.api.trade.ota.enums.QunarErrorCode;
import com.hbc.api.trade.ota.resp.ctrip.CTripResult;
import com.hbc.api.trade.ota.resp.ctrip.CTripResult.MsgCodeEnum;
import com.hbc.api.trade.ota.resp.qunar.QunarResult;
import com.hbc.api.trade.ota.validator.Channel;

/**
 * @author Jongly Ran
 */
@Component
@Aspect
public class ParameterValidator {
	private Logger log = LoggerFactory.getLogger(ParameterValidator.class);
	
    @Around(value="@annotation(org.springframework.web.bind.annotation.RequestMapping)",argNames="jp")
	public Object basicParameterCheck(ProceedingJoinPoint jp) throws Throwable {
    	Object[] args = jp.getArgs();
    	if(args != null && args.length > 0) {
			for (Object arg : args) {
				// validate other inputs
				if (arg instanceof BindingResult) {
					BindingResult result = BindingResult.class.cast(arg);
					if (result.hasErrors()) {
						return validateInputs(jp, result);
					}
				}
			}
    	}
		return jp.proceed();
	}

	/**
	 * @param jp
	 * @param result
	 * @return
	 */
	private Object validateInputs(ProceedingJoinPoint jp, BindingResult result) {
		StringBuilder errors = new StringBuilder();
		Map<String, String> errMsgMap = new HashMap<>(result.getErrorCount());
		for (FieldError error : result.getFieldErrors()) {
			errMsgMap.put(error.getField(), error.getDefaultMessage());
			errors.append(error.getField()).append(":").append(error.getDefaultMessage()).append(",");
		}
		
		Class<? extends Object> clazz = jp.getTarget().getClass();
		if (clazz.isAnnotationPresent(Channel.class)) {
			String channel = clazz.getAnnotation(Channel.class).value();
			if(ChannelSet.CTRIP.equals(channel)) {
				return new CTripResult(MsgCodeEnum.ERROR, errors.toString());
			} else if(ChannelSet.QUNAR.equals(channel)) {
				return new QunarResult(QunarErrorCode.ERR_PARAM.code(), errors.toString());
			} else {
				// do as default.
			}
		}
		
		ReturnResult rs = new ReturnResult();
		rs.setStatus(CommonReturnCodeEnum.PARAM_ERROR.getCode());
		rs.setMessage(CommonReturnCodeEnum.PARAM_ERROR.getMessage());
		rs.setData(errMsgMap);
		log.error(errMsgMap.toString());
		return rs;
	}
}
