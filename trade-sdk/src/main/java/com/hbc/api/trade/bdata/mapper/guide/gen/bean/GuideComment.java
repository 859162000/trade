package com.hbc.api.trade.bdata.mapper.guide.gen.bean;

import java.math.BigDecimal;
import java.util.Date;

public class GuideComment {
    /**
     *  
     *  所属表字段为`guide_comment`.guide_comment_id
     */
    private Integer guideCommentId;

    /**
     *  
     *  所属表字段为`guide_comment`.order_no
     */
    private String orderNo;

    /**
     *  
     *  所属表字段为`guide_comment`.order_type
     */
    private Integer orderType;

    /**
     *  
     *  所属表字段为`guide_comment`.scenery_narrate
     */
    private BigDecimal sceneryNarrate;

    /**
     *  
     *  所属表字段为`guide_comment`.service_attitude
     */
    private BigDecimal serviceAttitude;

    /**
     *  
     *  所属表字段为`guide_comment`.route_familiar
     */
    private BigDecimal routeFamiliar;

    /**
     *  评价人ID
     *  所属表字段为`guide_comment`.from_uid
     */
    private String fromUid;

    /**
     *  评价人NAME
     *  所属表字段为`guide_comment`.from_uname
     */
    private String fromUname;

    /**
     *  被评价人ID
     *  所属表字段为`guide_comment`.guide_id
     */
    private String guideId;

    /**
     *  被评价人NAME
     *  所属表字段为`guide_comment`.guide_name
     */
    private String guideName;

    /**
     *  类型。1.-客人评价导游；2-运营评价导游
     *  所属表字段为`guide_comment`.comment_type
     */
    private Integer commentType;

    /**
     *  评分小项：0-未选择（默认）；1-选择
     *  所属表字段为`guide_comment`.kpi1
     */
    private Integer kpi1;

    /**
     *  评分小项：0-未选择（默认）；1-选择
     *  所属表字段为`guide_comment`.kpi2
     */
    private Integer kpi2;

    /**
     *  评分小项：0-未选择（默认）；1-选择
     *  所属表字段为`guide_comment`.kpi3
     */
    private Integer kpi3;

    /**
     *  评分小项：0-未选择（默认）；1-选择
     *  所属表字段为`guide_comment`.kpi4
     */
    private Integer kpi4;

    /**
     *  评分小项：0-未选择（默认）；1-选择
     *  所属表字段为`guide_comment`.kpi5
     */
    private Integer kpi5;

    /**
     *  评分小项：0-未选择（默认）；1-选择
     *  所属表字段为`guide_comment`.kpi6
     */
    private Integer kpi6;

    /**
     *  最终评价得分
     *  所属表字段为`guide_comment`.total_score
     */
    private BigDecimal totalScore;

    /**
     *  
     *  所属表字段为`guide_comment`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`guide_comment`.create_time
     */
    private Date createTime;

    /**
     *  
     *  所属表字段为`guide_comment`.content
     */
    private String content;

    /**
     *
     *`guide_comment`.guide_comment_id
     *
     * @return the value of `guide_comment`.guide_comment_id
     *
     * @mbggenerated
     */
    public Integer getGuideCommentId() {
        return guideCommentId;
    }

    /**
     *
     *`guide_comment`.guide_comment_id
     *
     * @param guideCommentId the value for `guide_comment`.guide_comment_id
     *
     * @mbggenerated
     */
    public void setGuideCommentId(Integer guideCommentId) {
        this.guideCommentId = guideCommentId;
    }

    /**
     *
     *`guide_comment`.order_no
     *
     * @return the value of `guide_comment`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *
     *`guide_comment`.order_no
     *
     * @param orderNo the value for `guide_comment`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *
     *`guide_comment`.order_type
     *
     * @return the value of `guide_comment`.order_type
     *
     * @mbggenerated
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     *
     *`guide_comment`.order_type
     *
     * @param orderType the value for `guide_comment`.order_type
     *
     * @mbggenerated
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     *
     *`guide_comment`.scenery_narrate
     *
     * @return the value of `guide_comment`.scenery_narrate
     *
     * @mbggenerated
     */
    public BigDecimal getSceneryNarrate() {
        return sceneryNarrate;
    }

