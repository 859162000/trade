package com.hbc.api.trade.bdata.mapper.guide.gen.bean;

import java.util.Date;

public class GuideExt {
    /**
     *  主键
     *  所属表字段为`guide_ext`.guide_ext_id
     */
    private Integer guideExtId;

    /**
     *  导游id
     *  所属表字段为`guide_ext`.guide_id
     */
    private String guideId;

    /**
     *  工龄
     *  所属表字段为`guide_ext`.seniority
     */
    private Integer seniority;

    /**
     *  积分
     *  所属表字段为`guide_ext`.integral
     */
    private String integral;

    /**
     *  服务星级
     *  所属表字段为`guide_ext`.service_star
     */
    private Float serviceStar;

    /**
     *  被收藏数
     *  所属表字段为`guide_ext`.cuser_collection_num
     */
    private Integer cuserCollectionNum;

    /**
     *  活跃天数
     *  所属表字段为`guide_ext`.active_day_num
     */
    private Integer activeDayNum;

    /**
     *  成功接单数
     *  所属表字段为`guide_ext`.success_receive_num
     */
    private Integer successReceiveNum;

    /**
     *  完成订单数
     *  所属表字段为`guide_ext`.complete_num
     */
    private Integer completeNum;

    /**
     *  改派订单数
     *  所属表字段为`guide_ext`.resassign_num
     */
    private Integer resassignNum;

    /**
     *  接受指派数
     *  所属表字段为`guide_ext`.assign_num
     */
    private Integer assignNum;

    /**
     *  累计表态数
     *  所属表字段为`guide_ext`.declare_num
     */
    private Integer declareNum;

    /**
     *  累计愿意接单数
     *  所属表字段为`guide_ext`.show_purpose_num
     */
    private Integer showPurposeNum;

    /**
     *  最后一次接单时间
     *  所属表字段为`guide_ext`.lastest_receive_time
     */
    private Date lastestReceiveTime;

    /**
     *  可服务项目id。用逗号分隔
     *  所属表字段为`guide_ext`.service_project_id
     */
    private String serviceProjectId;

    /**
     *  可服务项目。用逗号分隔
     *  所属表字段为`guide_ext`.service_projects
     */
    private String serviceProjects;

    /**
     *  可服务语言id。语言id用逗号分隔
     *  所属表字段为`guide_ext`.service_language_id
     */
    private String serviceLanguageId;

    /**
     *  可服务语言。语言名称用逗号分隔
     *  所属表字段为`guide_ext`.service_languages
     */
    private String serviceLanguages;

    /**
     *  日租可服务城市名称
     *  所属表字段为`guide_ext`.order_daily_citys
     */
    private String orderDailyCitys;

    /**
     *  创建时间
     *  所属表字段为`guide_ext`.create_time
     */
    private Date createTime;

    /**
     *  最近更新时间
     *  所属表字段为`guide_ext`.update_time
     */
    private Date updateTime;

    /**
     *主键
     *`guide_ext`.guide_ext_id
     *
     * @return the value of `guide_ext`.guide_ext_id
     *
     * @mbggenerated
     */
    public Integer getGuideExtId() {
        return guideExtId;
    }

    /**
     *主键
     *`guide_ext`.guide_ext_id
     *
     * @param guideExtId the value for `guide_ext`.guide_ext_id
     *
     * @mbggenerated
     */
    public void setGuideExtId(Integer guideExtId) {
        this.guideExtId = guideExtId;
    }

    /**
     *导游id
     *`guide_ext`.guide_id
     *
     * @return the value of `guide_ext`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *导游id
     *`guide_ext`.guide_id
     *
     * @param guideId the value for `guide_ext`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *工龄
     *`guide_ext`.seniority
     *
     * @return the value of `guide_ext`.seniority
     *
     * @mbggenerated
     */
    public Integer getSeniority() {
        return seniority;
    }

