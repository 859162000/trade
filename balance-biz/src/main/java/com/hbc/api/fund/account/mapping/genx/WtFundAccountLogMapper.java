/**
 * @Author lukangle
 * @2015年11月18日@下午5:42:57
 */
package com.hbc.api.fund.account.mapping.genx;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.hbc.api.fund.account.mapping.gen.bean.FundAccountLog;
import com.hbc.api.fund.account.parm.QAccountLogParam;

public interface WtFundAccountLogMapper {

	@Select({ "<script>", 
		" select * from fund_account_log where 1=1  ",
		"<if test='startTime != null'> and biz_time&gt;='${startTime} 00:00:00' </if>",
		"<if test='endTime != null'> and biz_time&lt;='${endTime} 23:59:59' </if>",
		"<if test='userId != null'>   and user_id like '%${userId}%' </if>", 
		"<if test='userName != null'> and user_name like '%${userName}%'</if>", 
		"<if test='bizTypeList != null'>",
			"and biz_status > 0 and biz_type in ",
        "<foreach item='item' collection='bizTypeList' open='(' separator=',' close=')'> #{item} </foreach>",
        "</if>",
		"order by create_time desc limit ${offset}, ${limit} ",
		"</script>" })
	
	List<FundAccountLog> selectOrderLogs(QAccountLogParam param);
	
	@Select({ "<script>", 
		" select count(1) from fund_account_log where 1=1  ",
		"<if test='startTime != null'> and biz_time&gt;='${startTime} 00:00:00' </if>",
		"<if test='endTime != null'> and biz_time&lt;='${endTime} 23:59:59' </if>",
		"<if test='userId != null'>   and user_id like '%${userId}%' </if>", 
		"<if test='userName != null'> and user_name like '%${userName}%'</if>", 
		"<if test='bizTypeList != null'>",
			"and biz_status > 0 and biz_type in ",
        "<foreach item='item' collection='bizTypeList' open='(' separator=',' close=')'> #{item} </foreach>",
        "</if>",
		"order by create_time desc  ",
		"</script>" })
	
	int selectOrderLogsTotalSize(QAccountLogParam param);
}
