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
package com.hbc.api.fund.biz.mapping.genx.xbean;

/**
 * @author LuoShuai
 *
 */
public class FundWithDrawQueryBean extends AbstractParameter {

	public static final int UNPROCESS = 0;
	public static final int PROCESSED = 1;
	public static final int AUTO_PROC = 2;

	private Integer processStatus; //0 1 2
	private String guideNo;
	private String guideName;
	private Integer drawStatus;

	public Integer getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}

	public String getGuideNo() {
		return guideNo;
	}

	public void setGuideNo(String guideNo) {
		this.guideNo = guideNo;
	}

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public Integer getDrawStatus() {
		return drawStatus;
	}

	public void setDrawStatus(Integer drawStatus) {
		this.drawStatus = drawStatus;
	}

	@Override
	public String toString() {
		return "FundWithDrawQueryBean [processStatus=" + processStatus + ", guideNo=" + guideNo + ", guideName=" + guideName + ", drawStatus=" + drawStatus + "]";
	}

}
