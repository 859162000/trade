package com.hbc.api.trade.order.mapping.gen.bean;

import java.util.Date;

public class TradeDeliverGuide {
    /**
     *  导游订单可见关联Id
     *  所属表字段为`trade_deliver_guide`.allocat_gno
     */
    private String allocatGno;

    /**
     *  订单发单表no
     *  所属表字段为`trade_deliver_guide`.deliver_no
     */
    private String deliverNo;

    /**
     *  订单Id
     *  所属表字段为`trade_deliver_guide`.order_no
     */
    private String orderNo;

    /**
     *  导游Id
     *  所属表字段为`trade_deliver_guide`.guide_id
     */
    private String guideId;

    /**
     *  导游姓名
     *  所属表字段为`trade_deliver_guide`.guide_name
     */
    private String guideName;

    /**
     *  导游价格
     *  所属表字段为`trade_deliver_guide`.guide_price
     */
    private Double guidePrice;

    /**
     *  
     *  所属表字段为`trade_deliver_guide`.guide_no
     */
    private String guideNo;

    /**
     *  1-正常发单；2-立即发单；3-增量补发；4-取消重发 5 指派导游 6 支付前预指派导游
     *  所属表字段为`trade_deliver_guide`.deliver_type
     */
    private Integer deliverType;

    /**
     *  
     *  所属表字段为`trade_deliver_guide`.deliver_type_name
     */
    private String deliverTypeName;

    /**
     *  1:未发单 2:已发送给导游 3:接单成功4:接单成功并且消息已经发送5:pk失败 6:取消重发
     *  所属表字段为`trade_deliver_guide`.deliver_status
     */
    private Integer deliverStatus;

    /**
     *  
     *  所属表字段为`trade_deliver_guide`.deliver_status_name
     */
    private String deliverStatusName;

    /**
     *  表态是否愿意接单：0-未表态（默认）1-愿意接 2 不愿意接
     *  所属表字段为`trade_deliver_guide`.demand_deal
     */
    private Integer demandDeal;

    /**
     *  订单详情是否查看 0-初始（默认）；1-已读
     *  所属表字段为`trade_deliver_guide`.is_read
     */
    private Integer isRead;

    /**
     *  错过页中是否已经查看0-初始（默认）；1-已读
     *  所属表字段为`trade_deliver_guide`.is_read_mis
     */
    private Integer isReadMis;

    /**
     *  主要用于二次重发 0-不可见；1-可见（默认）
     *  所属表字段为`trade_deliver_guide`.is_readable
     */
    private Integer isReadable;

    /**
     *  0 普通 1 串单
     *  所属表字段为`trade_deliver_guide`.is_on_way
     */
    private Integer isOnWay;

    /**
     *  PK 失败的原因：1-禁止接单；2-行程冲突；3-评分；4-抢单速度；5-RP;6-已串单
     *  所属表字段为`trade_deliver_guide`.fail_type
     */
    private Integer failType;

    /**
     *  查看PK结果 0-未查看（默认）1-已查看
     *  所属表字段为`trade_deliver_guide`.view_result
     */
    private Integer viewResult;

    /**
     *  拒接原因，存储格式为 1,2,3,4
     *  所属表字段为`trade_deliver_guide`.refuse_reason
     */
    private String refuseReason;

    /**
     *  导游接受时间
     *  所属表字段为`trade_deliver_guide`.accept_time
     */
    private Date acceptTime;

    /**
     *  
     *  所属表字段为`trade_deliver_guide`.first_read_time
     */
    private Date firstReadTime;

    /**
     *  其他原因
     *  所属表字段为`trade_deliver_guide`.other
     */
    private String other;

    /**
     *  服务时间，服务开始时间  日租默认8点
     *  所属表字段为`trade_deliver_guide`.service_time
     */
    private Date serviceTime;

    /**
     *  更新时间
     *  所属表字段为`trade_deliver_guide`.update_time
     */
    private Date updateTime;

    /**
     *  创建时间
     *  所属表字段为`trade_deliver_guide`.create_time
     */
    private Date createTime;

    /**
     *导游订单可见关联Id
     *`trade_deliver_guide`.allocat_gno
     *
     * @return the value of `trade_deliver_guide`.allocat_gno
     *
     * @mbggenerated
     */
    public String getAllocatGno() {
        return allocatGno;
    }

    /**
     *导游订单可见关联Id
     *`trade_deliver_guide`.allocat_gno
     *
     * @param allocatGno the value for `trade_deliver_guide`.allocat_gno
     *
     * @mbggenerated
     */
    public void setAllocatGno(String allocatGno) {
        this.allocatGno = allocatGno == null ? null : allocatGno.trim();
    }

