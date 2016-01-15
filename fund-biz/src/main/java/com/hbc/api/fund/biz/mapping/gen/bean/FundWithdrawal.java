package com.hbc.api.fund.biz.mapping.gen.bean;

import java.util.Date;

public class FundWithdrawal {
    /**
     *  
     *  所属表字段为`fund_withdraw`.draw_no
     */
    private String drawNo;

    /**
     *  
     *  所属表字段为`fund_withdraw`.bach_no
     */
    private String bachNo;

    /**
     *  
     *  所属表字段为`fund_withdraw`.guide_id
     */
    private String guideId;

    /**
     *  
     *  所属表字段为`fund_withdraw`.guide_name
     */
    private String guideName;

    /**
     *  导游地接社ID
     *  所属表字段为`fund_withdraw`.guide_agency_id
     */
    private String guideAgencyId;

    /**
     *  导游地接社名称
     *  所属表字段为`fund_withdraw`.guide_agency_name
     */
    private String guideAgencyName;

    /**
     *  申请时间
     *  所属表字段为`fund_withdraw`.apply_time
     */
    private Date applyTime;

    /**
     *  申请金额
     *  所属表字段为`fund_withdraw`.price
     */
    private Double price;

    /**
     *  导游银行卡 卡号ID
     *  所属表字段为`fund_withdraw`.fin_bank_no
     */
    private String finBankNo;

    /**
     *  收款银行账号开户名（冗余）
     *  所属表字段为`fund_withdraw`.fin_name
     */
    private String finName;

    /**
     *  收款账号（冗余）
     *  所属表字段为`fund_withdraw`.fin_account
     */
    private String finAccount;

    /**
     *  收款银行账号银行名称（冗余）
     *  所属表字段为`fund_withdraw`.fin_bank
     */
    private String finBank;

    /**
     *  收款币种（冗余）
     *  所属表字段为`fund_withdraw`.fin_currency
     */
    private String finCurrency;

    /**
     *  收款账号 1-银行账号；2-支付宝账号；3-paypal账号
     *  所属表字段为`fund_withdraw`.fin_type
     */
    private Integer finType;

    /**
     *  操作者
     *  所属表字段为`fund_withdraw`.admin_id
     */
    private String adminId;

    /**
     *  
     *  所属表字段为`fund_withdraw`.admin_name
     */
    private String adminName;

    /**
     *  我方支付的真实金额（仅财务可见）
     *  所属表字段为`fund_withdraw`.actual_price
     */
    private Double actualPrice;

    /**
     *  转账日期
     *  所属表字段为`fund_withdraw`.transfer_time
     */
    private Date transferTime;

    /**
     *  我方支付账号（仅财务可见）
     *  所属表字段为`fund_withdraw`.account
     */
    private String account;

    /**
     *  备注（仅财务可见）
     *  所属表字段为`fund_withdraw`.draw_comment
     */
    private String drawComment;

    /**
     *  状态：
            3-自动提现已申请; 
            2-自动提现失败; 
            1=已转账；
            0=申请提现；
            -1=删除申请提现；
            -2=删除（已处理列表不可见） 待确认
     *  所属表字段为`fund_withdraw`.draw_status
     */
    private Integer drawStatus;

    /**
     *  是否自动提现 0-否 1-是
     *  所属表字段为`fund_withdraw`.is_auto
     */
    private Integer isAuto;

    /**
     *  
     *  所属表字段为`fund_withdraw`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`fund_withdraw`.create_time
     */
    private Date createTime;

    /**
     *  处理状态 0：待处理 ，1 已处理 ，2 自动提款
     *  所属表字段为`fund_withdraw`.process_status
     */
    private Integer processStatus;

    /**
     *  导游编号
     *  所属表字段为`fund_withdraw`.guide_no
     */
    private String guideNo;

    /**
     *
     *`fund_withdraw`.draw_no
     *
     * @return the value of `fund_withdraw`.draw_no
     *
     * @mbggenerated
     */
    public String getDrawNo() {
        return drawNo;
    }

    /**
     *
     *`fund_withdraw`.draw_no
     *
     * @param drawNo the value for `fund_withdraw`.draw_no
     *
     * @mbggenerated
     */
    public void setDrawNo(String drawNo) {
        this.drawNo = drawNo == null ? null : drawNo.trim();
    }

    /**
     *
     *`fund_withdraw`.bach_no
     *
     * @return the value of `fund_withdraw`.bach_no
     *
     * @mbggenerated
     */
    public String getBachNo() {
        return bachNo;
    }

