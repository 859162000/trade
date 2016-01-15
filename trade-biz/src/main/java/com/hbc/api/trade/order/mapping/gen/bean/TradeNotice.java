package com.hbc.api.trade.order.mapping.gen.bean;

import java.util.Date;

public class TradeNotice {
    /**
     *  通知ID
     *  所属表字段为`trade_notice`.notice_no
     */
    private String noticeNo;

    /**
     *  订单号
     *  所属表字段为`trade_notice`.order_no
     */
    private String orderNo;

    /**
     *  发送的目标用户ID
     *  所属表字段为`trade_notice`.user_id
     */
    private Integer userId;

    /**
     *  发送的目标用户类型：1-客服运营；2-导游；3-用户；4-代理用户
     *  所属表字段为`trade_notice`.user_type
     */
    private Integer userType;

    /**
     *  通知次数
     *  所属表字段为`trade_notice`.notice_num
     */
    private Integer noticeNum;

    /**
     *  通知类型：1：PUSH （目前只支持）2：国内SMS 3：国际SMS 4：weChat
     *  所属表字段为`trade_notice`.notice_type
     */
    private Integer noticeType;

    /**
     *  是否完成通知 0：未完成 1：完成
     *  所属表字段为`trade_notice`.flag
     */
    private Integer flag;

    /**
     *  通知目标 token 或者手机号
     *  所属表字段为`trade_notice`.notice_target
     */
    private String noticeTarget;

    /**
     *  通知内容
     *  所属表字段为`trade_notice`.notice_content
     */
    private String noticeContent;

    /**
     *  计划通知时间
     *  所属表字段为`trade_notice`.plan_time
     */
    private Date planTime;

    /**
     *  消息发送时间
     *  所属表字段为`trade_notice`.send_time
     */
    private Date sendTime;

    /**
     *  提醒种类：1-服务到时间，导游无操作
     *  所属表字段为`trade_notice`.alarm_type
     */
    private Integer alarmType;

    /**
     *  状态：0-初始化（默认）；1-成功；-1：失败
     *  所属表字段为`trade_notice`.alarm_status
     */
    private Integer alarmStatus;

    /**
     *  
     *  所属表字段为`trade_notice`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`trade_notice`.create_time
     */
    private Date createTime;

    /**
     *通知ID
     *`trade_notice`.notice_no
     *
     * @return the value of `trade_notice`.notice_no
     *
     * @mbggenerated
     */
    public String getNoticeNo() {
        return noticeNo;
    }

