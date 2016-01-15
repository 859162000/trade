package com.hbc.api.trade.bdata.mapper.basedata.gen.bean;

import java.util.Date;

public class Car {
    /**
     *  车型编号
     *  所属表字段为`basedata_car`.car_id
     */
    private Integer carId;

    /**
     *  品牌编号
     *  所属表字段为`basedata_car`.car_brand_id
     */
    private Integer carBrandId;

    /**
     *  车辆型号
     *  所属表字段为`basedata_car`.car_model
     */
    private String carModel;

    /**
     *  车辆类型 [1 => '经济型', 2 => '舒适型', 3 => '豪华型', 4 => '奢华型']
     *  所属表字段为`basedata_car`.car_type
     */
    private Integer carType;

    /**
     *  别名
     *  所属表字段为`basedata_car`.alias
     */
    private String alias;

    /**
     *  车辆级别 [1 => '5座车系', 2 => '7座车系', 3 => '9座车系', 4 => '12座车系']
     *  所属表字段为`basedata_car`.seat_type
     */
    private Integer seatType;

    /**
     *  座位数 [1 => 5, 2 => 7, 3 => 9, 4 => 12]
     *  所属表字段为`basedata_car`.seat_num
     */
    private Integer seatNum;

    /**
     *  座位分档

[0 => '未知', 1 => 'SUV', 2 => 'MPV', 3 => '小型车', 4 => '中型车', 5 => '中大型车', 6 => '大型车', 7 => '旅行车', 8 => '跑车', 9 => '紧凑型轿车', 10 => '新能源', 11 => '皮卡', 12 => 'Van', 13 => 'Mini Van']
     *  所属表字段为`basedata_car`.car_class
     */
    private Integer carClass;

    /**
     *  最多乘坐人数
     *  所属表字段为`basedata_car`.guest_num
     */
    private Integer guestNum;

    /**
     *  行李箱数
     *  所属表字段为`basedata_car`.luggage_num
     */
    private Integer luggageNum;

    /**
     *  
     *  所属表字段为`basedata_car`.spell
     */
    private String spell;

    /**
     *  
     *  所属表字段为`basedata_car`.en_name
     */
    private String enName;

    /**
     *  
     *  所属表字段为`basedata_car`.updated_at
     */
    private Date updatedAt;

    /**
     *  
     *  所属表字段为`basedata_car`.created_at
     */
    private Date createdAt;

    /**
     *车型编号
     *`basedata_car`.car_id
     *
     * @return the value of `basedata_car`.car_id
     *
     * @mbggenerated
     */
    public Integer getCarId() {
        return carId;
    }

    /**
     *车型编号
     *`basedata_car`.car_id
     *
     * @param carId the value for `basedata_car`.car_id
     *
     * @mbggenerated
     */
    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    /**
     *品牌编号
     *`basedata_car`.car_brand_id
     *
     * @return the value of `basedata_car`.car_brand_id
     *
     * @mbggenerated
     */
    public Integer getCarBrandId() {
        return carBrandId;
    }

    /**
     *品牌编号
     *`basedata_car`.car_brand_id
     *
     * @param carBrandId the value for `basedata_car`.car_brand_id
     *
     * @mbggenerated
     */
    public void setCarBrandId(Integer carBrandId) {
        this.carBrandId = carBrandId;
    }

    /**
     *车辆型号
     *`basedata_car`.car_model
     *
     * @return the value of `basedata_car`.car_model
     *
     * @mbggenerated
     */
    public String getCarModel() {
        return carModel;
    }

    /**
     *车辆型号
     *`basedata_car`.car_model
     *
     * @param carModel the value for `basedata_car`.car_model
     *
     * @mbggenerated
     */
    public void setCarModel(String carModel) {
        this.carModel = carModel == null ? null : carModel.trim();
    }

    /**
     *车辆类型 [1 => '经济型', 2 => '舒适型', 3 => '豪华型', 4 => '奢华型']
     *`basedata_car`.car_type
     *
     * @return the value of `basedata_car`.car_type
     *
     * @mbggenerated
     */
    public Integer getCarType() {
        return carType;
    }

    /**
     *车辆类型 [1 => '经济型', 2 => '舒适型', 3 => '豪华型', 4 => '奢华型']
     *`basedata_car`.car_type
     *
     * @param carType the value for `basedata_car`.car_type
     *
     * @mbggenerated
     */
    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    /**
     *别名
     *`basedata_car`.alias
     *
     * @return the value of `basedata_car`.alias
     *
     * @mbggenerated
     */
    public String getAlias() {
        return alias;
    }

    /**
     *别名
     *`basedata_car`.alias
     *
     * @param alias the value for `basedata_car`.alias
     *
     * @mbggenerated
     */
    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    /**
     *车辆级别 [1 => '5座车系', 2 => '7座车系', 3 => '9座车系', 4 => '12座车系']
     *`basedata_car`.seat_type
     *
     * @return the value of `basedata_car`.seat_type
     *
     * @mbggenerated
     */
    public Integer getSeatType() {
        return seatType;
    }

