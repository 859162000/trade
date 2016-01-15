/**
 * Copyrights  2015  HuangBaoChe
 *
 * All rights reserved.
 *
 * Created on 2015年11月23日 下午1:36:23
 * 
 * @author HanZhaozhan
 *
 */
package com.hbc.api.trade.third;

/**
 * 
 * 航班注册参数
 *
 */
public class RegistFlightParam {

	
	private String fnum; // 航班号
	
	private String dep; // 出发地三字码
	
	private String arr; // 目的地三字码
	
	private String date; // 航班日期（yyyy-mm-dd格式，例2014-09-01）

	public String getFnum() {
		return fnum;
	}

	public void setFnum(String fnum) {
		this.fnum = fnum;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getArr() {
		return arr;
	}

	public void setArr(String arr) {
		this.arr = arr;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
