package com.hbc.api.trade.bdata.mapper.guide.gen;

import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCar;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCarExample;
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

public interface GuideCarMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car`
     *
     * @mbggenerated
     */
    @SelectProvider(type=GuideCarSqlProvider.class, method="countByExample")
    int countByExample(GuideCarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car`
     *
     * @mbggenerated
     */
    @DeleteProvider(type=GuideCarSqlProvider.class, method="deleteByExample")
    int deleteByExample(GuideCarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car`
     *
     * @mbggenerated
     */
    @Delete({
        "delete from `guide_car`",
        "where guide_car_id = #{guideCarId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer guideCarId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car`
     *
     * @mbggenerated
     */
    @Insert({
        "insert into `guide_car` (guide_car_id, guide_id, ",
        "car_id, car_licence_no, ",
        "car_birth, equipment, ",
        "other_car, is_default, ",
        "car_name, car_type, ",
        "car_class, car_brand_id, ",
        "car_brand_name, car_guest_num, ",
        "car_luggage_num, car_seat_num, ",
        "set_mode, comment, ",
        "update_time, create_time)",
        "values (#{guideCarId,jdbcType=INTEGER}, #{guideId,jdbcType=VARCHAR}, ",
        "#{carId,jdbcType=INTEGER}, #{carLicenceNo,jdbcType=VARCHAR}, ",
        "#{carBirth,jdbcType=DATE}, #{equipment,jdbcType=VARCHAR}, ",
        "#{otherCar,jdbcType=VARCHAR}, #{isDefault,jdbcType=TINYINT}, ",
        "#{carName,jdbcType=VARCHAR}, #{carType,jdbcType=TINYINT}, ",
        "#{carClass,jdbcType=TINYINT}, #{carBrandId,jdbcType=INTEGER}, ",
        "#{carBrandName,jdbcType=VARCHAR}, #{carGuestNum,jdbcType=INTEGER}, ",
        "#{carLuggageNum,jdbcType=INTEGER}, #{carSeatNum,jdbcType=INTEGER}, ",
        "#{setMode,jdbcType=TINYINT}, #{comment,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(GuideCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car`
     *
     * @mbggenerated
     */
    @InsertProvider(type=GuideCarSqlProvider.class, method="insertSelective")
    int insertSelective(GuideCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car`
     *
     * @mbggenerated
     */
    @SelectProvider(type=GuideCarSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="guide_car_id", property="guideCarId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="guide_id", property="guideId", jdbcType=JdbcType.VARCHAR),
        @Result(column="car_id", property="carId", jdbcType=JdbcType.INTEGER),
        @Result(column="car_licence_no", property="carLicenceNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="car_birth", property="carBirth", jdbcType=JdbcType.DATE),
        @Result(column="equipment", property="equipment", jdbcType=JdbcType.VARCHAR),
        @Result(column="other_car", property="otherCar", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_default", property="isDefault", jdbcType=JdbcType.TINYINT),
        @Result(column="car_name", property="carName", jdbcType=JdbcType.VARCHAR),
        @Result(column="car_type", property="carType", jdbcType=JdbcType.TINYINT),
        @Result(column="car_class", property="carClass", jdbcType=JdbcType.TINYINT),
        @Result(column="car_brand_id", property="carBrandId", jdbcType=JdbcType.INTEGER),
        @Result(column="car_brand_name", property="carBrandName", jdbcType=JdbcType.VARCHAR),
        @Result(column="car_guest_num", property="carGuestNum", jdbcType=JdbcType.INTEGER),
        @Result(column="car_luggage_num", property="carLuggageNum", jdbcType=JdbcType.INTEGER),
        @Result(column="car_seat_num", property="carSeatNum", jdbcType=JdbcType.INTEGER),
        @Result(column="set_mode", property="setMode", jdbcType=JdbcType.TINYINT),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<GuideCar> selectByExample(GuideCarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car`
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "guide_car_id, guide_id, car_id, car_licence_no, car_birth, equipment, other_car, ",
        "is_default, car_name, car_type, car_class, car_brand_id, car_brand_name, car_guest_num, ",
        "car_luggage_num, car_seat_num, set_mode, comment, update_time, create_time",
        "from `guide_car`",
        "where guide_car_id = #{guideCarId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="guide_car_id", property="guideCarId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="guide_id", property="guideId", jdbcType=JdbcType.VARCHAR),
        @Result(column="car_id", property="carId", jdbcType=JdbcType.INTEGER),
        @Result(column="car_licence_no", property="carLicenceNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="car_birth", property="carBirth", jdbcType=JdbcType.DATE),
        @Result(column="equipment", property="equipment", jdbcType=JdbcType.VARCHAR),
        @Result(column="other_car", property="otherCar", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_default", property="isDefault", jdbcType=JdbcType.TINYINT),
        @Result(column="car_name", property="carName", jdbcType=JdbcType.VARCHAR),
        @Result(column="car_type", property="carType", jdbcType=JdbcType.TINYINT),
        @Result(column="car_class", property="carClass", jdbcType=JdbcType.TINYINT),
        @Result(column="car_brand_id", property="carBrandId", jdbcType=JdbcType.INTEGER),
        @Result(column="car_brand_name", property="carBrandName", jdbcType=JdbcType.VARCHAR),
        @Result(column="car_guest_num", property="carGuestNum", jdbcType=JdbcType.INTEGER),
        @Result(column="car_luggage_num", property="carLuggageNum", jdbcType=JdbcType.INTEGER),
        @Result(column="car_seat_num", property="carSeatNum", jdbcType=JdbcType.INTEGER),
        @Result(column="set_mode", property="setMode", jdbcType=JdbcType.TINYINT),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    GuideCar selectByPrimaryKey(Integer guideCarId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=GuideCarSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") GuideCar record, @Param("example") GuideCarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=GuideCarSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") GuideCar record, @Param("example") GuideCarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=GuideCarSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(GuideCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car`
     *
     * @mbggenerated
     */
    @Update({
        "update `guide_car`",
        "set guide_id = #{guideId,jdbcType=VARCHAR},",
          "car_id = #{carId,jdbcType=INTEGER},",
          "car_licence_no = #{carLicenceNo,jdbcType=VARCHAR},",
          "car_birth = #{carBirth,jdbcType=DATE},",
          "equipment = #{equipment,jdbcType=VARCHAR},",
          "other_car = #{otherCar,jdbcType=VARCHAR},",
          "is_default = #{isDefault,jdbcType=TINYINT},",
          "car_name = #{carName,jdbcType=VARCHAR},",
          "car_type = #{carType,jdbcType=TINYINT},",
          "car_class = #{carClass,jdbcType=TINYINT},",
          "car_brand_id = #{carBrandId,jdbcType=INTEGER},",
          "car_brand_name = #{carBrandName,jdbcType=VARCHAR},",
          "car_guest_num = #{carGuestNum,jdbcType=INTEGER},",
          "car_luggage_num = #{carLuggageNum,jdbcType=INTEGER},",
          "car_seat_num = #{carSeatNum,jdbcType=INTEGER},",
          "set_mode = #{setMode,jdbcType=TINYINT},",
          "comment = #{comment,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where guide_car_id = #{guideCarId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(GuideCar record);
}