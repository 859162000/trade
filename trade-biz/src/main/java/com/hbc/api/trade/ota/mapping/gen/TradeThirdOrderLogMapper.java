package com.hbc.api.trade.ota.mapping.gen;

import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrderLog;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrderLogExample;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrderLogWithBLOBs;
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

public interface TradeThirdOrderLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_third_order_log`
     *
     * @mbggenerated
     */
    @SelectProvider(type=TradeThirdOrderLogSqlProvider.class, method="countByExample")
    int countByExample(TradeThirdOrderLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_third_order_log`
     *
     * @mbggenerated
     */
    @DeleteProvider(type=TradeThirdOrderLogSqlProvider.class, method="deleteByExample")
    int deleteByExample(TradeThirdOrderLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_third_order_log`
     *
     * @mbggenerated
     */
    @Delete({
        "delete from `trade_third_order_log`",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_third_order_log`
     *
     * @mbggenerated
     */
    @Insert({
        "insert into `trade_third_order_log` (id, order_no, ",
        "third_partner, third_trade_no, ",
        "log_type, request_url, ",
        "create_time, request_data, ",
        "response_data)",
        "values (#{id,jdbcType=INTEGER}, #{orderNo,jdbcType=VARCHAR}, ",
        "#{thirdPartner,jdbcType=INTEGER}, #{thirdTradeNo,jdbcType=VARCHAR}, ",
        "#{logType,jdbcType=VARCHAR}, #{requestUrl,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{requestData,jdbcType=LONGVARCHAR}, ",
        "#{responseData,jdbcType=LONGVARCHAR})"
    })
    int insert(TradeThirdOrderLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_third_order_log`
     *
     * @mbggenerated
     */
    @InsertProvider(type=TradeThirdOrderLogSqlProvider.class, method="insertSelective")
    int insertSelective(TradeThirdOrderLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_third_order_log`
     *
     * @mbggenerated
     */
    @SelectProvider(type=TradeThirdOrderLogSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_no", property="orderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="third_partner", property="thirdPartner", jdbcType=JdbcType.INTEGER),
        @Result(column="third_trade_no", property="thirdTradeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="log_type", property="logType", jdbcType=JdbcType.VARCHAR),
        @Result(column="request_url", property="requestUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="request_data", property="requestData", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="response_data", property="responseData", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<TradeThirdOrderLogWithBLOBs> selectByExampleWithBLOBs(TradeThirdOrderLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_third_order_log`
     *
     * @mbggenerated
     */
    @SelectProvider(type=TradeThirdOrderLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_no", property="orderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="third_partner", property="thirdPartner", jdbcType=JdbcType.INTEGER),
        @Result(column="third_trade_no", property="thirdTradeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="log_type", property="logType", jdbcType=JdbcType.VARCHAR),
        @Result(column="request_url", property="requestUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TradeThirdOrderLog> selectByExample(TradeThirdOrderLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_third_order_log`
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "id, order_no, third_partner, third_trade_no, log_type, request_url, create_time, ",
        "request_data, response_data",
        "from `trade_third_order_log`",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_no", property="orderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="third_partner", property="thirdPartner", jdbcType=JdbcType.INTEGER),
        @Result(column="third_trade_no", property="thirdTradeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="log_type", property="logType", jdbcType=JdbcType.VARCHAR),
        @Result(column="request_url", property="requestUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="request_data", property="requestData", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="response_data", property="responseData", jdbcType=JdbcType.LONGVARCHAR)
    })
    TradeThirdOrderLogWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_third_order_log`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=TradeThirdOrderLogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TradeThirdOrderLogWithBLOBs record, @Param("example") TradeThirdOrderLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_third_order_log`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=TradeThirdOrderLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TradeThirdOrderLogWithBLOBs record);
}