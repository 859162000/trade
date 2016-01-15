package com.hbc.api.trade.order.mapping.genx.xbean;

import java.util.Date;

public class TradeDeliverGuideVoBean {
	/**
	 * 导游订单可见关联Id 所属表字段为`trade_deliver_guide`.allocat_gno
	 */
	private String allocatGno;

	/**
	 * 订单发单表no 所属表字段为`trade_deliver_guide`.deliver_no
	 */
	private String deliverNo;

	/**
	 * 订单Id 所属表字段为`trade_deliver_guide`.order_no
	 */
	private String orderNo;

	/**
	 * 导游Id 所属表字段为`trade_deliver_guide`.guide_id
	 */
	private String guideId;

	/**
	 * 导游姓名 所属表字段为`trade_deliver_guide`.guide_name
	 */
	private String guideName;

	/**
	 * 导游价格 所属表字段为`trade_deliver_guide`.guide_price
	 */
	private Double guidePrice;

	/**
	 * 
	 * 所属表字段为`trade_deliver_guide`.guide_no
	 */
	private String guideNo;

	/**
	 * 1-正常发单；2-立即发单；3-增量补发；4-取消重发 5 指派导游 6 支付前预指派导游
	 * 所属表字段为`trade_deliver_guide`.deliver_type
	 */
	private Integer deliverType;

	/**
	 * 
	 * 所属表字段为`trade_deliver_guide`.deliver_type_name
	 */
	private String deliverTypeName;

	/**
	 * 1:未发单 2:已发送给导游 3:接单成功4:接单成功并且消息已经发送5:pk失败 6:取消重发
	 * 所属表字段为`trade_deliver_guide`.deliver_status
	 */
	private Integer deliverStatus;

	/**
	 * 
	 * 所属表字段为`trade_deliver_guide`.deliver_status_name
	 */
	private String deliverStatusName;

	/**
	 * 表态是否愿意接单：0-未表态（默认）1-愿意接 2 不愿意接 所属表字段为`trade_deliver_guide`.demand_deal
	 */
	private Integer demandDeal;

	/**
	 * 订单详情是否查看 0-初始（默认）；1-已读 所属表字段为`trade_deliver_guide`.is_read
	 */
	private Integer isRead;

	/**
	 * 错过页中是否已经查看0-初始（默认）；1-已读 所属表字段为`trade_deliver_guide`.is_read_mis
	 */
	private Integer isReadMis;

	/**
	 * 主要用于二次重发 0-不可见；1-可见（默认） 所属表字段为`trade_deliver_guide`.is_readable
	 */
	private Integer isReadable;

	/**
	 * 0 普通 1 串单 所属表字段为`trade_deliver_guide`.is_on_way
	 */
	private Integer isOnWay;

	/**
	 * PK 失败的原因：1-禁止接单；2-行程冲突；3-评分；4-抢单速度；5-RP;6-已串单
	 * 所属表字段为`trade_deliver_guide`.fail_type
	 */
	private Integer failType;

	/**
	 * 查看PK结果 0-未查看（默认）1-已查看 所属表字段为`trade_deliver_guide`.view_result
	 */
	private Integer viewResult;

	/**
	 * 拒接原因，存储格式为 1,2,3,4 所属表字段为`trade_deliver_guide`.refuse_reason
	 */
	private String refuseReason;

	/**
	 * 导游接受时间 所属表字段为`trade_deliver_guide`.accept_time
	 */
	private Date acceptTime;

	/**
	 * 
	 * 所属表字段为`trade_deliver_guide`.first_read_time
	 */
	private Date firstReadTime;

	/**
	 * 其他原因 所属表字段为`trade_deliver_guide`.other
	 */
	private String other;

	/**
	 * 服务时间，服务开始时间 日租默认8点 所属表字段为`trade_deliver_guide`.service_time
	 */
	private Date serviceTime;

	/**
	 * 更新时间 所属表字段为`trade_deliver_guide`.update_time
	 */
	private Date updateTime;

	/**
	 * 创建时间 所属表字段为`trade_deliver_guide`.create_time
	 */
	private Date createTime;

	private Date deliverTime;

	public String getAllocatGno() {
		return allocatGno;
	}

	public void setAllocatGno(String allocatGno) {
		this.allocatGno = allocatGno;
	}

	public String getDeliverNo() {
		return deliverNo;
	}

	public void setDeliverNo(String deliverNo) {
		this.deliverNo = deliverNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getGuideId() {
		return guideId;
	}

	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public Double getGuidePrice() {
		return guidePrice;
	}

	public void setGuidePrice(Double guidePrice) {
		this.guidePrice = guidePrice;
	}

	public String getGuideNo() {
		return guideNo;
	}

	public void setGuideNo(String guideNo) {
		this.guideNo = guideNo;
	}

	public Integer getDeliverType() {
		return deliverType;
	}

	public void setDeliverType(Integer deliverType) {
		this.deliverType = deliverType;
	}

	public String getDeliverTypeName() {
		return deliverTypeName;
	}

	public void setDeliverTypeName(String deliverTypeName) {
		this.deliverTypeName = deliverTypeName;
	}

	public Integer getDeliverStatus() {
		return deliverStatus;
	}

	public void setDeliverStatus(Integer deliverStatus) {
		this.deliverStatus = deliverStatus;
	}

	public String getDeliverStatusName() {
		return deliverStatusName;
	}

	public void setDeliverStatusName(String deliverStatusName) {
		this.deliverStatusName = deliverStatusName;
	}

	public Integer getDemandDeal() {
		return demandDeal;
	}

	public void setDemandDeal(Integer demandDeal) {
		this.demandDeal = demandDeal;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Integer getIsReadMis() {
		return isReadMis;
	}

	public void setIsReadMis(Integer isReadMis) {
		this.isReadMis = isReadMis;
	}

	public Integer getIsReadable() {
		return isReadable;
	}

	public void setIsReadable(Integer isReadable) {
		this.isReadable = isReadable;
	}

	public Integer getIsOnWay() {
		return isOnWay;
	}

	public void setIsOnWay(Integer isOnWay) {
		this.isOnWay = isOnWay;
	}

	public Integer getFailType() {
		return failType;
	}

	public void setFailType(Integer failType) {
		this.failType = failType;
	}

	public Integer getViewResult() {
		return viewResult;
	}

	public void setViewResult(Integer viewResult) {
		this.viewResult = viewResult;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public Date getFirstReadTime() {
		return firstReadTime;
	}

	public void setFirstReadTime(Date firstReadTime) {
		this.firstReadTime = firstReadTime;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Date getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Date serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}

	@Override
	public String toString() {
		return "TradeDeliverGuideVoBean [allocatGno=" + allocatGno + ", deliverNo=" + deliverNo + ", orderNo=" + orderNo + ", guideId=" + guideId + ", guideName=" + guideName + ", guidePrice=" + guidePrice + ", guideNo=" + guideNo + ", deliverType="
				+ deliverType + ", deliverTypeName=" + deliverTypeName + ", deliverStatus=" + deliverStatus + ", deliverStatusName=" + deliverStatusName + ", demandDeal=" + demandDeal + ", isRead=" + isRead + ", isReadMis=" + isReadMis
				+ ", isReadable=" + isReadable + ", isOnWay=" + isOnWay + ", failType=" + failType + ", viewResult=" + viewResult + ", refuseReason=" + refuseReason + ", acceptTime=" + acceptTime + ", firstReadTime=" + firstReadTime + ", other="
				+ other + ", serviceTime=" + serviceTime + ", updateTime=" + updateTime + ", createTime=" + createTime + ", deliverTime=" + deliverTime + "]";
	}

}