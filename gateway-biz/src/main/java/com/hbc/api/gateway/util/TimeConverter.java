/**
 * @Author lukangle
 * @2015年10月7日@上午11:10:18
 */
package com.hbc.api.gateway.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverter {
	/** yyyy-MM-dd HH:mm:ss */
	public static final String FULL_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
	
	/** yyyy-MM-dd */
	public static final String END_WITH_DATE = "yyyy-MM-dd";
	
	
	public static Date converTime(String timestr, String... formattmp) throws ParseException {
		String pat1 = FULL_TIMESTAMP;
		if (formattmp != null && formattmp.length>0) {
			pat1 = formattmp[0];
		}
		SimpleDateFormat sdf1 = new SimpleDateFormat(pat1);

		Date date = sdf1.parse(timestr);
		return date;
	}
	
	public static final String formatDate(Date date, String pattern) {
		if (date == null) 
			return null;
	    if (pattern == null) 
	    	pattern = FULL_TIMESTAMP;
        return new SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * @param date
	 * @return format: yyyy-MM-dd HH:mm:ss
	 */
	public static final String formatDate(Date date) {
		return formatDate(date, FULL_TIMESTAMP);
	}
	
	/**
	 * @param date
	 * @return format: yyyy-MM-dd
	 */
	public static final String formatDates(Date date) {
		return formatDate(date, END_WITH_DATE);
	}
}
