/**
 * @Author lukangle
 * @2015年11月6日@下午8:17:00
 */
package com.hbc.api.trade.order.mapping.genx.deliver;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;

public interface GxGuideDeliverMapper {
	@Update({ "update trade_deliver_guide ", 
		"set deliver_status ='${target_deliver_status}', ",
		 "deliver_status_name ='${target_status_name}'",
		"where allocat_gno = '${allocatGno}' and deliver_status='${deliver_status}'" })
	int updateDeliverStatus(@Param("allocatGno") String allocatGno, @Param("deliver_status") int deliver_status, 
			@Param("target_deliver_status") int target_deliver_status,@Param("target_status_name")String target_status_name);

	@Select("select * from trade_deliver_guide where  allocat_gno = '${allocatGno}' for update")
	public TradeDeliverGuide forupdateAllocatGno(@Param("allocatGno") String allocatGno);

	
	@Update({ "update trade_deliver_guide ", "set is_readable ='${updIsReadable}' ", "where guide_id = '${guideId}' and order_no='${orderNo}' and is_readable='${isReadable}' " })
	int updatePreviousDelivers( @Param("guideId") String guideId, @Param("orderNo") String orderNo,@Param("isReadable") int isReadable,@Param("updIsReadable") int updIsReadable);
	
	@Update({ "update trade_deliver_guide ", 
		"set deliver_status ='${target_deliver_status}', ",
		 "deliver_status_name ='${target_status_name}'",
		"where  deliver_no = '${deliver_no}' and deliver_status='${deliver_status}'" })
	int updateDeliverStatusByDeliverNo(@Param("deliver_no") String deliver_no, @Param("deliver_status") int deliver_status, @Param("target_deliver_status") int target_deliver_status,@Param("target_status_name")String target_status_name);
	@Update({ "update trade_deliver_guide ", "set is_readable =${target_is_readable} ", "where deliver_no = '${deliverNo}' and is_readable =${original_is_readable}" })
	int updateAllBachNoReadable(@Param("deliverNo") String deliverNo,@Param("original_is_readable") int originalReadable, @Param("target_is_readable") int newReadable);
	@Update({ "update trade_deliver_guide ", 
		"set deliver_status ='${target_deliver_status}', ",
		 "deliver_status_name ='${target_status_name}'",
		"where deliver_no = '${deliverNo}' and  deliver_status ='${original_deliver_status}' " })
	int updateAllBachNoDeliverStatus(@Param("deliverNo") String deliverNo,@Param("original_deliver_status") int original_deliver_status, @Param("target_deliver_status") int target_deliver_status,@Param("target_status_name")String target_status_name);
	
	@Update({ "update trade_deliver_guide ", "set is_readable ='${isReadable}' ", "where allocat_gno = '${allocatGno}'" })
	int disableGuideOrder( @Param("allocatGno") String allocatGno, @Param("isReadable") int isReadable);
	
	@Update({ "update trade_deliver_guide ", "set is_readable =${target_is_readable} ", "where order_no = '${orderNo}' and is_readable =${original_is_readable}" })
	int updateByOrderNoReadable(@Param("orderNo") String orderNo,@Param("original_is_readable") int originalReadable, @Param("target_is_readable") int newReadable);
	
	@Update({ "update trade_deliver_guide ", "set deliver_status=${deliver_status} ,deliver_status_name='${deliver_status_name}' ", "where order_no = '${orderNo}' and guide_id =${guide_id} and is_readable =${target_is_readable}" })
	int updateDeliverStatusByGuideId(@Param("orderNo") String orderNo,@Param("guide_id") String guideId, @Param("target_is_readable") int newReadable,@Param("deliver_status") int deliver_status,@Param("deliver_status_name") String deliverStatusName);
	
	
	@Update({ "update trade_deliver_guide ", "set deliver_status=${deliver_status} ,deliver_status_name='${deliver_status_name}',fail_type= ${fail_type}", "where order_no = '${orderNo}' and guide_id =${guide_id}" })
	int updatePKFailDeliverGuide(@Param("orderNo") String orderNo,@Param("guide_id") String guideId, @Param("fail_type") int fail_type,@Param("deliver_status") int deliver_status,@Param("deliver_status_name") String deliverStatusName);
	
}
