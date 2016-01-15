package com.hbc.api.trade.bdata.mapper.channel.gen.bean;

import java.util.Date;

public class ChannelAgent {
    /**
     *  
     *  所属表字段为`channel_agent`.agent_id
     */
    private String agentId;

    /**
     *  
     *  所属表字段为`channel_agent`.agent_name
     */
    private String agentName;

    /**
     *  该公司超级管理员用户ID（agentUserId），同一公司只有一个超级管理员
     *  所属表字段为`channel_agent`.agent_admin_id
     */
    private String agentAdminId;

    /**
     *  
     *  所属表字段为`channel_agent`.agent_admin_name
     */
    private String agentAdminName;

    /**
     *  
     *  所属表字段为`channel_agent`.province_id
     */
    private Integer provinceId;

    /**
     *  密文密码:md5("token"+"agentkey"
     *  所属表字段为`channel_agent`.province_name
     */
    private String provinceName;

    /**
     *  
     *  所属表字段为`channel_agent`.city_id
     */
    private Integer cityId;

    /**
     *  针对该用户的该设备的令牌
     *  所属表字段为`channel_agent`.city_name
     */
    private String cityName;

    /**
     *  
     *  所属表字段为`channel_agent`.fund_account_id
     */
    private String fundAccountId;

    /**
     *  
     *  所属表字段为`channel_agent`.industry_type
     */
    private Integer industryType;

    /**
     *  
     *  所属表字段为`channel_agent`.phone
     */
    private String phone;

    /**
     *  备注信息（仅运营可见）
     *  所属表字段为`channel_agent`.comment
     */
    private String comment;

    /**
     *  状态1:正常,0:注销,-1:删除
     *  所属表字段为`channel_agent`.status
     */
    private Integer status;

    /**
     *  
     *  所属表字段为`channel_agent`.is_config
     */
    private Integer isConfig;

    /**
     *  
     *  所属表字段为`channel_agent`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`channel_agent`.create_time
     */
    private Date createTime;

    /**
     *  
     *  所属表字段为`channel_agent`.fund_remain
     */
    private Integer fundRemain;

    /**
     *  
     *  所属表字段为`channel_agent`.member_num
     */
    private Integer memberNum;

    /**
     *  支持接机
     *  所属表字段为`channel_agent`.support_receive
     */
    private Integer supportReceive;

    /**
     *  支持送机
     *  所属表字段为`channel_agent`.support_send
     */
    private Integer supportSend;

    /**
     *  支持日租
     *  所属表字段为`channel_agent`.support_perday
     */
    private Integer supportPerday;

    /**
     *  支持次租
     *  所属表字段为`channel_agent`.support_pertime
     */
    private Integer supportPertime;

    /**
     *  PK模式：1服务优先，2收入优先，3响应优先
     *  所属表字段为`channel_agent`.channel_service_type
     */
    private Integer channelServiceType;

    /**
     *
     *`channel_agent`.agent_id
     *
     * @return the value of `channel_agent`.agent_id
     *
     * @mbggenerated
     */
    public String getAgentId() {
        return agentId;
    }

    /**
     *
     *`channel_agent`.agent_id
     *
     * @param agentId the value for `channel_agent`.agent_id
     *
     * @mbggenerated
     */
    public void setAgentId(String agentId) {
        this.agentId = agentId == null ? null : agentId.trim();
    }

    /**
     *
     *`channel_agent`.agent_name
     *
     * @return the value of `channel_agent`.agent_name
     *
     * @mbggenerated
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     *
     *`channel_agent`.agent_name
     *
     * @param agentName the value for `channel_agent`.agent_name
     *
     * @mbggenerated
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    /**
     *该公司超级管理员用户ID（agentUserId），同一公司只有一个超级管理员
     *`channel_agent`.agent_admin_id
     *
     * @return the value of `channel_agent`.agent_admin_id
     *
     * @mbggenerated
     */
    public String getAgentAdminId() {
        return agentAdminId;
    }

    /**
     *该公司超级管理员用户ID（agentUserId），同一公司只有一个超级管理员
     *`channel_agent`.agent_admin_id
     *
     * @param agentAdminId the value for `channel_agent`.agent_admin_id
     *
     * @mbggenerated
     */
    public void setAgentAdminId(String agentAdminId) {
        this.agentAdminId = agentAdminId == null ? null : agentAdminId.trim();
    }

    /**
     *
     *`channel_agent`.agent_admin_name
     *
     * @return the value of `channel_agent`.agent_admin_name
     *
     * @mbggenerated
     */
    public String getAgentAdminName() {
        return agentAdminName;
    }

