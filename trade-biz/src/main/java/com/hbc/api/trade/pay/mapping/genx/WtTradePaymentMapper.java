/**
 * @Author lukangle
 * @2015年10月22日@下午8:39:00
 */
package com.hbc.api.trade.pay.mapping.genx;

import org.apache.ibatis.annotations.Update;

import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;

public interface WtTradePaymentMapper {
	@Update({
        "update trade_payment",
        "set ",
          "pay_status = #{payStatus,jdbcType=INTEGER},",
          "third_notify_status = #{thirdNotifyStatus,jdbcType=VARCHAR}",
        "where pay_no = #{payNo,jdbcType=VARCHAR}"
    })
    int updatePayStatus(TradePayment record);
	
	
	  @Update({
	        "update trade_payment",
	        "set ",
	          "pay_status = #{payStatus,jdbcType=INTEGER},",
	          "pay_fee = #{payFee,jdbcType=DOUBLE},",
	          "pay_time = #{payTime,jdbcType=TIMESTAMP},",
	          "user_mobile = #{userMobile,jdbcType=VARCHAR},",
	          "user_id = #{userId,jdbcType=VARCHAR},",
	          "user_name = #{userName,jdbcType=VARCHAR},",
	          "pay_desc = #{payDesc,jdbcType=VARCHAR},",
	          "third_pay_no = #{thirdPayNo,jdbcType=VARCHAR},",
	          "third_notify_status = #{thirdNotifyStatus,jdbcType=VARCHAR},",
	          "third_notify_log = #{thirdNotifyLog,jdbcType=LONGVARCHAR}",
	        "where pay_no = #{payNo,jdbcType=VARCHAR}"
	    })
	    int updateByPrimaryKeyWithBLOBs(TradePayment record);
}
