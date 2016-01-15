/**
 * @Author lukangle
 * @2015年10月8日@下午3:03:49
 */
package com.hbc.api.trade.ota.aop;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TradeReqLogWriter {
	private Logger log = LoggerFactory.getLogger(TradeReqLogWriter.class);
	
    @Around(value="@annotation(org.springframework.web.bind.annotation.RequestMapping)",argNames="jp")
	public Object logAopWrite(ProceedingJoinPoint jp) throws Throwable {
		long curtime = System.currentTimeMillis();
    	Object[] args = jp.getArgs();
		HttpServletRequest request = null;
		for(Object obj : args){
			if(obj instanceof HttpServletRequest){
				request = (HttpServletRequest)obj;
			}
		}
		
		if(request != null) {
			StringBuffer sbstr = new StringBuffer();
			Set<String>  keyset = request.getParameterMap().keySet();
			for (String key : keyset) {  
	            String[] values = request.getParameterValues(key);  
	            for (int i = 0; i < values.length; i++) {  
	                String value = values[i];  
	                sbstr.append(key + "=" + value + "&");
	            }  
	        }  
			log.info("@@@@@@@@@@@@@@@"+request.getRequestURL().toString()+"?"+sbstr.toString()+"\t");
			Object object = jp.proceed();// 执行该方法
			request.getHeader("");
			log.info(request.getRequestURL().toString()+"?"+sbstr.toString()+"\t"+object+"\tcost ["+(System.currentTimeMillis()-curtime)+"]");
			return object;
		}
		return jp.proceed();
    }
}
