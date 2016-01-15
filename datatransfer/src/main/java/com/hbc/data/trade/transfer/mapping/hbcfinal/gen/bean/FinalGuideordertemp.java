package com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean;

import java.util.Date;

public class FinalGuideordertemp {
    /**
     *  
     *  所属表字段为`guideordertemp`.guideOrderTempId
     */
    private String guideordertempid;

    /**
     *  
     *  所属表字段为`guideordertemp`.orderId
     */
    private String orderid;

    /**
     *  
     *  所属表字段为`guideordertemp`.guideId
     */
    private Integer guideid;

    /**
     *  
     *  所属表字段为`guideordertemp`.priority
     */
    private Integer priority;

    /**
     *  
     *  所属表字段为`guideordertemp`.flag
     */
    private Integer flag;

    /**
     *  
     *  所属表字段为`guideordertemp`.deliverTime
     */
    private Date delivertime;

    /**
     *  
     *  所属表字段为`guideordertemp`.status
     */
    private Integer status;

    /**
     *  
     *  所属表字段为`guideordertemp`.updated_at
     */
    private Date updatedAt;

    /**
     *  
     *  所属表字段为`guideordertemp`.created_at
     */
    private Date createdAt;

    /**
     *
     *`guideordertemp`.guideOrderTempId
     *
     * @return the value of `guideordertemp`.guideOrderTempId
     *
     * @mbggenerated
     */
    public String getGuideordertempid() {
        return guideordertempid;
    }

    /**
     *
     *`guideordertemp`.guideOrderTempId
     *
     * @param guideordertempid the value for `guideordertemp`.guideOrderTempId
     *
     * @mbggenerated
     */
    public void setGuideordertempid(String guideordertempid) {
        this.guideordertempid = guideordertempid == null ? null : guideordertempid.trim();
    }

    /**
     *
     *`guideordertemp`.orderId
     *
     * @return the value of `guideordertemp`.orderId
     *
     * @mbggenerated
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     *
     *`guideordertemp`.orderId
     *
     * @param orderid the value for `guideordertemp`.orderId
     *
     * @mbggenerated
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    /**
     *
     *`guideordertemp`.guideId
     *
     * @return the value of `guideordertemp`.guideId
     *
     * @mbggenerated
     */
    public Integer getGuideid() {
        return guideid;
    }

    /**
     *
     *`guideordertemp`.guideId
     *
     * @param guideid the value for `guideordertemp`.guideId
     *
     * @mbggenerated
     */
    public void setGuideid(Integer guideid) {
        this.guideid = guideid;
    }

    /**
     *
     *`guideordertemp`.priority
     *
     * @return the value of `guideordertemp`.priority
     *
     * @mbggenerated
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     *
     *`guideordertemp`.priority
     *
     * @param priority the value for `guideordertemp`.priority
     *
     * @mbggenerated
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     *
     *`guideordertemp`.flag
     *
     * @return the value of `guideordertemp`.flag
     *
     * @mbggenerated
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     *
     *`guideordertemp`.flag
     *
     * @param flag the value for `guideordertemp`.flag
     *
     * @mbggenerated
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     *
     *`guideordertemp`.deliverTime
     *
     * @return the value of `guideordertemp`.deliverTime
     *
     * @mbggenerated
     */
    public Date getDelivertime() {
        return delivertime;
    }

    /**
     *
     *`guideordertemp`.deliverTime
     *
     * @param delivertime the value for `guideordertemp`.deliverTime
     *
     * @mbggenerated
     */
    public void setDelivertime(Date delivertime) {
        this.delivertime = delivertime;
    }

    /**
     *
     *`guideordertemp`.status
     *
     * @return the value of `guideordertemp`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     *`guideordertemp`.status
     *
     * @param status the value for `guideordertemp`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     *`guideordertemp`.updated_at
     *
     * @return the value of `guideordertemp`.updated_at
     *
     * @mbggenerated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     *`guideordertemp`.updated_at
     *
     * @param updatedAt the value for `guideordertemp`.updated_at
     *
     * @mbggenerated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     *`guideordertemp`.created_at
     *
     * @return the value of `guideordertemp`.created_at
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     *
     *`guideordertemp`.created_at
     *
     * @param createdAt the value for `guideordertemp`.created_at
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideordertemp`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guideordertempid=").append(guideordertempid);
        sb.append(", orderid=").append(orderid);
        sb.append(", guideid=").append(guideid);
        sb.append(", priority=").append(priority);
        sb.append(", flag=").append(flag);
        sb.append(", delivertime=").append(delivertime);
        sb.append(", status=").append(status);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append("]");
        return sb.toString();
    }
}