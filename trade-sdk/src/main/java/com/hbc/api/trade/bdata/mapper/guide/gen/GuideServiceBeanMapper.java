package com.hbc.api.trade.bdata.mapper.guide.gen;

import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideServiceBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideServiceBeanExample;
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

public interface GuideServiceBeanMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    @SelectProvider(type=GuideServiceBeanSqlProvider.class, method="countByExample")
    int countByExample(GuideServiceBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    @DeleteProvider(type=GuideServiceBeanSqlProvider.class, method="deleteByExample")
    int deleteByExample(GuideServiceBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    @Delete({
        "delete from `guide_service`",
        "where guide_service_id = #{guideServiceId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer guideServiceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    @Insert({
        "insert into `guide_service` (guide_service_id, guide_id, ",
        "lang_codes, expFamily, ",
        "expBusiness, expCross, ",
        "style, expSpot, ",
        "expSchool, expShopping, ",
        "expHistory, expTraffic, ",
        "expFood, expLaw, ",
        "expCulture, expHotel, ",
        "expPoi, update_time, ",
        "create_time)",
        "values (#{guideServiceId,jdbcType=INTEGER}, #{guideId,jdbcType=VARCHAR}, ",
        "#{langCodes,jdbcType=VARCHAR}, #{expfamily,jdbcType=TINYINT}, ",
        "#{expbusiness,jdbcType=TINYINT}, #{expcross,jdbcType=TINYINT}, ",
        "#{style,jdbcType=TINYINT}, #{expspot,jdbcType=TINYINT}, ",
        "#{expschool,jdbcType=TINYINT}, #{expshopping,jdbcType=TINYINT}, ",
        "#{exphistory,jdbcType=TINYINT}, #{exptraffic,jdbcType=TINYINT}, ",
        "#{expfood,jdbcType=TINYINT}, #{explaw,jdbcType=TINYINT}, ",
        "#{expculture,jdbcType=TINYINT}, #{exphotel,jdbcType=TINYINT}, ",
        "#{exppoi,jdbcType=TINYINT}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(GuideServiceBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    @InsertProvider(type=GuideServiceBeanSqlProvider.class, method="insertSelective")
    int insertSelective(GuideServiceBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    @SelectProvider(type=GuideServiceBeanSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="guide_service_id", property="guideServiceId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="guide_id", property="guideId", jdbcType=JdbcType.VARCHAR),
        @Result(column="lang_codes", property="langCodes", jdbcType=JdbcType.VARCHAR),
        @Result(column="expFamily", property="expfamily", jdbcType=JdbcType.TINYINT),
        @Result(column="expBusiness", property="expbusiness", jdbcType=JdbcType.TINYINT),
        @Result(column="expCross", property="expcross", jdbcType=JdbcType.TINYINT),
        @Result(column="style", property="style", jdbcType=JdbcType.TINYINT),
        @Result(column="expSpot", property="expspot", jdbcType=JdbcType.TINYINT),
        @Result(column="expSchool", property="expschool", jdbcType=JdbcType.TINYINT),
        @Result(column="expShopping", property="expshopping", jdbcType=JdbcType.TINYINT),
        @Result(column="expHistory", property="exphistory", jdbcType=JdbcType.TINYINT),
        @Result(column="expTraffic", property="exptraffic", jdbcType=JdbcType.TINYINT),
        @Result(column="expFood", property="expfood", jdbcType=JdbcType.TINYINT),
        @Result(column="expLaw", property="explaw", jdbcType=JdbcType.TINYINT),
        @Result(column="expCulture", property="expculture", jdbcType=JdbcType.TINYINT),
        @Result(column="expHotel", property="exphotel", jdbcType=JdbcType.TINYINT),
        @Result(column="expPoi", property="exppoi", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<GuideServiceBean> selectByExample(GuideServiceBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "guide_service_id, guide_id, lang_codes, expFamily, expBusiness, expCross, style, ",
        "expSpot, expSchool, expShopping, expHistory, expTraffic, expFood, expLaw, expCulture, ",
        "expHotel, expPoi, update_time, create_time",
        "from `guide_service`",
        "where guide_service_id = #{guideServiceId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="guide_service_id", property="guideServiceId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="guide_id", property="guideId", jdbcType=JdbcType.VARCHAR),
        @Result(column="lang_codes", property="langCodes", jdbcType=JdbcType.VARCHAR),
        @Result(column="expFamily", property="expfamily", jdbcType=JdbcType.TINYINT),
        @Result(column="expBusiness", property="expbusiness", jdbcType=JdbcType.TINYINT),
        @Result(column="expCross", property="expcross", jdbcType=JdbcType.TINYINT),
        @Result(column="style", property="style", jdbcType=JdbcType.TINYINT),
        @Result(column="expSpot", property="expspot", jdbcType=JdbcType.TINYINT),
        @Result(column="expSchool", property="expschool", jdbcType=JdbcType.TINYINT),
        @Result(column="expShopping", property="expshopping", jdbcType=JdbcType.TINYINT),
        @Result(column="expHistory", property="exphistory", jdbcType=JdbcType.TINYINT),
        @Result(column="expTraffic", property="exptraffic", jdbcType=JdbcType.TINYINT),
        @Result(column="expFood", property="expfood", jdbcType=JdbcType.TINYINT),
        @Result(column="expLaw", property="explaw", jdbcType=JdbcType.TINYINT),
        @Result(column="expCulture", property="expculture", jdbcType=JdbcType.TINYINT),
        @Result(column="expHotel", property="exphotel", jdbcType=JdbcType.TINYINT),
        @Result(column="expPoi", property="exppoi", jdbcType=JdbcType.TINYINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    GuideServiceBean selectByPrimaryKey(Integer guideServiceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=GuideServiceBeanSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") GuideServiceBean record, @Param("example") GuideServiceBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=GuideServiceBeanSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") GuideServiceBean record, @Param("example") GuideServiceBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=GuideServiceBeanSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(GuideServiceBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    @Update({
        "update `guide_service`",
        "set guide_id = #{guideId,jdbcType=VARCHAR},",
          "lang_codes = #{langCodes,jdbcType=VARCHAR},",
          "expFamily = #{expfamily,jdbcType=TINYINT},",
          "expBusiness = #{expbusiness,jdbcType=TINYINT},",
          "expCross = #{expcross,jdbcType=TINYINT},",
          "style = #{style,jdbcType=TINYINT},",
          "expSpot = #{expspot,jdbcType=TINYINT},",
          "expSchool = #{expschool,jdbcType=TINYINT},",
          "expShopping = #{expshopping,jdbcType=TINYINT},",
          "expHistory = #{exphistory,jdbcType=TINYINT},",
          "expTraffic = #{exptraffic,jdbcType=TINYINT},",
          "expFood = #{expfood,jdbcType=TINYINT},",
          "expLaw = #{explaw,jdbcType=TINYINT},",
          "expCulture = #{expculture,jdbcType=TINYINT},",
          "expHotel = #{exphotel,jdbcType=TINYINT},",
          "expPoi = #{exppoi,jdbcType=TINYINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where guide_service_id = #{guideServiceId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(GuideServiceBean record);
}