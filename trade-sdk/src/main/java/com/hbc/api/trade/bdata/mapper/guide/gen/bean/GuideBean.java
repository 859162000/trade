package com.hbc.api.trade.bdata.mapper.guide.gen.bean;

import java.util.Date;

public class GuideBean {
    /**
     *  导游id
     *  所属表字段为`guide`.guide_id
     */
    private String guideId;

    /**
     *  导游业务编号
     *  所属表字段为`guide`.guide_no
     */
    private String guideNo;

    /**
     *  导游名称
     *  所属表字段为`guide`.guide_name
     */
    private String guideName;

    /**
     *  国际区号
     *  所属表字段为`guide`.area_code
     */
    private String areaCode;

    /**
     *  手机号
     *  所属表字段为`guide`.mobile
     */
    private String mobile;

    /**
     *  
     *  所属表字段为`guide`.fund_account_id
     */
    private String fundAccountId;

    /**
     *  
     *  所属表字段为`guide`.continent_id
     */
    private Integer continentId;

    /**
     *  
     *  所属表字段为`guide`.continent_name
     */
    private String continentName;

    /**
     *  地区id
     *  所属表字段为`guide`.country_id
     */
    private Integer countryId;

    /**
     *  
     *  所属表字段为`guide`.country_name
     */
    private String countryName;

    /**
     *  城市id
     *  所属表字段为`guide`.city_id
     */
    private Integer cityId;

    /**
     *  
     *  所属表字段为`guide`.city_name
     */
    private String cityName;

    /**
     *  原type，导游类型。1-待审核;2-已审核待培训;3-已培训;4-审核不通过
     *  所属表字段为`guide`.sign_status
     */
    private Integer signStatus;

    /**
     *  车辆行驶证明照片
     *  所属表字段为`guide`.driving_licence
     */
    private String drivingLicence;

    /**
     *  车辆照片
     *  所属表字段为`guide`.car_photo
     */
    private String carPhoto;

    /**
     *  身份证照片
     *  所属表字段为`guide`.identity_card
     */
    private String identityCard;

    /**
     *  手机是否验证;1-未认证;2-已认证;3-认证失败;
     *  所属表字段为`guide`.mobile_valid
     */
    private Integer mobileValid;

    /**
     *  
     *  所属表字段为`guide`.mobile_valid_mode
     */
    private Integer mobileValidMode;

    /**
     *  头像
     *  所属表字段为`guide`.avatar
     */
    private String avatar;

    /**
     *  性别
     *  所属表字段为`guide`.gender
     */
    private Integer gender;

    /**
     *  生日
     *  所属表字段为`guide`.birthday
     */
    private Date birthday;

    /**
     *  地址
     *  所属表字段为`guide`.address
     */
    private String address;

    /**
     *  邮编
     *  所属表字段为`guide`.zipcode
     */
    private String zipcode;

    /**
     *  开始生活时间 1 => '1年及以下', 2 => '2-3年', 3 => '4-5年', 4 => '6-9年', 5 => '10年及以上'
     *  所属表字段为`guide`.live_start
     */
    private Integer liveStart;

    /**
     *  存储年份，当地生活起始年限
     *  所属表字段为`guide`.live_year
     */
    private String liveYear;

    /**
     *  email
     *  所属表字段为`guide`.email
     */
    private String email;

    /**
     *  微信
     *  所属表字段为`guide`.weixin
     */
    private String weixin;

    /**
     *  qq
     *  所属表字段为`guide`.qq
     */
    private String qq;

    /**
     *  facebook
     *  所属表字段为`guide`.facebook
     */
    private String facebook;

    /**
     *  家乡id
     *  所属表字段为`guide`.hometown_id
     */
    private Integer hometownId;

    /**
     *  家乡名字
     *  所属表字段为`guide`.hometown_name
     */
    private String hometownName;

    /**
     *  身份证号
     *  所属表字段为`guide`.identity_card_no
     */
    private String identityCardNo;

    /**
     *  护照号
     *  所属表字段为`guide`.passport_no
     */
    private String passportNo;

    /**
     *  学历。1-大专以下;2-大专;3-本科;4-硕士;5-博士
     *  所属表字段为`guide`.education
     */
    private Integer education;

    /**
     *  个人信息描述（自我介绍）
     *  所属表字段为`guide`.description
     */
    private String description;

    /**
     *  其他联系方式
     *  所属表字段为`guide`.other_contact
     */
    private String otherContact;

    /**
     *  紧急联系人姓名
     *  所属表字段为`guide`.contact_name
     */
    private String contactName;

    /**
     *  紧急联系人国际区号
     *  所属表字段为`guide`.contact_area_code
     */
    private String contactAreaCode;

    /**
     *  紧急联系人手机号
     *  所属表字段为`guide`.contact_mobile
     */
    private String contactMobile;

    /**
     *  原model，当前身份（账号类型）。1-兼职(学生)；2-兼职(工作)；3-专职(个体）；4-专职（地接社）
     *  所属表字段为`guide`.job_type
     */
    private Integer jobType;

    /**
     *  导游最新提交审核时间
     *  所属表字段为`guide`.latest_commit_time
     */
    private Date latestCommitTime;

    /**
     *  运营最后审核时间
     *  所属表字段为`guide`.latest_check_time
     */
    private Date latestCheckTime;

    /**
     *  培训认证时间
     *  所属表字段为`guide`.train_time
     */
    private Date trainTime;

    /**
     *  地接社员工状态：1=地接社员工；2=地接社管理员；0=不属于地接社（默认）
     *  所属表字段为`guide`.agency_type
     */
    private Integer agencyType;

    /**
     *  老板id
     *  所属表字段为`guide`.agency_id
     */
    private Integer agencyId;

