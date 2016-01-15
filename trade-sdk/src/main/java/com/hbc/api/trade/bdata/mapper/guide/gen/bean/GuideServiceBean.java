package com.hbc.api.trade.bdata.mapper.guide.gen.bean;

import java.util.Date;

public class GuideServiceBean {
    /**
     *  
     *  所属表字段为`guide_service`.guide_service_id
     */
    private Integer guideServiceId;

    /**
     *  
     *  所属表字段为`guide_service`.guide_id
     */
    private String guideId;

    /**
     *  服务语言。逗号分隔存储langCode
     *  所属表字段为`guide_service`.lang_codes
     */
    private String langCodes;

    /**
     *  带团经验-家庭小团。1：1-5次；2：6-10次；3：11-20次；4：20次以上
     *  所属表字段为`guide_service`.expFamily
     */
    private Integer expfamily;

    /**
     *  带团经验-商务大团。1：1-5次；2：6-10次；3：11-20次；4：20次以上
     *  所属表字段为`guide_service`.expBusiness
     */
    private Integer expbusiness;

    /**
     *  带团经验-跨城市跨国家接待。1：1-5次；2：6-10次；3：11-20次；4：20次以上
     *  所属表字段为`guide_service`.expCross
     */
    private Integer expcross;

    /**
     *  个人导游风格。1-主动沟通；2-活力四射；3-成熟稳重；4-细心周到
     *  所属表字段为`guide_service`.style
     */
    private Integer style;

    /**
     *  熟悉-经典景点。1-一般；2-熟悉；3-非常熟悉
     *  所属表字段为`guide_service`.expSpot
     */
    private Integer expspot;

    /**
     *  熟悉-学校。1-一般；2-熟悉；3-非常熟悉
     *  所属表字段为`guide_service`.expSchool
     */
    private Integer expschool;

    /**
     *  熟悉-购物场所。1-一般；2-熟悉；3-非常熟悉
     *  所属表字段为`guide_service`.expShopping
     */
    private Integer expshopping;

    /**
     *  熟悉-历史人文故居。1-一般；2-熟悉；3-非常熟悉
     *  所属表字段为`guide_service`.expHistory
     */
    private Integer exphistory;

    /**
     *  熟悉-交通情况。1-一般；2-熟悉；3-非常熟悉
     *  所属表字段为`guide_service`.expTraffic
     */
    private Integer exptraffic;

    /**
     *  熟悉-餐饮美食。1-一般；2-熟悉；3-非常熟悉
     *  所属表字段为`guide_service`.expFood
     */
    private Integer expfood;

    /**
     *  熟悉-法律法规。1-一般；2-熟悉；3-非常熟悉
     *  所属表字段为`guide_service`.expLaw
     */
    private Integer explaw;

    /**
     *  熟悉-风土人情。1-一般；2-熟悉；3-非常熟悉
     *  所属表字段为`guide_service`.expCulture
     */
    private Integer expculture;

    /**
     *  熟悉-酒店住宿。1-一般；2-熟悉；3-非常熟悉
     *  所属表字段为`guide_service`.expHotel
     */
    private Integer exphotel;

    /**
     *  熟悉-本地玩乐。1-一般；2-熟悉；3-非常熟悉
     *  所属表字段为`guide_service`.expPoi
     */
    private Integer exppoi;

    /**
     *  更新时间
     *  所属表字段为`guide_service`.update_time
     */
    private Date updateTime;

    /**
     *  创建时间
     *  所属表字段为`guide_service`.create_time
     */
    private Date createTime;

    /**
     *
     *`guide_service`.guide_service_id
     *
     * @return the value of `guide_service`.guide_service_id
     *
     * @mbggenerated
     */
    public Integer getGuideServiceId() {
        return guideServiceId;
    }

    /**
     *
     *`guide_service`.guide_service_id
     *
     * @param guideServiceId the value for `guide_service`.guide_service_id
     *
     * @mbggenerated
     */
    public void setGuideServiceId(Integer guideServiceId) {
        this.guideServiceId = guideServiceId;
    }

    /**
     *
     *`guide_service`.guide_id
     *
     * @return the value of `guide_service`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *
     *`guide_service`.guide_id
     *
     * @param guideId the value for `guide_service`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *服务语言。逗号分隔存储langCode
     *`guide_service`.lang_codes
     *
     * @return the value of `guide_service`.lang_codes
     *
     * @mbggenerated
     */
    public String getLangCodes() {
        return langCodes;
    }

