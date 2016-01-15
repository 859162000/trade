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
package com.hbc.api.trade.order.enums.order;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hbc.api.trade.order.enums.third.SMSChannel;

/**
 * @author Luoshuai
 *
 */
public class AgentCategory {

	private static final String[] ZHIKE = new String[] { "纯粹旅行", "测试公司", "皇包车淘宝店", "纯粹旅行皇包车", "皇包车天猫店", "去哪儿（车车）", "去哪儿（当地人）", "皇包车微信", "去哪儿" /* api */, "淘在路上", "皇包车淘宝企业店", "蚂蜂窝", "携程当地玩乐", "面包旅行", "皇包车旅游旗舰店", "爱旅行" };

	private static final String[] HANGLV = new String[] { "卧客商旅", "游谱旅行", "趣旅网", "T-mice", "度假客", "四川易飞易旅游专营店", "百利天下出国留学",
			"上海天御票务服务有限公司", "深圳盼游旅行社", "iTrip.com爱去自由", "贵阳三泰航空服务有限公司", "浙江环途票务服务有限公司", "广州一起飞国际旅行社有限公司", "北京游谱科技发展有限公司",
			"深圳侨中国际旅行社有限公司", "深圳市淘游天下电子商务有限公司", "沈阳立华航空服务有限公司", "沈阳天一航空服务有限责任公司", "北京云杰祥航空服务有限公司", "上海麦途国际旅行社有限公司",
			"辽宁祥瑞国际旅行社有限公司", "北京汉鼎国际会议服务有限公司", "北京联合国际旅行社有限公司", "北京海坤新旅资产管理有限公司", "北京保盛航空服务有限公司", "北京亚美运通国际旅行社有限公司",
			"北京广信国际旅行社有限公司", "北京迈途国际旅行社有限公司", "北京逍遥行国际旅行社有限公司", "深圳市八星国际旅行社有限公司", "昆明行游网商务有限公司", "黑龙江商旅国际旅行社有限公司",
			"北京安捷之旅国际旅行社有限公司", "北京鲲鹏之旅航空服务有限公司", "北京和平天下国际旅行社有限公司", "北京王府国际旅行社有限公司", "北京王府国际旅行社有限公司（美洲部）",
			"深圳市职工国际旅行社有限公司", "空港（深圳）国际旅行社有限公司", "上海天御票务服务有限公司（北京分公司）", "北京联合国际旅行社有限公司（上海分部）", "四川省中国青年旅行社有限公司（成都风筝）",
			"天津市银达金融旅行社有限公司", "深圳南游记国际旅行社有限公司", "江苏舜天海外", "北京假期国际旅行社有限公司上海博达分公司", "广东中旅假日国际旅行社有限公司", "上海中宝国际旅行社有限公司",
			"上海兹悠网络科技有限公司", "航旅分销", "北京万延航空有限公司河南分公司", "中国天鹅国际旅游公司", "深圳金鹏工贸有限责任公司", "我要游学（北京）科技有限公司", "北京万延旅游股份有限公司",
			"北京遛弯科技有限公司" };

	private static final String[] NONE = new String[] { "携程海外接送机" };

	private static List<String> ZHIKES = Arrays.asList(ZHIKE);
	private static List<String> HANGLVS = Arrays.asList(HANGLV);
	private static List<String> NONES = Arrays.asList(NONE);

	public static SMSChannel getChannelCategory(String agentName) {
		if (StringUtils.isBlank(agentName)) {
			return SMSChannel.INVALID;
		}

		if (ZHIKES != null && ZHIKES.contains(agentName)) {
			return SMSChannel.zhixiao;
		} else if (NONES != null && NONES.contains(agentName)) {
			return SMSChannel.FORBIDDEN;
		} else {
			return SMSChannel.fenxiao;
		}
	}
}