    /**
     *  
     *  所属表字段为`guide`.agency_boss_id
     */
    private String agencyBossId;

    /**
     *  来源。1-自录数据（线下招募）；2-地接社报名；3-APP报名；4-运营数据
     *  所属表字段为`guide`.register_source
     */
    private Integer registerSource;

    /**
     *  签约日期
     *  所属表字段为`guide`.sign_date
     */
    private Date signDate;

    /**
     *  备注信息
     *  所属表字段为`guide`.comment
     */
    private String comment;

    /**
     *  介绍人（推荐人）
     *  所属表字段为`guide`.referrer
     */
    private String referrer;

    /**
     *  介绍人（推荐人）描述
     *  所属表字段为`guide`.referrer_description
     */
    private String referrerDescription;

    /**
     *  是否分发订单 0=不发送； 1=发送（默认）；
     *  所属表字段为`guide`.send_order_flag
     */
    private Integer sendOrderFlag;

    /**
     *  状态。0-禁用（不允许接单）；1-正常；-1-已删除
     *  所属表字段为`guide`.status
     */
    private Integer status;

    /**
     *  签约奖金金额（大于零表示付给导游签约金）
     *  所属表字段为`guide`.signing_bonus
     */
    private Integer signingBonus;

    /**
     *  签名奖励描述
     *  所属表字段为`guide`.signing_bonus_comment
     */
    private String signingBonusComment;

    /**
     *  导游评级。0-未评级;1-A;2-B;3-C;4-D;5-E
     *  所属表字段为`guide`.guide_level
     */
    private Integer guideLevel;

    /**
     *  
     *  所属表字段为`guide`.im_token
     */
    private String imToken;

    /**
     *  驾照号
     *  所属表字段为`guide`.drive_licence
     */
    private String driveLicence;

    /**
     *  经营许可证
     *  所属表字段为`guide`.operate_permit
     */
    private String operatePermit;

    /**
     *  驾驶证
     *  所属表字段为`guide`.car_licence
     */
    private String carLicence;

    /**
     *  
     *  所属表字段为`guide`.car_photo1
     */
    private String carPhoto1;

    /**
     *  
     *  所属表字段为`guide`.car_photo2
     */
    private String carPhoto2;

    /**
     *  
     *  所属表字段为`guide`.car_photo3
     */
    private String carPhoto3;

    /**
     *  
     *  所属表字段为`guide`.car_photo4
     */
    private String carPhoto4;

    /**
     *  
     *  所属表字段为`guide`.car_photo5
     */
    private String carPhoto5;

    /**
     *  驾龄（驾驶开始时间）
     *  所属表字段为`guide`.drive_start
     */
    private Date driveStart;

    /**
     *  审核基础资料不合格理由
     *  所属表字段为`guide`.base_check_reason
     */
    private String baseCheckReason;

    /**
     *  审核照片不合格理由
     *  所属表字段为`guide`.photo_check_reason
     */
    private String photoCheckReason;

    /**
     *  惩罚分
     *  所属表字段为`guide`.punishment
     */
    private Integer punishment;

    /**
     *  
     *  所属表字段为`guide`.integral
     */
    private Integer integral;

    /**
     *  
     *  所属表字段为`guide`.integral_collect_time
     */
    private Date integralCollectTime;

    /**
     *  登陆次数
     *  所属表字段为`guide`.login_num
     */
    private Integer loginNum;

    /**
     *  最近一次登录时间
     *  所属表字段为`guide`.latest_login_time
     */
    private Date latestLoginTime;

    /**
     *  创建时间
     *  所属表字段为`guide`.create_time
     */
    private Date createTime;

    /**
     *  最近更新时间
     *  所属表字段为`guide`.update_time
     */
    private Date updateTime;

    /**
     *导游id
     *`guide`.guide_id
     *
     * @return the value of `guide`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *导游id
     *`guide`.guide_id
     *
     * @param guideId the value for `guide`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *导游业务编号
     *`guide`.guide_no
     *
     * @return the value of `guide`.guide_no
     *
     * @mbggenerated
     */
    public String getGuideNo() {
        return guideNo;
    }

    /**
     *导游业务编号
     *`guide`.guide_no
     *
     * @param guideNo the value for `guide`.guide_no
     *
     * @mbggenerated
     */
    public void setGuideNo(String guideNo) {
        this.guideNo = guideNo == null ? null : guideNo.trim();
    }

    /**
     *导游名称
     *`guide`.guide_name
     *
     * @return the value of `guide`.guide_name
     *
     * @mbggenerated
     */
    public String getGuideName() {
        return guideName;
    }

    /**
     *导游名称
     *`guide`.guide_name
     *
     * @param guideName the value for `guide`.guide_name
     *
     * @mbggenerated
     */
    public void setGuideName(String guideName) {
        this.guideName = guideName == null ? null : guideName.trim();
    }

    /**
     *国际区号
     *`guide`.area_code
     *
     * @return the value of `guide`.area_code
     *
     * @mbggenerated
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     *国际区号
     *`guide`.area_code
     *
     * @param areaCode the value for `guide`.area_code
     *
     * @mbggenerated
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     *手机号
     *`guide`.mobile
     *
     * @return the value of `guide`.mobile
     *
     * @mbggenerated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     *手机号
     *`guide`.mobile
     *
     * @param mobile the value for `guide`.mobile
     *
     * @mbggenerated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     *
     *`guide`.fund_account_id
     *
     * @return the value of `guide`.fund_account_id
     *
     * @mbggenerated
     */
    public String getFundAccountId() {
        return fundAccountId;
    }

    /**
     *
     *`guide`.fund_account_id
     *
     * @param fundAccountId the value for `guide`.fund_account_id
     *
     * @mbggenerated
     */
    public void setFundAccountId(String fundAccountId) {
        this.fundAccountId = fundAccountId == null ? null : fundAccountId.trim();
    }

