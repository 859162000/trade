package com.hbc.data.trade.transfer.mapping.hbcbasedata.gen;

import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalGuide;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalGuideCriteria;
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

public interface FinalGuideMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide`
     *
     * @mbggenerated
     */
    @SelectProvider(type=FinalGuideSqlProvider.class, method="countByExample")
    int countByExample(FinalGuideCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide`
     *
     * @mbggenerated
     */
    @DeleteProvider(type=FinalGuideSqlProvider.class, method="deleteByExample")
    int deleteByExample(FinalGuideCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide`
     *
     * @mbggenerated
     */
    @Delete({
        "delete from `guide`",
        "where guideId = #{guideid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer guideid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide`
     *
     * @mbggenerated
     */
    @Insert({
        "insert into `guide` (guideId, placeId, ",
        "placeCityId, guideNo, ",
        "name, password, ",
        "encryptedPwd, avatar, ",
        "gender, birthday, address, ",
        "zipcode, liveStart, ",
        "liveYear, mobile, ",
        "areaCode, areaPlace, ",
        "isMobileValidated, email, ",
        "isEmailValidated, weixin, ",
        "isWeixinValidated, qq, ",
        "isQQValidated, companyId, ",
        "otherContact, contactName, ",
        "contactAreaCode, contactAreaPlace, ",
        "contactMobile, level, ",
        "model, type, source, ",
        "dep, arr, signDate, ",
        "commentNum, pickupNum, ",
        "transferNum, dailyNum, ",
        "peruserNum, cancelNum, ",
        "comment, referrer, ",
        "createdIP, loginNum, ",
        "lastLogin, imToken, ",
        "agencyType, sendFlag, ",
        "status, receiveStatus, ",
        "identityCard, drivingLicence, ",
        "carLicence, operatePermit, ",
        "guideLevel, updated_at, ",
        "deleted_at, created_at, ",
        "serviceCitys, signingBonus, ",
        "signingBonusComment)",
        "values (#{guideid,jdbcType=INTEGER}, #{placeid,jdbcType=INTEGER}, ",
        "#{placecityid,jdbcType=INTEGER}, #{guideno,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{encryptedpwd,jdbcType=VARCHAR}, #{avatar,jdbcType=VARCHAR}, ",
        "#{gender,jdbcType=TINYINT}, #{birthday,jdbcType=DATE}, #{address,jdbcType=VARCHAR}, ",
        "#{zipcode,jdbcType=VARCHAR}, #{livestart,jdbcType=TINYINT}, ",
        "#{liveyear,jdbcType=INTEGER}, #{mobile,jdbcType=VARCHAR}, ",
        "#{areacode,jdbcType=VARCHAR}, #{areaplace,jdbcType=VARCHAR}, ",
        "#{ismobilevalidated,jdbcType=TINYINT}, #{email,jdbcType=VARCHAR}, ",
        "#{isemailvalidated,jdbcType=TINYINT}, #{weixin,jdbcType=VARCHAR}, ",
        "#{isweixinvalidated,jdbcType=TINYINT}, #{qq,jdbcType=VARCHAR}, ",
        "#{isqqvalidated,jdbcType=TINYINT}, #{companyid,jdbcType=INTEGER}, ",
        "#{othercontact,jdbcType=VARCHAR}, #{contactname,jdbcType=VARCHAR}, ",
        "#{contactareacode,jdbcType=VARCHAR}, #{contactareaplace,jdbcType=VARCHAR}, ",
        "#{contactmobile,jdbcType=VARCHAR}, #{level,jdbcType=INTEGER}, ",
        "#{model,jdbcType=INTEGER}, #{type,jdbcType=TINYINT}, #{source,jdbcType=INTEGER}, ",
        "#{dep,jdbcType=VARCHAR}, #{arr,jdbcType=VARCHAR}, #{signdate,jdbcType=DATE}, ",
        "#{commentnum,jdbcType=INTEGER}, #{pickupnum,jdbcType=INTEGER}, ",
        "#{transfernum,jdbcType=INTEGER}, #{dailynum,jdbcType=INTEGER}, ",
        "#{perusernum,jdbcType=INTEGER}, #{cancelnum,jdbcType=INTEGER}, ",
        "#{comment,jdbcType=VARCHAR}, #{referrer,jdbcType=VARCHAR}, ",
        "#{createdip,jdbcType=VARCHAR}, #{loginnum,jdbcType=INTEGER}, ",
        "#{lastlogin,jdbcType=TIMESTAMP}, #{imtoken,jdbcType=VARCHAR}, ",
        "#{agencytype,jdbcType=INTEGER}, #{sendflag,jdbcType=INTEGER}, ",
        "#{status,jdbcType=INTEGER}, #{receivestatus,jdbcType=INTEGER}, ",
        "#{identitycard,jdbcType=VARCHAR}, #{drivinglicence,jdbcType=VARCHAR}, ",
        "#{carlicence,jdbcType=VARCHAR}, #{operatepermit,jdbcType=VARCHAR}, ",
        "#{guidelevel,jdbcType=INTEGER}, #{updatedAt,jdbcType=TIMESTAMP}, ",
        "#{deletedAt,jdbcType=TIMESTAMP}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{servicecitys,jdbcType=VARCHAR}, #{signingbonus,jdbcType=INTEGER}, ",
        "#{signingbonuscomment,jdbcType=VARCHAR})"
    })
    int insert(FinalGuide record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide`
     *
     * @mbggenerated
     */
    @InsertProvider(type=FinalGuideSqlProvider.class, method="insertSelective")
    int insertSelective(FinalGuide record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide`
     *
     * @mbggenerated
     */
    @SelectProvider(type=FinalGuideSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="guideId", property="guideid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="placeId", property="placeid", jdbcType=JdbcType.INTEGER),
        @Result(column="placeCityId", property="placecityid", jdbcType=JdbcType.INTEGER),
        @Result(column="guideNo", property="guideno", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="encryptedPwd", property="encryptedpwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.TINYINT),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="zipcode", property="zipcode", jdbcType=JdbcType.VARCHAR),
        @Result(column="liveStart", property="livestart", jdbcType=JdbcType.TINYINT),
        @Result(column="liveYear", property="liveyear", jdbcType=JdbcType.INTEGER),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="areaCode", property="areacode", jdbcType=JdbcType.VARCHAR),
        @Result(column="areaPlace", property="areaplace", jdbcType=JdbcType.VARCHAR),
        @Result(column="isMobileValidated", property="ismobilevalidated", jdbcType=JdbcType.TINYINT),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="isEmailValidated", property="isemailvalidated", jdbcType=JdbcType.TINYINT),
        @Result(column="weixin", property="weixin", jdbcType=JdbcType.VARCHAR),
        @Result(column="isWeixinValidated", property="isweixinvalidated", jdbcType=JdbcType.TINYINT),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="isQQValidated", property="isqqvalidated", jdbcType=JdbcType.TINYINT),
        @Result(column="companyId", property="companyid", jdbcType=JdbcType.INTEGER),
        @Result(column="otherContact", property="othercontact", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactName", property="contactname", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactAreaCode", property="contactareacode", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactAreaPlace", property="contactareaplace", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactMobile", property="contactmobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="model", property="model", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="source", property="source", jdbcType=JdbcType.INTEGER),
        @Result(column="dep", property="dep", jdbcType=JdbcType.VARCHAR),
        @Result(column="arr", property="arr", jdbcType=JdbcType.VARCHAR),
        @Result(column="signDate", property="signdate", jdbcType=JdbcType.DATE),
        @Result(column="commentNum", property="commentnum", jdbcType=JdbcType.INTEGER),
        @Result(column="pickupNum", property="pickupnum", jdbcType=JdbcType.INTEGER),
        @Result(column="transferNum", property="transfernum", jdbcType=JdbcType.INTEGER),
        @Result(column="dailyNum", property="dailynum", jdbcType=JdbcType.INTEGER),
        @Result(column="peruserNum", property="perusernum", jdbcType=JdbcType.INTEGER),
        @Result(column="cancelNum", property="cancelnum", jdbcType=JdbcType.INTEGER),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="referrer", property="referrer", jdbcType=JdbcType.VARCHAR),
        @Result(column="createdIP", property="createdip", jdbcType=JdbcType.VARCHAR),
        @Result(column="loginNum", property="loginnum", jdbcType=JdbcType.INTEGER),
        @Result(column="lastLogin", property="lastlogin", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="imToken", property="imtoken", jdbcType=JdbcType.VARCHAR),
        @Result(column="agencyType", property="agencytype", jdbcType=JdbcType.INTEGER),
        @Result(column="sendFlag", property="sendflag", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="receiveStatus", property="receivestatus", jdbcType=JdbcType.INTEGER),
        @Result(column="identityCard", property="identitycard", jdbcType=JdbcType.VARCHAR),
        @Result(column="drivingLicence", property="drivinglicence", jdbcType=JdbcType.VARCHAR),
        @Result(column="carLicence", property="carlicence", jdbcType=JdbcType.VARCHAR),
        @Result(column="operatePermit", property="operatepermit", jdbcType=JdbcType.VARCHAR),
        @Result(column="guideLevel", property="guidelevel", jdbcType=JdbcType.INTEGER),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="deleted_at", property="deletedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="serviceCitys", property="servicecitys", jdbcType=JdbcType.VARCHAR),
        @Result(column="signingBonus", property="signingbonus", jdbcType=JdbcType.INTEGER),
        @Result(column="signingBonusComment", property="signingbonuscomment", jdbcType=JdbcType.VARCHAR)
    })
    List<FinalGuide> selectByExampleWithRowbounds(FinalGuideCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide`
     *
     * @mbggenerated
     */
    @SelectProvider(type=FinalGuideSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="guideId", property="guideid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="placeId", property="placeid", jdbcType=JdbcType.INTEGER),
        @Result(column="placeCityId", property="placecityid", jdbcType=JdbcType.INTEGER),
        @Result(column="guideNo", property="guideno", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="encryptedPwd", property="encryptedpwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.TINYINT),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="zipcode", property="zipcode", jdbcType=JdbcType.VARCHAR),
        @Result(column="liveStart", property="livestart", jdbcType=JdbcType.TINYINT),
        @Result(column="liveYear", property="liveyear", jdbcType=JdbcType.INTEGER),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="areaCode", property="areacode", jdbcType=JdbcType.VARCHAR),
        @Result(column="areaPlace", property="areaplace", jdbcType=JdbcType.VARCHAR),
        @Result(column="isMobileValidated", property="ismobilevalidated", jdbcType=JdbcType.TINYINT),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="isEmailValidated", property="isemailvalidated", jdbcType=JdbcType.TINYINT),
        @Result(column="weixin", property="weixin", jdbcType=JdbcType.VARCHAR),
        @Result(column="isWeixinValidated", property="isweixinvalidated", jdbcType=JdbcType.TINYINT),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="isQQValidated", property="isqqvalidated", jdbcType=JdbcType.TINYINT),
        @Result(column="companyId", property="companyid", jdbcType=JdbcType.INTEGER),
        @Result(column="otherContact", property="othercontact", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactName", property="contactname", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactAreaCode", property="contactareacode", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactAreaPlace", property="contactareaplace", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactMobile", property="contactmobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="model", property="model", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="source", property="source", jdbcType=JdbcType.INTEGER),
        @Result(column="dep", property="dep", jdbcType=JdbcType.VARCHAR),
        @Result(column="arr", property="arr", jdbcType=JdbcType.VARCHAR),
        @Result(column="signDate", property="signdate", jdbcType=JdbcType.DATE),
        @Result(column="commentNum", property="commentnum", jdbcType=JdbcType.INTEGER),
        @Result(column="pickupNum", property="pickupnum", jdbcType=JdbcType.INTEGER),
        @Result(column="transferNum", property="transfernum", jdbcType=JdbcType.INTEGER),
        @Result(column="dailyNum", property="dailynum", jdbcType=JdbcType.INTEGER),
        @Result(column="peruserNum", property="perusernum", jdbcType=JdbcType.INTEGER),
        @Result(column="cancelNum", property="cancelnum", jdbcType=JdbcType.INTEGER),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="referrer", property="referrer", jdbcType=JdbcType.VARCHAR),
        @Result(column="createdIP", property="createdip", jdbcType=JdbcType.VARCHAR),
        @Result(column="loginNum", property="loginnum", jdbcType=JdbcType.INTEGER),
        @Result(column="lastLogin", property="lastlogin", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="imToken", property="imtoken", jdbcType=JdbcType.VARCHAR),
        @Result(column="agencyType", property="agencytype", jdbcType=JdbcType.INTEGER),
        @Result(column="sendFlag", property="sendflag", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="receiveStatus", property="receivestatus", jdbcType=JdbcType.INTEGER),
        @Result(column="identityCard", property="identitycard", jdbcType=JdbcType.VARCHAR),
        @Result(column="drivingLicence", property="drivinglicence", jdbcType=JdbcType.VARCHAR),
        @Result(column="carLicence", property="carlicence", jdbcType=JdbcType.VARCHAR),
        @Result(column="operatePermit", property="operatepermit", jdbcType=JdbcType.VARCHAR),
        @Result(column="guideLevel", property="guidelevel", jdbcType=JdbcType.INTEGER),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="deleted_at", property="deletedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="serviceCitys", property="servicecitys", jdbcType=JdbcType.VARCHAR),
        @Result(column="signingBonus", property="signingbonus", jdbcType=JdbcType.INTEGER),
        @Result(column="signingBonusComment", property="signingbonuscomment", jdbcType=JdbcType.VARCHAR)
    })
    List<FinalGuide> selectByExample(FinalGuideCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide`
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "guideId, placeId, placeCityId, guideNo, name, password, encryptedPwd, avatar, ",
        "gender, birthday, address, zipcode, liveStart, liveYear, mobile, areaCode, areaPlace, ",
        "isMobileValidated, email, isEmailValidated, weixin, isWeixinValidated, qq, isQQValidated, ",
        "companyId, otherContact, contactName, contactAreaCode, contactAreaPlace, contactMobile, ",
        "level, model, type, source, dep, arr, signDate, commentNum, pickupNum, transferNum, ",
        "dailyNum, peruserNum, cancelNum, comment, referrer, createdIP, loginNum, lastLogin, ",
        "imToken, agencyType, sendFlag, status, receiveStatus, identityCard, drivingLicence, ",
        "carLicence, operatePermit, guideLevel, updated_at, deleted_at, created_at, serviceCitys, ",
        "signingBonus, signingBonusComment",
        "from `guide`",
        "where guideId = #{guideid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="guideId", property="guideid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="placeId", property="placeid", jdbcType=JdbcType.INTEGER),
        @Result(column="placeCityId", property="placecityid", jdbcType=JdbcType.INTEGER),
        @Result(column="guideNo", property="guideno", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="encryptedPwd", property="encryptedpwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.TINYINT),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="zipcode", property="zipcode", jdbcType=JdbcType.VARCHAR),
        @Result(column="liveStart", property="livestart", jdbcType=JdbcType.TINYINT),
        @Result(column="liveYear", property="liveyear", jdbcType=JdbcType.INTEGER),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="areaCode", property="areacode", jdbcType=JdbcType.VARCHAR),
        @Result(column="areaPlace", property="areaplace", jdbcType=JdbcType.VARCHAR),
        @Result(column="isMobileValidated", property="ismobilevalidated", jdbcType=JdbcType.TINYINT),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="isEmailValidated", property="isemailvalidated", jdbcType=JdbcType.TINYINT),
        @Result(column="weixin", property="weixin", jdbcType=JdbcType.VARCHAR),
        @Result(column="isWeixinValidated", property="isweixinvalidated", jdbcType=JdbcType.TINYINT),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="isQQValidated", property="isqqvalidated", jdbcType=JdbcType.TINYINT),
        @Result(column="companyId", property="companyid", jdbcType=JdbcType.INTEGER),
        @Result(column="otherContact", property="othercontact", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactName", property="contactname", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactAreaCode", property="contactareacode", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactAreaPlace", property="contactareaplace", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactMobile", property="contactmobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="model", property="model", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="source", property="source", jdbcType=JdbcType.INTEGER),
        @Result(column="dep", property="dep", jdbcType=JdbcType.VARCHAR),
        @Result(column="arr", property="arr", jdbcType=JdbcType.VARCHAR),
        @Result(column="signDate", property="signdate", jdbcType=JdbcType.DATE),
        @Result(column="commentNum", property="commentnum", jdbcType=JdbcType.INTEGER),
        @Result(column="pickupNum", property="pickupnum", jdbcType=JdbcType.INTEGER),
        @Result(column="transferNum", property="transfernum", jdbcType=JdbcType.INTEGER),
        @Result(column="dailyNum", property="dailynum", jdbcType=JdbcType.INTEGER),
        @Result(column="peruserNum", property="perusernum", jdbcType=JdbcType.INTEGER),
        @Result(column="cancelNum", property="cancelnum", jdbcType=JdbcType.INTEGER),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="referrer", property="referrer", jdbcType=JdbcType.VARCHAR),
        @Result(column="createdIP", property="createdip", jdbcType=JdbcType.VARCHAR),
        @Result(column="loginNum", property="loginnum", jdbcType=JdbcType.INTEGER),
        @Result(column="lastLogin", property="lastlogin", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="imToken", property="imtoken", jdbcType=JdbcType.VARCHAR),
        @Result(column="agencyType", property="agencytype", jdbcType=JdbcType.INTEGER),
        @Result(column="sendFlag", property="sendflag", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="receiveStatus", property="receivestatus", jdbcType=JdbcType.INTEGER),
        @Result(column="identityCard", property="identitycard", jdbcType=JdbcType.VARCHAR),
        @Result(column="drivingLicence", property="drivinglicence", jdbcType=JdbcType.VARCHAR),
        @Result(column="carLicence", property="carlicence", jdbcType=JdbcType.VARCHAR),
        @Result(column="operatePermit", property="operatepermit", jdbcType=JdbcType.VARCHAR),
        @Result(column="guideLevel", property="guidelevel", jdbcType=JdbcType.INTEGER),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="deleted_at", property="deletedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="serviceCitys", property="servicecitys", jdbcType=JdbcType.VARCHAR),
        @Result(column="signingBonus", property="signingbonus", jdbcType=JdbcType.INTEGER),
        @Result(column="signingBonusComment", property="signingbonuscomment", jdbcType=JdbcType.VARCHAR)
    })
    FinalGuide selectByPrimaryKey(Integer guideid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=FinalGuideSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FinalGuide record, @Param("example") FinalGuideCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=FinalGuideSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FinalGuide record, @Param("example") FinalGuideCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=FinalGuideSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FinalGuide record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide`
     *
     * @mbggenerated
     */
    @Update({
        "update `guide`",
        "set placeId = #{placeid,jdbcType=INTEGER},",
          "placeCityId = #{placecityid,jdbcType=INTEGER},",
          "guideNo = #{guideno,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "encryptedPwd = #{encryptedpwd,jdbcType=VARCHAR},",
          "avatar = #{avatar,jdbcType=VARCHAR},",
          "gender = #{gender,jdbcType=TINYINT},",
          "birthday = #{birthday,jdbcType=DATE},",
          "address = #{address,jdbcType=VARCHAR},",
          "zipcode = #{zipcode,jdbcType=VARCHAR},",
          "liveStart = #{livestart,jdbcType=TINYINT},",
          "liveYear = #{liveyear,jdbcType=INTEGER},",
          "mobile = #{mobile,jdbcType=VARCHAR},",
          "areaCode = #{areacode,jdbcType=VARCHAR},",
          "areaPlace = #{areaplace,jdbcType=VARCHAR},",
          "isMobileValidated = #{ismobilevalidated,jdbcType=TINYINT},",
          "email = #{email,jdbcType=VARCHAR},",
          "isEmailValidated = #{isemailvalidated,jdbcType=TINYINT},",
          "weixin = #{weixin,jdbcType=VARCHAR},",
          "isWeixinValidated = #{isweixinvalidated,jdbcType=TINYINT},",
          "qq = #{qq,jdbcType=VARCHAR},",
          "isQQValidated = #{isqqvalidated,jdbcType=TINYINT},",
          "companyId = #{companyid,jdbcType=INTEGER},",
          "otherContact = #{othercontact,jdbcType=VARCHAR},",
          "contactName = #{contactname,jdbcType=VARCHAR},",
          "contactAreaCode = #{contactareacode,jdbcType=VARCHAR},",
          "contactAreaPlace = #{contactareaplace,jdbcType=VARCHAR},",
          "contactMobile = #{contactmobile,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=INTEGER},",
          "model = #{model,jdbcType=INTEGER},",
          "type = #{type,jdbcType=TINYINT},",
          "source = #{source,jdbcType=INTEGER},",
          "dep = #{dep,jdbcType=VARCHAR},",
          "arr = #{arr,jdbcType=VARCHAR},",
          "signDate = #{signdate,jdbcType=DATE},",
          "commentNum = #{commentnum,jdbcType=INTEGER},",
          "pickupNum = #{pickupnum,jdbcType=INTEGER},",
          "transferNum = #{transfernum,jdbcType=INTEGER},",
          "dailyNum = #{dailynum,jdbcType=INTEGER},",
          "peruserNum = #{perusernum,jdbcType=INTEGER},",
          "cancelNum = #{cancelnum,jdbcType=INTEGER},",
          "comment = #{comment,jdbcType=VARCHAR},",
          "referrer = #{referrer,jdbcType=VARCHAR},",
          "createdIP = #{createdip,jdbcType=VARCHAR},",
          "loginNum = #{loginnum,jdbcType=INTEGER},",
          "lastLogin = #{lastlogin,jdbcType=TIMESTAMP},",
          "imToken = #{imtoken,jdbcType=VARCHAR},",
          "agencyType = #{agencytype,jdbcType=INTEGER},",
          "sendFlag = #{sendflag,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "receiveStatus = #{receivestatus,jdbcType=INTEGER},",
          "identityCard = #{identitycard,jdbcType=VARCHAR},",
          "drivingLicence = #{drivinglicence,jdbcType=VARCHAR},",
          "carLicence = #{carlicence,jdbcType=VARCHAR},",
          "operatePermit = #{operatepermit,jdbcType=VARCHAR},",
          "guideLevel = #{guidelevel,jdbcType=INTEGER},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP},",
          "deleted_at = #{deletedAt,jdbcType=TIMESTAMP},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "serviceCitys = #{servicecitys,jdbcType=VARCHAR},",
          "signingBonus = #{signingbonus,jdbcType=INTEGER},",
          "signingBonusComment = #{signingbonuscomment,jdbcType=VARCHAR}",
        "where guideId = #{guideid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinalGuide record);
}