package com.hbc.api.trade.bdata.mapper.channel.gen.bean;

import java.util.Date;

public class ChannelAgentUser {
    /**
     *  
     *  所属表字段为`channel_agent_user`.agent_user_id
     */
    private Long agentUserId;

    /**
     *  
     *  所属表字段为`channel_agent_user`.role_id
     */
    private Integer roleId;

    /**
     *  
     *  所属表字段为`channel_agent_user`.role_name
     */
    private String roleName;

    /**
     *  
     *  所属表字段为`channel_agent_user`.agent_id
     */
    private String agentId;

    /**
     *  
     *  所属表字段为`channel_agent_user`.agent_name
     */
    private String agentName;

    /**
     *  
     *  所属表字段为`channel_agent_user`.login_name
     */
    private String loginName;

    /**
     *  
     *  所属表字段为`channel_agent_user`.agent_user_name
     */
    private String agentUserName;

    /**
     *  
     *  所属表字段为`channel_agent_user`.password
     */
    private String password;

    /**
     *  
     *  所属表字段为`channel_agent_user`.encrypted_pwd
     */
    private String encryptedPwd;

    /**
     *  
     *  所属表字段为`channel_agent_user`.phone
     */
    private String phone;

    /**
     *  
     *  所属表字段为`channel_agent_user`.email
     */
    private String email;

    /**
     *  有效期（在有效期内可用）
     *  所属表字段为`channel_agent_user`.expire_time
     */
    private Date expireTime;

    /**
     *  使用限额（扣款/下订单操作），0-表示不限制。限额暂定为累计计算，而非每笔金额限制。
     *  所属表字段为`channel_agent_user`.quota
     */
    private String quota;

    /**
     *  该代理员工总计订单数（冗余字段）
     *  所属表字段为`channel_agent_user`.login_num
     */
    private Integer loginNum;

    /**
     *  累计使用金额（下单总额，冗余字段）
     *  所属表字段为`channel_agent_user`.order_num
     */
    private Integer orderNum;

    /**
     *  
     *  所属表字段为`channel_agent_user`.last_login_time
     */
    private Date lastLoginTime;

    /**
     *  
     *  所属表字段为`channel_agent_user`.total_price
     */
    private Double totalPrice;

    /**
     *  账号类型：0=普通销售；1=管理员；
     *  所属表字段为`channel_agent_user`.type
     */
    private Integer type;

    /**
     *  账号状态。0-已限制（过期）；1-正常（默认）；-1-已删除
     *  所属表字段为`channel_agent_user`.status
     */
    private Integer status;

    /**
     *  
     *  所属表字段为`channel_agent_user`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`channel_agent_user`.create_time
     */
    private Date createTime;

    /**
     *
     *`channel_agent_user`.agent_user_id
     *
     * @return the value of `channel_agent_user`.agent_user_id
     *
     * @mbggenerated
     */
    public Long getAgentUserId() {
        return agentUserId;
    }

    /**
     *
     *`channel_agent_user`.agent_user_id
     *
     * @param agentUserId the value for `channel_agent_user`.agent_user_id
     *
     * @mbggenerated
     */
    public void setAgentUserId(Long agentUserId) {
        this.agentUserId = agentUserId;
    }

    /**
     *
     *`channel_agent_user`.role_id
     *
     * @return the value of `channel_agent_user`.role_id
     *
     * @mbggenerated
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     *
     *`channel_agent_user`.role_id
     *
     * @param roleId the value for `channel_agent_user`.role_id
     *
     * @mbggenerated
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     *
     *`channel_agent_user`.role_name
     *
     * @return the value of `channel_agent_user`.role_name
     *
     * @mbggenerated
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     *
     *`channel_agent_user`.role_name
     *
     * @param roleName the value for `channel_agent_user`.role_name
     *
     * @mbggenerated
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     *
     *`channel_agent_user`.agent_id
     *
     * @return the value of `channel_agent_user`.agent_id
     *
     * @mbggenerated
     */
    public String getAgentId() {
        return agentId;
    }

    /**
     *
     *`channel_agent_user`.agent_id
     *
     * @param agentId the value for `channel_agent_user`.agent_id
     *
     * @mbggenerated
     */
    public void setAgentId(String agentId) {
        this.agentId = agentId == null ? null : agentId.trim();
    }

    /**
     *
     *`channel_agent_user`.agent_name
     *
     * @return the value of `channel_agent_user`.agent_name
     *
     * @mbggenerated
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     *
     *`channel_agent_user`.agent_name
     *
     * @param agentName the value for `channel_agent_user`.agent_name
     *
     * @mbggenerated
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    /**
     *
     *`channel_agent_user`.login_name
     *
     * @return the value of `channel_agent_user`.login_name
     *
     * @mbggenerated
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     *
     *`channel_agent_user`.login_name
     *
     * @param loginName the value for `channel_agent_user`.login_name
     *
     * @mbggenerated
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     *
     *`channel_agent_user`.agent_user_name
     *
     * @return the value of `channel_agent_user`.agent_user_name
     *
     * @mbggenerated
     */
    public String getAgentUserName() {
        return agentUserName;
    }

    /**
     *
     *`channel_agent_user`.agent_user_name
     *
     * @param agentUserName the value for `channel_agent_user`.agent_user_name
     *
     * @mbggenerated
     */
    public void setAgentUserName(String agentUserName) {
        this.agentUserName = agentUserName == null ? null : agentUserName.trim();
    }