    /**
     *
     *`guide`.continent_id
     *
     * @return the value of `guide`.continent_id
     *
     * @mbggenerated
     */
    public Integer getContinentId() {
        return continentId;
    }

    /**
     *
     *`guide`.continent_id
     *
     * @param continentId the value for `guide`.continent_id
     *
     * @mbggenerated
     */
    public void setContinentId(Integer continentId) {
        this.continentId = continentId;
    }

    /**
     *
     *`guide`.continent_name
     *
     * @return the value of `guide`.continent_name
     *
     * @mbggenerated
     */
    public String getContinentName() {
        return continentName;
    }

    /**
     *
     *`guide`.continent_name
     *
     * @param continentName the value for `guide`.continent_name
     *
     * @mbggenerated
     */
    public void setContinentName(String continentName) {
        this.continentName = continentName == null ? null : continentName.trim();
    }

    /**
     *地区id
     *`guide`.country_id
     *
     * @return the value of `guide`.country_id
     *
     * @mbggenerated
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     *地区id
     *`guide`.country_id
     *
     * @param countryId the value for `guide`.country_id
     *
     * @mbggenerated
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     *
     *`guide`.country_name
     *
     * @return the value of `guide`.country_name
     *
     * @mbggenerated
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     *
     *`guide`.country_name
     *
     * @param countryName the value for `guide`.country_name
     *
     * @mbggenerated
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    /**
     *城市id
     *`guide`.city_id
     *
     * @return the value of `guide`.city_id
     *
     * @mbggenerated
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     *城市id
     *`guide`.city_id
     *
     * @param cityId the value for `guide`.city_id
     *
     * @mbggenerated
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     *
     *`guide`.city_name
     *
     * @return the value of `guide`.city_name
     *
     * @mbggenerated
     */
    public String getCityName() {
        return cityName;
    }

    /**
     *
     *`guide`.city_name
     *
     * @param cityName the value for `guide`.city_name
     *
     * @mbggenerated
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     *原type，导游类型。1-待审核;2-已审核待培训;3-已培训;4-审核不通过
     *`guide`.sign_status
     *
     * @return the value of `guide`.sign_status
     *
     * @mbggenerated
     */
    public Integer getSignStatus() {
        return signStatus;
    }

    /**
     *原type，导游类型。1-待审核;2-已审核待培训;3-已培训;4-审核不通过
     *`guide`.sign_status
     *
     * @param signStatus the value for `guide`.sign_status
     *
     * @mbggenerated
     */
    public void setSignStatus(Integer signStatus) {
        this.signStatus = signStatus;
    }

    /**
     *车辆行驶证明照片
     *`guide`.driving_licence
     *
     * @return the value of `guide`.driving_licence
     *
     * @mbggenerated
     */
    public String getDrivingLicence() {
        return drivingLicence;
    }

