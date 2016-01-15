/**
 * @Author lukangle
 * @2015年11月5日@上午10:58:10
 */
package com.hbc.api.fund.account.mapping.genx;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hbc.api.fund.account.mapping.gen.bean.FundAccount;

public interface WtFundAccountMapper {
	/**
	 * 更新 资金账户金额
	 * @param record
	 * @return
	 */
	@Update({
        "update fund_account",
        "set ",
          "amount = #{amount,jdbcType=DOUBLE},",
          "total_amount = #{totalAmount,jdbcType=DOUBLE}",
        "where account_no = #{accountNo,jdbcType=VARCHAR} and account_status=#{accountStatus}"
    })
    int updateAmount(FundAccount record);
	
	
	@Update({
        "update fund_account",
        "set ",
          "frozen_amount = #{frozenAmount,jdbcType=DOUBLE}",
        "where account_no = #{accountNo,jdbcType=VARCHAR} and account_status=#{accountStatus}"
    })
    int updateFrozenAmount(FundAccount record);
	
	@Select("select * from fund_account where account_no= #{accountNo} for update")
	public FundAccount forupdateByAccountNo(String accountNo);
}
