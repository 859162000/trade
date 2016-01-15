package com.hbc.api.trade.order.mapping.gen.bean;

import java.util.Date;

public class OrderLog {
    /**
     *  日志ID
     *  所属表字段为`trade_order_log`.log_id
     */
    private Integer logId;

    /**
     *  订单号
     *  所属表字段为`trade_order_log`.order_no
     */
    private String orderNo;

    /**
     *  导游ID
     *  所属表字段为`trade_order_log`.guide_id
     */
    private String guideId;

    /**
     *  导游名称
     *  所属表字段为`trade_order_log`.guide_name
     */
    private String guideName;

    /**
     *  日志类型。
                        1001-提交订单；
                        
                        3001-补充订单资料（包括部分内容修改）；
                        
                        1002-付款；
                        3002-已确定导游（导游已接单）；
                        1004-导游已到达预定地点；
                        1005-导游已接到客人（开始出发）；
                        1006-已完成服务；
                        1007-客户已确认服务
                        3003-导游评价客人；
                        3004-客人评价导游；
                        
                        
                        1008: 交易成功
                        
                        
                        101-客人（代理）取消订单；
                        102-导游（运营）取消订单；
                        103-手动添加备注信息；
                        4003-运营确认退款（存储具体金额）；
                        4007-系统自动取消订单
     *  所属表字段为`trade_order_log`.log_type
     */
    private Integer logType;

    /**
     *  日志分类。1-代理商行为；2-用户行为；3-导游行为；4-客服行为；5-系统行为；6-地接社行为
     *  所属表字段为`trade_order_log`.op_type
     */
    private Integer opType;

    /**
     *  日志文本内容。根据type不同，文本内容不同。系统自动填写
     *  所属表字段为`trade_order_log`.content
     */
    private String content;

    /**
     *  客服或代理商主动填写的备注信息
     *  所属表字段为`trade_order_log`.comment
     */
    private String comment;

    /**
     *  
     *  所属表字段为`trade_order_log`.op_user_id
     */
    private String opUserId;

    /**
     *  
     *  所属表字段为`trade_order_log`.op_user_name
     */
    private String opUserName;

    /**
     *  
     *  所属表字段为`trade_order_log`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`trade_order_log`.create_time
     */
    private Date createTime;

    /**
     *日志ID
     *`trade_order_log`.log_id
     *
     * @return the value of `trade_order_log`.log_id
     *
     * @mbggenerated
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     *日志ID
     *`trade_order_log`.log_id
     *
     * @param logId the value for `trade_order_log`.log_id
     *
     * @mbggenerated
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /**
     *订单号
     *`trade_order_log`.order_no
     *
     * @return the value of `trade_order_log`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *订单号
     *`trade_order_log`.order_no
     *
     * @param orderNo the value for `trade_order_log`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *导游ID
     *`trade_order_log`.guide_id
     *
     * @return the value of `trade_order_log`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *导游ID
     *`trade_order_log`.guide_id
     *
     * @param guideId the value for `trade_order_log`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *导游名称
     *`trade_order_log`.guide_name
     *
     * @return the value of `trade_order_log`.guide_name
     *
     * @mbggenerated
     */
    public String getGuideName() {
        return guideName;
    }

    /**
     *导游名称
     *`trade_order_log`.guide_name
     *
     * @param guideName the value for `trade_order_log`.guide_name
     *
     * @mbggenerated
     */
    public void setGuideName(String guideName) {
        this.guideName = guideName == null ? null : guideName.trim();
    }

    /**
     *日志类型。
                        1001-提交订单；
                        
                        3001-补充订单资料（包括部分内容修改）；
                        
                        1002-付款；
                        3002-已确定导游（导游已接单）；
                        1004-导游已到达预定地点；
                        1005-导游已接到客人（开始出发）；
                        1006-已完成服务；
                        1007-客户已确认服务
                        3003-导游评价客人；
                        3004-客人评价导游；
                        
                        
                        1008: 交易成功
                        
                        
                        101-客人（代理）取消订单；
                        102-导游（运营）取消订单；
                        103-手动添加备注信息；
                        4003-运营确认退款（存储具体金额）；
                        4007-系统自动取消订单
     *`trade_order_log`.log_type
     *
     * @return the value of `trade_order_log`.log_type
     *
     * @mbggenerated
     */
    public Integer getLogType() {
        return logType;
    }

