/**
 * @Author lukangle
 * @2015年10月26日@下午8:00:23
 */
package com.hbc.api.trade.order.mapping.genx.deliver;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;

public interface GxDeliverOrderMapper {
	@Update({
        "update trade_deliver ",
          "set deliver_status ='${target_deliver_status}' ,",
          "deliver_status_name ='${target_status_name}',",
          "deliver_time ='${deliverTime}'",
        "where deliver_no = '${deliverNo}' and deliver_status='${deliver_status}'"
    })
    int updateDeliverStatusDtime(@Param("deliverNo") String deliverNo,@Param("deliver_status")int deliver_status,@Param("target_deliver_status")int target_deliver_status,
    		@Param("target_status_name")String target_status_name,@Param("deliverTime")Timestamp deliverTime);
	
	@Update({
        "update trade_deliver ",
          "set deliver_status ='${target_deliver_status}' ,",
          "deliver_status_name ='${target_status_name}'",
        "where deliver_no = '${deliverNo}' and deliver_status='${deliver_status}'"
    })
    int updateDeliverStatus(@Param("deliverNo") String deliverNo,@Param("deliver_status")int deliver_status,@Param("target_deliver_status")int target_deliver_status,
    		@Param("target_status_name")String target_status_name
    		);
	
	@Update({
        "update trade_deliver ",
          "set ",
          "pk_time ='${pkTime}'",
        "where deliver_no = '${deliverNo}'"
    })
    int updatePktime(@Param("deliverNo") String deliverNo,@Param("pkTime")Timestamp pkTime);
	
	@Update({
        "update trade_deliver ",
          "set deliver_time ='${deliver_time}' ,",
         " deliver_count ='${deliverCount}' ",
        "where deliver_no = '${deliverNo}'"
    })
    int updateDeliverTimeAndCount(@Param("deliverNo") String deliverNo,@Param("deliver_time") Timestamp deliverTime,@Param("deliverCount") int deliverCount);


	@Select("SELECT * FROM trade_deliver where  deliver_type in (${deliverType}) and deliver_status =1")
	public List<TradeOrderDeliver> getForPkDelivers(@Param("deliverType")String deliverType);
	
	
	@Update({
        "update trade_deliver ",
          "set pk_status ='${pk_status}' ,",
          " pk_status_name ='${pk_status_name}' ",
        "where deliver_no = '${deliverNo}' "
    })
    int updatePkStatus(@Param("deliverNo") String deliverNo,@Param("pk_status")int pk_status,@Param("pk_status_name")String pk_status_name);
	
	@Update({
        "update trade_deliver ",
          "set pk_status ='${pk_status}' ,",
          " pk_status_name ='${pk_status_name}' ",
        "where order_no = '${order_no}' and (pk_status <> '${pk_status_invariability}'  OR pk_status IS NULL)"
    })
    int updatePkStatusByOrderNo(@Param("order_no") String orderno,@Param("pk_status")int pk_status,@Param("pk_status_name")String pk_status_name,@Param("pk_status_invariability") int invariabilityPkStatus);
	
	
	@Update({
        "update trade_deliver ",
          "set deliver_time ='${deliverTime}', ", 
          " deliver_count ='${deliverCount}' ", 
        "where deliver_no = '${deliverNo}' "
    })
    int updateDeliverTime(@Param("deliverNo") String deliverNo,@Param("deliverTime")Timestamp deliverTime,@Param("deliverCount") int deliverCount);
	
	@Update({
        "update trade_deliver ",
          "set deliver_time ='${deliverTime}', ", 
          " deliver_count ='${deliverCount}', ",
          " deliver_status ='${deliverStatus}', ", 
          "deliver_status_name ='${deliverStatusName}'",
        "where deliver_no = '${deliverNo}' "
    })
    int updateIncrementDeliver(@Param("deliverNo") String deliverNo,@Param("deliverTime")Timestamp deliverTime,@Param("deliverCount") int deliverCount,@Param("deliverStatus") int deliverStatus,@Param("deliverStatusName") String deliverStatusName);
	
	@Update({
		"update trade_deliver ",
        "set deliver_status ='${target_deliver_status}' ,",
        "deliver_status_name ='${target_status_name}',",
        "pk_status ='${pk_status}' ,",
         " pk_status_name ='${pk_status_name}' ,",
         "pk_time ='${pkTime}'",
      "where deliver_no = '${deliverNo}' and deliver_status='${deliver_status}'"
    })
    int updatePkSuccess(@Param("deliverNo") String deliverNo,@Param("pk_status")int pk_status,@Param("pk_status_name")String pk_status_name,@Param("pkTime")Timestamp pkTime,@Param("deliver_status")int deliver_status,@Param("target_deliver_status")int target_deliver_status,@Param("target_status_name")String target_status_name);
}
