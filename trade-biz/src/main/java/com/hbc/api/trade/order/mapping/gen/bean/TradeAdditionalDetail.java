package com.hbc.api.trade.order.mapping.gen.bean;

import java.util.Date;

public class TradeAdditionalDetail {
    /**
     *  
     *  所属表字段为`trade_additional_detail`.detail_id
     */
    private String detailId;

    /**
     *  总的增项费用ID
     *  所属表字段为`trade_additional_detail`.additional_id
     */
    private String additionalId;

    /**
     *  包车日期
     *  所属表字段为`trade_additional_detail`.daily_date
     */
    private Date dailyDate;

    /**
     *  超时 包车以小时计数，接机以分钟计数
     *  所属表字段为`trade_additional_detail`.over_time
     */
    private Integer overTime;

    /**
     *  超时费
     *  所属表字段为`trade_additional_detail`.over_time_price
     */
    private Double overTimePrice;

    /**
     *  超时单价  包车是一小时的价格，接机是一分钟的价格（一个overTime的价格）
     *  所属表字段为`trade_additional_detail`.unit_time_price
     */
    private Double unitTimePrice;

    /**
     *  超公里数
     *  所属表字段为`trade_additional_detail`.over_distance
     */
    private Double overDistance;

    /**
     *  
     *  所属表字段为`trade_additional_detail`.over_distance_price
     */
    private Double overDistancePrice;

    /**
     *  一公里单价
     *  所属表字段为`trade_additional_detail`.unit_distance_price
     */
    private Double unitDistancePrice;

    /**
     *  超天数
     *  所属表字段为`trade_additional_detail`.over_day
     */
    private Double overDay;

    /**
     *  
     *  所属表字段为`trade_additional_detail`.over_day_price
     */
    private Double overDayPrice;

    /**
     *  一天的报价
     *  所属表字段为`trade_additional_detail`.unit_day_price
     */
    private Double unitDayPrice;

    /**
     *  超等待时间（仅限接送机，次租）
     *  所属表字段为`trade_additional_detail`.over_wait_time_cost
     */
    private Double overWaitTimeCost;

    /**
     *  其他费用1
     *  所属表字段为`trade_additional_detail`.other_fee1
     */
    private Double otherFee1;

    /**
     *  其他费用2
     *  所属表字段为`trade_additional_detail`.other_fee2
     */
    private Double otherFee2;

    /**
     *  申请总费用
     *  所属表字段为`trade_additional_detail`.applyFee
     */
    private Double applyfee;

    /**
     *  确认超时时间
     *  所属表字段为`trade_additional_detail`.cfm_over_time
     */
    private Integer cfmOverTime;

    /**
     *  确认超公里数
     *  所属表字段为`trade_additional_detail`.cfm_over_distance
     */
    private Double cfmOverDistance;

    /**
     *  确认超天数
     *  所属表字段为`trade_additional_detail`.cfm_over_day
     */
    private Double cfmOverDay;

    /**
     *  确认其他费用1
     *  所属表字段为`trade_additional_detail`.cfm_other_fee1
     */
    private Double cfmOtherFee1;

    /**
     *  确认其他费用2
     *  所属表字段为`trade_additional_detail`.cfm_other_fee2
     */
    private Double cfmOtherFee2;

    /**
     *  确认总费用
     *  所属表字段为`trade_additional_detail`.cfm_fee
     */
    private Double cfmFee;

    /**
     *  
     *  所属表字段为`trade_additional_detail`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`trade_additional_detail`.create_time
     */
    private Date createTime;

    /**
     *
     *`trade_additional_detail`.detail_id
     *
     * @return the value of `trade_additional_detail`.detail_id
     *
     * @mbggenerated
     */
    public String getDetailId() {
        return detailId;
    }

    /**
     *
     *`trade_additional_detail`.detail_id
     *
     * @param detailId the value for `trade_additional_detail`.detail_id
     *
     * @mbggenerated
     */
    public void setDetailId(String detailId) {
        this.detailId = detailId == null ? null : detailId.trim();
    }

    /**
     *总的增项费用ID
     *`trade_additional_detail`.additional_id
     *
     * @return the value of `trade_additional_detail`.additional_id
     *
     * @mbggenerated
     */
    public String getAdditionalId() {
        return additionalId;
    }

    /**
     *总的增项费用ID
     *`trade_additional_detail`.additional_id
     *
     * @param additionalId the value for `trade_additional_detail`.additional_id
     *
     * @mbggenerated
     */
    public void setAdditionalId(String additionalId) {
        this.additionalId = additionalId == null ? null : additionalId.trim();
    }

