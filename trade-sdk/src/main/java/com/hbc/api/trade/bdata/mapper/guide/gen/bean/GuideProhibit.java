package com.hbc.api.trade.bdata.mapper.guide.gen.bean;

import java.util.Date;

public class GuideProhibit {
    /**
     *  导游禁用功能id
     *  所属表字段为`guide_prohibit`.guide_prohibit_id
     */
    private String guideProhibitId;

    /**
     *  导游id
     *  所属表字段为`guide_prohibit`.guide_id
     */
    private String guideId;

    /**
     *  关联订单编号
     *  所属表字段为`guide_prohibit`.order_no
     */
    private String orderNo;

    /**
     *  禁用类型。1-订单推送；2-接单；3-提现；4-登录
     *  所属表字段为`guide_prohibit`.type
     */
    private Integer type;

    /**
     *  禁用开始时间
     *  所属表字段为`guide_prohibit`.start_date
     */
    private Date startDate;

    /**
     *  禁用结束时间，null为永久
     *  所属表字段为`guide_prohibit`.end_date
     */
    private Date endDate;

    /**
     *  设置禁用的管理员ID（只记录最后一次操作的人）
     *  所属表字段为`guide_prohibit`.op_admin_id
     */
    private String opAdminId;

    /**
     *  设置禁用的管理员姓名（只记录最后一次操作的人）
     *  所属表字段为`guide_prohibit`.op_admin_name
     */
    private String opAdminName;

    /**
     *  处理意见
     *  所属表字段为`guide_prohibit`.comment
     */
    private String comment;

    /**
     *  状态。0-不可用；1-可用（默认）。不可用的记录不生效
     *  所属表字段为`guide_prohibit`.status
     */
    private Integer status;

    /**
     *  最近更新时间
     *  所属表字段为`guide_prohibit`.update_time
     */
    private Date updateTime;

    /**
     *  创建时间
     *  所属表字段为`guide_prohibit`.create_time
     */
    private Date createTime;

    /**
     *导游禁用功能id
     *`guide_prohibit`.guide_prohibit_id
     *
     * @return the value of `guide_prohibit`.guide_prohibit_id
     *
     * @mbggenerated
     */
    public String getGuideProhibitId() {
        return guideProhibitId;
    }

    /**
     *导游禁用功能id
     *`guide_prohibit`.guide_prohibit_id
     *
     * @param guideProhibitId the value for `guide_prohibit`.guide_prohibit_id
     *
     * @mbggenerated
     */
    public void setGuideProhibitId(String guideProhibitId) {
        this.guideProhibitId = guideProhibitId == null ? null : guideProhibitId.trim();
    }

    /**
     *导游id
     *`guide_prohibit`.guide_id
     *
     * @return the value of `guide_prohibit`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *导游id
     *`guide_prohibit`.guide_id
     *
     * @param guideId the value for `guide_prohibit`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *关联订单编号
     *`guide_prohibit`.order_no
     *
     * @return the value of `guide_prohibit`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *关联订单编号
     *`guide_prohibit`.order_no
     *
     * @param orderNo the value for `guide_prohibit`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *禁用类型。1-订单推送；2-接单；3-提现；4-登录
     *`guide_prohibit`.type
     *
     * @return the value of `guide_prohibit`.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     *禁用类型。1-订单推送；2-接单；3-提现；4-登录
     *`guide_prohibit`.type
     *
     * @param type the value for `guide_prohibit`.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     *禁用开始时间
     *`guide_prohibit`.start_date
     *
     * @return the value of `guide_prohibit`.start_date
     *
     * @mbggenerated
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     *禁用开始时间
     *`guide_prohibit`.start_date
     *
     * @param startDate the value for `guide_prohibit`.start_date
     *
     * @mbggenerated
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     *禁用结束时间，null为永久
     *`guide_prohibit`.end_date
     *
     * @return the value of `guide_prohibit`.end_date
     *
     * @mbggenerated
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     *禁用结束时间，null为永久
     *`guide_prohibit`.end_date
     *
     * @param endDate the value for `guide_prohibit`.end_date
     *
     * @mbggenerated
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     *设置禁用的管理员ID（只记录最后一次操作的人）
     *`guide_prohibit`.op_admin_id
     *
     * @return the value of `guide_prohibit`.op_admin_id
     *
     * @mbggenerated
     */
    public String getOpAdminId() {
        return opAdminId;
    }

    /**
     *设置禁用的管理员ID（只记录最后一次操作的人）
     *`guide_prohibit`.op_admin_id
     *
     * @param opAdminId the value for `guide_prohibit`.op_admin_id
     *
     * @mbggenerated
     */
    public void setOpAdminId(String opAdminId) {
        this.opAdminId = opAdminId == null ? null : opAdminId.trim();
    }

    /**
     *设置禁用的管理员姓名（只记录最后一次操作的人）
     *`guide_prohibit`.op_admin_name
     *
     * @return the value of `guide_prohibit`.op_admin_name
     *
     * @mbggenerated
     */
    public String getOpAdminName() {
        return opAdminName;
    }

    /**
     *设置禁用的管理员姓名（只记录最后一次操作的人）
     *`guide_prohibit`.op_admin_name
     *
     * @param opAdminName the value for `guide_prohibit`.op_admin_name
     *
     * @mbggenerated
     */
    public void setOpAdminName(String opAdminName) {
        this.opAdminName = opAdminName == null ? null : opAdminName.trim();
    }

    /**
     *处理意见
     *`guide_prohibit`.comment
     *
     * @return the value of `guide_prohibit`.comment
     *
     * @mbggenerated
     */
    public String getComment() {
        return comment;
    }

    /**
     *处理意见
     *`guide_prohibit`.comment
     *
     * @param comment the value for `guide_prohibit`.comment
     *
     * @mbggenerated
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     *状态。0-不可用；1-可用（默认）。不可用的记录不生效
     *`guide_prohibit`.status
     *
     * @return the value of `guide_prohibit`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *状态。0-不可用；1-可用（默认）。不可用的记录不生效
     *`guide_prohibit`.status
     *
     * @param status the value for `guide_prohibit`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *最近更新时间
     *`guide_prohibit`.update_time
     *
     * @return the value of `guide_prohibit`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *最近更新时间
     *`guide_prohibit`.update_time
     *
     * @param updateTime the value for `guide_prohibit`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *创建时间
     *`guide_prohibit`.create_time
     *
     * @return the value of `guide_prohibit`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     *`guide_prohibit`.create_time
     *
     * @param createTime the value for `guide_prohibit`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guideProhibitId=").append(guideProhibitId);
        sb.append(", guideId=").append(guideId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", type=").append(type);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", opAdminId=").append(opAdminId);
        sb.append(", opAdminName=").append(opAdminName);
        sb.append(", comment=").append(comment);
        sb.append(", status=").append(status);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}