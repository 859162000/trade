package com.hbc.api.trade.pay.mapping.gen;

import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefund;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefundExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TradeRefundMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund`
     *
     * @mbggenerated
     */
    @SelectProvider(type=TradeRefundSqlProvider.class, method="countByExample")
    int countByExample(TradeRefundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund`
     *
     * @mbggenerated
     */
    @DeleteProvider(type=TradeRefundSqlProvider.class, method="deleteByExample")
    int deleteByExample(TradeRefundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund`
     *
     * @mbggenerated
     */
    @Delete({
        "delete from `trade_refund`",
        "where refund_no = #{refundNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String refundNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund`
     *
     * @mbggenerated
     */
    @Insert({
        "insert into `trade_refund` (refund_no, order_no, ",
        "batch_no, batch_num, ",
        "refund_status, refund_type, ",
        "order_status, order_status_name, ",
        "pay_no, refund_account, ",
        "refund_money, refund_money_guide, ",
        "refund_money_system, refund_subject, ",
        "refund_desc, refund_error, ",
        "refund_detail, refund_request, ",
        "refund_response, refund_time, ",
        "user_id, user_mobile, ",
        "pay_getway, pay_gateway_name, ",
        "refund_getway, refund_getway_name, ",
        "create_time, update_time)",
        "values (#{refundNo,jdbcType=VARCHAR}, #{orderNo,jdbcType=VARCHAR}, ",
        "#{batchNo,jdbcType=VARCHAR}, #{batchNum,jdbcType=INTEGER}, ",
        "#{refundStatus,jdbcType=INTEGER}, #{refundType,jdbcType=INTEGER}, ",
        "#{orderStatus,jdbcType=INTEGER}, #{orderStatusName,jdbcType=VARCHAR}, ",
        "#{payNo,jdbcType=VARCHAR}, #{refundAccount,jdbcType=VARCHAR}, ",
        "#{refundMoney,jdbcType=DOUBLE}, #{refundMoneyGuide,jdbcType=DOUBLE}, ",
        "#{refundMoneySystem,jdbcType=DOUBLE}, #{refundSubject,jdbcType=VARCHAR}, ",
        "#{refundDesc,jdbcType=VARCHAR}, #{refundError,jdbcType=VARCHAR}, ",
        "#{refundDetail,jdbcType=VARCHAR}, #{refundRequest,jdbcType=VARCHAR}, ",
        "#{refundResponse,jdbcType=VARCHAR}, #{refundTime,jdbcType=TIMESTAMP}, ",
        "#{userId,jdbcType=VARCHAR}, #{userMobile,jdbcType=VARCHAR}, ",
        "#{payGetway,jdbcType=INTEGER}, #{payGatewayName,jdbcType=VARCHAR}, ",
        "#{refundGetway,jdbcType=INTEGER}, #{refundGetwayName,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(TradeRefund record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund`
     *
     * @mbggenerated
     */
    @InsertProvider(type=TradeRefundSqlProvider.class, method="insertSelective")
    int insertSelective(TradeRefund record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund`
     *
     * @mbggenerated
     */
    @SelectProvider(type=TradeRefundSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="refund_no", property="refundNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="order_no", property="orderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="batch_no", property="batchNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="batch_num", property="batchNum", jdbcType=JdbcType.INTEGER),
        @Result(column="refund_status", property="refundStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="refund_type", property="refundType", jdbcType=JdbcType.INTEGER),
        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="order_status_name", property="orderStatusName", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_no", property="payNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_account", property="refundAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_money", property="refundMoney", jdbcType=JdbcType.DOUBLE),
        @Result(column="refund_money_guide", property="refundMoneyGuide", jdbcType=JdbcType.DOUBLE),
        @Result(column="refund_money_system", property="refundMoneySystem", jdbcType=JdbcType.DOUBLE),
        @Result(column="refund_subject", property="refundSubject", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_desc", property="refundDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_error", property="refundError", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_detail", property="refundDetail", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_request", property="refundRequest", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_response", property="refundResponse", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_time", property="refundTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_mobile", property="userMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_getway", property="payGetway", jdbcType=JdbcType.INTEGER),
        @Result(column="pay_gateway_name", property="payGatewayName", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_getway", property="refundGetway", jdbcType=JdbcType.INTEGER),
        @Result(column="refund_getway_name", property="refundGetwayName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TradeRefund> selectByExample(TradeRefundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund`
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "refund_no, order_no, batch_no, batch_num, refund_status, refund_type, order_status, ",
        "order_status_name, pay_no, refund_account, refund_money, refund_money_guide, ",
        "refund_money_system, refund_subject, refund_desc, refund_error, refund_detail, ",
        "refund_request, refund_response, refund_time, user_id, user_mobile, pay_getway, ",
        "pay_gateway_name, refund_getway, refund_getway_name, create_time, update_time",
        "from `trade_refund`",
        "where refund_no = #{refundNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="refund_no", property="refundNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="order_no", property="orderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="batch_no", property="batchNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="batch_num", property="batchNum", jdbcType=JdbcType.INTEGER),
        @Result(column="refund_status", property="refundStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="refund_type", property="refundType", jdbcType=JdbcType.INTEGER),
        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="order_status_name", property="orderStatusName", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_no", property="payNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_account", property="refundAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_money", property="refundMoney", jdbcType=JdbcType.DOUBLE),
        @Result(column="refund_money_guide", property="refundMoneyGuide", jdbcType=JdbcType.DOUBLE),
        @Result(column="refund_money_system", property="refundMoneySystem", jdbcType=JdbcType.DOUBLE),
        @Result(column="refund_subject", property="refundSubject", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_desc", property="refundDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_error", property="refundError", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_detail", property="refundDetail", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_request", property="refundRequest", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_response", property="refundResponse", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_time", property="refundTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_mobile", property="userMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_getway", property="payGetway", jdbcType=JdbcType.INTEGER),
        @Result(column="pay_gateway_name", property="payGatewayName", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_getway", property="refundGetway", jdbcType=JdbcType.INTEGER),
        @Result(column="refund_getway_name", property="refundGetwayName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TradeRefund selectByPrimaryKey(String refundNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=TradeRefundSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TradeRefund record, @Param("example") TradeRefundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=TradeRefundSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TradeRefund record);
}