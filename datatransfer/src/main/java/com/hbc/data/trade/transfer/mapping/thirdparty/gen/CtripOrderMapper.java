package com.hbc.data.trade.transfer.mapping.thirdparty.gen;

import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.CtripOrder;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.CtripOrderCriteria;
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

public interface CtripOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `ctriporder`
     *
     * @mbggenerated
     */
    @SelectProvider(type=CtripOrderSqlProvider.class, method="countByExample")
    int countByExample(CtripOrderCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `ctriporder`
     *
     * @mbggenerated
     */
    @DeleteProvider(type=CtripOrderSqlProvider.class, method="deleteByExample")
    int deleteByExample(CtripOrderCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `ctriporder`
     *
     * @mbggenerated
     */
    @Delete({
        "delete from `ctriporder`",
        "where ctripOrderId = #{ctriporderid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String ctriporderid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `ctriporder`
     *
     * @mbggenerated
     */
    @Insert({
        "insert into `ctriporder` (ctripOrderId, vOrderId, ",
        "flightNo, airportName, ",
        "carType, orderType, ",
        "totalPrice, distance, ",
        "estimatedTime, guideId, ",
        "country, city, fromAddrName, ",
        "fromLong, fromLat, ",
        "toAddrName, toLong, ",
        "toLat, status, airportThreeCode, ",
        "depFlightTime, fromAddrDetail, ",
        "toAddrDetail, priceMark, ",
        "ctripPurchaseOrderID, isSupportPickup, ",
        "pickupCardMsg, ChildSeatCount, ",
        "areaCode, saleCode, ",
        "salePrice, data, ",
        "updated_at, created_at)",
        "values (#{ctriporderid,jdbcType=VARCHAR}, #{vorderid,jdbcType=VARCHAR}, ",
        "#{flightno,jdbcType=VARCHAR}, #{airportname,jdbcType=VARCHAR}, ",
        "#{cartype,jdbcType=INTEGER}, #{ordertype,jdbcType=INTEGER}, ",
        "#{totalprice,jdbcType=DECIMAL}, #{distance,jdbcType=INTEGER}, ",
        "#{estimatedtime,jdbcType=INTEGER}, #{guideid,jdbcType=INTEGER}, ",
        "#{country,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, #{fromaddrname,jdbcType=VARCHAR}, ",
        "#{fromlong,jdbcType=DOUBLE}, #{fromlat,jdbcType=DOUBLE}, ",
        "#{toaddrname,jdbcType=VARCHAR}, #{tolong,jdbcType=DOUBLE}, ",
        "#{tolat,jdbcType=DOUBLE}, #{status,jdbcType=INTEGER}, #{airportthreecode,jdbcType=VARCHAR}, ",
        "#{depflighttime,jdbcType=TIMESTAMP}, #{fromaddrdetail,jdbcType=VARCHAR}, ",
        "#{toaddrdetail,jdbcType=VARCHAR}, #{pricemark,jdbcType=VARCHAR}, ",
        "#{ctrippurchaseorderid,jdbcType=VARCHAR}, #{issupportpickup,jdbcType=INTEGER}, ",
        "#{pickupcardmsg,jdbcType=VARCHAR}, #{childseatcount,jdbcType=INTEGER}, ",
        "#{areacode,jdbcType=VARCHAR}, #{salecode,jdbcType=VARCHAR}, ",
        "#{saleprice,jdbcType=DECIMAL}, #{data,jdbcType=VARCHAR}, ",
        "#{updatedAt,jdbcType=TIMESTAMP}, #{createdAt,jdbcType=TIMESTAMP})"
    })
    int insert(CtripOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `ctriporder`
     *
     * @mbggenerated
     */
    @InsertProvider(type=CtripOrderSqlProvider.class, method="insertSelective")
    int insertSelective(CtripOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `ctriporder`
     *
     * @mbggenerated
     */
    @SelectProvider(type=CtripOrderSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ctripOrderId", property="ctriporderid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="vOrderId", property="vorderid", jdbcType=JdbcType.VARCHAR),
        @Result(column="flightNo", property="flightno", jdbcType=JdbcType.VARCHAR),
        @Result(column="airportName", property="airportname", jdbcType=JdbcType.VARCHAR),
        @Result(column="carType", property="cartype", jdbcType=JdbcType.INTEGER),
        @Result(column="orderType", property="ordertype", jdbcType=JdbcType.INTEGER),
        @Result(column="totalPrice", property="totalprice", jdbcType=JdbcType.DECIMAL),
        @Result(column="distance", property="distance", jdbcType=JdbcType.INTEGER),
        @Result(column="estimatedTime", property="estimatedtime", jdbcType=JdbcType.INTEGER),
        @Result(column="guideId", property="guideid", jdbcType=JdbcType.INTEGER),
        @Result(column="country", property="country", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="fromAddrName", property="fromaddrname", jdbcType=JdbcType.VARCHAR),
        @Result(column="fromLong", property="fromlong", jdbcType=JdbcType.DOUBLE),
        @Result(column="fromLat", property="fromlat", jdbcType=JdbcType.DOUBLE),
        @Result(column="toAddrName", property="toaddrname", jdbcType=JdbcType.VARCHAR),
        @Result(column="toLong", property="tolong", jdbcType=JdbcType.DOUBLE),
        @Result(column="toLat", property="tolat", jdbcType=JdbcType.DOUBLE),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="airportThreeCode", property="airportthreecode", jdbcType=JdbcType.VARCHAR),
        @Result(column="depFlightTime", property="depflighttime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="fromAddrDetail", property="fromaddrdetail", jdbcType=JdbcType.VARCHAR),
        @Result(column="toAddrDetail", property="toaddrdetail", jdbcType=JdbcType.VARCHAR),
        @Result(column="priceMark", property="pricemark", jdbcType=JdbcType.VARCHAR),
        @Result(column="ctripPurchaseOrderID", property="ctrippurchaseorderid", jdbcType=JdbcType.VARCHAR),
        @Result(column="isSupportPickup", property="issupportpickup", jdbcType=JdbcType.INTEGER),
        @Result(column="pickupCardMsg", property="pickupcardmsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="ChildSeatCount", property="childseatcount", jdbcType=JdbcType.INTEGER),
        @Result(column="areaCode", property="areacode", jdbcType=JdbcType.VARCHAR),
        @Result(column="saleCode", property="salecode", jdbcType=JdbcType.VARCHAR),
        @Result(column="salePrice", property="saleprice", jdbcType=JdbcType.DECIMAL),
        @Result(column="data", property="data", jdbcType=JdbcType.VARCHAR),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CtripOrder> selectByExampleWithRowbounds(CtripOrderCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `ctriporder`
     *
     * @mbggenerated
     */
    @SelectProvider(type=CtripOrderSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ctripOrderId", property="ctriporderid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="vOrderId", property="vorderid", jdbcType=JdbcType.VARCHAR),
        @Result(column="flightNo", property="flightno", jdbcType=JdbcType.VARCHAR),
        @Result(column="airportName", property="airportname", jdbcType=JdbcType.VARCHAR),
        @Result(column="carType", property="cartype", jdbcType=JdbcType.INTEGER),
        @Result(column="orderType", property="ordertype", jdbcType=JdbcType.INTEGER),
        @Result(column="totalPrice", property="totalprice", jdbcType=JdbcType.DECIMAL),
        @Result(column="distance", property="distance", jdbcType=JdbcType.INTEGER),
        @Result(column="estimatedTime", property="estimatedtime", jdbcType=JdbcType.INTEGER),
        @Result(column="guideId", property="guideid", jdbcType=JdbcType.INTEGER),
        @Result(column="country", property="country", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="fromAddrName", property="fromaddrname", jdbcType=JdbcType.VARCHAR),
        @Result(column="fromLong", property="fromlong", jdbcType=JdbcType.DOUBLE),
        @Result(column="fromLat", property="fromlat", jdbcType=JdbcType.DOUBLE),
        @Result(column="toAddrName", property="toaddrname", jdbcType=JdbcType.VARCHAR),
        @Result(column="toLong", property="tolong", jdbcType=JdbcType.DOUBLE),
        @Result(column="toLat", property="tolat", jdbcType=JdbcType.DOUBLE),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="airportThreeCode", property="airportthreecode", jdbcType=JdbcType.VARCHAR),
        @Result(column="depFlightTime", property="depflighttime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="fromAddrDetail", property="fromaddrdetail", jdbcType=JdbcType.VARCHAR),
        @Result(column="toAddrDetail", property="toaddrdetail", jdbcType=JdbcType.VARCHAR),
        @Result(column="priceMark", property="pricemark", jdbcType=JdbcType.VARCHAR),
        @Result(column="ctripPurchaseOrderID", property="ctrippurchaseorderid", jdbcType=JdbcType.VARCHAR),
        @Result(column="isSupportPickup", property="issupportpickup", jdbcType=JdbcType.INTEGER),
        @Result(column="pickupCardMsg", property="pickupcardmsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="ChildSeatCount", property="childseatcount", jdbcType=JdbcType.INTEGER),
        @Result(column="areaCode", property="areacode", jdbcType=JdbcType.VARCHAR),
        @Result(column="saleCode", property="salecode", jdbcType=JdbcType.VARCHAR),
        @Result(column="salePrice", property="saleprice", jdbcType=JdbcType.DECIMAL),
        @Result(column="data", property="data", jdbcType=JdbcType.VARCHAR),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CtripOrder> selectByExample(CtripOrderCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `ctriporder`
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "ctripOrderId, vOrderId, flightNo, airportName, carType, orderType, totalPrice, ",
        "distance, estimatedTime, guideId, country, city, fromAddrName, fromLong, fromLat, ",
        "toAddrName, toLong, toLat, status, airportThreeCode, depFlightTime, fromAddrDetail, ",
        "toAddrDetail, priceMark, ctripPurchaseOrderID, isSupportPickup, pickupCardMsg, ",
        "ChildSeatCount, areaCode, saleCode, salePrice, data, updated_at, created_at",
        "from `ctriporder`",
        "where ctripOrderId = #{ctriporderid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ctripOrderId", property="ctriporderid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="vOrderId", property="vorderid", jdbcType=JdbcType.VARCHAR),
        @Result(column="flightNo", property="flightno", jdbcType=JdbcType.VARCHAR),
        @Result(column="airportName", property="airportname", jdbcType=JdbcType.VARCHAR),
        @Result(column="carType", property="cartype", jdbcType=JdbcType.INTEGER),
        @Result(column="orderType", property="ordertype", jdbcType=JdbcType.INTEGER),
        @Result(column="totalPrice", property="totalprice", jdbcType=JdbcType.DECIMAL),
        @Result(column="distance", property="distance", jdbcType=JdbcType.INTEGER),
        @Result(column="estimatedTime", property="estimatedtime", jdbcType=JdbcType.INTEGER),
        @Result(column="guideId", property="guideid", jdbcType=JdbcType.INTEGER),
        @Result(column="country", property="country", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="fromAddrName", property="fromaddrname", jdbcType=JdbcType.VARCHAR),
        @Result(column="fromLong", property="fromlong", jdbcType=JdbcType.DOUBLE),
        @Result(column="fromLat", property="fromlat", jdbcType=JdbcType.DOUBLE),
        @Result(column="toAddrName", property="toaddrname", jdbcType=JdbcType.VARCHAR),
        @Result(column="toLong", property="tolong", jdbcType=JdbcType.DOUBLE),
        @Result(column="toLat", property="tolat", jdbcType=JdbcType.DOUBLE),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="airportThreeCode", property="airportthreecode", jdbcType=JdbcType.VARCHAR),
        @Result(column="depFlightTime", property="depflighttime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="fromAddrDetail", property="fromaddrdetail", jdbcType=JdbcType.VARCHAR),
        @Result(column="toAddrDetail", property="toaddrdetail", jdbcType=JdbcType.VARCHAR),
        @Result(column="priceMark", property="pricemark", jdbcType=JdbcType.VARCHAR),
        @Result(column="ctripPurchaseOrderID", property="ctrippurchaseorderid", jdbcType=JdbcType.VARCHAR),
        @Result(column="isSupportPickup", property="issupportpickup", jdbcType=JdbcType.INTEGER),
        @Result(column="pickupCardMsg", property="pickupcardmsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="ChildSeatCount", property="childseatcount", jdbcType=JdbcType.INTEGER),
        @Result(column="areaCode", property="areacode", jdbcType=JdbcType.VARCHAR),
        @Result(column="saleCode", property="salecode", jdbcType=JdbcType.VARCHAR),
        @Result(column="salePrice", property="saleprice", jdbcType=JdbcType.DECIMAL),
        @Result(column="data", property="data", jdbcType=JdbcType.VARCHAR),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP)
    })
    CtripOrder selectByPrimaryKey(String ctriporderid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `ctriporder`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=CtripOrderSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CtripOrder record, @Param("example") CtripOrderCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `ctriporder`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=CtripOrderSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CtripOrder record, @Param("example") CtripOrderCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `ctriporder`
     *
     * @mbggenerated
     */
    @UpdateProvider(type=CtripOrderSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CtripOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `ctriporder`
     *
     * @mbggenerated
     */
    @Update({
        "update `ctriporder`",
        "set vOrderId = #{vorderid,jdbcType=VARCHAR},",
          "flightNo = #{flightno,jdbcType=VARCHAR},",
          "airportName = #{airportname,jdbcType=VARCHAR},",
          "carType = #{cartype,jdbcType=INTEGER},",
          "orderType = #{ordertype,jdbcType=INTEGER},",
          "totalPrice = #{totalprice,jdbcType=DECIMAL},",
          "distance = #{distance,jdbcType=INTEGER},",
          "estimatedTime = #{estimatedtime,jdbcType=INTEGER},",
          "guideId = #{guideid,jdbcType=INTEGER},",
          "country = #{country,jdbcType=VARCHAR},",
          "city = #{city,jdbcType=VARCHAR},",
          "fromAddrName = #{fromaddrname,jdbcType=VARCHAR},",
          "fromLong = #{fromlong,jdbcType=DOUBLE},",
          "fromLat = #{fromlat,jdbcType=DOUBLE},",
          "toAddrName = #{toaddrname,jdbcType=VARCHAR},",
          "toLong = #{tolong,jdbcType=DOUBLE},",
          "toLat = #{tolat,jdbcType=DOUBLE},",
          "status = #{status,jdbcType=INTEGER},",
          "airportThreeCode = #{airportthreecode,jdbcType=VARCHAR},",
          "depFlightTime = #{depflighttime,jdbcType=TIMESTAMP},",
          "fromAddrDetail = #{fromaddrdetail,jdbcType=VARCHAR},",
          "toAddrDetail = #{toaddrdetail,jdbcType=VARCHAR},",
          "priceMark = #{pricemark,jdbcType=VARCHAR},",
          "ctripPurchaseOrderID = #{ctrippurchaseorderid,jdbcType=VARCHAR},",
          "isSupportPickup = #{issupportpickup,jdbcType=INTEGER},",
          "pickupCardMsg = #{pickupcardmsg,jdbcType=VARCHAR},",
          "ChildSeatCount = #{childseatcount,jdbcType=INTEGER},",
          "areaCode = #{areacode,jdbcType=VARCHAR},",
          "saleCode = #{salecode,jdbcType=VARCHAR},",
          "salePrice = #{saleprice,jdbcType=DECIMAL},",
          "data = #{data,jdbcType=VARCHAR},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP}",
        "where ctripOrderId = #{ctriporderid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(CtripOrder record);
}