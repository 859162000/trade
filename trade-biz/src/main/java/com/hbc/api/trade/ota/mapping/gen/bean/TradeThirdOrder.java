package com.hbc.api.trade.ota.mapping.gen.bean;

import java.util.Date;

public class TradeThirdOrder {
    /**
     *  hbc订单号
     *  所属表字段为`trade_third_order`.order_no
     */
    private String orderNo;

    /**
     *  第三方合作id
     *  所属表字段为`trade_third_order`.third_partner
     */
    private Integer thirdPartner;

    /**
     *  第三方订单号
     *  所属表字段为`trade_third_order`.third_trade_no
     */
    private String thirdTradeNo;

    /**
     *  对应第三方车型
     *  所属表字段为`trade_third_order`.third_car_type
     */
    private Integer thirdCarType;

    /**
     *  订单状态 1001-下单成功 1002-导游接单 4002-订单取消 6001-订单完成
     *  所属表字段为`trade_third_order`.order_status
     */
    private Integer orderStatus;

    /**
     *  订单价格
     *  所属表字段为`trade_third_order`.price
     */
    private Double price;

    /**
     *  价格系统唯一标识
     *  所属表字段为`trade_third_order`.price_mark
     */
    private String priceMark;

    /**
     *  导游接单时间
     *  所属表字段为`trade_third_order`.guide_confirm_time
     */
    private Date guideConfirmTime;

    /**
     *  订单确认时间
     *  所属表字段为`trade_third_order`.order_confirm_time
     */
    private Date orderConfirmTime;

    /**
     *  优惠码
     *  所属表字段为`trade_third_order`.sale_code
     */
    private String saleCode;

    /**
     *  优惠券金额
     *  所属表字段为`trade_third_order`.sale_price
     */
    private Double salePrice;

    /**
     *  在渠道端使用的优惠券总金额
     *  所属表字段为`trade_third_order`.coupon_amount
     */
    private Double couponAmount;

    /**
     *  订单创建时间
     *  所属表字段为`trade_third_order`.create_time
     */
    private Date createTime;

    /**
     *  订单更新时间
     *  所属表字段为`trade_third_order`.update_time
     */
    private Date updateTime;

    /**
     *hbc订单号
     *`trade_third_order`.order_no
     *
     * @return the value of `trade_third_order`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *hbc订单号
     *`trade_third_order`.order_no
     *
     * @param orderNo the value for `trade_third_order`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *第三方合作id
     *`trade_third_order`.third_partner
     *
     * @return the value of `trade_third_order`.third_partner
     *
     * @mbggenerated
     */
    public Integer getThirdPartner() {
        return thirdPartner;
    }

    /**
     *第三方合作id
     *`trade_third_order`.third_partner
     *
     * @param thirdPartner the value for `trade_third_order`.third_partner
     *
     * @mbggenerated
     */
    public void setThirdPartner(Integer thirdPartner) {
        this.thirdPartner = thirdPartner;
    }

    /**
     *第三方订单号
     *`trade_third_order`.third_trade_no
     *
     * @return the value of `trade_third_order`.third_trade_no
     *
     * @mbggenerated
     */
    public String getThirdTradeNo() {
        return thirdTradeNo;
    }

    /**
     *第三方订单号
     *`trade_third_order`.third_trade_no
     *
     * @param thirdTradeNo the value for `trade_third_order`.third_trade_no
     *
     * @mbggenerated
     */
    public void setThirdTradeNo(String thirdTradeNo) {
        this.thirdTradeNo = thirdTradeNo == null ? null : thirdTradeNo.trim();
    }

    /**
     *对应第三方车型
     *`trade_third_order`.third_car_type
     *
     * @return the value of `trade_third_order`.third_car_type
     *
     * @mbggenerated
     */
    public Integer getThirdCarType() {
        return thirdCarType;
    }

    /**
     *对应第三方车型
     *`trade_third_order`.third_car_type
     *
     * @param thirdCarType the value for `trade_third_order`.third_car_type
     *
     * @mbggenerated
     */
    public void setThirdCarType(Integer thirdCarType) {
        this.thirdCarType = thirdCarType;
    }

    /**
     *订单状态 1001-下单成功 1002-导游接单 4002-订单取消 6001-订单完成
     *`trade_third_order`.order_status
     *
     * @return the value of `trade_third_order`.order_status
     *
     * @mbggenerated
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     *订单状态 1001-下单成功 1002-导游接单 4002-订单取消 6001-订单完成
     *`trade_third_order`.order_status
     *
     * @param orderStatus the value for `trade_third_order`.order_status
     *
     * @mbggenerated
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     *订单价格
     *`trade_third_order`.price
     *
     * @return the value of `trade_third_order`.price
     *
     * @mbggenerated
     */
    public Double getPrice() {
        return price;
    }

