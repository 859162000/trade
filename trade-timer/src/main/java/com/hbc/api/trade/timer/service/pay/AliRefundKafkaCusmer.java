package com.hbc.api.trade.timer.service.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.pay.enums.RefundStatus;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefund;
import com.hbc.api.trade.pay.service.NotifyRefundService;
import com.hbc.api.trade.pay.service.PaymentService;
import com.hbc.api.trade.timer.kafka.KafkaMessageReceiver;
@Component
public class AliRefundKafkaCusmer  extends KafkaMessageReceiver{
	private Logger log = LoggerFactory.getLogger(AliPayKafkaCusmer.class);
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	NotifyRefundService notifyRefundService;
	@Autowired
	PaymentService paymentService;
	@Override
	public void handleMessage(String msg) {
		try{
			log.info("receive kafka msg refund : "+msg);
			AliRefundNotifyParam refundNotifyParam = JSON.parseObject(msg, AliRefundNotifyParam.class);
			String batchNo = refundNotifyParam.getBatch_no();
			String redetail = refundNotifyParam.getResult_details();
			int succesNum = Integer.parseInt(refundNotifyParam.getSuccess_num());
			
			String[] reundStrs = redetail.split(";");
			for(String refundStr : reundStrs){
				String[] refundInfos = refundStr.split("\\^");
				if(refundInfos.length==3){
					String tradeNo = refundInfos[0];
					double refundMoney = Double.parseDouble(refundInfos[1]);
					String refundResult = refundInfos[2];
					
					TradePayment tradePayment = paymentService.queryByTradeNo(tradeNo);
					TradeRefund tradeRefund = notifyRefundService.queryRefundByBathNOOrderNo(tradePayment.getPayNo(), tradePayment.getOrderNo());
					if("SUCCESS".equalsIgnoreCase(refundResult)){
						if(!tradeRefund.getRefundMoney().equals(refundMoney)){
							tradeRefund.setRefundError("退款通知 金额 ["+refundMoney+"] 和 退款金额不等 ");
						}
						RefundStatus refundStatus = RefundStatus.getType(tradeRefund.getRefundStatus());
						if(refundStatus.equals(RefundStatus.UNKNOWN)){
							tradeRefund.setRefundStatus(RefundStatus.SUCCESS.value);
						}else{
							log.warn(tradeRefund.getRefundNo()+" 退款状态可能异常"+refundStatus+" ");
							tradeRefund.setRefundStatus(RefundStatus.SUCCESS.value);
						}
						tradeRefund.setRefundResponse(msg);
						notifyRefundService.refundSuccess(tradeRefund,refundMoney);
					}else{
						
						log.info(tradePayment.getOrderNo()+"支付宝 退款 失败 " +msg);
					}
				}else{
					log.error("支付宝 退款 格式错误 " +msg);
				}
				
			}
		}catch(Exception e){
			log.error("", e);
		}
	}
}
class AliRefundNotifyParam {
	private String notify_time ;//	通知时间 	Date 	通知发送的时间。格式为：yyyy-MM-dd HH:mm:ss。 	不可空 	2009-08-12 11:08:32
	private String notify_type ;//	通知类型 	String 	通知的类型。 	不可空 	batch_refund_notify
	private String notify_id 	;//通知校验ID 	String 	通知校验ID。 	不可空 	70fec0c2730b27528665af4517c27b95
	private String sign_type ;//	签名方式 	String 	DSA、RSA、MD5三个值可选，必须大写。 	不可空 	MD5
	private String sign 	;//签名 	String 	请参见安全接入与验证。 	不可空 	b7baf9af3c91b37bef4261849aa76281
	private String batch_no ;//	退款批次号 	String 	原请求退款批次号。 	不可空 	20060702001
	private String success_num 	;//退款成功总数 	String 	退交易成功的笔数。0<= success_num<= 总退款笔数。 	不可空 	2
	private String result_details 	;//退款结果明细 	String 	退款结果明细：退手续费结果返回格式：交易号^退款金额^处理结果\$退费账号^退费账户ID^退费金额^处理结果；不退手续费结果返回格式：交易号^退款金额^处理结果。若退款申请提交成功，处理结果会返回“SUCCESS”。若提交失败，退款的处理结果中会有报错码，参见“9.1 即时到账批量退款业务错误码”。 	可空 	2010031906272929^80^SUCCESS\$jax_chuanhang@alipay.com^2088101003147483^0.01^SUCCESS
	
	public String getNotify_time() {
		return notify_time;
	}
	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}
	public String getNotify_type() {
		return notify_type;
	}
	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}
	public String getNotify_id() {
		return notify_id;
	}
	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getSuccess_num() {
		return success_num;
	}
	public void setSuccess_num(String success_num) {
		this.success_num = success_num;
	}
	public String getResult_details() {
		return result_details;
	}
	public void setResult_details(String result_details) {
		this.result_details = result_details;
	}
}
