/**
 * @Author lukangle
 * @2015年11月28日@下午2:26:21
 */
package com.hbc.api.trade.bdata.common.rsp;

import java.util.List;

public class ListServiceRsp<T> {
	private List<T> datalist;
	private int tsize;
	public List<T> getDatalist() {
		return datalist;
	}
	public void setDatalist(List<T> datalist) {
		this.datalist = datalist;
	}
	public int getTsize() {
		return tsize;
	}
	public void setTsize(int tsize) {
		this.tsize = tsize;
	}
	
}
