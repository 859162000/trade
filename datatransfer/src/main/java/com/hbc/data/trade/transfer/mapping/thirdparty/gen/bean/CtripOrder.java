package com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean;

import java.math.BigDecimal;
import java.util.Date;

public class CtripOrder {
    /**
     *  携程订单号
     *  所属表字段为`ctriporder`.ctripOrderId
     */
    private String ctriporderid;

    /**
     *  我方订单号
     *  所属表字段为`ctriporder`.vOrderId
     */
    private String vorderid;

    /**
     *  
     *  所属表字段为`ctriporder`.flightNo
     */
    private String flightno;

    /**
     *  
     *  所属表字段为`ctriporder`.airportName
     */
    private String airportname;

    /**
     *  第三方 车型
     *  所属表字段为`ctriporder`.carType
     */
    private Integer cartype;

    /**
     *  
     *  所属表字段为`ctriporder`.orderType
     */
    private Integer ordertype;

    /**
     *  
     *  所属表字段为`ctriporder`.totalPrice
     */
    private BigDecimal totalprice;

    /**
     *  
     *  所属表字段为`ctriporder`.distance
     */
    private Integer distance;

    /**
     *  
     *  所属表字段为`ctriporder`.estimatedTime
     */
    private Integer estimatedtime;

    /**
     *  
     *  所属表字段为`ctriporder`.guideId
     */
    private Integer guideid;

    /**
     *  
     *  所属表字段为`ctriporder`.country
     */
    private String country;

    /**
     *  
     *  所属表字段为`ctriporder`.city
     */
    private String city;

    /**
     *  
     *  所属表字段为`ctriporder`.fromAddrName
     */
    private String fromaddrname;

    /**
     *  
     *  所属表字段为`ctriporder`.fromLong
     */
    private Double fromlong;

    /**
     *  
     *  所属表字段为`ctriporder`.fromLat
     */
    private Double fromlat;

    /**
     *  
     *  所属表字段为`ctriporder`.toAddrName
     */
    private String toaddrname;

    /**
     *  
     *  所属表字段为`ctriporder`.toLong
     */
    private Double tolong;

    /**
     *  
     *  所属表字段为`ctriporder`.toLat
     */
    private Double tolat;

    /**
     *  
     *  所属表字段为`ctriporder`.status
     */
    private Integer status;

    /**
     *  
     *  所属表字段为`ctriporder`.airportThreeCode
     */
    private String airportthreecode;

    /**
     *  
     *  所属表字段为`ctriporder`.depFlightTime
     */
    private Date depflighttime;

    /**
     *  
     *  所属表字段为`ctriporder`.fromAddrDetail
     */
    private String fromaddrdetail;

    /**
     *  
     *  所属表字段为`ctriporder`.toAddrDetail
     */
    private String toaddrdetail;

    /**
     *  
     *  所属表字段为`ctriporder`.priceMark
     */
    private String pricemark;

    /**
     *  
     *  所属表字段为`ctriporder`.ctripPurchaseOrderID
     */
    private String ctrippurchaseorderid;

    /**
     *  
     *  所属表字段为`ctriporder`.isSupportPickup
     */
    private Integer issupportpickup;

    /**
     *  
     *  所属表字段为`ctriporder`.pickupCardMsg
     */
    private String pickupcardmsg;

    /**
     *  
     *  所属表字段为`ctriporder`.ChildSeatCount
     */
    private Integer childseatcount;

    /**
     *  
     *  所属表字段为`ctriporder`.areaCode
     */
    private String areacode;

    /**
     *  优惠码
     *  所属表字段为`ctriporder`.saleCode
     */
    private String salecode;

    /**
     *  优惠价格
     *  所属表字段为`ctriporder`.salePrice
     */
    private BigDecimal saleprice;

    /**
     *  
     *  所属表字段为`ctriporder`.data
     */
    private String data;

    /**
     *  
     *  所属表字段为`ctriporder`.updated_at
     */
    private Date updatedAt;

    /**
     *  
     *  所属表字段为`ctriporder`.created_at
     */
    private Date createdAt;

    /**
     *携程订单号
     *`ctriporder`.ctripOrderId
     *
     * @return the value of `ctriporder`.ctripOrderId
     *
     * @mbggenerated
     */
    public String getCtriporderid() {
        return ctriporderid;
    }

