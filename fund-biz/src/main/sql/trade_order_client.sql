/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50171
Source Host           : localhost:3306
Source Database       : trade

Target Server Type    : MYSQL
Target Server Version : 50171
File Encoding         : 65001

Date: 2015-10-03 18:16:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `trade_order_client`
-- ----------------------------
DROP TABLE IF EXISTS `trade_order_client`;
CREATE TABLE `trade_order_client` (
  `order_no` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `order_status` int(11) DEFAULT NULL,
  `order_type` int(11) DEFAULT NULL COMMENT '订单类型。1-接机；2-送机；3-日租；4-次租',
  `biz_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '1 串单 2拼车',
  `service_time` timestamp NULL DEFAULT NULL COMMENT '服务时间',
  `service_continent_id` int(11) DEFAULT NULL COMMENT '大洲ID',
  `service_continent_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '大洲',
  `service_country_id` int(11) DEFAULT NULL,
  `service_country_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `service_city_id` int(11) DEFAULT NULL,
  `service_city_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `service_area_code` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `service_address_tel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '目的地酒店或者区域电话号码',
  `start_address` text COLLATE utf8_unicode_ci COMMENT '接送机：出发地（冗余）机场名+航站楼',
  `start_address_detail` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `start_address_poi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出发地经纬度',
  `dest__address` text COLLATE utf8_unicode_ci,
  `dest_address_detail` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dest_address_poi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '到达地经纬度',
  `distance` decimal(5,1) DEFAULT NULL,
  `car_license_num` int(11) DEFAULT NULL COMMENT '车牌号',
  `car_type_id` int(11) DEFAULT NULL COMMENT '经济 舒适 豪华 奢华',
  `car_seat_num` tinyint(4) DEFAULT NULL COMMENT '车座数',
  `car_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '经济型5座车系',
  `car_desc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '现代圣达菲,起亚K5,雪佛兰迈锐宝',
  `price_base` double(11,2) DEFAULT NULL,
  `price_guide` double(11,2) DEFAULT NULL COMMENT '导游价',
  `price_sale` double(11,2) DEFAULT NULL COMMENT '渠道销售价',
  `price_ticket` double(11,2) DEFAULT NULL COMMENT '票面价',
  `guide_cuse_id` int(11) DEFAULT NULL COMMENT '对于日租业务C端提交订单时所选择的导游ID',
  `guide_pre_id` int(11) DEFAULT NULL COMMENT 'mis指定',
  `guide_id` int(11) DEFAULT NULL,
  `guide_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '导游名称',
  `guide_mobile` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '导游手机',
  `guid_comment_status` smallint(2) DEFAULT NULL COMMENT '评论状态',
  `guid_agency_type` int(11) DEFAULT '0' COMMENT '?接单导游类型 同guide表的agencyType 地接社员工状态：1=地接社员工；2=地接社管理员枚举；0=不属于地接社（默认）',
  `guide_deal_time` timestamp NULL DEFAULT NULL COMMENT '接单时间',
  `admin_id` int(11) DEFAULT NULL COMMENT '管理员（客服人员）ID',
  `mark_status` int(11) DEFAULT NULL COMMENT '跟踪 只有数据库存在',
  `delivered_priority` int(11) DEFAULT NULL COMMENT '已发导游的优先级 默认为1（对应guideGrade表中的dispatchPrority）',
  `re_deliver_type` int(11) DEFAULT NULL COMMENT '重新发单类型：1-导游取消发单；2-系统自动重新发单',
  `re_deliver_count` int(11) DEFAULT NULL COMMENT '订单重发次数：默认为0',
  `deliver_time` timestamp NULL DEFAULT NULL COMMENT '发单时间',
  `imToken` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `serial_type` int(11) DEFAULT NULL COMMENT '串单类型； 0=正常订单；1=串单；2=被串单；',
  `serial_order_id` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '串单关联ID',
  `group_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '组号 拼车时使用',
  `flight_no` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '航班编号',
  `flight_airport_code` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '机场3字码',
  `flight_airport_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '机场名称',
  `flight_fly_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '航班计划起飞时间',
  `flight_arrive_time` timestamp NULL DEFAULT NULL COMMENT '航班计划到达时间',
  `flight_is_custom` smallint(6) DEFAULT NULL COMMENT '航班是否为顾客自定义',
  `flight_airport_buiding` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '航站楼',
  `flight_register_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '非常准机场ID ?',
  `flight_brand_sign` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '接机牌姓名',
  `agent_id` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户ID。clienttype=1时为agentuserid；clienttype=2时为userpassportid',
  `agent_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'gds 登录用户代理商名称',
  `agent_opid` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `agent_opname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户ID',
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_mobile1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_mobile2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_mobile3` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_comment_status` int(11) DEFAULT NULL,
  `user_remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户备注',
  `user_contract_type` smallint(4) DEFAULT NULL COMMENT '用户接受确认函方式。1-短信；2-邮件；3-微信；逗号分隔存储',
  `user_contract_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '短信；2-邮件；3-微信；逗号分隔存储',
  `adult_num` int(11) DEFAULT NULL,
  `child_num` int(11) DEFAULT NULL COMMENT '儿童数',
  `child_seat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'child为0，无此字段。1-婴儿（0-1岁）；2-幼儿（1-4岁）；3-学童（4-7岁）；4-儿童（7-12岁）；0-未确定。',
  `is_arrival_visa` smallint(6) DEFAULT NULL COMMENT '是否为落地签证；0-否；1-是',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_no`),
  KEY `admin_id` (`admin_id`) USING HASH,
  KEY `guide_id` (`guide_id`) USING HASH,
  KEY `user_id` (`agent_id`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='交易C GDS 初创订单表';

-- ----------------------------
-- Records of trade_order_client
-- ----------------------------
