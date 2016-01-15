/*
 * COPYRIGHT (C) 2015-2016,LUOSHUAI. ALL RIGHTS RESERVED.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files
 * (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, 
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions: 
 *
 *   a).The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software!
 *   b).Any individual or entity would be granted by LUOSHUAI before using this Software!
 *  
 * Please contact through email luoshuai@live.com if you need additional informations OR have any questions.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * Author: Luoshuai 
 * Revision: 1.0
 * 
 *  
 */
package com.hbc.api.gateway.alizhifu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.gateway.alizhifu.config.AlipayConfig;
import com.hbc.api.gateway.alizhifu.sign.AliPayMD5;

/**
 * @author Luoshuai
 *
 */
@Component
public class AlipayCallBackValidator {

	@Autowired
	private AlipayConfig alipayConfig;

	private final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";

	private static final String SIGN_TYPE_OF_MD5 = "MD5";
	private static final String SIGN_TYPE_OF_RSA = "RSA";
	private static final String SIGN_ENCODING = "utf-8";

	public boolean signValidate(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String, String> parmmap = this.getParameterMap(request);
		Map<String, String> filterMap = this.parmFilter(parmmap);
		String cleanLink = this.createLinkString(filterMap);

		String alisign = request.getParameter("sign");
		String signType = request.getParameter("sign_type");
		if (StringUtils.isBlank(alisign) || StringUtils.isBlank(signType)) {
			return Boolean.FALSE;
		}

		if (signType.equalsIgnoreCase(SIGN_TYPE_OF_MD5)) {
			return AliPayMD5.verify(cleanLink, alisign, alipayConfig.securityCode, SIGN_ENCODING);
		} else if (signType.equalsIgnoreCase(SIGN_TYPE_OF_RSA)) {
			StringBuffer spayurl = new StringBuffer();
			for (String keystr : filterMap.keySet()) {
				spayurl = spayurl.append(keystr + "=" + URLDecoder.decode(filterMap.get(keystr), "UTF-8") + "&");
			}
			String dspayurl = spayurl.substring(0, spayurl.length() - 1);
			//mysign = RSA.sign(dspayurl, alipayConfig.privateKey, "UTF-8");
		}
		return Boolean.FALSE;
	}

	public boolean identifyValidate(HttpServletRequest request) {
		return Boolean.FALSE;
	}

	public boolean dataValidate(HttpServletRequest request) {
		return Boolean.FALSE;
	}

	public String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) { //拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}

	public Map<String, String> parmFilter(Map<String, String> fullArray) {
		Map<String, String> result = new HashMap<String, String>();

		if (fullArray != null && fullArray.size() > 0) {
			for (String key : fullArray.keySet()) {
				String value = fullArray.get(key);
				if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
						|| key.equalsIgnoreCase("sign_type")) {
					continue;
				}
				result.put(key, value);
			}
			return result;
		} else {
			return result;
		}
	}

	public static Map<String, String> getParameterMap(HttpServletRequest request) {
		Set<String> keyset = request.getParameterMap().keySet();
		Map<String, String> result = new HashMap<String, String>();
		for (String key : keyset) {
			String[] values = request.getParameterValues(key);
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				result.put(key, value);
			}
		}
		return result;
	}
	

	
}
