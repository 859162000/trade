package com.hbc.api.trade.bdata.mapper.basedata.gen.bean;

import java.util.Date;

public class AirportBean {
    /**
     *  
     *  所属表字段为`basedata_airport`.airport_id
     */
    private Integer airportId;

    /**
     *  
     *  所属表字段为`basedata_airport`.city_id
     */
    private Integer cityId;

    /**
     *  
     *  所属表字段为`basedata_airport`.city_name
     */
    private String cityName;

    /**
     *  
     *  所属表字段为`basedata_airport`.country_name
     */
    private String countryName;

    /**
     *  
     *  所属表字段为`basedata_airport`.name
     */
    private String name;

    /**
     *  
     *  所属表字段为`basedata_airport`.code
     */
    private String code;

    /**
     *  
     *  所属表字段为`basedata_airport`.is_view
     */
    private Integer isView;

    /**
     *  
     *  所属表字段为`basedata_airport`.location
     */
    private String location;

    /**
     *  
     *  所属表字段为`basedata_airport`.updated_at
     */
    private Date updatedAt;

    /**
     *  
     *  所属表字段为`basedata_airport`.created_at
     */
    private Date createdAt;

    /**
     *
     *`basedata_airport`.airport_id
     *
     * @return the value of `basedata_airport`.airport_id
     *
     * @mbggenerated
     */
    public Integer getAirportId() {
        return airportId;
    }

    /**
     *
     *`basedata_airport`.airport_id
     *
     * @param airportId the value for `basedata_airport`.airport_id
     *
     * @mbggenerated
     */
    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }

    /**
     *
     *`basedata_airport`.city_id
     *
     * @return the value of `basedata_airport`.city_id
     *
     * @mbggenerated
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     *
     *`basedata_airport`.city_id
     *
     * @param cityId the value for `basedata_airport`.city_id
     *
     * @mbggenerated
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     *
     *`basedata_airport`.city_name
     *
     * @return the value of `basedata_airport`.city_name
     *
     * @mbggenerated
     */
    public String getCityName() {
        return cityName;
    }

    /**
     *
     *`basedata_airport`.city_name
     *
     * @param cityName the value for `basedata_airport`.city_name
     *
     * @mbggenerated
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     *
     *`basedata_airport`.country_name
     *
     * @return the value of `basedata_airport`.country_name
     *
     * @mbggenerated
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     *
     *`basedata_airport`.country_name
     *
     * @param countryName the value for `basedata_airport`.country_name
     *
     * @mbggenerated
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    /**
     *
     *`basedata_airport`.name
     *
     * @return the value of `basedata_airport`.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     *
     *`basedata_airport`.name
     *
     * @param name the value for `basedata_airport`.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     *
     *`basedata_airport`.code
     *
     * @return the value of `basedata_airport`.code
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     *
     *`basedata_airport`.code
     *
     * @param code the value for `basedata_airport`.code
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     *
     *`basedata_airport`.is_view
     *
     * @return the value of `basedata_airport`.is_view
     *
     * @mbggenerated
     */
    public Integer getIsView() {
        return isView;
    }

    /**
     *
     *`basedata_airport`.is_view
     *
     * @param isView the value for `basedata_airport`.is_view
     *
     * @mbggenerated
     */
    public void setIsView(Integer isView) {
        this.isView = isView;
    }

    /**
     *
     *`basedata_airport`.location
     *
     * @return the value of `basedata_airport`.location
     *
     * @mbggenerated
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     *`basedata_airport`.location
     *
     * @param location the value for `basedata_airport`.location
     *
     * @mbggenerated
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     *
     *`basedata_airport`.updated_at
     *
     * @return the value of `basedata_airport`.updated_at
     *
     * @mbggenerated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     *`basedata_airport`.updated_at
     *
     * @param updatedAt the value for `basedata_airport`.updated_at
     *
     * @mbggenerated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     *`basedata_airport`.created_at
     *
     * @return the value of `basedata_airport`.created_at
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     *
     *`basedata_airport`.created_at
     *
     * @param createdAt the value for `basedata_airport`.created_at
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `basedata_airport`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", airportId=").append(airportId);
        sb.append(", cityId=").append(cityId);
        sb.append(", cityName=").append(cityName);
        sb.append(", countryName=").append(countryName);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", isView=").append(isView);
        sb.append(", location=").append(location);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append("]");
        return sb.toString();
    }
}