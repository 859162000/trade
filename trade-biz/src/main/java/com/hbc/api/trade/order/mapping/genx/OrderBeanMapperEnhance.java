/**
 * @Author lukangle
 * @2015年10月7日@下午4:45:11
 */
package com.hbc.api.trade.order.mapping.genx;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.xbean.GDSOrderQueryBean;
import com.hbc.api.trade.order.mapping.genx.xbean.GOrderQueryBean;
import com.hbc.api.trade.order.mapping.genx.xbean.MISOrderQueryBean;
import com.hbc.api.trade.order.mapping.genx.xbean.MISOrderResultBean;

public interface OrderBeanMapperEnhance {
	
	@Select({"<script>",
		"select * from trade_order where guide_id=#{guideId} ",
		"<if test='deliverStatus != null'> and deliver_status=#{deliverStatus}</if>",
		"<if test='orderStatus != null'>",
		"and order_status in ",
		"<foreach item='item' index='index' collection='orderStatus' open='(' separator=',' close=')'> #{item} </foreach>",
		"</if>",
		"order by service_time limit #{offset}, #{limit} ",
	"</script>"})
	List<OrderBean> selectByGuideIdAndOrderStatus(GOrderQueryBean queryBean);
	

    @Select({"<script>",
    	"select count(1) from trade_order",
    	"where  guide_id=#{guideId}",
		"<if test='deliverStatus != null'> and deliver_status=#{deliverStatus}</if>",
		"<if test='orderStatus != null'>",
		"and order_status in ",
		"<foreach item='item' index='index' collection='orderStatus' open='(' separator=',' close=')'> #{item} </foreach>",
		"</if>",
    "</script>"})
    int selectByGuideIdAndOrderStatusTotalSize(GOrderQueryBean gOrderQueryBean);
	
	@Select({"<script>",
			"select * from trade_order where 1=1",
			"<if test='serviceTimeBegin != null'> and service_time &gt;='${serviceTimeBegin} 00:00:00'</if>",
			"<if test='serviceTimeEnd != null'> and service_time &lt;='${serviceTimeEnd} 23:59:59'</if>",
			"<if test='orderNo != null'> and order_no like '%${orderNo}%'</if>",
			"<if test='thirdTradeNo != null'> and third_trade_no like '%${thirdTradeNo}%'</if>",
		"<if test='orderStartDate != null'> and create_time &gt;= '${orderStartDate} 00:00:00'</if>",
		"<if test='orderEndDate != null'> and create_time &lt;= '${orderEndDate} 23:59:59'</if>",
		"<if test='orderSource != null'> and order_source=#{orderSource}</if>",
		"<if test='orderChannel != null'> and order_channel=#{orderChannel}</if>",
		"<if test='orderType != null'> and order_type=#{orderType}</if>",
		"<if test='deliverType != null'> and deliver_type=#{deliverType}</if>",
		"<if test='deliverStatus != null'> and deliver_status=#{deliverStatus}</if>",
		"<if test='userName != null'> and user_name=#{userName}</if>",
		"<if test='userMobile != null'> and user_mobile1=#{userMobile}</if>",
			"<if test='guideNo != null'> and guide_no like '%${guideNo}%'</if>",
		"<if test='guideId != null'> and guide_id=#{guideId}</if>",
			"<if test='guideName != null'> and guide_name like '%${guideName}%'</if>",
			"<if test='serviceCityId != null'> and service_city_id=#{serviceCityId}</if>",
		"<if test='serviceCityName != null'> and service_city_name=#{serviceCityName}</if>",
		"<if test='deliverStatus != null'> and deliver_status=#{deliverStatus}</if>",
		"<if test='evaluateStatus != null'> and user_comment_status=#{evaluateStatus}</if>",
		"<if test='hasNotRegistered != null'> and flight_register_id is null and flight_is_custom = 1 </if>",
		"<if test='orderStatusList != null'>",
		" and order_status in ",
		"<foreach item='item' index='index' collection='orderStatusList' open='(' separator=',' close=')'> #{item} </foreach>",
		"</if>",
		"<if test='orderNoList != null'>",
		" and order_no in ",
		"<foreach item='item' index='index' collection='orderNoList' open='(' separator=',' close=')'> #{item} </foreach>",
		"</if>",
		" order by create_time desc limit #{offset}, #{limit} ",
	"</script>"})
	List<MISOrderResultBean> selectOrderForMIS(MISOrderQueryBean orderBeanParam);

