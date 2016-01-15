package com.hbc.api.trade.bdata.mapper.guide.gen.bean;

import java.util.Date;

public class GuideActivity {
    /**
     *  导游行为id
     *  所属表字段为`guide_activity`.guide_activity_id
     */
    private Integer guideActivityId;

    /**
     *  导游id
     *  所属表字段为`guide_activity`.guide_id
     */
    private String guideId;

    /**
     *  设备key
     *  所属表字段为`guide_activity`.access_key
     */
    private String accessKey;

    /**
     *  应用宝：yingyongbao
            安智：A-anzhi
            百度手机助手：A-baidu
            安卓市场：A-anzhuo
            91：A-91
            豌豆荚：wandoujia
            360手机助手：A-360
            小米应用商店：xiaomi
            google play：google play
     *  所属表字段为`guide_activity`.channel
     */
    private String channel;

    /**
     *  操作类型：1.上线；2.appList;3.卸载
     *  所属表字段为`guide_activity`.op_type
     */
    private Integer opType;

    /**
     *  用户类型：-99-未注册；0-待处理（默认）；1-已签约；2-未签约（导游签约资料已完整）；-1-签约失败；5-报名；
     *  所属表字段为`guide_activity`.user_type
     */
    private Integer userType;

    /**
     *  操作系统。1-android；2-iOS；3-WindowsPhone
     *  所属表字段为`guide_activity`.device_type
     */
    private Integer deviceType;

    /**
     *  设备名称
     *  所属表字段为`guide_activity`.device_name
     */
    private String deviceName;

    /**
     *  系统版本号
     *  所属表字段为`guide_activity`.os_version
     */
    private String osVersion;

    /**
     *  app版本
     *  所属表字段为`guide_activity`.app_version
     */
    private String appVersion;

    /**
     *  当前网络类型
     *  所属表字段为`guide_activity`.network
     */
    private String network;

    /**
     *  运营商
     *  所属表字段为`guide_activity`.carrier_operator
     */
    private String carrierOperator;

    /**
     *  设备制造商
     *  所属表字段为`guide_activity`.manufacturer
     */
    private String manufacturer;

    /**
     *  客户端时间戳
     *  所属表字段为`guide_activity`.op_time
     */
    private Long opTime;

    /**
     *  MAC地址
     *  所属表字段为`guide_activity`.mac_address
     */
    private String macAddress;

    /**
     *  IMEI
     *  所属表字段为`guide_activity`.imei
     */
    private String imei;

    /**
     *  分表率
     *  所属表字段为`guide_activity`.resolution
     */
    private String resolution;

    /**
     *  Sim卡信息
     *  所属表字段为`guide_activity`.imsi
     */
    private String imsi;

    /**
     *  IDFA
     *  所属表字段为`guide_activity`.idfa
     */
    private String idfa;

    /**
     *  IDFV
     *  所属表字段为`guide_activity`.idfv
     */
    private String idfv;

    /**
     *  更新时间
     *  所属表字段为`guide_activity`.update_time
     */
    private Date updateTime;

    /**
     *  创建时间
     *  所属表字段为`guide_activity`.create_time
     */
    private Date createTime;

    /**
     *  
     *  所属表字段为`guide_activity`.content
     */
    private String content;

    /**
     *导游行为id
     *`guide_activity`.guide_activity_id
     *
     * @return the value of `guide_activity`.guide_activity_id
     *
     * @mbggenerated
     */
    public Integer getGuideActivityId() {
        return guideActivityId;
    }

    /**
     *导游行为id
     *`guide_activity`.guide_activity_id
     *
     * @param guideActivityId the value for `guide_activity`.guide_activity_id
     *
     * @mbggenerated
     */
    public void setGuideActivityId(Integer guideActivityId) {
        this.guideActivityId = guideActivityId;
    }

    /**
     *导游id
     *`guide_activity`.guide_id
     *
     * @return the value of `guide_activity`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *导游id
     *`guide_activity`.guide_id
     *
     * @param guideId the value for `guide_activity`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *设备key
     *`guide_activity`.access_key
     *
     * @return the value of `guide_activity`.access_key
     *
     * @mbggenerated
     */
    public String getAccessKey() {
        return accessKey;
    }