    /**
     *携程订单号
     *`ctriporder`.ctripOrderId
     *
     * @param ctriporderid the value for `ctriporder`.ctripOrderId
     *
     * @mbggenerated
     */
    public void setCtriporderid(String ctriporderid) {
        this.ctriporderid = ctriporderid == null ? null : ctriporderid.trim();
    }

    /**
     *我方订单号
     *`ctriporder`.vOrderId
     *
     * @return the value of `ctriporder`.vOrderId
     *
     * @mbggenerated
     */
    public String getVorderid() {
        return vorderid;
    }

    /**
     *我方订单号
     *`ctriporder`.vOrderId
     *
     * @param vorderid the value for `ctriporder`.vOrderId
     *
     * @mbggenerated
     */
    public void setVorderid(String vorderid) {
        this.vorderid = vorderid == null ? null : vorderid.trim();
    }

    /**
     *
     *`ctriporder`.flightNo
     *
     * @return the value of `ctriporder`.flightNo
     *
     * @mbggenerated
     */
    public String getFlightno() {
        return flightno;
    }

    /**
     *
     *`ctriporder`.flightNo
     *
     * @param flightno the value for `ctriporder`.flightNo
     *
     * @mbggenerated
     */
    public void setFlightno(String flightno) {
        this.flightno = flightno == null ? null : flightno.trim();
    }

    /**
     *
     *`ctriporder`.airportName
     *
     * @return the value of `ctriporder`.airportName
     *
     * @mbggenerated
     */
    public String getAirportname() {
        return airportname;
    }

    /**
     *
     *`ctriporder`.airportName
     *
     * @param airportname the value for `ctriporder`.airportName
     *
     * @mbggenerated
     */
    public void setAirportname(String airportname) {
        this.airportname = airportname == null ? null : airportname.trim();
    }

    /**
     *第三方 车型
     *`ctriporder`.carType
     *
     * @return the value of `ctriporder`.carType
     *
     * @mbggenerated
     */
    public Integer getCartype() {
        return cartype;
    }

    /**
     *第三方 车型
     *`ctriporder`.carType
     *
     * @param cartype the value for `ctriporder`.carType
     *
     * @mbggenerated
     */
    public void setCartype(Integer cartype) {
        this.cartype = cartype;
    }

    /**
     *
     *`ctriporder`.orderType
     *
     * @return the value of `ctriporder`.orderType
     *
     * @mbggenerated
     */
    public Integer getOrdertype() {
        return ordertype;
    }

    /**
     *
     *`ctriporder`.orderType
     *
     * @param ordertype the value for `ctriporder`.orderType
     *
     * @mbggenerated
     */
    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    /**
     *
     *`ctriporder`.totalPrice
     *
     * @return the value of `ctriporder`.totalPrice
     *
     * @mbggenerated
     */
    public BigDecimal getTotalprice() {
        return totalprice;
    }

    /**
     *
     *`ctriporder`.totalPrice
     *
     * @param totalprice the value for `ctriporder`.totalPrice
     *
     * @mbggenerated
     */
    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    /**
     *
     *`ctriporder`.distance
     *
     * @return the value of `ctriporder`.distance
     *
     * @mbggenerated
     */
    public Integer getDistance() {
        return distance;
    }

    /**
     *
     *`ctriporder`.distance
     *
     * @param distance the value for `ctriporder`.distance
     *
     * @mbggenerated
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    /**
     *
     *`ctriporder`.estimatedTime
     *
     * @return the value of `ctriporder`.estimatedTime
     *
     * @mbggenerated
     */
    public Integer getEstimatedtime() {
        return estimatedtime;
    }

    /**
     *
     *`ctriporder`.estimatedTime
     *
     * @param estimatedtime the value for `ctriporder`.estimatedTime
     *
     * @mbggenerated
     */
    public void setEstimatedtime(Integer estimatedtime) {
        this.estimatedtime = estimatedtime;
    }

    /**
     *
     *`ctriporder`.guideId
     *
     * @return the value of `ctriporder`.guideId
     *
     * @mbggenerated
     */
    public Integer getGuideid() {
        return guideid;
    }

