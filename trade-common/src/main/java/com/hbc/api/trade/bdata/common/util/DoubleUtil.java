/**
 * @Author lukangle
 * @2015年11月21日@上午11:38:02
 */
package com.hbc.api.trade.bdata.common.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class DoubleUtil {
	/**
	 * double 加法
	 * @param a
	 * @param b
	 * @return
	 */
	public static Double addDouble(Double a, Double b) {
		if(a==null){
			a = 0.0d;
		}
		
		if(b==null){
			b = 0.0d;
		}
		Double c = a + b;
		BigDecimal bc = new BigDecimal(c);
		Double f1 = bc.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	
	public static Double getNoFloat(Double a) {
		if(a==null){
			a = 0.0d;
		}
		
		Double c = a;
		BigDecimal bc = new BigDecimal(c);
		Double f1 = bc.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	
	public static Double getDoubleByTwo(Double a){
		BigDecimal bc = new BigDecimal(a);
		Double f1 = bc.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	
	public static Double addDouble(Double a, Double b, Double c) {
		Double f1 = addDouble(addDouble(a,b),c);
		return f1;
	}
	
	/**
	 * double 减法
	 * @param a
	 * @param b
	 * @return
	 */
	public static Double subtractionDouble(Double a, Double b) {
		if(a==null){
			a = 0.0d;
		}
		
		if(b==null){
			b = 0.0d;
		}
		Double c = a - b;
		BigDecimal bc = new BigDecimal(c);
		Double f1 = bc.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	/**
	 * double 乘法
	 * @param a
	 * @param b
	 * @return
	 */
	public static Double multiplicationDouble(Double a, Double b) {
		if(a==null){
			a = 0.0d;
		}
		
		if(b==null){
			b = 0.0d;
		}
		Double c = a * b;
		BigDecimal bc = new BigDecimal(c);
		Double f1 = bc.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	
	/**
	 * 计算百分比
	 * @param a 分子
	 * @param b 分母，为0有异常
	 * @param decimal 设置精确位数
	 * @return
	 */
	public static String percent(Double a, Double b, Integer decimal) {
		if(a == null) a = 0.0;
		if(b == null) b = 1.0;
		NumberFormat numberFormat = NumberFormat.getInstance();  
	    numberFormat.setMaximumFractionDigits(decimal);  
	    return numberFormat.format(a / b * 100) + "%"; 
	}
	
	/**
	 * 四舍五入运算
	 * @param number
	 * @return
	 */
	public static Integer to45(Double number) {
		return number == null ? null : new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
	}
}