	@Select({"<script>",
			"select count(1) from trade_order where 1=1",
			"<if test='serviceTimeBegin != null'> and service_time &gt;='${serviceTimeBegin} 00:00:00'</if>",
			"<if test='serviceTimeEnd != null'> and service_time &lt;='${serviceTimeEnd} 23:59:59'</if>",
			"<if test='orderNo != null'> and order_no like '%${orderNo}%'</if>",
			"<if test='thirdTradeNo != null'> and third_trade_no like '%${thirdTradeNo}%'</if>",
		"<if test='orderStartDate != null'> and create_time &gt;= '${orderStartDate} 00:00:00'</if>",
		"<if test='orderEndDate != null'> and create_time &lt;= '${orderEndDate} 23:59:59'</if>",
		"<if test='orderSource != null'> and order_source=#{orderSource}</if>",
		"<if test='orderChannel != null'> and order_channel=#{orderChannel}</if>",
		"<if test='orderType != null'> and order_type=#{orderType}</if>",
		"<if test='deliverType != null'> and deliver_type=#{deliverType}</if>",
		"<if test='deliverStatus != null'> and deliver_status=#{deliverStatus}</if>",
		"<if test='userName != null'> and user_name=#{userName}</if>",
		"<if test='userMobile != null'> and user_mobile1=#{userMobile}</if>",
		"<if test='guideId != null'> and guide_id=#{guideId}</if>",
			"<if test='guideNo != null'> and guide_no like '%${guideNo}%'</if>",
			"<if test='guideName != null'> and guide_name like '%${guideName}%'</if>",
			"<if test='serviceCityId != null'> and service_city_id=#{serviceCityId}</if>",
		"<if test='serviceCityName != null'> and service_city_name=#{serviceCityName}</if>",
		"<if test='deliverStatus != null'> and deliver_status=#{deliverStatus}</if>",
		"<if test='hasNotRegistered != null'> and flight_register_id is null and flight_is_custom = 1 </if>",
		"<if test='orderStatusList != null'>",
		" and order_status in ",
		"<foreach item='item' index='index' collection='orderStatusList' open='(' separator=',' close=')'> #{item} </foreach>",
		"</if>",
		"<if test='orderNoList != null'>",
		" and order_no in ",
		"<foreach item='item' index='index' collection='orderNoList' open='(' separator=',' close=')'> #{item} </foreach>",
		"</if>",
	"</script>"})
    int selectOrderForMISTotalSize(MISOrderQueryBean orderBeanParam);
	
	@Select("select * from trade_order where user_id=#{userId} order by service_time limit #{offset}, #{limit} ")
	List<MISOrderResultBean> selectOrderForMISByUserId(MISOrderQueryBean orderBeanParam);
	
	@Select("select * from trade_order where order_no=#{orderNo} order by service_time limit #{offset}, #{limit} ")
	MISOrderResultBean selectOrderForMISByOrderNo(MISOrderQueryBean orderBeanParam);
	
	@Select("select count(1) from trade_order where user_id=#{userId}")
	int selectOrderForMISByUserIdTotalSize(MISOrderQueryBean orderBeanParam);
	
	@Select("select * from trade_order where guide_id=#{guideId} order by service_time desc limit #{offset}, #{limit} ")
	List<MISOrderResultBean> queryAlreayAgrredOrder(MISOrderQueryBean orderBeanParam);
	
	
	@Select("select count(1) from trade_order where guide_id=#{guideId}")
	int queryAlreayAgrredOrderTotalSize(MISOrderQueryBean orderBeanParam);
	
