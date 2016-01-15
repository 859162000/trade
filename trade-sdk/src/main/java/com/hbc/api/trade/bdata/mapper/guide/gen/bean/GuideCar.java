package com.hbc.api.trade.bdata.mapper.guide.gen.bean;

import java.util.Date;

public class GuideCar {
    /**
     *  
     *  所属表字段为`guide_car`.guide_car_id
     */
    private Integer guideCarId;

    /**
     *  导游id
     *  所属表字段为`guide_car`.guide_id
     */
    private String guideId;

    /**
     *  车辆id
     *  所属表字段为`guide_car`.car_id
     */
    private Integer carId;

    /**
     *  车牌号
     *  所属表字段为`guide_car`.car_licence_no
     */
    private String carLicenceNo;

    /**
     *  车辆购买日期
     *  所属表字段为`guide_car`.car_birth
     */
    private Date carBirth;

    /**
     *  配置。逗号分隔存储。1-一次性拖鞋；2-多功能车载充电器；3-车载WIFI；4-饮料；5-儿童座椅；6-雨伞；7-车载冰箱；8-独立乘客区；9-纸巾；10-垃圾袋；11-特色小吃；12-书籍杂志；13-纪念品；14-热水壶；
     *  所属表字段为`guide_car`.equipment
     */
    private String equipment;

    /**
     *  其他车辆 多个以,号分割
     *  所属表字段为`guide_car`.other_car
     */
    private String otherCar;

    /**
     *  是否为该导游默认的车辆。0-否；1-是（默认）。目前认为一个导游只有一辆车
     *  所属表字段为`guide_car`.is_default
     */
    private Integer isDefault;

    /**
     *  
     *  所属表字段为`guide_car`.car_name
     */
    private String carName;

    /**
     *  1-经济型;2-舒适型;3-豪华型;4-奢华型
     *  所属表字段为`guide_car`.car_type
     */
    private Integer carType;

    /**
     *  5-5座车系;7-7座车系;9-9座车系;12-12座车系
     *  所属表字段为`guide_car`.car_class
     */
    private Integer carClass;

    /**
     *  
     *  所属表字段为`guide_car`.car_brand_id
     */
    private Integer carBrandId;

    /**
     *  
     *  所属表字段为`guide_car`.car_brand_name
     */
    private String carBrandName;

    /**
     *  最多乘坐人数
     *  所属表字段为`guide_car`.car_guest_num
     */
    private Integer carGuestNum;

    /**
     *  行李箱数
     *  所属表字段为`guide_car`.car_luggage_num
     */
    private Integer carLuggageNum;

    /**
     *  
     *  所属表字段为`guide_car`.car_seat_num
     */
    private Integer carSeatNum;

    /**
     *  车辆级别是否是系统设置 1-是;0-不是
     *  所属表字段为`guide_car`.set_mode
     */
    private Integer setMode;

    /**
     *  备注信息
     *  所属表字段为`guide_car`.comment
     */
    private String comment;

    /**
     *  
     *  所属表字段为`guide_car`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`guide_car`.create_time
     */
    private Date createTime;

    /**
     *
     *`guide_car`.guide_car_id
     *
     * @return the value of `guide_car`.guide_car_id
     *
     * @mbggenerated
     */
    public Integer getGuideCarId() {
        return guideCarId;
    }

    /**
     *
     *`guide_car`.guide_car_id
     *
     * @param guideCarId the value for `guide_car`.guide_car_id
     *
     * @mbggenerated
     */
    public void setGuideCarId(Integer guideCarId) {
        this.guideCarId = guideCarId;
    }

    /**
     *导游id
     *`guide_car`.guide_id
     *
     * @return the value of `guide_car`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *导游id
     *`guide_car`.guide_id
     *
     * @param guideId the value for `guide_car`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *车辆id
     *`guide_car`.car_id
     *
     * @return the value of `guide_car`.car_id
     *
     * @mbggenerated
     */
    public Integer getCarId() {
        return carId;
    }

    /**
     *车辆id
     *`guide_car`.car_id
     *
     * @param carId the value for `guide_car`.car_id
     *
     * @mbggenerated
     */
    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    /**
     *车牌号
     *`guide_car`.car_licence_no
     *
     * @return the value of `guide_car`.car_licence_no
     *
     * @mbggenerated
     */
    public String getCarLicenceNo() {
        return carLicenceNo;
    }