    /**
     *
     *`guide_comment`.scenery_narrate
     *
     * @param sceneryNarrate the value for `guide_comment`.scenery_narrate
     *
     * @mbggenerated
     */
    public void setSceneryNarrate(BigDecimal sceneryNarrate) {
        this.sceneryNarrate = sceneryNarrate;
    }

    /**
     *
     *`guide_comment`.service_attitude
     *
     * @return the value of `guide_comment`.service_attitude
     *
     * @mbggenerated
     */
    public BigDecimal getServiceAttitude() {
        return serviceAttitude;
    }

    /**
     *
     *`guide_comment`.service_attitude
     *
     * @param serviceAttitude the value for `guide_comment`.service_attitude
     *
     * @mbggenerated
     */
    public void setServiceAttitude(BigDecimal serviceAttitude) {
        this.serviceAttitude = serviceAttitude;
    }

    /**
     *
     *`guide_comment`.route_familiar
     *
     * @return the value of `guide_comment`.route_familiar
     *
     * @mbggenerated
     */
    public BigDecimal getRouteFamiliar() {
        return routeFamiliar;
    }

    /**
     *
     *`guide_comment`.route_familiar
     *
     * @param routeFamiliar the value for `guide_comment`.route_familiar
     *
     * @mbggenerated
     */
    public void setRouteFamiliar(BigDecimal routeFamiliar) {
        this.routeFamiliar = routeFamiliar;
    }

    /**
     *评价人ID
     *`guide_comment`.from_uid
     *
     * @return the value of `guide_comment`.from_uid
     *
     * @mbggenerated
     */
    public String getFromUid() {
        return fromUid;
    }

    /**
     *评价人ID
     *`guide_comment`.from_uid
     *
     * @param fromUid the value for `guide_comment`.from_uid
     *
     * @mbggenerated
     */
    public void setFromUid(String fromUid) {
        this.fromUid = fromUid == null ? null : fromUid.trim();
    }

    /**
     *评价人NAME
     *`guide_comment`.from_uname
     *
     * @return the value of `guide_comment`.from_uname
     *
     * @mbggenerated
     */
    public String getFromUname() {
        return fromUname;
    }

    /**
     *评价人NAME
     *`guide_comment`.from_uname
     *
     * @param fromUname the value for `guide_comment`.from_uname
     *
     * @mbggenerated
     */
    public void setFromUname(String fromUname) {
        this.fromUname = fromUname == null ? null : fromUname.trim();
    }

    /**
     *被评价人ID
     *`guide_comment`.guide_id
     *
     * @return the value of `guide_comment`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *被评价人ID
     *`guide_comment`.guide_id
     *
     * @param guideId the value for `guide_comment`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *被评价人NAME
     *`guide_comment`.guide_name
     *
     * @return the value of `guide_comment`.guide_name
     *
     * @mbggenerated
     */
    public String getGuideName() {
        return guideName;
    }

    /**
     *被评价人NAME
     *`guide_comment`.guide_name
     *
     * @param guideName the value for `guide_comment`.guide_name
     *
     * @mbggenerated
     */
    public void setGuideName(String guideName) {
        this.guideName = guideName == null ? null : guideName.trim();
    }

    /**
     *类型。1.-客人评价导游；2-运营评价导游
     *`guide_comment`.comment_type
     *
     * @return the value of `guide_comment`.comment_type
     *
     * @mbggenerated
     */
    public Integer getCommentType() {
        return commentType;
    }

    /**
     *类型。1.-客人评价导游；2-运营评价导游
     *`guide_comment`.comment_type
     *
     * @param commentType the value for `guide_comment`.comment_type
     *
     * @mbggenerated
     */
    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    /**
     *评分小项：0-未选择（默认）；1-选择
     *`guide_comment`.kpi1
     *
     * @return the value of `guide_comment`.kpi1
     *
     * @mbggenerated
     */
    public Integer getKpi1() {
        return kpi1;
    }

    /**
     *评分小项：0-未选择（默认）；1-选择
     *`guide_comment`.kpi1
     *
     * @param kpi1 the value for `guide_comment`.kpi1
     *
     * @mbggenerated
     */
    public void setKpi1(Integer kpi1) {
        this.kpi1 = kpi1;
    }

    /**
     *评分小项：0-未选择（默认）；1-选择
     *`guide_comment`.kpi2
     *
     * @return the value of `guide_comment`.kpi2
     *
     * @mbggenerated
     */
    public Integer getKpi2() {
        return kpi2;
    }

