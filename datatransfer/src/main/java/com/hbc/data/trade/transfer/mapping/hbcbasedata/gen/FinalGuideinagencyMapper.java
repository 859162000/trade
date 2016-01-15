package com.hbc.data.trade.transfer.mapping.hbcbasedata.gen;

import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalGuideinagency;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalGuideinagencyCriteria;
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

public interface FinalGuideinagencyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideinagency`
     *
     * @mbggenerated
     */
    @SelectProvider(type=FinalGuideinagencySqlProvider.class, method="countByExample")
    int countByExample(FinalGuideinagencyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideinagency`
     *
     * @mbggenerated
     */
    @DeleteProvider(type=FinalGuideinagencySqlProvider.class, method="deleteByExample")
    int deleteByExample(FinalGuideinagencyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideinagency`
     *
     * @mbggenerated
     */
    @Delete({
        "delete from `guideinagency`",
        "where guideInAgencyId = #{guideinagencyid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer guideinagencyid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideinagency`
     *
     * @mbggenerated
     */
    @Insert({
        "insert into `guideinagency` (guideInAgencyId, uniqueCode, ",
        "guideId, agencyId, ",
        "guideName, guideAreaCode, ",
        "guideMobile, sendTime, ",
        "usedTime, registerTime, ",
        "type, status, bindTime, ",
        "updated_at, created_at)",
        "values (#{guideinagencyid,jdbcType=INTEGER}, #{uniquecode,jdbcType=VARCHAR}, ",
        "#{guideid,jdbcType=INTEGER}, #{agencyid,jdbcType=INTEGER}, ",
        "#{guidename,jdbcType=VARCHAR}, #{guideareacode,jdbcType=VARCHAR}, ",
        "#{guidemobile,jdbcType=VARCHAR}, #{sendtime,jdbcType=TIMESTAMP}, ",
        "#{usedtime,jdbcType=TIMESTAMP}, #{registertime,jdbcType=TIMESTAMP}, ",
        "#{type,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, #{bindtime,jdbcType=DATE}, ",
        "#{updatedAt,jdbcType=TIMESTAMP}, #{createdAt,jdbcType=TIMESTAMP})"
    })
    int insert(FinalGuideinagency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideinagency`
     *
     * @mbggenerated
     */
    @InsertProvider(type=FinalGuideinagencySqlProvider.class, method="insertSelective")
    int insertSelective(FinalGuideinagency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideinagency`
     *
     * @mbggenerated
     */
    @SelectProvider(type=FinalGuideinagencySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="guideInAgencyId", property="guideinagencyid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uniqueCode", property="uniquecode", jdbcType=JdbcType.VARCHAR),
        @Result(column="guideId", property="guideid", jdbcType=JdbcType.INTEGER),
        @Result(column="agencyId", property="agencyid", jdbcType=JdbcType.INTEGER),
        @Result(column="guideName", property="guidename", jdbcType=JdbcType.VARCHAR),
        @Result(column="guideAreaCode", property="guideareacode", jdbcType=JdbcType.VARCHAR),
        @Result(column="guideMobile", property="guidemobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="sendTime", property="sendtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="usedTime", property="usedtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="registerTime", property="registertime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="bindTime", property="bindtime", jdbcType=JdbcType.DATE),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FinalGuideinagency> selectByExampleWithRowbounds(FinalGuideinagencyCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideinagency`
     *
     * @mbggenerated
     */
    @SelectProvider(type=FinalGuideinagencySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="guideInAgencyId", property="guideinagencyid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uniqueCode", property="uniquecode", jdbcType=JdbcType.VARCHAR),
        @Result(column="guideId", property="guideid", jdbcType=JdbcType.INTEGER),
        @Result(column="agencyId", property="agencyid", jdbcType=JdbcType.INTEGER),
        @Result(column="guideName", property="guidename", jdbcType=JdbcType.VARCHAR),
        @Result(column="guideAreaCode", property="guideareacode", jdbcType=JdbcType.VARCHAR),
        @Result(column="guideMobile", property="guidemobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="sendTime", property="sendtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="usedTime", property="usedtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="registerTime", property="registertime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="bindTime", property="bindtime", jdbcType=JdbcType.DATE),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FinalGuideinagency> selectByExample(FinalGuideinagencyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideinagency`
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "guideInAgencyId, uniqueCode, guideId, agencyId, guideName, guideAreaCode, guideMobile, ",
        "sendTime, usedTime, registerTime, type, status, bindTime, updated_at, created_at",
        "from `guideinagency`",
        "where guideInAgencyId = #{guideinagencyid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="guideInAgencyId", property="guideinagencyid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uniqueCode", property="uniquecode", jdbcType=JdbcType.VARCHAR),
        @Result(column="guideId", property="guideid", jdbcType=JdbcType.INTEGER),
        @Result(column="agencyId", property="agencyid", jdbcType=JdbcType.INTEGER),
        @Result(column="guideName", property="guidename", jdbcType=JdbcType.VARCHAR),
        @Result(column="guideAreaCode", property="guideareacode", jdbcType=JdbcType.VARCHAR),
        @Result(column="guideMobile", property="guidemobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="sendTime", property="sendtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="usedTime", property="usedtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="registerTime", property="registertime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="bindTime", property="bindtime", jdbcType=JdbcType.DATE),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP)
    })
    FinalGuideinagency selectByPrimaryKey(Integer guideinagencyid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideinagency`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=FinalGuideinagencySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FinalGuideinagency record, @Param("example") FinalGuideinagencyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideinagency`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=FinalGuideinagencySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FinalGuideinagency record, @Param("example") FinalGuideinagencyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideinagency`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=FinalGuideinagencySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FinalGuideinagency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideinagency`
     *
     * @mbggenerated
     */
    @Update({
        "update `guideinagency`",
        "set uniqueCode = #{uniquecode,jdbcType=VARCHAR},",
          "guideId = #{guideid,jdbcType=INTEGER},",
          "agencyId = #{agencyid,jdbcType=INTEGER},",
          "guideName = #{guidename,jdbcType=VARCHAR},",
          "guideAreaCode = #{guideareacode,jdbcType=VARCHAR},",
          "guideMobile = #{guidemobile,jdbcType=VARCHAR},",
          "sendTime = #{sendtime,jdbcType=TIMESTAMP},",
          "usedTime = #{usedtime,jdbcType=TIMESTAMP},",
          "registerTime = #{registertime,jdbcType=TIMESTAMP},",
          "type = #{type,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "bindTime = #{bindtime,jdbcType=DATE},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP}",
        "where guideInAgencyId = #{guideinagencyid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinalGuideinagency record);
}