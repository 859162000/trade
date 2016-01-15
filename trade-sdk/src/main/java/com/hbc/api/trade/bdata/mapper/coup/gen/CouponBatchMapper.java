package com.hbc.api.trade.bdata.mapper.coup.gen;

import com.hbc.api.trade.bdata.mapper.coup.gen.bean.CouponBatch;
import com.hbc.api.trade.bdata.mapper.coup.gen.bean.CouponBatchExample;
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

public interface CouponBatchMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_batch`
     *
     * @mbggenerated
     */
    @SelectProvider(type=CouponBatchSqlProvider.class, method="countByExample")
    int countByExample(CouponBatchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_batch`
     *
     * @mbggenerated
     */
    @DeleteProvider(type=CouponBatchSqlProvider.class, method="deleteByExample")
    int deleteByExample(CouponBatchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_batch`
     *
     * @mbggenerated
     */
    @Delete({
        "delete from `coupon_batch`",
        "where coupon_batch_id = #{couponBatchId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String couponBatchId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_batch`
     *
     * @mbggenerated
     */
    @Insert({
        "insert into `coupon_batch` (coupon_batch_id, batch_no, ",
        "batch_name, batch_channel, ",
        "coupon_type, coupon_total, ",
        "coupon_price, coupon_discount, ",
        "days_limit, end_date, ",
        "meet_price, distance_upper_limit, ",
        "duration_upper_limit, remark, ",
        "coupon_pic, coupon_url, ",
        "bind_count, use_count, ",
        "allow_transfer_count, coupon_scop_json, ",
        "district_names, crop_types, ",
        "update_time, create_time)",
        "values (#{couponBatchId,jdbcType=VARCHAR}, #{batchNo,jdbcType=VARCHAR}, ",
        "#{batchName,jdbcType=VARCHAR}, #{batchChannel,jdbcType=VARCHAR}, ",
        "#{couponType,jdbcType=TINYINT}, #{couponTotal,jdbcType=INTEGER}, ",
        "#{couponPrice,jdbcType=INTEGER}, #{couponDiscount,jdbcType=INTEGER}, ",
        "#{daysLimit,jdbcType=INTEGER}, #{endDate,jdbcType=TIMESTAMP}, ",
        "#{meetPrice,jdbcType=INTEGER}, #{distanceUpperLimit,jdbcType=INTEGER}, ",
        "#{durationUpperLimit,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR}, ",
        "#{couponPic,jdbcType=VARCHAR}, #{couponUrl,jdbcType=VARCHAR}, ",
        "#{bindCount,jdbcType=INTEGER}, #{useCount,jdbcType=INTEGER}, ",
        "#{allowTransferCount,jdbcType=INTEGER}, #{couponScopJson,jdbcType=VARCHAR}, ",
        "#{districtNames,jdbcType=VARCHAR}, #{cropTypes,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(CouponBatch record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_batch`
     *
     * @mbggenerated
     */
    @InsertProvider(type=CouponBatchSqlProvider.class, method="insertSelective")
    int insertSelective(CouponBatch record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_batch`
     *
     * @mbggenerated
     */
    @SelectProvider(type=CouponBatchSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="coupon_batch_id", property="couponBatchId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="batch_no", property="batchNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="batch_name", property="batchName", jdbcType=JdbcType.VARCHAR),
        @Result(column="batch_channel", property="batchChannel", jdbcType=JdbcType.VARCHAR),
        @Result(column="coupon_type", property="couponType", jdbcType=JdbcType.TINYINT),
        @Result(column="coupon_total", property="couponTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="coupon_price", property="couponPrice", jdbcType=JdbcType.INTEGER),
        @Result(column="coupon_discount", property="couponDiscount", jdbcType=JdbcType.INTEGER),
        @Result(column="days_limit", property="daysLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="meet_price", property="meetPrice", jdbcType=JdbcType.INTEGER),
        @Result(column="distance_upper_limit", property="distanceUpperLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="duration_upper_limit", property="durationUpperLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="coupon_pic", property="couponPic", jdbcType=JdbcType.VARCHAR),
        @Result(column="coupon_url", property="couponUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="bind_count", property="bindCount", jdbcType=JdbcType.INTEGER),
        @Result(column="use_count", property="useCount", jdbcType=JdbcType.INTEGER),
        @Result(column="allow_transfer_count", property="allowTransferCount", jdbcType=JdbcType.INTEGER),
        @Result(column="coupon_scop_json", property="couponScopJson", jdbcType=JdbcType.VARCHAR),
        @Result(column="district_names", property="districtNames", jdbcType=JdbcType.VARCHAR),
        @Result(column="crop_types", property="cropTypes", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CouponBatch> selectByExample(CouponBatchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_batch`
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "coupon_batch_id, batch_no, batch_name, batch_channel, coupon_type, coupon_total, ",
        "coupon_price, coupon_discount, days_limit, end_date, meet_price, distance_upper_limit, ",
        "duration_upper_limit, remark, coupon_pic, coupon_url, bind_count, use_count, ",
        "allow_transfer_count, coupon_scop_json, district_names, crop_types, update_time, ",
        "create_time",
        "from `coupon_batch`",
        "where coupon_batch_id = #{couponBatchId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="coupon_batch_id", property="couponBatchId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="batch_no", property="batchNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="batch_name", property="batchName", jdbcType=JdbcType.VARCHAR),
        @Result(column="batch_channel", property="batchChannel", jdbcType=JdbcType.VARCHAR),
        @Result(column="coupon_type", property="couponType", jdbcType=JdbcType.TINYINT),
        @Result(column="coupon_total", property="couponTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="coupon_price", property="couponPrice", jdbcType=JdbcType.INTEGER),
        @Result(column="coupon_discount", property="couponDiscount", jdbcType=JdbcType.INTEGER),
        @Result(column="days_limit", property="daysLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="meet_price", property="meetPrice", jdbcType=JdbcType.INTEGER),
        @Result(column="distance_upper_limit", property="distanceUpperLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="duration_upper_limit", property="durationUpperLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="coupon_pic", property="couponPic", jdbcType=JdbcType.VARCHAR),
        @Result(column="coupon_url", property="couponUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="bind_count", property="bindCount", jdbcType=JdbcType.INTEGER),
        @Result(column="use_count", property="useCount", jdbcType=JdbcType.INTEGER),
        @Result(column="allow_transfer_count", property="allowTransferCount", jdbcType=JdbcType.INTEGER),
        @Result(column="coupon_scop_json", property="couponScopJson", jdbcType=JdbcType.VARCHAR),
        @Result(column="district_names", property="districtNames", jdbcType=JdbcType.VARCHAR),
        @Result(column="crop_types", property="cropTypes", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    CouponBatch selectByPrimaryKey(String couponBatchId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_batch`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=CouponBatchSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CouponBatch record, @Param("example") CouponBatchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_batch`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=CouponBatchSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CouponBatch record);
}