    /**
     *订单发单表no
     *`trade_deliver_guide`.deliver_no
     *
     * @return the value of `trade_deliver_guide`.deliver_no
     *
     * @mbggenerated
     */
    public String getDeliverNo() {
        return deliverNo;
    }

    /**
     *订单发单表no
     *`trade_deliver_guide`.deliver_no
     *
     * @param deliverNo the value for `trade_deliver_guide`.deliver_no
     *
     * @mbggenerated
     */
    public void setDeliverNo(String deliverNo) {
        this.deliverNo = deliverNo == null ? null : deliverNo.trim();
    }

    /**
     *订单Id
     *`trade_deliver_guide`.order_no
     *
     * @return the value of `trade_deliver_guide`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *订单Id
     *`trade_deliver_guide`.order_no
     *
     * @param orderNo the value for `trade_deliver_guide`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *导游Id
     *`trade_deliver_guide`.guide_id
     *
     * @return the value of `trade_deliver_guide`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *导游Id
     *`trade_deliver_guide`.guide_id
     *
     * @param guideId the value for `trade_deliver_guide`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *导游姓名
     *`trade_deliver_guide`.guide_name
     *
     * @return the value of `trade_deliver_guide`.guide_name
     *
     * @mbggenerated
     */
    public String getGuideName() {
        return guideName;
    }

    /**
     *导游姓名
     *`trade_deliver_guide`.guide_name
     *
     * @param guideName the value for `trade_deliver_guide`.guide_name
     *
     * @mbggenerated
     */
    public void setGuideName(String guideName) {
        this.guideName = guideName == null ? null : guideName.trim();
    }

    /**
     *导游价格
     *`trade_deliver_guide`.guide_price
     *
     * @return the value of `trade_deliver_guide`.guide_price
     *
     * @mbggenerated
     */
    public Double getGuidePrice() {
        return guidePrice;
    }

    /**
     *导游价格
     *`trade_deliver_guide`.guide_price
     *
     * @param guidePrice the value for `trade_deliver_guide`.guide_price
     *
     * @mbggenerated
     */
    public void setGuidePrice(Double guidePrice) {
        this.guidePrice = guidePrice;
    }

    /**
     *
     *`trade_deliver_guide`.guide_no
     *
     * @return the value of `trade_deliver_guide`.guide_no
     *
     * @mbggenerated
     */
    public String getGuideNo() {
        return guideNo;
    }

    /**
     *
     *`trade_deliver_guide`.guide_no
     *
     * @param guideNo the value for `trade_deliver_guide`.guide_no
     *
     * @mbggenerated
     */
    public void setGuideNo(String guideNo) {
        this.guideNo = guideNo == null ? null : guideNo.trim();
    }

    /**
     *1-正常发单；2-立即发单；3-增量补发；4-取消重发 5 指派导游 6 支付前预指派导游
     *`trade_deliver_guide`.deliver_type
     *
     * @return the value of `trade_deliver_guide`.deliver_type
     *
     * @mbggenerated
     */
    public Integer getDeliverType() {
        return deliverType;
    }

    /**
     *1-正常发单；2-立即发单；3-增量补发；4-取消重发 5 指派导游 6 支付前预指派导游
     *`trade_deliver_guide`.deliver_type
     *
     * @param deliverType the value for `trade_deliver_guide`.deliver_type
     *
     * @mbggenerated
     */
    public void setDeliverType(Integer deliverType) {
        this.deliverType = deliverType;
    }

    /**
     *
     *`trade_deliver_guide`.deliver_type_name
     *
     * @return the value of `trade_deliver_guide`.deliver_type_name
     *
     * @mbggenerated
     */
    public String getDeliverTypeName() {
        return deliverTypeName;
    }

    /**
     *
     *`trade_deliver_guide`.deliver_type_name
     *
     * @param deliverTypeName the value for `trade_deliver_guide`.deliver_type_name
     *
     * @mbggenerated
     */
    public void setDeliverTypeName(String deliverTypeName) {
        this.deliverTypeName = deliverTypeName == null ? null : deliverTypeName.trim();
    }

    /**
     *1:未发单 2:已发送给导游 3:接单成功4:接单成功并且消息已经发送5:pk失败 6:取消重发
     *`trade_deliver_guide`.deliver_status
     *
     * @return the value of `trade_deliver_guide`.deliver_status
     *
     * @mbggenerated
     */
    public Integer getDeliverStatus() {
        return deliverStatus;
    }