    /**
     *服务语言。逗号分隔存储langCode
     *`guide_service`.lang_codes
     *
     * @param langCodes the value for `guide_service`.lang_codes
     *
     * @mbggenerated
     */
    public void setLangCodes(String langCodes) {
        this.langCodes = langCodes == null ? null : langCodes.trim();
    }

    /**
     *带团经验-家庭小团。1：1-5次；2：6-10次；3：11-20次；4：20次以上
     *`guide_service`.expFamily
     *
     * @return the value of `guide_service`.expFamily
     *
     * @mbggenerated
     */
    public Integer getExpfamily() {
        return expfamily;
    }

    /**
     *带团经验-家庭小团。1：1-5次；2：6-10次；3：11-20次；4：20次以上
     *`guide_service`.expFamily
     *
     * @param expfamily the value for `guide_service`.expFamily
     *
     * @mbggenerated
     */
    public void setExpfamily(Integer expfamily) {
        this.expfamily = expfamily;
    }

    /**
     *带团经验-商务大团。1：1-5次；2：6-10次；3：11-20次；4：20次以上
     *`guide_service`.expBusiness
     *
     * @return the value of `guide_service`.expBusiness
     *
     * @mbggenerated
     */
    public Integer getExpbusiness() {
        return expbusiness;
    }

    /**
     *带团经验-商务大团。1：1-5次；2：6-10次；3：11-20次；4：20次以上
     *`guide_service`.expBusiness
     *
     * @param expbusiness the value for `guide_service`.expBusiness
     *
     * @mbggenerated
     */
    public void setExpbusiness(Integer expbusiness) {
        this.expbusiness = expbusiness;
    }

    /**
     *带团经验-跨城市跨国家接待。1：1-5次；2：6-10次；3：11-20次；4：20次以上
     *`guide_service`.expCross
     *
     * @return the value of `guide_service`.expCross
     *
     * @mbggenerated
     */
    public Integer getExpcross() {
        return expcross;
    }

    /**
     *带团经验-跨城市跨国家接待。1：1-5次；2：6-10次；3：11-20次；4：20次以上
     *`guide_service`.expCross
     *
     * @param expcross the value for `guide_service`.expCross
     *
     * @mbggenerated
     */
    public void setExpcross(Integer expcross) {
        this.expcross = expcross;
    }

    /**
     *个人导游风格。1-主动沟通；2-活力四射；3-成熟稳重；4-细心周到
     *`guide_service`.style
     *
     * @return the value of `guide_service`.style
     *
     * @mbggenerated
     */
    public Integer getStyle() {
        return style;
    }

    /**
     *个人导游风格。1-主动沟通；2-活力四射；3-成熟稳重；4-细心周到
     *`guide_service`.style
     *
     * @param style the value for `guide_service`.style
     *
     * @mbggenerated
     */
    public void setStyle(Integer style) {
        this.style = style;
    }

    /**
     *熟悉-经典景点。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expSpot
     *
     * @return the value of `guide_service`.expSpot
     *
     * @mbggenerated
     */
    public Integer getExpspot() {
        return expspot;
    }

    /**
     *熟悉-经典景点。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expSpot
     *
     * @param expspot the value for `guide_service`.expSpot
     *
     * @mbggenerated
     */
    public void setExpspot(Integer expspot) {
        this.expspot = expspot;
    }

    /**
     *熟悉-学校。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expSchool
     *
     * @return the value of `guide_service`.expSchool
     *
     * @mbggenerated
     */
    public Integer getExpschool() {
        return expschool;
    }

    /**
     *熟悉-学校。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expSchool
     *
     * @param expschool the value for `guide_service`.expSchool
     *
     * @mbggenerated
     */
    public void setExpschool(Integer expschool) {
        this.expschool = expschool;
    }

    /**
     *熟悉-购物场所。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expShopping
     *
     * @return the value of `guide_service`.expShopping
     *
     * @mbggenerated
     */
    public Integer getExpshopping() {
        return expshopping;
    }

    /**
     *熟悉-购物场所。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expShopping
     *
     * @param expshopping the value for `guide_service`.expShopping
     *
     * @mbggenerated
     */
    public void setExpshopping(Integer expshopping) {
        this.expshopping = expshopping;
    }

    /**
     *熟悉-历史人文故居。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expHistory
     *
     * @return the value of `guide_service`.expHistory
     *
     * @mbggenerated
     */
    public Integer getExphistory() {
        return exphistory;
    }

