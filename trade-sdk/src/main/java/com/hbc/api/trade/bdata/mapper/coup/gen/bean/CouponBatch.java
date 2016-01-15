package com.hbc.api.trade.bdata.mapper.coup.gen.bean;

import java.util.Date;

public class CouponBatch {
    /**
     *  
     *  所属表字段为`coupon_batch`.coupon_batch_id
     */
    private String couponBatchId;

    /**
     *  
     *  所属表字段为`coupon_batch`.batch_no
     */
    private String batchNo;

    /**
     *  
     *  所属表字段为`coupon_batch`.batch_name
     */
    private String batchName;

    /**
     *  
     *  所属表字段为`coupon_batch`.batch_channel
     */
    private String batchChannel;

    /**
     *  
     *  所属表字段为`coupon_batch`.coupon_type
     */
    private Integer couponType;

    /**
     *  
     *  所属表字段为`coupon_batch`.coupon_total
     */
    private Integer couponTotal;

    /**
     *  
     *  所属表字段为`coupon_batch`.coupon_price
     */
    private Integer couponPrice;

    /**
     *  
     *  所属表字段为`coupon_batch`.coupon_discount
     */
    private Integer couponDiscount;

    /**
     *  包车折扣券天数限制
     *  所属表字段为`coupon_batch`.days_limit
     */
    private Integer daysLimit;

    /**
     *  
     *  所属表字段为`coupon_batch`.end_date
     */
    private Date endDate;

    /**
     *  
     *  所属表字段为`coupon_batch`.meet_price
     */
    private Integer meetPrice;

    /**
     *  
     *  所属表字段为`coupon_batch`.distance_upper_limit
     */
    private Integer distanceUpperLimit;

    /**
     *  
     *  所属表字段为`coupon_batch`.duration_upper_limit
     */
    private Integer durationUpperLimit;

    /**
     *  
     *  所属表字段为`coupon_batch`.remark
     */
    private String remark;

    /**
     *  
     *  所属表字段为`coupon_batch`.coupon_pic
     */
    private String couponPic;

    /**
     *  
     *  所属表字段为`coupon_batch`.coupon_url
     */
    private String couponUrl;

    /**
     *  
     *  所属表字段为`coupon_batch`.bind_count
     */
    private Integer bindCount;

    /**
     *  
     *  所属表字段为`coupon_batch`.use_count
     */
    private Integer useCount;

    /**
     *  
     *  所属表字段为`coupon_batch`.allow_transfer_count
     */
    private Integer allowTransferCount;

    /**
     *  
     *  所属表字段为`coupon_batch`.coupon_scop_json
     */
    private String couponScopJson;

    /**
     *  冗余，查询条件用
     *  所属表字段为`coupon_batch`.district_names
     */
    private String districtNames;

    /**
     *  冗余查询条件用
     *  所属表字段为`coupon_batch`.crop_types
     */
    private String cropTypes;

    /**
     *  
     *  所属表字段为`coupon_batch`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`coupon_batch`.create_time
     */
    private Date createTime;

    /**
     *
     *`coupon_batch`.coupon_batch_id
     *
     * @return the value of `coupon_batch`.coupon_batch_id
     *
     * @mbggenerated
     */
    public String getCouponBatchId() {
        return couponBatchId;
    }

    /**
     *
     *`coupon_batch`.coupon_batch_id
     *
     * @param couponBatchId the value for `coupon_batch`.coupon_batch_id
     *
     * @mbggenerated
     */
    public void setCouponBatchId(String couponBatchId) {
        this.couponBatchId = couponBatchId == null ? null : couponBatchId.trim();
    }

    /**
     *
     *`coupon_batch`.batch_no
     *
     * @return the value of `coupon_batch`.batch_no
     *
     * @mbggenerated
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     *
     *`coupon_batch`.batch_no
     *
     * @param batchNo the value for `coupon_batch`.batch_no
     *
     * @mbggenerated
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    /**
     *
     *`coupon_batch`.batch_name
     *
     * @return the value of `coupon_batch`.batch_name
     *
     * @mbggenerated
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     *
     *`coupon_batch`.batch_name
     *
     * @param batchName the value for `coupon_batch`.batch_name
     *
     * @mbggenerated
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName == null ? null : batchName.trim();
    }

    /**
     *
     *`coupon_batch`.batch_channel
     *
     * @return the value of `coupon_batch`.batch_channel
     *
     * @mbggenerated
     */
    public String getBatchChannel() {
        return batchChannel;
    }

    /**
     *
     *`coupon_batch`.batch_channel
     *
     * @param batchChannel the value for `coupon_batch`.batch_channel
     *
     * @mbggenerated
     */
    public void setBatchChannel(String batchChannel) {
        this.batchChannel = batchChannel == null ? null : batchChannel.trim();
    }

    /**
     *
     *`coupon_batch`.coupon_type
     *
     * @return the value of `coupon_batch`.coupon_type
     *
     * @mbggenerated
     */
    public Integer getCouponType() {
        return couponType;
    }

