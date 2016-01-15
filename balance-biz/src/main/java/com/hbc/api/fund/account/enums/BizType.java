/**
 * @Author lukangle
 * @2015年11月18日@下午1:40:22
 */
package com.hbc.api.fund.account.enums;

import com.hbc.api.fund.account.exceptions.BalanceException;

/**
 * 业务类型。1-接机； 2-送机； 3-日租；4-次租；5-提现；6-首单奖励；8-处罚；10-导游邀请奖励； 11-接机（接单奖励）；12-接机（取消补偿
 * ）；13-接机（改派订单）；14-接机（取消退款）；21-送机（接单奖励）；22-送机（取消补偿）；23 -送机（改派订单）；24-送机（取消退款）；31
 * -日租（接单奖励）；32-日租（取消补偿）；33-日租（改派订单）；34-包车（取消退款）；41-次租 （接单奖励）；42-次租（取消补偿）；43-次租
 * （改派订单）；44-次租（取消退款）；10-充值；5-提现；101-增项费用；102-返点；51-提现
 * （公司提现）；201-市场活动补贴（对C端）202-代金劵（对C端）。对于订单流水只有一条，后续的操作做update。
 **/
public enum BizType {
	PICKUP(1, "接机"), TRANSFER(2, "送机"), DAILY(3, "日租"), SINGLE(4, "次租"), RECHARAGE(5, "提现 "), REWARD(6, "首单奖励"), CHUFA(8, "处罚"), PAY(10, "充值"), REWARDPICKUP(11, "接机（接单奖励）"),
	BUCAHNG_PICKUP(12, "接机（取消补偿"), GAIPAI_PICKUP(13, "接机（改派订单）"), CANCAL_PICKUP(14, "接机（取消退款）"),
	REWARD_PICKUP(21, "送机（接单奖励）"), CANCAL_TRANSFER(24, "送机（取消退款）"), CANCAL_DAILY(34, "包车（取消退款）"),
	CANCLE_SINGLE(44, "次租（取消退款）"), CANCAL_COMMNENDATION(45, "精品线路退款"), ACCOUNT_OPERATION(46, "账号冻结启用"),
	WITHDRAW_DENY(47, "提现失败"), REWARD_BY_YINGDAOYOU(48, "导游奖励"), REWARD_BY_MIS(49, "MIS导游奖励,会与相关订单挂钩"),
	PUNISH_BY_MIS(50, "MIS导游处罚,会与相关订单挂钩"), WITHDRAW_APPLY(51, "提现申请 "), BOSS_RECHARAGE(52, "地接社老板提现 "), INVITEREWORD(53, "导游邀请奖励"),

	COMMENDATION(111, "精品线路"), RT(102, "返点"), REFUND_TOUSER_LESSTHANCOUP(4500, "退款 用户付款金额小于券金额 小于支付金额 直接给系统收益"), BIZ_HONGCHONG(5001, "业务红冲，对应老的提现没有冻结金额情况")
	;

	public int value;
	public String name;

	BizType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static BizType getType(int value) {
		BizType[] accountStatus = BizType.values();
		for (BizType accountStatu : accountStatus) {
			if (accountStatu.value == value) {
				return accountStatu;
			}
		}
		throw new BalanceException(BalanceReturnCodeEnum.BIZ_NOT_EXSIT, value);
	}
}
