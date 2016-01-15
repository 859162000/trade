/**
 * @brief 第三方签名service
 * @author Ivan Huang<ivanhuang@huangbaoche.com>
 * @since 2015-11-25
 */
package com.hbc.api.trade.ota.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.gateway.alizhifu.util.AlipayCore;
import com.hbc.api.trade.bdata.common.sign.MD5Adaptor;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * @author update by Jongly Ran
 */
@Component
public class SignService {
	private final static Logger log = LoggerFactory.getLogger(SignService.class);
	
	public static enum POSITION{START, BOTH, END}
	public static enum CHARACTER{UPPERCASE, LOWERCASE}
	
    @Autowired private AlipayCore alipayCore;
    
    /**
     * URL增加securityKey，然后JDK MD5加密
     * @param hmap
     * @param securityKey
     * @param position securityKey的位置 （同源静态变量）
     * @param character 指定返回值字符全大小写情况 （同源静态变量）
     * @return 返回符合规则的签名字符串
     */
    public String createSign(Map<String, String> hmap, String securityKey, MD5Adaptor md5, POSITION position, CHARACTER character) {
    	hmap = alipayCore.paraFilter(hmap);
    	String sortReqStr = alipayCore.createLinkString(hmap);
    	return exec(sortReqStr, securityKey, md5, position, character);
    }

	private String exec(String sortReqStr, String securityKey, MD5Adaptor md5, POSITION position, CHARACTER character) {
		String targetURL = null;
    	switch(position) {
    	case START:
    		targetURL = (securityKey + sortReqStr);
    		break;
    	case BOTH:
    		targetURL = (securityKey + sortReqStr + securityKey);
    		break;
    	case END:
    		targetURL = (sortReqStr + securityKey);
    		break;
    	}
    	log.info("加密前：" + targetURL);
    	targetURL = md5.toMD5(targetURL);
    	String result = CHARACTER.UPPERCASE.equals(character) ? targetURL.toUpperCase() : targetURL.toLowerCase();
    	log.info("加密后：" + result);
		return result;
	}

   /**
    * 验证签名是否正确
    * 
    * @param hmap
    * @param sign
    * @param securityKey
    * @param position securityKey的位置 （同源静态变量）
    * @param character 指定返回值字符全大小写情况 （同源静态变量）
    * @return
    */
    public boolean validateSign(Map<String, String> hmap, String sign, String securityKey, MD5Adaptor md5, POSITION position, CHARACTER character) {
    	if(hmap == null) {
    		throw new TradeException(TradeReturnCodeEnum.TRADE_ORDER_SIGN_ERROR) ;
    	}
    	String mySign = this.createSign(hmap, securityKey, md5, position, character);
    	if (!mySign.equals(sign)) {
    		log.error("根据URL参数生产的sign=" + mySign + "，URL中参数sign=" + sign);
    		throw new TradeException(TradeReturnCodeEnum.TRADE_ORDER_SIGN_ERROR) ;
    	}
    	return true ;
    }
    
    /**
     * 
     * @param prefix
     * @param sign
     * @param securityKey
     * @param md5
     * @param position securityKey的位置 （同源静态变量）
     * @param character 指定返回值字符全大小写情况 （同源静态变量）
     * @return
     */
    public boolean validateSign(String prefix, String sign, String securityKey, MD5Adaptor md5, POSITION position, CHARACTER character) {
    	if(prefix == null) {
    		throw new TradeException(TradeReturnCodeEnum.TRADE_ORDER_SIGN_ERROR) ;
    	}
    	String mySign = this.exec(prefix, securityKey, md5, position, character);
    	if (!mySign.equals(sign)) {
    		log.error("根据URL参数生产的sign=" + mySign + "，URL中参数sign=" + sign);
    		throw new TradeException(TradeReturnCodeEnum.TRADE_ORDER_SIGN_ERROR) ;
    	}
    	return true ;
    }
}
