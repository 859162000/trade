/**
 * @Author lukangle
 * @2015年11月7日@上午11:32:27
 */
package com.hbc.api.trade.order.mapping.genx;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;

public interface UpdateOrderBeanMapper {
	@Select("select * from trade_order where order_no= #{orderNo,jdbcType=VARCHAR} for update")
	public OrderBean forupdateByOrderNo(String orderNo);
	
	@Update({
        "update trade_order",
        "set order_status = #{orderStatus,jdbcType=INTEGER},",
         "order_status_name = #{orderStatusName,jdbcType=VARCHAR}",
        "where order_no = #{orderNo,jdbcType=VARCHAR}"
    })
    int updateStatusByPrimaryKey(OrderBean record);
	
	
	@Update("update trade_order set user_comment_status = 1  where order_no = #{orderNo}")
	int updateToAlreadyUserAppraised(@Param("orderNo") String orderNo);
	
	@Update("update trade_order set system_comment_status = 1  where order_no = #{orderNo}")
	int updateToAlreadySystemAppraised(@Param("orderNo") String orderNo);
	
	@Update({
        "<script>",
        "update trade_order",
        "<set>",
        "<if test='serviceTime != null'>service_time = #{serviceTime}, </if>",
        "<if test='serviceAreaCode != null'>service_area_code = #{serviceAreaCode}, </if>",
        "<if test='serviceAddressTel != null'>service_address_tel = #{serviceAddressTel}, </if>",
        "<if test='userName != null'>user_name = #{userName}, </if>",
        "<if test='userAreaCode1 != null'>user_area_code1 = #{userAreaCode1}, </if>",
        "<if test='userMobile1 != null'>user_mobile1 = #{userMobile1}, </if>",
        "<if test='userAreaCode2 != null'>user_area_code2 = #{userAreaCode2}, </if>",
        "<if test='userMobile2 != null'>user_mobile2 = #{userMobile2}, </if>",
        "<if test='userAreaCode3 != null'>user_area_code3 = #{userAreaCode3}, </if>",
        "<if test='userMobile3 != null'>user_mobile3 = #{userMobile3}, </if>",
        "<if test='userEmail != null'>user_email = #{userEmail}, </if>",
        "<if test='userRemark != null'>user_remark = #{userRemark}, </if>",
        "<if test='adultNum != null'>adult_num = #{adultNum}, </if>",
        "<if test='childNum != null'>child_num = #{childNum}, </if>",
        "<if test='childSeat != null'>child_seat = #{childSeat}, </if>",
        "<if test='userEmail != null'>user_email = #{userEmail}, </if>",
        "<if test='journeyComment != null'>journey_comment = #{journeyComment}, </if>",
        "<if test='isArrivalVisa != null'>is_arrival_visa = #{isArrivalVisa}, </if>",
        "</set>",
        "where order_no = #{orderNo,jdbcType=VARCHAR}",
        "</script>"
    })
    int updateByPrimaryKey(OrderBean record);
	
	
	@Update({"<script>",
        "update trade_order",
        "<set>",
        "<if test='serviceTime != null'>service_time = #{serviceTime}, </if>",
        "<if test='serviceAreaCode != null'>service_area_code = #{serviceAreaCode}, </if>",
        "<if test='serviceAddressTel != null'>service_address_tel = #{serviceAddressTel}, </if>",
        "<if test='userName != null'>user_name = #{userName}, </if>",
        "<if test='userAreaCode1 != null'>user_area_code1 = #{userAreaCode1}, </if>",
        "<if test='userMobile1 != null'>user_mobile1 = #{userMobile1}, </if>",
        "<if test='userAreaCode2 != null'>user_area_code2 = #{userAreaCode2}, </if>",
        "<if test='userMobile2 != null'>user_mobile2 = #{userMobile2}, </if>",
        "<if test='userAreaCode3 != null'>user_area_code3 = #{userAreaCode3}, </if>",
        "<if test='userMobile3 != null'>user_mobile3 = #{userMobile3}, </if>",
        "<if test='userEmail != null'>user_email = #{userEmail}, </if>",
        "<if test='userRemark != null'>user_remark = #{userRemark}, </if>",
        "<if test='adultNum != null'>adult_num = #{adultNum}, </if>",
        "<if test='childNum != null'>child_num = #{childNum}, </if>",
        "<if test='childSeat != null'>child_seat = #{childSeat}, </if>",
        "<if test='flightNo != null'>flight_no = #{flightNo}, </if>",
        "<if test='flightAirportCode != null'>flight_airport_code = #{flightAirportCode}, </if>",
        "<if test='flightAirportName != null'>flight_airport_name = #{flightAirportName}, </if>",
        "<if test='flightFlyTime != null'>flight_fly_time = #{flightFlyTime}, </if>",
        "<if test='flightArriveTime != null'>flight_arrive_time = #{flightArriveTime}, </if>",
        "<if test='flightAirportBuiding != null'>flight_airport_buiding = #{flightAirportBuiding}, </if>",
        "<if test='flightRegisterId != null'>flight_register_id = #{flightRegisterId}, </if>",
        "<if test='flightBrandSign != null'>flight_brand_sign = #{flightBrandSign}, </if>",
        "<if test='isArrivalVisa != null'>is_arrival_visa = #{isArrivalVisa}, </if>",
		"<if test='journeyComment != null'>journey_comment = #{journeyComment} </if>",
        "</set>",
        "where order_no = #{orderNo,jdbcType=VARCHAR}",
        "</script>"
    })
    int updateDailyOrderByPrimaryKey(OrderBean record);
	
