package com.hbc.api.trade.bdata.mapper.guide.gen;

import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCarExt;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCarExtExample;
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

public interface GuideCarExtMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car_ext`
     *
     * @mbggenerated
     */
    @SelectProvider(type=GuideCarExtSqlProvider.class, method="countByExample")
    int countByExample(GuideCarExtExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car_ext`
     *
     * @mbggenerated
     */
    @DeleteProvider(type=GuideCarExtSqlProvider.class, method="deleteByExample")
    int deleteByExample(GuideCarExtExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car_ext`
     *
     * @mbggenerated
     */
    @Delete({
        "delete from `guide_car_ext`",
        "where guide_car_ext_id = #{guideCarExtId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer guideCarExtId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car_ext`
     *
     * @mbggenerated
     */
    @Insert({
        "insert into `guide_car_ext` (guide_car_ext_id, car_id, ",
        "car_brand_id, car_brand_name, ",
        "car_type, car_class, ",
        "update_time, create_time)",
        "values (#{guideCarExtId,jdbcType=INTEGER}, #{carId,jdbcType=INTEGER}, ",
        "#{carBrandId,jdbcType=INTEGER}, #{carBrandName,jdbcType=INTEGER}, ",
        "#{carType,jdbcType=INTEGER}, #{carClass,jdbcType=INTEGER}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(GuideCarExt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car_ext`
     *
     * @mbggenerated
     */
    @InsertProvider(type=GuideCarExtSqlProvider.class, method="insertSelective")
    int insertSelective(GuideCarExt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car_ext`
     *
     * @mbggenerated
     */
    @SelectProvider(type=GuideCarExtSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="guide_car_ext_id", property="guideCarExtId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="car_id", property="carId", jdbcType=JdbcType.INTEGER),
        @Result(column="car_brand_id", property="carBrandId", jdbcType=JdbcType.INTEGER),
        @Result(column="car_brand_name", property="carBrandName", jdbcType=JdbcType.INTEGER),
        @Result(column="car_type", property="carType", jdbcType=JdbcType.INTEGER),
        @Result(column="car_class", property="carClass", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<GuideCarExt> selectByExample(GuideCarExtExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car_ext`
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "guide_car_ext_id, car_id, car_brand_id, car_brand_name, car_type, car_class, ",
        "update_time, create_time",
        "from `guide_car_ext`",
        "where guide_car_ext_id = #{guideCarExtId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="guide_car_ext_id", property="guideCarExtId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="car_id", property="carId", jdbcType=JdbcType.INTEGER),
        @Result(column="car_brand_id", property="carBrandId", jdbcType=JdbcType.INTEGER),
        @Result(column="car_brand_name", property="carBrandName", jdbcType=JdbcType.INTEGER),
        @Result(column="car_type", property="carType", jdbcType=JdbcType.INTEGER),
        @Result(column="car_class", property="carClass", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    GuideCarExt selectByPrimaryKey(Integer guideCarExtId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car_ext`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=GuideCarExtSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") GuideCarExt record, @Param("example") GuideCarExtExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car_ext`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=GuideCarExtSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") GuideCarExt record, @Param("example") GuideCarExtExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car_ext`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=GuideCarExtSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(GuideCarExt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_car_ext`
     *
     * @mbggenerated
     */
    @Update({
        "update `guide_car_ext`",
        "set car_id = #{carId,jdbcType=INTEGER},",
          "car_brand_id = #{carBrandId,jdbcType=INTEGER},",
          "car_brand_name = #{carBrandName,jdbcType=INTEGER},",
          "car_type = #{carType,jdbcType=INTEGER},",
          "car_class = #{carClass,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where guide_car_ext_id = #{guideCarExtId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(GuideCarExt record);
}