    /**
     *
     *`fund_withdraw`.bach_no
     *
     * @param bachNo the value for `fund_withdraw`.bach_no
     *
     * @mbggenerated
     */
    public void setBachNo(String bachNo) {
        this.bachNo = bachNo == null ? null : bachNo.trim();
    }

    /**
     *
     *`fund_withdraw`.guide_id
     *
     * @return the value of `fund_withdraw`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *
     *`fund_withdraw`.guide_id
     *
     * @param guideId the value for `fund_withdraw`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *
     *`fund_withdraw`.guide_name
     *
     * @return the value of `fund_withdraw`.guide_name
     *
     * @mbggenerated
     */
    public String getGuideName() {
        return guideName;
    }

    /**
     *
     *`fund_withdraw`.guide_name
     *
     * @param guideName the value for `fund_withdraw`.guide_name
     *
     * @mbggenerated
     */
    public void setGuideName(String guideName) {
        this.guideName = guideName == null ? null : guideName.trim();
    }

    /**
     *导游地接社ID
     *`fund_withdraw`.guide_agency_id
     *
     * @return the value of `fund_withdraw`.guide_agency_id
     *
     * @mbggenerated
     */
    public String getGuideAgencyId() {
        return guideAgencyId;
    }

    /**
     *导游地接社ID
     *`fund_withdraw`.guide_agency_id
     *
     * @param guideAgencyId the value for `fund_withdraw`.guide_agency_id
     *
     * @mbggenerated
     */
    public void setGuideAgencyId(String guideAgencyId) {
        this.guideAgencyId = guideAgencyId == null ? null : guideAgencyId.trim();
    }

    /**
     *导游地接社名称
     *`fund_withdraw`.guide_agency_name
     *
     * @return the value of `fund_withdraw`.guide_agency_name
     *
     * @mbggenerated
     */
    public String getGuideAgencyName() {
        return guideAgencyName;
    }

    /**
     *导游地接社名称
     *`fund_withdraw`.guide_agency_name
     *
     * @param guideAgencyName the value for `fund_withdraw`.guide_agency_name
     *
     * @mbggenerated
     */
    public void setGuideAgencyName(String guideAgencyName) {
        this.guideAgencyName = guideAgencyName == null ? null : guideAgencyName.trim();
    }

    /**
     *申请时间
     *`fund_withdraw`.apply_time
     *
     * @return the value of `fund_withdraw`.apply_time
     *
     * @mbggenerated
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     *申请时间
     *`fund_withdraw`.apply_time
     *
     * @param applyTime the value for `fund_withdraw`.apply_time
     *
     * @mbggenerated
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     *申请金额
     *`fund_withdraw`.price
     *
     * @return the value of `fund_withdraw`.price
     *
     * @mbggenerated
     */
    public Double getPrice() {
        return price;
    }

    /**
     *申请金额
     *`fund_withdraw`.price
     *
     * @param price the value for `fund_withdraw`.price
     *
     * @mbggenerated
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     *导游银行卡 卡号ID
     *`fund_withdraw`.fin_bank_no
     *
     * @return the value of `fund_withdraw`.fin_bank_no
     *
     * @mbggenerated
     */
    public String getFinBankNo() {
        return finBankNo;
    }

    /**
     *导游银行卡 卡号ID
     *`fund_withdraw`.fin_bank_no
     *
     * @param finBankNo the value for `fund_withdraw`.fin_bank_no
     *
     * @mbggenerated
     */
    public void setFinBankNo(String finBankNo) {
        this.finBankNo = finBankNo == null ? null : finBankNo.trim();
    }

    /**
     *收款银行账号开户名（冗余）
     *`fund_withdraw`.fin_name
     *
     * @return the value of `fund_withdraw`.fin_name
     *
     * @mbggenerated
     */
    public String getFinName() {
        return finName;
    }

    /**
     *收款银行账号开户名（冗余）
     *`fund_withdraw`.fin_name
     *
     * @param finName the value for `fund_withdraw`.fin_name
     *
     * @mbggenerated
     */
    public void setFinName(String finName) {
        this.finName = finName == null ? null : finName.trim();
    }

    /**
     *收款账号（冗余）
     *`fund_withdraw`.fin_account
     *
     * @return the value of `fund_withdraw`.fin_account
     *
     * @mbggenerated
     */
    public String getFinAccount() {
        return finAccount;
    }