    /**
     *日志类型。
                        1001-提交订单；
                        
                        3001-补充订单资料（包括部分内容修改）；
                        
                        1002-付款；
                        3002-已确定导游（导游已接单）；
                        1004-导游已到达预定地点；
                        1005-导游已接到客人（开始出发）；
                        1006-已完成服务；
                        1007-客户已确认服务
                        3003-导游评价客人；
                        3004-客人评价导游；
                        
                        
                        1008: 交易成功
                        
                        
                        101-客人（代理）取消订单；
                        102-导游（运营）取消订单；
                        103-手动添加备注信息；
                        4003-运营确认退款（存储具体金额）；
                        4007-系统自动取消订单
     *`trade_order_log`.log_type
     *
     * @param logType the value for `trade_order_log`.log_type
     *
     * @mbggenerated
     */
    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    /**
     *日志分类。1-代理商行为；2-用户行为；3-导游行为；4-客服行为；5-系统行为；6-地接社行为
     *`trade_order_log`.op_type
     *
     * @return the value of `trade_order_log`.op_type
     *
     * @mbggenerated
     */
    public Integer getOpType() {
        return opType;
    }

    /**
     *日志分类。1-代理商行为；2-用户行为；3-导游行为；4-客服行为；5-系统行为；6-地接社行为
     *`trade_order_log`.op_type
     *
     * @param opType the value for `trade_order_log`.op_type
     *
     * @mbggenerated
     */
    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    /**
     *日志文本内容。根据type不同，文本内容不同。系统自动填写
     *`trade_order_log`.content
     *
     * @return the value of `trade_order_log`.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     *日志文本内容。根据type不同，文本内容不同。系统自动填写
     *`trade_order_log`.content
     *
     * @param content the value for `trade_order_log`.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     *客服或代理商主动填写的备注信息
     *`trade_order_log`.comment
     *
     * @return the value of `trade_order_log`.comment
     *
     * @mbggenerated
     */
    public String getComment() {
        return comment;
    }

    /**
     *客服或代理商主动填写的备注信息
     *`trade_order_log`.comment
     *
     * @param comment the value for `trade_order_log`.comment
     *
     * @mbggenerated
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     *
     *`trade_order_log`.op_user_id
     *
     * @return the value of `trade_order_log`.op_user_id
     *
     * @mbggenerated
     */
    public String getOpUserId() {
        return opUserId;
    }

    /**
     *
     *`trade_order_log`.op_user_id
     *
     * @param opUserId the value for `trade_order_log`.op_user_id
     *
     * @mbggenerated
     */
    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId == null ? null : opUserId.trim();
    }

    /**
     *
     *`trade_order_log`.op_user_name
     *
     * @return the value of `trade_order_log`.op_user_name
     *
     * @mbggenerated
     */
    public String getOpUserName() {
        return opUserName;
    }

    /**
     *
     *`trade_order_log`.op_user_name
     *
     * @param opUserName the value for `trade_order_log`.op_user_name
     *
     * @mbggenerated
     */
    public void setOpUserName(String opUserName) {
        this.opUserName = opUserName == null ? null : opUserName.trim();
    }

    /**
     *
     *`trade_order_log`.update_time
     *
     * @return the value of `trade_order_log`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`trade_order_log`.update_time
     *
     * @param updateTime the value for `trade_order_log`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`trade_order_log`.create_time
     *
     * @return the value of `trade_order_log`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`trade_order_log`.create_time
     *
     * @param createTime the value for `trade_order_log`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_order_log`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logId=").append(logId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", guideId=").append(guideId);
        sb.append(", guideName=").append(guideName);
        sb.append(", logType=").append(logType);
        sb.append(", opType=").append(opType);
        sb.append(", content=").append(content);
        sb.append(", comment=").append(comment);
        sb.append(", opUserId=").append(opUserId);
        sb.append(", opUserName=").append(opUserName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}