    /**
     *
     *`channel_agent`.agent_admin_name
     *
     * @param agentAdminName the value for `channel_agent`.agent_admin_name
     *
     * @mbggenerated
     */
    public void setAgentAdminName(String agentAdminName) {
        this.agentAdminName = agentAdminName == null ? null : agentAdminName.trim();
    }

    /**
     *
     *`channel_agent`.province_id
     *
     * @return the value of `channel_agent`.province_id
     *
     * @mbggenerated
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     *
     *`channel_agent`.province_id
     *
     * @param provinceId the value for `channel_agent`.province_id
     *
     * @mbggenerated
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     *密文密码:md5("token"+"agentkey"
     *`channel_agent`.province_name
     *
     * @return the value of `channel_agent`.province_name
     *
     * @mbggenerated
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     *密文密码:md5("token"+"agentkey"
     *`channel_agent`.province_name
     *
     * @param provinceName the value for `channel_agent`.province_name
     *
     * @mbggenerated
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     *
     *`channel_agent`.city_id
     *
     * @return the value of `channel_agent`.city_id
     *
     * @mbggenerated
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     *
     *`channel_agent`.city_id
     *
     * @param cityId the value for `channel_agent`.city_id
     *
     * @mbggenerated
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     *针对该用户的该设备的令牌
     *`channel_agent`.city_name
     *
     * @return the value of `channel_agent`.city_name
     *
     * @mbggenerated
     */
    public String getCityName() {
        return cityName;
    }

    /**
     *针对该用户的该设备的令牌
     *`channel_agent`.city_name
     *
     * @param cityName the value for `channel_agent`.city_name
     *
     * @mbggenerated
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     *
     *`channel_agent`.fund_account_id
     *
     * @return the value of `channel_agent`.fund_account_id
     *
     * @mbggenerated
     */
    public String getFundAccountId() {
        return fundAccountId;
    }

    /**
     *
     *`channel_agent`.fund_account_id
     *
     * @param fundAccountId the value for `channel_agent`.fund_account_id
     *
     * @mbggenerated
     */
    public void setFundAccountId(String fundAccountId) {
        this.fundAccountId = fundAccountId == null ? null : fundAccountId.trim();
    }

    /**
     *
     *`channel_agent`.industry_type
     *
     * @return the value of `channel_agent`.industry_type
     *
     * @mbggenerated
     */
    public Integer getIndustryType() {
        return industryType;
    }

    /**
     *
     *`channel_agent`.industry_type
     *
     * @param industryType the value for `channel_agent`.industry_type
     *
     * @mbggenerated
     */
    public void setIndustryType(Integer industryType) {
        this.industryType = industryType;
    }

    /**
     *
     *`channel_agent`.phone
     *
     * @return the value of `channel_agent`.phone
     *
     * @mbggenerated
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     *`channel_agent`.phone
     *
     * @param phone the value for `channel_agent`.phone
     *
     * @mbggenerated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     *备注信息（仅运营可见）
     *`channel_agent`.comment
     *
     * @return the value of `channel_agent`.comment
     *
     * @mbggenerated
     */
    public String getComment() {
        return comment;
    }

    /**
     *备注信息（仅运营可见）
     *`channel_agent`.comment
     *
     * @param comment the value for `channel_agent`.comment
     *
     * @mbggenerated
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     *状态1:正常,0:注销,-1:删除
     *`channel_agent`.status
     *
     * @return the value of `channel_agent`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *状态1:正常,0:注销,-1:删除
     *`channel_agent`.status
     *
     * @param status the value for `channel_agent`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     *`channel_agent`.is_config
     *
     * @return the value of `channel_agent`.is_config
     *
     * @mbggenerated
     */
    public Integer getIsConfig() {
        return isConfig;
    }

    /**
     *
     *`channel_agent`.is_config
     *
     * @param isConfig the value for `channel_agent`.is_config
     *
     * @mbggenerated
     */
    public void setIsConfig(Integer isConfig) {
        this.isConfig = isConfig;
    }

    /**
     *
     *`channel_agent`.update_time
     *
     * @return the value of `channel_agent`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`channel_agent`.update_time
     *
     * @param updateTime the value for `channel_agent`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`channel_agent`.create_time
     *
     * @return the value of `channel_agent`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`channel_agent`.create_time
     *
     * @param createTime the value for `channel_agent`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     *`channel_agent`.fund_remain
     *
     * @return the value of `channel_agent`.fund_remain
     *
     * @mbggenerated
     */
    public Integer getFundRemain() {
        return fundRemain;
    }

