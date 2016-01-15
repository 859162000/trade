/**
 * @Author lukangle
 * @2015年11月14日@下午5:17:37
 */
package com.hbc.api.fund.account.parm;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class AccountParam {
	private String accountNo; // 用户注册自动创建时有值
	private String areacode;
	private String mobile;
	@NotBlank(message="账户名称不能为空")
	private String accountName;
	@NotNull(message="账户类型不能为空")
	private Integer accountType; // 1 导游账户 2C端账户 3为黄包车 4为代理商账户
	@NotBlank(message="用户ID不能为空")
	private String userId;
	
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAreacode() {
		return areacode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

}
