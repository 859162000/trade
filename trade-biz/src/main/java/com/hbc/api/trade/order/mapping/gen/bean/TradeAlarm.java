package com.hbc.api.trade.order.mapping.gen.bean;

import java.util.Date;

public class TradeAlarm {
    /**
     *  主键
     *  所属表字段为`trade_alarm`.alarm_id
     */
    private Integer alarmId;

    /**
     *  订单Id
     *  所属表字段为`trade_alarm`.order_no
     */
    private String orderNo;

    /**
     *  
     *  所属表字段为`trade_alarm`.order_service_time
     */
    private Date orderServiceTime;

    /**
     *  通知目标类型
     *  所属表字段为`trade_alarm`.alarm_type
     */
    private Integer alarmType;

    /**
     *  
     *  所属表字段为`trade_alarm`.alarm_type_name
     */
    private String alarmTypeName;

    /**
     *  是否完成通知 0：未完成 1：完成
     *  所属表字段为`trade_alarm`.flag
     */
    private Integer flag;

    /**
     *  提醒种类：1-服务到时间，导游无操作 2-无人接单;3-临行前提醒；
     *  所属表字段为`trade_alarm`.type
     */
    private Integer type;

    /**
     *  状态：0-初始化（默认）；1-成功；2：失败
     *  所属表字段为`trade_alarm`.status
     */
    private Integer status;

    /**
     *  
     *  所属表字段为`trade_alarm`.send_pre_time
     */
    private Date sendPreTime;

    /**
     *  
     *  所属表字段为`trade_alarm`.send_time
     */
    private Date sendTime;

    /**
     *  
     *  所属表字段为`trade_alarm`.req_info
     */
    private String reqInfo;

    /**
     *  
     *  所属表字段为`trade_alarm`.rsp_info
     */
    private String rspInfo;

    /**
     *  更新时间
     *  所属表字段为`trade_alarm`.update_time
     */
    private Date updateTime;

    /**
     *  创建时间
     *  所属表字段为`trade_alarm`.create_time
     */
    private Date createTime;

    /**
     *主键
     *`trade_alarm`.alarm_id
     *
     * @return the value of `trade_alarm`.alarm_id
     *
     * @mbggenerated
     */
    public Integer getAlarmId() {
        return alarmId;
    }

    /**
     *主键
     *`trade_alarm`.alarm_id
     *
     * @param alarmId the value for `trade_alarm`.alarm_id
     *
     * @mbggenerated
     */
    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    /**
     *订单Id
     *`trade_alarm`.order_no
     *
     * @return the value of `trade_alarm`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *订单Id
     *`trade_alarm`.order_no
     *
     * @param orderNo the value for `trade_alarm`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *
     *`trade_alarm`.order_service_time
     *
     * @return the value of `trade_alarm`.order_service_time
     *
     * @mbggenerated
     */
    public Date getOrderServiceTime() {
        return orderServiceTime;
    }

    /**
     *
     *`trade_alarm`.order_service_time
     *
     * @param orderServiceTime the value for `trade_alarm`.order_service_time
     *
     * @mbggenerated
     */
    public void setOrderServiceTime(Date orderServiceTime) {
        this.orderServiceTime = orderServiceTime;
    }

    /**
     *通知目标类型
     *`trade_alarm`.alarm_type
     *
     * @return the value of `trade_alarm`.alarm_type
     *
     * @mbggenerated
     */
    public Integer getAlarmType() {
        return alarmType;
    }

    /**
     *通知目标类型
     *`trade_alarm`.alarm_type
     *
     * @param alarmType the value for `trade_alarm`.alarm_type
     *
     * @mbggenerated
     */
    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    /**
     *
     *`trade_alarm`.alarm_type_name
     *
     * @return the value of `trade_alarm`.alarm_type_name
     *
     * @mbggenerated
     */
    public String getAlarmTypeName() {
        return alarmTypeName;
    }

