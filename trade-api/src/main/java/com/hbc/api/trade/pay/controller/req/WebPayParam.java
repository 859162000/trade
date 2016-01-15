/**
 * @Author lukangle
 * @2015年10月15日@下午8:14:10
 */
package com.hbc.api.trade.pay.controller.req;

public class WebPayParam {
	private String service;// 	接口名称 	String 	接口名称。 	不可空 	create_direct_pay_by_user
	private String partner 	;//合作者身份ID 	String(16) 	签约的支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。 	不可空 	2088101011913539
	private String _input_charset 	;//参数编码字符集 	String 	商户网站使用的编码格式，如utf-8、gbk、gb2312等。 	不可空 	gbk
	private String sign_type 	;//签名方式 	String 	DSA、RSA、MD5三个值可选，必须大写。 	不可空 	MD5
	private String sign 	;//签名 	String 	请参见安全接入与验证 	不可空 	7d314d22efba4f336fb187697793b9d2
	private String notify_url 	;//服务器异步通知页面路径 	String(190) 	支付宝服务器主动通知商户网站里指定的页面http路径。 	可空 	http://api.test.alipay.net/atinterface/receive_return.htm
	private String return_url 	;//页面跳转同步通知页面路径 	String(200) 	支付宝处理完请求后，当前页面自动跳转到商户网站里指定页面的http路径。 	可空 	http://api.test.alipay.net/atinterface/receive_return.htm
	private String error_notify_url 	;//请求出错时的通知页面路径 	String(200) 	当商户通过该接口发起请求时，如果出现提示报错，支付宝会根据item_orders_info出错时的通知错误码”和“请求出错时的通知错误码”通过异步的方式发送通知给商户。该功能需要联系支付宝开通。 	可空 	http://api.test.alipay.net/atinterface/receive_return.htm
	private String out_trade_no 	;//商户网站唯一订单号 	String(64) 	支付宝合作商户网站唯一订单号。 	不可空 	6843192280647118
	private String subject 	;//商品名称 	String(256) 	商品的标题/交易标题/订单标题/订单关键字等。该参数最长为128个汉字。 	不可空 	贝尔金护腕式
	private String payment_type ;//	支付类型 	String(4) 	取值范围请参见附录“收款类型”。默认值为：1（商品购买）。 注意：支付类型为“47”时，公共业务扩展参数（extend_param）中必须包含凭证号（evoucheprod_evouche_id）参数名和参数值。 	不可空 	1
	private double total_fee 	;//交易金额 	Number 	该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位。 	不可空 	100
	private String seller_id 	;//卖家支付宝用户号 	String(16) 	卖家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。 	不可空 	2088002007018966
	private String buyer_id 	;//买家支付宝用户号 	String(16) 	买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。 	可空 	2088002007018955
	private String seller_email ;//	卖家支付宝账号 	String(100) 	卖家支付宝账号，格式为邮箱或手机号。 	可空 	alipay-test01@alipay.com
	private String buyer_email 	;//买家支付宝账号 	String(100) 	买家支付宝账号，格式为邮箱或手机号。 	可空 	tstable01@alipay.com
	private String seller_account_name ;//	卖家别名支付宝账号 	String(100) 	卖家别名支付宝账号。卖家信息优先级：seller_id>seller_account_name>seller_email。 	可空 	tstable02@alipay.com
	private String buyer_account_name ;//	买家别名支付宝账号 	String(100) 	买家别名支付宝账号。买家信息优先级：buyer_id>buyer_account_name>buyer_email。 	可空 	tstable03@alipay.com
	private double price 	;//商品单价 	Number 	单位为：RMB Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位。此参数为单价。规则：price、quantity能代替total_fee。即存在total_fee，就不能存在price和quantity；存在price、quantity，就不能存在total_fee。 	可空 	10.00
	private double quantity ;//	购买数量 	Number 	price、quantity能代替total_fee。即存在total_fee，就不能存在price和quantity；存在price、quantity，就不能存在total_fee。 	可空 	1
	private String body 	;//商品描述 	String(1000) 	对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。 	可空 	美国专业护腕鼠标垫，舒缓式凝胶软垫模拟手腕的自然曲线和运动，创造和缓的GelFlex舒适地带！
	private String show_url ;//	商品展示网址 	String(400) 	收银台页面上，商品展示的超链接。 	可空 	http://www.360buy.com/product/113714.html
	private String paymethod ;//	默认支付方式 	String 	取值范围：creditPay（信用支付）；directPay（余额支付）。如果不设置，默认识别为余额支付。说明：必须注意区分大小写。 	可空 	directPay
	private String enable_paymethod;// 	支付渠道 	String 	用于控制收银台支付渠道显示，该值的取值范围请参见附录“支付渠道”。可支持多种支付渠道显示，以“^”分隔。 	可空 	directPay^bankPay^cartoon^cash
	private String need_ctu_check;// 	网银支付时是否做CTU校验 	String 	商户在配置了支持CTU（支付宝风险稽查系统）校验权限的前提下，可以选择本次交易是否需要经过CTU校验。Y：做CTU校验；N：不做CTU校验。 	可空 	Y
	private String royalty_type ;//	提成类型 	String(2) 	目前只支持一种类型：10（卖家给第三方提成）。当传递了参数royalty_parameters时，提成类型参数不能为空。 	可空 	10
	private String royalty_parameters;//	分润账号集 	String(1000) 	参见“royalty_parameters参数说明”。 	可空 	参见“ royalty_parameters参数说明”
	private String anti_phishing_key;// 	防钓鱼时间戳 	String 	通过时间戳查询接口获取的加密支付宝系统时间戳。如果已申请开通防钓鱼时间戳验证，则此字段必填。 	可空 	587FE3D2858E6B01E30104656E7805E2
	private String exter_invoke_ip;// 	客户端IP 	String(15) 	用户在创建交易时，该用户当前所使用机器的IP。如果商户申请后台开通防钓鱼IP地址检查选项，此字段必填，校验用。 	可空 	128.214.222.111
	private String extra_common_param ;//	公用回传参数 	String(100) 	如果用户请求时传递了该参数，则返回给商户时会回传该参数。 	可空 	你好，这是测试商户的广告。
	private String extend_param 	;//公用业务扩展参数 	String 	用于商户的特定业务信息的传递，只有商户与支付宝约定了传递此参数且约定了参数含义，此参数才有效。参数格式：参数名1^参数值1|参数名2^参数值2|…… 多条数据用“|”间隔。支付类型（payment_type）为47（电子卡券）时，需要包含凭证号（evoucheprod_evouche_id）参数名和参数值。 	可空 	pnr^MFGXDW|start_ticket_no^123|end_ticket_no^234|b2b_login_name^abc
	private String it_b_pay 	;//超时时间 	String 	设置未付款交易的超时时间，一旦超时，该笔交易就会自动被关闭。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。该参数数值不接受小数点，如1.5h，可转换为90m。该功能需要联系支付宝配置关闭时间。 	可空 	1h
	private String default_login ;//	自动登录标识 	String 	用于标识商户是否使用自动登录的流程。如果和参数buyer_email一起使用时，就不会再让用户登录支付宝，即在收银台中不会出现登录页面。取值有以下情况：Y代表使用；N代表不使用。该功能需要联系支付宝配置。 	可空 	Y
	private String product_type 	;//商户申请的产品类型 	String(50) 	用于针对不同的产品，采取不同的计费策略。如果开通了航旅垂直搜索平台产品，请填写CHANNEL_FAST_PAY；如果没有，则为空。 	可空 	CHANNEL_FAST_PAY
	private String token 	;//快捷登录授权令牌 	String(40) 	如果开通了快捷登录产品，则需要填写；如果没有开通，则为空。 	可空 	201103290c9f9f2c03db4267a4c8e1bfe3adfd52
	private String item_orders_info;// 	商户回传业务参数 	String(40000) 	买家通过etao购买的商品的详细清单。如果是etao商户则填写；如果不是，则为空。详细规则请参见“item_orders_info参数说明”。 	可空 	参见“item_orders_info参数样例”
	private String sign_id_ext ;//	商户买家签约号 	String(50) 	用于唯一标识商户买家。如果本参数不为空，则sign_name_ext不能为空。 	可空 	ZHANGSAN
	private String sign_name_ext ;//	商户买家签约名 	String(128) 	商户买家唯一标识对应的名字。 	可空 	张三
	private String qr_pay_mode 	;//扫码支付方式 	String(1) 	扫码支付的方式，支持前置模式和跳转模式。前置模式是将二维码前置到商户的订单确认页的模式。需要商户在自己的页面中以iframe方式请求支付宝页面。具体分为以下3种：1、订单码-简约前置模式，对应iframe宽度不能小于600px，高度不能小于300px；2、订单码-前置模式，对应iframe宽度不能小于300px，高度不能小于600px；3、订单码-迷你前置模式，对应iframe宽度不能小于75px，高度不能小于75px。跳转模式下，用户的扫码界面是由支付宝生成的，不在商户的域名下。4、订单码-跳转模式 	可空 	1
	
}
