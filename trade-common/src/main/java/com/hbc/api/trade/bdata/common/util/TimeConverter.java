/**
 * @Author lukangle
 * @2015年10月7日@上午11:10:18
 */
package com.hbc.api.trade.bdata.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbc.api.trade.bdata.common.exception.ParamValidateException;
import com.hbc.api.trade.bdata.common.exception.enums.CommonReturnCodeEnum;

public class TimeConverter {
	private static final Logger log = LoggerFactory.getLogger(TimeConverter.class);

	/** yyyy-MM-dd HH:mm:ss */
	public static final String FULL_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
	public static final String INT_TIMESTAMP = "yyyyMMddHHmmss";
	/** yyyy-MM-dd */
	public static final String END_WITH_DATE = "yyyy-MM-dd";
	public static final String hour_data = "HH:mm:ss";
	public static Date converTime(String timestr, String... formattmp) throws ParseException {
		String pat1 = FULL_TIMESTAMP;
		if (formattmp != null && formattmp.length > 0) {
			pat1 = formattmp[0];
		}
		SimpleDateFormat sdf1 = new SimpleDateFormat(pat1);

		Date date = sdf1.parse(timestr);
		return date;
	}

	/**
	 * 
	 * @param dateTime
	 * @param pattern
	 *            格式，如yyyy-MM-dd HH:mm:ss， yyyy-MM-dd等
	 * @param fieldName
	 *            字段名。发生异常时，报告该字段值异常
	 * @return
	 */
	public static Date converTime(String dateTime, String pattern, String fieldName) {
		try {
			return TimeConverter.converTime(dateTime);
		} catch (ParseException e) {
			log.error(fieldName, e);
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR, fieldName);
		}
	}

	/**
	 * 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateTime
	 * @param fieldName
	 *            字段名。发生异常时，报告该字段值异常
	 * @return
	 */
	public static Date converTime(String dateTime, String fieldName) {
		return converTime(dateTime, FULL_TIMESTAMP, fieldName);
	}

	/**
	 * 把时间格式字符串转换为Date类型，当异常时抛出unchecked exception.
	 * 
	 * @param dateString
	 * @param format
	 * @return
	 */
	public static Date toDate(String dateString, String format) {
		if (StringUtils.isBlank(dateString))
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			log.error(e.getMessage());
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR, dateString);
		}
	}

	/**
	 * 把时间格式字符串转换为Date类型，当异常时抛出unchecked exception.
	 * 
	 * @param dateString
	 * @return The pattern is yyyy-MM-dd HH:mm:ss
	 */
	public static Date toDate(String dateString) {
		return toDate(dateString, FULL_TIMESTAMP);
	}

	public static Timestamp convertTimestamp(String dateString) {
		if (StringUtils.isBlank(dateString))
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(FULL_TIMESTAMP);
		try {
			Date date = dateFormat.parse(dateString);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
			log.error(e.getMessage());
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR, dateString);
		}
		
	}
	
	public static Timestamp convertTimestampD(String dateString) {
		if (StringUtils.isBlank(dateString))
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(END_WITH_DATE);
		try {
			Date date = dateFormat.parse(dateString);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
			log.error(e.getMessage());
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR, dateString);
		}
		
	}

	/**
	 * 
	 * @param date
	 * @param pattern
	 *            yyyy-MM-dd HH:mm:ss, if null
	 * @return null, if ${date} is null
	 */
	public static final String formatDate(Date date, String pattern) {
		if (date == null)
			return null;
		if (pattern == null)
			pattern = FULL_TIMESTAMP;
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 
	 * @param date
	 * @return null, if ${date} is null
	 */
	public static final String formatDateToInt(Date date) {
		if (date == null)
			return null;
		return new SimpleDateFormat(INT_TIMESTAMP).format(date);
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