    /**
     *1:未发单 2:已发送给导游 3:接单成功4:接单成功并且消息已经发送5:pk失败 6:取消重发
     *`trade_deliver_guide`.deliver_status
     *
     * @param deliverStatus the value for `trade_deliver_guide`.deliver_status
     *
     * @mbggenerated
     */
    public void setDeliverStatus(Integer deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    /**
     *
     *`trade_deliver_guide`.deliver_status_name
     *
     * @return the value of `trade_deliver_guide`.deliver_status_name
     *
     * @mbggenerated
     */
    public String getDeliverStatusName() {
        return deliverStatusName;
    }

    /**
     *
     *`trade_deliver_guide`.deliver_status_name
     *
     * @param deliverStatusName the value for `trade_deliver_guide`.deliver_status_name
     *
     * @mbggenerated
     */
    public void setDeliverStatusName(String deliverStatusName) {
        this.deliverStatusName = deliverStatusName == null ? null : deliverStatusName.trim();
    }

    /**
     *表态是否愿意接单：0-未表态（默认）1-愿意接 2 不愿意接
     *`trade_deliver_guide`.demand_deal
     *
     * @return the value of `trade_deliver_guide`.demand_deal
     *
     * @mbggenerated
     */
    public Integer getDemandDeal() {
        return demandDeal;
    }

    /**
     *表态是否愿意接单：0-未表态（默认）1-愿意接 2 不愿意接
     *`trade_deliver_guide`.demand_deal
     *
     * @param demandDeal the value for `trade_deliver_guide`.demand_deal
     *
     * @mbggenerated
     */
    public void setDemandDeal(Integer demandDeal) {
        this.demandDeal = demandDeal;
    }

    /**
     *订单详情是否查看 0-初始（默认）；1-已读
     *`trade_deliver_guide`.is_read
     *
     * @return the value of `trade_deliver_guide`.is_read
     *
     * @mbggenerated
     */
    public Integer getIsRead() {
        return isRead;
    }

    /**
     *订单详情是否查看 0-初始（默认）；1-已读
     *`trade_deliver_guide`.is_read
     *
     * @param isRead the value for `trade_deliver_guide`.is_read
     *
     * @mbggenerated
     */
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    /**
     *错过页中是否已经查看0-初始（默认）；1-已读
     *`trade_deliver_guide`.is_read_mis
     *
     * @return the value of `trade_deliver_guide`.is_read_mis
     *
     * @mbggenerated
     */
    public Integer getIsReadMis() {
        return isReadMis;
    }

    /**
     *错过页中是否已经查看0-初始（默认）；1-已读
     *`trade_deliver_guide`.is_read_mis
     *
     * @param isReadMis the value for `trade_deliver_guide`.is_read_mis
     *
     * @mbggenerated
     */
    public void setIsReadMis(Integer isReadMis) {
        this.isReadMis = isReadMis;
    }

    /**
     *主要用于二次重发 0-不可见；1-可见（默认）
     *`trade_deliver_guide`.is_readable
     *
     * @return the value of `trade_deliver_guide`.is_readable
     *
     * @mbggenerated
     */
    public Integer getIsReadable() {
        return isReadable;
    }

    /**
     *主要用于二次重发 0-不可见；1-可见（默认）
     *`trade_deliver_guide`.is_readable
     *
     * @param isReadable the value for `trade_deliver_guide`.is_readable
     *
     * @mbggenerated
     */
    public void setIsReadable(Integer isReadable) {
        this.isReadable = isReadable;
    }

    /**
     *0 普通 1 串单
     *`trade_deliver_guide`.is_on_way
     *
     * @return the value of `trade_deliver_guide`.is_on_way
     *
     * @mbggenerated
     */
    public Integer getIsOnWay() {
        return isOnWay;
    }

    /**
     *0 普通 1 串单
     *`trade_deliver_guide`.is_on_way
     *
     * @param isOnWay the value for `trade_deliver_guide`.is_on_way
     *
     * @mbggenerated
     */
    public void setIsOnWay(Integer isOnWay) {
        this.isOnWay = isOnWay;
    }

    /**
     *PK 失败的原因：1-禁止接单；2-行程冲突；3-评分；4-抢单速度；5-RP;6-已串单
     *`trade_deliver_guide`.fail_type
     *
     * @return the value of `trade_deliver_guide`.fail_type
     *
     * @mbggenerated
     */
    public Integer getFailType() {
        return failType;
    }

    /**
     *PK 失败的原因：1-禁止接单；2-行程冲突；3-评分；4-抢单速度；5-RP;6-已串单
     *`trade_deliver_guide`.fail_type
     *
     * @param failType the value for `trade_deliver_guide`.fail_type
     *
     * @mbggenerated
     */
    public void setFailType(Integer failType) {
        this.failType = failType;
    }

    /**
     *查看PK结果 0-未查看（默认）1-已查看
     *`trade_deliver_guide`.view_result
     *
     * @return the value of `trade_deliver_guide`.view_result
     *
     * @mbggenerated
     */
    public Integer getViewResult() {
        return viewResult;
    }

    /**
     *查看PK结果 0-未查看（默认）1-已查看
     *`trade_deliver_guide`.view_result
     *
     * @param viewResult the value for `trade_deliver_guide`.view_result
     *
     * @mbggenerated
     */
    public void setViewResult(Integer viewResult) {
        this.viewResult = viewResult;
    }

    /**
     *拒接原因，存储格式为 1,2,3,4
     *`trade_deliver_guide`.refuse_reason
     *
     * @return the value of `trade_deliver_guide`.refuse_reason
     *
     * @mbggenerated
     */
    public String getRefuseReason() {
        return refuseReason;
    }

    /**
     *拒接原因，存储格式为 1,2,3,4
     *`trade_deliver_guide`.refuse_reason
     *
     * @param refuseReason the value for `trade_deliver_guide`.refuse_reason
     *
     * @mbggenerated
     */
    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason == null ? null : refuseReason.trim();
    }

