/**
 * @Author lukangle
 * @2015年11月21日@上午11:30:39
 */
package com.hbc.api.trade.timer.core;

import com.hbc.api.trade.bdata.common.util.DoubleUtil;

public class AA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Double a = 0.00d;
		for(int i=0;i<10;i++){
			a = DoubleUtil.addDouble(a, 0.01d);
			System.out.println(a); 
		}
		System.out.println(a); 
		
		System.out.println(DoubleUtil.subtractionDouble(a, 0.01d)); 
	}

}
