package com.hbc.api.trade.bdata.mapper.basedata.gen.bean;

import java.util.Date;

public class UserInfo {
    /**
     *  
     *  所属表字段为`user_info`.user_id
     */
    private String userId;

    /**
     *  姓名
     *  所属表字段为`user_info`.name
     */
    private String name;

    /**
     *  昵称
     *  所属表字段为`user_info`.nick_name
     */
    private String nickName;

    /**
     *  
     *  所属表字段为`user_info`.email
     */
    private String email;

    /**
     *  
     *  所属表字段为`user_info`.union_id
     */
    private String unionId;

    /**
     *  性别
     *  所属表字段为`user_info`.gender
     */
    private Integer gender;

    /**
     *  用户来源。1：andriod，2：IOS；3：微信；4：PC；5：GDS；6：去哪儿API；7：携程API；8：去啊API；9：其他
     *  所属表字段为`user_info`.source
     */
    private Integer source;

    /**
     *  1 自然量（默认）；[1000，3000) Android；[3000，5000) ios；[5000，7000) pc
     *  所属表字段为`user_info`.from_channel
     */
    private Integer fromChannel;

    /**
     *  代理商ID
     *  所属表字段为`user_info`.agent_id
     */
    private String agentId;

    /**
     *  代理商名称
     *  所属表字段为`user_info`.agent_name
     */
    private String agentName;

    /**
     *  头像
     *  所属表字段为`user_info`.avatar
     */
    private String avatar;

    /**
     *  推荐人ID
     *  所属表字段为`user_info`.referrer_id
     */
    private String referrerId;

    /**
     *  年龄类型。0：40后，1：50后，2：60后，3：70后，4：80后，5：90后，6：00后，7：10后
     *  所属表字段为`user_info`.age_type
     */
    private Integer ageType;

    /**
     *  出生日期
     *  所属表字段为`user_info`.birthday
     */
    private Date birthday;

    /**
     *  现地址邮编
     *  所属表字段为`user_info`.zipcode
     */
    private String zipcode;

    /**
     *  详细地址描述
     *  所属表字段为`user_info`.address
     */
    private String address;

    /**
     *  签名
     *  所属表字段为`user_info`.signature
     */
    private String signature;

    /**
     *  是否验证手机号。0：未验证，1：已验证
     *  所属表字段为`user_info`.is_phone
     */
    private Integer isPhone;

    /**
     *  是否验证邮箱
     *  所属表字段为`user_info`.is_email
     */
    private Integer isEmail;

    /**
     *  最后登入ip
     *  所属表字段为`user_info`.ip_last
     */
    private String ipLast;

    /**
     *  最后一次登入的浏览器信息
     *  所属表字段为`user_info`.browser_last
     */
    private String browserLast;

    /**
     *  
     *  所属表字段为`user_info`.place_city_name
     */
    private String placeCityName;

    /**
     *  
     *  所属表字段为`user_info`.place_city_id
     */
    private Integer placeCityId;

    /**
     *  省（州）名称
     *  所属表字段为`user_info`.province_name
     */
    private String provinceName;

    /**
     *  
     *  所属表字段为`user_info`.province_id
     */
    private Integer provinceId;

    /**
     *  国家名称
     *  所属表字段为`user_info`.place_name
     */
    private String placeName;

    /**
     *  国家ID
     *  所属表字段为`user_info`.place_id
     */
    private Integer placeId;

    /**
     *  大洲名称
     *  所属表字段为`user_info`.place_category_name
     */
    private String placeCategoryName;

    /**
     *  大洲ID
     *  所属表字段为`user_info`.place_category_id
     */
    private Integer placeCategoryId;

    /**
     *  激活来源。0：未激活；1：Android；2：IOS
     *  所属表字段为`user_info`.activate_source
     */
    private Integer activateSource;

    /**
     *  激活时间
     *  所属表字段为`user_info`.activate_time
     */
    private Date activateTime;

    /**
     *  显示的标签，如VIP，用半角逗号隔开
     *  所属表字段为`user_info`.tag_visible
     */
    private String tagVisible;

    /**
     *  隐式的标签，如XX总裁，用半角逗号隔开
     *  所属表字段为`user_info`.tag_hidden
     */
    private String tagHidden;

    /**
     *  用户等级,预留
     *  所属表字段为`user_info`.user_level
     */
    private Integer userLevel;

