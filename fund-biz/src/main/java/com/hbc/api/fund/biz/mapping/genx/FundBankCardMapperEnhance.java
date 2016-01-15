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

import com.hbc.api.fund.biz.mapping.gen.bean.FundBankCard;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundBankCardQueryBean;

/**
 * @author Jongly Ran
 */
public interface FundBankCardMapperEnhance {

	@Select({"<script>",
		"select * from fund_bank_card where status=1 and guide_id=#{guideId}",
		"<if test='guideAgencyId'> and guide_agency_id=#{guideAgencyId}</if>",
		" order by create_time desc limit #{offset}, #{limit} ",
	"</script>"})
	List<FundBankCard> selectFundBankCardList(FundBankCardQueryBean queryBean);
	
	@Select({"<script>",
		"select count(1) from fund_bank_card where status=1 and  guide_id=#{guideId} ",
		"<if test='guideAgencyId'> and guide_agency_id=#{guideAgencyId}</if>",
		" order by create_time desc limit #{offset}, #{limit} ",
	"</script>"})
	int selectFundBankCardListTotalSize(FundBankCardQueryBean queryBean);

	@Update("update fund_bank_card set status=0 where bank_no=#{bankNo}")
	int deleteFundBankCard(@Param("bankNo") String bankNo);
}
