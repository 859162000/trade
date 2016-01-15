package com.hbc.api.trade.order.mapping.gen.bean;

import java.util.Date;

public class OrderBean {
    /**
     *  订单号
     *  所属表字段为`trade_order`.order_no
     */
    private String orderNo;

    /**
     *  第三方订单号
     *  所属表字段为`trade_order`.third_trade_no
     */
    private String thirdTradeNo;

    /**
     *  1001: 未付款*;1010: 已取消未付款;1020: 付款超时关闭;2001: 付款成功;2030: 导游到达;2050: 接到客人;2070: 行程结束;2080: 确认费用（target：导游）;2100: 结算完成（target：导游）;3001: 客诉退款（服务已开始）;3002: 已取消已服务;3003: 客诉退款（服务未开始）;3004: 客诉处理中;3005: 取消退款;3007: 已取消已付款
     *  所属表字段为`trade_order`.order_status
     */
    private Integer orderStatus;

    /**
     *  订单状态
     *  所属表字段为`trade_order`.order_status_name
     */
    private String orderStatusName;

    /**
     *  订单类型。1-接机；2-送机；3-日租；4-次租；5-精品线路
     *  所属表字段为`trade_order`.order_type
     */
    private Integer orderType;

    /**
     *  1接 2 送 4次 5 大长途 6小长途 7市内包车 8固定线路产品 
     *  所属表字段为`trade_order`.order_goods_type
     */
    private Integer orderGoodsType;

    /**
     *  商品ID
     *  所属表字段为`trade_order`.good_no
     */
    private String goodNo;

    /**
     *  订单来源1 C端  2 GDS    3 OTA
     *  所属表字段为`trade_order`.order_source
     */
    private Integer orderSource;

    /**
     *  渠道ID
     *  所属表字段为`trade_order`.order_channel
     */
    private Integer orderChannel;

    /**
     *  服务时间，服务开始时间  日租默认8点
     *  所属表字段为`trade_order`.service_time
     */
    private Date serviceTime;

    /**
     *  日租结束时间
     *  所属表字段为`trade_order`.service_end_time
     */
    private Date serviceEndTime;

    /**
     *  服务大洲ID
     *  所属表字段为`trade_order`.service_continent_id
     */
    private Integer serviceContinentId;

    /**
     *  服务大洲
     *  所属表字段为`trade_order`.service_continent_name
     */
    private String serviceContinentName;

    /**
     *  服务所在国家ID
     *  所属表字段为`trade_order`.service_country_id
     */
    private Integer serviceCountryId;

    /**
     *  服务所在国家名称
     *  所属表字段为`trade_order`.service_country_name
     */
    private String serviceCountryName;

    /**
     *  服务城市ID
     *  所属表字段为`trade_order`.service_city_id
     */
    private Integer serviceCityId;

    /**
     *  服务城市 拼音
     *  所属表字段为`trade_order`.service_city_spell
     */
    private String serviceCitySpell;

    /**
     *  服务城市英文名
     *  所属表字段为`trade_order`.service_city_enname
     */
    private String serviceCityEnname;

    /**
     *  服务城市名称
     *  所属表字段为`trade_order`.service_city_name
     */
    private String serviceCityName;

    /**
     *  服务酒店 区号类似与 86中国
     *  所属表字段为`trade_order`.service_area_code
     */
    private String serviceAreaCode;

    /**
     *  服务酒店或者区域电话号码
     *  所属表字段为`trade_order`.service_address_tel
     */
    private String serviceAddressTel;

    /**
     *  车导服务类型（日租 泰国）：
                        0-默认（该城市暂不可选择车导服务类型）， 
                        1-当地司机，中文客服，
                        2-当地司机，中文导游
     *  所属表字段为`trade_order`.service_daily_type
     */
    private Integer serviceDailyType;

    /**
     *  日租市内天数
     *  所属表字段为`trade_order`.service_local_days
     */
    private Integer serviceLocalDays;

    /**
     *  日租市外天数
     *  所属表字段为`trade_order`.service_nonlocal_days
     */
    private Integer serviceNonlocalDays;

    /**
     *  日租天数
     *  所属表字段为`trade_order`.total_days
     */
    private Integer totalDays;

    /**
     *  城市ID_天数，城市ID_天数
     *  所属表字段为`trade_order`.service_pass_city
     */
    private String servicePassCity;

    /**
     *  日租终止地 城市ID
     *  所属表字段为`trade_order`.service_end_cityid
     */
    private Integer serviceEndCityid;

    /**
     *  日租 终止地 城市名
     *  所属表字段为`trade_order`.service_end_cityname
     */
    private String serviceEndCityname;

    /**
     *  接送机预计完成时间
     *  所属表字段为`trade_order`.expected_comp_time
     */
    private Integer expectedCompTime;

    /**
     *  接送机：出发地（冗余）机场名+航站楼
     *  所属表字段为`trade_order`.start_address
     */
    private String startAddress;

    /**
     *  出发地详细地址
     *  所属表字段为`trade_order`.start_address_detail
     */
    private String startAddressDetail;

    /**
     *  出发地经纬度
     *  所属表字段为`trade_order`.start_address_poi
     */
    private String startAddressPoi;

    /**
     *  到达地
     *  所属表字段为`trade_order`.dest_address
     */
    private String destAddress;

    /**
     *  到达地详细地址
     *  所属表字段为`trade_order`.dest_address_detail
     */
    private String destAddressDetail;

    /**
     *  到达地经纬度
     *  所属表字段为`trade_order`.dest_address_poi
     */
    private String destAddressPoi;

    /**
     *  预估路程公里数
     *  所属表字段为`trade_order`.distance
     */
    private Double distance;

    /**
     *  车ID
     *  所属表字段为`trade_order`.car_id
     */
    private String carId;

    /**
     *  车牌号
     *  所属表字段为`trade_order`.car_license_num
     */
    private String carLicenseNum;

    /**
     *  1-经济 2-舒适 3-豪华 4-奢华
     *  所属表字段为`trade_order`.car_type_id
     */
    private Integer carTypeId;

    /**
     *  车座数
     *  所属表字段为`trade_order`.car_seat_num
     */
    private Integer carSeatNum;

    /**
     *  经济型5座车系
     *  所属表字段为`trade_order`.car_name
     */
    private String carName;

    /**
     *  现代圣达菲,起亚K5,雪佛兰迈锐宝
     *  所属表字段为`trade_order`.car_desc
     */
    private String carDesc;

    /**
     *  
     *  所属表字段为`trade_order`.coup_id
     */
    private String coupId;

    /**
     *  
     *  所属表字段为`trade_order`.coup_type
     */
    private Integer coupType;

    /**
     *  
     *  所属表字段为`trade_order`.coup_label
     */
    private String coupLabel;

    /**
     *  
     *  所属表字段为`trade_order`.coup_price_info
     */
    private String coupPriceInfo;

    /**
     *  pricemark 价格系统唯一标识
     *  所属表字段为`trade_order`.price_mark
     */
    private String priceMark;

    /**
     *  系统价（基准导游价   每次重新发单需取该价格）
     *  所属表字段为`trade_order`.price_base
     */
    private Double priceBase;

    /**
     *  导游价
     *  所属表字段为`trade_order`.price_guide
     */
    private Double priceGuide;

    /**
     *  票面价（GDS需要记录  agent 天猫）
     *  所属表字段为`trade_order`.price_ticket
     */
    private Double priceTicket;

    /**
     *  渠道价（城市上浮 以及 渠道上浮）,接机时已包含checkInPrice
     *  所属表字段为`trade_order`.price_channel
     */
    private Double priceChannel;

    /**
     *  导游报价 基础价
     *  所属表字段为`trade_order`.price_guide_base
     */
    private Double priceGuideBase;

    /**
     *  订单奖金 退款时需要退给系统
     *  所属表字段为`trade_order`.price_reward
     */
    private Double priceReward;

    /**
     *  登录ID
     *  所属表字段为`trade_order`.user_id
     */
    private String userId;

    /**
     *  用户资金帐号
     *  所属表字段为`trade_order`.user_account
     */
    private String userAccount;

    /**
     *  联系人姓名
     *  所属表字段为`trade_order`.user_name
     */
    private String userName;

    /**
     *  用户 手机区域码 1 类似中国 86
     *  所属表字段为`trade_order`.user_area_code1
     */
    private String userAreaCode1;

    /**
     *  用户手机号1
     *  所属表字段为`trade_order`.user_mobile1
     */
    private String userMobile1;

    /**
     *  用户 手机区域码 2 类似中国 86
     *  所属表字段为`trade_order`.user_area_code2
     */
    private String userAreaCode2;

    /**
     *  用户备注手机号2
     *  所属表字段为`trade_order`.user_mobile2
     */
    private String userMobile2;

    /**
     *  用户区号3
     *  所属表字段为`trade_order`.user_area_code3
     */
    private String userAreaCode3;

    /**
     *  用户备注手机号3
     *  所属表字段为`trade_order`.user_mobile3
     */
    private String userMobile3;

    /**
     *  用户 email
     *  所属表字段为`trade_order`.user_email
     */
    private String userEmail;

    /**
     *  用户评论状态  0 未评论 1已评论
     *  所属表字段为`trade_order`.user_comment_status
     */
    private Integer userCommentStatus;

    /**
     *  用户备注
     *  所属表字段为`trade_order`.user_remark
     */
    private String userRemark;

    /**
     *  系统评价状态，0：未评价；1：已评价
     *  所属表字段为`trade_order`.system_comment_status
     */
    private Integer systemCommentStatus;

    /**
     *  导游被指定时间
     *  所属表字段为`trade_order`.guide_assign_time
     */
    private Date guideAssignTime;

    /**
     *  预设导游ID
     *  所属表字段为`trade_order`.guide_pre_id
     */
    private String guidePreId;

    /**
     *  导游ID 默认"0" 用于发单建索引
     *  所属表字段为`trade_order`.guide_id
     */
    private String guideId;

    /**
     *  
     *  所属表字段为`trade_order`.guide_no
     */
    private String guideNo;

    /**
     *  导游名称
     *  所属表字段为`trade_order`.guide_name
     */
    private String guideName;

    /**
     *  导游手机区号
     *  所属表字段为`trade_order`.guide_area_code
     */
    private String guideAreaCode;

    /**
     *  导游手机
     *  所属表字段为`trade_order`.guide_mobile
     */
    private String guideMobile;

    /**
     *  导游评论状态 评论状态 0 未评价 1 已评价
     *  所属表字段为`trade_order`.guide_comment_status
     */
    private Integer guideCommentStatus;

    /**
     *  接单导游类型 同guide表的agencyType 地接社员工状态：1=地接社员工；2=地接社管理员枚举；0=不属于地接社（默认）
     *  所属表字段为`trade_order`.guide_agency_type
     */
    private Integer guideAgencyType;

    /**
     *  地接社老板ID
     *  所属表字段为`trade_order`.guide_agency_boss_id
     */
    private String guideAgencyBossId;

    /**
     *  地接社老板名称
     *  所属表字段为`trade_order`.guide_agency_boss_name
     */
    private String guideAgencyBossName;

    /**
     *  导游资金帐号no
     *  所属表字段为`trade_order`.guide_account_no
     */
    private String guideAccountNo;