    @Select({"<script>",
    	"select * from trade_order where 1=1",
    	"<if test='agentId != null'> and agent_id=#{agentId}</if>",
    	"<if test='orderNo != null'> and order_no=#{orderNo}</if>",
		"<if test='agentOpname != null'> and  agent_opname=#{agentOpname}</if>",
		"<if test='userName != null'> and  user_name=#{userName}</if>",
		"<if test='agentOpid != null'> and  agent_opid=#{agentOpid}</if>",
		"<if test='orderType != null'> and  order_type=#{orderType}</if>",
		"<if test='serviceCityName != null'> and  service_city_name=#{serviceCityName}</if>",
    	"<if test='orderStatus != null'>",
    	" and order_status in ",
    	"<foreach item='item' index='index' collection='orderStatus' open='(' separator=',' close=')'> #{item} </foreach>",
    	"</if>",
			" order by service_time desc limit #{offset}, #{limit} ",
    "</script>"})
    List<OrderBean> selectOrderForGDS(GDSOrderQueryBean  queryBean);
    
    
    @Select({"<script>",
    	"select count(1) from trade_order where 1=1",
    	"<if test='agentId != null'> and agent_id=#{agentId}</if>",
    	"<if test='orderNo != null'> and order_no=#{orderNo}</if>",
		"<if test='agentOpname != null'> and  agent_opname=#{agentOpname}</if>",
		"<if test='userName != null'> and  user_name=#{userName}</if>",
		"<if test='agentOpid != null'> and  agent_opid=#{agentOpid}</if>",
		"<if test='orderType != null'> and  order_type=#{orderType}</if>",
		"<if test='serviceCityName != null'> and  service_city_name=#{serviceCityName}</if>",
    	"<if test='orderStatus != null'>",
    	" and order_status in ",
    	"<foreach item='item' index='index' collection='orderStatus' open='(' separator=',' close=')'> #{item} </foreach>",
    	"</if>",
    "</script>"})
    int selectOrderForGDSTotalSize(GDSOrderQueryBean  queryBean);
    
	@Select({"<script>",
		"select o.*, p.pay_time,p.pay_no from trade_order o left join trade_payment p on o.order_no=p.order_no",
		"where",
		"<if test='serviceTimeBegin != null and serviceTimeEnd != null'> o.service_time between #{serviceTimeBegin} and #{serviceTimeEnd} </if>",
		"<if test='hasMoreThanOneHour != null'> and p.pay_time &lt; #{payTime}</if>",
		"<if test='orderNo != null'> and o.order_no=#{orderNo}</if>",
		"<if test='orderSource != null'> and o.order_source=#{orderSource}</if>",
		"<if test='orderType != null'> and o.order_type=#{orderType}</if>",
		"<if test='deliverType != null'> and o.deliver_type=#{deliverType}</if>",
		"<if test='evaluateStatus != null'> and user_comment_status=#{evaluateStatus}</if>",
		"<if test='deliverStatus != null'> and o.deliver_status=#{deliverStatus}</if>",
		"<if test='orderStatusList != null'>",
		" and o.order_status in ",
		"<foreach item='item' index='index' collection='orderStatusList' open='(' separator=',' close=')'> #{item} </foreach>",
		"</if>",
		" order by o.service_time limit #{offset}, #{limit} ",
	"</script>"})
	List<MISOrderResultBean> selectOrderForMISWithPayment(MISOrderQueryBean orderBeanParam);
	
	@Select({"<script>",
		"select count(1) from trade_order o left join trade_payment p on o.order_no=p.order_no",
		"where",
		"<if test='serviceTimeBegin != null and serviceTimeEnd != null'> o.service_time between #{serviceTimeBegin} and #{serviceTimeEnd} </if>",
		"<if test='hasMoreThanOneHour != null'> and p.pay_time &lt; #{payTime}</if>",
		"<if test='orderNo != null'> and o.order_no=#{orderNo}</if>",
		"<if test='orderSource != null'> and o.order_source=#{orderSource}</if>",
		"<if test='orderType != null'> and o.order_type=#{orderType}</if>",
		"<if test='deliverType != null'> and o.deliver_type=#{deliverType}</if>",
		"<if test='evaluateStatus != null'> and user_comment_status=#{evaluateStatus}</if>",
		"<if test='deliverStatus != null'> and o.deliver_status=#{deliverStatus}</if>",
		"<if test='orderStatusList != null'>",
		" and o.order_status in ",
		"<foreach item='item' index='index' collection='orderStatusList' open='(' separator=',' close=')'> #{item} </foreach>",
		"</if>",
	"</script>"})
	int selectOrderForMISWithPaymentTotalSize(MISOrderQueryBean orderBeanParam);

	@Select({"<script>",
		"select * from trade_order where guide_id=#{guideId}",
		"<if test='orderType != null'> and order_type=#{orderType}</if>",
		"<if test='orderStatusList != null'>",
		" and order_status in ",
		"<foreach item='item' index='index' collection='orderStatusList' open='(' separator=',' close=')'> #{item} </foreach>",
		"</if>",
		" order by service_time",
	"</script>"})
	List<MISOrderResultBean> getUnbalancedOrders(MISOrderQueryBean orderBeanParam);
}