    /**
     *导游接受时间
     *`trade_deliver_guide`.accept_time
     *
     * @return the value of `trade_deliver_guide`.accept_time
     *
     * @mbggenerated
     */
    public Date getAcceptTime() {
        return acceptTime;
    }

    /**
     *导游接受时间
     *`trade_deliver_guide`.accept_time
     *
     * @param acceptTime the value for `trade_deliver_guide`.accept_time
     *
     * @mbggenerated
     */
    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    /**
     *
     *`trade_deliver_guide`.first_read_time
     *
     * @return the value of `trade_deliver_guide`.first_read_time
     *
     * @mbggenerated
     */
    public Date getFirstReadTime() {
        return firstReadTime;
    }

    /**
     *
     *`trade_deliver_guide`.first_read_time
     *
     * @param firstReadTime the value for `trade_deliver_guide`.first_read_time
     *
     * @mbggenerated
     */
    public void setFirstReadTime(Date firstReadTime) {
        this.firstReadTime = firstReadTime;
    }

    /**
     *其他原因
     *`trade_deliver_guide`.other
     *
     * @return the value of `trade_deliver_guide`.other
     *
     * @mbggenerated
     */
    public String getOther() {
        return other;
    }

    /**
     *其他原因
     *`trade_deliver_guide`.other
     *
     * @param other the value for `trade_deliver_guide`.other
     *
     * @mbggenerated
     */
    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    /**
     *服务时间，服务开始时间  日租默认8点
     *`trade_deliver_guide`.service_time
     *
     * @return the value of `trade_deliver_guide`.service_time
     *
     * @mbggenerated
     */
    public Date getServiceTime() {
        return serviceTime;
    }

    /**
     *服务时间，服务开始时间  日租默认8点
     *`trade_deliver_guide`.service_time
     *
     * @param serviceTime the value for `trade_deliver_guide`.service_time
     *
     * @mbggenerated
     */
    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     *更新时间
     *`trade_deliver_guide`.update_time
     *
     * @return the value of `trade_deliver_guide`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新时间
     *`trade_deliver_guide`.update_time
     *
     * @param updateTime the value for `trade_deliver_guide`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *创建时间
     *`trade_deliver_guide`.create_time
     *
     * @return the value of `trade_deliver_guide`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     *`trade_deliver_guide`.create_time
     *
     * @param createTime the value for `trade_deliver_guide`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_deliver_guide`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", allocatGno=").append(allocatGno);
        sb.append(", deliverNo=").append(deliverNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", guideId=").append(guideId);
        sb.append(", guideName=").append(guideName);
        sb.append(", guidePrice=").append(guidePrice);
        sb.append(", guideNo=").append(guideNo);
        sb.append(", deliverType=").append(deliverType);
        sb.append(", deliverTypeName=").append(deliverTypeName);
        sb.append(", deliverStatus=").append(deliverStatus);
        sb.append(", deliverStatusName=").append(deliverStatusName);
        sb.append(", demandDeal=").append(demandDeal);
        sb.append(", isRead=").append(isRead);
        sb.append(", isReadMis=").append(isReadMis);
        sb.append(", isReadable=").append(isReadable);
        sb.append(", isOnWay=").append(isOnWay);
        sb.append(", failType=").append(failType);
        sb.append(", viewResult=").append(viewResult);
        sb.append(", refuseReason=").append(refuseReason);
        sb.append(", acceptTime=").append(acceptTime);
        sb.append(", firstReadTime=").append(firstReadTime);
        sb.append(", other=").append(other);
        sb.append(", serviceTime=").append(serviceTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}