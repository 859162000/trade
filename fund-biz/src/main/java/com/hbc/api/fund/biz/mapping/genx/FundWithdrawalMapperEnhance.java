/*
 * Copyright (c) 2015-2016, CCLX.COM. All rights reserved.
 * WANDA GROUP PROPRIETARY/CONFIDENTIAL. 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is private property; you can't redistribute it and/or modify it
 * under the terms of the LICENSE you obtained from
 *
 *    http://www.cclx.com/
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * Author: Jongly Ran
 * Revision: 1.0
 */
package com.hbc.api.fund.biz.mapping.genx;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawal;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundWithdrawalQueryBean;

/**
 * @author Jongly Ran
 */
public interface FundWithdrawalMapperEnhance {
	
	@Update("update fund_withdraw set draw_status=-1 where draw_no=#{drawNo}")
	public int disagree(@Param("drawNo") String drawNo);
	
	@Select({"<script>",
		"select * from fund_withdraw where guide_id=#{guideId}",
		"<if test='finAccount'> and fin_account=#{finAccount}</if>",
		"<if test='drawStatus'> and draw_status=#{drawStatus}</if>",
		"<if test='drawStatusList != null'>",
		" and draw_status in ",
		"<foreach item='item' collection='drawStatusList' open='(' separator=',' close=')'> #{item} </foreach>",
		"</if>",
		" order by create_time desc limit #{offset}, #{limit} ",
	"</script>"})
	List<FundWithdrawal> selectFundWithdrawalList(FundWithdrawalQueryBean queryBean);
	
	@Select({"<script>",
		"select count(1) from fund_withdraw where guide_id=#{guideId}",
		"<if test='finAccount'> and fin_account=#{finAccount}</if>",
		"<if test='drawStatus'> and draw_status=#{drawStatus}</if>",
		"<if test='drawStatusList != null'>",
		" and draw_status in ",
		"<foreach item='item' collection='drawStatusList' open='(' separator=',' close=')'> #{item} </foreach>",
		"</if>",
	"</script>"})
	int selectFundWithdrawalListTotalSize(FundWithdrawalQueryBean queryBean);

	@Select("SELECT sum(price) price FROM fund_withdraw where guide_id=#{guideId}")
	double getFundWithdrawalTotalAmount(String guideId);

	@Select("select * from fund_withdraw f where f.apply_time = (select MAX(apply_time) as apply_time from fund_withdraw WHERE fin_account = #{accountNo})")
	public List<FundWithdrawal> getLatestFundWithdraw(String accountNo);

	@Select("select * from fund_withdraw where draw_no = #{drawNo} for update")
	public FundWithdrawal getFundWithdrawForUpdate(String drawNo);
}
