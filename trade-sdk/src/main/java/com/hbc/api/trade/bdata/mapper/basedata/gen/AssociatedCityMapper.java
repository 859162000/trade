package com.hbc.api.trade.bdata.mapper.basedata.gen;

import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.AssociatedCity;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.AssociatedCityExample;
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

public interface AssociatedCityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `basedata_associated_cities`
     *
     * @mbggenerated
     */
    @SelectProvider(type=AssociatedCitySqlProvider.class, method="countByExample")
    int countByExample(AssociatedCityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `basedata_associated_cities`
     *
     * @mbggenerated
     */
    @DeleteProvider(type=AssociatedCitySqlProvider.class, method="deleteByExample")
    int deleteByExample(AssociatedCityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `basedata_associated_cities`
     *
     * @mbggenerated
     */
    @Delete({
        "delete from `basedata_associated_cities`",
        "where city_id = #{cityId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer cityId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `basedata_associated_cities`
     *
     * @mbggenerated
     */
    @Insert({
        "insert into `basedata_associated_cities` (city_id, country_id, ",
        "service_cities, updated_at, ",
        "created_at)",
        "values (#{cityId,jdbcType=INTEGER}, #{countryId,jdbcType=INTEGER}, ",
        "#{serviceCities,jdbcType=VARCHAR}, #{updatedAt,jdbcType=TIMESTAMP}, ",
        "#{createdAt,jdbcType=TIMESTAMP})"
    })
    int insert(AssociatedCity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `basedata_associated_cities`
     *
     * @mbggenerated
     */
    @InsertProvider(type=AssociatedCitySqlProvider.class, method="insertSelective")
    int insertSelective(AssociatedCity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `basedata_associated_cities`
     *
     * @mbggenerated
     */
    @SelectProvider(type=AssociatedCitySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="city_id", property="cityId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="country_id", property="countryId", jdbcType=JdbcType.INTEGER),
        @Result(column="service_cities", property="serviceCities", jdbcType=JdbcType.VARCHAR),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<AssociatedCity> selectByExample(AssociatedCityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `basedata_associated_cities`
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "city_id, country_id, service_cities, updated_at, created_at",
        "from `basedata_associated_cities`",
        "where city_id = #{cityId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="city_id", property="cityId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="country_id", property="countryId", jdbcType=JdbcType.INTEGER),
        @Result(column="service_cities", property="serviceCities", jdbcType=JdbcType.VARCHAR),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP)
    })
    AssociatedCity selectByPrimaryKey(Integer cityId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `basedata_associated_cities`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=AssociatedCitySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AssociatedCity record, @Param("example") AssociatedCityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `basedata_associated_cities`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=AssociatedCitySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AssociatedCity record);
}