package com.hbc.api.trade.order.mapping.gen.bean;

import java.util.Date;

public class PriceSnapshot {
    /**
     *  主键
     *  所属表字段为`price_snapshot`.id
     */
    private Long id;

    /**
     *  创建时间
     *  所属表字段为`price_snapshot`.create_time
     */
    private Date createTime;

    /**
     *  更新时间
     *  所属表字段为`price_snapshot`.update_time
     */
    private Date updateTime;

    /**
     *  服务类型
     *  所属表字段为`price_snapshot`.service_type
     */
    private Integer serviceType;

    /**
     *  渠道
     *  所属表字段为`price_snapshot`.channel
     */
    private Integer channel;

    /**
     *  Pricemark
     *  所属表字段为`price_snapshot`.pricemark
     */
    private String pricemark;

    /**
     *  汇率
     *  所属表字段为`price_snapshot`.currency_rate
     */
    private Float currencyRate;

    /**
     *  车型
     *  所属表字段为`price_snapshot`.car_type
     */
    private Integer carType;

    /**
     *  座位类型
     *  所属表字段为`price_snapshot`.seat_category
     */
    private Integer seatCategory;

    /**
     *  城市id
     *  所属表字段为`price_snapshot`.city_id
     */
    private Integer cityId;

    /**
     *  机场id
     *  所属表字段为`price_snapshot`.airport_id
     */
    private Integer airportId;

    /**
     *  机场三字码
     *  所属表字段为`price_snapshot`.airport_code
     */
    private String airportCode;

    /**
     *  服务时间
     *  所属表字段为`price_snapshot`.service_date
     */
    private String serviceDate;

    /**
     *  起始点坐标（纬度,经度）
     *  所属表字段为`price_snapshot`.start_location
     */
    private String startLocation;

    /**
     *  目的点坐标（纬度,经度）
     *  所属表字段为`price_snapshot`.end_location
     */
    private String endLocation;

    /**
     *  是否急单报价
     *  所属表字段为`price_snapshot`.is_urgent
     */
    private Integer isUrgent;

    /**
     *  系统价
     *  所属表字段为`price_snapshot`.sys_price
     */
    private Double sysPrice;

    /**
     *  票面价
     *  所属表字段为`price_snapshot`.ticket_price
     */
    private Double ticketPrice;

    /**
     *  导游价
     *  所属表字段为`price_snapshot`.guide_price
     */
    private Double guidePrice;

    /**
     *  渠道价
     *  所属表字段为`price_snapshot`.channel_price
     */
    private Double channelPrice;

    /**
     *  
     *  所属表字段为`price_snapshot`.sys_slices
     */
    private String sysSlices;

    /**
     *  
     *  所属表字段为`price_snapshot`.ticket_slices
     */
    private String ticketSlices;

    /**
     *  
     *  所属表字段为`price_snapshot`.channel_slices
     */
    private String channelSlices;

    /**
     *  
     *  所属表字段为`price_snapshot`.guide_slices
     */
    private String guideSlices;

    /**
     *  价格计算因素
     *  所属表字段为`price_snapshot`.price_factor
     */
    private String priceFactor;

    /**
     *  接送次距离
     *  所属表字段为`price_snapshot`.distance
     */
    private Double distance;

    /**
     *  接送次预估时间
     *  所属表字段为`price_snapshot`.est_time
     */
    private Integer estTime;

    /**
     *  超时费/小时(RMB) 日租有效
     *  所属表字段为`price_snapshot`.charge_per_hour
     */
    private Double chargePerHour;

    /**
     *  超公里费/KM(RMB) 日租有效
     *  所属表字段为`price_snapshot`.charge_per_km
     */
    private Double chargePerKm;

    /**
     *  超时费/天(RMB) 日租有效
     *  所属表字段为`price_snapshot`.charge_per_day
     */
    private Double chargePerDay;

    /**
     *  超时等待费/分钟(RMB) 接送次有效
     *  所属表字段为`price_snapshot`.charge_per_minute
     */
    private Double chargePerMinute;

    /**
     *  过期时间
     *  所属表字段为`price_snapshot`.expired_time
     */
    private Date expiredTime;

    /**
     *  0.异常 1. 正常 
     *  所属表字段为`price_snapshot`.status
     */
    private Integer status;

