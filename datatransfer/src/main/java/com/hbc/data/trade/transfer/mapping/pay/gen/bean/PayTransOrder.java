package com.hbc.data.trade.transfer.mapping.pay.gen.bean;

public class PayTransOrder {
    /**
     *  主键id
     *  所属表字段为`pay_trans_order`.id
     */
    private String id;

    /**
     *  对应的用户Id
     *  所属表字段为`pay_trans_order`.user_id
     */
    private String userId;

    /**
     *  账户id
     *  所属表字段为`pay_trans_order`.account_id
     */
    private String accountId;

    /**
     *  频道
     *  所属表字段为`pay_trans_order`.channel
     */
    private Integer channel;

    /**
     *  支付网关
     *  所属表字段为`pay_trans_order`.gateway
     */
    private Object gateway;

    /**
     *  商户订单号 给支付宝的订单号
     *  所属表字段为`pay_trans_order`.mer_trans_no
     */
    private String merTransNo;

    /**
     *  第三方支付平台订单号 支付宝返回订单号
     *  所属表字段为`pay_trans_order`.ser_trans_no
     */
    private String serTransNo;

    /**
     *  业务线订单号   
     *  所属表字段为`pay_trans_order`.busi_trans_no
     */
    private String busiTransNo;

    /**
     *  批量转账批次号,取首单商户网站交易号
     *  所属表字段为`pay_trans_order`.batch_no
     */
    private String batchNo;

    /**
     *  转账目标账号
     *  所属表字段为`pay_trans_order`.person_account
     */
    private String personAccount;

    /**
     *  转账目标用户的姓名
     *  所属表字段为`pay_trans_order`.person_name
     */
    private String personName;

    /**
     *  手机号
     *  所属表字段为`pay_trans_order`.mobile_no
     */
    private String mobileNo;

    /**
     *  银行编码
     *  所属表字段为`pay_trans_order`.bank_code
     */
    private String bankCode;

    /**
     *  转账金额
     *  所属表字段为`pay_trans_order`.trans_amount
     */
    private Integer transAmount;

    /**
     *  转账名称
     *  所属表字段为`pay_trans_order`.subject
     */
    private String subject;

    /**
     *  转账描述
     *  所属表字段为`pay_trans_order`.body
     */
    private String body;

    /**
     *  订单创建时间
     *  所属表字段为`pay_trans_order`.create_time
     */
    private Integer createTime;

    /**
     *  支付时间
     *  所属表字段为`pay_trans_order`.pay_time
     */
    private Integer payTime;

    /**
     *  第三方回调时间
     *  所属表字段为`pay_trans_order`.ser_notify_time
     */
    private Integer serNotifyTime;

    /**
     *  1:未通知;2,转账成功;3,转账失败
     *  所属表字段为`pay_trans_order`.ser_notify_status
     */
    private Object serNotifyStatus;

    /**
     *  第三方回调结果
     *  所属表字段为`pay_trans_order`.ser_notify_log
     */
    private String serNotifyLog;

    /**
     *  回调URL
     *  所属表字段为`pay_trans_order`.callback_url
     */
    private String callbackUrl;

    /**
     *  回调状态
     *  所属表字段为`pay_trans_order`.callback_status
     */
    private Object callbackStatus;

    /**
     *  回调次数
     *  所属表字段为`pay_trans_order`.callback_count
     */
    private Object callbackCount;

    /**
     *  最后回调时间
     *  所属表字段为`pay_trans_order`.callback_time
     */
    private Integer callbackTime;

