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
package com.hbc.api.trade.order.mapping.genx;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.genx.xbean.TradeDeliverGuideParamBean;
import com.hbc.api.trade.order.mapping.genx.xbean.TradeDeliverGuideQueryBean;
import com.hbc.api.trade.order.mapping.genx.xbean.TradeDeliverGuideVoBean;

/**
 * @author Jongly Ran
 */
public interface TradeDeliverGuideMapperEnhance {
	@Select("select * from trade_deliver_guide where order_no = #{orderNo} order by service_time limit #{offset}, #{limit} ")
	List<TradeDeliverGuide> selectDeliverGuides(TradeDeliverGuideQueryBean queryBean);

	@Select("select count(1) from trade_deliver_guide where order_no = #{orderNo} order by service_time limit #{offset}, #{limit} ")
	int selectDeliverGuidesTotalSize(TradeDeliverGuideQueryBean queryBean);

	@Select({ "<script>",
			"select * from trade_deliver_guide where guide_id = #{guideId} and demand_deal &lt; 2 and is_readable=1 ",
			"<if test='deliverStatusList != null'>",
			"and deliver_status in ",
			"<foreach item='item' collection='deliverStatusList' open='(' separator=',' close=')'> #{item} </foreach>",
			"</if>",
			"order by service_time limit #{offset}, #{limit} ",
			"</script>" })
	List<TradeDeliverGuide> selectPurposeOrders(TradeDeliverGuideQueryBean queryBean);

	@Select({ "<script>",
			"select count(1) from trade_deliver_guide where guide_id = #{guideId} and demand_deal &lt; 2 and is_readable=1 ",
			"<if test='deliverStatusList != null'>",
			"and deliver_status in ",
			"<foreach item='item' collection='deliverStatusList' open='(' separator=',' close=')'> #{item} </foreach>",
			"</if>",
			"</script>" })
	int selectPurposeOrdersTotalSize(TradeDeliverGuideQueryBean queryBean);

	@Select("select * from trade_deliver_guide where allocat_gno = #{allocateGid}")
	TradeDeliverGuide selectOne(@Param("allocateGid") String allocateGid);

	/**
	 * @param paramBean
	 *            参数对象
	 *            <p>
	 *            同意接单，必须字段：demandDeal，allocateGuideId，orderNo，updateTime，
	 *            acceptTime
	 *            </p>
	 *            <p>
	 *            拒绝接单，必须字段：demandDeal，allocateGuideId，orderNo，updateTime
	 *            </p>
	 *            <p>
	 *            设置为已读，必须字段：demandDeal，allocateGuideId，orderNo，updateTime，
	 *            isRead
	 *            </p>
	 * @return
	 */
	@Update({ "<script>",
			"update trade_deliver_guide ",
			"<set>",
			"<if test='demandDeal != null'>demand_deal=#{demandDeal},</if>",
			"<if test='refuseReason != null'>refuse_reason=#{refuseReason},</if>",
			"<if test='acceptTime != null'>accept_time=#{acceptTime},</if>",
			"<if test='updateTime != null'>update_time=#{updateTime},</if>",
			"<if test='isRead != null'>is_read=#{isRead},</if>",
			"<if test='guideId != null'>guide_id=#{guideId}</if>",
			"</set>",
			"where allocat_gno=#{allocateGid} and order_no=#{orderNo}",
			"</script>"
	})
	int update(TradeDeliverGuideParamBean paramBean);

	@Update("update trade_deliver_guide set is_readable=0 where allocat_gno=#{allocateGid} and order_no=#{orderNo} and guide_id=#{guideId} and deliver_status=5")
	int delete(TradeDeliverGuideParamBean paramBean);

	@Update({ "<script>",
			"update trade_deliver_guide set demand_deal=2, ",
			"<if test='refuseReason != null'>refuse_reason=#{refuseReason},</if>",
			"<if test='acceptTime != null'>accept_time=#{acceptTime},</if>",
			"<if test='updateTime != null'>update_time=#{updateTime},</if>",
			"<if test='isRead != null'>is_read=#{isRead},</if>",
			"<if test='guideId != null'>guide_id=#{guideId}</if>",
			"where allocat_gno=#{allocateGid} and order_no=#{orderNo} and demand_deal=0",
			"</script>"
	})
	int refuse(TradeDeliverGuideParamBean inputs);

	@Select({ "<script>",
			"SELECT g.allocat_gno,g.deliver_no,g.order_no,g.guide_id,g.guide_name,g.guide_no,"
					+ "g.guide_price,g.deliver_type,g.deliver_type_name,g.deliver_status,g.deliver_status_name,"
					+ "g.demand_deal,g.is_read,g.is_read_mis,g.is_readable,g.view_result,g.refuse_reason,g.accept_time,"
					+ "g.first_read_time,g.service_time,t.deliver_time FROM trade_deliver_guide g,trade_deliver t where g.deliver_status != #{tradeDeliverGuideStatus} AND t.deliver_status != #{tradeDeliverStatus} AND "
					+ "g.deliver_no = t.deliver_no AND g.order_no = t.order_no AND t.deliver_type = #{deliverType} AND g.order_no = #{orderNo}",
			"<if test='searchInfo != null'>",
			"AND (g.guide_no like '%${searchInfo}%' OR g.guide_name like '%${searchInfo}%')",
			"</if>",
			"order by service_time limit #{offset}, #{limit} ",
			"</script>" })
	List<TradeDeliverGuideVoBean> selectTradeDeliverGuidesByType(TradeDeliverGuideQueryBean queryBean);

	@Select({ "<script>",
			"SELECT count(1) FROM trade_deliver_guide g,trade_deliver t where g.deliver_status != #{tradeDeliverGuideStatus} AND t.deliver_status != #{tradeDeliverStatus} AND "
					+ "g.deliver_no = t.deliver_no AND g.order_no = t.order_no AND t.deliver_type = #{deliverType} AND g.order_no = #{orderNo}",
			"<if test='searchInfo != null'>",
			"AND (g.guide_no like '%${searchInfo}%' OR g.guide_name like '%${searchInfo}%')",
			"</if>", "</script>" })
	int countTradeDeliverGuidesByType(TradeDeliverGuideQueryBean queryBean);
}
