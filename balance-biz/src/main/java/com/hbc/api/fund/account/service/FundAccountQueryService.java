/**
 * @Author lukangle
 * @2015年11月18日@下午2:04:57
 */
package com.hbc.api.fund.account.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbc.api.fund.account.mapping.gen.FundAccountLogMapper;
import com.hbc.api.fund.account.mapping.gen.FundAccountMapper;
import com.hbc.api.fund.account.mapping.genx.WtFundAccountMapper;

public class FundAccountQueryService {
	@Autowired
	FundAccountLogMapper fundAccountLogMapper;
	@Autowired
	FundAccountMapper fundAccountMapper;
	@Autowired
	WtFundAccountMapper wtFundAccountMapper;
	
}