    /**
     *主键id
     *`pay_trans_order`.id
     *
     * @return the value of `pay_trans_order`.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     *主键id
     *`pay_trans_order`.id
     *
     * @param id the value for `pay_trans_order`.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *对应的用户Id
     *`pay_trans_order`.user_id
     *
     * @return the value of `pay_trans_order`.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     *对应的用户Id
     *`pay_trans_order`.user_id
     *
     * @param userId the value for `pay_trans_order`.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     *账户id
     *`pay_trans_order`.account_id
     *
     * @return the value of `pay_trans_order`.account_id
     *
     * @mbggenerated
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     *账户id
     *`pay_trans_order`.account_id
     *
     * @param accountId the value for `pay_trans_order`.account_id
     *
     * @mbggenerated
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     *频道
     *`pay_trans_order`.channel
     *
     * @return the value of `pay_trans_order`.channel
     *
     * @mbggenerated
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     *频道
     *`pay_trans_order`.channel
     *
     * @param channel the value for `pay_trans_order`.channel
     *
     * @mbggenerated
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    /**
     *支付网关
     *`pay_trans_order`.gateway
     *
     * @return the value of `pay_trans_order`.gateway
     *
     * @mbggenerated
     */
    public Object getGateway() {
        return gateway;
    }

    /**
     *支付网关
     *`pay_trans_order`.gateway
     *
     * @param gateway the value for `pay_trans_order`.gateway
     *
     * @mbggenerated
     */
    public void setGateway(Object gateway) {
        this.gateway = gateway;
    }

    /**
     *商户订单号 给支付宝的订单号
     *`pay_trans_order`.mer_trans_no
     *
     * @return the value of `pay_trans_order`.mer_trans_no
     *
     * @mbggenerated
     */
    public String getMerTransNo() {
        return merTransNo;
    }

    /**
     *商户订单号 给支付宝的订单号
     *`pay_trans_order`.mer_trans_no
     *
     * @param merTransNo the value for `pay_trans_order`.mer_trans_no
     *
     * @mbggenerated
     */
    public void setMerTransNo(String merTransNo) {
        this.merTransNo = merTransNo == null ? null : merTransNo.trim();
    }

    /**
     *第三方支付平台订单号 支付宝返回订单号
     *`pay_trans_order`.ser_trans_no
     *
     * @return the value of `pay_trans_order`.ser_trans_no
     *
     * @mbggenerated
     */
    public String getSerTransNo() {
        return serTransNo;
    }

    /**
     *第三方支付平台订单号 支付宝返回订单号
     *`pay_trans_order`.ser_trans_no
     *
     * @param serTransNo the value for `pay_trans_order`.ser_trans_no
     *
     * @mbggenerated
     */
    public void setSerTransNo(String serTransNo) {
        this.serTransNo = serTransNo == null ? null : serTransNo.trim();
    }

    /**
     *业务线订单号   
     *`pay_trans_order`.busi_trans_no
     *
     * @return the value of `pay_trans_order`.busi_trans_no
     *
     * @mbggenerated
     */
    public String getBusiTransNo() {
        return busiTransNo;
    }

    /**
     *业务线订单号   
     *`pay_trans_order`.busi_trans_no
     *
     * @param busiTransNo the value for `pay_trans_order`.busi_trans_no
     *
     * @mbggenerated
     */
    public void setBusiTransNo(String busiTransNo) {
        this.busiTransNo = busiTransNo == null ? null : busiTransNo.trim();
    }

    /**
     *批量转账批次号,取首单商户网站交易号
     *`pay_trans_order`.batch_no
     *
     * @return the value of `pay_trans_order`.batch_no
     *
     * @mbggenerated
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     *批量转账批次号,取首单商户网站交易号
     *`pay_trans_order`.batch_no
     *
     * @param batchNo the value for `pay_trans_order`.batch_no
     *
     * @mbggenerated
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    /**
     *转账目标账号
     *`pay_trans_order`.person_account
     *
     * @return the value of `pay_trans_order`.person_account
     *
     * @mbggenerated
     */
    public String getPersonAccount() {
        return personAccount;
    }

    /**
     *转账目标账号
     *`pay_trans_order`.person_account
     *
     * @param personAccount the value for `pay_trans_order`.person_account
     *
     * @mbggenerated
     */
    public void setPersonAccount(String personAccount) {
        this.personAccount = personAccount == null ? null : personAccount.trim();
    }

    /**
     *转账目标用户的姓名
     *`pay_trans_order`.person_name
     *
     * @return the value of `pay_trans_order`.person_name
     *
     * @mbggenerated
     */
    public String getPersonName() {
        return personName;
    }