	@Update({"<script>",
		"update trade_order",
		"<set>",
		"<if test='serviceAreaCode != null'>service_area_code = #{serviceAreaCode}, </if>",
		"<if test='serviceAddressTel != null'>service_address_tel = #{serviceAddressTel}, </if>",
		"<if test='userAreaCode1 != null'>user_area_code1 = #{userAreaCode1}, </if>",
		"<if test='userMobile1 != null'>user_mobile1 = #{userMobile1}, </if>",
		"<if test='userAreaCode2 != null'>user_area_code2 = #{userAreaCode2}, </if>",
		"<if test='userMobile2 != null'>user_mobile2 = #{userMobile2}, </if>",
		"<if test='userAreaCode3 != null'>user_area_code3 = #{userAreaCode3}, </if>",
		"<if test='userMobile3 != null'>user_mobile3 = #{userMobile3}, </if>",
        "<if test='userEmail != null'>user_email = #{userEmail}, </if>",
		"<if test='userRemark != null'>user_remark = #{userRemark}, </if>",
		"<if test='adultNum != null'>adult_num = #{adultNum}, </if>",
		"<if test='childNum != null'>child_num = #{childNum}, </if>",
		"<if test='childSeat != null'>child_seat = #{childSeat}, </if>",
		"<if test='userName != null'>user_name = #{userName}, </if>",
		"<if test='isArrivalVisa != null'>is_arrival_visa = #{isArrivalVisa}, </if>",

		"<if test='serviceTime != null'>service_time = #{serviceTime}, </if>",
		"<if test='servicePassCity != null'>service_pass_city = #{servicePassCity}, </if>",
		"<if test='startAddress != null'>start_address = #{startAddress}, </if>",
		"<if test='journeyComment != null'>journey_comment = #{journeyComment} </if>",
		"</set>",
		"where order_no = #{orderNo,jdbcType=VARCHAR}",
		"</script>"
	})
	int updateDailyOrder(OrderBean record);
	
	@Update({"<script>",
		"update trade_order",
		"<set>",
		"<if test='serviceAreaCode != null'>service_area_code = #{serviceAreaCode}, </if>",
		"<if test='serviceAddressTel != null'>service_address_tel = #{serviceAddressTel}, </if>",
		"<if test='userAreaCode1 != null'>user_area_code1 = #{userAreaCode1}, </if>",
		"<if test='userMobile1 != null'>user_mobile1 = #{userMobile1}, </if>",
		"<if test='userAreaCode2 != null'>user_area_code2 = #{userAreaCode2}, </if>",
		"<if test='userMobile2 != null'>user_mobile2 = #{userMobile2}, </if>",
		"<if test='userAreaCode3 != null'>user_area_code3 = #{userAreaCode3}, </if>",
		"<if test='userMobile3 != null'>user_mobile3 = #{userMobile3}, </if>",
        "<if test='userEmail != null'>user_email = #{userEmail}, </if>",
		"<if test='userRemark != null'>user_remark = #{userRemark}, </if>",
		"<if test='adultNum != null'>adult_num = #{adultNum}, </if>",
		"<if test='childNum != null'>child_num = #{childNum}, </if>",
		"<if test='childSeat != null'>child_seat = #{childSeat}, </if>",
		"<if test='userName != null'>user_name = #{userName}, </if>",
		"<if test='isArrivalVisa != null'>is_arrival_visa = #{isArrivalVisa}, </if>",
		
		"<if test='flightNo != null'>flight_no = #{flightNo}, </if>",
		"<if test='flightAirportCode != null'>flight_airport_code = #{flightAirportCode}, </if>",
		"<if test='flightAirportName != null'>flight_airport_name = #{flightAirportName}, </if>",
		"<if test='flightFlyTime != null'>flight_fly_time = #{flightFlyTime}, </if>",
		"<if test='flightArriveTime != null'>flight_arrive_time = #{flightArriveTime}, </if>",
		"<if test='flightAirportBuiding != null'>flight_airport_buiding = #{flightAirportBuiding}, </if>",
		"<if test='flightBrandSign != null'>flight_brand_sign = #{flightBrandSign}, </if>",
		"</set>",
		"where order_no = #{orderNo,jdbcType=VARCHAR}",
		"</script>"
	})
	int updateSendOrder(OrderBean record);
	
