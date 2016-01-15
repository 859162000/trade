package com.hbc.api.trade.third.push;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/23.
 */
public class TemplatePushIn {

	// 1-导游NO 2-用户ID 3-设备别名
	private Integer applyType;
	// 发送目标ID，由applyType类型决定，英文逗号分隔
	private String applyIds;
	// 模板id
	private Integer templateId;
	// 模板参数
	private String[] params;
	// 可选，推送附加参数，作为推送的附加数据发送给App，订单可将订单编号等放入该字段
	private Map<String, String> extras;

	public Map<String, String> getExtras() {
		return extras;
	}

	public void setExtras(Map<String, String> extras) {
		this.extras = extras;
	}

	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}

	public String getApplyIds() {
		return applyIds;
	}

	public void setApplyIds(String applyIds) {
		this.applyIds = applyIds;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "TemplatePushIn{" + "applyType=" + applyType + ", applyIds='" + applyIds + '\'' + ", templateId=" + templateId + ", params=" + Arrays.toString(params) + '}';
	}
}
