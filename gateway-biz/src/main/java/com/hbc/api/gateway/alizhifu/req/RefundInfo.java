/**
 * @Author lukangle
 * @2015年10月17日@上午11:56:43
 */
package com.hbc.api.gateway.alizhifu.req;

import java.util.List;

public class RefundInfo {
	/**
	 * 单笔数据集 	String 	退款请求的明细数据。格式详情参见“4.3 单笔数据集参数说明”。 	不可空 	2011011201037066^5.00^协商退款
	 */
	private String refundNo;
	private double refundMoney;
	private String reason;
	public String getRefundNo() {
		return refundNo;
	}
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}
	public double getRefundMoney() {
		return refundMoney;
	}
	public void setRefundMoney(double refundMoney) {
		this.refundMoney = refundMoney;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public static String getJsonStr(List<RefundInfo> refundInfos){
		String refundDetail = "";
		for(int i=0;i<refundInfos.size();i++){
			if(null == refundInfos.get(i).getReason() || refundInfos.get(i).getReason().length()<1){
				 refundInfos.get(i).setReason("皇包车协议退款");
			}
			if(i==0){
				refundDetail = refundDetail+refundInfos.get(i).getRefundNo()+"^"+refundInfos.get(i).getRefundMoney()+"^"+refundInfos.get(i).getReason();
			}else{
				refundDetail = refundDetail+"#"+refundInfos.get(i).getRefundNo()+"^"+refundInfos.get(i).getRefundMoney()+"^"+refundInfos.get(i).getReason();
			}
		}
		return refundDetail;
	}
}
