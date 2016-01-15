package com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean;

import java.math.BigDecimal;
import java.util.Date;

public class FinalOrderGuideReward {
    /**
     *  
     *  所属表字段为`orderguidereward`.orderGuideRewardId
     */
    private String orderguiderewardid;

    /**
     *  
     *  所属表字段为`orderguidereward`.orderId
     */
    private String orderid;

    /**
     *  
     *  所属表字段为`orderguidereward`.guideId
     */
    private Integer guideid;

    /**
     *  
     *  所属表字段为`orderguidereward`.orderRewardType
     */
    private Integer orderrewardtype;

    /**
     *  
     *  所属表字段为`orderguidereward`.comment
     */
    private String comment;

    /**
     *  
     *  所属表字段为`orderguidereward`.reward
     */
    private BigDecimal reward;

    /**
     *  
     *  所属表字段为`orderguidereward`.status
     */
    private Integer status;

    /**
     *  
     *  所属表字段为`orderguidereward`.updated_at
     */
    private Date updatedAt;

    /**
     *  
     *  所属表字段为`orderguidereward`.created_at
     */
    private Date createdAt;

    /**
     *
     *`orderguidereward`.orderGuideRewardId
     *
     * @return the value of `orderguidereward`.orderGuideRewardId
     *
     * @mbggenerated
     */
    public String getOrderguiderewardid() {
        return orderguiderewardid;
    }

    /**
     *
     *`orderguidereward`.orderGuideRewardId
     *
     * @param orderguiderewardid the value for `orderguidereward`.orderGuideRewardId
     *
     * @mbggenerated
     */
    public void setOrderguiderewardid(String orderguiderewardid) {
        this.orderguiderewardid = orderguiderewardid == null ? null : orderguiderewardid.trim();
    }

    /**
     *
     *`orderguidereward`.orderId
     *
     * @return the value of `orderguidereward`.orderId
     *
     * @mbggenerated
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     *
     *`orderguidereward`.orderId
     *
     * @param orderid the value for `orderguidereward`.orderId
     *
     * @mbggenerated
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    /**
     *
     *`orderguidereward`.guideId
     *
     * @return the value of `orderguidereward`.guideId
     *
     * @mbggenerated
     */
    public Integer getGuideid() {
        return guideid;
    }

    /**
     *
     *`orderguidereward`.guideId
     *
     * @param guideid the value for `orderguidereward`.guideId
     *
     * @mbggenerated
     */
    public void setGuideid(Integer guideid) {
        this.guideid = guideid;
    }

    /**
     *
     *`orderguidereward`.orderRewardType
     *
     * @return the value of `orderguidereward`.orderRewardType
     *
     * @mbggenerated
     */
    public Integer getOrderrewardtype() {
        return orderrewardtype;
    }

    /**
     *
     *`orderguidereward`.orderRewardType
     *
     * @param orderrewardtype the value for `orderguidereward`.orderRewardType
     *
     * @mbggenerated
     */
    public void setOrderrewardtype(Integer orderrewardtype) {
        this.orderrewardtype = orderrewardtype;
    }

    /**
     *
     *`orderguidereward`.comment
     *
     * @return the value of `orderguidereward`.comment
     *
     * @mbggenerated
     */
    public String getComment() {
        return comment;
    }

    /**
     *
     *`orderguidereward`.comment
     *
     * @param comment the value for `orderguidereward`.comment
     *
     * @mbggenerated
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     *
     *`orderguidereward`.reward
     *
     * @return the value of `orderguidereward`.reward
     *
     * @mbggenerated
     */
    public BigDecimal getReward() {
        return reward;
    }

    /**
     *
     *`orderguidereward`.reward
     *
     * @param reward the value for `orderguidereward`.reward
     *
     * @mbggenerated
     */
    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    /**
     *
     *`orderguidereward`.status
     *
     * @return the value of `orderguidereward`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     *`orderguidereward`.status
     *
     * @param status the value for `orderguidereward`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     *`orderguidereward`.updated_at
     *
     * @return the value of `orderguidereward`.updated_at
     *
     * @mbggenerated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     *`orderguidereward`.updated_at
     *
     * @param updatedAt the value for `orderguidereward`.updated_at
     *
     * @mbggenerated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     *`orderguidereward`.created_at
     *
     * @return the value of `orderguidereward`.created_at
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     *
     *`orderguidereward`.created_at
     *
     * @param createdAt the value for `orderguidereward`.created_at
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `orderguidereward`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderguiderewardid=").append(orderguiderewardid);
        sb.append(", orderid=").append(orderid);
        sb.append(", guideid=").append(guideid);
        sb.append(", orderrewardtype=").append(orderrewardtype);
        sb.append(", comment=").append(comment);
        sb.append(", reward=").append(reward);
        sb.append(", status=").append(status);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append("]");
        return sb.toString();
    }
}