    /**
     *转账目标用户的姓名
     *`pay_trans_order`.person_name
     *
     * @param personName the value for `pay_trans_order`.person_name
     *
     * @mbggenerated
     */
    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    /**
     *手机号
     *`pay_trans_order`.mobile_no
     *
     * @return the value of `pay_trans_order`.mobile_no
     *
     * @mbggenerated
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     *手机号
     *`pay_trans_order`.mobile_no
     *
     * @param mobileNo the value for `pay_trans_order`.mobile_no
     *
     * @mbggenerated
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo == null ? null : mobileNo.trim();
    }

    /**
     *银行编码
     *`pay_trans_order`.bank_code
     *
     * @return the value of `pay_trans_order`.bank_code
     *
     * @mbggenerated
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     *银行编码
     *`pay_trans_order`.bank_code
     *
     * @param bankCode the value for `pay_trans_order`.bank_code
     *
     * @mbggenerated
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    /**
     *转账金额
     *`pay_trans_order`.trans_amount
     *
     * @return the value of `pay_trans_order`.trans_amount
     *
     * @mbggenerated
     */
    public Integer getTransAmount() {
        return transAmount;
    }

    /**
     *转账金额
     *`pay_trans_order`.trans_amount
     *
     * @param transAmount the value for `pay_trans_order`.trans_amount
     *
     * @mbggenerated
     */
    public void setTransAmount(Integer transAmount) {
        this.transAmount = transAmount;
    }

    /**
     *转账名称
     *`pay_trans_order`.subject
     *
     * @return the value of `pay_trans_order`.subject
     *
     * @mbggenerated
     */
    public String getSubject() {
        return subject;
    }

    /**
     *转账名称
     *`pay_trans_order`.subject
     *
     * @param subject the value for `pay_trans_order`.subject
     *
     * @mbggenerated
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    /**
     *转账描述
     *`pay_trans_order`.body
     *
     * @return the value of `pay_trans_order`.body
     *
     * @mbggenerated
     */
    public String getBody() {
        return body;
    }

    /**
     *转账描述
     *`pay_trans_order`.body
     *
     * @param body the value for `pay_trans_order`.body
     *
     * @mbggenerated
     */
    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    /**
     *订单创建时间
     *`pay_trans_order`.create_time
     *
     * @return the value of `pay_trans_order`.create_time
     *
     * @mbggenerated
     */
    public Integer getCreateTime() {
        return createTime;
    }

    /**
     *订单创建时间
     *`pay_trans_order`.create_time
     *
     * @param createTime the value for `pay_trans_order`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**
     *支付时间
     *`pay_trans_order`.pay_time
     *
     * @return the value of `pay_trans_order`.pay_time
     *
     * @mbggenerated
     */
    public Integer getPayTime() {
        return payTime;
    }

    /**
     *支付时间
     *`pay_trans_order`.pay_time
     *
     * @param payTime the value for `pay_trans_order`.pay_time
     *
     * @mbggenerated
     */
    public void setPayTime(Integer payTime) {
        this.payTime = payTime;
    }

    /**
     *第三方回调时间
     *`pay_trans_order`.ser_notify_time
     *
     * @return the value of `pay_trans_order`.ser_notify_time
     *
     * @mbggenerated
     */
    public Integer getSerNotifyTime() {
        return serNotifyTime;
    }

    /**
     *第三方回调时间
     *`pay_trans_order`.ser_notify_time
     *
     * @param serNotifyTime the value for `pay_trans_order`.ser_notify_time
     *
     * @mbggenerated
     */
    public void setSerNotifyTime(Integer serNotifyTime) {
        this.serNotifyTime = serNotifyTime;
    }

    /**
     *1:未通知;2,转账成功;3,转账失败
     *`pay_trans_order`.ser_notify_status
     *
     * @return the value of `pay_trans_order`.ser_notify_status
     *
     * @mbggenerated
     */
    public Object getSerNotifyStatus() {
        return serNotifyStatus;
    }

    /**
     *1:未通知;2,转账成功;3,转账失败
     *`pay_trans_order`.ser_notify_status
     *
     * @param serNotifyStatus the value for `pay_trans_order`.ser_notify_status
     *
     * @mbggenerated
     */
    public void setSerNotifyStatus(Object serNotifyStatus) {
        this.serNotifyStatus = serNotifyStatus;
    }

