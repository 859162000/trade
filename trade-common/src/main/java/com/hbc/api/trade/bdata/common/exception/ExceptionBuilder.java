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
package com.hbc.api.trade.bdata.common.exception;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * @author LuoShuai
 */
@Component
public class ExceptionBuilder {

	private static final String RESPONSE_MESSAGE_PREFIX = "api.response.code.";

	private static final Charset CHARSET = Charset.forName("UTF-8");

	private static final Pattern LANG_PATTERN = Pattern.compile("([a-z])([a-z])(-)([A-Z])([A-Z])");

	@Autowired
	private MessageSource messageSource;

	public String build(int code, String language) {
		if (isValid(language)) {
			String[] langReg = new String[]{};
//			String[] langReg = StringUtils.split(language, "-");
			Locale local = this.findLocal(langReg[0], langReg[1]);
			return build(code, local);
		} else {
			throw new IllegalArgumentException("Language formate exception!!");
		}
	}

	public String build(int code, Locale local) {
		String message = messageSource.getMessage(new StringBuilder().append(RESPONSE_MESSAGE_PREFIX).append(code).toString(), null, local);
		return new String(message.getBytes(CHARSET));
	}

	private Locale findLocal(String language, String region) {
		return new Locale.Builder().setLanguage(language).setRegion(region).build();
	}

	private boolean isValid(String lang) {
		return LANG_PATTERN.matcher(lang).matches();
	}

}