    /**
     *车辆级别 [1 => '5座车系', 2 => '7座车系', 3 => '9座车系', 4 => '12座车系']
     *`basedata_car`.seat_type
     *
     * @param seatType the value for `basedata_car`.seat_type
     *
     * @mbggenerated
     */
    public void setSeatType(Integer seatType) {
        this.seatType = seatType;
    }

    /**
     *座位数 [1 => 5, 2 => 7, 3 => 9, 4 => 12]
     *`basedata_car`.seat_num
     *
     * @return the value of `basedata_car`.seat_num
     *
     * @mbggenerated
     */
    public Integer getSeatNum() {
        return seatNum;
    }

    /**
     *座位数 [1 => 5, 2 => 7, 3 => 9, 4 => 12]
     *`basedata_car`.seat_num
     *
     * @param seatNum the value for `basedata_car`.seat_num
     *
     * @mbggenerated
     */
    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    /**
     *座位分档

[0 => '未知', 1 => 'SUV', 2 => 'MPV', 3 => '小型车', 4 => '中型车', 5 => '中大型车', 6 => '大型车', 7 => '旅行车', 8 => '跑车', 9 => '紧凑型轿车', 10 => '新能源', 11 => '皮卡', 12 => 'Van', 13 => 'Mini Van']
     *`basedata_car`.car_class
     *
     * @return the value of `basedata_car`.car_class
     *
     * @mbggenerated
     */
    public Integer getCarClass() {
        return carClass;
    }

    /**
     *座位分档

[0 => '未知', 1 => 'SUV', 2 => 'MPV', 3 => '小型车', 4 => '中型车', 5 => '中大型车', 6 => '大型车', 7 => '旅行车', 8 => '跑车', 9 => '紧凑型轿车', 10 => '新能源', 11 => '皮卡', 12 => 'Van', 13 => 'Mini Van']
     *`basedata_car`.car_class
     *
     * @param carClass the value for `basedata_car`.car_class
     *
     * @mbggenerated
     */
    public void setCarClass(Integer carClass) {
        this.carClass = carClass;
    }

    /**
     *最多乘坐人数
     *`basedata_car`.guest_num
     *
     * @return the value of `basedata_car`.guest_num
     *
     * @mbggenerated
     */
    public Integer getGuestNum() {
        return guestNum;
    }

    /**
     *最多乘坐人数
     *`basedata_car`.guest_num
     *
     * @param guestNum the value for `basedata_car`.guest_num
     *
     * @mbggenerated
     */
    public void setGuestNum(Integer guestNum) {
        this.guestNum = guestNum;
    }

    /**
     *行李箱数
     *`basedata_car`.luggage_num
     *
     * @return the value of `basedata_car`.luggage_num
     *
     * @mbggenerated
     */
    public Integer getLuggageNum() {
        return luggageNum;
    }

    /**
     *行李箱数
     *`basedata_car`.luggage_num
     *
     * @param luggageNum the value for `basedata_car`.luggage_num
     *
     * @mbggenerated
     */
    public void setLuggageNum(Integer luggageNum) {
        this.luggageNum = luggageNum;
    }

    /**
     *
     *`basedata_car`.spell
     *
     * @return the value of `basedata_car`.spell
     *
     * @mbggenerated
     */
    public String getSpell() {
        return spell;
    }

    /**
     *
     *`basedata_car`.spell
     *
     * @param spell the value for `basedata_car`.spell
     *
     * @mbggenerated
     */
    public void setSpell(String spell) {
        this.spell = spell == null ? null : spell.trim();
    }

    /**
     *
     *`basedata_car`.en_name
     *
     * @return the value of `basedata_car`.en_name
     *
     * @mbggenerated
     */
    public String getEnName() {
        return enName;
    }

    /**
     *
     *`basedata_car`.en_name
     *
     * @param enName the value for `basedata_car`.en_name
     *
     * @mbggenerated
     */
    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    /**
     *
     *`basedata_car`.updated_at
     *
     * @return the value of `basedata_car`.updated_at
     *
     * @mbggenerated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     *`basedata_car`.updated_at
     *
     * @param updatedAt the value for `basedata_car`.updated_at
     *
     * @mbggenerated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     *`basedata_car`.created_at
     *
     * @return the value of `basedata_car`.created_at
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     *
     *`basedata_car`.created_at
     *
     * @param createdAt the value for `basedata_car`.created_at
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `basedata_car`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", carId=").append(carId);
        sb.append(", carBrandId=").append(carBrandId);
        sb.append(", carModel=").append(carModel);
        sb.append(", carType=").append(carType);
        sb.append(", alias=").append(alias);
        sb.append(", seatType=").append(seatType);
        sb.append(", seatNum=").append(seatNum);
        sb.append(", carClass=").append(carClass);
        sb.append(", guestNum=").append(guestNum);
        sb.append(", luggageNum=").append(luggageNum);
        sb.append(", spell=").append(spell);
        sb.append(", enName=").append(enName);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append("]");
        return sb.toString();
    }
}