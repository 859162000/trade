package com.hbc.api.trade.order.mapping.genx.deliver;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeGuideAttitudinal;

public interface GxGuideAttitudinalMapper {
//	@Select("select * from trade_deliver_guide where  allocat_gno = '${allocatGno}' for update")
//	public List<TradeGuideAttitudinal> getGuideAttitudinals(@Param("allocatGno") String guideIds);
	
	 @Update({
	        "update trade_guide_attitudinal",
	        "set serial_fail_times = #{serialFailTimes,jdbcType=INTEGER},",
	          "serial_fail_days = #{serialFailDays,jdbcType=INTEGER},",
	          "today_fail_times = #{todayFailTimes,jdbcType=INTEGER},",
	          "today_deal_times = #{todayDealTimes,jdbcType=INTEGER},",
	          "accumulative_fail_times = #{accumulativeFailTimes,jdbcType=INTEGER},",
	          "accumulative_fail_days = #{accumulativeFailDays,jdbcType=INTEGER},",
	          "accumulative_deal_orders = #{accumulativeDealOrders,jdbcType=INTEGER},",
	          "accumulative_expect_times = #{accumulativeExpectTimes,jdbcType=INTEGER},",
	          "accumulative_demand_times = #{accumulativeDemandTimes,jdbcType=INTEGER},",
	          "reptdt = #{reptdt,jdbcType=TIMESTAMP},",
	          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
	          "create_time = #{createTime,jdbcType=TIMESTAMP}",
	        "where guide_id = #{guideId,jdbcType=INTEGER}"
	    })
	    int updateByPrimaryKey(TradeGuideAttitudinal record);
}
