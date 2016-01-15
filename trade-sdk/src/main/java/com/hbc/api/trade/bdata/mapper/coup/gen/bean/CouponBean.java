package com.hbc.api.trade.bdata.mapper.coup.gen.bean;

import java.util.Date;

public class CouponBean {
    /**
     *  
     *  所属表字段为`coupon`.coupon_id
     */
    private String couponId;

    /**
     *  
     *  所属表字段为`coupon`.coupon_batch_id
     */
    private String couponBatchId;

    /**
     *  该优惠券所属礼包id
     *  所属表字段为`coupon`.coupon_package_id
     */
    private String couponPackageId;

    /**
     *  
     *  所属表字段为`coupon`.coupon_code
     */
    private String couponCode;

    /**
     *  对应订单id
     *  所属表字段为`coupon`.order_no
     */
    private String orderNo;

    /**
     *  
     *  所属表字段为`coupon`.coupon_price
     */
    private Integer couponPrice;

    /**
     *  
     *  所属表字段为`coupon`.coupon_discount
     */
    private Integer couponDiscount;

    /**
     *  优惠券密码（暂时无，不check）
     *  所属表字段为`coupon`.password
     */
    private String password;

    /**
     *  
     *  所属表字段为`coupon`.is_read
     */
    private Integer isRead;

    /**
     *  代金券状态：0-初始化（默认）；1-绑定；2-使用；-1-废弃
     *  所属表字段为`coupon`.status
     */
    private Integer status;

    /**
     *  用户绑定类型：1-B端代理；2-C端用户
     *  所属表字段为`coupon`.bind_user_type
     */
    private Integer bindUserType;

    /**
     *  用户绑定id
     *  所属表字段为`coupon`.bind_user_id
     */
    private String bindUserId;

    /**
     *  用户绑定时间
     *  所属表字段为`coupon`.bind_date_time
     */
    private Date bindDateTime;

    /**
     *  订单用户Id
     *  所属表字段为`coupon`.use_user_id
     */
    private String useUserId;

    /**
     *  使用时间
     *  所属表字段为`coupon`.use_date_time
     */
    private Date useDateTime;

    /**
     *  优惠券类型：1-现金券；2-折扣券； 3-包车折扣券
     *  所属表字段为`coupon`.coupon_type
     */
    private Integer couponType;

    /**
     *  转赠次数
     *  所属表字段为`coupon`.transfer_count
     */
    private Integer transferCount;

    /**
     *  转赠人类型：1-B端用户；2-C端用户
     *  所属表字段为`coupon`.from_user_type
     */
    private Integer fromUserType;

    /**
     *  转赠人Id
     *  所属表字段为`coupon`.from_user_id
     */
    private String fromUserId;

    /**
     *  销售id
     *  所属表字段为`coupon`.vendor_id
     */
    private String vendorId;

    /**
     *  销售类型
     *  所属表字段为`coupon`.vendor_type
     */
    private Integer vendorType;

    /**
     *  
     *  所属表字段为`coupon`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`coupon`.create_time
     */
    private Date createTime;

    /**
     *
     *`coupon`.coupon_id
     *
     * @return the value of `coupon`.coupon_id
     *
     * @mbggenerated
     */
    public String getCouponId() {
        return couponId;
    }