    /**
     *收款账号（冗余）
     *`fund_withdraw`.fin_account
     *
     * @param finAccount the value for `fund_withdraw`.fin_account
     *
     * @mbggenerated
     */
    public void setFinAccount(String finAccount) {
        this.finAccount = finAccount == null ? null : finAccount.trim();
    }

    /**
     *收款银行账号银行名称（冗余）
     *`fund_withdraw`.fin_bank
     *
     * @return the value of `fund_withdraw`.fin_bank
     *
     * @mbggenerated
     */
    public String getFinBank() {
        return finBank;
    }

    /**
     *收款银行账号银行名称（冗余）
     *`fund_withdraw`.fin_bank
     *
     * @param finBank the value for `fund_withdraw`.fin_bank
     *
     * @mbggenerated
     */
    public void setFinBank(String finBank) {
        this.finBank = finBank == null ? null : finBank.trim();
    }

    /**
     *收款币种（冗余）
     *`fund_withdraw`.fin_currency
     *
     * @return the value of `fund_withdraw`.fin_currency
     *
     * @mbggenerated
     */
    public String getFinCurrency() {
        return finCurrency;
    }

    /**
     *收款币种（冗余）
     *`fund_withdraw`.fin_currency
     *
     * @param finCurrency the value for `fund_withdraw`.fin_currency
     *
     * @mbggenerated
     */
    public void setFinCurrency(String finCurrency) {
        this.finCurrency = finCurrency == null ? null : finCurrency.trim();
    }

    /**
     *收款账号 1-银行账号；2-支付宝账号；3-paypal账号
     *`fund_withdraw`.fin_type
     *
     * @return the value of `fund_withdraw`.fin_type
     *
     * @mbggenerated
     */
    public Integer getFinType() {
        return finType;
    }

    /**
     *收款账号 1-银行账号；2-支付宝账号；3-paypal账号
     *`fund_withdraw`.fin_type
     *
     * @param finType the value for `fund_withdraw`.fin_type
     *
     * @mbggenerated
     */
    public void setFinType(Integer finType) {
        this.finType = finType;
    }

    /**
     *操作者
     *`fund_withdraw`.admin_id
     *
     * @return the value of `fund_withdraw`.admin_id
     *
     * @mbggenerated
     */
    public String getAdminId() {
        return adminId;
    }

    /**
     *操作者
     *`fund_withdraw`.admin_id
     *
     * @param adminId the value for `fund_withdraw`.admin_id
     *
     * @mbggenerated
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
    }

    /**
     *
     *`fund_withdraw`.admin_name
     *
     * @return the value of `fund_withdraw`.admin_name
     *
     * @mbggenerated
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     *
     *`fund_withdraw`.admin_name
     *
     * @param adminName the value for `fund_withdraw`.admin_name
     *
     * @mbggenerated
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    /**
     *我方支付的真实金额（仅财务可见）
     *`fund_withdraw`.actual_price
     *
     * @return the value of `fund_withdraw`.actual_price
     *
     * @mbggenerated
     */
    public Double getActualPrice() {
        return actualPrice;
    }

