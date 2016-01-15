/**
 * @Author lukangle
 * @2015年10月16日@下午3:26:15
 */
package com.hbc.api.gateway.service;

import java.sql.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.gateway.alizhifu.req.AliWithDrawNotifyParm;
import com.hbc.api.gateway.enums.bean.GetWayNo;
import com.hbc.api.gateway.enums.bean.IpnStatus;
import com.hbc.api.gateway.enums.bean.IpnType;
import com.hbc.api.gateway.enums.bean.SendKafkaFlag;
import com.hbc.api.gateway.mapping.gen.GatewayIpnMapper;
import com.hbc.api.gateway.mapping.gen.bean.GatewayIpn;
import com.hbc.api.trade.bdata.common.redis.RedisDao;

@Component
public class AliGetWayService {

	private Logger log = LoggerFactory.getLogger(AliGetWayService.class);

	@Autowired
	GatewayIpnMapper gatewayIpnMapper;

	@Value("${ali.pay.redistopic}")
	private String aliWebPayTopci;

	@Value("${ali.refund.redistopic}")
	private String aliRefundTopci;

	@Value("${ali.withdraw.redistopic}")
	private String aliWithdrawTopic;

	public String getAliWebPayTopci() {
		return aliWebPayTopci;
	}

	public void setAliWebPayTopci(String aliWebPayTopci) {
		this.aliWebPayTopci = aliWebPayTopci;
	}

	@Autowired
	RedisDao redisDao;

	public boolean receiveAliPayNotify(String notifyId, String tradeStatus, String outTradeNo, String jsonParm, String sourceReqStr) {
		GatewayIpn gatewayIpn = new GatewayIpn();
		Date curdate = new Date(System.currentTimeMillis());
		gatewayIpn.setCreateTime(curdate);
		gatewayIpn.setUpdateTime(curdate);
		gatewayIpn.setIpnNo(notifyId + "|" + System.currentTimeMillis());
		gatewayIpn.setIpnType(IpnType.ALI_PAY_NOTISFY.value);
		gatewayIpn.setIpnTypeName(IpnType.ALI_PAY_NOTISFY.name);

		if ("TRADE_SUCCESS".equals(tradeStatus)) {
			gatewayIpn.setIpnStatus(IpnStatus.SUCCESS.value);
		} else {
			gatewayIpn.setIpnStatus(IpnStatus.FAILED.value);
		}
		gatewayIpn.setPayNo(outTradeNo);
		gatewayIpn.setGatewayId(GetWayNo.ALI.value);

		gatewayIpn.setIpnContent(sourceReqStr);
		try {
			redisDao.lpush(aliWebPayTopci, jsonParm);
			log.info("send to redis [" + aliWebPayTopci + "]" + jsonParm);
			gatewayIpn.setSendKafkaFlag(SendKafkaFlag.SEND_KAFKA_SUCCESS.value);
		} catch (Exception e) {
			log.error("", e);
			gatewayIpn.setSendKafkaFlag(SendKafkaFlag.SEND_KAFKA_FAILED.value);
		}

		int updNum = gatewayIpnMapper.insert(gatewayIpn);
		if (updNum > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean receiveAliRefundNotify(String notifyId, String Batch_no, String Success_num, String Result_details, String jsonParm, String sourceReqStr) {
		GatewayIpn gatewayIpn = new GatewayIpn();
		Date curdate = new Date(System.currentTimeMillis());
		gatewayIpn.setCreateTime(curdate);
		gatewayIpn.setUpdateTime(curdate);
		gatewayIpn.setIpnNo(notifyId + "|" + System.currentTimeMillis());
		gatewayIpn.setIpnType(IpnType.ALI_REFUND_NOTISFY.value);
		gatewayIpn.setIpnTypeName(IpnType.ALI_REFUND_NOTISFY.name);

		Integer refundNum = Result_details.split(";").length;//退款笔数
		int successNum = Integer.parseInt(Success_num);
		if (successNum == refundNum) {
			gatewayIpn.setIpnStatus(IpnStatus.SUCCESS.value);
		} else if (0 < successNum && successNum < refundNum) {
			gatewayIpn.setIpnStatus(IpnStatus.PARTSUCCESS.value);
		} else {
			gatewayIpn.setIpnStatus(IpnStatus.FAILED.value);
		}
		gatewayIpn.setIpnBatchNo(Batch_no);
		gatewayIpn.setOrderNo(Result_details.split("\\^")[0]);//默认去第一个 订单号
		gatewayIpn.setGatewayId(GetWayNo.ALI.value);

		gatewayIpn.setIpnContent(sourceReqStr);
		try {
			redisDao.lpush(aliRefundTopci, jsonParm);
			gatewayIpn.setSendKafkaFlag(SendKafkaFlag.SEND_KAFKA_SUCCESS.value);
		} catch (Exception e) {
			log.error("", e);
			gatewayIpn.setSendKafkaFlag(SendKafkaFlag.SEND_KAFKA_FAILED.value);
		}

		int updNum = gatewayIpnMapper.insert(gatewayIpn);
		if (updNum > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean receiveAliWithdrawNotify(AliWithDrawNotifyParm param, String queryString) {
		if (param == null) {
			return Boolean.FALSE;
		}
		String jsonParm = JSON.toJSONString(param);
		log.info("导游提现-支付宝回调处理->{} | {}", jsonParm, queryString);
		boolean success = Boolean.TRUE;
		GatewayIpn gatewayIpn = new GatewayIpn();
		Date curdate = new Date(System.currentTimeMillis());
		gatewayIpn.setCreateTime(curdate);
		gatewayIpn.setUpdateTime(curdate);
		gatewayIpn.setIpnNo(param.getNotify_id() + "|" + System.currentTimeMillis());
		gatewayIpn.setIpnType(IpnType.ALI_WITHDRAW_NOTISFY.value);
		gatewayIpn.setIpnTypeName(IpnType.ALI_WITHDRAW_NOTISFY.name);
		gatewayIpn.setIpnBatchNo(param.getBatch_no());
		gatewayIpn.setGatewayId(GetWayNo.ALI.value);
		gatewayIpn.setIpnContent(queryString);

		try {
			redisDao.lpush(aliWithdrawTopic, jsonParm);
		} catch (Throwable e) {
			log.error("push redis queue encounter:{}", ExceptionUtils.getStackTrace(e));
			gatewayIpn.setSendKafkaFlag(SendKafkaFlag.SEND_KAFKA_FAILED.value);
			success = Boolean.FALSE;
		}
		return gatewayIpnMapper.insert(gatewayIpn) > 0 && success;
	}
}
