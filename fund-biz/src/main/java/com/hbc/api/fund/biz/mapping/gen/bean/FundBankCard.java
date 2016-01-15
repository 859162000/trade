package com.hbc.api.fund.biz.mapping.gen.bean;

import java.util.Date;

public class FundBankCard {
    /**
     *  
     *  所属表字段为`fund_bank_card`.bank_no
     */
    private String bankNo;

    /**
     *  卖家id：导游/地接社
     *  所属表字段为`fund_bank_card`.guide_id
     */
    private String guideId;

    /**
     *  
     *  所属表字段为`fund_bank_card`.guide_name
     */
    private String guideName;

    /**
     *  导游地接社ID
     *  所属表字段为`fund_bank_card`.guide_agency_id
     */
    private String guideAgencyId;

    /**
     *  导游地接社名称
     *  所属表字段为`fund_bank_card`.guide_agency_name
     */
    private String guideAgencyName;

    /**
     *  开户人姓名
     *  所属表字段为`fund_bank_card`.account_holder_name
     */
    private String accountHolderName;

    /**
     *  银行账号信息
     *  所属表字段为`fund_bank_card`.account
     */
    private String account;

    /**
     *  银行卡所属银行（包括开户行）
     *  所属表字段为`fund_bank_card`.bank
     */
    private String bank;

    /**
     *  城市ID
     *  所属表字段为`fund_bank_card`.city_id
     */
    private Integer cityId;

    /**
     *  城市名称
     *  所属表字段为`fund_bank_card`.city_name
     */
    private String cityName;

    /**
     *  银行卡-币种
     *  所属表字段为`fund_bank_card`.currency
     */
    private String currency;

    /**
     *  银行卡-国际代码swift
     *  所属表字段为`fund_bank_card`.swift
     */
    private String swift;

    /**
     *  是否已验证。0-否（默认）；1-是
     *  所属表字段为`fund_bank_card`.is_valid
     */
    private Integer isValid;

    /**
     *  财务信息分类。1-银行账号；2-支付宝账号；3-paypal账号
     *  所属表字段为`fund_bank_card`.type
     */
    private Integer type;

    /**
     *  状态；0 = 删除（解除绑定）；1=正常（绑定）
     *  所属表字段为`fund_bank_card`.status
     */
    private Integer status;

    /**
     *  更新时间
     *  所属表字段为`fund_bank_card`.update_time
     */
    private Date updateTime;

    /**
     *  创建时间
     *  所属表字段为`fund_bank_card`.create_time
     */
    private Date createTime;

    /**
     *
     *`fund_bank_card`.bank_no
     *
     * @return the value of `fund_bank_card`.bank_no
     *
     * @mbggenerated
     */
    public String getBankNo() {
        return bankNo;
    }

    /**
     *
     *`fund_bank_card`.bank_no
     *
     * @param bankNo the value for `fund_bank_card`.bank_no
     *
     * @mbggenerated
     */
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    /**
     *卖家id：导游/地接社
     *`fund_bank_card`.guide_id
     *
     * @return the value of `fund_bank_card`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *卖家id：导游/地接社
     *`fund_bank_card`.guide_id
     *
     * @param guideId the value for `fund_bank_card`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *
     *`fund_bank_card`.guide_name
     *
     * @return the value of `fund_bank_card`.guide_name
     *
     * @mbggenerated
     */
    public String getGuideName() {
        return guideName;
    }

    /**
     *
     *`fund_bank_card`.guide_name
     *
     * @param guideName the value for `fund_bank_card`.guide_name
     *
     * @mbggenerated
     */
    public void setGuideName(String guideName) {
        this.guideName = guideName == null ? null : guideName.trim();
    }

    /**
     *导游地接社ID
     *`fund_bank_card`.guide_agency_id
     *
     * @return the value of `fund_bank_card`.guide_agency_id
     *
     * @mbggenerated
     */
    public String getGuideAgencyId() {
        return guideAgencyId;
    }

    /**
     *导游地接社ID
     *`fund_bank_card`.guide_agency_id
     *
     * @param guideAgencyId the value for `fund_bank_card`.guide_agency_id
     *
     * @mbggenerated
     */
    public void setGuideAgencyId(String guideAgencyId) {
        this.guideAgencyId = guideAgencyId == null ? null : guideAgencyId.trim();
    }

    /**
     *导游地接社名称
     *`fund_bank_card`.guide_agency_name
     *
     * @return the value of `fund_bank_card`.guide_agency_name
     *
     * @mbggenerated
     */
    public String getGuideAgencyName() {
        return guideAgencyName;
    }

    /**
     *导游地接社名称
     *`fund_bank_card`.guide_agency_name
     *
     * @param guideAgencyName the value for `fund_bank_card`.guide_agency_name
     *
     * @mbggenerated
     */
    public void setGuideAgencyName(String guideAgencyName) {
        this.guideAgencyName = guideAgencyName == null ? null : guideAgencyName.trim();
    }