    /**
     *  
     *  所属表字段为`user_info`.device_last
     */
    private String deviceLast;

    /**
     *  
     *  所属表字段为`user_info`.app_version
     */
    private String appVersion;

    /**
     *  
     *  所属表字段为`user_info`.updated_at
     */
    private Date updatedAt;

    /**
     *  
     *  所属表字段为`user_info`.created_at
     */
    private Date createdAt;

    /**
     *
     *`user_info`.user_id
     *
     * @return the value of `user_info`.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     *`user_info`.user_id
     *
     * @param userId the value for `user_info`.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     *姓名
     *`user_info`.name
     *
     * @return the value of `user_info`.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     *姓名
     *`user_info`.name
     *
     * @param name the value for `user_info`.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     *昵称
     *`user_info`.nick_name
     *
     * @return the value of `user_info`.nick_name
     *
     * @mbggenerated
     */
    public String getNickName() {
        return nickName;
    }

    /**
     *昵称
     *`user_info`.nick_name
     *
     * @param nickName the value for `user_info`.nick_name
     *
     * @mbggenerated
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     *
     *`user_info`.email
     *
     * @return the value of `user_info`.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     *`user_info`.email
     *
     * @param email the value for `user_info`.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     *
     *`user_info`.union_id
     *
     * @return the value of `user_info`.union_id
     *
     * @mbggenerated
     */
    public String getUnionId() {
        return unionId;
    }

    /**
     *
     *`user_info`.union_id
     *
     * @param unionId the value for `user_info`.union_id
     *
     * @mbggenerated
     */
    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    /**
     *性别
     *`user_info`.gender
     *
     * @return the value of `user_info`.gender
     *
     * @mbggenerated
     */
    public Integer getGender() {
        return gender;
    }

    /**
     *性别
     *`user_info`.gender
     *
     * @param gender the value for `user_info`.gender
     *
     * @mbggenerated
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     *用户来源。1：andriod，2：IOS；3：微信；4：PC；5：GDS；6：去哪儿API；7：携程API；8：去啊API；9：其他
     *`user_info`.source
     *
     * @return the value of `user_info`.source
     *
     * @mbggenerated
     */
    public Integer getSource() {
        return source;
    }

    /**
     *用户来源。1：andriod，2：IOS；3：微信；4：PC；5：GDS；6：去哪儿API；7：携程API；8：去啊API；9：其他
     *`user_info`.source
     *
     * @param source the value for `user_info`.source
     *
     * @mbggenerated
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     *1 自然量（默认）；[1000，3000) Android；[3000，5000) ios；[5000，7000) pc
     *`user_info`.from_channel
     *
     * @return the value of `user_info`.from_channel
     *
     * @mbggenerated
     */
    public Integer getFromChannel() {
        return fromChannel;
    }

    /**
     *1 自然量（默认）；[1000，3000) Android；[3000，5000) ios；[5000，7000) pc
     *`user_info`.from_channel
     *
     * @param fromChannel the value for `user_info`.from_channel
     *
     * @mbggenerated
     */
    public void setFromChannel(Integer fromChannel) {
        this.fromChannel = fromChannel;
    }

    /**
     *代理商ID
     *`user_info`.agent_id
     *
     * @return the value of `user_info`.agent_id
     *
     * @mbggenerated
     */
    public String getAgentId() {
        return agentId;
    }

    /**
     *代理商ID
     *`user_info`.agent_id
     *
     * @param agentId the value for `user_info`.agent_id
     *
     * @mbggenerated
     */
    public void setAgentId(String agentId) {
        this.agentId = agentId == null ? null : agentId.trim();
    }

    /**
     *代理商名称
     *`user_info`.agent_name
     *
     * @return the value of `user_info`.agent_name
     *
     * @mbggenerated
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     *代理商名称
     *`user_info`.agent_name
     *
     * @param agentName the value for `user_info`.agent_name
     *
     * @mbggenerated
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    /**
     *头像
     *`user_info`.avatar
     *
     * @return the value of `user_info`.avatar
     *
     * @mbggenerated
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     *头像
     *`user_info`.avatar
     *
     * @param avatar the value for `user_info`.avatar
     *
     * @mbggenerated
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     *推荐人ID
     *`user_info`.referrer_id
     *
     * @return the value of `user_info`.referrer_id
     *
     * @mbggenerated
     */
    public String getReferrerId() {
        return referrerId;
    }

