package com.hbc.api.trade.ota.mapping.genx;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;

public interface UpdateTradeThirdOrderMapper {

	@Select("select * from trade_third_order where order_no= #{orderNo, jdbcType=VARCHAR} for update")
	public TradeThirdOrder forupdateById(String orderNo);
	
	@Update({
        "update trade_third_order",
        "set order_status = #{orderStatus, jdbcType=INTEGER}",
        "where order_no = #{orderNo, jdbcType=VARCHAR}"
    })
    int updateStatusByPrimaryKey(TradeThirdOrder record);

	@Update({
        "update trade_third_order",
        "set guide_confirm_time = #{guideConfirmTime, jdbcType=DATE}",
        "where order_no = #{orderNo, jdbcType=VARCHAR}"
	})
	int updateConfirmTimeByPrimaryKey(TradeThirdOrder record);
}