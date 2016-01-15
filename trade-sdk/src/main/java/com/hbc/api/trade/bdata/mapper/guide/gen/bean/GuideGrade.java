package com.hbc.api.trade.bdata.mapper.guide.gen.bean;

import java.util.Date;

public class GuideGrade {
    /**
     *  
     *  所属表字段为`guide_grade`.guide_id
     */
    private String guideId;

    /**
     *  起评日期
     *  所属表字段为`guide_grade`.start_date
     */
    private Date startDate;

    /**
     *  导游预评级分数
     *  所属表字段为`guide_grade`.pre_assessment
     */
    private Float preAssessment;

    /**
     *  系统评级分数
     *  所属表字段为`guide_grade`.sys_assessment
     */
    private Float sysAssessment;

    /**
     *  抢单倍率（默认为1）
     *  所属表字段为`guide_grade`.grab_order_rate
     */
    private Integer grabOrderRate;

    /**
     *  重置评分次数（默认为0）
     *  所属表字段为`guide_grade`.reset_count
     */
    private Integer resetCount;

    /**
     *  派单优先级：3-低级；2-中级；1-高级（默认）
     *  所属表字段为`guide_grade`.dispatch_priority
     */
    private Integer dispatchPriority;

    /**
     *  评分数据源中接机的数量
     *  所属表字段为`guide_grade`.pickup_base
     */
    private Integer pickupBase;

    /**
     *  评分数据源中送机的数量
     *  所属表字段为`guide_grade`.transfer_base
     */
    private Integer transferBase;

    /**
     *  评分数据源中包车的数量
     *  所属表字段为`guide_grade`.daily_base
     */
    private Integer dailyBase;

    /**
     *  评分数据源中单次用车的数量
     *  所属表字段为`guide_grade`.per_use_base
     */
    private Integer perUseBase;

    /**
     *  总的Base，包车按比例转换成接送机
     *  所属表字段为`guide_grade`.total_base
     */
    private Integer totalBase;

    /**
     *  
     *  所属表字段为`guide_grade`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`guide_grade`.create_time
     */
    private Date createTime;

    /**
     *
     *`guide_grade`.guide_id
     *
     * @return the value of `guide_grade`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *
     *`guide_grade`.guide_id
     *
     * @param guideId the value for `guide_grade`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *起评日期
     *`guide_grade`.start_date
     *
     * @return the value of `guide_grade`.start_date
     *
     * @mbggenerated
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     *起评日期
     *`guide_grade`.start_date
     *
     * @param startDate the value for `guide_grade`.start_date
     *
     * @mbggenerated
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     *导游预评级分数
     *`guide_grade`.pre_assessment
     *
     * @return the value of `guide_grade`.pre_assessment
     *
     * @mbggenerated
     */
    public Float getPreAssessment() {
        return preAssessment;
    }

    /**
     *导游预评级分数
     *`guide_grade`.pre_assessment
     *
     * @param preAssessment the value for `guide_grade`.pre_assessment
     *
     * @mbggenerated
     */
    public void setPreAssessment(Float preAssessment) {
        this.preAssessment = preAssessment;
    }

    /**
     *系统评级分数
     *`guide_grade`.sys_assessment
     *
     * @return the value of `guide_grade`.sys_assessment
     *
     * @mbggenerated
     */
    public Float getSysAssessment() {
        return sysAssessment;
    }

    /**
     *系统评级分数
     *`guide_grade`.sys_assessment
     *
     * @param sysAssessment the value for `guide_grade`.sys_assessment
     *
     * @mbggenerated
     */
    public void setSysAssessment(Float sysAssessment) {
        this.sysAssessment = sysAssessment;
    }

    /**
     *抢单倍率（默认为1）
     *`guide_grade`.grab_order_rate
     *
     * @return the value of `guide_grade`.grab_order_rate
     *
     * @mbggenerated
     */
    public Integer getGrabOrderRate() {
        return grabOrderRate;
    }

    /**
     *抢单倍率（默认为1）
     *`guide_grade`.grab_order_rate
     *
     * @param grabOrderRate the value for `guide_grade`.grab_order_rate
     *
     * @mbggenerated
     */
    public void setGrabOrderRate(Integer grabOrderRate) {
        this.grabOrderRate = grabOrderRate;
    }

    /**
     *重置评分次数（默认为0）
     *`guide_grade`.reset_count
     *
     * @return the value of `guide_grade`.reset_count
     *
     * @mbggenerated
     */
    public Integer getResetCount() {
        return resetCount;
    }

