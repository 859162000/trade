package com.hbc.api.trade.bdata.mapper.guide.gen.bean;

import java.util.Date;

public class GuideCrop {
    /**
     *  
     *  所属表字段为`guide_crop`.guide_crop_id
     */
    private Integer guideCropId;

    /**
     *  
     *  所属表字段为`guide_crop`.guide_id
     */
    private String guideId;

    /**
     *  
     *  所属表字段为`guide_crop`.city_id
     */
    private Integer cityId;

    /**
     *  
     *  所属表字段为`guide_crop`.city_name
     */
    private String cityName;

    /**
     *  
     *  所属表字段为`guide_crop`.country_id
     */
    private Integer countryId;

    /**
     *  
     *  所属表字段为`guide_crop`.country_name
     */
    private String countryName;

    /**
     *  服务种类。1-接机；2-送机；3-日租；4-按次。
     *  所属表字段为`guide_crop`.crop_type
     */
    private Integer cropType;

    /**
     *  
     *  所属表字段为`guide_crop`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`guide_crop`.create_time
     */
    private Date createTime;

    /**
     *
     *`guide_crop`.guide_crop_id
     *
     * @return the value of `guide_crop`.guide_crop_id
     *
     * @mbggenerated
     */
    public Integer getGuideCropId() {
        return guideCropId;
    }

    /**
     *
     *`guide_crop`.guide_crop_id
     *
     * @param guideCropId the value for `guide_crop`.guide_crop_id
     *
     * @mbggenerated
     */
    public void setGuideCropId(Integer guideCropId) {
        this.guideCropId = guideCropId;
    }

    /**
     *
     *`guide_crop`.guide_id
     *
     * @return the value of `guide_crop`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *
     *`guide_crop`.guide_id
     *
     * @param guideId the value for `guide_crop`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *
     *`guide_crop`.city_id
     *
     * @return the value of `guide_crop`.city_id
     *
     * @mbggenerated
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     *
     *`guide_crop`.city_id
     *
     * @param cityId the value for `guide_crop`.city_id
     *
     * @mbggenerated
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     *
     *`guide_crop`.city_name
     *
     * @return the value of `guide_crop`.city_name
     *
     * @mbggenerated
     */
    public String getCityName() {
        return cityName;
    }

    /**
     *
     *`guide_crop`.city_name
     *
     * @param cityName the value for `guide_crop`.city_name
     *
     * @mbggenerated
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     *
     *`guide_crop`.country_id
     *
     * @return the value of `guide_crop`.country_id
     *
     * @mbggenerated
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     *
     *`guide_crop`.country_id
     *
     * @param countryId the value for `guide_crop`.country_id
     *
     * @mbggenerated
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     *
     *`guide_crop`.country_name
     *
     * @return the value of `guide_crop`.country_name
     *
     * @mbggenerated
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     *
     *`guide_crop`.country_name
     *
     * @param countryName the value for `guide_crop`.country_name
     *
     * @mbggenerated
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    /**
     *服务种类。1-接机；2-送机；3-日租；4-按次。
     *`guide_crop`.crop_type
     *
     * @return the value of `guide_crop`.crop_type
     *
     * @mbggenerated
     */
    public Integer getCropType() {
        return cropType;
    }

    /**
     *服务种类。1-接机；2-送机；3-日租；4-按次。
     *`guide_crop`.crop_type
     *
     * @param cropType the value for `guide_crop`.crop_type
     *
     * @mbggenerated
     */
    public void setCropType(Integer cropType) {
        this.cropType = cropType;
    }

    /**
     *
     *`guide_crop`.update_time
     *
     * @return the value of `guide_crop`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`guide_crop`.update_time
     *
     * @param updateTime the value for `guide_crop`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`guide_crop`.create_time
     *
     * @return the value of `guide_crop`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`guide_crop`.create_time
     *
     * @param createTime the value for `guide_crop`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_crop`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guideCropId=").append(guideCropId);
        sb.append(", guideId=").append(guideId);
        sb.append(", cityId=").append(cityId);
        sb.append(", cityName=").append(cityName);
        sb.append(", countryId=").append(countryId);
        sb.append(", countryName=").append(countryName);
        sb.append(", cropType=").append(cropType);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}