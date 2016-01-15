/**
 * @Author lukangle
 * @2015年10月22日@上午11:38:59
 */
package com.hbc.api.trade.pay.mapping.genx;

import org.apache.ibatis.annotations.Update;

import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefund;

public interface WtRefundMapper {

    @Update({
        "update trade_refund",
        "set ",
          "refund_status = #{refundStatus,jdbcType=INTEGER},",
          "refund_subject = #{refundSubject,jdbcType=VARCHAR},",
          "refund_desc = #{refundDesc,jdbcType=VARCHAR},",
          "refund_detail = #{refundDetail,jdbcType=VARCHAR},",
          "refund_response = #{refundResponse,jdbcType=VARCHAR},",
          "refund_error = #{refundError,jdbcType=VARCHAR}",
        "where refund_no = #{refundNo,jdbcType=VARCHAR}"
    })
    int updateNotifyByPrimaryKey(TradeRefund record);
}