    /**
     *
     *`channel_agent_user`.password
     *
     * @return the value of `channel_agent_user`.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     *`channel_agent_user`.password
     *
     * @param password the value for `channel_agent_user`.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     *
     *`channel_agent_user`.encrypted_pwd
     *
     * @return the value of `channel_agent_user`.encrypted_pwd
     *
     * @mbggenerated
     */
    public String getEncryptedPwd() {
        return encryptedPwd;
    }

    /**
     *
     *`channel_agent_user`.encrypted_pwd
     *
     * @param encryptedPwd the value for `channel_agent_user`.encrypted_pwd
     *
     * @mbggenerated
     */
    public void setEncryptedPwd(String encryptedPwd) {
        this.encryptedPwd = encryptedPwd == null ? null : encryptedPwd.trim();
    }

    /**
     *
     *`channel_agent_user`.phone
     *
     * @return the value of `channel_agent_user`.phone
     *
     * @mbggenerated
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     *`channel_agent_user`.phone
     *
     * @param phone the value for `channel_agent_user`.phone
     *
     * @mbggenerated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     *
     *`channel_agent_user`.email
     *
     * @return the value of `channel_agent_user`.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     *`channel_agent_user`.email
     *
     * @param email the value for `channel_agent_user`.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     *有效期（在有效期内可用）
     *`channel_agent_user`.expire_time
     *
     * @return the value of `channel_agent_user`.expire_time
     *
     * @mbggenerated
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     *有效期（在有效期内可用）
     *`channel_agent_user`.expire_time
     *
     * @param expireTime the value for `channel_agent_user`.expire_time
     *
     * @mbggenerated
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     *使用限额（扣款/下订单操作），0-表示不限制。限额暂定为累计计算，而非每笔金额限制。
     *`channel_agent_user`.quota
     *
     * @return the value of `channel_agent_user`.quota
     *
     * @mbggenerated
     */
    public String getQuota() {
        return quota;
    }

    /**
     *使用限额（扣款/下订单操作），0-表示不限制。限额暂定为累计计算，而非每笔金额限制。
     *`channel_agent_user`.quota
     *
     * @param quota the value for `channel_agent_user`.quota
     *
     * @mbggenerated
     */
    public void setQuota(String quota) {
        this.quota = quota == null ? null : quota.trim();
    }

    /**
     *该代理员工总计订单数（冗余字段）
     *`channel_agent_user`.login_num
     *
     * @return the value of `channel_agent_user`.login_num
     *
     * @mbggenerated
     */
    public Integer getLoginNum() {
        return loginNum;
    }

    /**
     *该代理员工总计订单数（冗余字段）
     *`channel_agent_user`.login_num
     *
     * @param loginNum the value for `channel_agent_user`.login_num
     *
     * @mbggenerated
     */
    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    /**
     *累计使用金额（下单总额，冗余字段）
     *`channel_agent_user`.order_num
     *
     * @return the value of `channel_agent_user`.order_num
     *
     * @mbggenerated
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     *累计使用金额（下单总额，冗余字段）
     *`channel_agent_user`.order_num
     *
     * @param orderNum the value for `channel_agent_user`.order_num
     *
     * @mbggenerated
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     *
     *`channel_agent_user`.last_login_time
     *
     * @return the value of `channel_agent_user`.last_login_time
     *
     * @mbggenerated
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     *
     *`channel_agent_user`.last_login_time
     *
     * @param lastLoginTime the value for `channel_agent_user`.last_login_time
     *
     * @mbggenerated
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     *
     *`channel_agent_user`.total_price
     *
     * @return the value of `channel_agent_user`.total_price
     *
     * @mbggenerated
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     *
     *`channel_agent_user`.total_price
     *
     * @param totalPrice the value for `channel_agent_user`.total_price
     *
     * @mbggenerated
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     *账号类型：0=普通销售；1=管理员；
     *`channel_agent_user`.type
     *
     * @return the value of `channel_agent_user`.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     *账号类型：0=普通销售；1=管理员；
     *`channel_agent_user`.type
     *
     * @param type the value for `channel_agent_user`.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     *账号状态。0-已限制（过期）；1-正常（默认）；-1-已删除
     *`channel_agent_user`.status
     *
     * @return the value of `channel_agent_user`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *账号状态。0-已限制（过期）；1-正常（默认）；-1-已删除
     *`channel_agent_user`.status
     *
     * @param status the value for `channel_agent_user`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     *`channel_agent_user`.update_time
     *
     * @return the value of `channel_agent_user`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`channel_agent_user`.update_time
     *
     * @param updateTime the value for `channel_agent_user`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`channel_agent_user`.create_time
     *
     * @return the value of `channel_agent_user`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`channel_agent_user`.create_time
     *
     * @param createTime the value for `channel_agent_user`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `channel_agent_user`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", agentUserId=").append(agentUserId);
        sb.append(", roleId=").append(roleId);
        sb.append(", roleName=").append(roleName);
        sb.append(", agentId=").append(agentId);
        sb.append(", agentName=").append(agentName);
        sb.append(", loginName=").append(loginName);
        sb.append(", agentUserName=").append(agentUserName);
        sb.append(", password=").append(password);
        sb.append(", encryptedPwd=").append(encryptedPwd);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", quota=").append(quota);
        sb.append(", loginNum=").append(loginNum);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}