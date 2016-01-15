package com.hbc.api.fund.account.mapping.gen;

import com.hbc.api.fund.account.mapping.gen.bean.FundAccountLog;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccountLogExample;
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
import org.apache.ibatis.type.JdbcType;

public interface FundAccountLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_account_log`
     *
     * @mbggenerated
     */
    @SelectProvider(type=FundAccountLogSqlProvider.class, method="countByExample")
    int countByExample(FundAccountLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_account_log`
     *
     * @mbggenerated
     */
    @DeleteProvider(type=FundAccountLogSqlProvider.class, method="deleteByExample")
    int deleteByExample(FundAccountLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_account_log`
     *
     * @mbggenerated
     */
    @Delete({
        "delete from `fund_account_log`",
        "where log_no = #{logNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String logNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_account_log`
     *
     * @mbggenerated
     */
    @Insert({
        "insert into `fund_account_log` (log_no, account_no, ",
        "target_account_no, amount_cur, ",
        "amount_cg, chang_amount, ",
        "change_type, order_no, ",
        "pay_no, refund_no, ",
        "op_user_id, op_user_name, ",
        "user_id, user_name, ",
        "biz_type, biz_status, ",
        "biz_message, biz_time, ",
        "comment, remark1, ",
        "remark2, remark3, ",
        "remark4, remark5, ",
        "update_time, create_time)",
        "values (#{logNo,jdbcType=VARCHAR}, #{accountNo,jdbcType=VARCHAR}, ",
        "#{targetAccountNo,jdbcType=VARCHAR}, #{amountCur,jdbcType=DOUBLE}, ",
        "#{amountCg,jdbcType=DOUBLE}, #{changAmount,jdbcType=DOUBLE}, ",
        "#{changeType,jdbcType=INTEGER}, #{orderNo,jdbcType=VARCHAR}, ",
        "#{payNo,jdbcType=VARCHAR}, #{refundNo,jdbcType=VARCHAR}, ",
        "#{opUserId,jdbcType=VARCHAR}, #{opUserName,jdbcType=VARCHAR}, ",
        "#{userId,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR}, ",
        "#{bizType,jdbcType=INTEGER}, #{bizStatus,jdbcType=INTEGER}, ",
        "#{bizMessage,jdbcType=VARCHAR}, #{bizTime,jdbcType=TIMESTAMP}, ",
        "#{comment,jdbcType=VARCHAR}, #{remark1,jdbcType=VARCHAR}, ",
        "#{remark2,jdbcType=VARCHAR}, #{remark3,jdbcType=VARCHAR}, ",
        "#{remark4,jdbcType=VARCHAR}, #{remark5,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(FundAccountLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_account_log`
     *
     * @mbggenerated
     */
    @InsertProvider(type=FundAccountLogSqlProvider.class, method="insertSelective")
    int insertSelective(FundAccountLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_account_log`
     *
     * @mbggenerated
     */
    @SelectProvider(type=FundAccountLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="log_no", property="logNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="account_no", property="accountNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="target_account_no", property="targetAccountNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount_cur", property="amountCur", jdbcType=JdbcType.DOUBLE),
        @Result(column="amount_cg", property="amountCg", jdbcType=JdbcType.DOUBLE),
        @Result(column="chang_amount", property="changAmount", jdbcType=JdbcType.DOUBLE),
        @Result(column="change_type", property="changeType", jdbcType=JdbcType.INTEGER),
        @Result(column="order_no", property="orderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_no", property="payNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_no", property="refundNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="op_user_id", property="opUserId", jdbcType=JdbcType.VARCHAR),
        @Result(column="op_user_name", property="opUserName", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="biz_type", property="bizType", jdbcType=JdbcType.INTEGER),
        @Result(column="biz_status", property="bizStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="biz_message", property="bizMessage", jdbcType=JdbcType.VARCHAR),
        @Result(column="biz_time", property="bizTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark1", property="remark1", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark2", property="remark2", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark3", property="remark3", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark4", property="remark4", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark5", property="remark5", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FundAccountLog> selectByExample(FundAccountLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_account_log`
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "log_no, account_no, target_account_no, amount_cur, amount_cg, chang_amount, ",
        "change_type, order_no, pay_no, refund_no, op_user_id, op_user_name, user_id, ",
        "user_name, biz_type, biz_status, biz_message, biz_time, comment, remark1, remark2, ",
        "remark3, remark4, remark5, update_time, create_time",
        "from `fund_account_log`",
        "where log_no = #{logNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="log_no", property="logNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="account_no", property="accountNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="target_account_no", property="targetAccountNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount_cur", property="amountCur", jdbcType=JdbcType.DOUBLE),
        @Result(column="amount_cg", property="amountCg", jdbcType=JdbcType.DOUBLE),
        @Result(column="chang_amount", property="changAmount", jdbcType=JdbcType.DOUBLE),
        @Result(column="change_type", property="changeType", jdbcType=JdbcType.INTEGER),
        @Result(column="order_no", property="orderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_no", property="payNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_no", property="refundNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="op_user_id", property="opUserId", jdbcType=JdbcType.VARCHAR),
        @Result(column="op_user_name", property="opUserName", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="biz_type", property="bizType", jdbcType=JdbcType.INTEGER),
        @Result(column="biz_status", property="bizStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="biz_message", property="bizMessage", jdbcType=JdbcType.VARCHAR),
        @Result(column="biz_time", property="bizTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark1", property="remark1", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark2", property="remark2", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark3", property="remark3", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark4", property="remark4", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark5", property="remark5", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FundAccountLog selectByPrimaryKey(String logNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_account_log`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=FundAccountLogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FundAccountLog record, @Param("example") FundAccountLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_account_log`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=FundAccountLogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FundAccountLog record, @Param("example") FundAccountLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_account_log`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=FundAccountLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FundAccountLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_account_log`
     *
     * @mbggenerated
     */
    @Update({
        "update `fund_account_log`",
        "set account_no = #{accountNo,jdbcType=VARCHAR},",
          "amount_cur = #{amountCur,jdbcType=DOUBLE},",
          "amount_cg = #{amountCg,jdbcType=DOUBLE},",
          "chang_amount = #{changAmount,jdbcType=DOUBLE},",
          "change_type = #{changeType,jdbcType=INTEGER},",
          "order_no = #{orderNo,jdbcType=VARCHAR},",
          "pay_no = #{payNo,jdbcType=VARCHAR},",
          "refund_no = #{refundNo,jdbcType=VARCHAR},",
          "op_user_id = #{opUserId,jdbcType=VARCHAR},",
          "op_user_name = #{opUserName,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=VARCHAR},",
          "user_name = #{userName,jdbcType=VARCHAR},",
          "biz_type = #{bizType,jdbcType=INTEGER},",
          "biz_status = #{bizStatus,jdbcType=INTEGER},",
          "biz_message = #{bizMessage,jdbcType=VARCHAR},",
          "biz_time = #{bizTime,jdbcType=TIMESTAMP},",
          "comment = #{comment,jdbcType=VARCHAR},",
          "remark1 = #{remark1,jdbcType=VARCHAR},",
          "remark2 = #{remark2,jdbcType=VARCHAR},",
          "remark3 = #{remark3,jdbcType=VARCHAR},",
          "remark4 = #{remark4,jdbcType=VARCHAR},",
          "remark5 = #{remark5,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where log_no = #{logNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(FundAccountLog record);
}