    /**
     *
     *`trade_alarm`.alarm_type_name
     *
     * @param alarmTypeName the value for `trade_alarm`.alarm_type_name
     *
     * @mbggenerated
     */
    public void setAlarmTypeName(String alarmTypeName) {
        this.alarmTypeName = alarmTypeName == null ? null : alarmTypeName.trim();
    }

    /**
     *是否完成通知 0：未完成 1：完成
     *`trade_alarm`.flag
     *
     * @return the value of `trade_alarm`.flag
     *
     * @mbggenerated
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     *是否完成通知 0：未完成 1：完成
     *`trade_alarm`.flag
     *
     * @param flag the value for `trade_alarm`.flag
     *
     * @mbggenerated
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     *提醒种类：1-服务到时间，导游无操作 2-无人接单;3-临行前提醒；
     *`trade_alarm`.type
     *
     * @return the value of `trade_alarm`.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     *提醒种类：1-服务到时间，导游无操作 2-无人接单;3-临行前提醒；
     *`trade_alarm`.type
     *
     * @param type the value for `trade_alarm`.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     *状态：0-初始化（默认）；1-成功；2：失败
     *`trade_alarm`.status
     *
     * @return the value of `trade_alarm`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *状态：0-初始化（默认）；1-成功；2：失败
     *`trade_alarm`.status
     *
     * @param status the value for `trade_alarm`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     *`trade_alarm`.send_pre_time
     *
     * @return the value of `trade_alarm`.send_pre_time
     *
     * @mbggenerated
     */
    public Date getSendPreTime() {
        return sendPreTime;
    }

    /**
     *
     *`trade_alarm`.send_pre_time
     *
     * @param sendPreTime the value for `trade_alarm`.send_pre_time
     *
     * @mbggenerated
     */
    public void setSendPreTime(Date sendPreTime) {
        this.sendPreTime = sendPreTime;
    }

    /**
     *
     *`trade_alarm`.send_time
     *
     * @return the value of `trade_alarm`.send_time
     *
     * @mbggenerated
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     *
     *`trade_alarm`.send_time
     *
     * @param sendTime the value for `trade_alarm`.send_time
     *
     * @mbggenerated
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     *
     *`trade_alarm`.req_info
     *
     * @return the value of `trade_alarm`.req_info
     *
     * @mbggenerated
     */
    public String getReqInfo() {
        return reqInfo;
    }

    /**
     *
     *`trade_alarm`.req_info
     *
     * @param reqInfo the value for `trade_alarm`.req_info
     *
     * @mbggenerated
     */
    public void setReqInfo(String reqInfo) {
        this.reqInfo = reqInfo == null ? null : reqInfo.trim();
    }

    /**
     *
     *`trade_alarm`.rsp_info
     *
     * @return the value of `trade_alarm`.rsp_info
     *
     * @mbggenerated
     */
    public String getRspInfo() {
        return rspInfo;
    }

    /**
     *
     *`trade_alarm`.rsp_info
     *
     * @param rspInfo the value for `trade_alarm`.rsp_info
     *
     * @mbggenerated
     */
    public void setRspInfo(String rspInfo) {
        this.rspInfo = rspInfo == null ? null : rspInfo.trim();
    }

    /**
     *更新时间
     *`trade_alarm`.update_time
     *
     * @return the value of `trade_alarm`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新时间
     *`trade_alarm`.update_time
     *
     * @param updateTime the value for `trade_alarm`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *创建时间
     *`trade_alarm`.create_time
     *
     * @return the value of `trade_alarm`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     *`trade_alarm`.create_time
     *
     * @param createTime the value for `trade_alarm`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_alarm`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", alarmId=").append(alarmId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", orderServiceTime=").append(orderServiceTime);
        sb.append(", alarmType=").append(alarmType);
        sb.append(", alarmTypeName=").append(alarmTypeName);
        sb.append(", flag=").append(flag);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", sendPreTime=").append(sendPreTime);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", reqInfo=").append(reqInfo);
        sb.append(", rspInfo=").append(rspInfo);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}