    /**
     *车辆行驶证明照片
     *`guide`.driving_licence
     *
     * @param drivingLicence the value for `guide`.driving_licence
     *
     * @mbggenerated
     */
    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence == null ? null : drivingLicence.trim();
    }

    /**
     *车辆照片
     *`guide`.car_photo
     *
     * @return the value of `guide`.car_photo
     *
     * @mbggenerated
     */
    public String getCarPhoto() {
        return carPhoto;
    }

    /**
     *车辆照片
     *`guide`.car_photo
     *
     * @param carPhoto the value for `guide`.car_photo
     *
     * @mbggenerated
     */
    public void setCarPhoto(String carPhoto) {
        this.carPhoto = carPhoto == null ? null : carPhoto.trim();
    }

    /**
     *身份证照片
     *`guide`.identity_card
     *
     * @return the value of `guide`.identity_card
     *
     * @mbggenerated
     */
    public String getIdentityCard() {
        return identityCard;
    }

    /**
     *身份证照片
     *`guide`.identity_card
     *
     * @param identityCard the value for `guide`.identity_card
     *
     * @mbggenerated
     */
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard == null ? null : identityCard.trim();
    }

    /**
     *手机是否验证;1-未认证;2-已认证;3-认证失败;
     *`guide`.mobile_valid
     *
     * @return the value of `guide`.mobile_valid
     *
     * @mbggenerated
     */
    public Integer getMobileValid() {
        return mobileValid;
    }

    /**
     *手机是否验证;1-未认证;2-已认证;3-认证失败;
     *`guide`.mobile_valid
     *
     * @param mobileValid the value for `guide`.mobile_valid
     *
     * @mbggenerated
     */
    public void setMobileValid(Integer mobileValid) {
        this.mobileValid = mobileValid;
    }

    /**
     *
     *`guide`.mobile_valid_mode
     *
     * @return the value of `guide`.mobile_valid_mode
     *
     * @mbggenerated
     */
    public Integer getMobileValidMode() {
        return mobileValidMode;
    }

    /**
     *
     *`guide`.mobile_valid_mode
     *
     * @param mobileValidMode the value for `guide`.mobile_valid_mode
     *
     * @mbggenerated
     */
    public void setMobileValidMode(Integer mobileValidMode) {
        this.mobileValidMode = mobileValidMode;
    }

    /**
     *头像
     *`guide`.avatar
     *
     * @return the value of `guide`.avatar
     *
     * @mbggenerated
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     *头像
     *`guide`.avatar
     *
     * @param avatar the value for `guide`.avatar
     *
     * @mbggenerated
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     *性别
     *`guide`.gender
     *
     * @return the value of `guide`.gender
     *
     * @mbggenerated
     */
    public Integer getGender() {
        return gender;
    }

    /**
     *性别
     *`guide`.gender
     *
     * @param gender the value for `guide`.gender
     *
     * @mbggenerated
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     *生日
     *`guide`.birthday
     *
     * @return the value of `guide`.birthday
     *
     * @mbggenerated
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     *生日
     *`guide`.birthday
     *
     * @param birthday the value for `guide`.birthday
     *
     * @mbggenerated
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     *地址
     *`guide`.address
     *
     * @return the value of `guide`.address
     *
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     *地址
     *`guide`.address
     *
     * @param address the value for `guide`.address
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     *邮编
     *`guide`.zipcode
     *
     * @return the value of `guide`.zipcode
     *
     * @mbggenerated
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     *邮编
     *`guide`.zipcode
     *
     * @param zipcode the value for `guide`.zipcode
     *
     * @mbggenerated
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    /**
     *开始生活时间 1 => '1年及以下', 2 => '2-3年', 3 => '4-5年', 4 => '6-9年', 5 => '10年及以上'
     *`guide`.live_start
     *
     * @return the value of `guide`.live_start
     *
     * @mbggenerated
     */
    public Integer getLiveStart() {
        return liveStart;
    }

    /**
     *开始生活时间 1 => '1年及以下', 2 => '2-3年', 3 => '4-5年', 4 => '6-9年', 5 => '10年及以上'
     *`guide`.live_start
     *
     * @param liveStart the value for `guide`.live_start
     *
     * @mbggenerated
     */
    public void setLiveStart(Integer liveStart) {
        this.liveStart = liveStart;
    }

    /**
     *存储年份，当地生活起始年限
     *`guide`.live_year
     *
     * @return the value of `guide`.live_year
     *
     * @mbggenerated
     */
    public String getLiveYear() {
        return liveYear;
    }

    /**
     *存储年份，当地生活起始年限
     *`guide`.live_year
     *
     * @param liveYear the value for `guide`.live_year
     *
     * @mbggenerated
     */
    public void setLiveYear(String liveYear) {
        this.liveYear = liveYear == null ? null : liveYear.trim();
    }

    /**
     *email
     *`guide`.email
     *
     * @return the value of `guide`.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     *email
     *`guide`.email
     *
     * @param email the value for `guide`.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     *微信
     *`guide`.weixin
     *
     * @return the value of `guide`.weixin
     *
     * @mbggenerated
     */
    public String getWeixin() {
        return weixin;
    }

    /**
     *微信
     *`guide`.weixin
     *
     * @param weixin the value for `guide`.weixin
     *
     * @mbggenerated
     */
    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    /**
     *qq
     *`guide`.qq
     *
     * @return the value of `guide`.qq
     *
     * @mbggenerated
     */
    public String getQq() {
        return qq;
    }

    /**
     *qq
     *`guide`.qq
     *
     * @param qq the value for `guide`.qq
     *
     * @mbggenerated
     */
    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    /**
     *facebook
     *`guide`.facebook
     *
     * @return the value of `guide`.facebook
     *
     * @mbggenerated
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     *facebook
     *`guide`.facebook
     *
     * @param facebook the value for `guide`.facebook
     *
     * @mbggenerated
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook == null ? null : facebook.trim();
    }

    /**
     *家乡id
     *`guide`.hometown_id
     *
     * @return the value of `guide`.hometown_id
     *
     * @mbggenerated
     */
    public Integer getHometownId() {
        return hometownId;
    }

    /**
     *家乡id
     *`guide`.hometown_id
     *
     * @param hometownId the value for `guide`.hometown_id
     *
     * @mbggenerated
     */
    public void setHometownId(Integer hometownId) {
        this.hometownId = hometownId;
    }

    /**
     *家乡名字
     *`guide`.hometown_name
     *
     * @return the value of `guide`.hometown_name
     *
     * @mbggenerated
     */
    public String getHometownName() {
        return hometownName;
    }

    /**
     *家乡名字
     *`guide`.hometown_name
     *
     * @param hometownName the value for `guide`.hometown_name
     *
     * @mbggenerated
     */
    public void setHometownName(String hometownName) {
        this.hometownName = hometownName == null ? null : hometownName.trim();
    }

    /**
     *身份证号
     *`guide`.identity_card_no
     *
     * @return the value of `guide`.identity_card_no
     *
     * @mbggenerated
     */
    public String getIdentityCardNo() {
        return identityCardNo;
    }

    /**
     *身份证号
     *`guide`.identity_card_no
     *
     * @param identityCardNo the value for `guide`.identity_card_no
     *
     * @mbggenerated
     */
    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo == null ? null : identityCardNo.trim();
    }

    /**
     *护照号
     *`guide`.passport_no
     *
     * @return the value of `guide`.passport_no
     *
     * @mbggenerated
     */
    public String getPassportNo() {
        return passportNo;
    }

    /**
     *护照号
     *`guide`.passport_no
     *
     * @param passportNo the value for `guide`.passport_no
     *
     * @mbggenerated
     */
    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo == null ? null : passportNo.trim();
    }

    /**
     *学历。1-大专以下;2-大专;3-本科;4-硕士;5-博士
     *`guide`.education
     *
     * @return the value of `guide`.education
     *
     * @mbggenerated
     */
    public Integer getEducation() {
        return education;
    }

    /**
     *学历。1-大专以下;2-大专;3-本科;4-硕士;5-博士
     *`guide`.education
     *
     * @param education the value for `guide`.education
     *
     * @mbggenerated
     */
    public void setEducation(Integer education) {
        this.education = education;
    }

    /**
     *个人信息描述（自我介绍）
     *`guide`.description
     *
     * @return the value of `guide`.description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     *个人信息描述（自我介绍）
     *`guide`.description
     *
     * @param description the value for `guide`.description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     *其他联系方式
     *`guide`.other_contact
     *
     * @return the value of `guide`.other_contact
     *
     * @mbggenerated
     */
    public String getOtherContact() {
        return otherContact;
    }

    /**
     *其他联系方式
     *`guide`.other_contact
     *
     * @param otherContact the value for `guide`.other_contact
     *
     * @mbggenerated
     */
    public void setOtherContact(String otherContact) {
        this.otherContact = otherContact == null ? null : otherContact.trim();
    }

    /**
     *紧急联系人姓名
     *`guide`.contact_name
     *
     * @return the value of `guide`.contact_name
     *
     * @mbggenerated
     */
    public String getContactName() {
        return contactName;
    }

    /**
     *紧急联系人姓名
     *`guide`.contact_name
     *
     * @param contactName the value for `guide`.contact_name
     *
     * @mbggenerated
     */
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    /**
     *紧急联系人国际区号
     *`guide`.contact_area_code
     *
     * @return the value of `guide`.contact_area_code
     *
     * @mbggenerated
     */
    public String getContactAreaCode() {
        return contactAreaCode;
    }

    /**
     *紧急联系人国际区号
     *`guide`.contact_area_code
     *
     * @param contactAreaCode the value for `guide`.contact_area_code
     *
     * @mbggenerated
     */
    public void setContactAreaCode(String contactAreaCode) {
        this.contactAreaCode = contactAreaCode == null ? null : contactAreaCode.trim();
    }

    /**
     *紧急联系人手机号
     *`guide`.contact_mobile
     *
     * @return the value of `guide`.contact_mobile
     *
     * @mbggenerated
     */
    public String getContactMobile() {
        return contactMobile;
    }

    /**
     *紧急联系人手机号
     *`guide`.contact_mobile
     *
     * @param contactMobile the value for `guide`.contact_mobile
     *
     * @mbggenerated
     */
    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    /**
     *原model，当前身份（账号类型）。1-兼职(学生)；2-兼职(工作)；3-专职(个体）；4-专职（地接社）
     *`guide`.job_type
     *
     * @return the value of `guide`.job_type
     *
     * @mbggenerated
     */
    public Integer getJobType() {
        return jobType;
    }

    /**
     *原model，当前身份（账号类型）。1-兼职(学生)；2-兼职(工作)；3-专职(个体）；4-专职（地接社）
     *`guide`.job_type
     *
     * @param jobType the value for `guide`.job_type
     *
     * @mbggenerated
     */
    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    /**
     *导游最新提交审核时间
     *`guide`.latest_commit_time
     *
     * @return the value of `guide`.latest_commit_time
     *
     * @mbggenerated
     */
    public Date getLatestCommitTime() {
        return latestCommitTime;
    }

    /**
     *导游最新提交审核时间
     *`guide`.latest_commit_time
     *
     * @param latestCommitTime the value for `guide`.latest_commit_time
     *
     * @mbggenerated
     */
    public void setLatestCommitTime(Date latestCommitTime) {
        this.latestCommitTime = latestCommitTime;
    }

    /**
     *运营最后审核时间
     *`guide`.latest_check_time
     *
     * @return the value of `guide`.latest_check_time
     *
     * @mbggenerated
     */
    public Date getLatestCheckTime() {
        return latestCheckTime;
    }

    /**
     *运营最后审核时间
     *`guide`.latest_check_time
     *
     * @param latestCheckTime the value for `guide`.latest_check_time
     *
     * @mbggenerated
     */
    public void setLatestCheckTime(Date latestCheckTime) {
        this.latestCheckTime = latestCheckTime;
    }

    /**
     *培训认证时间
     *`guide`.train_time
     *
     * @return the value of `guide`.train_time
     *
     * @mbggenerated
     */
    public Date getTrainTime() {
        return trainTime;
    }

    /**
     *培训认证时间
     *`guide`.train_time
     *
     * @param trainTime the value for `guide`.train_time
     *
     * @mbggenerated
     */
    public void setTrainTime(Date trainTime) {
        this.trainTime = trainTime;
    }

    /**
     *地接社员工状态：1=地接社员工；2=地接社管理员；0=不属于地接社（默认）
     *`guide`.agency_type
     *
     * @return the value of `guide`.agency_type
     *
     * @mbggenerated
     */
    public Integer getAgencyType() {
        return agencyType;
    }

    /**
     *地接社员工状态：1=地接社员工；2=地接社管理员；0=不属于地接社（默认）
     *`guide`.agency_type
     *
     * @param agencyType the value for `guide`.agency_type
     *
     * @mbggenerated
     */
    public void setAgencyType(Integer agencyType) {
        this.agencyType = agencyType;
    }

    /**
     *老板id
     *`guide`.agency_id
     *
     * @return the value of `guide`.agency_id
     *
     * @mbggenerated
     */
    public Integer getAgencyId() {
        return agencyId;
    }

    /**
     *老板id
     *`guide`.agency_id
     *
     * @param agencyId the value for `guide`.agency_id
     *
     * @mbggenerated
     */
    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    /**
     *
     *`guide`.agency_boss_id
     *
     * @return the value of `guide`.agency_boss_id
     *
     * @mbggenerated
     */
    public String getAgencyBossId() {
        return agencyBossId;
    }

    /**
     *
     *`guide`.agency_boss_id
     *
     * @param agencyBossId the value for `guide`.agency_boss_id
     *
     * @mbggenerated
     */
    public void setAgencyBossId(String agencyBossId) {
        this.agencyBossId = agencyBossId == null ? null : agencyBossId.trim();
    }

    /**
     *来源。1-自录数据（线下招募）；2-地接社报名；3-APP报名；4-运营数据
     *`guide`.register_source
     *
     * @return the value of `guide`.register_source
     *
     * @mbggenerated
     */
    public Integer getRegisterSource() {
        return registerSource;
    }

    /**
     *来源。1-自录数据（线下招募）；2-地接社报名；3-APP报名；4-运营数据
     *`guide`.register_source
     *
     * @param registerSource the value for `guide`.register_source
     *
     * @mbggenerated
     */
    public void setRegisterSource(Integer registerSource) {
        this.registerSource = registerSource;
    }

    /**
     *签约日期
     *`guide`.sign_date
     *
     * @return the value of `guide`.sign_date
     *
     * @mbggenerated
     */
    public Date getSignDate() {
        return signDate;
    }

    /**
     *签约日期
     *`guide`.sign_date
     *
     * @param signDate the value for `guide`.sign_date
     *
     * @mbggenerated
     */
    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    /**
     *备注信息
     *`guide`.comment
     *
     * @return the value of `guide`.comment
     *
     * @mbggenerated
     */
    public String getComment() {
        return comment;
    }

    /**
     *备注信息
     *`guide`.comment
     *
     * @param comment the value for `guide`.comment
     *
     * @mbggenerated
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     *介绍人（推荐人）
     *`guide`.referrer
     *
     * @return the value of `guide`.referrer
     *
     * @mbggenerated
     */
    public String getReferrer() {
        return referrer;
    }

    /**
     *介绍人（推荐人）
     *`guide`.referrer
     *
     * @param referrer the value for `guide`.referrer
     *
     * @mbggenerated
     */
    public void setReferrer(String referrer) {
        this.referrer = referrer == null ? null : referrer.trim();
    }

    /**
     *介绍人（推荐人）描述
     *`guide`.referrer_description
     *
     * @return the value of `guide`.referrer_description
     *
     * @mbggenerated
     */
    public String getReferrerDescription() {
        return referrerDescription;
    }

    /**
     *介绍人（推荐人）描述
     *`guide`.referrer_description
     *
     * @param referrerDescription the value for `guide`.referrer_description
     *
     * @mbggenerated
     */
    public void setReferrerDescription(String referrerDescription) {
        this.referrerDescription = referrerDescription == null ? null : referrerDescription.trim();
    }

    /**
     *是否分发订单 0=不发送； 1=发送（默认）；
     *`guide`.send_order_flag
     *
     * @return the value of `guide`.send_order_flag
     *
     * @mbggenerated
     */
    public Integer getSendOrderFlag() {
        return sendOrderFlag;
    }

    /**
     *是否分发订单 0=不发送； 1=发送（默认）；
     *`guide`.send_order_flag
     *
     * @param sendOrderFlag the value for `guide`.send_order_flag
     *
     * @mbggenerated
     */
    public void setSendOrderFlag(Integer sendOrderFlag) {
        this.sendOrderFlag = sendOrderFlag;
    }

    /**
     *状态。0-禁用（不允许接单）；1-正常；-1-已删除
     *`guide`.status
     *
     * @return the value of `guide`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *状态。0-禁用（不允许接单）；1-正常；-1-已删除
     *`guide`.status
     *
     * @param status the value for `guide`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *签约奖金金额（大于零表示付给导游签约金）
     *`guide`.signing_bonus
     *
     * @return the value of `guide`.signing_bonus
     *
     * @mbggenerated
     */
    public Integer getSigningBonus() {
        return signingBonus;
    }

    /**
     *签约奖金金额（大于零表示付给导游签约金）
     *`guide`.signing_bonus
     *
     * @param signingBonus the value for `guide`.signing_bonus
     *
     * @mbggenerated
     */
    public void setSigningBonus(Integer signingBonus) {
        this.signingBonus = signingBonus;
    }

    /**
     *签名奖励描述
     *`guide`.signing_bonus_comment
     *
     * @return the value of `guide`.signing_bonus_comment
     *
     * @mbggenerated
     */
    public String getSigningBonusComment() {
        return signingBonusComment;
    }

    /**
     *签名奖励描述
     *`guide`.signing_bonus_comment
     *
     * @param signingBonusComment the value for `guide`.signing_bonus_comment
     *
     * @mbggenerated
     */
    public void setSigningBonusComment(String signingBonusComment) {
        this.signingBonusComment = signingBonusComment == null ? null : signingBonusComment.trim();
    }

    /**
     *导游评级。0-未评级;1-A;2-B;3-C;4-D;5-E
     *`guide`.guide_level
     *
     * @return the value of `guide`.guide_level
     *
     * @mbggenerated
     */
    public Integer getGuideLevel() {
        return guideLevel;
    }

    /**
     *导游评级。0-未评级;1-A;2-B;3-C;4-D;5-E
     *`guide`.guide_level
     *
     * @param guideLevel the value for `guide`.guide_level
     *
     * @mbggenerated
     */
    public void setGuideLevel(Integer guideLevel) {
        this.guideLevel = guideLevel;
    }

    /**
     *
     *`guide`.im_token
     *
     * @return the value of `guide`.im_token
     *
     * @mbggenerated
     */
    public String getImToken() {
        return imToken;
    }

    /**
     *
     *`guide`.im_token
     *
     * @param imToken the value for `guide`.im_token
     *
     * @mbggenerated
     */
    public void setImToken(String imToken) {
        this.imToken = imToken == null ? null : imToken.trim();
    }

    /**
     *驾照号
     *`guide`.drive_licence
     *
     * @return the value of `guide`.drive_licence
     *
     * @mbggenerated
     */
    public String getDriveLicence() {
        return driveLicence;
    }

    /**
     *驾照号
     *`guide`.drive_licence
     *
     * @param driveLicence the value for `guide`.drive_licence
     *
     * @mbggenerated
     */
    public void setDriveLicence(String driveLicence) {
        this.driveLicence = driveLicence == null ? null : driveLicence.trim();
    }

    /**
     *经营许可证
     *`guide`.operate_permit
     *
     * @return the value of `guide`.operate_permit
     *
     * @mbggenerated
     */
    public String getOperatePermit() {
        return operatePermit;
    }

    /**
     *经营许可证
     *`guide`.operate_permit
     *
     * @param operatePermit the value for `guide`.operate_permit
     *
     * @mbggenerated
     */
    public void setOperatePermit(String operatePermit) {
        this.operatePermit = operatePermit == null ? null : operatePermit.trim();
    }

    /**
     *驾驶证
     *`guide`.car_licence
     *
     * @return the value of `guide`.car_licence
     *
     * @mbggenerated
     */
    public String getCarLicence() {
        return carLicence;
    }

    /**
     *驾驶证
     *`guide`.car_licence
     *
     * @param carLicence the value for `guide`.car_licence
     *
     * @mbggenerated
     */
    public void setCarLicence(String carLicence) {
        this.carLicence = carLicence == null ? null : carLicence.trim();
    }

    /**
     *
     *`guide`.car_photo1
     *
     * @return the value of `guide`.car_photo1
     *
     * @mbggenerated
     */
    public String getCarPhoto1() {
        return carPhoto1;
    }

    /**
     *
     *`guide`.car_photo1
     *
     * @param carPhoto1 the value for `guide`.car_photo1
     *
     * @mbggenerated
     */
    public void setCarPhoto1(String carPhoto1) {
        this.carPhoto1 = carPhoto1 == null ? null : carPhoto1.trim();
    }

    /**
     *
     *`guide`.car_photo2
     *
     * @return the value of `guide`.car_photo2
     *
     * @mbggenerated
     */
    public String getCarPhoto2() {
        return carPhoto2;
    }

    /**
     *
     *`guide`.car_photo2
     *
     * @param carPhoto2 the value for `guide`.car_photo2
     *
     * @mbggenerated
     */
    public void setCarPhoto2(String carPhoto2) {
        this.carPhoto2 = carPhoto2 == null ? null : carPhoto2.trim();
    }

    /**
     *
     *`guide`.car_photo3
     *
     * @return the value of `guide`.car_photo3
     *
     * @mbggenerated
     */
    public String getCarPhoto3() {
        return carPhoto3;
    }

    /**
     *
     *`guide`.car_photo3
     *
     * @param carPhoto3 the value for `guide`.car_photo3
     *
     * @mbggenerated
     */
    public void setCarPhoto3(String carPhoto3) {
        this.carPhoto3 = carPhoto3 == null ? null : carPhoto3.trim();
    }

    /**
     *
     *`guide`.car_photo4
     *
     * @return the value of `guide`.car_photo4
     *
     * @mbggenerated
     */
    public String getCarPhoto4() {
        return carPhoto4;
    }

    /**
     *
     *`guide`.car_photo4
     *
     * @param carPhoto4 the value for `guide`.car_photo4
     *
     * @mbggenerated
     */
    public void setCarPhoto4(String carPhoto4) {
        this.carPhoto4 = carPhoto4 == null ? null : carPhoto4.trim();
    }

    /**
     *
     *`guide`.car_photo5
     *
     * @return the value of `guide`.car_photo5
     *
     * @mbggenerated
     */
    public String getCarPhoto5() {
        return carPhoto5;
    }

    /**
     *
     *`guide`.car_photo5
     *
     * @param carPhoto5 the value for `guide`.car_photo5
     *
     * @mbggenerated
     */
    public void setCarPhoto5(String carPhoto5) {
        this.carPhoto5 = carPhoto5 == null ? null : carPhoto5.trim();
    }

    /**
     *驾龄（驾驶开始时间）
     *`guide`.drive_start
     *
     * @return the value of `guide`.drive_start
     *
     * @mbggenerated
     */
    public Date getDriveStart() {
        return driveStart;
    }

    /**
     *驾龄（驾驶开始时间）
     *`guide`.drive_start
     *
     * @param driveStart the value for `guide`.drive_start
     *
     * @mbggenerated
     */
    public void setDriveStart(Date driveStart) {
        this.driveStart = driveStart;
    }

    /**
     *审核基础资料不合格理由
     *`guide`.base_check_reason
     *
     * @return the value of `guide`.base_check_reason
     *
     * @mbggenerated
     */
    public String getBaseCheckReason() {
        return baseCheckReason;
    }

    /**
     *审核基础资料不合格理由
     *`guide`.base_check_reason
     *
     * @param baseCheckReason the value for `guide`.base_check_reason
     *
     * @mbggenerated
     */
    public void setBaseCheckReason(String baseCheckReason) {
        this.baseCheckReason = baseCheckReason == null ? null : baseCheckReason.trim();
    }

    /**
     *审核照片不合格理由
     *`guide`.photo_check_reason
     *
     * @return the value of `guide`.photo_check_reason
     *
     * @mbggenerated
     */
    public String getPhotoCheckReason() {
        return photoCheckReason;
    }

    /**
     *审核照片不合格理由
     *`guide`.photo_check_reason
     *
     * @param photoCheckReason the value for `guide`.photo_check_reason
     *
     * @mbggenerated
     */
    public void setPhotoCheckReason(String photoCheckReason) {
        this.photoCheckReason = photoCheckReason == null ? null : photoCheckReason.trim();
    }

    /**
     *惩罚分
     *`guide`.punishment
     *
     * @return the value of `guide`.punishment
     *
     * @mbggenerated
     */
    public Integer getPunishment() {
        return punishment;
    }

    /**
     *惩罚分
     *`guide`.punishment
     *
     * @param punishment the value for `guide`.punishment
     *
     * @mbggenerated
     */
    public void setPunishment(Integer punishment) {
        this.punishment = punishment;
    }

    /**
     *
     *`guide`.integral
     *
     * @return the value of `guide`.integral
     *
     * @mbggenerated
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     *
     *`guide`.integral
     *
     * @param integral the value for `guide`.integral
     *
     * @mbggenerated
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     *
     *`guide`.integral_collect_time
     *
     * @return the value of `guide`.integral_collect_time
     *
     * @mbggenerated
     */
    public Date getIntegralCollectTime() {
        return integralCollectTime;
    }

    /**
     *
     *`guide`.integral_collect_time
     *
     * @param integralCollectTime the value for `guide`.integral_collect_time
     *
     * @mbggenerated
     */
    public void setIntegralCollectTime(Date integralCollectTime) {
        this.integralCollectTime = integralCollectTime;
    }

    /**
     *登陆次数
     *`guide`.login_num
     *
     * @return the value of `guide`.login_num
     *
     * @mbggenerated
     */
    public Integer getLoginNum() {
        return loginNum;
    }

    /**
     *登陆次数
     *`guide`.login_num
     *
     * @param loginNum the value for `guide`.login_num
     *
     * @mbggenerated
     */
    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    /**
     *最近一次登录时间
     *`guide`.latest_login_time
     *
     * @return the value of `guide`.latest_login_time
     *
     * @mbggenerated
     */
    public Date getLatestLoginTime() {
        return latestLoginTime;
    }

    /**
     *最近一次登录时间
     *`guide`.latest_login_time
     *
     * @param latestLoginTime the value for `guide`.latest_login_time
     *
     * @mbggenerated
     */
    public void setLatestLoginTime(Date latestLoginTime) {
        this.latestLoginTime = latestLoginTime;
    }

    /**
     *创建时间
     *`guide`.create_time
     *
     * @return the value of `guide`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     *`guide`.create_time
     *
     * @param createTime the value for `guide`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *最近更新时间
     *`guide`.update_time
     *
     * @return the value of `guide`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *最近更新时间
     *`guide`.update_time
     *
     * @param updateTime the value for `guide`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guideId=").append(guideId);
        sb.append(", guideNo=").append(guideNo);
        sb.append(", guideName=").append(guideName);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", mobile=").append(mobile);
        sb.append(", fundAccountId=").append(fundAccountId);
        sb.append(", continentId=").append(continentId);
        sb.append(", continentName=").append(continentName);
        sb.append(", countryId=").append(countryId);
        sb.append(", countryName=").append(countryName);
        sb.append(", cityId=").append(cityId);
        sb.append(", cityName=").append(cityName);
        sb.append(", signStatus=").append(signStatus);
        sb.append(", drivingLicence=").append(drivingLicence);
        sb.append(", carPhoto=").append(carPhoto);
        sb.append(", identityCard=").append(identityCard);
        sb.append(", mobileValid=").append(mobileValid);
        sb.append(", mobileValidMode=").append(mobileValidMode);
        sb.append(", avatar=").append(avatar);
        sb.append(", gender=").append(gender);
        sb.append(", birthday=").append(birthday);
        sb.append(", address=").append(address);
        sb.append(", zipcode=").append(zipcode);
        sb.append(", liveStart=").append(liveStart);
        sb.append(", liveYear=").append(liveYear);
        sb.append(", email=").append(email);
        sb.append(", weixin=").append(weixin);
        sb.append(", qq=").append(qq);
        sb.append(", facebook=").append(facebook);
        sb.append(", hometownId=").append(hometownId);
        sb.append(", hometownName=").append(hometownName);
        sb.append(", identityCardNo=").append(identityCardNo);
        sb.append(", passportNo=").append(passportNo);
        sb.append(", education=").append(education);
        sb.append(", description=").append(description);
        sb.append(", otherContact=").append(otherContact);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactAreaCode=").append(contactAreaCode);
        sb.append(", contactMobile=").append(contactMobile);
        sb.append(", jobType=").append(jobType);
        sb.append(", latestCommitTime=").append(latestCommitTime);
        sb.append(", latestCheckTime=").append(latestCheckTime);
        sb.append(", trainTime=").append(trainTime);
        sb.append(", agencyType=").append(agencyType);
        sb.append(", agencyId=").append(agencyId);
        sb.append(", agencyBossId=").append(agencyBossId);
        sb.append(", registerSource=").append(registerSource);
        sb.append(", signDate=").append(signDate);
        sb.append(", comment=").append(comment);
        sb.append(", referrer=").append(referrer);
        sb.append(", referrerDescription=").append(referrerDescription);
        sb.append(", sendOrderFlag=").append(sendOrderFlag);
        sb.append(", status=").append(status);
        sb.append(", signingBonus=").append(signingBonus);
        sb.append(", signingBonusComment=").append(signingBonusComment);
        sb.append(", guideLevel=").append(guideLevel);
        sb.append(", imToken=").append(imToken);
        sb.append(", driveLicence=").append(driveLicence);
        sb.append(", operatePermit=").append(operatePermit);
        sb.append(", carLicence=").append(carLicence);
        sb.append(", carPhoto1=").append(carPhoto1);
        sb.append(", carPhoto2=").append(carPhoto2);
        sb.append(", carPhoto3=").append(carPhoto3);
        sb.append(", carPhoto4=").append(carPhoto4);
        sb.append(", carPhoto5=").append(carPhoto5);
        sb.append(", driveStart=").append(driveStart);
        sb.append(", baseCheckReason=").append(baseCheckReason);
        sb.append(", photoCheckReason=").append(photoCheckReason);
        sb.append(", punishment=").append(punishment);
        sb.append(", integral=").append(integral);
        sb.append(", integralCollectTime=").append(integralCollectTime);
        sb.append(", loginNum=").append(loginNum);
        sb.append(", latestLoginTime=").append(latestLoginTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}