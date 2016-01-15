/**
 * @Author lukangle
 * @2015年11月18日@下午5:56:20
 */
package com.hbc.api.fund.account.parm;

import java.util.List;

import javax.validation.constraints.Pattern;

public class QAccountLogParam {
	@Pattern(regexp = "^\\d{4}[-]?\\d{1,2}[-]?\\d{1,2}$",message="开始时间格式不对 yyyy-MM-dd")
	private String startTime;
	@Pattern(regexp = "^\\d{4}[-]?\\d{1,2}[-]?\\d{1,2}$",message="结束时间格式不对 yyyy-MM-dd")
	private String endTime;
	private String userId;
	private String userName;
	
	private List<Integer> bizTypeList;
	private Integer limit=10;
	private Integer offset=0;
	
	/**
	 * @return the bizTypeList
	 */
	public List<Integer> getBizTypeList() {
		return bizTypeList;
	}
	/**
	 * @param bizTypeList the bizTypeList to set
	 */
	public void setBizTypeList(List<Integer> bizTypeList) {
		this.bizTypeList = bizTypeList;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
}