    /**
     *
     *`ctriporder`.guideId
     *
     * @param guideid the value for `ctriporder`.guideId
     *
     * @mbggenerated
     */
    public void setGuideid(Integer guideid) {
        this.guideid = guideid;
    }

    /**
     *
     *`ctriporder`.country
     *
     * @return the value of `ctriporder`.country
     *
     * @mbggenerated
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     *`ctriporder`.country
     *
     * @param country the value for `ctriporder`.country
     *
     * @mbggenerated
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     *
     *`ctriporder`.city
     *
     * @return the value of `ctriporder`.city
     *
     * @mbggenerated
     */
    public String getCity() {
        return city;
    }

    /**
     *
     *`ctriporder`.city
     *
     * @param city the value for `ctriporder`.city
     *
     * @mbggenerated
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     *
     *`ctriporder`.fromAddrName
     *
     * @return the value of `ctriporder`.fromAddrName
     *
     * @mbggenerated
     */
    public String getFromaddrname() {
        return fromaddrname;
    }

    /**
     *
     *`ctriporder`.fromAddrName
     *
     * @param fromaddrname the value for `ctriporder`.fromAddrName
     *
     * @mbggenerated
     */
    public void setFromaddrname(String fromaddrname) {
        this.fromaddrname = fromaddrname == null ? null : fromaddrname.trim();
    }

    /**
     *
     *`ctriporder`.fromLong
     *
     * @return the value of `ctriporder`.fromLong
     *
     * @mbggenerated
     */
    public Double getFromlong() {
        return fromlong;
    }

    /**
     *
     *`ctriporder`.fromLong
     *
     * @param fromlong the value for `ctriporder`.fromLong
     *
     * @mbggenerated
     */
    public void setFromlong(Double fromlong) {
        this.fromlong = fromlong;
    }

    /**
     *
     *`ctriporder`.fromLat
     *
     * @return the value of `ctriporder`.fromLat
     *
     * @mbggenerated
     */
    public Double getFromlat() {
        return fromlat;
    }

    /**
     *
     *`ctriporder`.fromLat
     *
     * @param fromlat the value for `ctriporder`.fromLat
     *
     * @mbggenerated
     */
    public void setFromlat(Double fromlat) {
        this.fromlat = fromlat;
    }

    /**
     *
     *`ctriporder`.toAddrName
     *
     * @return the value of `ctriporder`.toAddrName
     *
     * @mbggenerated
     */
    public String getToaddrname() {
        return toaddrname;
    }

    /**
     *
     *`ctriporder`.toAddrName
     *
     * @param toaddrname the value for `ctriporder`.toAddrName
     *
     * @mbggenerated
     */
    public void setToaddrname(String toaddrname) {
        this.toaddrname = toaddrname == null ? null : toaddrname.trim();
    }

    /**
     *
     *`ctriporder`.toLong
     *
     * @return the value of `ctriporder`.toLong
     *
     * @mbggenerated
     */
    public Double getTolong() {
        return tolong;
    }

    /**
     *
     *`ctriporder`.toLong
     *
     * @param tolong the value for `ctriporder`.toLong
     *
     * @mbggenerated
     */
    public void setTolong(Double tolong) {
        this.tolong = tolong;
    }

    /**
     *
     *`ctriporder`.toLat
     *
     * @return the value of `ctriporder`.toLat
     *
     * @mbggenerated
     */
    public Double getTolat() {
        return tolat;
    }

    /**
     *
     *`ctriporder`.toLat
     *
     * @param tolat the value for `ctriporder`.toLat
     *
     * @mbggenerated
     */
    public void setTolat(Double tolat) {
        this.tolat = tolat;
    }

    /**
     *
     *`ctriporder`.status
     *
     * @return the value of `ctriporder`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     *`ctriporder`.status
     *
     * @param status the value for `ctriporder`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     *`ctriporder`.airportThreeCode
     *
     * @return the value of `ctriporder`.airportThreeCode
     *
     * @mbggenerated
     */
    public String getAirportthreecode() {
        return airportthreecode;
    }

    /**
     *
     *`ctriporder`.airportThreeCode
     *
     * @param airportthreecode the value for `ctriporder`.airportThreeCode
     *
     * @mbggenerated
     */
    public void setAirportthreecode(String airportthreecode) {
        this.airportthreecode = airportthreecode == null ? null : airportthreecode.trim();
    }

