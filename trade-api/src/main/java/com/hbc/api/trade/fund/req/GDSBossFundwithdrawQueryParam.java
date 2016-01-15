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
package com.hbc.api.trade.fund.req;

import java.io.Serializable;
import java.util.List;

import com.hbc.api.fund.biz.mapping.genx.xbean.BossTransferGuideInfo;

/**
 * @author LuoShuai
 *
 */
public class GDSBossFundwithdrawQueryParam implements Serializable{
	
	private static final long serialVersionUID = 7764137158058302893L;

	private List<BossTransferGuideInfo> bossTransferGuideInfos;
	private Double totalAmount;
	private String targetBossGuideId;
	private String targetAccountNo;
	private String targetBankNo;
	private String optId;
	private String optName;

	public List<BossTransferGuideInfo> getBossTransferGuideInfos() {
		return bossTransferGuideInfos;
	}

	public void setBossTransferGuideInfos(List<BossTransferGuideInfo> bossTransferGuideInfos) {
		this.bossTransferGuideInfos = bossTransferGuideInfos;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTargetBossGuideId() {
		return targetBossGuideId;
	}

	public void setTargetBossGuideId(String targetBossGuideId) {
		this.targetBossGuideId = targetBossGuideId;
	}

	public String getTargetAccountNo() {
		return targetAccountNo;
	}

	public void setTargetAccountNo(String targetAccountNo) {
		this.targetAccountNo = targetAccountNo;
	}

	public String getTargetBankNo() {
		return targetBankNo;
	}

	public void setTargetBankNo(String targetBankNo) {
		this.targetBankNo = targetBankNo;
	}

	public String getOptId() {
		return optId;
	}

	public void setOptId(String optId) {
		this.optId = optId;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	@Override
	public String toString() {
		return "GDSBossFundwithdrawQueryParam [bossTransferGuideInfos=" + bossTransferGuideInfos + ", totalAmount=" + totalAmount + ", targetBossGuideId=" + targetBossGuideId + ", targetAccountNo=" + targetAccountNo + ", targetBankNo="
				+ targetBankNo + ", optId=" + optId + ", optName=" + optName + "]";
	}

}
