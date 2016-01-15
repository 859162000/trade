package com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean;

import java.util.Date;

public class FinalAgencyguide {
    /**
     *  
     *  所属表字段为`agencyguide`.agencyGuideId
     */
    private String agencyguideid;

    /**
     *  
     *  所属表字段为`agencyguide`.agencyId
     */
    private Integer agencyid;

    /**
     *  
     *  所属表字段为`agencyguide`.name
     */
    private String name;

    /**
     *  
     *  所属表字段为`agencyguide`.areaCode
     */
    private String areacode;

    /**
     *  
     *  所属表字段为`agencyguide`.mobile
     */
    private String mobile;

    /**
     *  
     *  所属表字段为`agencyguide`.isPrc
     */
    private Integer isprc;

    /**
     *  
     *  所属表字段为`agencyguide`.serviceCount
     */
    private Integer servicecount;

    /**
     *  
     *  所属表字段为`agencyguide`.status
     */
    private Integer status;

    /**
     *  
     *  所属表字段为`agencyguide`.updated_at
     */
    private Date updatedAt;

    /**
     *  
     *  所属表字段为`agencyguide`.created_at
     */
    private Date createdAt;

    /**
     *
     *`agencyguide`.agencyGuideId
     *
     * @return the value of `agencyguide`.agencyGuideId
     *
     * @mbggenerated
     */
    public String getAgencyguideid() {
        return agencyguideid;
    }

    /**
     *
     *`agencyguide`.agencyGuideId
     *
     * @param agencyguideid the value for `agencyguide`.agencyGuideId
     *
     * @mbggenerated
     */
    public void setAgencyguideid(String agencyguideid) {
        this.agencyguideid = agencyguideid == null ? null : agencyguideid.trim();
    }

    /**
     *
     *`agencyguide`.agencyId
     *
     * @return the value of `agencyguide`.agencyId
     *
     * @mbggenerated
     */
    public Integer getAgencyid() {
        return agencyid;
    }

    /**
     *
     *`agencyguide`.agencyId
     *
     * @param agencyid the value for `agencyguide`.agencyId
     *
     * @mbggenerated
     */
    public void setAgencyid(Integer agencyid) {
        this.agencyid = agencyid;
    }

    /**
     *
     *`agencyguide`.name
     *
     * @return the value of `agencyguide`.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     *
     *`agencyguide`.name
     *
     * @param name the value for `agencyguide`.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     *
     *`agencyguide`.areaCode
     *
     * @return the value of `agencyguide`.areaCode
     *
     * @mbggenerated
     */
    public String getAreacode() {
        return areacode;
    }

    /**
     *
     *`agencyguide`.areaCode
     *
     * @param areacode the value for `agencyguide`.areaCode
     *
     * @mbggenerated
     */
    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }

    /**
     *
     *`agencyguide`.mobile
     *
     * @return the value of `agencyguide`.mobile
     *
     * @mbggenerated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     *
     *`agencyguide`.mobile
     *
     * @param mobile the value for `agencyguide`.mobile
     *
     * @mbggenerated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     *
     *`agencyguide`.isPrc
     *
     * @return the value of `agencyguide`.isPrc
     *
     * @mbggenerated
     */
    public Integer getIsprc() {
        return isprc;
    }

    /**
     *
     *`agencyguide`.isPrc
     *
     * @param isprc the value for `agencyguide`.isPrc
     *
     * @mbggenerated
     */
    public void setIsprc(Integer isprc) {
        this.isprc = isprc;
    }

    /**
     *
     *`agencyguide`.serviceCount
     *
     * @return the value of `agencyguide`.serviceCount
     *
     * @mbggenerated
     */
    public Integer getServicecount() {
        return servicecount;
    }

    /**
     *
     *`agencyguide`.serviceCount
     *
     * @param servicecount the value for `agencyguide`.serviceCount
     *
     * @mbggenerated
     */
    public void setServicecount(Integer servicecount) {
        this.servicecount = servicecount;
    }

    /**
     *
     *`agencyguide`.status
     *
     * @return the value of `agencyguide`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     *`agencyguide`.status
     *
     * @param status the value for `agencyguide`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     *`agencyguide`.updated_at
     *
     * @return the value of `agencyguide`.updated_at
     *
     * @mbggenerated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     *`agencyguide`.updated_at
     *
     * @param updatedAt the value for `agencyguide`.updated_at
     *
     * @mbggenerated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     *`agencyguide`.created_at
     *
     * @return the value of `agencyguide`.created_at
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     *
     *`agencyguide`.created_at
     *
     * @param createdAt the value for `agencyguide`.created_at
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agencyguide`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", agencyguideid=").append(agencyguideid);
        sb.append(", agencyid=").append(agencyid);
        sb.append(", name=").append(name);
        sb.append(", areacode=").append(areacode);
        sb.append(", mobile=").append(mobile);
        sb.append(", isprc=").append(isprc);
        sb.append(", servicecount=").append(servicecount);
        sb.append(", status=").append(status);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append("]");
        return sb.toString();
    }
}