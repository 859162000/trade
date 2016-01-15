package com.hbc.data.trade.transfer.service.account;

public class InvalidAccount {

	private String id;

	private String type;

	private String name;

	private String dbValue;

	private String sumValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDbValue() {
		return dbValue;
	}

	public void setDbValue(String dbValue) {
		this.dbValue = dbValue;
	}

	public String getSumValue() {
		return sumValue;
	}

	public void setSumValue(String sumValue) {
		this.sumValue = sumValue;
	}

	public String toString() {
		return "[ id:" + id + ", 名称: " + name + ", type:" + type + ", 账号值:" + dbValue + ", 流水累加值:" + sumValue + " ]";
	}
}
