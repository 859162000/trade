package com.hbc.api.trade.order.mapping.gen.bean;

import java.util.Date;

public class TradeOrderSerial {
    /**
     *  串单Id
     *  所属表字段为`trade_order_serial`.serial_id
     */
    private Integer serialId;

    /**
     *  串单订单号
     *  所属表字段为`trade_order_serial`.order_no
     */
    private String orderNo;

    /**
     *  导游价格
     *  所属表字段为`trade_order_serial`.guide_price
     */
    private Double guidePrice;

    /**
     *  符合串单条件的订单号
     *  所属表字段为`trade_order_serial`.meet_order_no
     */
    private String meetOrderNo;

    /**
     *  符合串单条件的订单对应的导游Id
     *  所属表字段为`trade_order_serial`.meet_guide_id
     */
    private String meetGuideId;

    /**
     *  符合串单条件的订单对应的订单类型
     *  所属表字段为`trade_order_serial`.meet_order_type
     */
    private Integer meetOrderType;

    /**
     *  符合串单条件的订单对应的服务日期
     *  所属表字段为`trade_order_serial`.meet_order_service_time
     */
    private Date meetOrderServiceTime;

    /**
     *  符合串单条件的订单对应的服务地
     *  所属表字段为`trade_order_serial`.meet_order_address
     */
    private String meetOrderAddress;

    /**
     *  是否匹配 0-初始化（默认）；1-已匹配
     *  所属表字段为`trade_order_serial`.is_match
     */
    private Integer isMatch;

    /**
     *  更新时间
     *  所属表字段为`trade_order_serial`.update_time
     */
    private Date updateTime;

    /**
     *  创建时间
     *  所属表字段为`trade_order_serial`.create_time
     */
    private Date createTime;

    /**
     *串单Id
     *`trade_order_serial`.serial_id
     *
     * @return the value of `trade_order_serial`.serial_id
     *
     * @mbggenerated
     */
    public Integer getSerialId() {
        return serialId;
    }

    /**
     *串单Id
     *`trade_order_serial`.serial_id
     *
     * @param serialId the value for `trade_order_serial`.serial_id
     *
     * @mbggenerated
     */
    public void setSerialId(Integer serialId) {
        this.serialId = serialId;
    }

    /**
     *串单订单号
     *`trade_order_serial`.order_no
     *
     * @return the value of `trade_order_serial`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *串单订单号
     *`trade_order_serial`.order_no
     *
     * @param orderNo the value for `trade_order_serial`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *导游价格
     *`trade_order_serial`.guide_price
     *
     * @return the value of `trade_order_serial`.guide_price
     *
     * @mbggenerated
     */
    public Double getGuidePrice() {
        return guidePrice;
    }

    /**
     *导游价格
     *`trade_order_serial`.guide_price
     *
     * @param guidePrice the value for `trade_order_serial`.guide_price
     *
     * @mbggenerated
     */
    public void setGuidePrice(Double guidePrice) {
        this.guidePrice = guidePrice;
    }

    /**
     *符合串单条件的订单号
     *`trade_order_serial`.meet_order_no
     *
     * @return the value of `trade_order_serial`.meet_order_no
     *
     * @mbggenerated
     */
    public String getMeetOrderNo() {
        return meetOrderNo;
    }

    /**
     *符合串单条件的订单号
     *`trade_order_serial`.meet_order_no
     *
     * @param meetOrderNo the value for `trade_order_serial`.meet_order_no
     *
     * @mbggenerated
     */
    public void setMeetOrderNo(String meetOrderNo) {
        this.meetOrderNo = meetOrderNo == null ? null : meetOrderNo.trim();
    }

    /**
     *符合串单条件的订单对应的导游Id
     *`trade_order_serial`.meet_guide_id
     *
     * @return the value of `trade_order_serial`.meet_guide_id
     *
     * @mbggenerated
     */
    public String getMeetGuideId() {
        return meetGuideId;
    }

