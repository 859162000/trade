package com.hbc.api.trade.pay.mapping.gen.bean;

import java.util.Date;

public class TradeRefundConf {
    /**
     *  
     *  所属表字段为`trade_refund_conf`.refund_conf_no
     */
    private String refundConfNo;

    /**
     *  起点 
     *  所属表字段为`trade_refund_conf`.start_hour
     */
    private Integer startHour;

    /**
     *  是否包含起点
     *  所属表字段为`trade_refund_conf`.is_contain_start
     */
    private Integer isContainStart;

    /**
     *  终点
     *  所属表字段为`trade_refund_conf`.end_hour
     */
    private Integer endHour;

    /**
     *  是否包含终点
     *  所属表字段为`trade_refund_conf`.is_contain_end
     */
    private Integer isContainEnd;

    /**
     *  
     *  所属表字段为`trade_refund_conf`.user_ratio
     */
    private Double userRatio;

    /**
     *  
     *  所属表字段为`trade_refund_conf`.guide_ratio
     */
    private Double guideRatio;

    /**
     *  1 接送次退款 2 日租退款
     *  所属表字段为`trade_refund_conf`.refund_type
     */
    private Integer refundType;

    /**
     *  
     *  所属表字段为`trade_refund_conf`.refund_type_name
     */
    private String refundTypeName;

    /**
     *  
     *  所属表字段为`trade_refund_conf`.city_id
     */
    private String cityId;

    /**
     *  
     *  所属表字段为`trade_refund_conf`.create_time
     */
    private Date createTime;

    /**
     *  
     *  所属表字段为`trade_refund_conf`.update_time
     */
    private Date updateTime;

    /**
     *
     *`trade_refund_conf`.refund_conf_no
     *
     * @return the value of `trade_refund_conf`.refund_conf_no
     *
     * @mbggenerated
     */
    public String getRefundConfNo() {
        return refundConfNo;
    }

    /**
     *
     *`trade_refund_conf`.refund_conf_no
     *
     * @param refundConfNo the value for `trade_refund_conf`.refund_conf_no
     *
     * @mbggenerated
     */
    public void setRefundConfNo(String refundConfNo) {
        this.refundConfNo = refundConfNo == null ? null : refundConfNo.trim();
    }

    /**
     *起点 
     *`trade_refund_conf`.start_hour
     *
     * @return the value of `trade_refund_conf`.start_hour
     *
     * @mbggenerated
     */
    public Integer getStartHour() {
        return startHour;
    }

    /**
     *起点 
     *`trade_refund_conf`.start_hour
     *
     * @param startHour the value for `trade_refund_conf`.start_hour
     *
     * @mbggenerated
     */
    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    /**
     *是否包含起点
     *`trade_refund_conf`.is_contain_start
     *
     * @return the value of `trade_refund_conf`.is_contain_start
     *
     * @mbggenerated
     */
    public Integer getIsContainStart() {
        return isContainStart;
    }

    /**
     *是否包含起点
     *`trade_refund_conf`.is_contain_start
     *
     * @param isContainStart the value for `trade_refund_conf`.is_contain_start
     *
     * @mbggenerated
     */
    public void setIsContainStart(Integer isContainStart) {
        this.isContainStart = isContainStart;
    }

    /**
     *终点
     *`trade_refund_conf`.end_hour
     *
     * @return the value of `trade_refund_conf`.end_hour
     *
     * @mbggenerated
     */
    public Integer getEndHour() {
        return endHour;
    }

    /**
     *终点
     *`trade_refund_conf`.end_hour
     *
     * @param endHour the value for `trade_refund_conf`.end_hour
     *
     * @mbggenerated
     */
    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    /**
     *是否包含终点
     *`trade_refund_conf`.is_contain_end
     *
     * @return the value of `trade_refund_conf`.is_contain_end
     *
     * @mbggenerated
     */
    public Integer getIsContainEnd() {
        return isContainEnd;
    }

    /**
     *是否包含终点
     *`trade_refund_conf`.is_contain_end
     *
     * @param isContainEnd the value for `trade_refund_conf`.is_contain_end
     *
     * @mbggenerated
     */
    public void setIsContainEnd(Integer isContainEnd) {
        this.isContainEnd = isContainEnd;
    }

    /**
     *
     *`trade_refund_conf`.user_ratio
     *
     * @return the value of `trade_refund_conf`.user_ratio
     *
     * @mbggenerated
     */
    public Double getUserRatio() {
        return userRatio;
    }

    /**
     *
     *`trade_refund_conf`.user_ratio
     *
     * @param userRatio the value for `trade_refund_conf`.user_ratio
     *
     * @mbggenerated
     */
    public void setUserRatio(Double userRatio) {
        this.userRatio = userRatio;
    }

    /**
     *
     *`trade_refund_conf`.guide_ratio
     *
     * @return the value of `trade_refund_conf`.guide_ratio
     *
     * @mbggenerated
     */
    public Double getGuideRatio() {
        return guideRatio;
    }

    /**
     *
     *`trade_refund_conf`.guide_ratio
     *
     * @param guideRatio the value for `trade_refund_conf`.guide_ratio
     *
     * @mbggenerated
     */
    public void setGuideRatio(Double guideRatio) {
        this.guideRatio = guideRatio;
    }

    /**
     *1 接送次退款 2 日租退款
     *`trade_refund_conf`.refund_type
     *
     * @return the value of `trade_refund_conf`.refund_type
     *
     * @mbggenerated
     */
    public Integer getRefundType() {
        return refundType;
    }

    /**
     *1 接送次退款 2 日租退款
     *`trade_refund_conf`.refund_type
     *
     * @param refundType the value for `trade_refund_conf`.refund_type
     *
     * @mbggenerated
     */
    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    /**
     *
     *`trade_refund_conf`.refund_type_name
     *
     * @return the value of `trade_refund_conf`.refund_type_name
     *
     * @mbggenerated
     */
    public String getRefundTypeName() {
        return refundTypeName;
    }

    /**
     *
     *`trade_refund_conf`.refund_type_name
     *
     * @param refundTypeName the value for `trade_refund_conf`.refund_type_name
     *
     * @mbggenerated
     */
    public void setRefundTypeName(String refundTypeName) {
        this.refundTypeName = refundTypeName == null ? null : refundTypeName.trim();
    }

    /**
     *
     *`trade_refund_conf`.city_id
     *
     * @return the value of `trade_refund_conf`.city_id
     *
     * @mbggenerated
     */
    public String getCityId() {
        return cityId;
    }

    /**
     *
     *`trade_refund_conf`.city_id
     *
     * @param cityId the value for `trade_refund_conf`.city_id
     *
     * @mbggenerated
     */
    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    /**
     *
     *`trade_refund_conf`.create_time
     *
     * @return the value of `trade_refund_conf`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`trade_refund_conf`.create_time
     *
     * @param createTime the value for `trade_refund_conf`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     *`trade_refund_conf`.update_time
     *
     * @return the value of `trade_refund_conf`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`trade_refund_conf`.update_time
     *
     * @param updateTime the value for `trade_refund_conf`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund_conf`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", refundConfNo=").append(refundConfNo);
        sb.append(", startHour=").append(startHour);
        sb.append(", isContainStart=").append(isContainStart);
        sb.append(", endHour=").append(endHour);
        sb.append(", isContainEnd=").append(isContainEnd);
        sb.append(", userRatio=").append(userRatio);
        sb.append(", guideRatio=").append(guideRatio);
        sb.append(", refundType=").append(refundType);
        sb.append(", refundTypeName=").append(refundTypeName);
        sb.append(", cityId=").append(cityId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}