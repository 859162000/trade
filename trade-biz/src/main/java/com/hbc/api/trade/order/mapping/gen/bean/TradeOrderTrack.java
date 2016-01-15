package com.hbc.api.trade.order.mapping.gen.bean;

import java.util.Date;

public class TradeOrderTrack {
    /**
     *  
     *  所属表字段为`trade_order_track`.track_id
     */
    private String trackId;

    /**
     *  
     *  所属表字段为`trade_order_track`.order_no
     */
    private String orderNo;

    /**
     *  行程动态文字内容
     *  所属表字段为`trade_order_track`.track_desc
     */
    private String trackDesc;

    /**
     *  例如：我已到达，需获取gps坐标
     *  所属表字段为`trade_order_track`.track_content
     */
    private String trackContent;

    /**
     *  行程动态类型。1-接下订单；2-航班延误；3-航班起飞；4-航班落地；5-已到达接车地点；6-已接到客人；7-已完成服务；8-导游评价客人；9-客人评价导游；99-订单被取消；-1-客人取消订单；98-订单被修改；10-服务费用结算完成，11-改派订单，12-订单奖金,13-航班取消，14-航班备降，15-航班返航,16-您确认了服务费用，并提交了增项费用申请 17 -服务中
     *  所属表字段为`trade_order_track`.track_type
     */
    private Integer trackType;

    /**
     *  状态：1- 正常（默认）；2：删除
     *  所属表字段为`trade_order_track`.status
     */
    private Integer status;

    /**
     *  是否已读：0-未读（默认）；1-已读
     *  所属表字段为`trade_order_track`.is_read
     */
    private Integer isRead;

    /**
     *  
     *  所属表字段为`trade_order_track`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`trade_order_track`.create_time
     */
    private Date createTime;

    /**
     *
     *`trade_order_track`.track_id
     *
     * @return the value of `trade_order_track`.track_id
     *
     * @mbggenerated
     */
    public String getTrackId() {
        return trackId;
    }

    /**
     *
     *`trade_order_track`.track_id
     *
     * @param trackId the value for `trade_order_track`.track_id
     *
     * @mbggenerated
     */
    public void setTrackId(String trackId) {
        this.trackId = trackId == null ? null : trackId.trim();
    }

    /**
     *
     *`trade_order_track`.order_no
     *
     * @return the value of `trade_order_track`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *
     *`trade_order_track`.order_no
     *
     * @param orderNo the value for `trade_order_track`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *行程动态文字内容
     *`trade_order_track`.track_desc
     *
     * @return the value of `trade_order_track`.track_desc
     *
     * @mbggenerated
     */
    public String getTrackDesc() {
        return trackDesc;
    }

    /**
     *行程动态文字内容
     *`trade_order_track`.track_desc
     *
     * @param trackDesc the value for `trade_order_track`.track_desc
     *
     * @mbggenerated
     */
    public void setTrackDesc(String trackDesc) {
        this.trackDesc = trackDesc == null ? null : trackDesc.trim();
    }

    /**
     *例如：我已到达，需获取gps坐标
     *`trade_order_track`.track_content
     *
     * @return the value of `trade_order_track`.track_content
     *
     * @mbggenerated
     */
    public String getTrackContent() {
        return trackContent;
    }

    /**
     *例如：我已到达，需获取gps坐标
     *`trade_order_track`.track_content
     *
     * @param trackContent the value for `trade_order_track`.track_content
     *
     * @mbggenerated
     */
    public void setTrackContent(String trackContent) {
        this.trackContent = trackContent == null ? null : trackContent.trim();
    }

    /**
     *行程动态类型。1-接下订单；2-航班延误；3-航班起飞；4-航班落地；5-已到达接车地点；6-已接到客人；7-已完成服务；8-导游评价客人；9-客人评价导游；99-订单被取消；-1-客人取消订单；98-订单被修改；10-服务费用结算完成，11-改派订单，12-订单奖金,13-航班取消，14-航班备降，15-航班返航,16-您确认了服务费用，并提交了增项费用申请 17 -服务中
     *`trade_order_track`.track_type
     *
     * @return the value of `trade_order_track`.track_type
     *
     * @mbggenerated
     */
    public Integer getTrackType() {
        return trackType;
    }

    /**
     *行程动态类型。1-接下订单；2-航班延误；3-航班起飞；4-航班落地；5-已到达接车地点；6-已接到客人；7-已完成服务；8-导游评价客人；9-客人评价导游；99-订单被取消；-1-客人取消订单；98-订单被修改；10-服务费用结算完成，11-改派订单，12-订单奖金,13-航班取消，14-航班备降，15-航班返航,16-您确认了服务费用，并提交了增项费用申请 17 -服务中
     *`trade_order_track`.track_type
     *
     * @param trackType the value for `trade_order_track`.track_type
     *
     * @mbggenerated
     */
    public void setTrackType(Integer trackType) {
        this.trackType = trackType;
    }

    /**
     *状态：1- 正常（默认）；2：删除
     *`trade_order_track`.status
     *
     * @return the value of `trade_order_track`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *状态：1- 正常（默认）；2：删除
     *`trade_order_track`.status
     *
     * @param status the value for `trade_order_track`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *是否已读：0-未读（默认）；1-已读
     *`trade_order_track`.is_read
     *
     * @return the value of `trade_order_track`.is_read
     *
     * @mbggenerated
     */
    public Integer getIsRead() {
        return isRead;
    }

    /**
     *是否已读：0-未读（默认）；1-已读
     *`trade_order_track`.is_read
     *
     * @param isRead the value for `trade_order_track`.is_read
     *
     * @mbggenerated
     */
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    /**
     *
     *`trade_order_track`.update_time
     *
     * @return the value of `trade_order_track`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`trade_order_track`.update_time
     *
     * @param updateTime the value for `trade_order_track`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`trade_order_track`.create_time
     *
     * @return the value of `trade_order_track`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`trade_order_track`.create_time
     *
     * @param createTime the value for `trade_order_track`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_order_track`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", trackId=").append(trackId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", trackDesc=").append(trackDesc);
        sb.append(", trackContent=").append(trackContent);
        sb.append(", trackType=").append(trackType);
        sb.append(", status=").append(status);
        sb.append(", isRead=").append(isRead);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}