    /**
     *
     *`ctriporder`.depFlightTime
     *
     * @return the value of `ctriporder`.depFlightTime
     *
     * @mbggenerated
     */
    public Date getDepflighttime() {
        return depflighttime;
    }

    /**
     *
     *`ctriporder`.depFlightTime
     *
     * @param depflighttime the value for `ctriporder`.depFlightTime
     *
     * @mbggenerated
     */
    public void setDepflighttime(Date depflighttime) {
        this.depflighttime = depflighttime;
    }

    /**
     *
     *`ctriporder`.fromAddrDetail
     *
     * @return the value of `ctriporder`.fromAddrDetail
     *
     * @mbggenerated
     */
    public String getFromaddrdetail() {
        return fromaddrdetail;
    }

    /**
     *
     *`ctriporder`.fromAddrDetail
     *
     * @param fromaddrdetail the value for `ctriporder`.fromAddrDetail
     *
     * @mbggenerated
     */
    public void setFromaddrdetail(String fromaddrdetail) {
        this.fromaddrdetail = fromaddrdetail == null ? null : fromaddrdetail.trim();
    }

    /**
     *
     *`ctriporder`.toAddrDetail
     *
     * @return the value of `ctriporder`.toAddrDetail
     *
     * @mbggenerated
     */
    public String getToaddrdetail() {
        return toaddrdetail;
    }

    /**
     *
     *`ctriporder`.toAddrDetail
     *
     * @param toaddrdetail the value for `ctriporder`.toAddrDetail
     *
     * @mbggenerated
     */
    public void setToaddrdetail(String toaddrdetail) {
        this.toaddrdetail = toaddrdetail == null ? null : toaddrdetail.trim();
    }

    /**
     *
     *`ctriporder`.priceMark
     *
     * @return the value of `ctriporder`.priceMark
     *
     * @mbggenerated
     */
    public String getPricemark() {
        return pricemark;
    }

    /**
     *
     *`ctriporder`.priceMark
     *
     * @param pricemark the value for `ctriporder`.priceMark
     *
     * @mbggenerated
     */
    public void setPricemark(String pricemark) {
        this.pricemark = pricemark == null ? null : pricemark.trim();
    }

    /**
     *
     *`ctriporder`.ctripPurchaseOrderID
     *
     * @return the value of `ctriporder`.ctripPurchaseOrderID
     *
     * @mbggenerated
     */
    public String getCtrippurchaseorderid() {
        return ctrippurchaseorderid;
    }

    /**
     *
     *`ctriporder`.ctripPurchaseOrderID
     *
     * @param ctrippurchaseorderid the value for `ctriporder`.ctripPurchaseOrderID
     *
     * @mbggenerated
     */
    public void setCtrippurchaseorderid(String ctrippurchaseorderid) {
        this.ctrippurchaseorderid = ctrippurchaseorderid == null ? null : ctrippurchaseorderid.trim();
    }

    /**
     *
     *`ctriporder`.isSupportPickup
     *
     * @return the value of `ctriporder`.isSupportPickup
     *
     * @mbggenerated
     */
    public Integer getIssupportpickup() {
        return issupportpickup;
    }

    /**
     *
     *`ctriporder`.isSupportPickup
     *
     * @param issupportpickup the value for `ctriporder`.isSupportPickup
     *
     * @mbggenerated
     */
    public void setIssupportpickup(Integer issupportpickup) {
        this.issupportpickup = issupportpickup;
    }

    /**
     *
     *`ctriporder`.pickupCardMsg
     *
     * @return the value of `ctriporder`.pickupCardMsg
     *
     * @mbggenerated
     */
    public String getPickupcardmsg() {
        return pickupcardmsg;
    }

    /**
     *
     *`ctriporder`.pickupCardMsg
     *
     * @param pickupcardmsg the value for `ctriporder`.pickupCardMsg
     *
     * @mbggenerated
     */
    public void setPickupcardmsg(String pickupcardmsg) {
        this.pickupcardmsg = pickupcardmsg == null ? null : pickupcardmsg.trim();
    }

    /**
     *
     *`ctriporder`.ChildSeatCount
     *
     * @return the value of `ctriporder`.ChildSeatCount
     *
     * @mbggenerated
     */
    public Integer getChildseatcount() {
        return childseatcount;
    }