    /**
     *评分小项：0-未选择（默认）；1-选择
     *`guide_comment`.kpi2
     *
     * @param kpi2 the value for `guide_comment`.kpi2
     *
     * @mbggenerated
     */
    public void setKpi2(Integer kpi2) {
        this.kpi2 = kpi2;
    }

    /**
     *评分小项：0-未选择（默认）；1-选择
     *`guide_comment`.kpi3
     *
     * @return the value of `guide_comment`.kpi3
     *
     * @mbggenerated
     */
    public Integer getKpi3() {
        return kpi3;
    }

    /**
     *评分小项：0-未选择（默认）；1-选择
     *`guide_comment`.kpi3
     *
     * @param kpi3 the value for `guide_comment`.kpi3
     *
     * @mbggenerated
     */
    public void setKpi3(Integer kpi3) {
        this.kpi3 = kpi3;
    }

    /**
     *评分小项：0-未选择（默认）；1-选择
     *`guide_comment`.kpi4
     *
     * @return the value of `guide_comment`.kpi4
     *
     * @mbggenerated
     */
    public Integer getKpi4() {
        return kpi4;
    }

    /**
     *评分小项：0-未选择（默认）；1-选择
     *`guide_comment`.kpi4
     *
     * @param kpi4 the value for `guide_comment`.kpi4
     *
     * @mbggenerated
     */
    public void setKpi4(Integer kpi4) {
        this.kpi4 = kpi4;
    }

    /**
     *评分小项：0-未选择（默认）；1-选择
     *`guide_comment`.kpi5
     *
     * @return the value of `guide_comment`.kpi5
     *
     * @mbggenerated
     */
    public Integer getKpi5() {
        return kpi5;
    }

    /**
     *评分小项：0-未选择（默认）；1-选择
     *`guide_comment`.kpi5
     *
     * @param kpi5 the value for `guide_comment`.kpi5
     *
     * @mbggenerated
     */
    public void setKpi5(Integer kpi5) {
        this.kpi5 = kpi5;
    }

    /**
     *评分小项：0-未选择（默认）；1-选择
     *`guide_comment`.kpi6
     *
     * @return the value of `guide_comment`.kpi6
     *
     * @mbggenerated
     */
    public Integer getKpi6() {
        return kpi6;
    }

    /**
     *评分小项：0-未选择（默认）；1-选择
     *`guide_comment`.kpi6
     *
     * @param kpi6 the value for `guide_comment`.kpi6
     *
     * @mbggenerated
     */
    public void setKpi6(Integer kpi6) {
        this.kpi6 = kpi6;
    }

    /**
     *最终评价得分
     *`guide_comment`.total_score
     *
     * @return the value of `guide_comment`.total_score
     *
     * @mbggenerated
     */
    public BigDecimal getTotalScore() {
        return totalScore;
    }

    /**
     *最终评价得分
     *`guide_comment`.total_score
     *
     * @param totalScore the value for `guide_comment`.total_score
     *
     * @mbggenerated
     */
    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    /**
     *
     *`guide_comment`.update_time
     *
     * @return the value of `guide_comment`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`guide_comment`.update_time
     *
     * @param updateTime the value for `guide_comment`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`guide_comment`.create_time
     *
     * @return the value of `guide_comment`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`guide_comment`.create_time
     *
     * @param createTime the value for `guide_comment`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     *`guide_comment`.content
     *
     * @return the value of `guide_comment`.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     *
     *`guide_comment`.content
     *
     * @param content the value for `guide_comment`.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_comment`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guideCommentId=").append(guideCommentId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", orderType=").append(orderType);
        sb.append(", sceneryNarrate=").append(sceneryNarrate);
        sb.append(", serviceAttitude=").append(serviceAttitude);
        sb.append(", routeFamiliar=").append(routeFamiliar);
        sb.append(", fromUid=").append(fromUid);
        sb.append(", fromUname=").append(fromUname);
        sb.append(", guideId=").append(guideId);
        sb.append(", guideName=").append(guideName);
        sb.append(", commentType=").append(commentType);
        sb.append(", kpi1=").append(kpi1);
        sb.append(", kpi2=").append(kpi2);
        sb.append(", kpi3=").append(kpi3);
        sb.append(", kpi4=").append(kpi4);
        sb.append(", kpi5=").append(kpi5);
        sb.append(", kpi6=").append(kpi6);
        sb.append(", totalScore=").append(totalScore);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}