    /**
     *
     *`coupon`.coupon_id
     *
     * @param couponId the value for `coupon`.coupon_id
     *
     * @mbggenerated
     */
    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }

    /**
     *
     *`coupon`.coupon_batch_id
     *
     * @return the value of `coupon`.coupon_batch_id
     *
     * @mbggenerated
     */
    public String getCouponBatchId() {
        return couponBatchId;
    }

    /**
     *
     *`coupon`.coupon_batch_id
     *
     * @param couponBatchId the value for `coupon`.coupon_batch_id
     *
     * @mbggenerated
     */
    public void setCouponBatchId(String couponBatchId) {
        this.couponBatchId = couponBatchId == null ? null : couponBatchId.trim();
    }

    /**
     *该优惠券所属礼包id
     *`coupon`.coupon_package_id
     *
     * @return the value of `coupon`.coupon_package_id
     *
     * @mbggenerated
     */
    public String getCouponPackageId() {
        return couponPackageId;
    }

    /**
     *该优惠券所属礼包id
     *`coupon`.coupon_package_id
     *
     * @param couponPackageId the value for `coupon`.coupon_package_id
     *
     * @mbggenerated
     */
    public void setCouponPackageId(String couponPackageId) {
        this.couponPackageId = couponPackageId == null ? null : couponPackageId.trim();
    }

    /**
     *
     *`coupon`.coupon_code
     *
     * @return the value of `coupon`.coupon_code
     *
     * @mbggenerated
     */
    public String getCouponCode() {
        return couponCode;
    }

    /**
     *
     *`coupon`.coupon_code
     *
     * @param couponCode the value for `coupon`.coupon_code
     *
     * @mbggenerated
     */
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode == null ? null : couponCode.trim();
    }

    /**
     *对应订单id
     *`coupon`.order_no
     *
     * @return the value of `coupon`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *对应订单id
     *`coupon`.order_no
     *
     * @param orderNo the value for `coupon`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *
     *`coupon`.coupon_price
     *
     * @return the value of `coupon`.coupon_price
     *
     * @mbggenerated
     */
    public Integer getCouponPrice() {
        return couponPrice;
    }

    /**
     *
     *`coupon`.coupon_price
     *
     * @param couponPrice the value for `coupon`.coupon_price
     *
     * @mbggenerated
     */
    public void setCouponPrice(Integer couponPrice) {
        this.couponPrice = couponPrice;
    }

    /**
     *
     *`coupon`.coupon_discount
     *
     * @return the value of `coupon`.coupon_discount
     *
     * @mbggenerated
     */
    public Integer getCouponDiscount() {
        return couponDiscount;
    }

    /**
     *
     *`coupon`.coupon_discount
     *
     * @param couponDiscount the value for `coupon`.coupon_discount
     *
     * @mbggenerated
     */
    public void setCouponDiscount(Integer couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    /**
     *优惠券密码（暂时无，不check）
     *`coupon`.password
     *
     * @return the value of `coupon`.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     *优惠券密码（暂时无，不check）
     *`coupon`.password
     *
     * @param password the value for `coupon`.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     *
     *`coupon`.is_read
     *
     * @return the value of `coupon`.is_read
     *
     * @mbggenerated
     */
    public Integer getIsRead() {
        return isRead;
    }

    /**
     *
     *`coupon`.is_read
     *
     * @param isRead the value for `coupon`.is_read
     *
     * @mbggenerated
     */
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    /**
     *代金券状态：0-初始化（默认）；1-绑定；2-使用；-1-废弃
     *`coupon`.status
     *
     * @return the value of `coupon`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *代金券状态：0-初始化（默认）；1-绑定；2-使用；-1-废弃
     *`coupon`.status
     *
     * @param status the value for `coupon`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *用户绑定类型：1-B端代理；2-C端用户
     *`coupon`.bind_user_type
     *
     * @return the value of `coupon`.bind_user_type
     *
     * @mbggenerated
     */
    public Integer getBindUserType() {
        return bindUserType;
    }

    /**
     *用户绑定类型：1-B端代理；2-C端用户
     *`coupon`.bind_user_type
     *
     * @param bindUserType the value for `coupon`.bind_user_type
     *
     * @mbggenerated
     */
    public void setBindUserType(Integer bindUserType) {
        this.bindUserType = bindUserType;
    }

    /**
     *用户绑定id
     *`coupon`.bind_user_id
     *
     * @return the value of `coupon`.bind_user_id
     *
     * @mbggenerated
     */
    public String getBindUserId() {
        return bindUserId;
    }

    /**
     *用户绑定id
     *`coupon`.bind_user_id
     *
     * @param bindUserId the value for `coupon`.bind_user_id
     *
     * @mbggenerated
     */
    public void setBindUserId(String bindUserId) {
        this.bindUserId = bindUserId == null ? null : bindUserId.trim();
    }

    /**
     *用户绑定时间
     *`coupon`.bind_date_time
     *
     * @return the value of `coupon`.bind_date_time
     *
     * @mbggenerated
     */
    public Date getBindDateTime() {
        return bindDateTime;
    }

    /**
     *用户绑定时间
     *`coupon`.bind_date_time
     *
     * @param bindDateTime the value for `coupon`.bind_date_time
     *
     * @mbggenerated
     */
    public void setBindDateTime(Date bindDateTime) {
        this.bindDateTime = bindDateTime;
    }

    /**
     *订单用户Id
     *`coupon`.use_user_id
     *
     * @return the value of `coupon`.use_user_id
     *
     * @mbggenerated
     */
    public String getUseUserId() {
        return useUserId;
    }

    /**
     *订单用户Id
     *`coupon`.use_user_id
     *
     * @param useUserId the value for `coupon`.use_user_id
     *
     * @mbggenerated
     */
    public void setUseUserId(String useUserId) {
        this.useUserId = useUserId == null ? null : useUserId.trim();
    }

    /**
     *使用时间
     *`coupon`.use_date_time
     *
     * @return the value of `coupon`.use_date_time
     *
     * @mbggenerated
     */
    public Date getUseDateTime() {
        return useDateTime;
    }

    /**
     *使用时间
     *`coupon`.use_date_time
     *
     * @param useDateTime the value for `coupon`.use_date_time
     *
     * @mbggenerated
     */
    public void setUseDateTime(Date useDateTime) {
        this.useDateTime = useDateTime;
    }

    /**
     *优惠券类型：1-现金券；2-折扣券； 3-包车折扣券
     *`coupon`.coupon_type
     *
     * @return the value of `coupon`.coupon_type
     *
     * @mbggenerated
     */
    public Integer getCouponType() {
        return couponType;
    }

    /**
     *优惠券类型：1-现金券；2-折扣券； 3-包车折扣券
     *`coupon`.coupon_type
     *
     * @param couponType the value for `coupon`.coupon_type
     *
     * @mbggenerated
     */
    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    /**
     *转赠次数
     *`coupon`.transfer_count
     *
     * @return the value of `coupon`.transfer_count
     *
     * @mbggenerated
     */
    public Integer getTransferCount() {
        return transferCount;
    }

    /**
     *转赠次数
     *`coupon`.transfer_count
     *
     * @param transferCount the value for `coupon`.transfer_count
     *
     * @mbggenerated
     */
    public void setTransferCount(Integer transferCount) {
        this.transferCount = transferCount;
    }

    /**
     *转赠人类型：1-B端用户；2-C端用户
     *`coupon`.from_user_type
     *
     * @return the value of `coupon`.from_user_type
     *
     * @mbggenerated
     */
    public Integer getFromUserType() {
        return fromUserType;
    }

    /**
     *转赠人类型：1-B端用户；2-C端用户
     *`coupon`.from_user_type
     *
     * @param fromUserType the value for `coupon`.from_user_type
     *
     * @mbggenerated
     */
    public void setFromUserType(Integer fromUserType) {
        this.fromUserType = fromUserType;
    }

    /**
     *转赠人Id
     *`coupon`.from_user_id
     *
     * @return the value of `coupon`.from_user_id
     *
     * @mbggenerated
     */
    public String getFromUserId() {
        return fromUserId;
    }

    /**
     *转赠人Id
     *`coupon`.from_user_id
     *
     * @param fromUserId the value for `coupon`.from_user_id
     *
     * @mbggenerated
     */
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId == null ? null : fromUserId.trim();
    }

    /**
     *销售id
     *`coupon`.vendor_id
     *
     * @return the value of `coupon`.vendor_id
     *
     * @mbggenerated
     */
    public String getVendorId() {
        return vendorId;
    }

    /**
     *销售id
     *`coupon`.vendor_id
     *
     * @param vendorId the value for `coupon`.vendor_id
     *
     * @mbggenerated
     */
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId == null ? null : vendorId.trim();
    }

    /**
     *销售类型
     *`coupon`.vendor_type
     *
     * @return the value of `coupon`.vendor_type
     *
     * @mbggenerated
     */
    public Integer getVendorType() {
        return vendorType;
    }

    /**
     *销售类型
     *`coupon`.vendor_type
     *
     * @param vendorType the value for `coupon`.vendor_type
     *
     * @mbggenerated
     */
    public void setVendorType(Integer vendorType) {
        this.vendorType = vendorType;
    }

    /**
     *
     *`coupon`.update_time
     *
     * @return the value of `coupon`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`coupon`.update_time
     *
     * @param updateTime the value for `coupon`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`coupon`.create_time
     *
     * @return the value of `coupon`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`coupon`.create_time
     *
     * @param createTime the value for `coupon`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", couponId=").append(couponId);
        sb.append(", couponBatchId=").append(couponBatchId);
        sb.append(", couponPackageId=").append(couponPackageId);
        sb.append(", couponCode=").append(couponCode);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", couponPrice=").append(couponPrice);
        sb.append(", couponDiscount=").append(couponDiscount);
        sb.append(", password=").append(password);
        sb.append(", isRead=").append(isRead);
        sb.append(", status=").append(status);
        sb.append(", bindUserType=").append(bindUserType);
        sb.append(", bindUserId=").append(bindUserId);
        sb.append(", bindDateTime=").append(bindDateTime);
        sb.append(", useUserId=").append(useUserId);
        sb.append(", useDateTime=").append(useDateTime);
        sb.append(", couponType=").append(couponType);
        sb.append(", transferCount=").append(transferCount);
        sb.append(", fromUserType=").append(fromUserType);
        sb.append(", fromUserId=").append(fromUserId);
        sb.append(", vendorId=").append(vendorId);
        sb.append(", vendorType=").append(vendorType);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}