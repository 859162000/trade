package com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean;

import java.util.Date;

public class FinalOrderJourney {
    /**
     *  
     *  所属表字段为`orderjourney`.orderJourneyId
     */
    private Integer orderjourneyid;

    /**
     *  
     *  所属表字段为`orderjourney`.orderId
     */
    private String orderid;

    /**
     *  
     *  所属表字段为`orderjourney`.content
     */
    private String content;

    /**
     *  
     *  所属表字段为`orderjourney`.value
     */
    private String value;

    /**
     *  
     *  所属表字段为`orderjourney`.type
     */
    private Integer type;

    /**
     *  
     *  所属表字段为`orderjourney`.status
     */
    private Integer status;

    /**
     *  
     *  所属表字段为`orderjourney`.isRead
     */
    private Integer isread;

    /**
     *  
     *  所属表字段为`orderjourney`.updated_at
     */
    private Date updatedAt;

    /**
     *  
     *  所属表字段为`orderjourney`.created_at
     */
    private Date createdAt;

    /**
     *
     *`orderjourney`.orderJourneyId
     *
     * @return the value of `orderjourney`.orderJourneyId
     *
     * @mbggenerated
     */
    public Integer getOrderjourneyid() {
        return orderjourneyid;
    }

    /**
     *
     *`orderjourney`.orderJourneyId
     *
     * @param orderjourneyid the value for `orderjourney`.orderJourneyId
     *
     * @mbggenerated
     */
    public void setOrderjourneyid(Integer orderjourneyid) {
        this.orderjourneyid = orderjourneyid;
    }

    /**
     *
     *`orderjourney`.orderId
     *
     * @return the value of `orderjourney`.orderId
     *
     * @mbggenerated
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     *
     *`orderjourney`.orderId
     *
     * @param orderid the value for `orderjourney`.orderId
     *
     * @mbggenerated
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    /**
     *
     *`orderjourney`.content
     *
     * @return the value of `orderjourney`.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     *
     *`orderjourney`.content
     *
     * @param content the value for `orderjourney`.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     *
     *`orderjourney`.value
     *
     * @return the value of `orderjourney`.value
     *
     * @mbggenerated
     */
    public String getValue() {
        return value;
    }

    /**
     *
     *`orderjourney`.value
     *
     * @param value the value for `orderjourney`.value
     *
     * @mbggenerated
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     *
     *`orderjourney`.type
     *
     * @return the value of `orderjourney`.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     *
     *`orderjourney`.type
     *
     * @param type the value for `orderjourney`.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     *
     *`orderjourney`.status
     *
     * @return the value of `orderjourney`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     *`orderjourney`.status
     *
     * @param status the value for `orderjourney`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     *`orderjourney`.isRead
     *
     * @return the value of `orderjourney`.isRead
     *
     * @mbggenerated
     */
    public Integer getIsread() {
        return isread;
    }

    /**
     *
     *`orderjourney`.isRead
     *
     * @param isread the value for `orderjourney`.isRead
     *
     * @mbggenerated
     */
    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    /**
     *
     *`orderjourney`.updated_at
     *
     * @return the value of `orderjourney`.updated_at
     *
     * @mbggenerated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     *`orderjourney`.updated_at
     *
     * @param updatedAt the value for `orderjourney`.updated_at
     *
     * @mbggenerated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     *`orderjourney`.created_at
     *
     * @return the value of `orderjourney`.created_at
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     *
     *`orderjourney`.created_at
     *
     * @param createdAt the value for `orderjourney`.created_at
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `orderjourney`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderjourneyid=").append(orderjourneyid);
        sb.append(", orderid=").append(orderid);
        sb.append(", content=").append(content);
        sb.append(", value=").append(value);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", isread=").append(isread);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append("]");
        return sb.toString();
    }
}