    /**
     *开户人姓名
     *`fund_bank_card`.account_holder_name
     *
     * @return the value of `fund_bank_card`.account_holder_name
     *
     * @mbggenerated
     */
    public String getAccountHolderName() {
        return accountHolderName;
    }

    /**
     *开户人姓名
     *`fund_bank_card`.account_holder_name
     *
     * @param accountHolderName the value for `fund_bank_card`.account_holder_name
     *
     * @mbggenerated
     */
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName == null ? null : accountHolderName.trim();
    }

    /**
     *银行账号信息
     *`fund_bank_card`.account
     *
     * @return the value of `fund_bank_card`.account
     *
     * @mbggenerated
     */
    public String getAccount() {
        return account;
    }

    /**
     *银行账号信息
     *`fund_bank_card`.account
     *
     * @param account the value for `fund_bank_card`.account
     *
     * @mbggenerated
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     *银行卡所属银行（包括开户行）
     *`fund_bank_card`.bank
     *
     * @return the value of `fund_bank_card`.bank
     *
     * @mbggenerated
     */
    public String getBank() {
        return bank;
    }

    /**
     *银行卡所属银行（包括开户行）
     *`fund_bank_card`.bank
     *
     * @param bank the value for `fund_bank_card`.bank
     *
     * @mbggenerated
     */
    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    /**
     *城市ID
     *`fund_bank_card`.city_id
     *
     * @return the value of `fund_bank_card`.city_id
     *
     * @mbggenerated
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     *城市ID
     *`fund_bank_card`.city_id
     *
     * @param cityId the value for `fund_bank_card`.city_id
     *
     * @mbggenerated
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     *城市名称
     *`fund_bank_card`.city_name
     *
     * @return the value of `fund_bank_card`.city_name
     *
     * @mbggenerated
     */
    public String getCityName() {
        return cityName;
    }

    /**
     *城市名称
     *`fund_bank_card`.city_name
     *
     * @param cityName the value for `fund_bank_card`.city_name
     *
     * @mbggenerated
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     *银行卡-币种
     *`fund_bank_card`.currency
     *
     * @return the value of `fund_bank_card`.currency
     *
     * @mbggenerated
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *银行卡-币种
     *`fund_bank_card`.currency
     *
     * @param currency the value for `fund_bank_card`.currency
     *
     * @mbggenerated
     */
    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    /**
     *银行卡-国际代码swift
     *`fund_bank_card`.swift
     *
     * @return the value of `fund_bank_card`.swift
     *
     * @mbggenerated
     */
    public String getSwift() {
        return swift;
    }

    /**
     *银行卡-国际代码swift
     *`fund_bank_card`.swift
     *
     * @param swift the value for `fund_bank_card`.swift
     *
     * @mbggenerated
     */
    public void setSwift(String swift) {
        this.swift = swift == null ? null : swift.trim();
    }

    /**
     *是否已验证。0-否（默认）；1-是
     *`fund_bank_card`.is_valid
     *
     * @return the value of `fund_bank_card`.is_valid
     *
     * @mbggenerated
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     *是否已验证。0-否（默认）；1-是
     *`fund_bank_card`.is_valid
     *
     * @param isValid the value for `fund_bank_card`.is_valid
     *
     * @mbggenerated
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /**
     *财务信息分类。1-银行账号；2-支付宝账号；3-paypal账号
     *`fund_bank_card`.type
     *
     * @return the value of `fund_bank_card`.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     *财务信息分类。1-银行账号；2-支付宝账号；3-paypal账号
     *`fund_bank_card`.type
     *
     * @param type the value for `fund_bank_card`.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     *状态；0 = 删除（解除绑定）；1=正常（绑定）
     *`fund_bank_card`.status
     *
     * @return the value of `fund_bank_card`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *状态；0 = 删除（解除绑定）；1=正常（绑定）
     *`fund_bank_card`.status
     *
     * @param status the value for `fund_bank_card`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *更新时间
     *`fund_bank_card`.update_time
     *
     * @return the value of `fund_bank_card`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新时间
     *`fund_bank_card`.update_time
     *
     * @param updateTime the value for `fund_bank_card`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *创建时间
     *`fund_bank_card`.create_time
     *
     * @return the value of `fund_bank_card`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     *`fund_bank_card`.create_time
     *
     * @param createTime the value for `fund_bank_card`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_bank_card`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bankNo=").append(bankNo);
        sb.append(", guideId=").append(guideId);
        sb.append(", guideName=").append(guideName);
        sb.append(", guideAgencyId=").append(guideAgencyId);
        sb.append(", guideAgencyName=").append(guideAgencyName);
        sb.append(", accountHolderName=").append(accountHolderName);
        sb.append(", account=").append(account);
        sb.append(", bank=").append(bank);
        sb.append(", cityId=").append(cityId);
        sb.append(", cityName=").append(cityName);
        sb.append(", currency=").append(currency);
        sb.append(", swift=").append(swift);
        sb.append(", isValid=").append(isValid);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}