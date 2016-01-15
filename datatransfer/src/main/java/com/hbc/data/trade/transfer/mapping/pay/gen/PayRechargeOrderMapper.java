package com.hbc.data.trade.transfer.mapping.pay.gen;

import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayRechargeOrder;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayRechargeOrderCriteria;
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
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface PayRechargeOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_recharge_order`
     *
     * @mbggenerated
     */
    @SelectProvider(type=PayRechargeOrderSqlProvider.class, method="countByExample")
    int countByExample(PayRechargeOrderCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_recharge_order`
     *
     * @mbggenerated
     */
    @DeleteProvider(type=PayRechargeOrderSqlProvider.class, method="deleteByExample")
    int deleteByExample(PayRechargeOrderCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_recharge_order`
     *
     * @mbggenerated
     */
    @Delete({
        "delete from `pay_recharge_order`",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_recharge_order`
     *
     * @mbggenerated
     */
    @Insert({
        "insert into `pay_recharge_order` (id, user_id, ",
        "account_id, channel, ",
        "gateway, gateway_ext, ",
        "mer_recharge_no, ser_recharge_no, ",
        "busi_recharge_no, recharge_amount, ",
        "consume_id, status, ",
        "create_time, pay_time, ",
        "subject, body, gateway_account, ",
        "seller_partner, city_id, ",
        "refund_count, expire_time, ",
        "refund_amount, refund_time, ",
        "update_time, mobile_no, ",
        "bank_code, plat, ",
        "plat_ext, return_url, ",
        "callback_url, callback_status, ",
        "callback_count, callback_time, ",
        "ser_notify_time, ser_notify_log)",
        "values (#{id,jdbcType=VARCHAR}, #{userId,jdbcType=VARCHAR}, ",
        "#{accountId,jdbcType=VARCHAR}, #{channel,jdbcType=INTEGER}, ",
        "#{gateway,jdbcType=INTEGER}, #{gatewayExt,jdbcType=VARCHAR}, ",
        "#{merRechargeNo,jdbcType=VARCHAR}, #{serRechargeNo,jdbcType=VARCHAR}, ",
        "#{busiRechargeNo,jdbcType=VARCHAR}, #{rechargeAmount,jdbcType=INTEGER}, ",
        "#{consumeId,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=INTEGER}, #{payTime,jdbcType=INTEGER}, ",
        "#{subject,jdbcType=VARCHAR}, #{body,jdbcType=VARCHAR}, #{gatewayAccount,jdbcType=VARCHAR}, ",
        "#{sellerPartner,jdbcType=VARCHAR}, #{cityId,jdbcType=INTEGER}, ",
        "#{refundCount,jdbcType=INTEGER}, #{expireTime,jdbcType=INTEGER}, ",
        "#{refundAmount,jdbcType=INTEGER}, #{refundTime,jdbcType=INTEGER}, ",
        "#{updateTime,jdbcType=INTEGER}, #{mobileNo,jdbcType=CHAR}, ",
        "#{bankCode,jdbcType=VARCHAR}, #{plat,jdbcType=INTEGER}, ",
        "#{platExt,jdbcType=VARCHAR}, #{returnUrl,jdbcType=VARCHAR}, ",
        "#{callbackUrl,jdbcType=VARCHAR}, #{callbackStatus,jdbcType=INTEGER}, ",
        "#{callbackCount,jdbcType=INTEGER}, #{callbackTime,jdbcType=INTEGER}, ",
        "#{serNotifyTime,jdbcType=INTEGER}, #{serNotifyLog,jdbcType=VARCHAR})"
    })
    int insert(PayRechargeOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_recharge_order`
     *
     * @mbggenerated
     */
    @InsertProvider(type=PayRechargeOrderSqlProvider.class, method="insertSelective")
    int insertSelective(PayRechargeOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_recharge_order`
     *
     * @mbggenerated
     */
    @SelectProvider(type=PayRechargeOrderSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR),
        @Result(column="channel", property="channel", jdbcType=JdbcType.INTEGER),
        @Result(column="gateway", property="gateway", jdbcType=JdbcType.INTEGER),
        @Result(column="gateway_ext", property="gatewayExt", jdbcType=JdbcType.VARCHAR),
        @Result(column="mer_recharge_no", property="merRechargeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ser_recharge_no", property="serRechargeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="busi_recharge_no", property="busiRechargeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="recharge_amount", property="rechargeAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="consume_id", property="consumeId", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.INTEGER),
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.INTEGER),
        @Result(column="subject", property="subject", jdbcType=JdbcType.VARCHAR),
        @Result(column="body", property="body", jdbcType=JdbcType.VARCHAR),
        @Result(column="gateway_account", property="gatewayAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="seller_partner", property="sellerPartner", jdbcType=JdbcType.VARCHAR),
        @Result(column="city_id", property="cityId", jdbcType=JdbcType.INTEGER),
        @Result(column="refund_count", property="refundCount", jdbcType=JdbcType.INTEGER),
        @Result(column="expire_time", property="expireTime", jdbcType=JdbcType.INTEGER),
        @Result(column="refund_amount", property="refundAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="refund_time", property="refundTime", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.INTEGER),
        @Result(column="mobile_no", property="mobileNo", jdbcType=JdbcType.CHAR),
        @Result(column="bank_code", property="bankCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="plat", property="plat", jdbcType=JdbcType.INTEGER),
        @Result(column="plat_ext", property="platExt", jdbcType=JdbcType.VARCHAR),
        @Result(column="return_url", property="returnUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="callback_url", property="callbackUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="callback_status", property="callbackStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="callback_count", property="callbackCount", jdbcType=JdbcType.INTEGER),
        @Result(column="callback_time", property="callbackTime", jdbcType=JdbcType.INTEGER),
        @Result(column="ser_notify_time", property="serNotifyTime", jdbcType=JdbcType.INTEGER),
        @Result(column="ser_notify_log", property="serNotifyLog", jdbcType=JdbcType.VARCHAR)
    })
    List<PayRechargeOrder> selectByExampleWithRowbounds(PayRechargeOrderCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_recharge_order`
     *
     * @mbggenerated
     */
    @SelectProvider(type=PayRechargeOrderSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR),
        @Result(column="channel", property="channel", jdbcType=JdbcType.INTEGER),
        @Result(column="gateway", property="gateway", jdbcType=JdbcType.INTEGER),
        @Result(column="gateway_ext", property="gatewayExt", jdbcType=JdbcType.VARCHAR),
        @Result(column="mer_recharge_no", property="merRechargeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ser_recharge_no", property="serRechargeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="busi_recharge_no", property="busiRechargeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="recharge_amount", property="rechargeAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="consume_id", property="consumeId", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.INTEGER),
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.INTEGER),
        @Result(column="subject", property="subject", jdbcType=JdbcType.VARCHAR),
        @Result(column="body", property="body", jdbcType=JdbcType.VARCHAR),
        @Result(column="gateway_account", property="gatewayAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="seller_partner", property="sellerPartner", jdbcType=JdbcType.VARCHAR),
        @Result(column="city_id", property="cityId", jdbcType=JdbcType.INTEGER),
        @Result(column="refund_count", property="refundCount", jdbcType=JdbcType.INTEGER),
        @Result(column="expire_time", property="expireTime", jdbcType=JdbcType.INTEGER),
        @Result(column="refund_amount", property="refundAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="refund_time", property="refundTime", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.INTEGER),
        @Result(column="mobile_no", property="mobileNo", jdbcType=JdbcType.CHAR),
        @Result(column="bank_code", property="bankCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="plat", property="plat", jdbcType=JdbcType.INTEGER),
        @Result(column="plat_ext", property="platExt", jdbcType=JdbcType.VARCHAR),
        @Result(column="return_url", property="returnUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="callback_url", property="callbackUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="callback_status", property="callbackStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="callback_count", property="callbackCount", jdbcType=JdbcType.INTEGER),
        @Result(column="callback_time", property="callbackTime", jdbcType=JdbcType.INTEGER),
        @Result(column="ser_notify_time", property="serNotifyTime", jdbcType=JdbcType.INTEGER),
        @Result(column="ser_notify_log", property="serNotifyLog", jdbcType=JdbcType.VARCHAR)
    })
    List<PayRechargeOrder> selectByExample(PayRechargeOrderCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_recharge_order`
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "id, user_id, account_id, channel, gateway, gateway_ext, mer_recharge_no, ser_recharge_no, ",
        "busi_recharge_no, recharge_amount, consume_id, status, create_time, pay_time, ",
        "subject, body, gateway_account, seller_partner, city_id, refund_count, expire_time, ",
        "refund_amount, refund_time, update_time, mobile_no, bank_code, plat, plat_ext, ",
        "return_url, callback_url, callback_status, callback_count, callback_time, ser_notify_time, ",
        "ser_notify_log",
        "from `pay_recharge_order`",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR),
        @Result(column="channel", property="channel", jdbcType=JdbcType.INTEGER),
        @Result(column="gateway", property="gateway", jdbcType=JdbcType.INTEGER),
        @Result(column="gateway_ext", property="gatewayExt", jdbcType=JdbcType.VARCHAR),
        @Result(column="mer_recharge_no", property="merRechargeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ser_recharge_no", property="serRechargeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="busi_recharge_no", property="busiRechargeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="recharge_amount", property="rechargeAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="consume_id", property="consumeId", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.INTEGER),
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.INTEGER),
        @Result(column="subject", property="subject", jdbcType=JdbcType.VARCHAR),
        @Result(column="body", property="body", jdbcType=JdbcType.VARCHAR),
        @Result(column="gateway_account", property="gatewayAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="seller_partner", property="sellerPartner", jdbcType=JdbcType.VARCHAR),
        @Result(column="city_id", property="cityId", jdbcType=JdbcType.INTEGER),
        @Result(column="refund_count", property="refundCount", jdbcType=JdbcType.INTEGER),
        @Result(column="expire_time", property="expireTime", jdbcType=JdbcType.INTEGER),
        @Result(column="refund_amount", property="refundAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="refund_time", property="refundTime", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.INTEGER),
        @Result(column="mobile_no", property="mobileNo", jdbcType=JdbcType.CHAR),
        @Result(column="bank_code", property="bankCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="plat", property="plat", jdbcType=JdbcType.INTEGER),
        @Result(column="plat_ext", property="platExt", jdbcType=JdbcType.VARCHAR),
        @Result(column="return_url", property="returnUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="callback_url", property="callbackUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="callback_status", property="callbackStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="callback_count", property="callbackCount", jdbcType=JdbcType.INTEGER),
        @Result(column="callback_time", property="callbackTime", jdbcType=JdbcType.INTEGER),
        @Result(column="ser_notify_time", property="serNotifyTime", jdbcType=JdbcType.INTEGER),
        @Result(column="ser_notify_log", property="serNotifyLog", jdbcType=JdbcType.VARCHAR)
    })
    PayRechargeOrder selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_recharge_order`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=PayRechargeOrderSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") PayRechargeOrder record, @Param("example") PayRechargeOrderCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_recharge_order`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=PayRechargeOrderSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") PayRechargeOrder record, @Param("example") PayRechargeOrderCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_recharge_order`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=PayRechargeOrderSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PayRechargeOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_recharge_order`
     *
     * @mbggenerated
     */
    @Update({
        "update `pay_recharge_order`",
        "set user_id = #{userId,jdbcType=VARCHAR},",
          "account_id = #{accountId,jdbcType=VARCHAR},",
          "channel = #{channel,jdbcType=INTEGER},",
          "gateway = #{gateway,jdbcType=INTEGER},",
          "gateway_ext = #{gatewayExt,jdbcType=VARCHAR},",
          "mer_recharge_no = #{merRechargeNo,jdbcType=VARCHAR},",
          "ser_recharge_no = #{serRechargeNo,jdbcType=VARCHAR},",
          "busi_recharge_no = #{busiRechargeNo,jdbcType=VARCHAR},",
          "recharge_amount = #{rechargeAmount,jdbcType=INTEGER},",
          "consume_id = #{consumeId,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=INTEGER},",
          "pay_time = #{payTime,jdbcType=INTEGER},",
          "subject = #{subject,jdbcType=VARCHAR},",
          "body = #{body,jdbcType=VARCHAR},",
          "gateway_account = #{gatewayAccount,jdbcType=VARCHAR},",
          "seller_partner = #{sellerPartner,jdbcType=VARCHAR},",
          "city_id = #{cityId,jdbcType=INTEGER},",
          "refund_count = #{refundCount,jdbcType=INTEGER},",
          "expire_time = #{expireTime,jdbcType=INTEGER},",
          "refund_amount = #{refundAmount,jdbcType=INTEGER},",
          "refund_time = #{refundTime,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=INTEGER},",
          "mobile_no = #{mobileNo,jdbcType=CHAR},",
          "bank_code = #{bankCode,jdbcType=VARCHAR},",
          "plat = #{plat,jdbcType=INTEGER},",
          "plat_ext = #{platExt,jdbcType=VARCHAR},",
          "return_url = #{returnUrl,jdbcType=VARCHAR},",
          "callback_url = #{callbackUrl,jdbcType=VARCHAR},",
          "callback_status = #{callbackStatus,jdbcType=INTEGER},",
          "callback_count = #{callbackCount,jdbcType=INTEGER},",
          "callback_time = #{callbackTime,jdbcType=INTEGER},",
          "ser_notify_time = #{serNotifyTime,jdbcType=INTEGER},",
          "ser_notify_log = #{serNotifyLog,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(PayRechargeOrder record);
}