    /**
     *
     *`ctriporder`.ChildSeatCount
     *
     * @param childseatcount the value for `ctriporder`.ChildSeatCount
     *
     * @mbggenerated
     */
    public void setChildseatcount(Integer childseatcount) {
        this.childseatcount = childseatcount;
    }

    /**
     *
     *`ctriporder`.areaCode
     *
     * @return the value of `ctriporder`.areaCode
     *
     * @mbggenerated
     */
    public String getAreacode() {
        return areacode;
    }

    /**
     *
     *`ctriporder`.areaCode
     *
     * @param areacode the value for `ctriporder`.areaCode
     *
     * @mbggenerated
     */
    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }

    /**
     *优惠码
     *`ctriporder`.saleCode
     *
     * @return the value of `ctriporder`.saleCode
     *
     * @mbggenerated
     */
    public String getSalecode() {
        return salecode;
    }

    /**
     *优惠码
     *`ctriporder`.saleCode
     *
     * @param salecode the value for `ctriporder`.saleCode
     *
     * @mbggenerated
     */
    public void setSalecode(String salecode) {
        this.salecode = salecode == null ? null : salecode.trim();
    }

    /**
     *优惠价格
     *`ctriporder`.salePrice
     *
     * @return the value of `ctriporder`.salePrice
     *
     * @mbggenerated
     */
    public BigDecimal getSaleprice() {
        return saleprice;
    }

    /**
     *优惠价格
     *`ctriporder`.salePrice
     *
     * @param saleprice the value for `ctriporder`.salePrice
     *
     * @mbggenerated
     */
    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }

    /**
     *
     *`ctriporder`.data
     *
     * @return the value of `ctriporder`.data
     *
     * @mbggenerated
     */
    public String getData() {
        return data;
    }

    /**
     *
     *`ctriporder`.data
     *
     * @param data the value for `ctriporder`.data
     *
     * @mbggenerated
     */
    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    /**
     *
     *`ctriporder`.updated_at
     *
     * @return the value of `ctriporder`.updated_at
     *
     * @mbggenerated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     *`ctriporder`.updated_at
     *
     * @param updatedAt the value for `ctriporder`.updated_at
     *
     * @mbggenerated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     *`ctriporder`.created_at
     *
     * @return the value of `ctriporder`.created_at
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     *
     *`ctriporder`.created_at
     *
     * @param createdAt the value for `ctriporder`.created_at
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `ctriporder`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ctriporderid=").append(ctriporderid);
        sb.append(", vorderid=").append(vorderid);
        sb.append(", flightno=").append(flightno);
        sb.append(", airportname=").append(airportname);
        sb.append(", cartype=").append(cartype);
        sb.append(", ordertype=").append(ordertype);
        sb.append(", totalprice=").append(totalprice);
        sb.append(", distance=").append(distance);
        sb.append(", estimatedtime=").append(estimatedtime);
        sb.append(", guideid=").append(guideid);
        sb.append(", country=").append(country);
        sb.append(", city=").append(city);
        sb.append(", fromaddrname=").append(fromaddrname);
        sb.append(", fromlong=").append(fromlong);
        sb.append(", fromlat=").append(fromlat);
        sb.append(", toaddrname=").append(toaddrname);
        sb.append(", tolong=").append(tolong);
        sb.append(", tolat=").append(tolat);
        sb.append(", status=").append(status);
        sb.append(", airportthreecode=").append(airportthreecode);
        sb.append(", depflighttime=").append(depflighttime);
        sb.append(", fromaddrdetail=").append(fromaddrdetail);
        sb.append(", toaddrdetail=").append(toaddrdetail);
        sb.append(", pricemark=").append(pricemark);
        sb.append(", ctrippurchaseorderid=").append(ctrippurchaseorderid);
        sb.append(", issupportpickup=").append(issupportpickup);
        sb.append(", pickupcardmsg=").append(pickupcardmsg);
        sb.append(", childseatcount=").append(childseatcount);
        sb.append(", areacode=").append(areacode);
        sb.append(", salecode=").append(salecode);
        sb.append(", saleprice=").append(saleprice);
        sb.append(", data=").append(data);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append("]");
        return sb.toString();
    }
}