    /**
     *
     *`coupon_batch`.coupon_type
     *
     * @param couponType the value for `coupon_batch`.coupon_type
     *
     * @mbggenerated
     */
    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    /**
     *
     *`coupon_batch`.coupon_total
     *
     * @return the value of `coupon_batch`.coupon_total
     *
     * @mbggenerated
     */
    public Integer getCouponTotal() {
        return couponTotal;
    }

    /**
     *
     *`coupon_batch`.coupon_total
     *
     * @param couponTotal the value for `coupon_batch`.coupon_total
     *
     * @mbggenerated
     */
    public void setCouponTotal(Integer couponTotal) {
        this.couponTotal = couponTotal;
    }

    /**
     *
     *`coupon_batch`.coupon_price
     *
     * @return the value of `coupon_batch`.coupon_price
     *
     * @mbggenerated
     */
    public Integer getCouponPrice() {
        return couponPrice;
    }

    /**
     *
     *`coupon_batch`.coupon_price
     *
     * @param couponPrice the value for `coupon_batch`.coupon_price
     *
     * @mbggenerated
     */
    public void setCouponPrice(Integer couponPrice) {
        this.couponPrice = couponPrice;
    }

    /**
     *
     *`coupon_batch`.coupon_discount
     *
     * @return the value of `coupon_batch`.coupon_discount
     *
     * @mbggenerated
     */
    public Integer getCouponDiscount() {
        return couponDiscount;
    }

