package com.hbc.api.trade.order.mapping.gen.bean;

import java.util.Date;

public class TradeAdditionalCost {
    /**
     *  增向费用ID
     *  所属表字段为`trade_additional_cost`.additional_no
     */
    private String additionalNo;

    /**
     *  导游ID
     *  所属表字段为`trade_additional_cost`.guide_id
     */
    private Integer guideId;

    /**
     *  增向费用 状态 1 初始态 2为导游已确认付款
     *  所属表字段为`trade_additional_cost`.addition_status
     */
    private Integer additionStatus;

    /**
     *  订单号
     *  所属表字段为`trade_additional_cost`.order_no
     */
    private String orderNo;

    /**
     *  导游申请的费用金额
     *  所属表字段为`trade_additional_cost`.apply_price
     */
    private Double applyPrice;

    /**
     *  超时费
     *  所属表字段为`trade_additional_cost`.over_time_cost
     */
    private Double overTimeCost;

    /**
     *  超公里费
     *  所属表字段为`trade_additional_cost`.over_distance_cost
     */
    private Double overDistanceCost;

    /**
     *  超天数费
     *  所属表字段为`trade_additional_cost`.over_day_cost
     */
    private Double overDayCost;

    /**
     *  0 未读 1已读
     *  所属表字段为`trade_additional_cost`.addition_is_read
     */
    private Integer additionIsRead;

    /**
     *  垫付费
     *  所属表字段为`trade_additional_cost`.over_pay_cost
     */
    private Double overPayCost;

    /**
     *  超等待时间费用（仅限接送机，次租）
     *  所属表字段为`trade_additional_cost`.over_wait_time_cost
     */
    private Double overWaitTimeCost;

    /**
     *  其他费用
     *  所属表字段为`trade_additional_cost`.other_cost1
     */
    private Double otherCost1;

    /**
     *  其他费用2
     *  所属表字段为`trade_additional_cost`.other_cost2
     */
    private Double otherCost2;

    /**
     *  
     *  所属表字段为`trade_additional_cost`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`trade_additional_cost`.create_time
     */
    private Date createTime;

    /**
     *  导游提交的费用异动申请原因
     *  所属表字段为`trade_additional_cost`.reason
     */
    private String reason;

    /**
     *增向费用ID
     *`trade_additional_cost`.additional_no
     *
     * @return the value of `trade_additional_cost`.additional_no
     *
     * @mbggenerated
     */
    public String getAdditionalNo() {
        return additionalNo;
    }

    /**
     *增向费用ID
     *`trade_additional_cost`.additional_no
     *
     * @param additionalNo the value for `trade_additional_cost`.additional_no
     *
     * @mbggenerated
     */
    public void setAdditionalNo(String additionalNo) {
        this.additionalNo = additionalNo == null ? null : additionalNo.trim();
    }

    /**
     *导游ID
     *`trade_additional_cost`.guide_id
     *
     * @return the value of `trade_additional_cost`.guide_id
     *
     * @mbggenerated
     */
    public Integer getGuideId() {
        return guideId;
    }

    /**
     *导游ID
     *`trade_additional_cost`.guide_id
     *
     * @param guideId the value for `trade_additional_cost`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
    }

    /**
     *增向费用 状态 1 初始态 2为导游已确认付款
     *`trade_additional_cost`.addition_status
     *
     * @return the value of `trade_additional_cost`.addition_status
     *
     * @mbggenerated
     */
    public Integer getAdditionStatus() {
        return additionStatus;
    }

    /**
     *增向费用 状态 1 初始态 2为导游已确认付款
     *`trade_additional_cost`.addition_status
     *
     * @param additionStatus the value for `trade_additional_cost`.addition_status
     *
     * @mbggenerated
     */
    public void setAdditionStatus(Integer additionStatus) {
        this.additionStatus = additionStatus;
    }

    /**
     *订单号
     *`trade_additional_cost`.order_no
     *
     * @return the value of `trade_additional_cost`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *订单号
     *`trade_additional_cost`.order_no
     *
     * @param orderNo the value for `trade_additional_cost`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *导游申请的费用金额
     *`trade_additional_cost`.apply_price
     *
     * @return the value of `trade_additional_cost`.apply_price
     *
     * @mbggenerated
     */
    public Double getApplyPrice() {
        return applyPrice;
    }

    /**
     *导游申请的费用金额
     *`trade_additional_cost`.apply_price
     *
     * @param applyPrice the value for `trade_additional_cost`.apply_price
     *
     * @mbggenerated
     */
    public void setApplyPrice(Double applyPrice) {
        this.applyPrice = applyPrice;
    }

    /**
     *超时费
     *`trade_additional_cost`.over_time_cost
     *
     * @return the value of `trade_additional_cost`.over_time_cost
     *
     * @mbggenerated
     */
    public Double getOverTimeCost() {
        return overTimeCost;
    }

    /**
     *超时费
     *`trade_additional_cost`.over_time_cost
     *
     * @param overTimeCost the value for `trade_additional_cost`.over_time_cost
     *
     * @mbggenerated
     */
    public void setOverTimeCost(Double overTimeCost) {
        this.overTimeCost = overTimeCost;
    }

    /**
     *超公里费
     *`trade_additional_cost`.over_distance_cost
     *
     * @return the value of `trade_additional_cost`.over_distance_cost
     *
     * @mbggenerated
     */
    public Double getOverDistanceCost() {
        return overDistanceCost;
    }

