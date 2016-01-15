package com.hbc.api.trade.third;

import java.util.Arrays;
import java.util.Map;

public class PushParam {

	// 推送方式，1-导游ID，2-用户ID,，3-别名alias
	private Integer push_type;
	// 根据推送终端的不同，分别传入 1-导游ID，2-用户ID，3-设备别名
	private String[] push_objects;
	// 推送标题，预留字段，可空，供android推送修改默认通知栏标题
	private String title;
	// 使用的推送模板ID
	private Integer template_id;
	// 如果选择的模板有参数，则必填模板的参数列表，会被按照顺序填充入模板
	private String[] params;
	// 可选，推送附加参数，作为推送的附加数据发送给App，订单可将订单编号等放入该字段
	private Map<String, String> extras;

	public Integer getPush_type() {
		return push_type;
	}

	public void setPush_type(Integer push_type) {
		this.push_type = push_type;
	}

	public String[] getPush_objects() {
		return push_objects;
	}

	public void setPush_objects(String[] push_objects) {
		this.push_objects = push_objects;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(Integer template_id) {
		this.template_id = template_id;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public Map<String, String> getExtras() {
		return extras;
	}

	public void setExtras(Map<String, String> extras) {
		this.extras = extras;
	}

	@Override
	public String toString() {
		return "PushParam{" +
				"push_type=" + push_type +
				", push_objects=" + Arrays.toString(push_objects) +
				", title='" + title + '\'' +
				", template_id=" + template_id +
				", params=" + Arrays.toString(params) +
				", extras=" + extras +
				'}';
	}
}