    /**
     *推荐人ID
     *`user_info`.referrer_id
     *
     * @param referrerId the value for `user_info`.referrer_id
     *
     * @mbggenerated
     */
    public void setReferrerId(String referrerId) {
        this.referrerId = referrerId == null ? null : referrerId.trim();
    }

    /**
     *年龄类型。0：40后，1：50后，2：60后，3：70后，4：80后，5：90后，6：00后，7：10后
     *`user_info`.age_type
     *
     * @return the value of `user_info`.age_type
     *
     * @mbggenerated
     */
    public Integer getAgeType() {
        return ageType;
    }

    /**
     *年龄类型。0：40后，1：50后，2：60后，3：70后，4：80后，5：90后，6：00后，7：10后
     *`user_info`.age_type
     *
     * @param ageType the value for `user_info`.age_type
     *
     * @mbggenerated
     */
    public void setAgeType(Integer ageType) {
        this.ageType = ageType;
    }

    /**
     *出生日期
     *`user_info`.birthday
     *
     * @return the value of `user_info`.birthday
     *
     * @mbggenerated
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     *出生日期
     *`user_info`.birthday
     *
     * @param birthday the value for `user_info`.birthday
     *
     * @mbggenerated
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     *现地址邮编
     *`user_info`.zipcode
     *
     * @return the value of `user_info`.zipcode
     *
     * @mbggenerated
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     *现地址邮编
     *`user_info`.zipcode
     *
     * @param zipcode the value for `user_info`.zipcode
     *
     * @mbggenerated
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    /**
     *详细地址描述
     *`user_info`.address
     *
     * @return the value of `user_info`.address
     *
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     *详细地址描述
     *`user_info`.address
     *
     * @param address the value for `user_info`.address
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     *签名
     *`user_info`.signature
     *
     * @return the value of `user_info`.signature
     *
     * @mbggenerated
     */
    public String getSignature() {
        return signature;
    }

    /**
     *签名
     *`user_info`.signature
     *
     * @param signature the value for `user_info`.signature
     *
     * @mbggenerated
     */
    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    /**
     *是否验证手机号。0：未验证，1：已验证
     *`user_info`.is_phone
     *
     * @return the value of `user_info`.is_phone
     *
     * @mbggenerated
     */
    public Integer getIsPhone() {
        return isPhone;
    }

    /**
     *是否验证手机号。0：未验证，1：已验证
     *`user_info`.is_phone
     *
     * @param isPhone the value for `user_info`.is_phone
     *
     * @mbggenerated
     */
    public void setIsPhone(Integer isPhone) {
        this.isPhone = isPhone;
    }

    /**
     *是否验证邮箱
     *`user_info`.is_email
     *
     * @return the value of `user_info`.is_email
     *
     * @mbggenerated
     */
    public Integer getIsEmail() {
        return isEmail;
    }

    /**
     *是否验证邮箱
     *`user_info`.is_email
     *
     * @param isEmail the value for `user_info`.is_email
     *
     * @mbggenerated
     */
    public void setIsEmail(Integer isEmail) {
        this.isEmail = isEmail;
    }

    /**
     *最后登入ip
     *`user_info`.ip_last
     *
     * @return the value of `user_info`.ip_last
     *
     * @mbggenerated
     */
    public String getIpLast() {
        return ipLast;
    }

    /**
     *最后登入ip
     *`user_info`.ip_last
     *
     * @param ipLast the value for `user_info`.ip_last
     *
     * @mbggenerated
     */
    public void setIpLast(String ipLast) {
        this.ipLast = ipLast == null ? null : ipLast.trim();
    }

    /**
     *最后一次登入的浏览器信息
     *`user_info`.browser_last
     *
     * @return the value of `user_info`.browser_last
     *
     * @mbggenerated
     */
    public String getBrowserLast() {
        return browserLast;
    }

    /**
     *最后一次登入的浏览器信息
     *`user_info`.browser_last
     *
     * @param browserLast the value for `user_info`.browser_last
     *
     * @mbggenerated
     */
    public void setBrowserLast(String browserLast) {
        this.browserLast = browserLast == null ? null : browserLast.trim();
    }

    /**
     *
     *`user_info`.place_city_name
     *
     * @return the value of `user_info`.place_city_name
     *
     * @mbggenerated
     */
    public String getPlaceCityName() {
        return placeCityName;
    }