    /**
     *第三方回调结果
     *`pay_trans_order`.ser_notify_log
     *
     * @return the value of `pay_trans_order`.ser_notify_log
     *
     * @mbggenerated
     */
    public String getSerNotifyLog() {
        return serNotifyLog;
    }

    /**
     *第三方回调结果
     *`pay_trans_order`.ser_notify_log
     *
     * @param serNotifyLog the value for `pay_trans_order`.ser_notify_log
     *
     * @mbggenerated
     */
    public void setSerNotifyLog(String serNotifyLog) {
        this.serNotifyLog = serNotifyLog == null ? null : serNotifyLog.trim();
    }

    /**
     *回调URL
     *`pay_trans_order`.callback_url
     *
     * @return the value of `pay_trans_order`.callback_url
     *
     * @mbggenerated
     */
    public String getCallbackUrl() {
        return callbackUrl;
    }

    /**
     *回调URL
     *`pay_trans_order`.callback_url
     *
     * @param callbackUrl the value for `pay_trans_order`.callback_url
     *
     * @mbggenerated
     */
    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl == null ? null : callbackUrl.trim();
    }

    /**
     *回调状态
     *`pay_trans_order`.callback_status
     *
     * @return the value of `pay_trans_order`.callback_status
     *
     * @mbggenerated
     */
    public Object getCallbackStatus() {
        return callbackStatus;
    }

    /**
     *回调状态
     *`pay_trans_order`.callback_status
     *
     * @param callbackStatus the value for `pay_trans_order`.callback_status
     *
     * @mbggenerated
     */
    public void setCallbackStatus(Object callbackStatus) {
        this.callbackStatus = callbackStatus;
    }

    /**
     *回调次数
     *`pay_trans_order`.callback_count
     *
     * @return the value of `pay_trans_order`.callback_count
     *
     * @mbggenerated
     */
    public Object getCallbackCount() {
        return callbackCount;
    }

    /**
     *回调次数
     *`pay_trans_order`.callback_count
     *
     * @param callbackCount the value for `pay_trans_order`.callback_count
     *
     * @mbggenerated
     */
    public void setCallbackCount(Object callbackCount) {
        this.callbackCount = callbackCount;
    }

    /**
     *最后回调时间
     *`pay_trans_order`.callback_time
     *
     * @return the value of `pay_trans_order`.callback_time
     *
     * @mbggenerated
     */
    public Integer getCallbackTime() {
        return callbackTime;
    }

    /**
     *最后回调时间
     *`pay_trans_order`.callback_time
     *
     * @param callbackTime the value for `pay_trans_order`.callback_time
     *
     * @mbggenerated
     */
    public void setCallbackTime(Integer callbackTime) {
        this.callbackTime = callbackTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_trans_order`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", accountId=").append(accountId);
        sb.append(", channel=").append(channel);
        sb.append(", gateway=").append(gateway);
        sb.append(", merTransNo=").append(merTransNo);
        sb.append(", serTransNo=").append(serTransNo);
        sb.append(", busiTransNo=").append(busiTransNo);
        sb.append(", batchNo=").append(batchNo);
        sb.append(", personAccount=").append(personAccount);
        sb.append(", personName=").append(personName);
        sb.append(", mobileNo=").append(mobileNo);
        sb.append(", bankCode=").append(bankCode);
        sb.append(", transAmount=").append(transAmount);
        sb.append(", subject=").append(subject);
        sb.append(", body=").append(body);
        sb.append(", createTime=").append(createTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", serNotifyTime=").append(serNotifyTime);
        sb.append(", serNotifyStatus=").append(serNotifyStatus);
        sb.append(", serNotifyLog=").append(serNotifyLog);
        sb.append(", callbackUrl=").append(callbackUrl);
        sb.append(", callbackStatus=").append(callbackStatus);
        sb.append(", callbackCount=").append(callbackCount);
        sb.append(", callbackTime=").append(callbackTime);
        sb.append("]");
        return sb.toString();
    }
}