    /**
     *符合串单条件的订单对应的导游Id
     *`trade_order_serial`.meet_guide_id
     *
     * @param meetGuideId the value for `trade_order_serial`.meet_guide_id
     *
     * @mbggenerated
     */
    public void setMeetGuideId(String meetGuideId) {
        this.meetGuideId = meetGuideId == null ? null : meetGuideId.trim();
    }

    /**
     *符合串单条件的订单对应的订单类型
     *`trade_order_serial`.meet_order_type
     *
     * @return the value of `trade_order_serial`.meet_order_type
     *
     * @mbggenerated
     */
    public Integer getMeetOrderType() {
        return meetOrderType;
    }

    /**
     *符合串单条件的订单对应的订单类型
     *`trade_order_serial`.meet_order_type
     *
     * @param meetOrderType the value for `trade_order_serial`.meet_order_type
     *
     * @mbggenerated
     */
    public void setMeetOrderType(Integer meetOrderType) {
        this.meetOrderType = meetOrderType;
    }

    /**
     *符合串单条件的订单对应的服务日期
     *`trade_order_serial`.meet_order_service_time
     *
     * @return the value of `trade_order_serial`.meet_order_service_time
     *
     * @mbggenerated
     */
    public Date getMeetOrderServiceTime() {
        return meetOrderServiceTime;
    }

    /**
     *符合串单条件的订单对应的服务日期
     *`trade_order_serial`.meet_order_service_time
     *
     * @param meetOrderServiceTime the value for `trade_order_serial`.meet_order_service_time
     *
     * @mbggenerated
     */
    public void setMeetOrderServiceTime(Date meetOrderServiceTime) {
        this.meetOrderServiceTime = meetOrderServiceTime;
    }

    /**
     *符合串单条件的订单对应的服务地
     *`trade_order_serial`.meet_order_address
     *
     * @return the value of `trade_order_serial`.meet_order_address
     *
     * @mbggenerated
     */
    public String getMeetOrderAddress() {
        return meetOrderAddress;
    }

    /**
     *符合串单条件的订单对应的服务地
     *`trade_order_serial`.meet_order_address
     *
     * @param meetOrderAddress the value for `trade_order_serial`.meet_order_address
     *
     * @mbggenerated
     */
    public void setMeetOrderAddress(String meetOrderAddress) {
        this.meetOrderAddress = meetOrderAddress == null ? null : meetOrderAddress.trim();
    }

    /**
     *是否匹配 0-初始化（默认）；1-已匹配
     *`trade_order_serial`.is_match
     *
     * @return the value of `trade_order_serial`.is_match
     *
     * @mbggenerated
     */
    public Integer getIsMatch() {
        return isMatch;
    }

    /**
     *是否匹配 0-初始化（默认）；1-已匹配
     *`trade_order_serial`.is_match
     *
     * @param isMatch the value for `trade_order_serial`.is_match
     *
     * @mbggenerated
     */
    public void setIsMatch(Integer isMatch) {
        this.isMatch = isMatch;
    }

    /**
     *更新时间
     *`trade_order_serial`.update_time
     *
     * @return the value of `trade_order_serial`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新时间
     *`trade_order_serial`.update_time
     *
     * @param updateTime the value for `trade_order_serial`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *创建时间
     *`trade_order_serial`.create_time
     *
     * @return the value of `trade_order_serial`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     *`trade_order_serial`.create_time
     *
     * @param createTime the value for `trade_order_serial`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_order_serial`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialId=").append(serialId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", guidePrice=").append(guidePrice);
        sb.append(", meetOrderNo=").append(meetOrderNo);
        sb.append(", meetGuideId=").append(meetGuideId);
        sb.append(", meetOrderType=").append(meetOrderType);
        sb.append(", meetOrderServiceTime=").append(meetOrderServiceTime);
        sb.append(", meetOrderAddress=").append(meetOrderAddress);
        sb.append(", isMatch=").append(isMatch);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}