    /**
     *
     *`coupon_batch`.coupon_discount
     *
     * @param couponDiscount the value for `coupon_batch`.coupon_discount
     *
     * @mbggenerated
     */
    public void setCouponDiscount(Integer couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    /**
     *包车折扣券天数限制
     *`coupon_batch`.days_limit
     *
     * @return the value of `coupon_batch`.days_limit
     *
     * @mbggenerated
     */
    public Integer getDaysLimit() {
        return daysLimit;
    }

    /**
     *包车折扣券天数限制
     *`coupon_batch`.days_limit
     *
     * @param daysLimit the value for `coupon_batch`.days_limit
     *
     * @mbggenerated
     */
    public void setDaysLimit(Integer daysLimit) {
        this.daysLimit = daysLimit;
    }

    /**
     *
     *`coupon_batch`.end_date
     *
     * @return the value of `coupon_batch`.end_date
     *
     * @mbggenerated
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     *
     *`coupon_batch`.end_date
     *
     * @param endDate the value for `coupon_batch`.end_date
     *
     * @mbggenerated
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     *
     *`coupon_batch`.meet_price
     *
     * @return the value of `coupon_batch`.meet_price
     *
     * @mbggenerated
     */
    public Integer getMeetPrice() {
        return meetPrice;
    }

    /**
     *
     *`coupon_batch`.meet_price
     *
     * @param meetPrice the value for `coupon_batch`.meet_price
     *
     * @mbggenerated
     */
    public void setMeetPrice(Integer meetPrice) {
        this.meetPrice = meetPrice;
    }

    /**
     *
     *`coupon_batch`.distance_upper_limit
     *
     * @return the value of `coupon_batch`.distance_upper_limit
     *
     * @mbggenerated
     */
    public Integer getDistanceUpperLimit() {
        return distanceUpperLimit;
    }

    /**
     *
     *`coupon_batch`.distance_upper_limit
     *
     * @param distanceUpperLimit the value for `coupon_batch`.distance_upper_limit
     *
     * @mbggenerated
     */
    public void setDistanceUpperLimit(Integer distanceUpperLimit) {
        this.distanceUpperLimit = distanceUpperLimit;
    }

    /**
     *
     *`coupon_batch`.duration_upper_limit
     *
     * @return the value of `coupon_batch`.duration_upper_limit
     *
     * @mbggenerated
     */
    public Integer getDurationUpperLimit() {
        return durationUpperLimit;
    }

    /**
     *
     *`coupon_batch`.duration_upper_limit
     *
     * @param durationUpperLimit the value for `coupon_batch`.duration_upper_limit
     *
     * @mbggenerated
     */
    public void setDurationUpperLimit(Integer durationUpperLimit) {
        this.durationUpperLimit = durationUpperLimit;
    }

    /**
     *
     *`coupon_batch`.remark
     *
     * @return the value of `coupon_batch`.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     *
     *`coupon_batch`.remark
     *
     * @param remark the value for `coupon_batch`.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     *
     *`coupon_batch`.coupon_pic
     *
     * @return the value of `coupon_batch`.coupon_pic
     *
     * @mbggenerated
     */
    public String getCouponPic() {
        return couponPic;
    }

    /**
     *
     *`coupon_batch`.coupon_pic
     *
     * @param couponPic the value for `coupon_batch`.coupon_pic
     *
     * @mbggenerated
     */
    public void setCouponPic(String couponPic) {
        this.couponPic = couponPic == null ? null : couponPic.trim();
    }

    /**
     *
     *`coupon_batch`.coupon_url
     *
     * @return the value of `coupon_batch`.coupon_url
     *
     * @mbggenerated
     */
    public String getCouponUrl() {
        return couponUrl;
    }

    /**
     *
     *`coupon_batch`.coupon_url
     *
     * @param couponUrl the value for `coupon_batch`.coupon_url
     *
     * @mbggenerated
     */
    public void setCouponUrl(String couponUrl) {
        this.couponUrl = couponUrl == null ? null : couponUrl.trim();
    }

    /**
     *
     *`coupon_batch`.bind_count
     *
     * @return the value of `coupon_batch`.bind_count
     *
     * @mbggenerated
     */
    public Integer getBindCount() {
        return bindCount;
    }

    /**
     *
     *`coupon_batch`.bind_count
     *
     * @param bindCount the value for `coupon_batch`.bind_count
     *
     * @mbggenerated
     */
    public void setBindCount(Integer bindCount) {
        this.bindCount = bindCount;
    }

    /**
     *
     *`coupon_batch`.use_count
     *
     * @return the value of `coupon_batch`.use_count
     *
     * @mbggenerated
     */
    public Integer getUseCount() {
        return useCount;
    }

    /**
     *
     *`coupon_batch`.use_count
     *
     * @param useCount the value for `coupon_batch`.use_count
     *
     * @mbggenerated
     */
    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    /**
     *
     *`coupon_batch`.allow_transfer_count
     *
     * @return the value of `coupon_batch`.allow_transfer_count
     *
     * @mbggenerated
     */
    public Integer getAllowTransferCount() {
        return allowTransferCount;
    }

    /**
     *
     *`coupon_batch`.allow_transfer_count
     *
     * @param allowTransferCount the value for `coupon_batch`.allow_transfer_count
     *
     * @mbggenerated
     */
    public void setAllowTransferCount(Integer allowTransferCount) {
        this.allowTransferCount = allowTransferCount;
    }

    /**
     *
     *`coupon_batch`.coupon_scop_json
     *
     * @return the value of `coupon_batch`.coupon_scop_json
     *
     * @mbggenerated
     */
    public String getCouponScopJson() {
        return couponScopJson;
    }

    /**
     *
     *`coupon_batch`.coupon_scop_json
     *
     * @param couponScopJson the value for `coupon_batch`.coupon_scop_json
     *
     * @mbggenerated
     */
    public void setCouponScopJson(String couponScopJson) {
        this.couponScopJson = couponScopJson == null ? null : couponScopJson.trim();
    }

    /**
     *冗余，查询条件用
     *`coupon_batch`.district_names
     *
     * @return the value of `coupon_batch`.district_names
     *
     * @mbggenerated
     */
    public String getDistrictNames() {
        return districtNames;
    }

    /**
     *冗余，查询条件用
     *`coupon_batch`.district_names
     *
     * @param districtNames the value for `coupon_batch`.district_names
     *
     * @mbggenerated
     */
    public void setDistrictNames(String districtNames) {
        this.districtNames = districtNames == null ? null : districtNames.trim();
    }

    /**
     *冗余查询条件用
     *`coupon_batch`.crop_types
     *
     * @return the value of `coupon_batch`.crop_types
     *
     * @mbggenerated
     */
    public String getCropTypes() {
        return cropTypes;
    }

    /**
     *冗余查询条件用
     *`coupon_batch`.crop_types
     *
     * @param cropTypes the value for `coupon_batch`.crop_types
     *
     * @mbggenerated
     */
    public void setCropTypes(String cropTypes) {
        this.cropTypes = cropTypes == null ? null : cropTypes.trim();
    }

    /**
     *
     *`coupon_batch`.update_time
     *
     * @return the value of `coupon_batch`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`coupon_batch`.update_time
     *
     * @param updateTime the value for `coupon_batch`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`coupon_batch`.create_time
     *
     * @return the value of `coupon_batch`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`coupon_batch`.create_time
     *
     * @param createTime the value for `coupon_batch`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_batch`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", couponBatchId=").append(couponBatchId);
        sb.append(", batchNo=").append(batchNo);
        sb.append(", batchName=").append(batchName);
        sb.append(", batchChannel=").append(batchChannel);
        sb.append(", couponType=").append(couponType);
        sb.append(", couponTotal=").append(couponTotal);
        sb.append(", couponPrice=").append(couponPrice);
        sb.append(", couponDiscount=").append(couponDiscount);
        sb.append(", daysLimit=").append(daysLimit);
        sb.append(", endDate=").append(endDate);
        sb.append(", meetPrice=").append(meetPrice);
        sb.append(", distanceUpperLimit=").append(distanceUpperLimit);
        sb.append(", durationUpperLimit=").append(durationUpperLimit);
        sb.append(", remark=").append(remark);
        sb.append(", couponPic=").append(couponPic);
        sb.append(", couponUrl=").append(couponUrl);
        sb.append(", bindCount=").append(bindCount);
        sb.append(", useCount=").append(useCount);
        sb.append(", allowTransferCount=").append(allowTransferCount);
        sb.append(", couponScopJson=").append(couponScopJson);
        sb.append(", districtNames=").append(districtNames);
        sb.append(", cropTypes=").append(cropTypes);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}