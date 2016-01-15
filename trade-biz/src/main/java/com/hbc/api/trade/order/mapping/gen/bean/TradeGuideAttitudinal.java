package com.hbc.api.trade.order.mapping.gen.bean;

import java.util.Date;

public class TradeGuideAttitudinal {
    /**
     *  导游Id
     *  所属表字段为`trade_guide_attitudinal`.guide_id
     */
    private String guideId;

    /**
     *  连续失败次数
     *  所属表字段为`trade_guide_attitudinal`.serial_fail_times
     */
    private Integer serialFailTimes;

    /**
     *  连续失败天数
     *  所属表字段为`trade_guide_attitudinal`.serial_fail_days
     */
    private Integer serialFailDays;

    /**
     *  今天失败次数
     *  所属表字段为`trade_guide_attitudinal`.today_fail_times
     */
    private Integer todayFailTimes;

    /**
     *  今天接单成功次数
     *  所属表字段为`trade_guide_attitudinal`.today_deal_times
     */
    private Integer todayDealTimes;

    /**
     *  累计失败次数
     *  所属表字段为`trade_guide_attitudinal`.accumulative_fail_times
     */
    private Integer accumulativeFailTimes;

    /**
     *  累计失败天数
     *  所属表字段为`trade_guide_attitudinal`.accumulative_fail_days
     */
    private Integer accumulativeFailDays;

    /**
     *  累计接单成功数
     *  所属表字段为`trade_guide_attitudinal`.accumulative_deal_orders
     */
    private Integer accumulativeDealOrders;

    /**
     *  累计表态数
     *  所属表字段为`trade_guide_attitudinal`.accumulative_expect_times
     */
    private Integer accumulativeExpectTimes;

    /**
     *  累计表态愿意接单数
     *  所属表字段为`trade_guide_attitudinal`.accumulative_demand_times
     */
    private Integer accumulativeDemandTimes;

    /**
     *  统计开始日期
     *  所属表字段为`trade_guide_attitudinal`.reptdt
     */
    private Date reptdt;

    /**
     *  更新时间
     *  所属表字段为`trade_guide_attitudinal`.update_time
     */
    private Date updateTime;

    /**
     *  创建时间
     *  所属表字段为`trade_guide_attitudinal`.create_time
     */
    private Date createTime;

    /**
     *导游Id
     *`trade_guide_attitudinal`.guide_id
     *
     * @return the value of `trade_guide_attitudinal`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *导游Id
     *`trade_guide_attitudinal`.guide_id
     *
     * @param guideId the value for `trade_guide_attitudinal`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *连续失败次数
     *`trade_guide_attitudinal`.serial_fail_times
     *
     * @return the value of `trade_guide_attitudinal`.serial_fail_times
     *
     * @mbggenerated
     */
    public Integer getSerialFailTimes() {
        return serialFailTimes;
    }

    /**
     *连续失败次数
     *`trade_guide_attitudinal`.serial_fail_times
     *
     * @param serialFailTimes the value for `trade_guide_attitudinal`.serial_fail_times
     *
     * @mbggenerated
     */
    public void setSerialFailTimes(Integer serialFailTimes) {
        this.serialFailTimes = serialFailTimes;
    }

    /**
     *连续失败天数
     *`trade_guide_attitudinal`.serial_fail_days
     *
     * @return the value of `trade_guide_attitudinal`.serial_fail_days
     *
     * @mbggenerated
     */
    public Integer getSerialFailDays() {
        return serialFailDays;
    }

    /**
     *连续失败天数
     *`trade_guide_attitudinal`.serial_fail_days
     *
     * @param serialFailDays the value for `trade_guide_attitudinal`.serial_fail_days
     *
     * @mbggenerated
     */
    public void setSerialFailDays(Integer serialFailDays) {
        this.serialFailDays = serialFailDays;
    }

    /**
     *今天失败次数
     *`trade_guide_attitudinal`.today_fail_times
     *
     * @return the value of `trade_guide_attitudinal`.today_fail_times
     *
     * @mbggenerated
     */
    public Integer getTodayFailTimes() {
        return todayFailTimes;
    }

    /**
     *今天失败次数
     *`trade_guide_attitudinal`.today_fail_times
     *
     * @param todayFailTimes the value for `trade_guide_attitudinal`.today_fail_times
     *
     * @mbggenerated
     */
    public void setTodayFailTimes(Integer todayFailTimes) {
        this.todayFailTimes = todayFailTimes;
    }

    /**
     *今天接单成功次数
     *`trade_guide_attitudinal`.today_deal_times
     *
     * @return the value of `trade_guide_attitudinal`.today_deal_times
     *
     * @mbggenerated
     */
    public Integer getTodayDealTimes() {
        return todayDealTimes;
    }

    /**
     *今天接单成功次数
     *`trade_guide_attitudinal`.today_deal_times
     *
     * @param todayDealTimes the value for `trade_guide_attitudinal`.today_deal_times
     *
     * @mbggenerated
     */
    public void setTodayDealTimes(Integer todayDealTimes) {
        this.todayDealTimes = todayDealTimes;
    }

    /**
     *累计失败次数
     *`trade_guide_attitudinal`.accumulative_fail_times
     *
     * @return the value of `trade_guide_attitudinal`.accumulative_fail_times
     *
     * @mbggenerated
     */
    public Integer getAccumulativeFailTimes() {
        return accumulativeFailTimes;
    }

