package com.hbc.api.trade.bdata.common.util;

public class SystemContext {
	public SystemContext() {

	}

	public String QueSystemContext() {
		String str = Thread.currentThread().getContextClassLoader().getResource("").toString();
		str = str.substring(6, str.length());
		str = str.replaceAll("%20", " ");
		int num = str.indexOf("WEB-INF");
		if (num == -1) {
			return null;
		}
		str = str.substring(0, num);

		if (str.charAt(num - 1) == '/') {
			str = "/" + str;
		}

		return str;
	}
}
