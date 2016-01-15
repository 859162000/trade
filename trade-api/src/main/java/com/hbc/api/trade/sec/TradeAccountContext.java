package com.hbc.api.trade.sec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hbc.passport.sdk.api.AccountContext;
@Component
public class TradeAccountContext {
	@Value("${trade.env}")
	private String env;
	public  String getUserId() {
		if(env!=null && (env.equals("local") || env.equals("dev"))){
			return "C121729548757";
		}
		return AccountContext.getUserId();
	}
	
	public String getFundId() {
		return AccountContext.getFundId();
	}

	/**
	 * @return the loginName
	 */
	public  String getLoginName() {
		return AccountContext.getLoginName();
	}

	/**
	 * @return the userToken
	 */
	public  String getUserToken() {
		return AccountContext.getUserToken();
	}


	/**
	 * @return the accessKey
	 */
	public  String getAccessKey() {
		return AccountContext.getAccessKey();
	}

}
