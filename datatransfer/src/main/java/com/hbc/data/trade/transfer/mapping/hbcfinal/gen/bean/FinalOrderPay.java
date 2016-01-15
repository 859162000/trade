package com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean;

import java.util.Date;

public class FinalOrderPay {
    /**
     *  
     *  所属表字段为`orderpay`.orderId
     */
    private String orderid;

    /**
     *  
     *  所属表字段为`orderpay`.payType
     */
    private Integer paytype;

    /**
     *  
     *  所属表字段为`orderpay`.orderPrice
     */
    private Integer orderprice;

    /**
     *  
     *  所属表字段为`orderpay`.costPrice
     */
    private Integer costprice;

    /**
     *  
     *  所属表字段为`orderpay`.actualPay
     */
    private Integer actualpay;

    /**
     *  
     *  所属表字段为`orderpay`.couponPay
     */
    private Integer couponpay;

    /**
     *  
     *  所属表字段为`orderpay`.refundPrice
     */
    private Integer refundprice;

    /**
     *  
     *  所属表字段为`orderpay`.couponId
     */
    private String couponid;

    /**
     *  
     *  所属表字段为`orderpay`.updated_at
     */
    private Date updatedAt;

    /**
     *  
     *  所属表字段为`orderpay`.created_at
     */
    private Date createdAt;

    /**
     *
     *`orderpay`.orderId
     *
     * @return the value of `orderpay`.orderId
     *
     * @mbggenerated
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     *
     *`orderpay`.orderId
     *
     * @param orderid the value for `orderpay`.orderId
     *
     * @mbggenerated
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    /**
     *
     *`orderpay`.payType
     *
     * @return the value of `orderpay`.payType
     *
     * @mbggenerated
     */
    public Integer getPaytype() {
        return paytype;
    }

    /**
     *
     *`orderpay`.payType
     *
     * @param paytype the value for `orderpay`.payType
     *
     * @mbggenerated
     */
    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    /**
     *
     *`orderpay`.orderPrice
     *
     * @return the value of `orderpay`.orderPrice
     *
     * @mbggenerated
     */
    public Integer getOrderprice() {
        return orderprice;
    }

    /**
     *
     *`orderpay`.orderPrice
     *
     * @param orderprice the value for `orderpay`.orderPrice
     *
     * @mbggenerated
     */
    public void setOrderprice(Integer orderprice) {
        this.orderprice = orderprice;
    }

    /**
     *
     *`orderpay`.costPrice
     *
     * @return the value of `orderpay`.costPrice
     *
     * @mbggenerated
     */
    public Integer getCostprice() {
        return costprice;
    }

    /**
     *
     *`orderpay`.costPrice
     *
     * @param costprice the value for `orderpay`.costPrice
     *
     * @mbggenerated
     */
    public void setCostprice(Integer costprice) {
        this.costprice = costprice;
    }

    /**
     *
     *`orderpay`.actualPay
     *
     * @return the value of `orderpay`.actualPay
     *
     * @mbggenerated
     */
    public Integer getActualpay() {
        return actualpay;
    }

    /**
     *
     *`orderpay`.actualPay
     *
     * @param actualpay the value for `orderpay`.actualPay
     *
     * @mbggenerated
     */
    public void setActualpay(Integer actualpay) {
        this.actualpay = actualpay;
    }

    /**
     *
     *`orderpay`.couponPay
     *
     * @return the value of `orderpay`.couponPay
     *
     * @mbggenerated
     */
    public Integer getCouponpay() {
        return couponpay;
    }

    /**
     *
     *`orderpay`.couponPay
     *
     * @param couponpay the value for `orderpay`.couponPay
     *
     * @mbggenerated
     */
    public void setCouponpay(Integer couponpay) {
        this.couponpay = couponpay;
    }

    /**
     *
     *`orderpay`.refundPrice
     *
     * @return the value of `orderpay`.refundPrice
     *
     * @mbggenerated
     */
    public Integer getRefundprice() {
        return refundprice;
    }

    /**
     *
     *`orderpay`.refundPrice
     *
     * @param refundprice the value for `orderpay`.refundPrice
     *
     * @mbggenerated
     */
    public void setRefundprice(Integer refundprice) {
        this.refundprice = refundprice;
    }

    /**
     *
     *`orderpay`.couponId
     *
     * @return the value of `orderpay`.couponId
     *
     * @mbggenerated
     */
    public String getCouponid() {
        return couponid;
    }

    /**
     *
     *`orderpay`.couponId
     *
     * @param couponid the value for `orderpay`.couponId
     *
     * @mbggenerated
     */
    public void setCouponid(String couponid) {
        this.couponid = couponid == null ? null : couponid.trim();
    }

    /**
     *
     *`orderpay`.updated_at
     *
     * @return the value of `orderpay`.updated_at
     *
     * @mbggenerated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     *`orderpay`.updated_at
     *
     * @param updatedAt the value for `orderpay`.updated_at
     *
     * @mbggenerated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     *`orderpay`.created_at
     *
     * @return the value of `orderpay`.created_at
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     *
     *`orderpay`.created_at
     *
     * @param createdAt the value for `orderpay`.created_at
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `orderpay`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderid=").append(orderid);
        sb.append(", paytype=").append(paytype);
        sb.append(", orderprice=").append(orderprice);
        sb.append(", costprice=").append(costprice);
        sb.append(", actualpay=").append(actualpay);
        sb.append(", couponpay=").append(couponpay);
        sb.append(", refundprice=").append(refundprice);
        sb.append(", couponid=").append(couponid);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append("]");
        return sb.toString();
    }
}