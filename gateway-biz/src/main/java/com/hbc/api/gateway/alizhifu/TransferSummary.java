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
package com.hbc.api.gateway.alizhifu;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hbc.api.gateway.alizhifu.req.TransInfo;
import com.hbc.api.gateway.enums.GatewayReturnCodeEnum;
import com.hbc.api.gateway.exception.GatewayException;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;

/**
 * @author LuoShuai
 *
 */
public class TransferSummary {

	private Double totalTransferAmount;
	private String description;

	public Double getTotalTransferAmount() {
		return totalTransferAmount;
	}

	public void setTotalTransferAmount(Double totalTransferAmount) {
		this.totalTransferAmount = totalTransferAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static String getTransferDetails(List<TransInfo> transInfoList, Double totalAmount) {
		StringBuilder transferDetails = new StringBuilder();
		final String comm = "|";
		Double checkPrice = 0.00d;
		for (TransInfo transInfo : transInfoList) {
			checkPrice = DoubleUtil.addDouble(checkPrice, transInfo.getActualAmount());
			transferDetails.append(transInfo.getTransNo()).append("^").append(transInfo.getPayeeAccount()).append("^")
					.append(transInfo.getPayeeName()).append("^").append(transInfo.getActualAmount()).append("^").append(transInfo.getTransSubject()).append(comm);
		}
		if (!totalAmount.equals(checkPrice)) {
			throw new GatewayException(GatewayReturnCodeEnum.ERR_TRANS_PRICE_NOT_MATCH);
		}
		return StringUtils.removeEnd(transferDetails.toString(), comm);
	}

	@Override
	public String toString() {
		return "TransferSummary [totalTransferAmount=" + totalTransferAmount + ", description=" + description + "]";
	}
}