    /**
     *熟悉-历史人文故居。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expHistory
     *
     * @param exphistory the value for `guide_service`.expHistory
     *
     * @mbggenerated
     */
    public void setExphistory(Integer exphistory) {
        this.exphistory = exphistory;
    }

    /**
     *熟悉-交通情况。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expTraffic
     *
     * @return the value of `guide_service`.expTraffic
     *
     * @mbggenerated
     */
    public Integer getExptraffic() {
        return exptraffic;
    }

    /**
     *熟悉-交通情况。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expTraffic
     *
     * @param exptraffic the value for `guide_service`.expTraffic
     *
     * @mbggenerated
     */
    public void setExptraffic(Integer exptraffic) {
        this.exptraffic = exptraffic;
    }

    /**
     *熟悉-餐饮美食。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expFood
     *
     * @return the value of `guide_service`.expFood
     *
     * @mbggenerated
     */
    public Integer getExpfood() {
        return expfood;
    }

    /**
     *熟悉-餐饮美食。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expFood
     *
     * @param expfood the value for `guide_service`.expFood
     *
     * @mbggenerated
     */
    public void setExpfood(Integer expfood) {
        this.expfood = expfood;
    }

    /**
     *熟悉-法律法规。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expLaw
     *
     * @return the value of `guide_service`.expLaw
     *
     * @mbggenerated
     */
    public Integer getExplaw() {
        return explaw;
    }

    /**
     *熟悉-法律法规。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expLaw
     *
     * @param explaw the value for `guide_service`.expLaw
     *
     * @mbggenerated
     */
    public void setExplaw(Integer explaw) {
        this.explaw = explaw;
    }

    /**
     *熟悉-风土人情。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expCulture
     *
     * @return the value of `guide_service`.expCulture
     *
     * @mbggenerated
     */
    public Integer getExpculture() {
        return expculture;
    }

    /**
     *熟悉-风土人情。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expCulture
     *
     * @param expculture the value for `guide_service`.expCulture
     *
     * @mbggenerated
     */
    public void setExpculture(Integer expculture) {
        this.expculture = expculture;
    }

    /**
     *熟悉-酒店住宿。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expHotel
     *
     * @return the value of `guide_service`.expHotel
     *
     * @mbggenerated
     */
    public Integer getExphotel() {
        return exphotel;
    }

    /**
     *熟悉-酒店住宿。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expHotel
     *
     * @param exphotel the value for `guide_service`.expHotel
     *
     * @mbggenerated
     */
    public void setExphotel(Integer exphotel) {
        this.exphotel = exphotel;
    }

    /**
     *熟悉-本地玩乐。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expPoi
     *
     * @return the value of `guide_service`.expPoi
     *
     * @mbggenerated
     */
    public Integer getExppoi() {
        return exppoi;
    }

    /**
     *熟悉-本地玩乐。1-一般；2-熟悉；3-非常熟悉
     *`guide_service`.expPoi
     *
     * @param exppoi the value for `guide_service`.expPoi
     *
     * @mbggenerated
     */
    public void setExppoi(Integer exppoi) {
        this.exppoi = exppoi;
    }

    /**
     *更新时间
     *`guide_service`.update_time
     *
     * @return the value of `guide_service`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新时间
     *`guide_service`.update_time
     *
     * @param updateTime the value for `guide_service`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *创建时间
     *`guide_service`.create_time
     *
     * @return the value of `guide_service`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     *`guide_service`.create_time
     *
     * @param createTime the value for `guide_service`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guideServiceId=").append(guideServiceId);
        sb.append(", guideId=").append(guideId);
        sb.append(", langCodes=").append(langCodes);
        sb.append(", expfamily=").append(expfamily);
        sb.append(", expbusiness=").append(expbusiness);
        sb.append(", expcross=").append(expcross);
        sb.append(", style=").append(style);
        sb.append(", expspot=").append(expspot);
        sb.append(", expschool=").append(expschool);
        sb.append(", expshopping=").append(expshopping);
        sb.append(", exphistory=").append(exphistory);
        sb.append(", exptraffic=").append(exptraffic);
        sb.append(", expfood=").append(expfood);
        sb.append(", explaw=").append(explaw);
        sb.append(", expculture=").append(expculture);
        sb.append(", exphotel=").append(exphotel);
        sb.append(", exppoi=").append(exppoi);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}