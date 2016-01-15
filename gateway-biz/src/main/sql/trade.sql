/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 60011
Source Host           : localhost:3306
Source Database       : trade

Target Server Type    : MYSQL
Target Server Version : 60011
File Encoding         : 65001

Date: 2015-10-13 21:06:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `agent_account`
-- ----------------------------
DROP TABLE IF EXISTS `agent_account`;
CREATE TABLE `agent_account` (
  `agentId` int(11) NOT NULL DEFAULT '0',
  `total` int(11) DEFAULT NULL,
  `currentPrice` int(11) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`agentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of agent_account
-- ----------------------------

-- ----------------------------
-- Table structure for `agent_account_stream`
-- ----------------------------
DROP TABLE IF EXISTS `agent_account_stream`;
CREATE TABLE `agent_account_stream` (
  `agentAccountLogId` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `agentId` int(11) DEFAULT NULL,
  `agentUserId` int(11) DEFAULT NULL,
  `agentUserName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderId` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `bizType` int(11) DEFAULT NULL,
  `payType` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `content` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `comment` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tips` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bizStatus` int(11) DEFAULT NULL,
  `bizComment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`agentAccountLogId`),
  KEY `R_52` (`agentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of agent_account_stream
-- ----------------------------

-- ----------------------------
-- Table structure for `agent_company`
-- ----------------------------
DROP TABLE IF EXISTS `agent_company`;
CREATE TABLE `agent_company` (
  `agentId` int(11) NOT NULL AUTO_INCREMENT,
  `agentName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `agentAdminId` char(18) COLLATE utf8_unicode_ci DEFAULT NULL,
  `agentKey` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `token` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `epwd` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province` int(11) DEFAULT NULL,
  `city` int(11) DEFAULT NULL,
  `county` int(11) DEFAULT NULL,
  `industry` tinyint(4) DEFAULT NULL,
  `bizType` int(11) DEFAULT NULL,
  `address` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tel` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contactName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contactMobile` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `comment` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`agentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of agent_company
-- ----------------------------

-- ----------------------------
-- Table structure for `agent_user`
-- ----------------------------
DROP TABLE IF EXISTS `agent_user`;
CREATE TABLE `agent_user` (
  `agentUserId` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL,
  `roleName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `agentId` int(11) DEFAULT NULL,
  `loginName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `encryptedPwd` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `expireTime` date DEFAULT NULL,
  `quota` int(11) DEFAULT NULL,
  `loginNum` int(11) DEFAULT NULL,
  `orderNum` int(11) DEFAULT NULL,
  `lastLogin` datetime DEFAULT NULL,
  `totalPrice` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`agentUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of agent_user
-- ----------------------------

-- ----------------------------
-- Table structure for `bdata_airport`
-- ----------------------------
DROP TABLE IF EXISTS `bdata_airport`;
CREATE TABLE `bdata_airport` (
  `airportId` int(11) NOT NULL AUTO_INCREMENT,
  `placeCityId` int(11) DEFAULT NULL,
  `cityName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `placeName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `code` char(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isView` int(11) DEFAULT NULL,
  `location` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`airportId`),
  KEY `R_47` (`placeCityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of bdata_airport
-- ----------------------------

-- ----------------------------
-- Table structure for `bdata_car`
-- ----------------------------
DROP TABLE IF EXISTS `bdata_car`;
CREATE TABLE `bdata_car` (
  `carId` int(11) NOT NULL AUTO_INCREMENT,
  `carBrandId` int(11) DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `spell` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `alias` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `seatCategory` tinyint(4) DEFAULT NULL,
  `seatNum` tinyint(4) DEFAULT NULL,
  `guestNum` tinyint(4) DEFAULT NULL,
  `luggageNum` tinyint(4) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `carBizType` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`carId`),
  KEY `R_9` (`carBrandId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of bdata_car
-- ----------------------------

-- ----------------------------
-- Table structure for `bdata_carbrand`
-- ----------------------------
DROP TABLE IF EXISTS `bdata_carbrand`;
CREATE TABLE `bdata_carbrand` (
  `carBrandId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `spell` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`carBrandId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of bdata_carbrand
-- ----------------------------

-- ----------------------------
-- Table structure for `bdata_exchangerate`
-- ----------------------------
DROP TABLE IF EXISTS `bdata_exchangerate`;
CREATE TABLE `bdata_exchangerate` (
  `exchangeRateId` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `placeId` int(11) DEFAULT NULL,
  `placeName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `currency` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `key` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rate` float DEFAULT NULL,
  `date` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ask` float DEFAULT NULL,
  `bid` float DEFAULT NULL,
  `opUserType` int(11) DEFAULT NULL,
  `opUserId` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`exchangeRateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of bdata_exchangerate
-- ----------------------------

-- ----------------------------
-- Table structure for `bdata_lang`
-- ----------------------------
DROP TABLE IF EXISTS `bdata_lang`;
CREATE TABLE `bdata_lang` (
  `langCode` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cnName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`langCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of bdata_lang
-- ----------------------------

-- ----------------------------
-- Table structure for `bdata_place`
-- ----------------------------
DROP TABLE IF EXISTS `bdata_place`;
CREATE TABLE `bdata_place` (
  `placeId` int(11) NOT NULL AUTO_INCREMENT,
  `placeCategoryId` int(11) DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `spell` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `areaCode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderId` int(11) DEFAULT NULL,
  `code` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`placeId`),
  KEY `R_2` (`placeCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of bdata_place
-- ----------------------------

-- ----------------------------
-- Table structure for `bdata_placecategory`
-- ----------------------------
DROP TABLE IF EXISTS `bdata_placecategory`;
CREATE TABLE `bdata_placecategory` (
  `placeCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderId` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`placeCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of bdata_placecategory
-- ----------------------------

-- ----------------------------
-- Table structure for `bdata_placecity`
-- ----------------------------
DROP TABLE IF EXISTS `bdata_placecity`;
CREATE TABLE `bdata_placecity` (
  `placeCityId` int(11) NOT NULL AUTO_INCREMENT,
  `placeCategoryId` int(11) DEFAULT NULL,
  `placeId` int(11) DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `spell` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `code` char(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `timezone` float DEFAULT NULL,
  `location` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isHot` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`placeCityId`),
  KEY `R_3` (`placeId`),
  KEY `R_4` (`placeCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of bdata_placecity
-- ----------------------------

-- ----------------------------
-- Table structure for `coupon`
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `couponId` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `couponBatchId` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `package_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '该优惠券所属礼包id',
  `couponCode` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `order_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '对应订单id',
  `couponPrice` int(11) NOT NULL DEFAULT '0',
  `couponDiscount` int(11) NOT NULL DEFAULT '0',
  `password` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `status` int(11) NOT NULL DEFAULT '0',
  `bindUserType` int(11) NOT NULL DEFAULT '1',
  `bindUserId` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `bindDateTime` int(11) NOT NULL DEFAULT '0',
  `useUserId` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `useDateTIme` int(11) NOT NULL DEFAULT '0',
  `couponType` int(11) NOT NULL DEFAULT '0',
  `transferCount` int(11) NOT NULL DEFAULT '0',
  `fromUserType` int(11) NOT NULL DEFAULT '0',
  `fromUserId` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `vendorId` varchar(20) COLLATE utf8_unicode_ci DEFAULT '',
  `vendorType` int(11) DEFAULT '0',
  `updated_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`couponId`),
  KEY `idx_status` (`status`),
  KEY `idx_code` (`couponCode`),
  KEY `idx_userId` (`bindUserType`,`bindUserId`),
  KEY `idx_type` (`couponType`),
  KEY `R_108` (`couponBatchId`),
  KEY `idx_package_id` (`package_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of coupon
-- ----------------------------

-- ----------------------------
-- Table structure for `coupon_batch`
-- ----------------------------
DROP TABLE IF EXISTS `coupon_batch`;
CREATE TABLE `coupon_batch` (
  `couponBatchId` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `batchNo` char(14) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `batchName` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `batchChannel` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `couponType` int(11) NOT NULL DEFAULT '0',
  `couponTotal` int(11) NOT NULL DEFAULT '0',
  `couponPrice` int(11) NOT NULL DEFAULT '0',
  `couponDiscount` int(11) NOT NULL DEFAULT '0',
  `days` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '包车折扣券天数限制',
  `endDate` int(11) NOT NULL DEFAULT '0',
  `meetPrice` int(11) NOT NULL DEFAULT '0',
  `distanceUpperLimit` int(11) NOT NULL DEFAULT '0',
  `durationUpperLimit` int(11) NOT NULL DEFAULT '0',
  `remark` varchar(512) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `couponPic` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `couponUrl` varchar(512) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `bindCount` int(11) NOT NULL DEFAULT '0',
  `useCount` int(11) NOT NULL DEFAULT '0',
  `allowTransferCount` int(11) NOT NULL DEFAULT '0',
  `restrictRemark` varchar(1024) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `updated_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`couponBatchId`),
  UNIQUE KEY `idx_batch_no` (`batchNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of coupon_batch
-- ----------------------------

-- ----------------------------
-- Table structure for `coupon_car`
-- ----------------------------
DROP TABLE IF EXISTS `coupon_car`;
CREATE TABLE `coupon_car` (
  `couponCarId` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `couponBatchId` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `carType` int(11) NOT NULL DEFAULT '0',
  `updated_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`couponCarId`),
  KEY `idx_batchId` (`couponBatchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of coupon_car
-- ----------------------------

-- ----------------------------
-- Table structure for `coupon_crop`
-- ----------------------------
DROP TABLE IF EXISTS `coupon_crop`;
CREATE TABLE `coupon_crop` (
  `couponCropId` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `couponBatchId` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `cropType` int(11) NOT NULL DEFAULT '0',
  `updated_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`couponCropId`),
  KEY `idx_batchId` (`couponBatchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of coupon_crop
-- ----------------------------

-- ----------------------------
-- Table structure for `coupon_distirct`
-- ----------------------------
DROP TABLE IF EXISTS `coupon_distirct`;
CREATE TABLE `coupon_distirct` (
  `couponDistirctId` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `couponBatchId` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `districtType` int(11) NOT NULL DEFAULT '0',
  `districtId` int(11) NOT NULL DEFAULT '0',
  `districtName` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `updated_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`couponDistirctId`),
  KEY `idx_batchId` (`couponBatchId`),
  KEY `idx_districtName` (`districtName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of coupon_distirct
-- ----------------------------

-- ----------------------------
-- Table structure for `coupon_package`
-- ----------------------------
DROP TABLE IF EXISTS `coupon_package`;
CREATE TABLE `coupon_package` (
  `package_id` varchar(20) NOT NULL DEFAULT '' COMMENT '礼包id',
  `package_name` varchar(50) NOT NULL DEFAULT '' COMMENT '礼包名称',
  `batch_no` varchar(20) NOT NULL DEFAULT '' COMMENT '批次号',
  `bind_user_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '绑定用户类型',
  `bind_user_id` varchar(20) NOT NULL DEFAULT '' COMMENT '绑定用户id',
  `bind_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '绑定时间',
  `package_total` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '礼包总数',
  `use_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '已使用数',
  `freed_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '释放数',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '礼包状态: 0默认,1已绑定,99已废弃',
  `remarks` varchar(1024) NOT NULL DEFAULT '' COMMENT '备注',
  `rule` text NOT NULL COMMENT '礼包规则',
  `created_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`package_id`),
  KEY `idx_status` (`status`),
  KEY `idx_batch_no` (`batch_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券礼包表';

-- ----------------------------
-- Records of coupon_package
-- ----------------------------

-- ----------------------------
-- Table structure for `fund_account`
-- ----------------------------
DROP TABLE IF EXISTS `fund_account`;
CREATE TABLE `fund_account` (
  `account_id` varchar(60) NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fund_account
-- ----------------------------

-- ----------------------------
-- Table structure for `poi_category`
-- ----------------------------
DROP TABLE IF EXISTS `poi_category`;
CREATE TABLE `poi_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `poi_type` smallint(6) NOT NULL COMMENT 'poi类型 0酒店 1美食 2购物 3景点 4交通 5娱乐',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '分类名称',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `index_category_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='POI分类';

-- ----------------------------
-- Records of poi_category
-- ----------------------------

-- ----------------------------
-- Table structure for `poi_item`
-- ----------------------------
DROP TABLE IF EXISTS `poi_item`;
CREATE TABLE `poi_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 poiId',
  `type` smallint(6) NOT NULL COMMENT 'poi类型 0酒店 1美食 2购物 3景点 4交通 5娱乐',
  `category_id` int(11) NOT NULL COMMENT '分类id',
  `continent_id` smallint(6) NOT NULL COMMENT '大洲id',
  `continent_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '大洲名',
  `place_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '国家/地区id',
  `place_name` varchar(80) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '国家/地区名',
  `city_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '城市id',
  `city_name` varchar(80) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '城市名',
  `plaza` varchar(100) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '广场/商圈',
  `name_cn` varchar(120) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '中文名',
  `name_en` varchar(120) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '英文名',
  `name_local` varchar(120) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '本地名',
  `address_cn` varchar(200) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '中文地址',
  `address_en` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '英文地址',
  `address_local` varchar(200) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '本地地址',
  `area_code` varchar(20) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '区号 酒店类型有效',
  `tel` varchar(200) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '电话信息 酒店类型有效',
  `website` varchar(200) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '网址',
  `lng` double(20,17) NOT NULL COMMENT '谷歌坐标经度',
  `lat` double(20,17) NOT NULL COMMENT '谷歌坐标纬度',
  `level` smallint(6) NOT NULL DEFAULT '0' COMMENT '酒店星级1-5级 默认0，只对酒店有效，非酒店类填-1001',
  `caption` varchar(150) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '一句话简介',
  `description` varchar(1024) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '描述',
  `label` varchar(200) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '标签',
  `ticket_info` varchar(300) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '门票信息，景点/娱乐类型有效',
  `opentime_info` varchar(300) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '开发时间，景点/娱乐类型有效',
  `pics` varchar(2048) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '图片 json格式',
  `hot_level` smallint(6) NOT NULL DEFAULT '0' COMMENT '热度 1-5',
  `last_editor` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '最后一次修改人',
  `status` smallint(6) NOT NULL COMMENT '-1删除 0未验证 1已验证',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='POI条目';

-- ----------------------------
-- Records of poi_item
-- ----------------------------

-- ----------------------------
-- Table structure for `poi_solr_upinfo`
-- ----------------------------
DROP TABLE IF EXISTS `poi_solr_upinfo`;
CREATE TABLE `poi_solr_upinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tablename` varchar(1024) DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of poi_solr_upinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `price_channel_air`
-- ----------------------------
DROP TABLE IF EXISTS `price_channel_air`;
CREATE TABLE `price_channel_air` (
  `pkid` int(11) NOT NULL AUTO_INCREMENT,
  `chanel_id` int(11) DEFAULT NULL,
  `place_id` int(11) DEFAULT NULL,
  `place_city_id` int(11) DEFAULT NULL,
  `place_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `airport_id` int(11) DEFAULT NULL,
  `airport_code` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `airport_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `seat_category` int(11) DEFAULT NULL,
  `normal_price` int(11) DEFAULT NULL,
  `promotion_price` int(11) DEFAULT NULL,
  `lowPrice` int(11) DEFAULT NULL,
  `topPrice` int(11) DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `maxDistance` int(11) DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `created_time` timestamp NULL DEFAULT NULL,
  `syc_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of price_channel_air
-- ----------------------------

-- ----------------------------
-- Table structure for `price_channel_city`
-- ----------------------------
DROP TABLE IF EXISTS `price_channel_city`;
CREATE TABLE `price_channel_city` (
  `pkid` int(11) NOT NULL AUTO_INCREMENT,
  `chanel_id` int(11) DEFAULT NULL,
  `place_id` int(11) DEFAULT NULL,
  `place_city_id` int(11) DEFAULT NULL,
  `place_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `seat_category` int(11) DEFAULT NULL,
  `normal_price` int(11) DEFAULT NULL,
  `promotion_price` int(11) DEFAULT NULL,
  `lowPrice` int(11) DEFAULT NULL,
  `topPrice` int(11) DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `maxDistance` int(11) DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `created_time` timestamp NULL DEFAULT NULL,
  `syc_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of price_channel_city
-- ----------------------------

-- ----------------------------
-- Table structure for `price_daily_rule`
-- ----------------------------
DROP TABLE IF EXISTS `price_daily_rule`;
CREATE TABLE `price_daily_rule` (
  `pkid` int(11) NOT NULL AUTO_INCREMENT,
  `place_city_id` int(11) DEFAULT NULL,
  `place_id` int(11) DEFAULT NULL,
  `place_category_id` int(11) DEFAULT NULL,
  `city_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_spell` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_en_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_spell` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_en_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_category_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `lat` double DEFAULT NULL COMMENT '纬度',
  `lng` double DEFAULT NULL COMMENT '经度',
  `t1_s5` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t1_s7` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t1_s9` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t1_s12` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s5` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s7` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s9` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s12` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s5` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s7` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s9` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s12` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s5` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s7` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s9` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s12` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `stay_charge` float(11,0) DEFAULT NULL COMMENT '住宿补助',
  `service_hour` int(11) DEFAULT NULL COMMENT '包含服务小时',
  `city_km` float(11,0) DEFAULT NULL COMMENT '市内包含公里数',
  `out_city_km` float DEFAULT NULL COMMENT '市外包含公里数',
  `over_time_charge` float DEFAULT NULL COMMENT '超时费',
  `over_km_charge` float DEFAULT NULL COMMENT '超公里费',
  `is_emptydrive_km` smallint(6) DEFAULT NULL COMMENT '是否包含空驶费 1包含2为不包含',
  `empty_drive_km1` float DEFAULT NULL,
  `empty_drive_km2` float DEFAULT NULL,
  `empty_drive_km3` float DEFAULT NULL,
  `prc_guid_charge` float DEFAULT NULL COMMENT '中文导游费用',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL,
  `syc_time` timestamp NULL DEFAULT NULL,
  `models` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '1. 可用 2.不可用',
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日租规则表';

-- ----------------------------
-- Records of price_daily_rule
-- ----------------------------

-- ----------------------------
-- Table structure for `price_edit_log`
-- ----------------------------
DROP TABLE IF EXISTS `price_edit_log`;
CREATE TABLE `price_edit_log` (
  `pkid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `did` varchar(255) DEFAULT NULL,
  `source_record` text,
  `target_record` text,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of price_edit_log
-- ----------------------------

-- ----------------------------
-- Table structure for `price_float`
-- ----------------------------
DROP TABLE IF EXISTS `price_float`;
CREATE TABLE `price_float` (
  `pkid` int(11) NOT NULL AUTO_INCREMENT,
  `rule_id` int(11) NOT NULL COMMENT '浮动 规则id',
  `place_id` int(11) DEFAULT NULL,
  `place_city_id` int(11) DEFAULT NULL,
  `float_type` int(11) DEFAULT NULL COMMENT '1接送机 2次租  3.poi 4.日租',
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `float_formula` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '价格浮动公式 B*120+50',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `created_time` timestamp NULL DEFAULT NULL,
  `syc_time` timestamp NULL DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL COMMENT '1表示有效 2表示删除',
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of price_float
-- ----------------------------

-- ----------------------------
-- Table structure for `price_peruse_rule`
-- ----------------------------
DROP TABLE IF EXISTS `price_peruse_rule`;
CREATE TABLE `price_peruse_rule` (
  `pkid` int(11) NOT NULL AUTO_INCREMENT,
  `place_city_id` int(11) DEFAULT NULL,
  `place_id` int(11) DEFAULT NULL,
  `place_category_id` int(11) DEFAULT NULL,
  `city_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_spell` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_en_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_spell` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_en_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_category_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `lat` double DEFAULT NULL COMMENT '纬度',
  `lng` double DEFAULT NULL COMMENT '经度',
  `t1_s5` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t1_s7` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t1_s9` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t1_s12` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s5` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s7` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s9` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s12` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s5` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s7` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s9` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s12` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s5` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s7` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s9` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s12` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `max_km` float(11,0) DEFAULT NULL,
  `service_charge` float DEFAULT NULL COMMENT '服务费 包含过路费等',
  `park_price` float(11,0) DEFAULT NULL COMMENT '停车费',
  `night_rate` varchar(1024) DEFAULT NULL COMMENT '夜间费率',
  `night_start_time` varchar(255) DEFAULT NULL,
  `night_end_time` varchar(255) DEFAULT NULL,
  `models` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `syc_time` timestamp NULL DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '1. 开通 2.未开通',
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='次租规则表';

-- ----------------------------
-- Records of price_peruse_rule
-- ----------------------------

-- ----------------------------
-- Table structure for `price_poi_rule`
-- ----------------------------
DROP TABLE IF EXISTS `price_poi_rule`;
CREATE TABLE `price_poi_rule` (
  `pkid` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '1接机 2为送机',
  `start_poi` varchar(255) DEFAULT NULL COMMENT '机场ID,用于接送机',
  `start_poi_radius` float DEFAULT NULL COMMENT '起点半径',
  `end_poi` varchar(255) DEFAULT NULL,
  `end_poi_radius` float DEFAULT NULL,
  `place_city_id` int(11) DEFAULT NULL,
  `place_id` int(11) DEFAULT NULL,
  `place_category_id` int(11) DEFAULT NULL,
  `city_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_spell` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_en_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_spell` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_en_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_category_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t1_s5` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t1_s7` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t1_s9` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t1_s12` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s5` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s7` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s9` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s12` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s5` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s7` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s9` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s12` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s5` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s7` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s9` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s12` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `max_km` float(11,0) DEFAULT NULL,
  `service_charge` float DEFAULT NULL COMMENT '服务费 包含过路费等',
  `park_price` float(11,0) DEFAULT NULL COMMENT '停车费',
  `night_rate` varchar(0) DEFAULT NULL COMMENT '夜间费率 计算公式 （B+100）*120%',
  `night_start_time` timestamp NULL DEFAULT NULL,
  `night_end_time` timestamp NULL DEFAULT NULL,
  `models` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of price_poi_rule
-- ----------------------------

-- ----------------------------
-- Table structure for `price_sale_formula`
-- ----------------------------
DROP TABLE IF EXISTS `price_sale_formula`;
CREATE TABLE `price_sale_formula` (
  `pkid` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL COMMENT '渠道 接送机 日租 次租',
  `guid_price_formula` varchar(255) DEFAULT NULL COMMENT '导游价计算公式',
  `sale_price_formula` varchar(255) DEFAULT NULL COMMENT '渠道销售价计算公式',
  `par_formula` varchar(255) DEFAULT NULL COMMENT '票面价计算公式',
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of price_sale_formula
-- ----------------------------

-- ----------------------------
-- Table structure for `price_shuttle_rule`
-- ----------------------------
DROP TABLE IF EXISTS `price_shuttle_rule`;
CREATE TABLE `price_shuttle_rule` (
  `pkid` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '1接机 2为送机',
  `airport_id` int(11) DEFAULT NULL COMMENT '机场ID,用于接送机',
  `airport_name` varchar(120) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  `lat` double DEFAULT NULL COMMENT '纬度',
  `lng` double DEFAULT NULL COMMENT '经度',
  `place_city_id` int(11) DEFAULT NULL COMMENT '机场三字码',
  `place_id` int(11) DEFAULT NULL,
  `place_category_id` int(11) DEFAULT NULL,
  `city_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_spell` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_en_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_spell` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_en_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `place_category_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t1_s5` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t1_s7` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t1_s9` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t1_s12` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s5` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s7` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s9` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t2_s12` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s5` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s7` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s9` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t3_s12` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s5` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s7` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s9` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t4_s12` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `max_km` float(11,0) DEFAULT NULL,
  `service_charge` float DEFAULT NULL COMMENT '服务费 包含过路费等',
  `park_price` float(11,0) DEFAULT NULL COMMENT '停车费',
  `night_rate` varchar(1024) DEFAULT NULL COMMENT '夜间费率 计算公式 （B+100）*120%',
  `night_start_time` varchar(20) DEFAULT NULL,
  `night_end_time` varchar(20) DEFAULT NULL,
  `models` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `syc_time` timestamp NULL DEFAULT NULL COMMENT '同步solr时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '1. 可用 2.不可用',
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接送机规则表';

-- ----------------------------
-- Records of price_shuttle_rule
-- ----------------------------

-- ----------------------------
-- Table structure for `price_snapshot`
-- ----------------------------
DROP TABLE IF EXISTS `price_snapshot`;
CREATE TABLE `price_snapshot` (
  `pkid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `pricemark` varchar(256) NOT NULL COMMENT '价格标识符',
  `car_type` smallint(6) NOT NULL,
  `seat_category` smallint(6) NOT NULL,
  `city_id` int(11) DEFAULT NULL,
  `airport_id` int(11) DEFAULT NULL,
  `order_type` tinyint(4) NOT NULL COMMENT '1. 接机 2.送机 3.日租 4次租',
  `price` double NOT NULL COMMENT '报价',
  `price_factor` varchar(1024) NOT NULL,
  `price_detail` text,
  `order_no` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='价格快照表';

-- ----------------------------
-- Records of price_snapshot
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_additional_cost`
-- ----------------------------
DROP TABLE IF EXISTS `trade_additional_cost`;
CREATE TABLE `trade_additional_cost` (
  `additional_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `guide_id` int(11) DEFAULT NULL,
  `order_id` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `apply_price` int(11) DEFAULT NULL,
  `over_time_cost` int(11) DEFAULT NULL,
  `over_distance_cost` int(11) DEFAULT NULL,
  `over_day_cost` int(11) DEFAULT NULL,
  `over_pay_cost` int(11) DEFAULT NULL,
  `over_wait_time_cost` int(11) DEFAULT '0',
  `reason` text COLLATE utf8_unicode_ci,
  `cfm_price` int(11) DEFAULT NULL,
  `cfm_over_time_cost` int(11) DEFAULT NULL,
  `cfm_over_distance_cost` int(11) DEFAULT NULL,
  `cfm_over_day_cost` int(11) DEFAULT NULL,
  `cfm_over_pay_cost` int(11) DEFAULT NULL,
  `cfm_over_wait_time_cost` int(11) DEFAULT '0',
  `comment` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `actual_price` int(11) DEFAULT NULL,
  `cfm_date_time` datetime DEFAULT NULL,
  `pay_status` int(11) DEFAULT NULL,
  `sys_pay_ment` int(11) DEFAULT NULL,
  `debt_comment` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_close` int(11) DEFAULT NULL,
  `op_user_id` int(11) DEFAULT NULL,
  `op_user_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`additional_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='导游异动金额申报';

-- ----------------------------
-- Records of trade_additional_cost
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_additional_cost_detail`
-- ----------------------------
DROP TABLE IF EXISTS `trade_additional_cost_detail`;
CREATE TABLE `trade_additional_cost_detail` (
  `additional_detail_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `additional_id` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `daily_date` date DEFAULT NULL COMMENT '包车日期',
  `over_time` int(11) DEFAULT NULL COMMENT '超时 包车以小时计数，接机以分钟计数',
  `unit_time_price` decimal(4,1) DEFAULT NULL COMMENT '超时单价  包车是一小时的价格，接机是一分钟的价格（一个overTime的价格）',
  `over_distance` decimal(4,1) DEFAULT NULL COMMENT '超公里数',
  `unit_distance_price` decimal(3,1) DEFAULT NULL COMMENT '一公里单价',
  `over_day` decimal(3,1) DEFAULT NULL,
  `unit_day_price` decimal(5,1) DEFAULT NULL,
  `other_cost1` decimal(6,1) DEFAULT NULL,
  `other_cost2` decimal(6,1) DEFAULT NULL,
  `apply_cost` decimal(8,1) DEFAULT NULL,
  `cfm_over_time` int(11) DEFAULT NULL,
  `cfm_over_distance` decimal(4,1) DEFAULT NULL,
  `cfm_over_day` decimal(3,1) DEFAULT NULL,
  `cfm_other_cost1` decimal(6,1) DEFAULT NULL,
  `cfm_other_cost2` decimal(6,1) DEFAULT NULL,
  `cfm_cost` decimal(8,1) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`additional_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of trade_additional_cost_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_comment`
-- ----------------------------
DROP TABLE IF EXISTS `trade_comment`;
CREATE TABLE `trade_comment` (
  `order_comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` text COLLATE utf8_unicode_ci,
  `star_number` decimal(2,1) DEFAULT NULL COMMENT '评价星级（1-5星）',
  `from_uid` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评价人ID',
  `from_uname` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评价人名称',
  `to_uid` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `to_uname` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `comment_type` tinyint(4) DEFAULT NULL COMMENT '类型。1-导游评价客人；2-客人评价导游；3-运营评价导游。需区分fromId和toId',
  `kpi1` int(4) DEFAULT NULL,
  `kpi2` int(4) DEFAULT NULL,
  `kpi3` int(4) DEFAULT NULL,
  `kpi4` int(4) DEFAULT NULL,
  `kpi5` int(4) DEFAULT NULL,
  `kpi6` int(4) DEFAULT NULL,
  `total_score` decimal(5,2) DEFAULT NULL COMMENT '最终评价得分',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`order_comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of trade_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_deliver-delete`
-- ----------------------------
DROP TABLE IF EXISTS `trade_deliver-delete`;
CREATE TABLE `trade_deliver-delete` (
  `order_deliver_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `order_no` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `notice_type` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '通知类型：0-不通知；1-SMS；2-push；3-wechat；',
  `deliver_type` int(11) DEFAULT NULL COMMENT '类型：1-立即发单；2-预览确认；3-增量补发；4-加价补发；',
  `flag` int(11) DEFAULT NULL COMMENT '是否处理：0-未处理；1-已处理',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`order_deliver_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='订单特殊发单处理，比如 紧急订单 增量补发  对应 orderdeliver 订单及时处理表  增量补发是在 一个订单一个月没处理情况下产生';

-- ----------------------------
-- Records of trade_deliver-delete
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_deliver_guid`
-- ----------------------------
DROP TABLE IF EXISTS `trade_deliver_guid`;
CREATE TABLE `trade_deliver_guid` (
  `allocat_gid` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `guide_id` int(11) DEFAULT NULL,
  `order_no` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `deliver_type` int(11) DEFAULT NULL COMMENT '类型：1-立即发单；2-预览确认；3-增量补发；4-加价补发；',
  `status` int(11) DEFAULT NULL COMMENT '挂接状态。\r\n0-初始（默认）；\r\n1-已接单（冗余）；2-已读（查看过详情页）;\r\n4-错过订单中查看详情 （已读 在错过查看页）\r\n-4 导游删除错过订单；\r\n-5 二次发单 该标记变成-5 标识该导游已经接了该单 但是临时取消',
  `notice_num` int(11) DEFAULT NULL,
  `fail_type` int(11) DEFAULT NULL COMMENT 'PK 失败的原因：1-截止接单；2-行程才冲突；3-评分；4-抢单速度；5-RP;6-已串单',
  `demand_deal` int(11) DEFAULT NULL COMMENT '表态是否愿意接单：0-未表态（默认）1-愿意接 -1 不愿意接',
  `view_result` int(11) DEFAULT NULL COMMENT '查看PK结果 0-未查看（默认）1-已查看',
  `refuse_reson` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '拒接原因，存储格式为 1,2,3,4',
  `other` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '其他原因',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`allocat_gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='分配给G的corder  对应guidorder表';

-- ----------------------------
-- Records of trade_deliver_guid
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_deliver_temp`
-- ----------------------------
DROP TABLE IF EXISTS `trade_deliver_temp`;
CREATE TABLE `trade_deliver_temp` (
  `temp_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `order_no` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `guide_id` int(11) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL COMMENT '优先级：1-最低优先级',
  `flag` int(11) DEFAULT NULL COMMENT '是否已发单 0-未发单（默认）；1-已发单',
  `status` int(11) DEFAULT NULL COMMENT '状态：1-正常（默认）；-1-删除 MIS可以删除 暂时没有了',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`temp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='发包预处理表 主要由于拉取导游和order ';

-- ----------------------------
-- Records of trade_deliver_temp
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_guide_reward`
-- ----------------------------
DROP TABLE IF EXISTS `trade_guide_reward`;
CREATE TABLE `trade_guide_reward` (
  `guide_reward_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `c_order_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `g_guide_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `guide_id` int(11) DEFAULT NULL,
  `order_reward_type` int(11) DEFAULT NULL COMMENT '奖励类型  1-首单奖励',
  `comment` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reward_money` decimal(10,2) DEFAULT NULL COMMENT '奖励金额',
  `status` int(11) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`guide_reward_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of trade_guide_reward
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_loan`
-- ----------------------------
DROP TABLE IF EXISTS `trade_loan`;
CREATE TABLE `trade_loan` (
  `settle_no` varchar(255) DEFAULT NULL COMMENT '结算号',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单号',
  `account_type` int(11) DEFAULT NULL COMMENT '账户类型 1 导游帐号 2代理商帐号 3',
  `account_no` varchar(255) DEFAULT NULL COMMENT '帐号ID',
  `price` double(11,2) DEFAULT NULL COMMENT '价钱',
  `profit` double(11,2) DEFAULT NULL COMMENT '利润',
  `settle_time` timestamp NULL DEFAULT NULL COMMENT '结算时间',
  `settle_status` int(11) DEFAULT NULL COMMENT '结算状态 1成功 2账户被冻结 3订单不容许结算',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `op_user_type` int(11) DEFAULT NULL COMMENT '操作员类型 系统默认1 ',
  `op_user_id` varchar(255) DEFAULT NULL COMMENT '操作员ID 系统 为1008',
  `op_user_name` varchar(255) DEFAULT NULL COMMENT '操作员ID 系统默认为system',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='放款 结算表';

-- ----------------------------
-- Records of trade_loan
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_order`
-- ----------------------------
DROP TABLE IF EXISTS `trade_order`;
CREATE TABLE `trade_order` (
  `order_no` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `order_status` int(11) DEFAULT NULL,
  `order_status_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_type` int(11) DEFAULT NULL COMMENT '订单类型。1-接机；2-送机；3-日租；4-次租',
  `biz_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '1 串单 2 被串单 3拼车',
  `service_time` timestamp NULL DEFAULT NULL COMMENT '服务时间',
  `service_end_time` timestamp NULL DEFAULT NULL COMMENT '日租结束时间',
  `service_continent_id` int(11) DEFAULT NULL COMMENT '大洲ID',
  `service_continent_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '大洲',
  `service_country_id` int(11) DEFAULT NULL,
  `service_country_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `service_city_id` int(11) DEFAULT NULL,
  `service_city_spell` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `service_city_enname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `service_city_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `service_area_code` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '服务酒店 区号类似与 86中国',
  `service_address_tel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '目的地酒店或者区域电话号码',
  `service_daily_type` smallint(6) DEFAULT NULL COMMENT '日租 泰国 车导服务类型：0-默认（该城市暂不可选择车导服务类型）， 1-当地司机，中文客服， 2-当地司机，中文导游',
  `service_local_days` smallint(6) DEFAULT NULL COMMENT '日租市内天数',
  `service_nonlocal_days` smallint(6) DEFAULT NULL COMMENT '日租市外天数',
  `service_end_cityid` int(11) DEFAULT NULL,
  `service_end_cityname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `start_address` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '接送机：出发地（冗余）机场名+航站楼',
  `start_address_detail` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `start_address_poi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出发地经纬度',
  `dest_address` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dest_address_detail` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dest_address_poi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '到达地经纬度',
  `distance` double(5,3) DEFAULT NULL,
  `car_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '车ID',
  `car_license_num` int(11) DEFAULT NULL COMMENT '车牌号',
  `car_type_id` int(11) DEFAULT NULL COMMENT '经济 舒适 豪华 奢华',
  `car_seat_num` tinyint(4) DEFAULT NULL COMMENT '车座数',
  `car_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '经济型5座车系',
  `car_desc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '现代圣达菲,起亚K5,雪佛兰迈锐宝',
  `price_mark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price_base` double(11,2) DEFAULT NULL,
  `price_guide` double(11,2) DEFAULT NULL COMMENT '导游价',
  `price_sale` double(11,2) DEFAULT NULL COMMENT '渠道销售价 C端价格',
  `price_ticket` double(11,2) DEFAULT NULL COMMENT '票面价',
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户ID',
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系名姓名',
  `user_area_code1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_mobile1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_area_code2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_mobile2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_area_code3` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_mobile3` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_comment_status` int(11) DEFAULT NULL,
  `user_remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户备注',
  `guide_cuse_id` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '对于日租业务C端提交订单时所选择的导游ID',
  `guide_pre_id` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'mis指定',
  `guide_id` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  `guide_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '导游名称',
  `guide_mobile` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '导游手机',
  `guid_comment_status` smallint(2) DEFAULT NULL COMMENT '评论状态',
  `guid_agency_type` int(11) DEFAULT '0' COMMENT '?接单导游类型 同guide表的agencyType 地接社员工状态：1=地接社员工；2=地接社管理员枚举；0=不属于地接社（默认）',
  `guide_deal_time` timestamp NULL DEFAULT NULL COMMENT '接单时间',
  `agent_id` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户ID。clienttype=1时为agentuserid；clienttype=2时为userpassportid',
  `agent_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'gds 登录用户代理商名称',
  `agent_opid` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `agent_opname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL COMMENT 'MIS 管理员（客服人员）ID',
  `deliver_status` int(11) DEFAULT NULL COMMENT '发单状态',
  `deliver_type` int(11) DEFAULT NULL COMMENT '类型：1-立即发单；2-预览确认；3-增量补发；4-加价补发；5 紧急订单',
  `deliver_count` int(11) DEFAULT NULL COMMENT '订单重发次数：默认为0',
  `deliver_time` timestamp NULL DEFAULT NULL COMMENT '发单时间',
  `im_token` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `serial_order_no` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '串单关联ID',
  `group_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '组号 拼车时使用',
  `flight_no` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '航班编号',
  `flight_airport_code` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '机场3字码',
  `flight_airport_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '机场名称',
  `flight_fly_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '航班计划起飞时间',
  `flight_arrive_time` timestamp NULL DEFAULT NULL COMMENT '航班计划到达时间',
  `flight_is_custom` smallint(6) DEFAULT NULL COMMENT '航班是否为顾客自定义',
  `flight_airport_buiding` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '航站楼',
  `flight_register_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '非常准机场ID 航班和订单关联ID',
  `flight_brand_sign` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '接机牌姓名',
  `adult_num` int(11) DEFAULT NULL,
  `child_num` int(11) DEFAULT NULL COMMENT '儿童数',
  `child_seat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '3岁;3岁;7岁 根据child_num 生成一个数组 以;分开 1-1,2-3',
  `is_arrival_visa` smallint(6) DEFAULT NULL COMMENT '是否为落地签证；0-否；1-是',
  `journey_comment` text COLLATE utf8_unicode_ci,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_no`),
  KEY `admin_id` (`admin_id`) USING BTREE,
  KEY `guide_id` (`guide_id`) USING BTREE,
  KEY `user_id` (`agent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='交易 订单表';

-- ----------------------------
-- Records of trade_order
-- ----------------------------
INSERT INTO `trade_order` VALUES ('S1001441019664569426', '1006', '行程结束', '1', null, '2015-10-27 11:35:00', null, '6', '亚洲', '54', '韩国', '204', 'shou er', 'Seoul', '首尔', '86', '14568975544', null, null, null, null, null, '金浦国际机场', '金浦国际机场 T3', '37.558654,126.794474', 'Grand Hilton Seoul', '353 Yeonhui-ro, Seodaemun-gu, 서울특별시 韩国', '37.588893,126.93405', '57.090', null, null, '1', '4', null, '起亚K5,现代圣达菲,现代avante', 'PF778525455', null, null, '100.00', null, 'AUYI12938123890', null, '20', '15652694117', null, null, null, null, null, null, '一定送到啊', null, null, '7', '王力宏', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'CA123', 'GMP', '仁川国际机场', '2015-10-07 09:35:00', '2015-10-07 08:30:00', '0', 'T3', null, null, '2', '2', '1-1,1-2', '1', null, '2015-10-12 15:51:57', '2015-10-12 16:03:07');

-- ----------------------------
-- Table structure for `trade_order_bunch-deleted`
-- ----------------------------
DROP TABLE IF EXISTS `trade_order_bunch-deleted`;
CREATE TABLE `trade_order_bunch-deleted` (
  `bunch_no` varchar(60) COLLATE utf8_unicode_ci NOT NULL COMMENT '串单no',
  `order_no` varchar(60) COLLATE utf8_unicode_ci NOT NULL COMMENT '被串单的订单号',
  `order_guide_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_airport_code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '机场三字码',
  `order_address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `match_price_sale` double(8,2) NOT NULL,
  `match_price_base` double(8,2) NOT NULL,
  `match_price_guide` double(8,2) NOT NULL,
  `match_price_ticket` double(8,2) DEFAULT NULL,
  `match_order_no` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `match_order_type` int(11) DEFAULT NULL,
  `match_order_service_date` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `match_airport_code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '机场三字码',
  `match_address` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '机场名称 或者 三字码',
  `is_match` int(11) DEFAULT NULL COMMENT '导游 接单才是true',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`bunch_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='串单表 描述被串订单原始信息 于 目标订单信息';

-- ----------------------------
-- Records of trade_order_bunch-deleted
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_order_daily`
-- ----------------------------
DROP TABLE IF EXISTS `trade_order_daily`;
CREATE TABLE `trade_order_daily` (
  `order_no` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `journey_comment` text COLLATE utf8_unicode_ci COMMENT '行程标注',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of trade_order_daily
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_order_log`
-- ----------------------------
DROP TABLE IF EXISTS `trade_order_log`;
CREATE TABLE `trade_order_log` (
  `order_log_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `order_no` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `guide_id` int(11) DEFAULT NULL,
  `guide_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_type` int(11) DEFAULT NULL COMMENT '日志类型。\r\n1-提交订单；\r\n2-补充订单资料（包括部分内容修改）；\r\n3-付款；\r\n4-已确定导游（导游已接单）；\r\n5-导游已到达预定地点；\r\n6-导游已接到客人（开始出发）；\r\n7-已完成服务；\r\n8-导游评价客人；\r\n9-客人评价导游；\r\n10-确认费用；\r\n11-结算完成；\r\n98-运营确认分发；\r\n\r\n101-客人（代理）取消订单；\r\n102-导游（运营）取消订单；\r\n103-手动添加备注信息；\r\n104-运营认领订单；\r\n105-运营确认退款（存储具体金额）；\r\n106-系统自动取消订单（',
  `op_type` tinyint(4) DEFAULT NULL COMMENT '日志分类。1-代理商行为；2-用户行为；3-导游行为；4-客服行为；5-系统行为；6-地接社行为',
  `content` varchar(123) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '日志文本内容。根据type不同，文本内容不同。系统自动填写',
  `comment` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客服或代理商主动填写的备注信息',
  `value` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '对应操作记录的值。',
  `op_user_id` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `op_user_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`order_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of trade_order_log
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_order_track`
-- ----------------------------
DROP TABLE IF EXISTS `trade_order_track`;
CREATE TABLE `trade_order_track` (
  `track_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `track_desc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '行程动态文字内容',
  `track_content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '例如：我已到达，需获取gps坐标',
  `type` int(11) DEFAULT NULL COMMENT '行程动态类型。1-接下订单；2-航班延误；3-航班起飞；4-航班落地；5-已到达接车地点；6-已接到客人；7-已完成服务；8-导游评价客人；9-客人评价导游；99-订单被取消；-1-客人取消订单；98-订单被修改；10-服务费用结算完成，11-改派订单，12-订单奖金,13-航班取消，14-航班备降，15-航班返航,16-您确认了服务费用，并提交了增项费用申请',
  `status` int(11) DEFAULT NULL COMMENT '状态：1- 正常（默认）；-1：删除',
  `is_read` int(11) DEFAULT NULL COMMENT '是否已读：0-未读（默认）；1-已读',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`track_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of trade_order_track
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_payment`
-- ----------------------------
DROP TABLE IF EXISTS `trade_payment`;
CREATE TABLE `trade_payment` (
  `pay_no` varchar(60) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '支付号',
  `order_no` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '订单号',
  `pay_getway` int(11) DEFAULT NULL COMMENT '支付方式。1-网银；2-信用卡；5-支付宝；6-微信；101-招商银行；102-工商银行；103-农业银行；104-中国银行；',
  `pay_gateway_account` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '支付帐号  暂时可以不存 ?',
  `seller_partner` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商户partner 支付宝或者wechat分配的Id  暂时不存 ？',
  `pay_status` int(11) DEFAULT NULL COMMENT '支付 状态',
  `order_price` double(11,2) DEFAULT NULL COMMENT '订单价',
  `coup_pay` double(11,2) DEFAULT NULL COMMENT '券抵扣',
  `pay_should` double(11,2) DEFAULT NULL COMMENT '应付',
  `pay_actual` double(11,2) DEFAULT NULL COMMENT '实付order_price=coup_pay+pay_should\r\npay_should=pay_actual+pay_fee',
  `pay_fee` double(11,2) DEFAULT NULL COMMENT '支付手续费',
  `coupon_id` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '券ID',
  `bank_code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '银行编码 是否保留 ？',
  `pay_source` smallint(6) DEFAULT NULL COMMENT '支付来源，1为PC，2表示wap 3客户端app',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `order_expire_time` timestamp NULL DEFAULT NULL COMMENT '订单过期时间',
  `order_create_time` timestamp NULL DEFAULT NULL COMMENT '订单创建时间',
  `user_mobile` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '支付用的手机号',
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '支付用户ID',
  `pay_subject` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '支付名称',
  `pay_desc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '支付描述',
  `third_pay_no` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '第三方支付号',
  `third_notify_log` text COLLATE utf8_unicode_ci,
  `third_notify_status` int(11) DEFAULT NULL COMMENT '1:未通知;2,支付成功;3,支付失败',
  `callback_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '回调URL  暂时使用业务编码方式实现',
  `callback_status` int(11) DEFAULT NULL COMMENT '回调状态',
  `callback_count` int(11) DEFAULT NULL,
  `callback_time` timestamp NULL DEFAULT NULL COMMENT '最后回调时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL COMMENT '待确认是否需要',
  PRIMARY KEY (`pay_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of trade_payment
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_price_history`
-- ----------------------------
DROP TABLE IF EXISTS `trade_price_history`;
CREATE TABLE `trade_price_history` (
  `pkid` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(60) NOT NULL,
  `source_guide_price` double(8,2) DEFAULT NULL COMMENT '导游价 原始价 ',
  `target_guide_price` double(8,2) DEFAULT NULL,
  `op_uid` varchar(255) DEFAULT NULL,
  `op_uname` varchar(255) DEFAULT NULL,
  `op_comment` varchar(255) DEFAULT NULL COMMENT '改价原因',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录订单的价格变化';

-- ----------------------------
-- Records of trade_price_history
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_redeliver-delete`
-- ----------------------------
DROP TABLE IF EXISTS `trade_redeliver-delete`;
CREATE TABLE `trade_redeliver-delete` (
  `guideOrderId` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `guide_id` int(11) DEFAULT NULL,
  `order_no` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vatch_no` int(11) DEFAULT NULL,
  `notice_num` int(11) DEFAULT NULL,
  `flag` tinyint(4) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `fail_type` int(11) DEFAULT NULL,
  `demand_deal` int(11) DEFAULT NULL,
  `view_result` int(11) DEFAULT NULL,
  `refuse_reason` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `other` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `deliver_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`guideOrderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='二次发单表 可能干掉';

-- ----------------------------
-- Records of trade_redeliver-delete
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_refund`
-- ----------------------------
DROP TABLE IF EXISTS `trade_refund`;
CREATE TABLE `trade_refund` (
  `refund_no` varchar(60) NOT NULL COMMENT '退款ID',
  `order_no` varchar(255) DEFAULT NULL,
  `refund_status` int(11) DEFAULT NULL COMMENT '退款状态',
  `pay_no` varchar(255) DEFAULT NULL COMMENT '该笔退款产生的支付订单号',
  `price` double(11,6) DEFAULT NULL COMMENT '退款金额',
  `refund_subject` varchar(255) DEFAULT NULL COMMENT '退款名称',
  `refund_desc` varchar(255) DEFAULT NULL COMMENT '退款描述',
  `refund_time` timestamp NULL DEFAULT NULL COMMENT '退款时间',
  `user_mobile` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `pay_getway` smallint(4) DEFAULT NULL COMMENT '支付方式。1-网银；2-信用卡；5-支付宝；6-微信；101-招商银行；102-工商银行；103-农业银行；104-中国银行；',
  `pay_gateway_account` varchar(255) DEFAULT NULL,
  `seller_partner` varchar(255) DEFAULT NULL COMMENT '商户partner 支付宝或者weixin分配的账户ID',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`refund_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退款表';

-- ----------------------------
-- Records of trade_refund
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_route`
-- ----------------------------
DROP TABLE IF EXISTS `trade_route`;
CREATE TABLE `trade_route` (
  `route_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `value` text COLLATE utf8_unicode_ci,
  `endTime` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`route_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of trade_route
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_withdrawals`
-- ----------------------------
DROP TABLE IF EXISTS `trade_withdrawals`;
CREATE TABLE `trade_withdrawals` (
  `withdrawals_no` varchar(60) NOT NULL,
  `wdraw_status` int(11) DEFAULT NULL COMMENT '提现状态',
  `user_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_target_account` varchar(255) DEFAULT NULL COMMENT '转账目标账号',
  `user_target_name` varchar(255) DEFAULT NULL COMMENT '转账目标用户的姓名',
  `user_mobile_no` varchar(255) DEFAULT NULL,
  `user_bank_code` varchar(255) DEFAULT NULL,
  `wdraw_amount` double(8,2) DEFAULT NULL COMMENT '提现金额',
  `wdraw_subject` varchar(255) DEFAULT NULL COMMENT '提现名称',
  `wdraw_desc` varchar(255) DEFAULT NULL COMMENT '提现描述',
  `third_notify_time` timestamp NULL DEFAULT NULL,
  `gateway` int(11) DEFAULT NULL,
  `thrid_order_no` varchar(255) DEFAULT NULL COMMENT '第三方支付平台订单号',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`withdrawals_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='取款 支付表';

-- ----------------------------
-- Records of trade_withdrawals
-- ----------------------------
