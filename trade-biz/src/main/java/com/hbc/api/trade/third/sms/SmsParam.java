package com.hbc.api.trade.third.sms;

public class SmsParam {
	// 短信区号
	private String area_code;
	// 短信号码
	private String mobile;
	// 如果选择的模板有参数，则必填模板的参数列表，会被按照顺序填充入模板
	private String[] params;	
	// 调用方名字
	private String sys_name;
	// 使用的发送短信的模板ID
	private Integer template_id;
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String[] getParams() {
		return params;
	}
	public void setParams(String[] params) {
		this.params = params;
	}
	public String getSys_name() {
		return sys_name;
	}
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}
	public Integer getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(Integer template_id) {
		this.template_id = template_id;
	}
	
}
