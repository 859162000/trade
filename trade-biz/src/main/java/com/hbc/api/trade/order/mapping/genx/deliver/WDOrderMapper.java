/**
 * @Author lukangle
 * @2015年10月24日@下午10:27:59
 */
package com.hbc.api.trade.order.mapping.genx.deliver;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
/**
 * 发单导致 主订单变化表
 */
public interface WDOrderMapper {
	@Select({ "select COUNT(1) from trade_order where guide_id = '${guideId}' and  order_status='${orderStatus}' and deliver_status='${deliverStatus}' and deliver_type!='6'" })
	int countByGuideIdAndOrderStatus(@Param("guideId") String guideId, @Param("orderStatus") int orderStatus, @Param("deliverStatus") int deliverStatus);
	/**
	 * 去除预设导游的 为 6的
	 * @param guideId
	 * @param orderStatus
	 * @param deliverStatus
	 * @param limit
	 * @param offset
	 * @return
	 */
	@Select({ "select * from trade_order where guide_id = '${guideId}' and order_status='${orderStatus}'  and deliver_status='${deliverStatus}' and deliver_type!='6'  limit ${offset}, ${limit} " })
	List<OrderBean> queryByGuideIdAndOrderStatus(@Param("guideId") String guideId, @Param("orderStatus") int orderStatus,@Param("deliverStatus") int deliverStatus, @Param("limit") int limit, @Param("offset") int offset);
	
	@Update({
        "update trade_order ",
          "set deliver_status ='${target_deliver_status}' ",
        "where order_no = '${orderNo}' and order_status='${order_status}' and deliver_status='${deliver_status}' and guide_id='${guide_id}'"
    })
    int updateSOrderDeliverStatus(@Param("orderNo") String orderNo,@Param("order_status")int order_status,@Param("deliver_status")int deliver_status,@Param("target_deliver_status")int target_deliver_status,@Param("guide_id")String guide_id);
	
	@Update({
        "update trade_order ",
          "set deliver_status ='${target_deliver_status}' ,",
          "serial_flag ='${serial_flag}' ",
        "where order_no = '${orderNo}' and order_status='${order_status}' and deliver_status='${deliver_status}' and guide_id='${guide_id}'"
    })
    int updateOrderDeliverStatus(@Param("orderNo") String orderNo,@Param("order_status")int order_status,@Param("deliver_status")int deliver_status,@Param("target_deliver_status")int target_deliver_status,@Param("guide_id")String guide_id,@Param("serial_flag") int serial_flag);
	
	@Update({
        "update trade_order ",
          "set serial_order_no ='${serialOrderNo}' ,",
          "serial_flag ='${serial_flag}' ",
        "where order_no = '${orderNo}' and serial_flag ='${old_serial_flag}' "
    })
	int updateOrderSerialFlag(@Param("orderNo") String orderNo,@Param("serialOrderNo") String serialOrderNo,@Param("serial_flag") int serial_flag,@Param("old_serial_flag") int old_serial_flag);
}
