package com.hbc.api.trade.bdata.common;



/** 
* @Description: Page类
* @author BRUCE
* @date 2014年11月5日 下午8:05:07 
* 
*/
public class Page {
	private int offset;
	// 每页显示记录数
	private int limit;
	
	/**
	 * 构造函数
	 * 
	 * @param begin
	 * @param length
	 */
	public Page(int offset, int limit) {
		this.offset = offset;
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