    /**
     *  导游地接社ID
     *  所属表字段为`trade_order`.guide_agency_id
     */
    private String guideAgencyId;

    /**
     *  导游地接社名称
     *  所属表字段为`trade_order`.guide_agency_name
     */
    private String guideAgencyName;

    /**
     *  gds代理商ID
     *  所属表字段为`trade_order`.agent_id
     */
    private String agentId;

    /**
     *  gds 登录用户代理商名称
     *  所属表字段为`trade_order`.agent_name
     */
    private String agentName;

    /**
     *  gds操作员ID
     *  所属表字段为`trade_order`.agent_opid
     */
    private String agentOpid;

    /**
     *  gds 操作员名称
     *  所属表字段为`trade_order`.agent_opname
     */
    private String agentOpname;

    /**
     *  MIS 管理员（客服人员）ID
     *  所属表字段为`trade_order`.admin_id
     */
    private Integer adminId;

    /**
     *  聊天token
     *  所属表字段为`trade_order`.im_token
     */
    private String imToken;

    /**
     *  0 初始态 1 发单中 2已接单
     *  所属表字段为`trade_order`.deliver_status
     */
    private Integer deliverStatus;

    /**
     *  1-正常发单；2-立即发单；3-增量补发；4-取消重发 5 指派导游 6 支付前预指派导游
     *  所属表字段为`trade_order`.deliver_type
     */
    private Integer deliverType;

    /**
     *  接单时间
     *  所属表字段为`trade_order`.deliver_acp_time
     */
    private Date deliverAcpTime;

    /**
     *  0 普通订单 1急单
     *  所属表字段为`trade_order`.urgent_flag
     */
    private Integer urgentFlag;

    /**
     *  该城市下急单 小时数
     *  所属表字段为`trade_order`.urgent_hour
     */
    private Integer urgentHour;

    /**
     *  0 正常订单 1表示串单 2表示被串单
     *  所属表字段为`trade_order`.serial_flag
     */
    private Integer serialFlag;

    /**
     *  串单关联订单号
     *  所属表字段为`trade_order`.serial_order_no
     */
    private String serialOrderNo;

    /**
     *  拼车标识 0 正常 1为拼车
     *  所属表字段为`trade_order`.cargroup_flag
     */
    private Integer cargroupFlag;

    /**
     *  拼车标识符，用于表述一组订单 为一个拼车组
     *  所属表字段为`trade_order`.cargroup_id
     */
    private String cargroupId;

    /**
     *  航班编号
     *  所属表字段为`trade_order`.flight_no
     */
    private String flightNo;

    /**
     *  起飞 机场3字码
     *  所属表字段为`trade_order`.flight_airport_code
     */
    private String flightAirportCode;

    /**
     *  起飞机场名称
     *  所属表字段为`trade_order`.flight_airport_name
     */
    private String flightAirportName;

    /**
     *  航班计划起飞时间
     *  所属表字段为`trade_order`.flight_fly_time
     */
    private Date flightFlyTime;

    /**
     *  航班计划到达时间
     *  所属表字段为`trade_order`.flight_arrive_time
     */
    private Date flightArriveTime;

    /**
     *  航班是否为顾客自定义，0：正常，1：自定义输入
     *  所属表字段为`trade_order`.flight_is_custom
     */
    private Integer flightIsCustom;

    /**
     *  起飞机场航站楼
     *  所属表字段为`trade_order`.flight_airport_buiding
     */
    private String flightAirportBuiding;

    /**
     *  非常准 航班注册ID  外键关联
     *  所属表字段为`trade_order`.flight_register_id
     */
    private String flightRegisterId;

    /**
     *  接机牌姓名
     *  所属表字段为`trade_order`.flight_brand_sign
     */
    private String flightBrandSign;

    /**
     *  到达机场三字码
     *  所属表字段为`trade_order`.flight_dest_code
     */
    private String flightDestCode;

    /**
     *  降落机场名称
     *  所属表字段为`trade_order`.flight_dest_name
     */
    private String flightDestName;

    /**
     *  降落机场航站楼
     *  所属表字段为`trade_order`.flight_dest_building
     */
    private String flightDestBuilding;

    /**
     *  线路包车（精品线路）主题（标题）
     *  所属表字段为`trade_order`.line_subject
     */
    private String lineSubject;

    /**
     *  线路包车（精品线路）描述
     *  所属表字段为`trade_order`.line_description
     */
    private String lineDescription;

    /**
     *  成人数
     *  所属表字段为`trade_order`.adult_num
     */
    private Integer adultNum;

    /**
     *  儿童数
     *  所属表字段为`trade_order`.child_num
     */
    private Integer childNum;

    /**
     *  规则：年龄范围-数量。例如：1-1,2-3 附：1:婴儿座椅(0-1岁) 2:幼儿座椅(1-4岁) 3:学童座椅(4-7岁) 4:儿童座椅(7-12岁)]
     *  所属表字段为`trade_order`.child_seat
     */
    private String childSeat;

    /**
     *  签证类型：0未确定 1-国内签证；2-落地签证
     *  所属表字段为`trade_order`.is_arrival_visa
     */
    private Integer isArrivalVisa;

    /**
     *  办理登机费
     *  所属表字段为`trade_order`.check_in_price
     */
    private Double checkInPrice;

    /**
     *  订单取消时间
     *  所属表字段为`trade_order`.cancel_time
     */
    private Date cancelTime;

    /**
     *  订单完成时间
     *  所属表字段为`trade_order`.complete_time
     */
    private Date completeTime;

    /**
     *  是否有效。1：有效数据；0：无效数据
     *  所属表字段为`trade_order`.is_readable
     */
    private Integer isReadable;

    /**
     *  是否有新的订单状态，0：否；1：是
     *  所属表字段为`trade_order`.is_new_track
     */
    private Integer isNewTrack;

    /**
     *  下单时间
     *  所属表字段为`trade_order`.create_time
     */
    private Date createTime;

    /**
     *  
     *  所属表字段为`trade_order`.update_time
     */
    private Date updateTime;

    /**
     *  日租 订单行程描述，线路包车（精品线路）备注
     *  所属表字段为`trade_order`.journey_comment
     */
    private String journeyComment;

    /**
     *订单号
     *`trade_order`.order_no
     *
     * @return the value of `trade_order`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *订单号
     *`trade_order`.order_no
     *
     * @param orderNo the value for `trade_order`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *第三方订单号
     *`trade_order`.third_trade_no
     *
     * @return the value of `trade_order`.third_trade_no
     *
     * @mbggenerated
     */
    public String getThirdTradeNo() {
        return thirdTradeNo;
    }

    /**
     *第三方订单号
     *`trade_order`.third_trade_no
     *
     * @param thirdTradeNo the value for `trade_order`.third_trade_no
     *
     * @mbggenerated
     */
    public void setThirdTradeNo(String thirdTradeNo) {
        this.thirdTradeNo = thirdTradeNo == null ? null : thirdTradeNo.trim();
    }

    /**
     *1001: 未付款*;1010: 已取消未付款;1020: 付款超时关闭;2001: 付款成功;2030: 导游到达;2050: 接到客人;2070: 行程结束;2080: 确认费用（target：导游）;2100: 结算完成（target：导游）;3001: 客诉退款（服务已开始）;3002: 已取消已服务;3003: 客诉退款（服务未开始）;3004: 客诉处理中;3005: 取消退款;3007: 已取消已付款
     *`trade_order`.order_status
     *
     * @return the value of `trade_order`.order_status
     *
     * @mbggenerated
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     *1001: 未付款*;1010: 已取消未付款;1020: 付款超时关闭;2001: 付款成功;2030: 导游到达;2050: 接到客人;2070: 行程结束;2080: 确认费用（target：导游）;2100: 结算完成（target：导游）;3001: 客诉退款（服务已开始）;3002: 已取消已服务;3003: 客诉退款（服务未开始）;3004: 客诉处理中;3005: 取消退款;3007: 已取消已付款
     *`trade_order`.order_status
     *
     * @param orderStatus the value for `trade_order`.order_status
     *
     * @mbggenerated
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     *订单状态
     *`trade_order`.order_status_name
     *
     * @return the value of `trade_order`.order_status_name
     *
     * @mbggenerated
     */
    public String getOrderStatusName() {
        return orderStatusName;
    }