    /**
     *订单价格
     *`trade_third_order`.price
     *
     * @param price the value for `trade_third_order`.price
     *
     * @mbggenerated
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     *价格系统唯一标识
     *`trade_third_order`.price_mark
     *
     * @return the value of `trade_third_order`.price_mark
     *
     * @mbggenerated
     */
    public String getPriceMark() {
        return priceMark;
    }

    /**
     *价格系统唯一标识
     *`trade_third_order`.price_mark
     *
     * @param priceMark the value for `trade_third_order`.price_mark
     *
     * @mbggenerated
     */
    public void setPriceMark(String priceMark) {
        this.priceMark = priceMark == null ? null : priceMark.trim();
    }

    /**
     *导游接单时间
     *`trade_third_order`.guide_confirm_time
     *
     * @return the value of `trade_third_order`.guide_confirm_time
     *
     * @mbggenerated
     */
    public Date getGuideConfirmTime() {
        return guideConfirmTime;
    }

    /**
     *导游接单时间
     *`trade_third_order`.guide_confirm_time
     *
     * @param guideConfirmTime the value for `trade_third_order`.guide_confirm_time
     *
     * @mbggenerated
     */
    public void setGuideConfirmTime(Date guideConfirmTime) {
        this.guideConfirmTime = guideConfirmTime;
    }

    /**
     *订单确认时间
     *`trade_third_order`.order_confirm_time
     *
     * @return the value of `trade_third_order`.order_confirm_time
     *
     * @mbggenerated
     */
    public Date getOrderConfirmTime() {
        return orderConfirmTime;
    }

    /**
     *订单确认时间
     *`trade_third_order`.order_confirm_time
     *
     * @param orderConfirmTime the value for `trade_third_order`.order_confirm_time
     *
     * @mbggenerated
     */
    public void setOrderConfirmTime(Date orderConfirmTime) {
        this.orderConfirmTime = orderConfirmTime;
    }

    /**
     *优惠码
     *`trade_third_order`.sale_code
     *
     * @return the value of `trade_third_order`.sale_code
     *
     * @mbggenerated
     */
    public String getSaleCode() {
        return saleCode;
    }

    /**
     *优惠码
     *`trade_third_order`.sale_code
     *
     * @param saleCode the value for `trade_third_order`.sale_code
     *
     * @mbggenerated
     */
    public void setSaleCode(String saleCode) {
        this.saleCode = saleCode == null ? null : saleCode.trim();
    }

    /**
     *优惠券金额
     *`trade_third_order`.sale_price
     *
     * @return the value of `trade_third_order`.sale_price
     *
     * @mbggenerated
     */
    public Double getSalePrice() {
        return salePrice;
    }

    /**
     *优惠券金额
     *`trade_third_order`.sale_price
     *
     * @param salePrice the value for `trade_third_order`.sale_price
     *
     * @mbggenerated
     */
    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    /**
     *在渠道端使用的优惠券总金额
     *`trade_third_order`.coupon_amount
     *
     * @return the value of `trade_third_order`.coupon_amount
     *
     * @mbggenerated
     */
    public Double getCouponAmount() {
        return couponAmount;
    }

    /**
     *在渠道端使用的优惠券总金额
     *`trade_third_order`.coupon_amount
     *
     * @param couponAmount the value for `trade_third_order`.coupon_amount
     *
     * @mbggenerated
     */
    public void setCouponAmount(Double couponAmount) {
        this.couponAmount = couponAmount;
    }

    /**
     *订单创建时间
     *`trade_third_order`.create_time
     *
     * @return the value of `trade_third_order`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *订单创建时间
     *`trade_third_order`.create_time
     *
     * @param createTime the value for `trade_third_order`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *订单更新时间
     *`trade_third_order`.update_time
     *
     * @return the value of `trade_third_order`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *订单更新时间
     *`trade_third_order`.update_time
     *
     * @param updateTime the value for `trade_third_order`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_third_order`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderNo=").append(orderNo);
        sb.append(", thirdPartner=").append(thirdPartner);
        sb.append(", thirdTradeNo=").append(thirdTradeNo);
        sb.append(", thirdCarType=").append(thirdCarType);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", price=").append(price);
        sb.append(", priceMark=").append(priceMark);
        sb.append(", guideConfirmTime=").append(guideConfirmTime);
        sb.append(", orderConfirmTime=").append(orderConfirmTime);
        sb.append(", saleCode=").append(saleCode);
        sb.append(", salePrice=").append(salePrice);
        sb.append(", couponAmount=").append(couponAmount);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}