    /**
     *重置评分次数（默认为0）
     *`guide_grade`.reset_count
     *
     * @param resetCount the value for `guide_grade`.reset_count
     *
     * @mbggenerated
     */
    public void setResetCount(Integer resetCount) {
        this.resetCount = resetCount;
    }

    /**
     *派单优先级：3-低级；2-中级；1-高级（默认）
     *`guide_grade`.dispatch_priority
     *
     * @return the value of `guide_grade`.dispatch_priority
     *
     * @mbggenerated
     */
    public Integer getDispatchPriority() {
        return dispatchPriority;
    }

    /**
     *派单优先级：3-低级；2-中级；1-高级（默认）
     *`guide_grade`.dispatch_priority
     *
     * @param dispatchPriority the value for `guide_grade`.dispatch_priority
     *
     * @mbggenerated
     */
    public void setDispatchPriority(Integer dispatchPriority) {
        this.dispatchPriority = dispatchPriority;
    }

    /**
     *评分数据源中接机的数量
     *`guide_grade`.pickup_base
     *
     * @return the value of `guide_grade`.pickup_base
     *
     * @mbggenerated
     */
    public Integer getPickupBase() {
        return pickupBase;
    }

    /**
     *评分数据源中接机的数量
     *`guide_grade`.pickup_base
     *
     * @param pickupBase the value for `guide_grade`.pickup_base
     *
     * @mbggenerated
     */
    public void setPickupBase(Integer pickupBase) {
        this.pickupBase = pickupBase;
    }

    /**
     *评分数据源中送机的数量
     *`guide_grade`.transfer_base
     *
     * @return the value of `guide_grade`.transfer_base
     *
     * @mbggenerated
     */
    public Integer getTransferBase() {
        return transferBase;
    }

    /**
     *评分数据源中送机的数量
     *`guide_grade`.transfer_base
     *
     * @param transferBase the value for `guide_grade`.transfer_base
     *
     * @mbggenerated
     */
    public void setTransferBase(Integer transferBase) {
        this.transferBase = transferBase;
    }

    /**
     *评分数据源中包车的数量
     *`guide_grade`.daily_base
     *
     * @return the value of `guide_grade`.daily_base
     *
     * @mbggenerated
     */
    public Integer getDailyBase() {
        return dailyBase;
    }

    /**
     *评分数据源中包车的数量
     *`guide_grade`.daily_base
     *
     * @param dailyBase the value for `guide_grade`.daily_base
     *
     * @mbggenerated
     */
    public void setDailyBase(Integer dailyBase) {
        this.dailyBase = dailyBase;
    }

    /**
     *评分数据源中单次用车的数量
     *`guide_grade`.per_use_base
     *
     * @return the value of `guide_grade`.per_use_base
     *
     * @mbggenerated
     */
    public Integer getPerUseBase() {
        return perUseBase;
    }

    /**
     *评分数据源中单次用车的数量
     *`guide_grade`.per_use_base
     *
     * @param perUseBase the value for `guide_grade`.per_use_base
     *
     * @mbggenerated
     */
    public void setPerUseBase(Integer perUseBase) {
        this.perUseBase = perUseBase;
    }

    /**
     *总的Base，包车按比例转换成接送机
     *`guide_grade`.total_base
     *
     * @return the value of `guide_grade`.total_base
     *
     * @mbggenerated
     */
    public Integer getTotalBase() {
        return totalBase;
    }

    /**
     *总的Base，包车按比例转换成接送机
     *`guide_grade`.total_base
     *
     * @param totalBase the value for `guide_grade`.total_base
     *
     * @mbggenerated
     */
    public void setTotalBase(Integer totalBase) {
        this.totalBase = totalBase;
    }

    /**
     *
     *`guide_grade`.update_time
     *
     * @return the value of `guide_grade`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`guide_grade`.update_time
     *
     * @param updateTime the value for `guide_grade`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`guide_grade`.create_time
     *
     * @return the value of `guide_grade`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`guide_grade`.create_time
     *
     * @param createTime the value for `guide_grade`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_grade`
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
        sb.append(", startDate=").append(startDate);
        sb.append(", preAssessment=").append(preAssessment);
        sb.append(", sysAssessment=").append(sysAssessment);
        sb.append(", grabOrderRate=").append(grabOrderRate);
        sb.append(", resetCount=").append(resetCount);
        sb.append(", dispatchPriority=").append(dispatchPriority);
        sb.append(", pickupBase=").append(pickupBase);
        sb.append(", transferBase=").append(transferBase);
        sb.append(", dailyBase=").append(dailyBase);
        sb.append(", perUseBase=").append(perUseBase);
        sb.append(", totalBase=").append(totalBase);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}