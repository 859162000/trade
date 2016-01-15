/*
 * Copyright (c) 2015-2016, CCLX.COM. All rights reserved.
 * WANDA GROUP PROPRIETARY/CONFIDENTIAL. 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is private property; you can't redistribute it and/or modify it
 * under the terms of the LICENSE you obtained from
 *
 *    http://www.cclx.com/
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * Author: Jongly Ran
 * Revision: 1.0
 */
package com.hbc.api.trade.bdata.common;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hbc.common.conn.ConfigLoader;

/**
 * @author Jongly Ran
 */
@Service
public class DownloadConfigService {
	private final static Logger logger = LoggerFactory.getLogger(DownloadConfigService.class);

	/**
	 * https 协议，默认大图
	 * @param relativePath
	 * @return
	 */
	public String getFullPath(String relativePath) {
		if(relativePath != null) {
			String downloadHost = ConfigLoader.getProp("host.download");
			if(StringUtils.isBlank(downloadHost)) {
				downloadHost = "";
				logger.info("获取下载的远程URL host失败");
			}
			String fullPath = downloadHost.concat("/").concat(relativePath);
			logger.info("获取下载的远程URL：" + fullPath);
			return fullPath;
		}
		return null;
	}
	
	/**
	 * http协议，默认小图
	 * @param avatar
	 * @return
	 */
	public String getGuideAvatar(String avatar) {
		if(avatar != null) {
			avatar = getFullPath(avatar).replaceAll("https", "http");
			logger.info("车导头像绝对路径：" + avatar);
			int endIndex = avatar.lastIndexOf(File.separator);
			String prefix = avatar.substring(0, endIndex + 1);
			String postfix = avatar.substring(endIndex + 1);
			return  prefix + "s_"+postfix;
		}
		return null;
	}
	
	/**
	 * http协议，默认小图
	 * @param avatar
	 * @return
	 */
	public String getUserSmallAvatar(String avatar) {
		String userAvatar = getGuideAvatar(avatar);
		logger.info("用户头像绝对路径：" + userAvatar);
		return userAvatar;
	}
}