    /**
     *累计失败次数
     *`trade_guide_attitudinal`.accumulative_fail_times
     *
     * @param accumulativeFailTimes the value for `trade_guide_attitudinal`.accumulative_fail_times
     *
     * @mbggenerated
     */
    public void setAccumulativeFailTimes(Integer accumulativeFailTimes) {
        this.accumulativeFailTimes = accumulativeFailTimes;
    }

    /**
     *累计失败天数
     *`trade_guide_attitudinal`.accumulative_fail_days
     *
     * @return the value of `trade_guide_attitudinal`.accumulative_fail_days
     *
     * @mbggenerated
     */
    public Integer getAccumulativeFailDays() {
        return accumulativeFailDays;
    }

    /**
     *累计失败天数
     *`trade_guide_attitudinal`.accumulative_fail_days
     *
     * @param accumulativeFailDays the value for `trade_guide_attitudinal`.accumulative_fail_days
     *
     * @mbggenerated
     */
    public void setAccumulativeFailDays(Integer accumulativeFailDays) {
        this.accumulativeFailDays = accumulativeFailDays;
    }

    /**
     *累计接单成功数
     *`trade_guide_attitudinal`.accumulative_deal_orders
     *
     * @return the value of `trade_guide_attitudinal`.accumulative_deal_orders
     *
     * @mbggenerated
     */
    public Integer getAccumulativeDealOrders() {
        return accumulativeDealOrders;
    }

    /**
     *累计接单成功数
     *`trade_guide_attitudinal`.accumulative_deal_orders
     *
     * @param accumulativeDealOrders the value for `trade_guide_attitudinal`.accumulative_deal_orders
     *
     * @mbggenerated
     */
    public void setAccumulativeDealOrders(Integer accumulativeDealOrders) {
        this.accumulativeDealOrders = accumulativeDealOrders;
    }

    /**
     *累计表态数
     *`trade_guide_attitudinal`.accumulative_expect_times
     *
     * @return the value of `trade_guide_attitudinal`.accumulative_expect_times
     *
     * @mbggenerated
     */
    public Integer getAccumulativeExpectTimes() {
        return accumulativeExpectTimes;
    }

    /**
     *累计表态数
     *`trade_guide_attitudinal`.accumulative_expect_times
     *
     * @param accumulativeExpectTimes the value for `trade_guide_attitudinal`.accumulative_expect_times
     *
     * @mbggenerated
     */
    public void setAccumulativeExpectTimes(Integer accumulativeExpectTimes) {
        this.accumulativeExpectTimes = accumulativeExpectTimes;
    }

    /**
     *累计表态愿意接单数
     *`trade_guide_attitudinal`.accumulative_demand_times
     *
     * @return the value of `trade_guide_attitudinal`.accumulative_demand_times
     *
     * @mbggenerated
     */
    public Integer getAccumulativeDemandTimes() {
        return accumulativeDemandTimes;
    }

    /**
     *累计表态愿意接单数
     *`trade_guide_attitudinal`.accumulative_demand_times
     *
     * @param accumulativeDemandTimes the value for `trade_guide_attitudinal`.accumulative_demand_times
     *
     * @mbggenerated
     */
    public void setAccumulativeDemandTimes(Integer accumulativeDemandTimes) {
        this.accumulativeDemandTimes = accumulativeDemandTimes;
    }

    /**
     *统计开始日期
     *`trade_guide_attitudinal`.reptdt
     *
     * @return the value of `trade_guide_attitudinal`.reptdt
     *
     * @mbggenerated
     */
    public Date getReptdt() {
        return reptdt;
    }

    /**
     *统计开始日期
     *`trade_guide_attitudinal`.reptdt
     *
     * @param reptdt the value for `trade_guide_attitudinal`.reptdt
     *
     * @mbggenerated
     */
    public void setReptdt(Date reptdt) {
        this.reptdt = reptdt;
    }

    /**
     *更新时间
     *`trade_guide_attitudinal`.update_time
     *
     * @return the value of `trade_guide_attitudinal`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新时间
     *`trade_guide_attitudinal`.update_time
     *
     * @param updateTime the value for `trade_guide_attitudinal`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *创建时间
     *`trade_guide_attitudinal`.create_time
     *
     * @return the value of `trade_guide_attitudinal`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     *`trade_guide_attitudinal`.create_time
     *
     * @param createTime the value for `trade_guide_attitudinal`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_guide_attitudinal`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guideId=").append(guideId);
        sb.append(", serialFailTimes=").append(serialFailTimes);
        sb.append(", serialFailDays=").append(serialFailDays);
        sb.append(", todayFailTimes=").append(todayFailTimes);
        sb.append(", todayDealTimes=").append(todayDealTimes);
        sb.append(", accumulativeFailTimes=").append(accumulativeFailTimes);
        sb.append(", accumulativeFailDays=").append(accumulativeFailDays);
        sb.append(", accumulativeDealOrders=").append(accumulativeDealOrders);
        sb.append(", accumulativeExpectTimes=").append(accumulativeExpectTimes);
        sb.append(", accumulativeDemandTimes=").append(accumulativeDemandTimes);
        sb.append(", reptdt=").append(reptdt);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}