    /**
     *
     *`channel_agent`.fund_remain
     *
     * @param fundRemain the value for `channel_agent`.fund_remain
     *
     * @mbggenerated
     */
    public void setFundRemain(Integer fundRemain) {
        this.fundRemain = fundRemain;
    }

    /**
     *
     *`channel_agent`.member_num
     *
     * @return the value of `channel_agent`.member_num
     *
     * @mbggenerated
     */
    public Integer getMemberNum() {
        return memberNum;
    }

    /**
     *
     *`channel_agent`.member_num
     *
     * @param memberNum the value for `channel_agent`.member_num
     *
     * @mbggenerated
     */
    public void setMemberNum(Integer memberNum) {
        this.memberNum = memberNum;
    }

    /**
     *支持接机
     *`channel_agent`.support_receive
     *
     * @return the value of `channel_agent`.support_receive
     *
     * @mbggenerated
     */
    public Integer getSupportReceive() {
        return supportReceive;
    }

    /**
     *支持接机
     *`channel_agent`.support_receive
     *
     * @param supportReceive the value for `channel_agent`.support_receive
     *
     * @mbggenerated
     */
    public void setSupportReceive(Integer supportReceive) {
        this.supportReceive = supportReceive;
    }

    /**
     *支持送机
     *`channel_agent`.support_send
     *
     * @return the value of `channel_agent`.support_send
     *
     * @mbggenerated
     */
    public Integer getSupportSend() {
        return supportSend;
    }

    /**
     *支持送机
     *`channel_agent`.support_send
     *
     * @param supportSend the value for `channel_agent`.support_send
     *
     * @mbggenerated
     */
    public void setSupportSend(Integer supportSend) {
        this.supportSend = supportSend;
    }

    /**
     *支持日租
     *`channel_agent`.support_perday
     *
     * @return the value of `channel_agent`.support_perday
     *
     * @mbggenerated
     */
    public Integer getSupportPerday() {
        return supportPerday;
    }

    /**
     *支持日租
     *`channel_agent`.support_perday
     *
     * @param supportPerday the value for `channel_agent`.support_perday
     *
     * @mbggenerated
     */
    public void setSupportPerday(Integer supportPerday) {
        this.supportPerday = supportPerday;
    }

    /**
     *支持次租
     *`channel_agent`.support_pertime
     *
     * @return the value of `channel_agent`.support_pertime
     *
     * @mbggenerated
     */
    public Integer getSupportPertime() {
        return supportPertime;
    }

    /**
     *支持次租
     *`channel_agent`.support_pertime
     *
     * @param supportPertime the value for `channel_agent`.support_pertime
     *
     * @mbggenerated
     */
    public void setSupportPertime(Integer supportPertime) {
        this.supportPertime = supportPertime;
    }

    /**
     *PK模式：1服务优先，2收入优先，3响应优先
     *`channel_agent`.channel_service_type
     *
     * @return the value of `channel_agent`.channel_service_type
     *
     * @mbggenerated
     */
    public Integer getChannelServiceType() {
        return channelServiceType;
    }

    /**
     *PK模式：1服务优先，2收入优先，3响应优先
     *`channel_agent`.channel_service_type
     *
     * @param channelServiceType the value for `channel_agent`.channel_service_type
     *
     * @mbggenerated
     */
    public void setChannelServiceType(Integer channelServiceType) {
        this.channelServiceType = channelServiceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `channel_agent`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", agentId=").append(agentId);
        sb.append(", agentName=").append(agentName);
        sb.append(", agentAdminId=").append(agentAdminId);
        sb.append(", agentAdminName=").append(agentAdminName);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", provinceName=").append(provinceName);
        sb.append(", cityId=").append(cityId);
        sb.append(", cityName=").append(cityName);
        sb.append(", fundAccountId=").append(fundAccountId);
        sb.append(", industryType=").append(industryType);
        sb.append(", phone=").append(phone);
        sb.append(", comment=").append(comment);
        sb.append(", status=").append(status);
        sb.append(", isConfig=").append(isConfig);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", fundRemain=").append(fundRemain);
        sb.append(", memberNum=").append(memberNum);
        sb.append(", supportReceive=").append(supportReceive);
        sb.append(", supportSend=").append(supportSend);
        sb.append(", supportPerday=").append(supportPerday);
        sb.append(", supportPertime=").append(supportPertime);
        sb.append(", channelServiceType=").append(channelServiceType);
        sb.append("]");
        return sb.toString();
    }
}