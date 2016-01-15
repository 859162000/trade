package com.hbc.api.trade.bdata.mapper.basedata.gen.bean;

import java.util.Date;

public class ContinentBean {
    /**
     *  
     *  所属表字段为`basedata_continents`.continent_id
     */
    private Integer continentId;

    /**
     *  
     *  所属表字段为`basedata_continents`.continent_name
     */
    private String continentName;

    /**
     *  
     *  所属表字段为`basedata_continents`.order_id
     */
    private Integer orderId;

    /**
     *  
     *  所属表字段为`basedata_continents`.status
     */
    private Integer status;

    /**
     *  
     *  所属表字段为`basedata_continents`.updated_at
     */
    private Date updatedAt;

    /**
     *  
     *  所属表字段为`basedata_continents`.deleted_at
     */
    private Date deletedAt;

    /**
     *  
     *  所属表字段为`basedata_continents`.created_at
     */
    private Date createdAt;

    /**
     *
     *`basedata_continents`.continent_id
     *
     * @return the value of `basedata_continents`.continent_id
     *
     * @mbggenerated
     */
    public Integer getContinentId() {
        return continentId;
    }

    /**
     *
     *`basedata_continents`.continent_id
     *
     * @param continentId the value for `basedata_continents`.continent_id
     *
     * @mbggenerated
     */
    public void setContinentId(Integer continentId) {
        this.continentId = continentId;
    }

    /**
     *
     *`basedata_continents`.continent_name
     *
     * @return the value of `basedata_continents`.continent_name
     *
     * @mbggenerated
     */
    public String getContinentName() {
        return continentName;
    }

    /**
     *
     *`basedata_continents`.continent_name
     *
     * @param continentName the value for `basedata_continents`.continent_name
     *
     * @mbggenerated
     */
    public void setContinentName(String continentName) {
        this.continentName = continentName == null ? null : continentName.trim();
    }

    /**
     *
     *`basedata_continents`.order_id
     *
     * @return the value of `basedata_continents`.order_id
     *
     * @mbggenerated
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     *
     *`basedata_continents`.order_id
     *
     * @param orderId the value for `basedata_continents`.order_id
     *
     * @mbggenerated
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     *
     *`basedata_continents`.status
     *
     * @return the value of `basedata_continents`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     *`basedata_continents`.status
     *
     * @param status the value for `basedata_continents`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     *`basedata_continents`.updated_at
     *
     * @return the value of `basedata_continents`.updated_at
     *
     * @mbggenerated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     *`basedata_continents`.updated_at
     *
     * @param updatedAt the value for `basedata_continents`.updated_at
     *
     * @mbggenerated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     *`basedata_continents`.deleted_at
     *
     * @return the value of `basedata_continents`.deleted_at
     *
     * @mbggenerated
     */
    public Date getDeletedAt() {
        return deletedAt;
    }

    /**
     *
     *`basedata_continents`.deleted_at
     *
     * @param deletedAt the value for `basedata_continents`.deleted_at
     *
     * @mbggenerated
     */
    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    /**
     *
     *`basedata_continents`.created_at
     *
     * @return the value of `basedata_continents`.created_at
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     *
     *`basedata_continents`.created_at
     *
     * @param createdAt the value for `basedata_continents`.created_at
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `basedata_continents`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", continentId=").append(continentId);
        sb.append(", continentName=").append(continentName);
        sb.append(", orderId=").append(orderId);
        sb.append(", status=").append(status);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", deletedAt=").append(deletedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append("]");
        return sb.toString();
    }
}