    /**
     *包车日期
     *`trade_additional_detail`.daily_date
     *
     * @return the value of `trade_additional_detail`.daily_date
     *
     * @mbggenerated
     */
    public Date getDailyDate() {
        return dailyDate;
    }

    /**
     *包车日期
     *`trade_additional_detail`.daily_date
     *
     * @param dailyDate the value for `trade_additional_detail`.daily_date
     *
     * @mbggenerated
     */
    public void setDailyDate(Date dailyDate) {
        this.dailyDate = dailyDate;
    }

    /**
     *超时 包车以小时计数，接机以分钟计数
     *`trade_additional_detail`.over_time
     *
     * @return the value of `trade_additional_detail`.over_time
     *
     * @mbggenerated
     */
    public Integer getOverTime() {
        return overTime;
    }

    /**
     *超时 包车以小时计数，接机以分钟计数
     *`trade_additional_detail`.over_time
     *
     * @param overTime the value for `trade_additional_detail`.over_time
     *
     * @mbggenerated
     */
    public void setOverTime(Integer overTime) {
        this.overTime = overTime;
    }

    /**
     *超时费
     *`trade_additional_detail`.over_time_price
     *
     * @return the value of `trade_additional_detail`.over_time_price
     *
     * @mbggenerated
     */
    public Double getOverTimePrice() {
        return overTimePrice;
    }

    /**
     *超时费
     *`trade_additional_detail`.over_time_price
     *
     * @param overTimePrice the value for `trade_additional_detail`.over_time_price
     *
     * @mbggenerated
     */
    public void setOverTimePrice(Double overTimePrice) {
        this.overTimePrice = overTimePrice;
    }

    /**
     *超时单价  包车是一小时的价格，接机是一分钟的价格（一个overTime的价格）
     *`trade_additional_detail`.unit_time_price
     *
     * @return the value of `trade_additional_detail`.unit_time_price
     *
     * @mbggenerated
     */
    public Double getUnitTimePrice() {
        return unitTimePrice;
    }

    /**
     *超时单价  包车是一小时的价格，接机是一分钟的价格（一个overTime的价格）
     *`trade_additional_detail`.unit_time_price
     *
     * @param unitTimePrice the value for `trade_additional_detail`.unit_time_price
     *
     * @mbggenerated
     */
    public void setUnitTimePrice(Double unitTimePrice) {
        this.unitTimePrice = unitTimePrice;
    }

    /**
     *超公里数
     *`trade_additional_detail`.over_distance
     *
     * @return the value of `trade_additional_detail`.over_distance
     *
     * @mbggenerated
     */
    public Double getOverDistance() {
        return overDistance;
    }

    /**
     *超公里数
     *`trade_additional_detail`.over_distance
     *
     * @param overDistance the value for `trade_additional_detail`.over_distance
     *
     * @mbggenerated
     */
    public void setOverDistance(Double overDistance) {
        this.overDistance = overDistance;
    }

    /**
     *
     *`trade_additional_detail`.over_distance_price
     *
     * @return the value of `trade_additional_detail`.over_distance_price
     *
     * @mbggenerated
     */
    public Double getOverDistancePrice() {
        return overDistancePrice;
    }

    /**
     *
     *`trade_additional_detail`.over_distance_price
     *
     * @param overDistancePrice the value for `trade_additional_detail`.over_distance_price
     *
     * @mbggenerated
     */
    public void setOverDistancePrice(Double overDistancePrice) {
        this.overDistancePrice = overDistancePrice;
    }

    /**
     *一公里单价
     *`trade_additional_detail`.unit_distance_price
     *
     * @return the value of `trade_additional_detail`.unit_distance_price
     *
     * @mbggenerated
     */
    public Double getUnitDistancePrice() {
        return unitDistancePrice;
    }

    /**
     *一公里单价
     *`trade_additional_detail`.unit_distance_price
     *
     * @param unitDistancePrice the value for `trade_additional_detail`.unit_distance_price
     *
     * @mbggenerated
     */
    public void setUnitDistancePrice(Double unitDistancePrice) {
        this.unitDistancePrice = unitDistancePrice;
    }

    /**
     *超天数
     *`trade_additional_detail`.over_day
     *
     * @return the value of `trade_additional_detail`.over_day
     *
     * @mbggenerated
     */
    public Double getOverDay() {
        return overDay;
    }

    /**
     *超天数
     *`trade_additional_detail`.over_day
     *
     * @param overDay the value for `trade_additional_detail`.over_day
     *
     * @mbggenerated
     */
    public void setOverDay(Double overDay) {
        this.overDay = overDay;
    }