    /**
     *  
     *  所属表字段为`price_snapshot`.price_version
     */
    private Integer priceVersion;

    /**
     *主键
     *`price_snapshot`.id
     *
     * @return the value of `price_snapshot`.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     *主键
     *`price_snapshot`.id
     *
     * @param id the value for `price_snapshot`.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *创建时间
     *`price_snapshot`.create_time
     *
     * @return the value of `price_snapshot`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     *`price_snapshot`.create_time
     *
     * @param createTime the value for `price_snapshot`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *更新时间
     *`price_snapshot`.update_time
     *
     * @return the value of `price_snapshot`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新时间
     *`price_snapshot`.update_time
     *
     * @param updateTime the value for `price_snapshot`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *服务类型
     *`price_snapshot`.service_type
     *
     * @return the value of `price_snapshot`.service_type
     *
     * @mbggenerated
     */
    public Integer getServiceType() {
        return serviceType;
    }

    /**
     *服务类型
     *`price_snapshot`.service_type
     *
     * @param serviceType the value for `price_snapshot`.service_type
     *
     * @mbggenerated
     */
    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    /**
     *渠道
     *`price_snapshot`.channel
     *
     * @return the value of `price_snapshot`.channel
     *
     * @mbggenerated
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     *渠道
     *`price_snapshot`.channel
     *
     * @param channel the value for `price_snapshot`.channel
     *
     * @mbggenerated
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    /**
     *Pricemark
     *`price_snapshot`.pricemark
     *
     * @return the value of `price_snapshot`.pricemark
     *
     * @mbggenerated
     */
    public String getPricemark() {
        return pricemark;
    }

    /**
     *Pricemark
     *`price_snapshot`.pricemark
     *
     * @param pricemark the value for `price_snapshot`.pricemark
     *
     * @mbggenerated
     */
    public void setPricemark(String pricemark) {
        this.pricemark = pricemark == null ? null : pricemark.trim();
    }

    /**
     *汇率
     *`price_snapshot`.currency_rate
     *
     * @return the value of `price_snapshot`.currency_rate
     *
     * @mbggenerated
     */
    public Float getCurrencyRate() {
        return currencyRate;
    }

    /**
     *汇率
     *`price_snapshot`.currency_rate
     *
     * @param currencyRate the value for `price_snapshot`.currency_rate
     *
     * @mbggenerated
     */
    public void setCurrencyRate(Float currencyRate) {
        this.currencyRate = currencyRate;
    }

    /**
     *车型
     *`price_snapshot`.car_type
     *
     * @return the value of `price_snapshot`.car_type
     *
     * @mbggenerated
     */
    public Integer getCarType() {
        return carType;
    }

    /**
     *车型
     *`price_snapshot`.car_type
     *
     * @param carType the value for `price_snapshot`.car_type
     *
     * @mbggenerated
     */
    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    /**
     *座位类型
     *`price_snapshot`.seat_category
     *
     * @return the value of `price_snapshot`.seat_category
     *
     * @mbggenerated
     */
    public Integer getSeatCategory() {
        return seatCategory;
    }

    /**
     *座位类型
     *`price_snapshot`.seat_category
     *
     * @param seatCategory the value for `price_snapshot`.seat_category
     *
     * @mbggenerated
     */
    public void setSeatCategory(Integer seatCategory) {
        this.seatCategory = seatCategory;
    }

    /**
     *城市id
     *`price_snapshot`.city_id
     *
     * @return the value of `price_snapshot`.city_id
     *
     * @mbggenerated
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     *城市id
     *`price_snapshot`.city_id
     *
     * @param cityId the value for `price_snapshot`.city_id
     *
     * @mbggenerated
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     *机场id
     *`price_snapshot`.airport_id
     *
     * @return the value of `price_snapshot`.airport_id
     *
     * @mbggenerated
     */
    public Integer getAirportId() {
        return airportId;
    }