	@Update({"<script>",
		"update trade_order",
		"<set>",
		"<if test='serviceAreaCode != null'>service_area_code = #{serviceAreaCode}, </if>",
		"<if test='serviceAddressTel != null'>service_address_tel = #{serviceAddressTel}, </if>",
		"<if test='userAreaCode1 != null'>user_area_code1 = #{userAreaCode1}, </if>",
		"<if test='userMobile1 != null'>user_mobile1 = #{userMobile1}, </if>",
		"<if test='userAreaCode2 != null'>user_area_code2 = #{userAreaCode2}, </if>",
		"<if test='userMobile2 != null'>user_mobile2 = #{userMobile2}, </if>",
		"<if test='userAreaCode3 != null'>user_area_code3 = #{userAreaCode3}, </if>",
		"<if test='userMobile3 != null'>user_mobile3 = #{userMobile3}, </if>",
        "<if test='userEmail != null'>user_email = #{userEmail}, </if>",
		"<if test='userRemark != null'>user_remark = #{userRemark}, </if>",
		"<if test='adultNum != null'>adult_num = #{adultNum}, </if>",
		"<if test='childNum != null'>child_num = #{childNum}, </if>",
		"<if test='childSeat != null'>child_seat = #{childSeat}, </if>",
		"<if test='userName != null'>user_name = #{userName}, </if>",
		"<if test='isArrivalVisa != null'>is_arrival_visa = #{isArrivalVisa}, </if>",
		
		"<if test='flightBrandSign != null'>flight_brand_sign = #{flightBrandSign}, </if>",
		"</set>",
		"where order_no = #{orderNo,jdbcType=VARCHAR}",
		"</script>"
	})
	int updatePickupOrder(OrderBean record);
	
	@Update({"<script>",
		"update trade_order",
		"<set>",
		"<if test='serviceAreaCode != null'>service_area_code = #{serviceAreaCode}, </if>",
		"<if test='serviceAddressTel != null'>service_address_tel = #{serviceAddressTel}, </if>",
		"<if test='userAreaCode1 != null'>user_area_code1 = #{userAreaCode1}, </if>",
		"<if test='userMobile1 != null'>user_mobile1 = #{userMobile1}, </if>",
		"<if test='userAreaCode2 != null'>user_area_code2 = #{userAreaCode2}, </if>",
		"<if test='userMobile2 != null'>user_mobile2 = #{userMobile2}, </if>",
		"<if test='userAreaCode3 != null'>user_area_code3 = #{userAreaCode3}, </if>",
		"<if test='userMobile3 != null'>user_mobile3 = #{userMobile3}, </if>",
        "<if test='userEmail != null'>user_email = #{userEmail}, </if>",
		"<if test='userRemark != null'>user_remark = #{userRemark}, </if>",
		"<if test='adultNum != null'>adult_num = #{adultNum}, </if>",
		"<if test='childNum != null'>child_num = #{childNum}, </if>",
		"<if test='childSeat != null'>child_seat = #{childSeat}, </if>",
		"<if test='userName != null'>user_name = #{userName}, </if>",
		"<if test='isArrivalVisa != null'>is_arrival_visa = #{isArrivalVisa}, </if>",
		"</set>",
		"where order_no = #{orderNo,jdbcType=VARCHAR}",
		"</script>"
	})
	int updateOnceOrder(OrderBean record);
	