    /**
     *通知ID
     *`trade_notice`.notice_no
     *
     * @param noticeNo the value for `trade_notice`.notice_no
     *
     * @mbggenerated
     */
    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo == null ? null : noticeNo.trim();
    }

    /**
     *订单号
     *`trade_notice`.order_no
     *
     * @return the value of `trade_notice`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *订单号
     *`trade_notice`.order_no
     *
     * @param orderNo the value for `trade_notice`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *发送的目标用户ID
     *`trade_notice`.user_id
     *
     * @return the value of `trade_notice`.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *发送的目标用户ID
     *`trade_notice`.user_id
     *
     * @param userId the value for `trade_notice`.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     *发送的目标用户类型：1-客服运营；2-导游；3-用户；4-代理用户
     *`trade_notice`.user_type
     *
     * @return the value of `trade_notice`.user_type
     *
     * @mbggenerated
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     *发送的目标用户类型：1-客服运营；2-导游；3-用户；4-代理用户
     *`trade_notice`.user_type
     *
     * @param userType the value for `trade_notice`.user_type
     *
     * @mbggenerated
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     *通知次数
     *`trade_notice`.notice_num
     *
     * @return the value of `trade_notice`.notice_num
     *
     * @mbggenerated
     */
    public Integer getNoticeNum() {
        return noticeNum;
    }

    /**
     *通知次数
     *`trade_notice`.notice_num
     *
     * @param noticeNum the value for `trade_notice`.notice_num
     *
     * @mbggenerated
     */
    public void setNoticeNum(Integer noticeNum) {
        this.noticeNum = noticeNum;
    }

    /**
     *通知类型：1：PUSH （目前只支持）2：国内SMS 3：国际SMS 4：weChat
     *`trade_notice`.notice_type
     *
     * @return the value of `trade_notice`.notice_type
     *
     * @mbggenerated
     */
    public Integer getNoticeType() {
        return noticeType;
    }

    /**
     *通知类型：1：PUSH （目前只支持）2：国内SMS 3：国际SMS 4：weChat
     *`trade_notice`.notice_type
     *
     * @param noticeType the value for `trade_notice`.notice_type
     *
     * @mbggenerated
     */
    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    /**
     *是否完成通知 0：未完成 1：完成
     *`trade_notice`.flag
     *
     * @return the value of `trade_notice`.flag
     *
     * @mbggenerated
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     *是否完成通知 0：未完成 1：完成
     *`trade_notice`.flag
     *
     * @param flag the value for `trade_notice`.flag
     *
     * @mbggenerated
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     *通知目标 token 或者手机号
     *`trade_notice`.notice_target
     *
     * @return the value of `trade_notice`.notice_target
     *
     * @mbggenerated
     */
    public String getNoticeTarget() {
        return noticeTarget;
    }

    /**
     *通知目标 token 或者手机号
     *`trade_notice`.notice_target
     *
     * @param noticeTarget the value for `trade_notice`.notice_target
     *
     * @mbggenerated
     */
    public void setNoticeTarget(String noticeTarget) {
        this.noticeTarget = noticeTarget == null ? null : noticeTarget.trim();
    }

    /**
     *通知内容
     *`trade_notice`.notice_content
     *
     * @return the value of `trade_notice`.notice_content
     *
     * @mbggenerated
     */
    public String getNoticeContent() {
        return noticeContent;
    }

    /**
     *通知内容
     *`trade_notice`.notice_content
     *
     * @param noticeContent the value for `trade_notice`.notice_content
     *
     * @mbggenerated
     */
    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
    }

    /**
     *计划通知时间
     *`trade_notice`.plan_time
     *
     * @return the value of `trade_notice`.plan_time
     *
     * @mbggenerated
     */
    public Date getPlanTime() {
        return planTime;
    }

    /**
     *计划通知时间
     *`trade_notice`.plan_time
     *
     * @param planTime the value for `trade_notice`.plan_time
     *
     * @mbggenerated
     */
    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    /**
     *消息发送时间
     *`trade_notice`.send_time
     *
     * @return the value of `trade_notice`.send_time
     *
     * @mbggenerated
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     *消息发送时间
     *`trade_notice`.send_time
     *
     * @param sendTime the value for `trade_notice`.send_time
     *
     * @mbggenerated
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     *提醒种类：1-服务到时间，导游无操作
     *`trade_notice`.alarm_type
     *
     * @return the value of `trade_notice`.alarm_type
     *
     * @mbggenerated
     */
    public Integer getAlarmType() {
        return alarmType;
    }

    /**
     *提醒种类：1-服务到时间，导游无操作
     *`trade_notice`.alarm_type
     *
     * @param alarmType the value for `trade_notice`.alarm_type
     *
     * @mbggenerated
     */
    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    /**
     *状态：0-初始化（默认）；1-成功；-1：失败
     *`trade_notice`.alarm_status
     *
     * @return the value of `trade_notice`.alarm_status
     *
     * @mbggenerated
     */
    public Integer getAlarmStatus() {
        return alarmStatus;
    }

    /**
     *状态：0-初始化（默认）；1-成功；-1：失败
     *`trade_notice`.alarm_status
     *
     * @param alarmStatus the value for `trade_notice`.alarm_status
     *
     * @mbggenerated
     */
    public void setAlarmStatus(Integer alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    /**
     *
     *`trade_notice`.update_time
     *
     * @return the value of `trade_notice`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`trade_notice`.update_time
     *
     * @param updateTime the value for `trade_notice`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`trade_notice`.create_time
     *
     * @return the value of `trade_notice`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`trade_notice`.create_time
     *
     * @param createTime the value for `trade_notice`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_notice`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", noticeNo=").append(noticeNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", userId=").append(userId);
        sb.append(", userType=").append(userType);
        sb.append(", noticeNum=").append(noticeNum);
        sb.append(", noticeType=").append(noticeType);
        sb.append(", flag=").append(flag);
        sb.append(", noticeTarget=").append(noticeTarget);
        sb.append(", noticeContent=").append(noticeContent);
        sb.append(", planTime=").append(planTime);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", alarmType=").append(alarmType);
        sb.append(", alarmStatus=").append(alarmStatus);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}