    /**
     *
     *`trade_additional_detail`.over_day_price
     *
     * @return the value of `trade_additional_detail`.over_day_price
     *
     * @mbggenerated
     */
    public Double getOverDayPrice() {
        return overDayPrice;
    }

    /**
     *
     *`trade_additional_detail`.over_day_price
     *
     * @param overDayPrice the value for `trade_additional_detail`.over_day_price
     *
     * @mbggenerated
     */
    public void setOverDayPrice(Double overDayPrice) {
        this.overDayPrice = overDayPrice;
    }

    /**
     *一天的报价
     *`trade_additional_detail`.unit_day_price
     *
     * @return the value of `trade_additional_detail`.unit_day_price
     *
     * @mbggenerated
     */
    public Double getUnitDayPrice() {
        return unitDayPrice;
    }

    /**
     *一天的报价
     *`trade_additional_detail`.unit_day_price
     *
     * @param unitDayPrice the value for `trade_additional_detail`.unit_day_price
     *
     * @mbggenerated
     */
    public void setUnitDayPrice(Double unitDayPrice) {
        this.unitDayPrice = unitDayPrice;
    }

    /**
     *超等待时间（仅限接送机，次租）
     *`trade_additional_detail`.over_wait_time_cost
     *
     * @return the value of `trade_additional_detail`.over_wait_time_cost
     *
     * @mbggenerated
     */
    public Double getOverWaitTimeCost() {
        return overWaitTimeCost;
    }

    /**
     *超等待时间（仅限接送机，次租）
     *`trade_additional_detail`.over_wait_time_cost
     *
     * @param overWaitTimeCost the value for `trade_additional_detail`.over_wait_time_cost
     *
     * @mbggenerated
     */
    public void setOverWaitTimeCost(Double overWaitTimeCost) {
        this.overWaitTimeCost = overWaitTimeCost;
    }

    /**
     *其他费用1
     *`trade_additional_detail`.other_fee1
     *
     * @return the value of `trade_additional_detail`.other_fee1
     *
     * @mbggenerated
     */
    public Double getOtherFee1() {
        return otherFee1;
    }

    /**
     *其他费用1
     *`trade_additional_detail`.other_fee1
     *
     * @param otherFee1 the value for `trade_additional_detail`.other_fee1
     *
     * @mbggenerated
     */
    public void setOtherFee1(Double otherFee1) {
        this.otherFee1 = otherFee1;
    }

    /**
     *其他费用2
     *`trade_additional_detail`.other_fee2
     *
     * @return the value of `trade_additional_detail`.other_fee2
     *
     * @mbggenerated
     */
    public Double getOtherFee2() {
        return otherFee2;
    }

    /**
     *其他费用2
     *`trade_additional_detail`.other_fee2
     *
     * @param otherFee2 the value for `trade_additional_detail`.other_fee2
     *
     * @mbggenerated
     */
    public void setOtherFee2(Double otherFee2) {
        this.otherFee2 = otherFee2;
    }

    /**
     *申请总费用
     *`trade_additional_detail`.applyFee
     *
     * @return the value of `trade_additional_detail`.applyFee
     *
     * @mbggenerated
     */
    public Double getApplyfee() {
        return applyfee;
    }

    /**
     *申请总费用
     *`trade_additional_detail`.applyFee
     *
     * @param applyfee the value for `trade_additional_detail`.applyFee
     *
     * @mbggenerated
     */
    public void setApplyfee(Double applyfee) {
        this.applyfee = applyfee;
    }

    /**
     *确认超时时间
     *`trade_additional_detail`.cfm_over_time
     *
     * @return the value of `trade_additional_detail`.cfm_over_time
     *
     * @mbggenerated
     */
    public Integer getCfmOverTime() {
        return cfmOverTime;
    }

    /**
     *确认超时时间
     *`trade_additional_detail`.cfm_over_time
     *
     * @param cfmOverTime the value for `trade_additional_detail`.cfm_over_time
     *
     * @mbggenerated
     */
    public void setCfmOverTime(Integer cfmOverTime) {
        this.cfmOverTime = cfmOverTime;
    }

    /**
     *确认超公里数
     *`trade_additional_detail`.cfm_over_distance
     *
     * @return the value of `trade_additional_detail`.cfm_over_distance
     *
     * @mbggenerated
     */
    public Double getCfmOverDistance() {
        return cfmOverDistance;
    }