    /**
     *
     *`user_info`.place_city_name
     *
     * @param placeCityName the value for `user_info`.place_city_name
     *
     * @mbggenerated
     */
    public void setPlaceCityName(String placeCityName) {
        this.placeCityName = placeCityName == null ? null : placeCityName.trim();
    }

    /**
     *
     *`user_info`.place_city_id
     *
     * @return the value of `user_info`.place_city_id
     *
     * @mbggenerated
     */
    public Integer getPlaceCityId() {
        return placeCityId;
    }

    /**
     *
     *`user_info`.place_city_id
     *
     * @param placeCityId the value for `user_info`.place_city_id
     *
     * @mbggenerated
     */
    public void setPlaceCityId(Integer placeCityId) {
        this.placeCityId = placeCityId;
    }

    /**
     *省（州）名称
     *`user_info`.province_name
     *
     * @return the value of `user_info`.province_name
     *
     * @mbggenerated
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     *省（州）名称
     *`user_info`.province_name
     *
     * @param provinceName the value for `user_info`.province_name
     *
     * @mbggenerated
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     *
     *`user_info`.province_id
     *
     * @return the value of `user_info`.province_id
     *
     * @mbggenerated
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     *
     *`user_info`.province_id
     *
     * @param provinceId the value for `user_info`.province_id
     *
     * @mbggenerated
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     *国家名称
     *`user_info`.place_name
     *
     * @return the value of `user_info`.place_name
     *
     * @mbggenerated
     */
    public String getPlaceName() {
        return placeName;
    }