    /**
     *订单状态
     *`trade_order`.order_status_name
     *
     * @param orderStatusName the value for `trade_order`.order_status_name
     *
     * @mbggenerated
     */
    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName == null ? null : orderStatusName.trim();
    }

    /**
     *订单类型。1-接机；2-送机；3-日租；4-次租；5-精品线路
     *`trade_order`.order_type
     *
     * @return the value of `trade_order`.order_type
     *
     * @mbggenerated
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     *订单类型。1-接机；2-送机；3-日租；4-次租；5-精品线路
     *`trade_order`.order_type
     *
     * @param orderType the value for `trade_order`.order_type
     *
     * @mbggenerated
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     *1接 2 送 4次 5 大长途 6小长途 7市内包车 8固定线路产品 
     *`trade_order`.order_goods_type
     *
     * @return the value of `trade_order`.order_goods_type
     *
     * @mbggenerated
     */
    public Integer getOrderGoodsType() {
        return orderGoodsType;
    }

    /**
     *1接 2 送 4次 5 大长途 6小长途 7市内包车 8固定线路产品 
     *`trade_order`.order_goods_type
     *
     * @param orderGoodsType the value for `trade_order`.order_goods_type
     *
     * @mbggenerated
     */
    public void setOrderGoodsType(Integer orderGoodsType) {
        this.orderGoodsType = orderGoodsType;
    }

    /**
     *商品ID
     *`trade_order`.good_no
     *
     * @return the value of `trade_order`.good_no
     *
     * @mbggenerated
     */
    public String getGoodNo() {
        return goodNo;
    }

    /**
     *商品ID
     *`trade_order`.good_no
     *
     * @param goodNo the value for `trade_order`.good_no
     *
     * @mbggenerated
     */
    public void setGoodNo(String goodNo) {
        this.goodNo = goodNo == null ? null : goodNo.trim();
    }

    /**
     *订单来源1 C端  2 GDS    3 OTA
     *`trade_order`.order_source
     *
     * @return the value of `trade_order`.order_source
     *
     * @mbggenerated
     */
    public Integer getOrderSource() {
        return orderSource;
    }

    /**
     *订单来源1 C端  2 GDS    3 OTA
     *`trade_order`.order_source
     *
     * @param orderSource the value for `trade_order`.order_source
     *
     * @mbggenerated
     */
    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    /**
     *渠道ID
     *`trade_order`.order_channel
     *
     * @return the value of `trade_order`.order_channel
     *
     * @mbggenerated
     */
    public Integer getOrderChannel() {
        return orderChannel;
    }

    /**
     *渠道ID
     *`trade_order`.order_channel
     *
     * @param orderChannel the value for `trade_order`.order_channel
     *
     * @mbggenerated
     */
    public void setOrderChannel(Integer orderChannel) {
        this.orderChannel = orderChannel;
    }

    /**
     *服务时间，服务开始时间  日租默认8点
     *`trade_order`.service_time
     *
     * @return the value of `trade_order`.service_time
     *
     * @mbggenerated
     */
    public Date getServiceTime() {
        return serviceTime;
    }

    /**
     *服务时间，服务开始时间  日租默认8点
     *`trade_order`.service_time
     *
     * @param serviceTime the value for `trade_order`.service_time
     *
     * @mbggenerated
     */
    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     *日租结束时间
     *`trade_order`.service_end_time
     *
     * @return the value of `trade_order`.service_end_time
     *
     * @mbggenerated
     */
    public Date getServiceEndTime() {
        return serviceEndTime;
    }

    /**
     *日租结束时间
     *`trade_order`.service_end_time
     *
     * @param serviceEndTime the value for `trade_order`.service_end_time
     *
     * @mbggenerated
     */
    public void setServiceEndTime(Date serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    /**
     *服务大洲ID
     *`trade_order`.service_continent_id
     *
     * @return the value of `trade_order`.service_continent_id
     *
     * @mbggenerated
     */
    public Integer getServiceContinentId() {
        return serviceContinentId;
    }

    /**
     *服务大洲ID
     *`trade_order`.service_continent_id
     *
     * @param serviceContinentId the value for `trade_order`.service_continent_id
     *
     * @mbggenerated
     */
    public void setServiceContinentId(Integer serviceContinentId) {
        this.serviceContinentId = serviceContinentId;
    }

    /**
     *服务大洲
     *`trade_order`.service_continent_name
     *
     * @return the value of `trade_order`.service_continent_name
     *
     * @mbggenerated
     */
    public String getServiceContinentName() {
        return serviceContinentName;
    }

    /**
     *服务大洲
     *`trade_order`.service_continent_name
     *
     * @param serviceContinentName the value for `trade_order`.service_continent_name
     *
     * @mbggenerated
     */
    public void setServiceContinentName(String serviceContinentName) {
        this.serviceContinentName = serviceContinentName == null ? null : serviceContinentName.trim();
    }

    /**
     *服务所在国家ID
     *`trade_order`.service_country_id
     *
     * @return the value of `trade_order`.service_country_id
     *
     * @mbggenerated
     */
    public Integer getServiceCountryId() {
        return serviceCountryId;
    }

    /**
     *服务所在国家ID
     *`trade_order`.service_country_id
     *
     * @param serviceCountryId the value for `trade_order`.service_country_id
     *
     * @mbggenerated
     */
    public void setServiceCountryId(Integer serviceCountryId) {
        this.serviceCountryId = serviceCountryId;
    }

    /**
     *服务所在国家名称
     *`trade_order`.service_country_name
     *
     * @return the value of `trade_order`.service_country_name
     *
     * @mbggenerated
     */
    public String getServiceCountryName() {
        return serviceCountryName;
    }

    /**
     *服务所在国家名称
     *`trade_order`.service_country_name
     *
     * @param serviceCountryName the value for `trade_order`.service_country_name
     *
     * @mbggenerated
     */
    public void setServiceCountryName(String serviceCountryName) {
        this.serviceCountryName = serviceCountryName == null ? null : serviceCountryName.trim();
    }

    /**
     *服务城市ID
     *`trade_order`.service_city_id
     *
     * @return the value of `trade_order`.service_city_id
     *
     * @mbggenerated
     */
    public Integer getServiceCityId() {
        return serviceCityId;
    }

    /**
     *服务城市ID
     *`trade_order`.service_city_id
     *
     * @param serviceCityId the value for `trade_order`.service_city_id
     *
     * @mbggenerated
     */
    public void setServiceCityId(Integer serviceCityId) {
        this.serviceCityId = serviceCityId;
    }

    /**
     *服务城市 拼音
     *`trade_order`.service_city_spell
     *
     * @return the value of `trade_order`.service_city_spell
     *
     * @mbggenerated
     */
    public String getServiceCitySpell() {
        return serviceCitySpell;
    }

    /**
     *服务城市 拼音
     *`trade_order`.service_city_spell
     *
     * @param serviceCitySpell the value for `trade_order`.service_city_spell
     *
     * @mbggenerated
     */
    public void setServiceCitySpell(String serviceCitySpell) {
        this.serviceCitySpell = serviceCitySpell == null ? null : serviceCitySpell.trim();
    }

    /**
     *服务城市英文名
     *`trade_order`.service_city_enname
     *
     * @return the value of `trade_order`.service_city_enname
     *
     * @mbggenerated
     */
    public String getServiceCityEnname() {
        return serviceCityEnname;
    }

    /**
     *服务城市英文名
     *`trade_order`.service_city_enname
     *
     * @param serviceCityEnname the value for `trade_order`.service_city_enname
     *
     * @mbggenerated
     */
    public void setServiceCityEnname(String serviceCityEnname) {
        this.serviceCityEnname = serviceCityEnname == null ? null : serviceCityEnname.trim();
    }

    /**
     *服务城市名称
     *`trade_order`.service_city_name
     *
     * @return the value of `trade_order`.service_city_name
     *
     * @mbggenerated
     */
    public String getServiceCityName() {
        return serviceCityName;
    }

    /**
     *服务城市名称
     *`trade_order`.service_city_name
     *
     * @param serviceCityName the value for `trade_order`.service_city_name
     *
     * @mbggenerated
     */
    public void setServiceCityName(String serviceCityName) {
        this.serviceCityName = serviceCityName == null ? null : serviceCityName.trim();
    }

    /**
     *服务酒店 区号类似与 86中国
     *`trade_order`.service_area_code
     *
     * @return the value of `trade_order`.service_area_code
     *
     * @mbggenerated
     */
    public String getServiceAreaCode() {
        return serviceAreaCode;
    }

    /**
     *服务酒店 区号类似与 86中国
     *`trade_order`.service_area_code
     *
     * @param serviceAreaCode the value for `trade_order`.service_area_code
     *
     * @mbggenerated
     */
    public void setServiceAreaCode(String serviceAreaCode) {
        this.serviceAreaCode = serviceAreaCode == null ? null : serviceAreaCode.trim();
    }

    /**
     *服务酒店或者区域电话号码
     *`trade_order`.service_address_tel
     *
     * @return the value of `trade_order`.service_address_tel
     *
     * @mbggenerated
     */
    public String getServiceAddressTel() {
        return serviceAddressTel;
    }

    /**
     *服务酒店或者区域电话号码
     *`trade_order`.service_address_tel
     *
     * @param serviceAddressTel the value for `trade_order`.service_address_tel
     *
     * @mbggenerated
     */
    public void setServiceAddressTel(String serviceAddressTel) {
        this.serviceAddressTel = serviceAddressTel == null ? null : serviceAddressTel.trim();
    }

    /**
     *车导服务类型（日租 泰国）：
                        0-默认（该城市暂不可选择车导服务类型）， 
                        1-当地司机，中文客服，
                        2-当地司机，中文导游
     *`trade_order`.service_daily_type
     *
     * @return the value of `trade_order`.service_daily_type
     *
     * @mbggenerated
     */
    public Integer getServiceDailyType() {
        return serviceDailyType;
    }

    /**
     *车导服务类型（日租 泰国）：
                        0-默认（该城市暂不可选择车导服务类型）， 
                        1-当地司机，中文客服，
                        2-当地司机，中文导游
     *`trade_order`.service_daily_type
     *
     * @param serviceDailyType the value for `trade_order`.service_daily_type
     *
     * @mbggenerated
     */
    public void setServiceDailyType(Integer serviceDailyType) {
        this.serviceDailyType = serviceDailyType;
    }

    /**
     *日租市内天数
     *`trade_order`.service_local_days
     *
     * @return the value of `trade_order`.service_local_days
     *
     * @mbggenerated
     */
    public Integer getServiceLocalDays() {
        return serviceLocalDays;
    }

    /**
     *日租市内天数
     *`trade_order`.service_local_days
     *
     * @param serviceLocalDays the value for `trade_order`.service_local_days
     *
     * @mbggenerated
     */
    public void setServiceLocalDays(Integer serviceLocalDays) {
        this.serviceLocalDays = serviceLocalDays;
    }

    /**
     *日租市外天数
     *`trade_order`.service_nonlocal_days
     *
     * @return the value of `trade_order`.service_nonlocal_days
     *
     * @mbggenerated
     */
    public Integer getServiceNonlocalDays() {
        return serviceNonlocalDays;
    }

    /**
     *日租市外天数
     *`trade_order`.service_nonlocal_days
     *
     * @param serviceNonlocalDays the value for `trade_order`.service_nonlocal_days
     *
     * @mbggenerated
     */
    public void setServiceNonlocalDays(Integer serviceNonlocalDays) {
        this.serviceNonlocalDays = serviceNonlocalDays;
    }

    /**
     *日租天数
     *`trade_order`.total_days
     *
     * @return the value of `trade_order`.total_days
     *
     * @mbggenerated
     */
    public Integer getTotalDays() {
        return totalDays;
    }

    /**
     *日租天数
     *`trade_order`.total_days
     *
     * @param totalDays the value for `trade_order`.total_days
     *
     * @mbggenerated
     */
    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    /**
     *城市ID_天数，城市ID_天数
     *`trade_order`.service_pass_city
     *
     * @return the value of `trade_order`.service_pass_city
     *
     * @mbggenerated
     */
    public String getServicePassCity() {
        return servicePassCity;
    }

    /**
     *城市ID_天数，城市ID_天数
     *`trade_order`.service_pass_city
     *
     * @param servicePassCity the value for `trade_order`.service_pass_city
     *
     * @mbggenerated
     */
    public void setServicePassCity(String servicePassCity) {
        this.servicePassCity = servicePassCity == null ? null : servicePassCity.trim();
    }

    /**
     *日租终止地 城市ID
     *`trade_order`.service_end_cityid
     *
     * @return the value of `trade_order`.service_end_cityid
     *
     * @mbggenerated
     */
    public Integer getServiceEndCityid() {
        return serviceEndCityid;
    }

    /**
     *日租终止地 城市ID
     *`trade_order`.service_end_cityid
     *
     * @param serviceEndCityid the value for `trade_order`.service_end_cityid
     *
     * @mbggenerated
     */
    public void setServiceEndCityid(Integer serviceEndCityid) {
        this.serviceEndCityid = serviceEndCityid;
    }

    /**
     *日租 终止地 城市名
     *`trade_order`.service_end_cityname
     *
     * @return the value of `trade_order`.service_end_cityname
     *
     * @mbggenerated
     */
    public String getServiceEndCityname() {
        return serviceEndCityname;
    }

    /**
     *日租 终止地 城市名
     *`trade_order`.service_end_cityname
     *
     * @param serviceEndCityname the value for `trade_order`.service_end_cityname
     *
     * @mbggenerated
     */
    public void setServiceEndCityname(String serviceEndCityname) {
        this.serviceEndCityname = serviceEndCityname == null ? null : serviceEndCityname.trim();
    }

    /**
     *接送机预计完成时间
     *`trade_order`.expected_comp_time
     *
     * @return the value of `trade_order`.expected_comp_time
     *
     * @mbggenerated
     */
    public Integer getExpectedCompTime() {
        return expectedCompTime;
    }

    /**
     *接送机预计完成时间
     *`trade_order`.expected_comp_time
     *
     * @param expectedCompTime the value for `trade_order`.expected_comp_time
     *
     * @mbggenerated
     */
    public void setExpectedCompTime(Integer expectedCompTime) {
        this.expectedCompTime = expectedCompTime;
    }

    /**
     *接送机：出发地（冗余）机场名+航站楼
     *`trade_order`.start_address
     *
     * @return the value of `trade_order`.start_address
     *
     * @mbggenerated
     */
    public String getStartAddress() {
        return startAddress;
    }

    /**
     *接送机：出发地（冗余）机场名+航站楼
     *`trade_order`.start_address
     *
     * @param startAddress the value for `trade_order`.start_address
     *
     * @mbggenerated
     */
    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress == null ? null : startAddress.trim();
    }

    /**
     *出发地详细地址
     *`trade_order`.start_address_detail
     *
     * @return the value of `trade_order`.start_address_detail
     *
     * @mbggenerated
     */
    public String getStartAddressDetail() {
        return startAddressDetail;
    }

    /**
     *出发地详细地址
     *`trade_order`.start_address_detail
     *
     * @param startAddressDetail the value for `trade_order`.start_address_detail
     *
     * @mbggenerated
     */
    public void setStartAddressDetail(String startAddressDetail) {
        this.startAddressDetail = startAddressDetail == null ? null : startAddressDetail.trim();
    }

    /**
     *出发地经纬度
     *`trade_order`.start_address_poi
     *
     * @return the value of `trade_order`.start_address_poi
     *
     * @mbggenerated
     */
    public String getStartAddressPoi() {
        return startAddressPoi;
    }

    /**
     *出发地经纬度
     *`trade_order`.start_address_poi
     *
     * @param startAddressPoi the value for `trade_order`.start_address_poi
     *
     * @mbggenerated
     */
    public void setStartAddressPoi(String startAddressPoi) {
        this.startAddressPoi = startAddressPoi == null ? null : startAddressPoi.trim();
    }

    /**
     *到达地
     *`trade_order`.dest_address
     *
     * @return the value of `trade_order`.dest_address
     *
     * @mbggenerated
     */
    public String getDestAddress() {
        return destAddress;
    }

    /**
     *到达地
     *`trade_order`.dest_address
     *
     * @param destAddress the value for `trade_order`.dest_address
     *
     * @mbggenerated
     */
    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress == null ? null : destAddress.trim();
    }

    /**
     *到达地详细地址
     *`trade_order`.dest_address_detail
     *
     * @return the value of `trade_order`.dest_address_detail
     *
     * @mbggenerated
     */
    public String getDestAddressDetail() {
        return destAddressDetail;
    }

    /**
     *到达地详细地址
     *`trade_order`.dest_address_detail
     *
     * @param destAddressDetail the value for `trade_order`.dest_address_detail
     *
     * @mbggenerated
     */
    public void setDestAddressDetail(String destAddressDetail) {
        this.destAddressDetail = destAddressDetail == null ? null : destAddressDetail.trim();
    }

    /**
     *到达地经纬度
     *`trade_order`.dest_address_poi
     *
     * @return the value of `trade_order`.dest_address_poi
     *
     * @mbggenerated
     */
    public String getDestAddressPoi() {
        return destAddressPoi;
    }

    /**
     *到达地经纬度
     *`trade_order`.dest_address_poi
     *
     * @param destAddressPoi the value for `trade_order`.dest_address_poi
     *
     * @mbggenerated
     */
    public void setDestAddressPoi(String destAddressPoi) {
        this.destAddressPoi = destAddressPoi == null ? null : destAddressPoi.trim();
    }

    /**
     *预估路程公里数
     *`trade_order`.distance
     *
     * @return the value of `trade_order`.distance
     *
     * @mbggenerated
     */
    public Double getDistance() {
        return distance;
    }

    /**
     *预估路程公里数
     *`trade_order`.distance
     *
     * @param distance the value for `trade_order`.distance
     *
     * @mbggenerated
     */
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    /**
     *车ID
     *`trade_order`.car_id
     *
     * @return the value of `trade_order`.car_id
     *
     * @mbggenerated
     */
    public String getCarId() {
        return carId;
    }

    /**
     *车ID
     *`trade_order`.car_id
     *
     * @param carId the value for `trade_order`.car_id
     *
     * @mbggenerated
     */
    public void setCarId(String carId) {
        this.carId = carId == null ? null : carId.trim();
    }

    /**
     *车牌号
     *`trade_order`.car_license_num
     *
     * @return the value of `trade_order`.car_license_num
     *
     * @mbggenerated
     */
    public String getCarLicenseNum() {
        return carLicenseNum;
    }

    /**
     *车牌号
     *`trade_order`.car_license_num
     *
     * @param carLicenseNum the value for `trade_order`.car_license_num
     *
     * @mbggenerated
     */
    public void setCarLicenseNum(String carLicenseNum) {
        this.carLicenseNum = carLicenseNum == null ? null : carLicenseNum.trim();
    }

    /**
     *1-经济 2-舒适 3-豪华 4-奢华
     *`trade_order`.car_type_id
     *
     * @return the value of `trade_order`.car_type_id
     *
     * @mbggenerated
     */
    public Integer getCarTypeId() {
        return carTypeId;
    }

    /**
     *1-经济 2-舒适 3-豪华 4-奢华
     *`trade_order`.car_type_id
     *
     * @param carTypeId the value for `trade_order`.car_type_id
     *
     * @mbggenerated
     */
    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

    /**
     *车座数
     *`trade_order`.car_seat_num
     *
     * @return the value of `trade_order`.car_seat_num
     *
     * @mbggenerated
     */
    public Integer getCarSeatNum() {
        return carSeatNum;
    }

    /**
     *车座数
     *`trade_order`.car_seat_num
     *
     * @param carSeatNum the value for `trade_order`.car_seat_num
     *
     * @mbggenerated
     */
    public void setCarSeatNum(Integer carSeatNum) {
        this.carSeatNum = carSeatNum;
    }

    /**
     *经济型5座车系
     *`trade_order`.car_name
     *
     * @return the value of `trade_order`.car_name
     *
     * @mbggenerated
     */
    public String getCarName() {
        return carName;
    }

    /**
     *经济型5座车系
     *`trade_order`.car_name
     *
     * @param carName the value for `trade_order`.car_name
     *
     * @mbggenerated
     */
    public void setCarName(String carName) {
        this.carName = carName == null ? null : carName.trim();
    }

    /**
     *现代圣达菲,起亚K5,雪佛兰迈锐宝
     *`trade_order`.car_desc
     *
     * @return the value of `trade_order`.car_desc
     *
     * @mbggenerated
     */
    public String getCarDesc() {
        return carDesc;
    }

    /**
     *现代圣达菲,起亚K5,雪佛兰迈锐宝
     *`trade_order`.car_desc
     *
     * @param carDesc the value for `trade_order`.car_desc
     *
     * @mbggenerated
     */
    public void setCarDesc(String carDesc) {
        this.carDesc = carDesc == null ? null : carDesc.trim();
    }

    /**
     *
     *`trade_order`.coup_id
     *
     * @return the value of `trade_order`.coup_id
     *
     * @mbggenerated
     */
    public String getCoupId() {
        return coupId;
    }

    /**
     *
     *`trade_order`.coup_id
     *
     * @param coupId the value for `trade_order`.coup_id
     *
     * @mbggenerated
     */
    public void setCoupId(String coupId) {
        this.coupId = coupId == null ? null : coupId.trim();
    }

    /**
     *
     *`trade_order`.coup_type
     *
     * @return the value of `trade_order`.coup_type
     *
     * @mbggenerated
     */
    public Integer getCoupType() {
        return coupType;
    }

    /**
     *
     *`trade_order`.coup_type
     *
     * @param coupType the value for `trade_order`.coup_type
     *
     * @mbggenerated
     */
    public void setCoupType(Integer coupType) {
        this.coupType = coupType;
    }

    /**
     *
     *`trade_order`.coup_label
     *
     * @return the value of `trade_order`.coup_label
     *
     * @mbggenerated
     */
    public String getCoupLabel() {
        return coupLabel;
    }

    /**
     *
     *`trade_order`.coup_label
     *
     * @param coupLabel the value for `trade_order`.coup_label
     *
     * @mbggenerated
     */
    public void setCoupLabel(String coupLabel) {
        this.coupLabel = coupLabel == null ? null : coupLabel.trim();
    }

    /**
     *
     *`trade_order`.coup_price_info
     *
     * @return the value of `trade_order`.coup_price_info
     *
     * @mbggenerated
     */
    public String getCoupPriceInfo() {
        return coupPriceInfo;
    }

    /**
     *
     *`trade_order`.coup_price_info
     *
     * @param coupPriceInfo the value for `trade_order`.coup_price_info
     *
     * @mbggenerated
     */
    public void setCoupPriceInfo(String coupPriceInfo) {
        this.coupPriceInfo = coupPriceInfo == null ? null : coupPriceInfo.trim();
    }

    /**
     *pricemark 价格系统唯一标识
     *`trade_order`.price_mark
     *
     * @return the value of `trade_order`.price_mark
     *
     * @mbggenerated
     */
    public String getPriceMark() {
        return priceMark;
    }

    /**
     *pricemark 价格系统唯一标识
     *`trade_order`.price_mark
     *
     * @param priceMark the value for `trade_order`.price_mark
     *
     * @mbggenerated
     */
    public void setPriceMark(String priceMark) {
        this.priceMark = priceMark == null ? null : priceMark.trim();
    }

    /**
     *系统价（基准导游价   每次重新发单需取该价格）
     *`trade_order`.price_base
     *
     * @return the value of `trade_order`.price_base
     *
     * @mbggenerated
     */
    public Double getPriceBase() {
        return priceBase;
    }

    /**
     *系统价（基准导游价   每次重新发单需取该价格）
     *`trade_order`.price_base
     *
     * @param priceBase the value for `trade_order`.price_base
     *
     * @mbggenerated
     */
    public void setPriceBase(Double priceBase) {
        this.priceBase = priceBase;
    }

    /**
     *导游价
     *`trade_order`.price_guide
     *
     * @return the value of `trade_order`.price_guide
     *
     * @mbggenerated
     */
    public Double getPriceGuide() {
        return priceGuide;
    }

    /**
     *导游价
     *`trade_order`.price_guide
     *
     * @param priceGuide the value for `trade_order`.price_guide
     *
     * @mbggenerated
     */
    public void setPriceGuide(Double priceGuide) {
        this.priceGuide = priceGuide;
    }

    /**
     *票面价（GDS需要记录  agent 天猫）
     *`trade_order`.price_ticket
     *
     * @return the value of `trade_order`.price_ticket
     *
     * @mbggenerated
     */
    public Double getPriceTicket() {
        return priceTicket;
    }

    /**
     *票面价（GDS需要记录  agent 天猫）
     *`trade_order`.price_ticket
     *
     * @param priceTicket the value for `trade_order`.price_ticket
     *
     * @mbggenerated
     */
    public void setPriceTicket(Double priceTicket) {
        this.priceTicket = priceTicket;
    }

    /**
     *渠道价（城市上浮 以及 渠道上浮）,接机时已包含checkInPrice
     *`trade_order`.price_channel
     *
     * @return the value of `trade_order`.price_channel
     *
     * @mbggenerated
     */
    public Double getPriceChannel() {
        return priceChannel;
    }

    /**
     *渠道价（城市上浮 以及 渠道上浮）,接机时已包含checkInPrice
     *`trade_order`.price_channel
     *
     * @param priceChannel the value for `trade_order`.price_channel
     *
     * @mbggenerated
     */
    public void setPriceChannel(Double priceChannel) {
        this.priceChannel = priceChannel;
    }

    /**
     *导游报价 基础价
     *`trade_order`.price_guide_base
     *
     * @return the value of `trade_order`.price_guide_base
     *
     * @mbggenerated
     */
    public Double getPriceGuideBase() {
        return priceGuideBase;
    }

    /**
     *导游报价 基础价
     *`trade_order`.price_guide_base
     *
     * @param priceGuideBase the value for `trade_order`.price_guide_base
     *
     * @mbggenerated
     */
    public void setPriceGuideBase(Double priceGuideBase) {
        this.priceGuideBase = priceGuideBase;
    }

    /**
     *订单奖金 退款时需要退给系统
     *`trade_order`.price_reward
     *
     * @return the value of `trade_order`.price_reward
     *
     * @mbggenerated
     */
    public Double getPriceReward() {
        return priceReward;
    }

    /**
     *订单奖金 退款时需要退给系统
     *`trade_order`.price_reward
     *
     * @param priceReward the value for `trade_order`.price_reward
     *
     * @mbggenerated
     */
    public void setPriceReward(Double priceReward) {
        this.priceReward = priceReward;
    }

    /**
     *登录ID
     *`trade_order`.user_id
     *
     * @return the value of `trade_order`.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     *登录ID
     *`trade_order`.user_id
     *
     * @param userId the value for `trade_order`.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     *用户资金帐号
     *`trade_order`.user_account
     *
     * @return the value of `trade_order`.user_account
     *
     * @mbggenerated
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     *用户资金帐号
     *`trade_order`.user_account
     *
     * @param userAccount the value for `trade_order`.user_account
     *
     * @mbggenerated
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    /**
     *联系人姓名
     *`trade_order`.user_name
     *
     * @return the value of `trade_order`.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     *联系人姓名
     *`trade_order`.user_name
     *
     * @param userName the value for `trade_order`.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     *用户 手机区域码 1 类似中国 86
     *`trade_order`.user_area_code1
     *
     * @return the value of `trade_order`.user_area_code1
     *
     * @mbggenerated
     */
    public String getUserAreaCode1() {
        return userAreaCode1;
    }

    /**
     *用户 手机区域码 1 类似中国 86
     *`trade_order`.user_area_code1
     *
     * @param userAreaCode1 the value for `trade_order`.user_area_code1
     *
     * @mbggenerated
     */
    public void setUserAreaCode1(String userAreaCode1) {
        this.userAreaCode1 = userAreaCode1 == null ? null : userAreaCode1.trim();
    }

    /**
     *用户手机号1
     *`trade_order`.user_mobile1
     *
     * @return the value of `trade_order`.user_mobile1
     *
     * @mbggenerated
     */
    public String getUserMobile1() {
        return userMobile1;
    }

    /**
     *用户手机号1
     *`trade_order`.user_mobile1
     *
     * @param userMobile1 the value for `trade_order`.user_mobile1
     *
     * @mbggenerated
     */
    public void setUserMobile1(String userMobile1) {
        this.userMobile1 = userMobile1 == null ? null : userMobile1.trim();
    }

    /**
     *用户 手机区域码 2 类似中国 86
     *`trade_order`.user_area_code2
     *
     * @return the value of `trade_order`.user_area_code2
     *
     * @mbggenerated
     */
    public String getUserAreaCode2() {
        return userAreaCode2;
    }

    /**
     *用户 手机区域码 2 类似中国 86
     *`trade_order`.user_area_code2
     *
     * @param userAreaCode2 the value for `trade_order`.user_area_code2
     *
     * @mbggenerated
     */
    public void setUserAreaCode2(String userAreaCode2) {
        this.userAreaCode2 = userAreaCode2 == null ? null : userAreaCode2.trim();
    }

    /**
     *用户备注手机号2
     *`trade_order`.user_mobile2
     *
     * @return the value of `trade_order`.user_mobile2
     *
     * @mbggenerated
     */
    public String getUserMobile2() {
        return userMobile2;
    }

    /**
     *用户备注手机号2
     *`trade_order`.user_mobile2
     *
     * @param userMobile2 the value for `trade_order`.user_mobile2
     *
     * @mbggenerated
     */
    public void setUserMobile2(String userMobile2) {
        this.userMobile2 = userMobile2 == null ? null : userMobile2.trim();
    }

    /**
     *用户区号3
     *`trade_order`.user_area_code3
     *
     * @return the value of `trade_order`.user_area_code3
     *
     * @mbggenerated
     */
    public String getUserAreaCode3() {
        return userAreaCode3;
    }

    /**
     *用户区号3
     *`trade_order`.user_area_code3
     *
     * @param userAreaCode3 the value for `trade_order`.user_area_code3
     *
     * @mbggenerated
     */
    public void setUserAreaCode3(String userAreaCode3) {
        this.userAreaCode3 = userAreaCode3 == null ? null : userAreaCode3.trim();
    }

    /**
     *用户备注手机号3
     *`trade_order`.user_mobile3
     *
     * @return the value of `trade_order`.user_mobile3
     *
     * @mbggenerated
     */
    public String getUserMobile3() {
        return userMobile3;
    }

    /**
     *用户备注手机号3
     *`trade_order`.user_mobile3
     *
     * @param userMobile3 the value for `trade_order`.user_mobile3
     *
     * @mbggenerated
     */
    public void setUserMobile3(String userMobile3) {
        this.userMobile3 = userMobile3 == null ? null : userMobile3.trim();
    }

    /**
     *用户 email
     *`trade_order`.user_email
     *
     * @return the value of `trade_order`.user_email
     *
     * @mbggenerated
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     *用户 email
     *`trade_order`.user_email
     *
     * @param userEmail the value for `trade_order`.user_email
     *
     * @mbggenerated
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    /**
     *用户评论状态  0 未评论 1已评论
     *`trade_order`.user_comment_status
     *
     * @return the value of `trade_order`.user_comment_status
     *
     * @mbggenerated
     */
    public Integer getUserCommentStatus() {
        return userCommentStatus;
    }

    /**
     *用户评论状态  0 未评论 1已评论
     *`trade_order`.user_comment_status
     *
     * @param userCommentStatus the value for `trade_order`.user_comment_status
     *
     * @mbggenerated
     */
    public void setUserCommentStatus(Integer userCommentStatus) {
        this.userCommentStatus = userCommentStatus;
    }

    /**
     *用户备注
     *`trade_order`.user_remark
     *
     * @return the value of `trade_order`.user_remark
     *
     * @mbggenerated
     */
    public String getUserRemark() {
        return userRemark;
    }

    /**
     *用户备注
     *`trade_order`.user_remark
     *
     * @param userRemark the value for `trade_order`.user_remark
     *
     * @mbggenerated
     */
    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark == null ? null : userRemark.trim();
    }

    /**
     *系统评价状态，0：未评价；1：已评价
     *`trade_order`.system_comment_status
     *
     * @return the value of `trade_order`.system_comment_status
     *
     * @mbggenerated
     */
    public Integer getSystemCommentStatus() {
        return systemCommentStatus;
    }

    /**
     *系统评价状态，0：未评价；1：已评价
     *`trade_order`.system_comment_status
     *
     * @param systemCommentStatus the value for `trade_order`.system_comment_status
     *
     * @mbggenerated
     */
    public void setSystemCommentStatus(Integer systemCommentStatus) {
        this.systemCommentStatus = systemCommentStatus;
    }

    /**
     *导游被指定时间
     *`trade_order`.guide_assign_time
     *
     * @return the value of `trade_order`.guide_assign_time
     *
     * @mbggenerated
     */
    public Date getGuideAssignTime() {
        return guideAssignTime;
    }

    /**
     *导游被指定时间
     *`trade_order`.guide_assign_time
     *
     * @param guideAssignTime the value for `trade_order`.guide_assign_time
     *
     * @mbggenerated
     */
    public void setGuideAssignTime(Date guideAssignTime) {
        this.guideAssignTime = guideAssignTime;
    }

    /**
     *预设导游ID
     *`trade_order`.guide_pre_id
     *
     * @return the value of `trade_order`.guide_pre_id
     *
     * @mbggenerated
     */
    public String getGuidePreId() {
        return guidePreId;
    }

    /**
     *预设导游ID
     *`trade_order`.guide_pre_id
     *
     * @param guidePreId the value for `trade_order`.guide_pre_id
     *
     * @mbggenerated
     */
    public void setGuidePreId(String guidePreId) {
        this.guidePreId = guidePreId == null ? null : guidePreId.trim();
    }

    /**
     *导游ID 默认"0" 用于发单建索引
     *`trade_order`.guide_id
     *
     * @return the value of `trade_order`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *导游ID 默认"0" 用于发单建索引
     *`trade_order`.guide_id
     *
     * @param guideId the value for `trade_order`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *
     *`trade_order`.guide_no
     *
     * @return the value of `trade_order`.guide_no
     *
     * @mbggenerated
     */
    public String getGuideNo() {
        return guideNo;
    }

    /**
     *
     *`trade_order`.guide_no
     *
     * @param guideNo the value for `trade_order`.guide_no
     *
     * @mbggenerated
     */
    public void setGuideNo(String guideNo) {
        this.guideNo = guideNo == null ? null : guideNo.trim();
    }

    /**
     *导游名称
     *`trade_order`.guide_name
     *
     * @return the value of `trade_order`.guide_name
     *
     * @mbggenerated
     */
    public String getGuideName() {
        return guideName;
    }

    /**
     *导游名称
     *`trade_order`.guide_name
     *
     * @param guideName the value for `trade_order`.guide_name
     *
     * @mbggenerated
     */
    public void setGuideName(String guideName) {
        this.guideName = guideName == null ? null : guideName.trim();
    }

    /**
     *导游手机区号
     *`trade_order`.guide_area_code
     *
     * @return the value of `trade_order`.guide_area_code
     *
     * @mbggenerated
     */
    public String getGuideAreaCode() {
        return guideAreaCode;
    }

    /**
     *导游手机区号
     *`trade_order`.guide_area_code
     *
     * @param guideAreaCode the value for `trade_order`.guide_area_code
     *
     * @mbggenerated
     */
    public void setGuideAreaCode(String guideAreaCode) {
        this.guideAreaCode = guideAreaCode == null ? null : guideAreaCode.trim();
    }

    /**
     *导游手机
     *`trade_order`.guide_mobile
     *
     * @return the value of `trade_order`.guide_mobile
     *
     * @mbggenerated
     */
    public String getGuideMobile() {
        return guideMobile;
    }

    /**
     *导游手机
     *`trade_order`.guide_mobile
     *
     * @param guideMobile the value for `trade_order`.guide_mobile
     *
     * @mbggenerated
     */
    public void setGuideMobile(String guideMobile) {
        this.guideMobile = guideMobile == null ? null : guideMobile.trim();
    }

    /**
     *导游评论状态 评论状态 0 未评价 1 已评价
     *`trade_order`.guide_comment_status
     *
     * @return the value of `trade_order`.guide_comment_status
     *
     * @mbggenerated
     */
    public Integer getGuideCommentStatus() {
        return guideCommentStatus;
    }

    /**
     *导游评论状态 评论状态 0 未评价 1 已评价
     *`trade_order`.guide_comment_status
     *
     * @param guideCommentStatus the value for `trade_order`.guide_comment_status
     *
     * @mbggenerated
     */
    public void setGuideCommentStatus(Integer guideCommentStatus) {
        this.guideCommentStatus = guideCommentStatus;
    }

    /**
     *接单导游类型 同guide表的agencyType 地接社员工状态：1=地接社员工；2=地接社管理员枚举；0=不属于地接社（默认）
     *`trade_order`.guide_agency_type
     *
     * @return the value of `trade_order`.guide_agency_type
     *
     * @mbggenerated
     */
    public Integer getGuideAgencyType() {
        return guideAgencyType;
    }

    /**
     *接单导游类型 同guide表的agencyType 地接社员工状态：1=地接社员工；2=地接社管理员枚举；0=不属于地接社（默认）
     *`trade_order`.guide_agency_type
     *
     * @param guideAgencyType the value for `trade_order`.guide_agency_type
     *
     * @mbggenerated
     */
    public void setGuideAgencyType(Integer guideAgencyType) {
        this.guideAgencyType = guideAgencyType;
    }

    /**
     *地接社老板ID
     *`trade_order`.guide_agency_boss_id
     *
     * @return the value of `trade_order`.guide_agency_boss_id
     *
     * @mbggenerated
     */
    public String getGuideAgencyBossId() {
        return guideAgencyBossId;
    }

    /**
     *地接社老板ID
     *`trade_order`.guide_agency_boss_id
     *
     * @param guideAgencyBossId the value for `trade_order`.guide_agency_boss_id
     *
     * @mbggenerated
     */
    public void setGuideAgencyBossId(String guideAgencyBossId) {
        this.guideAgencyBossId = guideAgencyBossId == null ? null : guideAgencyBossId.trim();
    }

    /**
     *地接社老板名称
     *`trade_order`.guide_agency_boss_name
     *
     * @return the value of `trade_order`.guide_agency_boss_name
     *
     * @mbggenerated
     */
    public String getGuideAgencyBossName() {
        return guideAgencyBossName;
    }

    /**
     *地接社老板名称
     *`trade_order`.guide_agency_boss_name
     *
     * @param guideAgencyBossName the value for `trade_order`.guide_agency_boss_name
     *
     * @mbggenerated
     */
    public void setGuideAgencyBossName(String guideAgencyBossName) {
        this.guideAgencyBossName = guideAgencyBossName == null ? null : guideAgencyBossName.trim();
    }

    /**
     *导游资金帐号no
     *`trade_order`.guide_account_no
     *
     * @return the value of `trade_order`.guide_account_no
     *
     * @mbggenerated
     */
    public String getGuideAccountNo() {
        return guideAccountNo;
    }

    /**
     *导游资金帐号no
     *`trade_order`.guide_account_no
     *
     * @param guideAccountNo the value for `trade_order`.guide_account_no
     *
     * @mbggenerated
     */
    public void setGuideAccountNo(String guideAccountNo) {
        this.guideAccountNo = guideAccountNo == null ? null : guideAccountNo.trim();
    }

    /**
     *导游地接社ID
     *`trade_order`.guide_agency_id
     *
     * @return the value of `trade_order`.guide_agency_id
     *
     * @mbggenerated
     */
    public String getGuideAgencyId() {
        return guideAgencyId;
    }

    /**
     *导游地接社ID
     *`trade_order`.guide_agency_id
     *
     * @param guideAgencyId the value for `trade_order`.guide_agency_id
     *
     * @mbggenerated
     */
    public void setGuideAgencyId(String guideAgencyId) {
        this.guideAgencyId = guideAgencyId == null ? null : guideAgencyId.trim();
    }

    /**
     *导游地接社名称
     *`trade_order`.guide_agency_name
     *
     * @return the value of `trade_order`.guide_agency_name
     *
     * @mbggenerated
     */
    public String getGuideAgencyName() {
        return guideAgencyName;
    }

    /**
     *导游地接社名称
     *`trade_order`.guide_agency_name
     *
     * @param guideAgencyName the value for `trade_order`.guide_agency_name
     *
     * @mbggenerated
     */
    public void setGuideAgencyName(String guideAgencyName) {
        this.guideAgencyName = guideAgencyName == null ? null : guideAgencyName.trim();
    }

    /**
     *gds代理商ID
     *`trade_order`.agent_id
     *
     * @return the value of `trade_order`.agent_id
     *
     * @mbggenerated
     */
    public String getAgentId() {
        return agentId;
    }

    /**
     *gds代理商ID
     *`trade_order`.agent_id
     *
     * @param agentId the value for `trade_order`.agent_id
     *
     * @mbggenerated
     */
    public void setAgentId(String agentId) {
        this.agentId = agentId == null ? null : agentId.trim();
    }

    /**
     *gds 登录用户代理商名称
     *`trade_order`.agent_name
     *
     * @return the value of `trade_order`.agent_name
     *
     * @mbggenerated
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     *gds 登录用户代理商名称
     *`trade_order`.agent_name
     *
     * @param agentName the value for `trade_order`.agent_name
     *
     * @mbggenerated
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    /**
     *gds操作员ID
     *`trade_order`.agent_opid
     *
     * @return the value of `trade_order`.agent_opid
     *
     * @mbggenerated
     */
    public String getAgentOpid() {
        return agentOpid;
    }

    /**
     *gds操作员ID
     *`trade_order`.agent_opid
     *
     * @param agentOpid the value for `trade_order`.agent_opid
     *
     * @mbggenerated
     */
    public void setAgentOpid(String agentOpid) {
        this.agentOpid = agentOpid == null ? null : agentOpid.trim();
    }

    /**
     *gds 操作员名称
     *`trade_order`.agent_opname
     *
     * @return the value of `trade_order`.agent_opname
     *
     * @mbggenerated
     */
    public String getAgentOpname() {
        return agentOpname;
    }

    /**
     *gds 操作员名称
     *`trade_order`.agent_opname
     *
     * @param agentOpname the value for `trade_order`.agent_opname
     *
     * @mbggenerated
     */
    public void setAgentOpname(String agentOpname) {
        this.agentOpname = agentOpname == null ? null : agentOpname.trim();
    }

    /**
     *MIS 管理员（客服人员）ID
     *`trade_order`.admin_id
     *
     * @return the value of `trade_order`.admin_id
     *
     * @mbggenerated
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     *MIS 管理员（客服人员）ID
     *`trade_order`.admin_id
     *
     * @param adminId the value for `trade_order`.admin_id
     *
     * @mbggenerated
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     *聊天token
     *`trade_order`.im_token
     *
     * @return the value of `trade_order`.im_token
     *
     * @mbggenerated
     */
    public String getImToken() {
        return imToken;
    }

    /**
     *聊天token
     *`trade_order`.im_token
     *
     * @param imToken the value for `trade_order`.im_token
     *
     * @mbggenerated
     */
    public void setImToken(String imToken) {
        this.imToken = imToken == null ? null : imToken.trim();
    }

    /**
     *0 初始态 1 发单中 2已接单
     *`trade_order`.deliver_status
     *
     * @return the value of `trade_order`.deliver_status
     *
     * @mbggenerated
     */
    public Integer getDeliverStatus() {
        return deliverStatus;
    }

    /**
     *0 初始态 1 发单中 2已接单
     *`trade_order`.deliver_status
     *
     * @param deliverStatus the value for `trade_order`.deliver_status
     *
     * @mbggenerated
     */
    public void setDeliverStatus(Integer deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    /**
     *1-正常发单；2-立即发单；3-增量补发；4-取消重发 5 指派导游 6 支付前预指派导游
     *`trade_order`.deliver_type
     *
     * @return the value of `trade_order`.deliver_type
     *
     * @mbggenerated
     */
    public Integer getDeliverType() {
        return deliverType;
    }

    /**
     *1-正常发单；2-立即发单；3-增量补发；4-取消重发 5 指派导游 6 支付前预指派导游
     *`trade_order`.deliver_type
     *
     * @param deliverType the value for `trade_order`.deliver_type
     *
     * @mbggenerated
     */
    public void setDeliverType(Integer deliverType) {
        this.deliverType = deliverType;
    }

    /**
     *接单时间
     *`trade_order`.deliver_acp_time
     *
     * @return the value of `trade_order`.deliver_acp_time
     *
     * @mbggenerated
     */
    public Date getDeliverAcpTime() {
        return deliverAcpTime;
    }

    /**
     *接单时间
     *`trade_order`.deliver_acp_time
     *
     * @param deliverAcpTime the value for `trade_order`.deliver_acp_time
     *
     * @mbggenerated
     */
    public void setDeliverAcpTime(Date deliverAcpTime) {
        this.deliverAcpTime = deliverAcpTime;
    }

    /**
     *0 普通订单 1急单
     *`trade_order`.urgent_flag
     *
     * @return the value of `trade_order`.urgent_flag
     *
     * @mbggenerated
     */
    public Integer getUrgentFlag() {
        return urgentFlag;
    }

    /**
     *0 普通订单 1急单
     *`trade_order`.urgent_flag
     *
     * @param urgentFlag the value for `trade_order`.urgent_flag
     *
     * @mbggenerated
     */
    public void setUrgentFlag(Integer urgentFlag) {
        this.urgentFlag = urgentFlag;
    }

    /**
     *该城市下急单 小时数
     *`trade_order`.urgent_hour
     *
     * @return the value of `trade_order`.urgent_hour
     *
     * @mbggenerated
     */
    public Integer getUrgentHour() {
        return urgentHour;
    }

    /**
     *该城市下急单 小时数
     *`trade_order`.urgent_hour
     *
     * @param urgentHour the value for `trade_order`.urgent_hour
     *
     * @mbggenerated
     */
    public void setUrgentHour(Integer urgentHour) {
        this.urgentHour = urgentHour;
    }

    /**
     *0 正常订单 1表示串单 2表示被串单
     *`trade_order`.serial_flag
     *
     * @return the value of `trade_order`.serial_flag
     *
     * @mbggenerated
     */
    public Integer getSerialFlag() {
        return serialFlag;
    }

    /**
     *0 正常订单 1表示串单 2表示被串单
     *`trade_order`.serial_flag
     *
     * @param serialFlag the value for `trade_order`.serial_flag
     *
     * @mbggenerated
     */
    public void setSerialFlag(Integer serialFlag) {
        this.serialFlag = serialFlag;
    }

    /**
     *串单关联订单号
     *`trade_order`.serial_order_no
     *
     * @return the value of `trade_order`.serial_order_no
     *
     * @mbggenerated
     */
    public String getSerialOrderNo() {
        return serialOrderNo;
    }

    /**
     *串单关联订单号
     *`trade_order`.serial_order_no
     *
     * @param serialOrderNo the value for `trade_order`.serial_order_no
     *
     * @mbggenerated
     */
    public void setSerialOrderNo(String serialOrderNo) {
        this.serialOrderNo = serialOrderNo == null ? null : serialOrderNo.trim();
    }

    /**
     *拼车标识 0 正常 1为拼车
     *`trade_order`.cargroup_flag
     *
     * @return the value of `trade_order`.cargroup_flag
     *
     * @mbggenerated
     */
    public Integer getCargroupFlag() {
        return cargroupFlag;
    }

    /**
     *拼车标识 0 正常 1为拼车
     *`trade_order`.cargroup_flag
     *
     * @param cargroupFlag the value for `trade_order`.cargroup_flag
     *
     * @mbggenerated
     */
    public void setCargroupFlag(Integer cargroupFlag) {
        this.cargroupFlag = cargroupFlag;
    }

    /**
     *拼车标识符，用于表述一组订单 为一个拼车组
     *`trade_order`.cargroup_id
     *
     * @return the value of `trade_order`.cargroup_id
     *
     * @mbggenerated
     */
    public String getCargroupId() {
        return cargroupId;
    }

    /**
     *拼车标识符，用于表述一组订单 为一个拼车组
     *`trade_order`.cargroup_id
     *
     * @param cargroupId the value for `trade_order`.cargroup_id
     *
     * @mbggenerated
     */
    public void setCargroupId(String cargroupId) {
        this.cargroupId = cargroupId == null ? null : cargroupId.trim();
    }

    /**
     *航班编号
     *`trade_order`.flight_no
     *
     * @return the value of `trade_order`.flight_no
     *
     * @mbggenerated
     */
    public String getFlightNo() {
        return flightNo;
    }

    /**
     *航班编号
     *`trade_order`.flight_no
     *
     * @param flightNo the value for `trade_order`.flight_no
     *
     * @mbggenerated
     */
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo == null ? null : flightNo.trim();
    }

    /**
     *起飞 机场3字码
     *`trade_order`.flight_airport_code
     *
     * @return the value of `trade_order`.flight_airport_code
     *
     * @mbggenerated
     */
    public String getFlightAirportCode() {
        return flightAirportCode;
    }

    /**
     *起飞 机场3字码
     *`trade_order`.flight_airport_code
     *
     * @param flightAirportCode the value for `trade_order`.flight_airport_code
     *
     * @mbggenerated
     */
    public void setFlightAirportCode(String flightAirportCode) {
        this.flightAirportCode = flightAirportCode == null ? null : flightAirportCode.trim();
    }

    /**
     *起飞机场名称
     *`trade_order`.flight_airport_name
     *
     * @return the value of `trade_order`.flight_airport_name
     *
     * @mbggenerated
     */
    public String getFlightAirportName() {
        return flightAirportName;
    }

    /**
     *起飞机场名称
     *`trade_order`.flight_airport_name
     *
     * @param flightAirportName the value for `trade_order`.flight_airport_name
     *
     * @mbggenerated
     */
    public void setFlightAirportName(String flightAirportName) {
        this.flightAirportName = flightAirportName == null ? null : flightAirportName.trim();
    }

    /**
     *航班计划起飞时间
     *`trade_order`.flight_fly_time
     *
     * @return the value of `trade_order`.flight_fly_time
     *
     * @mbggenerated
     */
    public Date getFlightFlyTime() {
        return flightFlyTime;
    }

    /**
     *航班计划起飞时间
     *`trade_order`.flight_fly_time
     *
     * @param flightFlyTime the value for `trade_order`.flight_fly_time
     *
     * @mbggenerated
     */
    public void setFlightFlyTime(Date flightFlyTime) {
        this.flightFlyTime = flightFlyTime;
    }

    /**
     *航班计划到达时间
     *`trade_order`.flight_arrive_time
     *
     * @return the value of `trade_order`.flight_arrive_time
     *
     * @mbggenerated
     */
    public Date getFlightArriveTime() {
        return flightArriveTime;
    }

    /**
     *航班计划到达时间
     *`trade_order`.flight_arrive_time
     *
     * @param flightArriveTime the value for `trade_order`.flight_arrive_time
     *
     * @mbggenerated
     */
    public void setFlightArriveTime(Date flightArriveTime) {
        this.flightArriveTime = flightArriveTime;
    }

    /**
     *航班是否为顾客自定义，0：正常，1：自定义输入
     *`trade_order`.flight_is_custom
     *
     * @return the value of `trade_order`.flight_is_custom
     *
     * @mbggenerated
     */
    public Integer getFlightIsCustom() {
        return flightIsCustom;
    }

    /**
     *航班是否为顾客自定义，0：正常，1：自定义输入
     *`trade_order`.flight_is_custom
     *
     * @param flightIsCustom the value for `trade_order`.flight_is_custom
     *
     * @mbggenerated
     */
    public void setFlightIsCustom(Integer flightIsCustom) {
        this.flightIsCustom = flightIsCustom;
    }

    /**
     *起飞机场航站楼
     *`trade_order`.flight_airport_buiding
     *
     * @return the value of `trade_order`.flight_airport_buiding
     *
     * @mbggenerated
     */
    public String getFlightAirportBuiding() {
        return flightAirportBuiding;
    }

    /**
     *起飞机场航站楼
     *`trade_order`.flight_airport_buiding
     *
     * @param flightAirportBuiding the value for `trade_order`.flight_airport_buiding
     *
     * @mbggenerated
     */
    public void setFlightAirportBuiding(String flightAirportBuiding) {
        this.flightAirportBuiding = flightAirportBuiding == null ? null : flightAirportBuiding.trim();
    }

    /**
     *非常准 航班注册ID  外键关联
     *`trade_order`.flight_register_id
     *
     * @return the value of `trade_order`.flight_register_id
     *
     * @mbggenerated
     */
    public String getFlightRegisterId() {
        return flightRegisterId;
    }

    /**
     *非常准 航班注册ID  外键关联
     *`trade_order`.flight_register_id
     *
     * @param flightRegisterId the value for `trade_order`.flight_register_id
     *
     * @mbggenerated
     */
    public void setFlightRegisterId(String flightRegisterId) {
        this.flightRegisterId = flightRegisterId == null ? null : flightRegisterId.trim();
    }

    /**
     *接机牌姓名
     *`trade_order`.flight_brand_sign
     *
     * @return the value of `trade_order`.flight_brand_sign
     *
     * @mbggenerated
     */
    public String getFlightBrandSign() {
        return flightBrandSign;
    }

    /**
     *接机牌姓名
     *`trade_order`.flight_brand_sign
     *
     * @param flightBrandSign the value for `trade_order`.flight_brand_sign
     *
     * @mbggenerated
     */
    public void setFlightBrandSign(String flightBrandSign) {
        this.flightBrandSign = flightBrandSign == null ? null : flightBrandSign.trim();
    }

    /**
     *到达机场三字码
     *`trade_order`.flight_dest_code
     *
     * @return the value of `trade_order`.flight_dest_code
     *
     * @mbggenerated
     */
    public String getFlightDestCode() {
        return flightDestCode;
    }

    /**
     *到达机场三字码
     *`trade_order`.flight_dest_code
     *
     * @param flightDestCode the value for `trade_order`.flight_dest_code
     *
     * @mbggenerated
     */
    public void setFlightDestCode(String flightDestCode) {
        this.flightDestCode = flightDestCode == null ? null : flightDestCode.trim();
    }

    /**
     *降落机场名称
     *`trade_order`.flight_dest_name
     *
     * @return the value of `trade_order`.flight_dest_name
     *
     * @mbggenerated
     */
    public String getFlightDestName() {
        return flightDestName;
    }

    /**
     *降落机场名称
     *`trade_order`.flight_dest_name
     *
     * @param flightDestName the value for `trade_order`.flight_dest_name
     *
     * @mbggenerated
     */
    public void setFlightDestName(String flightDestName) {
        this.flightDestName = flightDestName == null ? null : flightDestName.trim();
    }

    /**
     *降落机场航站楼
     *`trade_order`.flight_dest_building
     *
     * @return the value of `trade_order`.flight_dest_building
     *
     * @mbggenerated
     */
    public String getFlightDestBuilding() {
        return flightDestBuilding;
    }

    /**
     *降落机场航站楼
     *`trade_order`.flight_dest_building
     *
     * @param flightDestBuilding the value for `trade_order`.flight_dest_building
     *
     * @mbggenerated
     */
    public void setFlightDestBuilding(String flightDestBuilding) {
        this.flightDestBuilding = flightDestBuilding == null ? null : flightDestBuilding.trim();
    }

    /**
     *线路包车（精品线路）主题（标题）
     *`trade_order`.line_subject
     *
     * @return the value of `trade_order`.line_subject
     *
     * @mbggenerated
     */
    public String getLineSubject() {
        return lineSubject;
    }

    /**
     *线路包车（精品线路）主题（标题）
     *`trade_order`.line_subject
     *
     * @param lineSubject the value for `trade_order`.line_subject
     *
     * @mbggenerated
     */
    public void setLineSubject(String lineSubject) {
        this.lineSubject = lineSubject == null ? null : lineSubject.trim();
    }

    /**
     *线路包车（精品线路）描述
     *`trade_order`.line_description
     *
     * @return the value of `trade_order`.line_description
     *
     * @mbggenerated
     */
    public String getLineDescription() {
        return lineDescription;
    }

    /**
     *线路包车（精品线路）描述
     *`trade_order`.line_description
     *
     * @param lineDescription the value for `trade_order`.line_description
     *
     * @mbggenerated
     */
    public void setLineDescription(String lineDescription) {
        this.lineDescription = lineDescription == null ? null : lineDescription.trim();
    }

    /**
     *成人数
     *`trade_order`.adult_num
     *
     * @return the value of `trade_order`.adult_num
     *
     * @mbggenerated
     */
    public Integer getAdultNum() {
        return adultNum;
    }

    /**
     *成人数
     *`trade_order`.adult_num
     *
     * @param adultNum the value for `trade_order`.adult_num
     *
     * @mbggenerated
     */
    public void setAdultNum(Integer adultNum) {
        this.adultNum = adultNum;
    }

    /**
     *儿童数
     *`trade_order`.child_num
     *
     * @return the value of `trade_order`.child_num
     *
     * @mbggenerated
     */
    public Integer getChildNum() {
        return childNum;
    }

    /**
     *儿童数
     *`trade_order`.child_num
     *
     * @param childNum the value for `trade_order`.child_num
     *
     * @mbggenerated
     */
    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    /**
     *规则：年龄范围-数量。例如：1-1,2-3 附：1:婴儿座椅(0-1岁) 2:幼儿座椅(1-4岁) 3:学童座椅(4-7岁) 4:儿童座椅(7-12岁)]
     *`trade_order`.child_seat
     *
     * @return the value of `trade_order`.child_seat
     *
     * @mbggenerated
     */
    public String getChildSeat() {
        return childSeat;
    }

    /**
     *规则：年龄范围-数量。例如：1-1,2-3 附：1:婴儿座椅(0-1岁) 2:幼儿座椅(1-4岁) 3:学童座椅(4-7岁) 4:儿童座椅(7-12岁)]
     *`trade_order`.child_seat
     *
     * @param childSeat the value for `trade_order`.child_seat
     *
     * @mbggenerated
     */
    public void setChildSeat(String childSeat) {
        this.childSeat = childSeat == null ? null : childSeat.trim();
    }

    /**
     *签证类型：0未确定 1-国内签证；2-落地签证
     *`trade_order`.is_arrival_visa
     *
     * @return the value of `trade_order`.is_arrival_visa
     *
     * @mbggenerated
     */
    public Integer getIsArrivalVisa() {
        return isArrivalVisa;
    }

    /**
     *签证类型：0未确定 1-国内签证；2-落地签证
     *`trade_order`.is_arrival_visa
     *
     * @param isArrivalVisa the value for `trade_order`.is_arrival_visa
     *
     * @mbggenerated
     */
    public void setIsArrivalVisa(Integer isArrivalVisa) {
        this.isArrivalVisa = isArrivalVisa;
    }

    /**
     *办理登机费
     *`trade_order`.check_in_price
     *
     * @return the value of `trade_order`.check_in_price
     *
     * @mbggenerated
     */
    public Double getCheckInPrice() {
        return checkInPrice;
    }

    /**
     *办理登机费
     *`trade_order`.check_in_price
     *
     * @param checkInPrice the value for `trade_order`.check_in_price
     *
     * @mbggenerated
     */
    public void setCheckInPrice(Double checkInPrice) {
        this.checkInPrice = checkInPrice;
    }

    /**
     *订单取消时间
     *`trade_order`.cancel_time
     *
     * @return the value of `trade_order`.cancel_time
     *
     * @mbggenerated
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    /**
     *订单取消时间
     *`trade_order`.cancel_time
     *
     * @param cancelTime the value for `trade_order`.cancel_time
     *
     * @mbggenerated
     */
    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    /**
     *订单完成时间
     *`trade_order`.complete_time
     *
     * @return the value of `trade_order`.complete_time
     *
     * @mbggenerated
     */
    public Date getCompleteTime() {
        return completeTime;
    }

    /**
     *订单完成时间
     *`trade_order`.complete_time
     *
     * @param completeTime the value for `trade_order`.complete_time
     *
     * @mbggenerated
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    /**
     *是否有效。1：有效数据；0：无效数据
     *`trade_order`.is_readable
     *
     * @return the value of `trade_order`.is_readable
     *
     * @mbggenerated
     */
    public Integer getIsReadable() {
        return isReadable;
    }

    /**
     *是否有效。1：有效数据；0：无效数据
     *`trade_order`.is_readable
     *
     * @param isReadable the value for `trade_order`.is_readable
     *
     * @mbggenerated
     */
    public void setIsReadable(Integer isReadable) {
        this.isReadable = isReadable;
    }

    /**
     *是否有新的订单状态，0：否；1：是
     *`trade_order`.is_new_track
     *
     * @return the value of `trade_order`.is_new_track
     *
     * @mbggenerated
     */
    public Integer getIsNewTrack() {
        return isNewTrack;
    }

    /**
     *是否有新的订单状态，0：否；1：是
     *`trade_order`.is_new_track
     *
     * @param isNewTrack the value for `trade_order`.is_new_track
     *
     * @mbggenerated
     */
    public void setIsNewTrack(Integer isNewTrack) {
        this.isNewTrack = isNewTrack;
    }

    /**
     *下单时间
     *`trade_order`.create_time
     *
     * @return the value of `trade_order`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *下单时间
     *`trade_order`.create_time
     *
     * @param createTime the value for `trade_order`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     *`trade_order`.update_time
     *
     * @return the value of `trade_order`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`trade_order`.update_time
     *
     * @param updateTime the value for `trade_order`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *日租 订单行程描述，线路包车（精品线路）备注
     *`trade_order`.journey_comment
     *
     * @return the value of `trade_order`.journey_comment
     *
     * @mbggenerated
     */
    public String getJourneyComment() {
        return journeyComment;
    }

    /**
     *日租 订单行程描述，线路包车（精品线路）备注
     *`trade_order`.journey_comment
     *
     * @param journeyComment the value for `trade_order`.journey_comment
     *
     * @mbggenerated
     */
    public void setJourneyComment(String journeyComment) {
        this.journeyComment = journeyComment == null ? null : journeyComment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_order`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderNo=").append(orderNo);
        sb.append(", thirdTradeNo=").append(thirdTradeNo);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", orderStatusName=").append(orderStatusName);
        sb.append(", orderType=").append(orderType);
        sb.append(", orderGoodsType=").append(orderGoodsType);
        sb.append(", goodNo=").append(goodNo);
        sb.append(", orderSource=").append(orderSource);
        sb.append(", orderChannel=").append(orderChannel);
        sb.append(", serviceTime=").append(serviceTime);
        sb.append(", serviceEndTime=").append(serviceEndTime);
        sb.append(", serviceContinentId=").append(serviceContinentId);
        sb.append(", serviceContinentName=").append(serviceContinentName);
        sb.append(", serviceCountryId=").append(serviceCountryId);
        sb.append(", serviceCountryName=").append(serviceCountryName);
        sb.append(", serviceCityId=").append(serviceCityId);
        sb.append(", serviceCitySpell=").append(serviceCitySpell);
        sb.append(", serviceCityEnname=").append(serviceCityEnname);
        sb.append(", serviceCityName=").append(serviceCityName);
        sb.append(", serviceAreaCode=").append(serviceAreaCode);
        sb.append(", serviceAddressTel=").append(serviceAddressTel);
        sb.append(", serviceDailyType=").append(serviceDailyType);
        sb.append(", serviceLocalDays=").append(serviceLocalDays);
        sb.append(", serviceNonlocalDays=").append(serviceNonlocalDays);
        sb.append(", totalDays=").append(totalDays);
        sb.append(", servicePassCity=").append(servicePassCity);
        sb.append(", serviceEndCityid=").append(serviceEndCityid);
        sb.append(", serviceEndCityname=").append(serviceEndCityname);
        sb.append(", expectedCompTime=").append(expectedCompTime);
        sb.append(", startAddress=").append(startAddress);
        sb.append(", startAddressDetail=").append(startAddressDetail);
        sb.append(", startAddressPoi=").append(startAddressPoi);
        sb.append(", destAddress=").append(destAddress);
        sb.append(", destAddressDetail=").append(destAddressDetail);
        sb.append(", destAddressPoi=").append(destAddressPoi);
        sb.append(", distance=").append(distance);
        sb.append(", carId=").append(carId);
        sb.append(", carLicenseNum=").append(carLicenseNum);
        sb.append(", carTypeId=").append(carTypeId);
        sb.append(", carSeatNum=").append(carSeatNum);
        sb.append(", carName=").append(carName);
        sb.append(", carDesc=").append(carDesc);
        sb.append(", coupId=").append(coupId);
        sb.append(", coupType=").append(coupType);
        sb.append(", coupLabel=").append(coupLabel);
        sb.append(", coupPriceInfo=").append(coupPriceInfo);
        sb.append(", priceMark=").append(priceMark);
        sb.append(", priceBase=").append(priceBase);
        sb.append(", priceGuide=").append(priceGuide);
        sb.append(", priceTicket=").append(priceTicket);
        sb.append(", priceChannel=").append(priceChannel);
        sb.append(", priceGuideBase=").append(priceGuideBase);
        sb.append(", priceReward=").append(priceReward);
        sb.append(", userId=").append(userId);
        sb.append(", userAccount=").append(userAccount);
        sb.append(", userName=").append(userName);
        sb.append(", userAreaCode1=").append(userAreaCode1);
        sb.append(", userMobile1=").append(userMobile1);
        sb.append(", userAreaCode2=").append(userAreaCode2);
        sb.append(", userMobile2=").append(userMobile2);
        sb.append(", userAreaCode3=").append(userAreaCode3);
        sb.append(", userMobile3=").append(userMobile3);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", userCommentStatus=").append(userCommentStatus);
        sb.append(", userRemark=").append(userRemark);
        sb.append(", systemCommentStatus=").append(systemCommentStatus);
        sb.append(", guideAssignTime=").append(guideAssignTime);
        sb.append(", guidePreId=").append(guidePreId);
        sb.append(", guideId=").append(guideId);
        sb.append(", guideNo=").append(guideNo);
        sb.append(", guideName=").append(guideName);
        sb.append(", guideAreaCode=").append(guideAreaCode);
        sb.append(", guideMobile=").append(guideMobile);
        sb.append(", guideCommentStatus=").append(guideCommentStatus);
        sb.append(", guideAgencyType=").append(guideAgencyType);
        sb.append(", guideAgencyBossId=").append(guideAgencyBossId);
        sb.append(", guideAgencyBossName=").append(guideAgencyBossName);
        sb.append(", guideAccountNo=").append(guideAccountNo);
        sb.append(", guideAgencyId=").append(guideAgencyId);
        sb.append(", guideAgencyName=").append(guideAgencyName);
        sb.append(", agentId=").append(agentId);
        sb.append(", agentName=").append(agentName);
        sb.append(", agentOpid=").append(agentOpid);
        sb.append(", agentOpname=").append(agentOpname);
        sb.append(", adminId=").append(adminId);
        sb.append(", imToken=").append(imToken);
        sb.append(", deliverStatus=").append(deliverStatus);
        sb.append(", deliverType=").append(deliverType);
        sb.append(", deliverAcpTime=").append(deliverAcpTime);
        sb.append(", urgentFlag=").append(urgentFlag);
        sb.append(", urgentHour=").append(urgentHour);
        sb.append(", serialFlag=").append(serialFlag);
        sb.append(", serialOrderNo=").append(serialOrderNo);
        sb.append(", cargroupFlag=").append(cargroupFlag);
        sb.append(", cargroupId=").append(cargroupId);
        sb.append(", flightNo=").append(flightNo);
        sb.append(", flightAirportCode=").append(flightAirportCode);
        sb.append(", flightAirportName=").append(flightAirportName);
        sb.append(", flightFlyTime=").append(flightFlyTime);
        sb.append(", flightArriveTime=").append(flightArriveTime);
        sb.append(", flightIsCustom=").append(flightIsCustom);
        sb.append(", flightAirportBuiding=").append(flightAirportBuiding);
        sb.append(", flightRegisterId=").append(flightRegisterId);
        sb.append(", flightBrandSign=").append(flightBrandSign);
        sb.append(", flightDestCode=").append(flightDestCode);
        sb.append(", flightDestName=").append(flightDestName);
        sb.append(", flightDestBuilding=").append(flightDestBuilding);
        sb.append(", lineSubject=").append(lineSubject);
        sb.append(", lineDescription=").append(lineDescription);
        sb.append(", adultNum=").append(adultNum);
        sb.append(", childNum=").append(childNum);
        sb.append(", childSeat=").append(childSeat);
        sb.append(", isArrivalVisa=").append(isArrivalVisa);
        sb.append(", checkInPrice=").append(checkInPrice);
        sb.append(", cancelTime=").append(cancelTime);
        sb.append(", completeTime=").append(completeTime);
        sb.append(", isReadable=").append(isReadable);
        sb.append(", isNewTrack=").append(isNewTrack);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", journeyComment=").append(journeyComment);
        sb.append("]");
        return sb.toString();
    }
}