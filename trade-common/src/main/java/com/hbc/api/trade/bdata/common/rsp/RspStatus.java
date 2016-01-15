package com.hbc.api.trade.bdata.common.rsp;

public enum RspStatus {
	Continue(100),//Continue in the case of a request for which a body needs to be sent; for example), a POST request
	Processing(102),//Processing This code indicates that the server has received and is processing the request), but no response is available yet.
	
	SUCCESS(200),

	
	BadRequest(400),//The server cannot or will not process the request due to something that is perceived to be a client error
	Unauthorized(401),// Unauthorized uthentication is required and has failed or has not yet been provided
	Forbidden(403),
	NotAcceptable(406),//
	RequestTimeout(408),//SOA架构中 服务超时 必须在message中标识 超时URL
	AuthenticationTimeout(419),// Authentication Timeout
	Locked (423),//The resource that is being accessed is locked
	TooManyRequests(429),//The user has sent too many requests in a given amount of time
	Tokenrequired (499),
	TokenExpired(498),//过期token
	UpgradeRequired(426),//强制升级

	ERROR(500),//Internal Server Error
	UnknownError(520);
	
	public int value;
	RspStatus(int value){
		this.value = value;
	}
	
	
	@Override
	public String toString() {
		return this.value+"";
	}
	
	
}