    /**
     *我方支付的真实金额（仅财务可见）
     *`fund_withdraw`.actual_price
     *
     * @param actualPrice the value for `fund_withdraw`.actual_price
     *
     * @mbggenerated
     */
    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
    }

    /**
     *转账日期
     *`fund_withdraw`.transfer_time
     *
     * @return the value of `fund_withdraw`.transfer_time
     *
     * @mbggenerated
     */
    public Date getTransferTime() {
        return transferTime;
    }

    /**
     *转账日期
     *`fund_withdraw`.transfer_time
     *
     * @param transferTime the value for `fund_withdraw`.transfer_time
     *
     * @mbggenerated
     */
    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    /**
     *我方支付账号（仅财务可见）
     *`fund_withdraw`.account
     *
     * @return the value of `fund_withdraw`.account
     *
     * @mbggenerated
     */
    public String getAccount() {
        return account;
    }

    /**
     *我方支付账号（仅财务可见）
     *`fund_withdraw`.account
     *
     * @param account the value for `fund_withdraw`.account
     *
     * @mbggenerated
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     *备注（仅财务可见）
     *`fund_withdraw`.draw_comment
     *
     * @return the value of `fund_withdraw`.draw_comment
     *
     * @mbggenerated
     */
    public String getDrawComment() {
        return drawComment;
    }

    /**
     *备注（仅财务可见）
     *`fund_withdraw`.draw_comment
     *
     * @param drawComment the value for `fund_withdraw`.draw_comment
     *
     * @mbggenerated
     */
    public void setDrawComment(String drawComment) {
        this.drawComment = drawComment == null ? null : drawComment.trim();
    }

    /**
     *状态：
            3-自动提现已申请; 
            2-自动提现失败; 
            1=已转账；
            0=申请提现；
            -1=删除申请提现；
            -2=删除（已处理列表不可见） 待确认
     *`fund_withdraw`.draw_status
     *
     * @return the value of `fund_withdraw`.draw_status
     *
     * @mbggenerated
     */
    public Integer getDrawStatus() {
        return drawStatus;
    }

    /**
     *状态：
            3-自动提现已申请; 
            2-自动提现失败; 
            1=已转账；
            0=申请提现；
            -1=删除申请提现；
            -2=删除（已处理列表不可见） 待确认
     *`fund_withdraw`.draw_status
     *
     * @param drawStatus the value for `fund_withdraw`.draw_status
     *
     * @mbggenerated
     */
    public void setDrawStatus(Integer drawStatus) {
        this.drawStatus = drawStatus;
    }

    /**
     *是否自动提现 0-否 1-是
     *`fund_withdraw`.is_auto
     *
     * @return the value of `fund_withdraw`.is_auto
     *
     * @mbggenerated
     */
    public Integer getIsAuto() {
        return isAuto;
    }

    /**
     *是否自动提现 0-否 1-是
     *`fund_withdraw`.is_auto
     *
     * @param isAuto the value for `fund_withdraw`.is_auto
     *
     * @mbggenerated
     */
    public void setIsAuto(Integer isAuto) {
        this.isAuto = isAuto;
    }

    /**
     *
     *`fund_withdraw`.update_time
     *
     * @return the value of `fund_withdraw`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`fund_withdraw`.update_time
     *
     * @param updateTime the value for `fund_withdraw`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`fund_withdraw`.create_time
     *
     * @return the value of `fund_withdraw`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`fund_withdraw`.create_time
     *
     * @param createTime the value for `fund_withdraw`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *处理状态 0：待处理 ，1 已处理 ，2 自动提款
     *`fund_withdraw`.process_status
     *
     * @return the value of `fund_withdraw`.process_status
     *
     * @mbggenerated
     */
    public Integer getProcessStatus() {
        return processStatus;
    }

    /**
     *处理状态 0：待处理 ，1 已处理 ，2 自动提款
     *`fund_withdraw`.process_status
     *
     * @param processStatus the value for `fund_withdraw`.process_status
     *
     * @mbggenerated
     */
    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    /**
     *导游编号
     *`fund_withdraw`.guide_no
     *
     * @return the value of `fund_withdraw`.guide_no
     *
     * @mbggenerated
     */
    public String getGuideNo() {
        return guideNo;
    }

    /**
     *导游编号
     *`fund_withdraw`.guide_no
     *
     * @param guideNo the value for `fund_withdraw`.guide_no
     *
     * @mbggenerated
     */
    public void setGuideNo(String guideNo) {
        this.guideNo = guideNo == null ? null : guideNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_withdraw`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", drawNo=").append(drawNo);
        sb.append(", bachNo=").append(bachNo);
        sb.append(", guideId=").append(guideId);
        sb.append(", guideName=").append(guideName);
        sb.append(", guideAgencyId=").append(guideAgencyId);
        sb.append(", guideAgencyName=").append(guideAgencyName);
        sb.append(", applyTime=").append(applyTime);
        sb.append(", price=").append(price);
        sb.append(", finBankNo=").append(finBankNo);
        sb.append(", finName=").append(finName);
        sb.append(", finAccount=").append(finAccount);
        sb.append(", finBank=").append(finBank);
        sb.append(", finCurrency=").append(finCurrency);
        sb.append(", finType=").append(finType);
        sb.append(", adminId=").append(adminId);
        sb.append(", adminName=").append(adminName);
        sb.append(", actualPrice=").append(actualPrice);
        sb.append(", transferTime=").append(transferTime);
        sb.append(", account=").append(account);
        sb.append(", drawComment=").append(drawComment);
        sb.append(", drawStatus=").append(drawStatus);
        sb.append(", isAuto=").append(isAuto);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", processStatus=").append(processStatus);
        sb.append(", guideNo=").append(guideNo);
        sb.append("]");
        return sb.toString();
    }
}