package com.hbc.data.trade.transfer.mapping.hbcbasedata.gen;

import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgency;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgencyCriteria;
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

public interface FinalAgencyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agency`
     *
     * @mbggenerated
     */
    @SelectProvider(type=FinalAgencySqlProvider.class, method="countByExample")
    int countByExample(FinalAgencyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agency`
     *
     * @mbggenerated
     */
    @DeleteProvider(type=FinalAgencySqlProvider.class, method="deleteByExample")
    int deleteByExample(FinalAgencyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agency`
     *
     * @mbggenerated
     */
    @Delete({
        "delete from `agency`",
        "where agencyId = #{agencyid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer agencyid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agency`
     *
     * @mbggenerated
     */
    @Insert({
        "insert into `agency` (agencyId, name, ",
        "shortName, address, ",
        "birthday, placeId, ",
        "placeCityId, guideId, ",
        "bossName, ownerName, ",
        "ownerMobile, contactName, ",
        "contactMobile, finaName, ",
        "finaMobile, signDay, ",
        "sendFlag, status, ",
        "updated_at, created_at)",
        "values (#{agencyid,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{shortname,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{birthday,jdbcType=DATE}, #{placeid,jdbcType=INTEGER}, ",
        "#{placecityid,jdbcType=INTEGER}, #{guideid,jdbcType=INTEGER}, ",
        "#{bossname,jdbcType=VARCHAR}, #{ownername,jdbcType=VARCHAR}, ",
        "#{ownermobile,jdbcType=VARCHAR}, #{contactname,jdbcType=VARCHAR}, ",
        "#{contactmobile,jdbcType=VARCHAR}, #{finaname,jdbcType=VARCHAR}, ",
        "#{finamobile,jdbcType=VARCHAR}, #{signday,jdbcType=DATE}, ",
        "#{sendflag,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
        "#{updatedAt,jdbcType=TIMESTAMP}, #{createdAt,jdbcType=TIMESTAMP})"
    })
    int insert(FinalAgency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agency`
     *
     * @mbggenerated
     */
    @InsertProvider(type=FinalAgencySqlProvider.class, method="insertSelective")
    int insertSelective(FinalAgency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agency`
     *
     * @mbggenerated
     */
    @SelectProvider(type=FinalAgencySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="agencyId", property="agencyid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="shortName", property="shortname", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="placeId", property="placeid", jdbcType=JdbcType.INTEGER),
        @Result(column="placeCityId", property="placecityid", jdbcType=JdbcType.INTEGER),
        @Result(column="guideId", property="guideid", jdbcType=JdbcType.INTEGER),
        @Result(column="bossName", property="bossname", jdbcType=JdbcType.VARCHAR),
        @Result(column="ownerName", property="ownername", jdbcType=JdbcType.VARCHAR),
        @Result(column="ownerMobile", property="ownermobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactName", property="contactname", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactMobile", property="contactmobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="finaName", property="finaname", jdbcType=JdbcType.VARCHAR),
        @Result(column="finaMobile", property="finamobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="signDay", property="signday", jdbcType=JdbcType.DATE),
        @Result(column="sendFlag", property="sendflag", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FinalAgency> selectByExampleWithRowbounds(FinalAgencyCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agency`
     *
     * @mbggenerated
     */
    @SelectProvider(type=FinalAgencySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="agencyId", property="agencyid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="shortName", property="shortname", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="placeId", property="placeid", jdbcType=JdbcType.INTEGER),
        @Result(column="placeCityId", property="placecityid", jdbcType=JdbcType.INTEGER),
        @Result(column="guideId", property="guideid", jdbcType=JdbcType.INTEGER),
        @Result(column="bossName", property="bossname", jdbcType=JdbcType.VARCHAR),
        @Result(column="ownerName", property="ownername", jdbcType=JdbcType.VARCHAR),
        @Result(column="ownerMobile", property="ownermobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactName", property="contactname", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactMobile", property="contactmobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="finaName", property="finaname", jdbcType=JdbcType.VARCHAR),
        @Result(column="finaMobile", property="finamobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="signDay", property="signday", jdbcType=JdbcType.DATE),
        @Result(column="sendFlag", property="sendflag", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FinalAgency> selectByExample(FinalAgencyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agency`
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "agencyId, name, shortName, address, birthday, placeId, placeCityId, guideId, ",
        "bossName, ownerName, ownerMobile, contactName, contactMobile, finaName, finaMobile, ",
        "signDay, sendFlag, status, updated_at, created_at",
        "from `agency`",
        "where agencyId = #{agencyid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="agencyId", property="agencyid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="shortName", property="shortname", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="placeId", property="placeid", jdbcType=JdbcType.INTEGER),
        @Result(column="placeCityId", property="placecityid", jdbcType=JdbcType.INTEGER),
        @Result(column="guideId", property="guideid", jdbcType=JdbcType.INTEGER),
        @Result(column="bossName", property="bossname", jdbcType=JdbcType.VARCHAR),
        @Result(column="ownerName", property="ownername", jdbcType=JdbcType.VARCHAR),
        @Result(column="ownerMobile", property="ownermobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactName", property="contactname", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactMobile", property="contactmobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="finaName", property="finaname", jdbcType=JdbcType.VARCHAR),
        @Result(column="finaMobile", property="finamobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="signDay", property="signday", jdbcType=JdbcType.DATE),
        @Result(column="sendFlag", property="sendflag", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP)
    })
    FinalAgency selectByPrimaryKey(Integer agencyid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agency`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=FinalAgencySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FinalAgency record, @Param("example") FinalAgencyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agency`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=FinalAgencySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FinalAgency record, @Param("example") FinalAgencyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agency`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=FinalAgencySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FinalAgency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agency`
     *
     * @mbggenerated
     */
    @Update({
        "update `agency`",
        "set name = #{name,jdbcType=VARCHAR},",
          "shortName = #{shortname,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "birthday = #{birthday,jdbcType=DATE},",
          "placeId = #{placeid,jdbcType=INTEGER},",
          "placeCityId = #{placecityid,jdbcType=INTEGER},",
          "guideId = #{guideid,jdbcType=INTEGER},",
          "bossName = #{bossname,jdbcType=VARCHAR},",
          "ownerName = #{ownername,jdbcType=VARCHAR},",
          "ownerMobile = #{ownermobile,jdbcType=VARCHAR},",
          "contactName = #{contactname,jdbcType=VARCHAR},",
          "contactMobile = #{contactmobile,jdbcType=VARCHAR},",
          "finaName = #{finaname,jdbcType=VARCHAR},",
          "finaMobile = #{finamobile,jdbcType=VARCHAR},",
          "signDay = #{signday,jdbcType=DATE},",
          "sendFlag = #{sendflag,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP}",
        "where agencyId = #{agencyid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinalAgency record);
}