    /**
     *车牌号
     *`guide_car`.car_licence_no
     *
     * @param carLicenceNo the value for `guide_car`.car_licence_no
     *
     * @mbggenerated
     */
    public void setCarLicenceNo(String carLicenceNo) {
        this.carLicenceNo = carLicenceNo == null ? null : carLicenceNo.trim();
    }

    /**
     *车辆购买日期
     *`guide_car`.car_birth
     *
     * @return the value of `guide_car`.car_birth
     *
     * @mbggenerated
     */
    public Date getCarBirth() {
        return carBirth;
    }

    /**
     *车辆购买日期
     *`guide_car`.car_birth
     *
     * @param carBirth the value for `guide_car`.car_birth
     *
     * @mbggenerated
     */
    public void setCarBirth(Date carBirth) {
        this.carBirth = carBirth;
    }

    /**
     *配置。逗号分隔存储。1-一次性拖鞋；2-多功能车载充电器；3-车载WIFI；4-饮料；5-儿童座椅；6-雨伞；7-车载冰箱；8-独立乘客区；9-纸巾；10-垃圾袋；11-特色小吃；12-书籍杂志；13-纪念品；14-热水壶；
     *`guide_car`.equipment
     *
     * @return the value of `guide_car`.equipment
     *
     * @mbggenerated
     */
    public String getEquipment() {
        return equipment;
    }

    /**
     *配置。逗号分隔存储。1-一次性拖鞋；2-多功能车载充电器；3-车载WIFI；4-饮料；5-儿童座椅；6-雨伞；7-车载冰箱；8-独立乘客区；9-纸巾；10-垃圾袋；11-特色小吃；12-书籍杂志；13-纪念品；14-热水壶；
     *`guide_car`.equipment
     *
     * @param equipment the value for `guide_car`.equipment
     *
     * @mbggenerated
     */
    public void setEquipment(String equipment) {
        this.equipment = equipment == null ? null : equipment.trim();
    }

    /**
     *其他车辆 多个以,号分割
     *`guide_car`.other_car
     *
     * @return the value of `guide_car`.other_car
     *
     * @mbggenerated
     */
    public String getOtherCar() {
        return otherCar;
    }

    /**
     *其他车辆 多个以,号分割
     *`guide_car`.other_car
     *
     * @param otherCar the value for `guide_car`.other_car
     *
     * @mbggenerated
     */
    public void setOtherCar(String otherCar) {
        this.otherCar = otherCar == null ? null : otherCar.trim();
    }

    /**
     *是否为该导游默认的车辆。0-否；1-是（默认）。目前认为一个导游只有一辆车
     *`guide_car`.is_default
     *
     * @return the value of `guide_car`.is_default
     *
     * @mbggenerated
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     *是否为该导游默认的车辆。0-否；1-是（默认）。目前认为一个导游只有一辆车
     *`guide_car`.is_default
     *
     * @param isDefault the value for `guide_car`.is_default
     *
     * @mbggenerated
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    /**
     *
     *`guide_car`.car_name
     *
     * @return the value of `guide_car`.car_name
     *
     * @mbggenerated
     */
    public String getCarName() {
        return carName;
    }

    /**
     *
     *`guide_car`.car_name
     *
     * @param carName the value for `guide_car`.car_name
     *
     * @mbggenerated
     */
    public void setCarName(String carName) {
        this.carName = carName == null ? null : carName.trim();
    }

    /**
     *1-经济型;2-舒适型;3-豪华型;4-奢华型
     *`guide_car`.car_type
     *
     * @return the value of `guide_car`.car_type
     *
     * @mbggenerated
     */
    public Integer getCarType() {
        return carType;
    }

    /**
     *1-经济型;2-舒适型;3-豪华型;4-奢华型
     *`guide_car`.car_type
     *
     * @param carType the value for `guide_car`.car_type
     *
     * @mbggenerated
     */
    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    /**
     *5-5座车系;7-7座车系;9-9座车系;12-12座车系
     *`guide_car`.car_class
     *
     * @return the value of `guide_car`.car_class
     *
     * @mbggenerated
     */
    public Integer getCarClass() {
        return carClass;
    }

    /**
     *5-5座车系;7-7座车系;9-9座车系;12-12座车系
     *`guide_car`.car_class
     *
     * @param carClass the value for `guide_car`.car_class
     *
     * @mbggenerated
     */
    public void setCarClass(Integer carClass) {
        this.carClass = carClass;
    }

    /**
     *
     *`guide_car`.car_brand_id
     *
     * @return the value of `guide_car`.car_brand_id
     *
     * @mbggenerated
     */
    public Integer getCarBrandId() {
        return carBrandId;
    }

