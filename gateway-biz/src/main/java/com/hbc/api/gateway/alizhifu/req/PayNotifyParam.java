/**
 * @Author lukangle
 * @2015年12月21日@下午11:51:20
 */
package com.hbc.api.gateway.alizhifu.req;

public class PayNotifyParam {
//	private String notify_time;//通知时间 	Date 	通知的发送时间。格式为yyyy-MM-dd HH:mm:ss。 	不可空 	2013-08-22 14:45:24
//	private String notify_type ;//	通知类型 	String 	通知的类型。 	不可空 	trade_status_sync
//	private String notify_id ;//	通知校验ID 	String 	通知校验ID。 	不可空 	64ce1b6ab92d00ede0ee56ade98fdf2f4c
//	private String sign_type ;//	签名方式 	String 	固定取值为RSA。 	不可空 	RSA
//	private String sign 	;//签名 	String 	请参见签名机制。 	不可空 	lBBK%2F0w5LOajrMrji7DUgEqNjIhQbidR13GovA5r3TgIbNqv231yC1NksLdw%2Ba3JnfHXoXuet6XNNHtn7VE%2BeCoRO1O%2BR1KugLrQEZMtG5jmJI
//	private String out_trade_no;// 	商户网站唯一订单号 	String(64) 	对应商户网站的订单系统中的唯一订单号，非支付宝交易号。需保证在商户网站中的唯一性。是请求时对应的参数，原样返回。 	可空 	082215222612710
//	private String subject ;//	商品名称 	String(128) 	商品的标题/交易标题/订单标题/订单关键字等。它在支付宝的交易明细中排在第一列，对于财务对账尤为重要。是请求时对应的参数，原样通知回来。 	可空 	测试
//	private String payment_type 	支付类型 	String(4) 	支付类型。默认值为：1（商品购买）。 	可空 	1
//	private String trade_no 	支付宝交易号 	String(64) 	该交易在支付宝系统中的交易流水号。最短16位，最长64位。 	不可空 	2013082244524842
//	private String trade_status 	交易状态 	String 	交易状态，取值范围请参见“交易状态”。 	不可空 	TRADE_SUCCESS
//	private String seller_id 	卖家支付宝用户号 	String(30) 	卖家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。 	不可空 	2088501624816263
//	private String seller_email 	卖家支付宝账号 	String(100) 	卖家支付宝账号，可以是email和手机号码。 	不可空 	xxx@alipay.com
//	private String buyer_id 	买家支付宝用户号 	String(30) 	买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。 	不可空 	2088602315385429
//	private String buyer_email 	买家支付宝账号 	String(100) 	买家支付宝账号，可以是Email或手机号码。 	不可空 	dlwdgl@gmail.com
//	private String total_fee 	交易金额 	Number 	该笔订单的总金额。请求时对应的参数，原样通知回来。 	不可空 	1.00
//	private String quantity 	购买数量 	Number 	购买数量，固定取值为1（请求时使用的是total_fee）。 	可空 	1
//	private String price 	商品单价 	Number 	price等于total_fee（请求时使用的是total_fee）。 	可空 	1.00
//	private String body 	商品描述 	String(512) 	该笔订单的备注、描述、明细等。对应请求时的body参数，原样通知回来。 	可空 	测试测试
//	private String gmt_create 	交易创建时间 	Date 	该笔交易创建的时间。格式为yyyy-MM-dd HH:mm:ss。 	可空 	2013-08-22 14:45:23
//	private String gmt_payment 	交易付款时间 	Date 	该笔交易的买家付款时间。格式为yyyy-MM-dd HH:mm:ss。 	可空 	2013-08-22 14:45:24
//	private String is_total_fee_adjust 	是否调整总价 	String(1) 	该交易是否调整过价格。 	可空 	N
//	private String use_coupon 	是否使用红包买家 	String(1) 	是否在交易过程中使用了红包。 	可空 	N
//	private String discount 	折扣 	String 	支付宝系统会把discount的值加到交易金额上，如果有折扣，本参数为负数，单位为元。 	可空 	0.00
//	private String refund_status 	退款状态 	String 	取值范围请参见“退款状态”。 	可空 	REFUND_SUCCESS
//	private String gmt_refund 	退款时间 	Date 	卖家退款的时间，退款通知时会发送。格式为yyyy-MM-dd HH:mm:ss。 	可空 	2008-10-29 19:38:25
}
