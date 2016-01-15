package com.hbc.api.trade.ota.ctrip.req;

import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.ota.req.OrderDetailParam;

/**
 * CTrip API.
 * @author Jongly Ran
 */
public class CTripOrderDetailParam {


	public String CtripPurchaseOrderID;	// 	携程订单号
	
	public String sign;		// 签名

    public Long timestamp ;	// 时间戳
    
    public String 	noncestr ;	// 随机字符串
    
    public OrderDetailParam toStandardOrderDetailBean() {
    	OrderDetailParam param = new OrderDetailParam();
    	param.setServicePartner(AgentChannelEnum.CTRIP_CHANNEL.value);
    	param.setSign(sign);
    	param.setThirdTradeNo(CtripPurchaseOrderID);
    	return param;
    }
}