	@Update({
        "update trade_order ",
          "set order_status = '${updatestatus}',",
          "order_status_name ='${statusname}' ",
        "where order_no = '${orderNo}' and order_status='${sourceupdatestatus}' "
    })
    int updateOrderStatus(@Param("orderNo") String orderNo,@Param("sourceupdatestatus")int sourceupdatestatus,@Param("updatestatus")int updatestatus,@Param("statusname")String statusname);
	
	@Update({
        "update trade_order ",
          "set ",
          "guide_pre_id = #{guidePreId,jdbcType=VARCHAR},",
          "guide_id = #{guideId,jdbcType=VARCHAR},",
          "guide_name = #{guideName,jdbcType=VARCHAR},",
          "guide_no = #{guideNo,jdbcType=VARCHAR},",
          "guide_area_code = #{guideAreaCode,jdbcType=VARCHAR},",
          "guide_mobile = #{guideMobile,jdbcType=VARCHAR},",
          "guide_account_no = #{guideAccountNo,jdbcType=VARCHAR},",
          "guide_agency_type = #{guideAgencyType,jdbcType=INTEGER},",
          "deliver_type = #{deliverType,jdbcType=INTEGER},",
          "price_guide = #{priceGuide,jdbcType=DOUBLE},",
          "guide_assign_time = #{guideAssignTime},",
          "flight_register_id = #{flightRegisterId},",
          "deliver_acp_time = #{deliverAcpTime},",
         " deliver_status = #{deliverStatus,jdbcType=INTEGER}",
        "where order_no = #{orderNo,jdbcType=VARCHAR}"
    })
    int resetOrderGuid(OrderBean record);
	
	@Update({
        "update trade_order ",
          "set ",
          "guide_pre_id = #{guidePreId,jdbcType=VARCHAR},",
          "deliver_type = #{deliverType,jdbcType=INTEGER},",
         " deliver_status = #{deliverStatus,jdbcType=INTEGER}",
        "where order_no = #{orderNo,jdbcType=VARCHAR} "
    })
    int preOrderGuid(@Param("guidePreId") String guidePreId,@Param("deliverType") int deliverType,@Param("deliverStatus") int deliverStatus,@Param("orderNo") String orderNo);
	
	
	@Update({
        "update trade_order ",
          "set ",
          "coup_id = #{coupId,jdbcType=VARCHAR},",
          "coup_type = #{coupType,jdbcType=INTEGER},",
          "coup_label = #{coupLabel,jdbcType=VARCHAR},",
          "coup_price_info = #{coupPriceInfo,jdbcType=VARCHAR}",
        "where order_no = #{orderNo,jdbcType=VARCHAR}"
    })
    int addCoupOrder(OrderBean record);
	
	@Update({
        "update trade_order ",
          "set ",
          "serial_flag = #{serialFlag,jdbcType=INTEGER},",
          "serial_order_no = #{serialOrderNo,jdbcType=VARCHAR} ",        
        "where order_no = #{orderNo,jdbcType=VARCHAR} "
    })
    int updateOrderSerialFlag(@Param("serialFlag") int serialFlag,@Param("serialOrderNo") String serialOrderNo,@Param("orderNo") String orderNo);
	
	@Update({
        "update trade_order ",
          "set ",         
          "deliver_type = #{deliverType,jdbcType=INTEGER},",  
         " deliver_status = #{deliverStatus,jdbcType=INTEGER}",
        "where order_no = #{orderNo,jdbcType=VARCHAR} and order_status=#{orderStatus}"
    })
    int updateOrderDeliverStatus(OrderBean record);
	
	@Update({
        "update trade_order ",
          "set ",        
          "guide_pre_id = #{guidePreId,jdbcType=VARCHAR},",
          "deliver_type = #{deliverType,jdbcType=INTEGER},",  
         " deliver_status = #{deliverStatus,jdbcType=INTEGER}",
        "where order_no = #{orderNo,jdbcType=VARCHAR}"
    })
    int updateOrderDeliverStatusAndPreGuideId(OrderBean record);
	
	@Update( "update trade_order set price_guide=#{priceGuideNew}  where order_no = #{orderNo} and  price_guide=#{priceGuideOld}")
	int updateGuidePrice(@Param("orderNo") String orderNo, @Param("priceGuideNew") Double priceGuideNew, @Param("priceGuideOld") Double priceGuideOld);
	
	@Update( "update trade_order set price_reward=#{priceRewardNew}  where order_no = #{orderNo} ")
	int updateRewordGuidePrice(@Param("orderNo") String orderNo, @Param("priceRewardNew") Double priceRewardNew);
}
