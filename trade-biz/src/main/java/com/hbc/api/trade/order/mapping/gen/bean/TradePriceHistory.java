package com.hbc.api.trade.order.mapping.gen.bean;

import java.util.Date;

public class TradePriceHistory {
    /**
     *  
     *  所属表字段为`trade_price_history`.pkid
     */
    private Integer pkid;

    /**
     *  订单编号
     *  所属表字段为`trade_price_history`.order_no
     */
    private String orderNo;

    /**
     *  修改序号 第一次 修改 第二次修改
     *  所属表字段为`trade_price_history`.price_seq
     */
    private Integer priceSeq;

    /**
     *  1 导游价 修改 2 渠道价修改
     *  所属表字段为`trade_price_history`.price_type
     */
    private Integer priceType;

    /**
     *  价格 原始价
     *  所属表字段为`trade_price_history`.source_price
     */
    private Double sourcePrice;

    /**
     *  价格 变动后的价格
     *  所属表字段为`trade_price_history`.target_price
     */
    private Double targetPrice;

    /**
     *  价格改动类型：
            1.串单
            2.管理员修改导游价
            
            3.发单导游级别降价
     *  所属表字段为`trade_price_history`.op_type
     */
    private Integer opType;

    /**
     *  
     *  所属表字段为`trade_price_history`.op_uid
     */
    private String opUid;

    /**
     *  
     *  所属表字段为`trade_price_history`.op_uname
     */
    private String opUname;

    /**
     *  改价原因
     *  所属表字段为`trade_price_history`.op_comment
     */
    private String opComment;

    /**
     *  
     *  所属表字段为`trade_price_history`.create_time
     */
    private Date createTime;

    /**
     *  
     *  所属表字段为`trade_price_history`.update_time
     */
    private Date updateTime;

    /**
     *
     *`trade_price_history`.pkid
     *
     * @return the value of `trade_price_history`.pkid
     *
     * @mbggenerated
     */
    public Integer getPkid() {
        return pkid;
    }

    /**
     *
     *`trade_price_history`.pkid
     *
     * @param pkid the value for `trade_price_history`.pkid
     *
     * @mbggenerated
     */
    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    /**
     *订单编号
     *`trade_price_history`.order_no
     *
     * @return the value of `trade_price_history`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *订单编号
     *`trade_price_history`.order_no
     *
     * @param orderNo the value for `trade_price_history`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *修改序号 第一次 修改 第二次修改
     *`trade_price_history`.price_seq
     *
     * @return the value of `trade_price_history`.price_seq
     *
     * @mbggenerated
     */
    public Integer getPriceSeq() {
        return priceSeq;
    }

    /**
     *修改序号 第一次 修改 第二次修改
     *`trade_price_history`.price_seq
     *
     * @param priceSeq the value for `trade_price_history`.price_seq
     *
     * @mbggenerated
     */
    public void setPriceSeq(Integer priceSeq) {
        this.priceSeq = priceSeq;
    }

    /**
     *1 导游价 修改 2 渠道价修改
     *`trade_price_history`.price_type
     *
     * @return the value of `trade_price_history`.price_type
     *
     * @mbggenerated
     */
    public Integer getPriceType() {
        return priceType;
    }

    /**
     *1 导游价 修改 2 渠道价修改
     *`trade_price_history`.price_type
     *
     * @param priceType the value for `trade_price_history`.price_type
     *
     * @mbggenerated
     */
    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    /**
     *价格 原始价
     *`trade_price_history`.source_price
     *
     * @return the value of `trade_price_history`.source_price
     *
     * @mbggenerated
     */
    public Double getSourcePrice() {
        return sourcePrice;
    }

    /**
     *价格 原始价
     *`trade_price_history`.source_price
     *
     * @param sourcePrice the value for `trade_price_history`.source_price
     *
     * @mbggenerated
     */
    public void setSourcePrice(Double sourcePrice) {
        this.sourcePrice = sourcePrice;
    }

    /**
     *价格 变动后的价格
     *`trade_price_history`.target_price
     *
     * @return the value of `trade_price_history`.target_price
     *
     * @mbggenerated
     */
    public Double getTargetPrice() {
        return targetPrice;
    }

    /**
     *价格 变动后的价格
     *`trade_price_history`.target_price
     *
     * @param targetPrice the value for `trade_price_history`.target_price
     *
     * @mbggenerated
     */
    public void setTargetPrice(Double targetPrice) {
        this.targetPrice = targetPrice;
    }

    /**
     *价格改动类型：
            1.串单
            2.管理员修改导游价
            
            3.发单导游级别降价
     *`trade_price_history`.op_type
     *
     * @return the value of `trade_price_history`.op_type
     *
     * @mbggenerated
     */
    public Integer getOpType() {
        return opType;
    }

    /**
     *价格改动类型：
            1.串单
            2.管理员修改导游价
            
            3.发单导游级别降价
     *`trade_price_history`.op_type
     *
     * @param opType the value for `trade_price_history`.op_type
     *
     * @mbggenerated
     */
    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    /**
     *
     *`trade_price_history`.op_uid
     *
     * @return the value of `trade_price_history`.op_uid
     *
     * @mbggenerated
     */
    public String getOpUid() {
        return opUid;
    }

    /**
     *
     *`trade_price_history`.op_uid
     *
     * @param opUid the value for `trade_price_history`.op_uid
     *
     * @mbggenerated
     */
    public void setOpUid(String opUid) {
        this.opUid = opUid == null ? null : opUid.trim();
    }

    /**
     *
     *`trade_price_history`.op_uname
     *
     * @return the value of `trade_price_history`.op_uname
     *
     * @mbggenerated
     */
    public String getOpUname() {
        return opUname;
    }

    /**
     *
     *`trade_price_history`.op_uname
     *
     * @param opUname the value for `trade_price_history`.op_uname
     *
     * @mbggenerated
     */
    public void setOpUname(String opUname) {
        this.opUname = opUname == null ? null : opUname.trim();
    }

    /**
     *改价原因
     *`trade_price_history`.op_comment
     *
     * @return the value of `trade_price_history`.op_comment
     *
     * @mbggenerated
     */
    public String getOpComment() {
        return opComment;
    }

    /**
     *改价原因
     *`trade_price_history`.op_comment
     *
     * @param opComment the value for `trade_price_history`.op_comment
     *
     * @mbggenerated
     */
    public void setOpComment(String opComment) {
        this.opComment = opComment == null ? null : opComment.trim();
    }

    /**
     *
     *`trade_price_history`.create_time
     *
     * @return the value of `trade_price_history`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`trade_price_history`.create_time
     *
     * @param createTime the value for `trade_price_history`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     *`trade_price_history`.update_time
     *
     * @return the value of `trade_price_history`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`trade_price_history`.update_time
     *
     * @param updateTime the value for `trade_price_history`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_price_history`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkid=").append(pkid);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", priceSeq=").append(priceSeq);
        sb.append(", priceType=").append(priceType);
        sb.append(", sourcePrice=").append(sourcePrice);
        sb.append(", targetPrice=").append(targetPrice);
        sb.append(", opType=").append(opType);
        sb.append(", opUid=").append(opUid);
        sb.append(", opUname=").append(opUname);
        sb.append(", opComment=").append(opComment);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}