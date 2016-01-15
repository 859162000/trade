package com.hbc.api.fund.biz.mapping.genx.xbean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class FundBankCardBindBean {

    /**
     *  卖家id：导游/地接社
     *  所属表字段为`fund_bank_card`.guide_id
     */
    private String guideId;


    /**
     *  开户人姓名
     *  所属表字段为`fund_bank_card`.account_holder_name
     */
    @NotBlank(message="开户人姓名不能为空")
    private String accountHolderName;

    /**
     *  银行账号信息
     *  所属表字段为`fund_bank_card`.account
     */
    @NotBlank(message="银行账号不能为空")
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
     *  财务信息分类。1-银行账号；2-支付宝账号；3-paypal账号
     *  所属表字段为`fund_bank_card`.type
     */
    @NotNull(message="财务信息分类不能为空")
    private Integer type;


    /**
     *卖家id：导游/地接社
     *`fund_bank_card`.guide_id
     *
     * @return the value of `fund_bank_card`.guide_id
     *
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
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
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }


    /**
     *开户人姓名
     *`fund_bank_card`.account_holder_name
     *
     * @return the value of `fund_bank_card`.account_holder_name
     *
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
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
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
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
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
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
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
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
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
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
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
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
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
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
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
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
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
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
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
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
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
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
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
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
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
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
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
     */
    public void setSwift(String swift) {
        this.swift = swift == null ? null : swift.trim();
    }


    /**
     *财务信息分类。1-银行账号；2-支付宝账号；3-paypal账号
     *`fund_bank_card`.type
     *
     * @return the value of `fund_bank_card`.type
     *
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
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
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_bank_card`
     *
     * @mbggenerated Thu Nov 26 15:54:35 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guideId=").append(guideId);
        sb.append(", accountHolderName=").append(accountHolderName);
        sb.append(", account=").append(account);
        sb.append(", bank=").append(bank);
        sb.append(", cityId=").append(cityId);
        sb.append(", cityName=").append(cityName);
        sb.append(", currency=").append(currency);
        sb.append(", swift=").append(swift);
        sb.append(", type=").append(type);
        sb.append("]");
        return sb.toString();
    }
}