    /**
     *机场id
     *`price_snapshot`.airport_id
     *
     * @param airportId the value for `price_snapshot`.airport_id
     *
     * @mbggenerated
     */
    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }

    /**
     *机场三字码
     *`price_snapshot`.airport_code
     *
     * @return the value of `price_snapshot`.airport_code
     *
     * @mbggenerated
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     *机场三字码
     *`price_snapshot`.airport_code
     *
     * @param airportCode the value for `price_snapshot`.airport_code
     *
     * @mbggenerated
     */
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode == null ? null : airportCode.trim();
    }

    /**
     *服务时间
     *`price_snapshot`.service_date
     *
     * @return the value of `price_snapshot`.service_date
     *
     * @mbggenerated
     */
    public String getServiceDate() {
        return serviceDate;
    }

    /**
     *服务时间
     *`price_snapshot`.service_date
     *
     * @param serviceDate the value for `price_snapshot`.service_date
     *
     * @mbggenerated
     */
    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate == null ? null : serviceDate.trim();
    }

    /**
     *起始点坐标（纬度,经度）
     *`price_snapshot`.start_location
     *
     * @return the value of `price_snapshot`.start_location
     *
     * @mbggenerated
     */
    public String getStartLocation() {
        return startLocation;
    }

    /**
     *起始点坐标（纬度,经度）
     *`price_snapshot`.start_location
     *
     * @param startLocation the value for `price_snapshot`.start_location
     *
     * @mbggenerated
     */
    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation == null ? null : startLocation.trim();
    }

    /**
     *目的点坐标（纬度,经度）
     *`price_snapshot`.end_location
     *
     * @return the value of `price_snapshot`.end_location
     *
     * @mbggenerated
     */
    public String getEndLocation() {
        return endLocation;
    }

    /**
     *目的点坐标（纬度,经度）
     *`price_snapshot`.end_location
     *
     * @param endLocation the value for `price_snapshot`.end_location
     *
     * @mbggenerated
     */
    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation == null ? null : endLocation.trim();
    }

    /**
     *是否急单报价
     *`price_snapshot`.is_urgent
     *
     * @return the value of `price_snapshot`.is_urgent
     *
     * @mbggenerated
     */
    public Integer getIsUrgent() {
        return isUrgent;
    }

    /**
     *是否急单报价
     *`price_snapshot`.is_urgent
     *
     * @param isUrgent the value for `price_snapshot`.is_urgent
     *
     * @mbggenerated
     */
    public void setIsUrgent(Integer isUrgent) {
        this.isUrgent = isUrgent;
    }

    /**
     *系统价
     *`price_snapshot`.sys_price
     *
     * @return the value of `price_snapshot`.sys_price
     *
     * @mbggenerated
     */
    public Double getSysPrice() {
        return sysPrice;
    }

    /**
     *系统价
     *`price_snapshot`.sys_price
     *
     * @param sysPrice the value for `price_snapshot`.sys_price
     *
     * @mbggenerated
     */
    public void setSysPrice(Double sysPrice) {
        this.sysPrice = sysPrice;
    }

    /**
     *票面价
     *`price_snapshot`.ticket_price
     *
     * @return the value of `price_snapshot`.ticket_price
     *
     * @mbggenerated
     */
    public Double getTicketPrice() {
        return ticketPrice;
    }

    /**
     *票面价
     *`price_snapshot`.ticket_price
     *
     * @param ticketPrice the value for `price_snapshot`.ticket_price
     *
     * @mbggenerated
     */
    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     *导游价
     *`price_snapshot`.guide_price
     *
     * @return the value of `price_snapshot`.guide_price
     *
     * @mbggenerated
     */
    public Double getGuidePrice() {
        return guidePrice;
    }

    /**
     *导游价
     *`price_snapshot`.guide_price
     *
     * @param guidePrice the value for `price_snapshot`.guide_price
     *
     * @mbggenerated
     */
    public void setGuidePrice(Double guidePrice) {
        this.guidePrice = guidePrice;
    }

    /**
     *渠道价
     *`price_snapshot`.channel_price
     *
     * @return the value of `price_snapshot`.channel_price
     *
     * @mbggenerated
     */
    public Double getChannelPrice() {
        return channelPrice;
    }

    /**
     *渠道价
     *`price_snapshot`.channel_price
     *
     * @param channelPrice the value for `price_snapshot`.channel_price
     *
     * @mbggenerated
     */
    public void setChannelPrice(Double channelPrice) {
        this.channelPrice = channelPrice;
    }

    /**
     *
     *`price_snapshot`.sys_slices
     *
     * @return the value of `price_snapshot`.sys_slices
     *
     * @mbggenerated
     */
    public String getSysSlices() {
        return sysSlices;
    }

    /**
     *
     *`price_snapshot`.sys_slices
     *
     * @param sysSlices the value for `price_snapshot`.sys_slices
     *
     * @mbggenerated
     */
    public void setSysSlices(String sysSlices) {
        this.sysSlices = sysSlices == null ? null : sysSlices.trim();
    }

    /**
     *
     *`price_snapshot`.ticket_slices
     *
     * @return the value of `price_snapshot`.ticket_slices
     *
     * @mbggenerated
     */
    public String getTicketSlices() {
        return ticketSlices;
    }

    /**
     *
     *`price_snapshot`.ticket_slices
     *
     * @param ticketSlices the value for `price_snapshot`.ticket_slices
     *
     * @mbggenerated
     */
    public void setTicketSlices(String ticketSlices) {
        this.ticketSlices = ticketSlices == null ? null : ticketSlices.trim();
    }

    /**
     *
     *`price_snapshot`.channel_slices
     *
     * @return the value of `price_snapshot`.channel_slices
     *
     * @mbggenerated
     */
    public String getChannelSlices() {
        return channelSlices;
    }

    /**
     *
     *`price_snapshot`.channel_slices
     *
     * @param channelSlices the value for `price_snapshot`.channel_slices
     *
     * @mbggenerated
     */
    public void setChannelSlices(String channelSlices) {
        this.channelSlices = channelSlices == null ? null : channelSlices.trim();
    }

    /**
     *
     *`price_snapshot`.guide_slices
     *
     * @return the value of `price_snapshot`.guide_slices
     *
     * @mbggenerated
     */
    public String getGuideSlices() {
        return guideSlices;
    }

    /**
     *
     *`price_snapshot`.guide_slices
     *
     * @param guideSlices the value for `price_snapshot`.guide_slices
     *
     * @mbggenerated
     */
    public void setGuideSlices(String guideSlices) {
        this.guideSlices = guideSlices == null ? null : guideSlices.trim();
    }

    /**
     *价格计算因素
     *`price_snapshot`.price_factor
     *
     * @return the value of `price_snapshot`.price_factor
     *
     * @mbggenerated
     */
    public String getPriceFactor() {
        return priceFactor;
    }

    /**
     *价格计算因素
     *`price_snapshot`.price_factor
     *
     * @param priceFactor the value for `price_snapshot`.price_factor
     *
     * @mbggenerated
     */
    public void setPriceFactor(String priceFactor) {
        this.priceFactor = priceFactor == null ? null : priceFactor.trim();
    }

    /**
     *接送次距离
     *`price_snapshot`.distance
     *
     * @return the value of `price_snapshot`.distance
     *
     * @mbggenerated
     */
    public Double getDistance() {
        return distance;
    }

    /**
     *接送次距离
     *`price_snapshot`.distance
     *
     * @param distance the value for `price_snapshot`.distance
     *
     * @mbggenerated
     */
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    /**
     *接送次预估时间
     *`price_snapshot`.est_time
     *
     * @return the value of `price_snapshot`.est_time
     *
     * @mbggenerated
     */
    public Integer getEstTime() {
        return estTime;
    }

    /**
     *接送次预估时间
     *`price_snapshot`.est_time
     *
     * @param estTime the value for `price_snapshot`.est_time
     *
     * @mbggenerated
     */
    public void setEstTime(Integer estTime) {
        this.estTime = estTime;
    }

    /**
     *超时费/小时(RMB) 日租有效
     *`price_snapshot`.charge_per_hour
     *
     * @return the value of `price_snapshot`.charge_per_hour
     *
     * @mbggenerated
     */
    public Double getChargePerHour() {
        return chargePerHour;
    }

    /**
     *超时费/小时(RMB) 日租有效
     *`price_snapshot`.charge_per_hour
     *
     * @param chargePerHour the value for `price_snapshot`.charge_per_hour
     *
     * @mbggenerated
     */
    public void setChargePerHour(Double chargePerHour) {
        this.chargePerHour = chargePerHour;
    }

    /**
     *超公里费/KM(RMB) 日租有效
     *`price_snapshot`.charge_per_km
     *
     * @return the value of `price_snapshot`.charge_per_km
     *
     * @mbggenerated
     */
    public Double getChargePerKm() {
        return chargePerKm;
    }

    /**
     *超公里费/KM(RMB) 日租有效
     *`price_snapshot`.charge_per_km
     *
     * @param chargePerKm the value for `price_snapshot`.charge_per_km
     *
     * @mbggenerated
     */
    public void setChargePerKm(Double chargePerKm) {
        this.chargePerKm = chargePerKm;
    }

    /**
     *超时费/天(RMB) 日租有效
     *`price_snapshot`.charge_per_day
     *
     * @return the value of `price_snapshot`.charge_per_day
     *
     * @mbggenerated
     */
    public Double getChargePerDay() {
        return chargePerDay;
    }

    /**
     *超时费/天(RMB) 日租有效
     *`price_snapshot`.charge_per_day
     *
     * @param chargePerDay the value for `price_snapshot`.charge_per_day
     *
     * @mbggenerated
     */
    public void setChargePerDay(Double chargePerDay) {
        this.chargePerDay = chargePerDay;
    }

    /**
     *超时等待费/分钟(RMB) 接送次有效
     *`price_snapshot`.charge_per_minute
     *
     * @return the value of `price_snapshot`.charge_per_minute
     *
     * @mbggenerated
     */
    public Double getChargePerMinute() {
        return chargePerMinute;
    }

    /**
     *超时等待费/分钟(RMB) 接送次有效
     *`price_snapshot`.charge_per_minute
     *
     * @param chargePerMinute the value for `price_snapshot`.charge_per_minute
     *
     * @mbggenerated
     */
    public void setChargePerMinute(Double chargePerMinute) {
        this.chargePerMinute = chargePerMinute;
    }

    /**
     *过期时间
     *`price_snapshot`.expired_time
     *
     * @return the value of `price_snapshot`.expired_time
     *
     * @mbggenerated
     */
    public Date getExpiredTime() {
        return expiredTime;
    }

    /**
     *过期时间
     *`price_snapshot`.expired_time
     *
     * @param expiredTime the value for `price_snapshot`.expired_time
     *
     * @mbggenerated
     */
    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    /**
     *0.异常 1. 正常 
     *`price_snapshot`.status
     *
     * @return the value of `price_snapshot`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *0.异常 1. 正常 
     *`price_snapshot`.status
     *
     * @param status the value for `price_snapshot`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     *`price_snapshot`.price_version
     *
     * @return the value of `price_snapshot`.price_version
     *
     * @mbggenerated
     */
    public Integer getPriceVersion() {
        return priceVersion;
    }

    /**
     *
     *`price_snapshot`.price_version
     *
     * @param priceVersion the value for `price_snapshot`.price_version
     *
     * @mbggenerated
     */
    public void setPriceVersion(Integer priceVersion) {
        this.priceVersion = priceVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `price_snapshot`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serviceType=").append(serviceType);
        sb.append(", channel=").append(channel);
        sb.append(", pricemark=").append(pricemark);
        sb.append(", currencyRate=").append(currencyRate);
        sb.append(", carType=").append(carType);
        sb.append(", seatCategory=").append(seatCategory);
        sb.append(", cityId=").append(cityId);
        sb.append(", airportId=").append(airportId);
        sb.append(", airportCode=").append(airportCode);
        sb.append(", serviceDate=").append(serviceDate);
        sb.append(", startLocation=").append(startLocation);
        sb.append(", endLocation=").append(endLocation);
        sb.append(", isUrgent=").append(isUrgent);
        sb.append(", sysPrice=").append(sysPrice);
        sb.append(", ticketPrice=").append(ticketPrice);
        sb.append(", guidePrice=").append(guidePrice);
        sb.append(", channelPrice=").append(channelPrice);
        sb.append(", sysSlices=").append(sysSlices);
        sb.append(", ticketSlices=").append(ticketSlices);
        sb.append(", channelSlices=").append(channelSlices);
        sb.append(", guideSlices=").append(guideSlices);
        sb.append(", priceFactor=").append(priceFactor);
        sb.append(", distance=").append(distance);
        sb.append(", estTime=").append(estTime);
        sb.append(", chargePerHour=").append(chargePerHour);
        sb.append(", chargePerKm=").append(chargePerKm);
        sb.append(", chargePerDay=").append(chargePerDay);
        sb.append(", chargePerMinute=").append(chargePerMinute);
        sb.append(", expiredTime=").append(expiredTime);
        sb.append(", status=").append(status);
        sb.append(", priceVersion=").append(priceVersion);
        sb.append("]");
        return sb.toString();
    }
}