    /**
     *设备key
     *`guide_activity`.access_key
     *
     * @param accessKey the value for `guide_activity`.access_key
     *
     * @mbggenerated
     */
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey == null ? null : accessKey.trim();
    }

    /**
     *应用宝：yingyongbao
            安智：A-anzhi
            百度手机助手：A-baidu
            安卓市场：A-anzhuo
            91：A-91
            豌豆荚：wandoujia
            360手机助手：A-360
            小米应用商店：xiaomi
            google play：google play
     *`guide_activity`.channel
     *
     * @return the value of `guide_activity`.channel
     *
     * @mbggenerated
     */
    public String getChannel() {
        return channel;
    }

    /**
     *应用宝：yingyongbao
            安智：A-anzhi
            百度手机助手：A-baidu
            安卓市场：A-anzhuo
            91：A-91
            豌豆荚：wandoujia
            360手机助手：A-360
            小米应用商店：xiaomi
            google play：google play
     *`guide_activity`.channel
     *
     * @param channel the value for `guide_activity`.channel
     *
     * @mbggenerated
     */
    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    /**
     *操作类型：1.上线；2.appList;3.卸载
     *`guide_activity`.op_type
     *
     * @return the value of `guide_activity`.op_type
     *
     * @mbggenerated
     */
    public Integer getOpType() {
        return opType;
    }

    /**
     *操作类型：1.上线；2.appList;3.卸载
     *`guide_activity`.op_type
     *
     * @param opType the value for `guide_activity`.op_type
     *
     * @mbggenerated
     */
    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    /**
     *用户类型：-99-未注册；0-待处理（默认）；1-已签约；2-未签约（导游签约资料已完整）；-1-签约失败；5-报名；
     *`guide_activity`.user_type
     *
     * @return the value of `guide_activity`.user_type
     *
     * @mbggenerated
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     *用户类型：-99-未注册；0-待处理（默认）；1-已签约；2-未签约（导游签约资料已完整）；-1-签约失败；5-报名；
     *`guide_activity`.user_type
     *
     * @param userType the value for `guide_activity`.user_type
     *
     * @mbggenerated
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     *操作系统。1-android；2-iOS；3-WindowsPhone
     *`guide_activity`.device_type
     *
     * @return the value of `guide_activity`.device_type
     *
     * @mbggenerated
     */
    public Integer getDeviceType() {
        return deviceType;
    }

    /**
     *操作系统。1-android；2-iOS；3-WindowsPhone
     *`guide_activity`.device_type
     *
     * @param deviceType the value for `guide_activity`.device_type
     *
     * @mbggenerated
     */
    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    /**
     *设备名称
     *`guide_activity`.device_name
     *
     * @return the value of `guide_activity`.device_name
     *
     * @mbggenerated
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     *设备名称
     *`guide_activity`.device_name
     *
     * @param deviceName the value for `guide_activity`.device_name
     *
     * @mbggenerated
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    /**
     *系统版本号
     *`guide_activity`.os_version
     *
     * @return the value of `guide_activity`.os_version
     *
     * @mbggenerated
     */
    public String getOsVersion() {
        return osVersion;
    }

    /**
     *系统版本号
     *`guide_activity`.os_version
     *
     * @param osVersion the value for `guide_activity`.os_version
     *
     * @mbggenerated
     */
    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion == null ? null : osVersion.trim();
    }

    /**
     *app版本
     *`guide_activity`.app_version
     *
     * @return the value of `guide_activity`.app_version
     *
     * @mbggenerated
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     *app版本
     *`guide_activity`.app_version
     *
     * @param appVersion the value for `guide_activity`.app_version
     *
     * @mbggenerated
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    /**
     *当前网络类型
     *`guide_activity`.network
     *
     * @return the value of `guide_activity`.network
     *
     * @mbggenerated
     */
    public String getNetwork() {
        return network;
    }

    /**
     *当前网络类型
     *`guide_activity`.network
     *
     * @param network the value for `guide_activity`.network
     *
     * @mbggenerated
     */
    public void setNetwork(String network) {
        this.network = network == null ? null : network.trim();
    }

    /**
     *运营商
     *`guide_activity`.carrier_operator
     *
     * @return the value of `guide_activity`.carrier_operator
     *
     * @mbggenerated
     */
    public String getCarrierOperator() {
        return carrierOperator;
    }

    /**
     *运营商
     *`guide_activity`.carrier_operator
     *
     * @param carrierOperator the value for `guide_activity`.carrier_operator
     *
     * @mbggenerated
     */
    public void setCarrierOperator(String carrierOperator) {
        this.carrierOperator = carrierOperator == null ? null : carrierOperator.trim();
    }

    /**
     *设备制造商
     *`guide_activity`.manufacturer
     *
     * @return the value of `guide_activity`.manufacturer
     *
     * @mbggenerated
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     *设备制造商
     *`guide_activity`.manufacturer
     *
     * @param manufacturer the value for `guide_activity`.manufacturer
     *
     * @mbggenerated
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    /**
     *客户端时间戳
     *`guide_activity`.op_time
     *
     * @return the value of `guide_activity`.op_time
     *
     * @mbggenerated
     */
    public Long getOpTime() {
        return opTime;
    }

    /**
     *客户端时间戳
     *`guide_activity`.op_time
     *
     * @param opTime the value for `guide_activity`.op_time
     *
     * @mbggenerated
     */
    public void setOpTime(Long opTime) {
        this.opTime = opTime;
    }

    /**
     *MAC地址
     *`guide_activity`.mac_address
     *
     * @return the value of `guide_activity`.mac_address
     *
     * @mbggenerated
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     *MAC地址
     *`guide_activity`.mac_address
     *
     * @param macAddress the value for `guide_activity`.mac_address
     *
     * @mbggenerated
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress == null ? null : macAddress.trim();
    }

    /**
     *IMEI
     *`guide_activity`.imei
     *
     * @return the value of `guide_activity`.imei
     *
     * @mbggenerated
     */
    public String getImei() {
        return imei;
    }

    /**
     *IMEI
     *`guide_activity`.imei
     *
     * @param imei the value for `guide_activity`.imei
     *
     * @mbggenerated
     */
    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    /**
     *分表率
     *`guide_activity`.resolution
     *
     * @return the value of `guide_activity`.resolution
     *
     * @mbggenerated
     */
    public String getResolution() {
        return resolution;
    }

    /**
     *分表率
     *`guide_activity`.resolution
     *
     * @param resolution the value for `guide_activity`.resolution
     *
     * @mbggenerated
     */
    public void setResolution(String resolution) {
        this.resolution = resolution == null ? null : resolution.trim();
    }

    /**
     *Sim卡信息
     *`guide_activity`.imsi
     *
     * @return the value of `guide_activity`.imsi
     *
     * @mbggenerated
     */
    public String getImsi() {
        return imsi;
    }

    /**
     *Sim卡信息
     *`guide_activity`.imsi
     *
     * @param imsi the value for `guide_activity`.imsi
     *
     * @mbggenerated
     */
    public void setImsi(String imsi) {
        this.imsi = imsi == null ? null : imsi.trim();
    }

    /**
     *IDFA
     *`guide_activity`.idfa
     *
     * @return the value of `guide_activity`.idfa
     *
     * @mbggenerated
     */
    public String getIdfa() {
        return idfa;
    }

    /**
     *IDFA
     *`guide_activity`.idfa
     *
     * @param idfa the value for `guide_activity`.idfa
     *
     * @mbggenerated
     */
    public void setIdfa(String idfa) {
        this.idfa = idfa == null ? null : idfa.trim();
    }

    /**
     *IDFV
     *`guide_activity`.idfv
     *
     * @return the value of `guide_activity`.idfv
     *
     * @mbggenerated
     */
    public String getIdfv() {
        return idfv;
    }

    /**
     *IDFV
     *`guide_activity`.idfv
     *
     * @param idfv the value for `guide_activity`.idfv
     *
     * @mbggenerated
     */
    public void setIdfv(String idfv) {
        this.idfv = idfv == null ? null : idfv.trim();
    }

    /**
     *更新时间
     *`guide_activity`.update_time
     *
     * @return the value of `guide_activity`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新时间
     *`guide_activity`.update_time
     *
     * @param updateTime the value for `guide_activity`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *创建时间
     *`guide_activity`.create_time
     *
     * @return the value of `guide_activity`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     *`guide_activity`.create_time
     *
     * @param createTime the value for `guide_activity`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     *`guide_activity`.content
     *
     * @return the value of `guide_activity`.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     *
     *`guide_activity`.content
     *
     * @param content the value for `guide_activity`.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_activity`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guideActivityId=").append(guideActivityId);
        sb.append(", guideId=").append(guideId);
        sb.append(", accessKey=").append(accessKey);
        sb.append(", channel=").append(channel);
        sb.append(", opType=").append(opType);
        sb.append(", userType=").append(userType);
        sb.append(", deviceType=").append(deviceType);
        sb.append(", deviceName=").append(deviceName);
        sb.append(", osVersion=").append(osVersion);
        sb.append(", appVersion=").append(appVersion);
        sb.append(", network=").append(network);
        sb.append(", carrierOperator=").append(carrierOperator);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", opTime=").append(opTime);
        sb.append(", macAddress=").append(macAddress);
        sb.append(", imei=").append(imei);
        sb.append(", resolution=").append(resolution);
        sb.append(", imsi=").append(imsi);
        sb.append(", idfa=").append(idfa);
        sb.append(", idfv=").append(idfv);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}