package com.hbc.api.gateway.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hbc.api.gateway.alizhifu.req.AliWithDrawNotifyParm;
import com.hbc.api.gateway.alizhifu.util.AlipayCore;
import com.hbc.api.gateway.alizhifu.util.AlipayNotify;
import com.hbc.api.gateway.alizhifu.util.RequestUtil;
import com.hbc.api.gateway.controller.req.AliNotifyParam;
import com.hbc.api.gateway.controller.req.AliRefundNotifyParam;
import com.hbc.api.gateway.service.AliGetWayService;
import com.hbc.api.gateway.service.AliSignVerService;

/**
 * 送机
 */
@Controller
@RequestMapping("gateway")
public class AliGetWayNotifyController {
	private Logger log = LoggerFactory.getLogger(AliGetWayNotifyController.class);
	@Autowired
	AliGetWayService aliGetWayService;

	@Autowired
	AlipayNotify alipayNotify;

	@Autowired
	AlipayCore alipayCore;

	@Autowired
	AliSignVerService aliSignVerService;

	@RequestMapping(value = "v1.0/alipay/notify/pay", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String alipayNotify(@Valid AliNotifyParam param, BindingResult result, HttpServletRequest request) throws UnsupportedEncodingException {
		StringBuffer sbstr = new StringBuffer();

		Map<String, String> pmap = RequestUtil.getRequestParams(request);
		for (String key : pmap.keySet()) {
			String value = pmap.get(key);
			sbstr.append(key + "=" + value + "&");
		}
		String jparam = JSON.toJSONString(param);
		String signType = pmap.get("sign_type");
		boolean isSign = false;
		if("MD5".equals(signType)){
			isSign = alipayNotify.verify(pmap);
		}else{
			isSign = com.alipay.util.AlipayNotify.verify(pmap);
		}
		if(isSign){
			boolean isSuc = aliGetWayService.receiveAliPayNotify(param.getNotify_id(), param.getTrade_status(), param.getTrade_no(), jparam, sbstr.toString());
			if (isSuc) {
				return "SUCCESS";
			} else {
				return "FAILED";
			}
		}else{
			return "FAILED";
		}
//		aliSignVerService.versigReq(request);

	}

	@RequestMapping(value = "v1.0/alipay/notify/refund", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String aliRefundNotify(@Valid AliRefundNotifyParam param, BindingResult result, HttpServletRequest request) throws UnsupportedEncodingException {
		Set<String> keyset = request.getParameterMap().keySet();
		StringBuffer sbstr = new StringBuffer();
		Map<String, String> pmap = new HashMap<String, String>();
		for (String key : keyset) {
			String[] values = request.getParameterValues(key);
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				sbstr.append(key + "=" + value + "&");
				pmap.put(key, value);
			}
		}
		String signType = pmap.get("sign_type");
		boolean isSign = false;
		if("MD5".equals(signType)){
			isSign = alipayNotify.verify(pmap);
		}else{
			isSign = com.alipay.util.AlipayNotify.verify(pmap);
		}
		if(isSign){
			String jparam = JSON.toJSONString(param);
			log.info("aliRefundNotify notify is [" + sbstr.toString() + "]");

			boolean isSuc = aliGetWayService.receiveAliRefundNotify(param.getNotify_id(), param.getBatch_no(), param.getSuccess_num(), param.getResult_details(), jparam, sbstr.toString());

			if (isSuc) {
				return "SUCCESS";
			} else {
				return "FAILED";
			}
		}
		return "FAILED";

	}

	@RequestMapping(value = "v1.0/alipay/notify/withdraw", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String aliWithdrawNotify(@Valid AliWithDrawNotifyParm param, HttpServletRequest request) {
		Set<String> keyset = request.getParameterMap().keySet();
		StringBuffer sbstr = new StringBuffer();
		Map<String, String> pmap = new HashMap<String, String>();
		for (String key : keyset) {
			String[] values = request.getParameterValues(key);
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				sbstr.append(key + "=" + value + "&");
				pmap.put(key, value);
			}
		}
		String signType = pmap.get("sign_type");
		boolean isSign = false;
		if("MD5".equals(signType)){
			isSign = alipayNotify.verify(pmap);
		}else{
			isSign = com.alipay.util.AlipayNotify.verify(pmap);
		}
		if(isSign){
			boolean success = aliGetWayService.receiveAliWithdrawNotify(param, request.getQueryString());
			return success ? "success" : "failed";
		}else{
			return "failed";
		}
	}
}