    /**
     *国家名称
     *`user_info`.place_name
     *
     * @param placeName the value for `user_info`.place_name
     *
     * @mbggenerated
     */
    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }

    /**
     *国家ID
     *`user_info`.place_id
     *
     * @return the value of `user_info`.place_id
     *
     * @mbggenerated
     */
    public Integer getPlaceId() {
        return placeId;
    }

    /**
     *国家ID
     *`user_info`.place_id
     *
     * @param placeId the value for `user_info`.place_id
     *
     * @mbggenerated
     */
    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    /**
     *大洲名称
     *`user_info`.place_category_name
     *
     * @return the value of `user_info`.place_category_name
     *
     * @mbggenerated
     */
    public String getPlaceCategoryName() {
        return placeCategoryName;
    }

    /**
     *大洲名称
     *`user_info`.place_category_name
     *
     * @param placeCategoryName the value for `user_info`.place_category_name
     *
     * @mbggenerated
     */
    public void setPlaceCategoryName(String placeCategoryName) {
        this.placeCategoryName = placeCategoryName == null ? null : placeCategoryName.trim();
    }

    /**
     *大洲ID
     *`user_info`.place_category_id
     *
     * @return the value of `user_info`.place_category_id
     *
     * @mbggenerated
     */
    public Integer getPlaceCategoryId() {
        return placeCategoryId;
    }

    /**
     *大洲ID
     *`user_info`.place_category_id
     *
     * @param placeCategoryId the value for `user_info`.place_category_id
     *
     * @mbggenerated
     */
    public void setPlaceCategoryId(Integer placeCategoryId) {
        this.placeCategoryId = placeCategoryId;
    }

    /**
     *激活来源。0：未激活；1：Android；2：IOS
     *`user_info`.activate_source
     *
     * @return the value of `user_info`.activate_source
     *
     * @mbggenerated
     */
    public Integer getActivateSource() {
        return activateSource;
    }

    /**
     *激活来源。0：未激活；1：Android；2：IOS
     *`user_info`.activate_source
     *
     * @param activateSource the value for `user_info`.activate_source
     *
     * @mbggenerated
     */
    public void setActivateSource(Integer activateSource) {
        this.activateSource = activateSource;
    }

    /**
     *激活时间
     *`user_info`.activate_time
     *
     * @return the value of `user_info`.activate_time
     *
     * @mbggenerated
     */
    public Date getActivateTime() {
        return activateTime;
    }

    /**
     *激活时间
     *`user_info`.activate_time
     *
     * @param activateTime the value for `user_info`.activate_time
     *
     * @mbggenerated
     */
    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    /**
     *显示的标签，如VIP，用半角逗号隔开
     *`user_info`.tag_visible
     *
     * @return the value of `user_info`.tag_visible
     *
     * @mbggenerated
     */
    public String getTagVisible() {
        return tagVisible;
    }

    /**
     *显示的标签，如VIP，用半角逗号隔开
     *`user_info`.tag_visible
     *
     * @param tagVisible the value for `user_info`.tag_visible
     *
     * @mbggenerated
     */
    public void setTagVisible(String tagVisible) {
        this.tagVisible = tagVisible == null ? null : tagVisible.trim();
    }

    /**
     *隐式的标签，如XX总裁，用半角逗号隔开
     *`user_info`.tag_hidden
     *
     * @return the value of `user_info`.tag_hidden
     *
     * @mbggenerated
     */
    public String getTagHidden() {
        return tagHidden;
    }

    /**
     *隐式的标签，如XX总裁，用半角逗号隔开
     *`user_info`.tag_hidden
     *
     * @param tagHidden the value for `user_info`.tag_hidden
     *
     * @mbggenerated
     */
    public void setTagHidden(String tagHidden) {
        this.tagHidden = tagHidden == null ? null : tagHidden.trim();
    }

    /**
     *用户等级,预留
     *`user_info`.user_level
     *
     * @return the value of `user_info`.user_level
     *
     * @mbggenerated
     */
    public Integer getUserLevel() {
        return userLevel;
    }

    /**
     *用户等级,预留
     *`user_info`.user_level
     *
     * @param userLevel the value for `user_info`.user_level
     *
     * @mbggenerated
     */
    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    /**
     *
     *`user_info`.device_last
     *
     * @return the value of `user_info`.device_last
     *
     * @mbggenerated
     */
    public String getDeviceLast() {
        return deviceLast;
    }

    /**
     *
     *`user_info`.device_last
     *
     * @param deviceLast the value for `user_info`.device_last
     *
     * @mbggenerated
     */
    public void setDeviceLast(String deviceLast) {
        this.deviceLast = deviceLast == null ? null : deviceLast.trim();
    }

    /**
     *
     *`user_info`.app_version
     *
     * @return the value of `user_info`.app_version
     *
     * @mbggenerated
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     *
     *`user_info`.app_version
     *
     * @param appVersion the value for `user_info`.app_version
     *
     * @mbggenerated
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    /**
     *
     *`user_info`.updated_at
     *
     * @return the value of `user_info`.updated_at
     *
     * @mbggenerated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     *`user_info`.updated_at
     *
     * @param updatedAt the value for `user_info`.updated_at
     *
     * @mbggenerated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     *`user_info`.created_at
     *
     * @return the value of `user_info`.created_at
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     *
     *`user_info`.created_at
     *
     * @param createdAt the value for `user_info`.created_at
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `user_info`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", nickName=").append(nickName);
        sb.append(", email=").append(email);
        sb.append(", unionId=").append(unionId);
        sb.append(", gender=").append(gender);
        sb.append(", source=").append(source);
        sb.append(", fromChannel=").append(fromChannel);
        sb.append(", agentId=").append(agentId);
        sb.append(", agentName=").append(agentName);
        sb.append(", avatar=").append(avatar);
        sb.append(", referrerId=").append(referrerId);
        sb.append(", ageType=").append(ageType);
        sb.append(", birthday=").append(birthday);
        sb.append(", zipcode=").append(zipcode);
        sb.append(", address=").append(address);
        sb.append(", signature=").append(signature);
        sb.append(", isPhone=").append(isPhone);
        sb.append(", isEmail=").append(isEmail);
        sb.append(", ipLast=").append(ipLast);
        sb.append(", browserLast=").append(browserLast);
        sb.append(", placeCityName=").append(placeCityName);
        sb.append(", placeCityId=").append(placeCityId);
        sb.append(", provinceName=").append(provinceName);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", placeName=").append(placeName);
        sb.append(", placeId=").append(placeId);
        sb.append(", placeCategoryName=").append(placeCategoryName);
        sb.append(", placeCategoryId=").append(placeCategoryId);
        sb.append(", activateSource=").append(activateSource);
        sb.append(", activateTime=").append(activateTime);
        sb.append(", tagVisible=").append(tagVisible);
        sb.append(", tagHidden=").append(tagHidden);
        sb.append(", userLevel=").append(userLevel);
        sb.append(", deviceLast=").append(deviceLast);
        sb.append(", appVersion=").append(appVersion);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append("]");
        return sb.toString();
    }
}