    /**
     *确认超公里数
     *`trade_additional_detail`.cfm_over_distance
     *
     * @param cfmOverDistance the value for `trade_additional_detail`.cfm_over_distance
     *
     * @mbggenerated
     */
    public void setCfmOverDistance(Double cfmOverDistance) {
        this.cfmOverDistance = cfmOverDistance;
    }

    /**
     *确认超天数
     *`trade_additional_detail`.cfm_over_day
     *
     * @return the value of `trade_additional_detail`.cfm_over_day
     *
     * @mbggenerated
     */
    public Double getCfmOverDay() {
        return cfmOverDay;
    }

    /**
     *确认超天数
     *`trade_additional_detail`.cfm_over_day
     *
     * @param cfmOverDay the value for `trade_additional_detail`.cfm_over_day
     *
     * @mbggenerated
     */
    public void setCfmOverDay(Double cfmOverDay) {
        this.cfmOverDay = cfmOverDay;
    }

    /**
     *确认其他费用1
     *`trade_additional_detail`.cfm_other_fee1
     *
     * @return the value of `trade_additional_detail`.cfm_other_fee1
     *
     * @mbggenerated
     */
    public Double getCfmOtherFee1() {
        return cfmOtherFee1;
    }

    /**
     *确认其他费用1
     *`trade_additional_detail`.cfm_other_fee1
     *
     * @param cfmOtherFee1 the value for `trade_additional_detail`.cfm_other_fee1
     *
     * @mbggenerated
     */
    public void setCfmOtherFee1(Double cfmOtherFee1) {
        this.cfmOtherFee1 = cfmOtherFee1;
    }

    /**
     *确认其他费用2
     *`trade_additional_detail`.cfm_other_fee2
     *
     * @return the value of `trade_additional_detail`.cfm_other_fee2
     *
     * @mbggenerated
     */
    public Double getCfmOtherFee2() {
        return cfmOtherFee2;
    }

    /**
     *确认其他费用2
     *`trade_additional_detail`.cfm_other_fee2
     *
     * @param cfmOtherFee2 the value for `trade_additional_detail`.cfm_other_fee2
     *
     * @mbggenerated
     */
    public void setCfmOtherFee2(Double cfmOtherFee2) {
        this.cfmOtherFee2 = cfmOtherFee2;
    }

    /**
     *确认总费用
     *`trade_additional_detail`.cfm_fee
     *
     * @return the value of `trade_additional_detail`.cfm_fee
     *
     * @mbggenerated
     */
    public Double getCfmFee() {
        return cfmFee;
    }

    /**
     *确认总费用
     *`trade_additional_detail`.cfm_fee
     *
     * @param cfmFee the value for `trade_additional_detail`.cfm_fee
     *
     * @mbggenerated
     */
    public void setCfmFee(Double cfmFee) {
        this.cfmFee = cfmFee;
    }

    /**
     *
     *`trade_additional_detail`.update_time
     *
     * @return the value of `trade_additional_detail`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`trade_additional_detail`.update_time
     *
     * @param updateTime the value for `trade_additional_detail`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`trade_additional_detail`.create_time
     *
     * @return the value of `trade_additional_detail`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`trade_additional_detail`.create_time
     *
     * @param createTime the value for `trade_additional_detail`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_additional_detail`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", detailId=").append(detailId);
        sb.append(", additionalId=").append(additionalId);
        sb.append(", dailyDate=").append(dailyDate);
        sb.append(", overTime=").append(overTime);
        sb.append(", overTimePrice=").append(overTimePrice);
        sb.append(", unitTimePrice=").append(unitTimePrice);
        sb.append(", overDistance=").append(overDistance);
        sb.append(", overDistancePrice=").append(overDistancePrice);
        sb.append(", unitDistancePrice=").append(unitDistancePrice);
        sb.append(", overDay=").append(overDay);
        sb.append(", overDayPrice=").append(overDayPrice);
        sb.append(", unitDayPrice=").append(unitDayPrice);
        sb.append(", overWaitTimeCost=").append(overWaitTimeCost);
        sb.append(", otherFee1=").append(otherFee1);
        sb.append(", otherFee2=").append(otherFee2);
        sb.append(", applyfee=").append(applyfee);
        sb.append(", cfmOverTime=").append(cfmOverTime);
        sb.append(", cfmOverDistance=").append(cfmOverDistance);
        sb.append(", cfmOverDay=").append(cfmOverDay);
        sb.append(", cfmOtherFee1=").append(cfmOtherFee1);
        sb.append(", cfmOtherFee2=").append(cfmOtherFee2);
        sb.append(", cfmFee=").append(cfmFee);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}