    /**
     *
     *`guide_car`.car_brand_id
     *
     * @param carBrandId the value for `guide_car`.car_brand_id
     *
     * @mbggenerated
     */
    public void setCarBrandId(Integer carBrandId) {
        this.carBrandId = carBrandId;
    }

    /**
     *
     *`guide_car`.car_brand_name
     *
     * @return the value of `guide_car`.car_brand_name
     *
     * @mbggenerated
     */
    public String getCarBrandName() {
        return carBrandName;
    }

    /**
     *
     *`guide_car`.car_brand_name
     *
     * @param carBrandName the value for `guide_car`.car_brand_name
     *
     * @mbggenerated
     */
    public void setCarBrandName(String carBrandName) {
        this.carBrandName = carBrandName == null ? null : carBrandName.trim();
    }

    /**
     *最多乘坐人数
     *`guide_car`.car_guest_num
     *
     * @return the value of `guide_car`.car_guest_num
     *
     * @mbggenerated
     */
    public Integer getCarGuestNum() {
        return carGuestNum;
    }

    /**
     *最多乘坐人数
     *`guide_car`.car_guest_num
     *
     * @param carGuestNum the value for `guide_car`.car_guest_num
     *
     * @mbggenerated
     */
    public void setCarGuestNum(Integer carGuestNum) {
        this.carGuestNum = carGuestNum;
    }

    /**
     *行李箱数
     *`guide_car`.car_luggage_num
     *
     * @return the value of `guide_car`.car_luggage_num
     *
     * @mbggenerated
     */
    public Integer getCarLuggageNum() {
        return carLuggageNum;
    }

    /**
     *行李箱数
     *`guide_car`.car_luggage_num
     *
     * @param carLuggageNum the value for `guide_car`.car_luggage_num
     *
     * @mbggenerated
     */
    public void setCarLuggageNum(Integer carLuggageNum) {
        this.carLuggageNum = carLuggageNum;
    }

    /**
     *
     *`guide_car`.car_seat_num
     *
     * @return the value of `guide_car`.car_seat_num
     *
     * @mbggenerated
     */
    public Integer getCarSeatNum() {
        return carSeatNum;
    }

    /**
     *
     *`guide_car`.car_seat_num
     *
     * @param carSeatNum the value for `guide_car`.car_seat_num
     *
     * @mbggenerated
     */
    public void setCarSeatNum(Integer carSeatNum) {
        this.carSeatNum = carSeatNum;
    }

    /**
     *车辆级别是否是系统设置 1-是;0-不是
     *`guide_car`.set_mode
     *
     * @return the value of `guide_car`.set_mode
     *
     * @mbggenerated
     */
    public Integer getSetMode() {
        return setMode;
    }

    /**
     *车辆级别是否是系统设置 1-是;0-不是
     *`guide_car`.set_mode
     *
     * @param setMode the value for `guide_car`.set_mode
     *
     * @mbggenerated
     */
    public void setSetMode(Integer setMode) {
        this.setMode = setMode;
    }

    /**
     *备注信息
     *`guide_car`.comment
     *
     * @return the value of `guide_car`.comment
     *
     * @mbggenerated
     */
    public String getComment() {
        return comment;
    }

    /**
     *备注信息
     *`guide_car`.comment
     *
     * @param comment the value for `guide_car`.comment
     *
     * @mbggenerated
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     *
     *`guide_car`.update_time
     *
     * @return the value of `guide_car`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`guide_car`.update_time
     *
     * @param updateTime the value for `guide_car`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`guide_car`.create_time
     *
     * @return the value of `guide_car`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`guide_car`.create_time
     *
     * @param createTime the value for `guide_car`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guideCarId=").append(guideCarId);
        sb.append(", guideId=").append(guideId);
        sb.append(", carId=").append(carId);
        sb.append(", carLicenceNo=").append(carLicenceNo);
        sb.append(", carBirth=").append(carBirth);
        sb.append(", equipment=").append(equipment);
        sb.append(", otherCar=").append(otherCar);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", carName=").append(carName);
        sb.append(", carType=").append(carType);
        sb.append(", carClass=").append(carClass);
        sb.append(", carBrandId=").append(carBrandId);
        sb.append(", carBrandName=").append(carBrandName);
        sb.append(", carGuestNum=").append(carGuestNum);
        sb.append(", carLuggageNum=").append(carLuggageNum);
        sb.append(", carSeatNum=").append(carSeatNum);
        sb.append(", setMode=").append(setMode);
        sb.append(", comment=").append(comment);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}