    /**
     *工龄
     *`guide_ext`.seniority
     *
     * @param seniority the value for `guide_ext`.seniority
     *
     * @mbggenerated
     */
    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }

    /**
     *积分
     *`guide_ext`.integral
     *
     * @return the value of `guide_ext`.integral
     *
     * @mbggenerated
     */
    public String getIntegral() {
        return integral;
    }

    /**
     *积分
     *`guide_ext`.integral
     *
     * @param integral the value for `guide_ext`.integral
     *
     * @mbggenerated
     */
    public void setIntegral(String integral) {
        this.integral = integral == null ? null : integral.trim();
    }

    /**
     *服务星级
     *`guide_ext`.service_star
     *
     * @return the value of `guide_ext`.service_star
     *
     * @mbggenerated
     */
    public Float getServiceStar() {
        return serviceStar;
    }

    /**
     *服务星级
     *`guide_ext`.service_star
     *
     * @param serviceStar the value for `guide_ext`.service_star
     *
     * @mbggenerated
     */
    public void setServiceStar(Float serviceStar) {
        this.serviceStar = serviceStar;
    }

    /**
     *被收藏数
     *`guide_ext`.cuser_collection_num
     *
     * @return the value of `guide_ext`.cuser_collection_num
     *
     * @mbggenerated
     */
    public Integer getCuserCollectionNum() {
        return cuserCollectionNum;
    }

    /**
     *被收藏数
     *`guide_ext`.cuser_collection_num
     *
     * @param cuserCollectionNum the value for `guide_ext`.cuser_collection_num
     *
     * @mbggenerated
     */
    public void setCuserCollectionNum(Integer cuserCollectionNum) {
        this.cuserCollectionNum = cuserCollectionNum;
    }

    /**
     *活跃天数
     *`guide_ext`.active_day_num
     *
     * @return the value of `guide_ext`.active_day_num
     *
     * @mbggenerated
     */
    public Integer getActiveDayNum() {
        return activeDayNum;
    }

    /**
     *活跃天数
     *`guide_ext`.active_day_num
     *
     * @param activeDayNum the value for `guide_ext`.active_day_num
     *
     * @mbggenerated
     */
    public void setActiveDayNum(Integer activeDayNum) {
        this.activeDayNum = activeDayNum;
    }

    /**
     *成功接单数
     *`guide_ext`.success_receive_num
     *
     * @return the value of `guide_ext`.success_receive_num
     *
     * @mbggenerated
     */
    public Integer getSuccessReceiveNum() {
        return successReceiveNum;
    }

    /**
     *成功接单数
     *`guide_ext`.success_receive_num
     *
     * @param successReceiveNum the value for `guide_ext`.success_receive_num
     *
     * @mbggenerated
     */
    public void setSuccessReceiveNum(Integer successReceiveNum) {
        this.successReceiveNum = successReceiveNum;
    }

    /**
     *完成订单数
     *`guide_ext`.complete_num
     *
     * @return the value of `guide_ext`.complete_num
     *
     * @mbggenerated
     */
    public Integer getCompleteNum() {
        return completeNum;
    }

    /**
     *完成订单数
     *`guide_ext`.complete_num
     *
     * @param completeNum the value for `guide_ext`.complete_num
     *
     * @mbggenerated
     */
    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
    }

    /**
     *改派订单数
     *`guide_ext`.resassign_num
     *
     * @return the value of `guide_ext`.resassign_num
     *
     * @mbggenerated
     */
    public Integer getResassignNum() {
        return resassignNum;
    }

    /**
     *改派订单数
     *`guide_ext`.resassign_num
     *
     * @param resassignNum the value for `guide_ext`.resassign_num
     *
     * @mbggenerated
     */
    public void setResassignNum(Integer resassignNum) {
        this.resassignNum = resassignNum;
    }

    /**
     *接受指派数
     *`guide_ext`.assign_num
     *
     * @return the value of `guide_ext`.assign_num
     *
     * @mbggenerated
     */
    public Integer getAssignNum() {
        return assignNum;
    }

    /**
     *接受指派数
     *`guide_ext`.assign_num
     *
     * @param assignNum the value for `guide_ext`.assign_num
     *
     * @mbggenerated
     */
    public void setAssignNum(Integer assignNum) {
        this.assignNum = assignNum;
    }

    /**
     *累计表态数
     *`guide_ext`.declare_num
     *
     * @return the value of `guide_ext`.declare_num
     *
     * @mbggenerated
     */
    public Integer getDeclareNum() {
        return declareNum;
    }

    /**
     *累计表态数
     *`guide_ext`.declare_num
     *
     * @param declareNum the value for `guide_ext`.declare_num
     *
     * @mbggenerated
     */
    public void setDeclareNum(Integer declareNum) {
        this.declareNum = declareNum;
    }

    /**
     *累计愿意接单数
     *`guide_ext`.show_purpose_num
     *
     * @return the value of `guide_ext`.show_purpose_num
     *
     * @mbggenerated
     */
    public Integer getShowPurposeNum() {
        return showPurposeNum;
    }

    /**
     *累计愿意接单数
     *`guide_ext`.show_purpose_num
     *
     * @param showPurposeNum the value for `guide_ext`.show_purpose_num
     *
     * @mbggenerated
     */
    public void setShowPurposeNum(Integer showPurposeNum) {
        this.showPurposeNum = showPurposeNum;
    }

    /**
     *最后一次接单时间
     *`guide_ext`.lastest_receive_time
     *
     * @return the value of `guide_ext`.lastest_receive_time
     *
     * @mbggenerated
     */
    public Date getLastestReceiveTime() {
        return lastestReceiveTime;
    }

    /**
     *最后一次接单时间
     *`guide_ext`.lastest_receive_time
     *
     * @param lastestReceiveTime the value for `guide_ext`.lastest_receive_time
     *
     * @mbggenerated
     */
    public void setLastestReceiveTime(Date lastestReceiveTime) {
        this.lastestReceiveTime = lastestReceiveTime;
    }

    /**
     *可服务项目id。用逗号分隔
     *`guide_ext`.service_project_id
     *
     * @return the value of `guide_ext`.service_project_id
     *
     * @mbggenerated
     */
    public String getServiceProjectId() {
        return serviceProjectId;
    }

    /**
     *可服务项目id。用逗号分隔
     *`guide_ext`.service_project_id
     *
     * @param serviceProjectId the value for `guide_ext`.service_project_id
     *
     * @mbggenerated
     */
    public void setServiceProjectId(String serviceProjectId) {
        this.serviceProjectId = serviceProjectId == null ? null : serviceProjectId.trim();
    }

    /**
     *可服务项目。用逗号分隔
     *`guide_ext`.service_projects
     *
     * @return the value of `guide_ext`.service_projects
     *
     * @mbggenerated
     */
    public String getServiceProjects() {
        return serviceProjects;
    }

    /**
     *可服务项目。用逗号分隔
     *`guide_ext`.service_projects
     *
     * @param serviceProjects the value for `guide_ext`.service_projects
     *
     * @mbggenerated
     */
    public void setServiceProjects(String serviceProjects) {
        this.serviceProjects = serviceProjects == null ? null : serviceProjects.trim();
    }

    /**
     *可服务语言id。语言id用逗号分隔
     *`guide_ext`.service_language_id
     *
     * @return the value of `guide_ext`.service_language_id
     *
     * @mbggenerated
     */
    public String getServiceLanguageId() {
        return serviceLanguageId;
    }

    /**
     *可服务语言id。语言id用逗号分隔
     *`guide_ext`.service_language_id
     *
     * @param serviceLanguageId the value for `guide_ext`.service_language_id
     *
     * @mbggenerated
     */
    public void setServiceLanguageId(String serviceLanguageId) {
        this.serviceLanguageId = serviceLanguageId == null ? null : serviceLanguageId.trim();
    }

    /**
     *可服务语言。语言名称用逗号分隔
     *`guide_ext`.service_languages
     *
     * @return the value of `guide_ext`.service_languages
     *
     * @mbggenerated
     */
    public String getServiceLanguages() {
        return serviceLanguages;
    }

    /**
     *可服务语言。语言名称用逗号分隔
     *`guide_ext`.service_languages
     *
     * @param serviceLanguages the value for `guide_ext`.service_languages
     *
     * @mbggenerated
     */
    public void setServiceLanguages(String serviceLanguages) {
        this.serviceLanguages = serviceLanguages == null ? null : serviceLanguages.trim();
    }

    /**
     *日租可服务城市名称
     *`guide_ext`.order_daily_citys
     *
     * @return the value of `guide_ext`.order_daily_citys
     *
     * @mbggenerated
     */
    public String getOrderDailyCitys() {
        return orderDailyCitys;
    }

    /**
     *日租可服务城市名称
     *`guide_ext`.order_daily_citys
     *
     * @param orderDailyCitys the value for `guide_ext`.order_daily_citys
     *
     * @mbggenerated
     */
    public void setOrderDailyCitys(String orderDailyCitys) {
        this.orderDailyCitys = orderDailyCitys == null ? null : orderDailyCitys.trim();
    }

    /**
     *创建时间
     *`guide_ext`.create_time
     *
     * @return the value of `guide_ext`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     *`guide_ext`.create_time
     *
     * @param createTime the value for `guide_ext`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *最近更新时间
     *`guide_ext`.update_time
     *
     * @return the value of `guide_ext`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *最近更新时间
     *`guide_ext`.update_time
     *
     * @param updateTime the value for `guide_ext`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_ext`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guideExtId=").append(guideExtId);
        sb.append(", guideId=").append(guideId);
        sb.append(", seniority=").append(seniority);
        sb.append(", integral=").append(integral);
        sb.append(", serviceStar=").append(serviceStar);
        sb.append(", cuserCollectionNum=").append(cuserCollectionNum);
        sb.append(", activeDayNum=").append(activeDayNum);
        sb.append(", successReceiveNum=").append(successReceiveNum);
        sb.append(", completeNum=").append(completeNum);
        sb.append(", resassignNum=").append(resassignNum);
        sb.append(", assignNum=").append(assignNum);
        sb.append(", declareNum=").append(declareNum);
        sb.append(", showPurposeNum=").append(showPurposeNum);
        sb.append(", lastestReceiveTime=").append(lastestReceiveTime);
        sb.append(", serviceProjectId=").append(serviceProjectId);
        sb.append(", serviceProjects=").append(serviceProjects);
        sb.append(", serviceLanguageId=").append(serviceLanguageId);
        sb.append(", serviceLanguages=").append(serviceLanguages);
        sb.append(", orderDailyCitys=").append(orderDailyCitys);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}