    /**
     *超公里费
     *`trade_additional_cost`.over_distance_cost
     *
     * @param overDistanceCost the value for `trade_additional_cost`.over_distance_cost
     *
     * @mbggenerated
     */
    public void setOverDistanceCost(Double overDistanceCost) {
        this.overDistanceCost = overDistanceCost;
    }

    /**
     *超天数费
     *`trade_additional_cost`.over_day_cost
     *
     * @return the value of `trade_additional_cost`.over_day_cost
     *
     * @mbggenerated
     */
    public Double getOverDayCost() {
        return overDayCost;
    }

    /**
     *超天数费
     *`trade_additional_cost`.over_day_cost
     *
     * @param overDayCost the value for `trade_additional_cost`.over_day_cost
     *
     * @mbggenerated
     */
    public void setOverDayCost(Double overDayCost) {
        this.overDayCost = overDayCost;
    }

    /**
     *0 未读 1已读
     *`trade_additional_cost`.addition_is_read
     *
     * @return the value of `trade_additional_cost`.addition_is_read
     *
     * @mbggenerated
     */
    public Integer getAdditionIsRead() {
        return additionIsRead;
    }

    /**
     *0 未读 1已读
     *`trade_additional_cost`.addition_is_read
     *
     * @param additionIsRead the value for `trade_additional_cost`.addition_is_read
     *
     * @mbggenerated
     */
    public void setAdditionIsRead(Integer additionIsRead) {
        this.additionIsRead = additionIsRead;
    }

    /**
     *垫付费
     *`trade_additional_cost`.over_pay_cost
     *
     * @return the value of `trade_additional_cost`.over_pay_cost
     *
     * @mbggenerated
     */
    public Double getOverPayCost() {
        return overPayCost;
    }

    /**
     *垫付费
     *`trade_additional_cost`.over_pay_cost
     *
     * @param overPayCost the value for `trade_additional_cost`.over_pay_cost
     *
     * @mbggenerated
     */
    public void setOverPayCost(Double overPayCost) {
        this.overPayCost = overPayCost;
    }

    /**
     *超等待时间费用（仅限接送机，次租）
     *`trade_additional_cost`.over_wait_time_cost
     *
     * @return the value of `trade_additional_cost`.over_wait_time_cost
     *
     * @mbggenerated
     */
    public Double getOverWaitTimeCost() {
        return overWaitTimeCost;
    }

    /**
     *超等待时间费用（仅限接送机，次租）
     *`trade_additional_cost`.over_wait_time_cost
     *
     * @param overWaitTimeCost the value for `trade_additional_cost`.over_wait_time_cost
     *
     * @mbggenerated
     */
    public void setOverWaitTimeCost(Double overWaitTimeCost) {
        this.overWaitTimeCost = overWaitTimeCost;
    }

    /**
     *其他费用
     *`trade_additional_cost`.other_cost1
     *
     * @return the value of `trade_additional_cost`.other_cost1
     *
     * @mbggenerated
     */
    public Double getOtherCost1() {
        return otherCost1;
    }

    /**
     *其他费用
     *`trade_additional_cost`.other_cost1
     *
     * @param otherCost1 the value for `trade_additional_cost`.other_cost1
     *
     * @mbggenerated
     */
    public void setOtherCost1(Double otherCost1) {
        this.otherCost1 = otherCost1;
    }

    /**
     *其他费用2
     *`trade_additional_cost`.other_cost2
     *
     * @return the value of `trade_additional_cost`.other_cost2
     *
     * @mbggenerated
     */
    public Double getOtherCost2() {
        return otherCost2;
    }

    /**
     *其他费用2
     *`trade_additional_cost`.other_cost2
     *
     * @param otherCost2 the value for `trade_additional_cost`.other_cost2
     *
     * @mbggenerated
     */
    public void setOtherCost2(Double otherCost2) {
        this.otherCost2 = otherCost2;
    }

    /**
     *
     *`trade_additional_cost`.update_time
     *
     * @return the value of `trade_additional_cost`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`trade_additional_cost`.update_time
     *
     * @param updateTime the value for `trade_additional_cost`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`trade_additional_cost`.create_time
     *
     * @return the value of `trade_additional_cost`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`trade_additional_cost`.create_time
     *
     * @param createTime the value for `trade_additional_cost`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *导游提交的费用异动申请原因
     *`trade_additional_cost`.reason
     *
     * @return the value of `trade_additional_cost`.reason
     *
     * @mbggenerated
     */
    public String getReason() {
        return reason;
    }

    /**
     *导游提交的费用异动申请原因
     *`trade_additional_cost`.reason
     *
     * @param reason the value for `trade_additional_cost`.reason
     *
     * @mbggenerated
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_additional_cost`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", additionalNo=").append(additionalNo);
        sb.append(", guideId=").append(guideId);
        sb.append(", additionStatus=").append(additionStatus);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", applyPrice=").append(applyPrice);
        sb.append(", overTimeCost=").append(overTimeCost);
        sb.append(", overDistanceCost=").append(overDistanceCost);
        sb.append(", overDayCost=").append(overDayCost);
        sb.append(", additionIsRead=").append(additionIsRead);
        sb.append(", overPayCost=").append(overPayCost);
        sb.append(", overWaitTimeCost=").append(overWaitTimeCost);
        sb.append(", otherCost1=").append(otherCost1);
        sb.append(", otherCost2=").append(otherCost2);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", reason=").append(reason);
        sb.append("]");
        return sb.toString();
    }
}