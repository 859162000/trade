/**
 * @Author lukangle
 * @2015年11月9日@下午8:00:15
 */
package com.hbc.api.trade.order.service.deliver.conf;

import org.apache.solr.client.solrj.beans.Field;

import com.hbc.api.trade.order.enums.CarClassTypeEnum;
import com.hbc.api.trade.order.enums.third.GuideLevelEnums;

public class TradeCitySolrConf {
	public static String collectionName = "tradedeliver";
	@Field
	private String id;
	@Field
	private int continent_id;
	@Field
	private String continent_name;
	@Field
	private int country_id;
	@Field
	private String country_name;
	@Field
	private int city_id;
	@Field
	private String city_name;

	@Field
	private int conf_type;//PICKUPORDER(1, "接送次"),DAILY(2, "日租"),
	// <!-- 急单配置 -->
//	@Field
//	private String urgent_1_5;
//	@Field
//	private String urgent_1_7;
//	@Field
//	private String urgent_1_9;
//	@Field
//	private String urgent_1_12;
//
//	@Field
//	private String urgent_2_5;
//	@Field
//	private String urgent_2_7;
//	@Field
//	private String urgent_2_9;
//	@Field
//	private String urgent_2_12;
//
//	@Field
//	private String urgent_3_5;
//	@Field
//	private String urgent_3_7;
//	@Field
//	private String urgent_3_9;
//	@Field
//	private String urgent_3_12;
//
//	@Field
//	private String urgent_4_5;
//	@Field
//	private String urgent_4_7;
//	@Field
//	private String urgent_4_9;
//	@Field
//	private String urgent_4_12;

	// <!-- 导游降价设置(该城市同一订单，发给不同导游时，“导游价”降低比例）-->
	@Field
	private int guid_a;
	@Field
	private int guid_b;
	@Field
	private int guid_c;
	@Field
	private int guid_d;
	@Field
	private int guid_e;

	@Field
	private int conflict_sing_airport;//OPEN(1, "接送次"),CLOSED(2, "日租"),
	@Field
	private int conflict_time_gap;// 接送次冲突时间差 分钟计算

	@Field
	private int ser_open;// <!-- 串单是否开启 --> CLOSE(0, "关闭"), OPEN(1, "启用");
	@Field
	private int ser_start_minute;// <!--接送机串单时差 开始时间-->
	@Field
	private int ser_end_minute;// <!--接送机串单时差 结束时间-->
	@Field
	private double ser_reduce_price;// <!--串单降价系数-->

	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getContinent_id() {
		return continent_id;
	}
	public void setContinent_id(int continent_id) {
		this.continent_id = continent_id;
	}
	public String getContinent_name() {
		return continent_name;
	}
	public void setContinent_name(String continent_name) {
		this.continent_name = continent_name;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public int getConf_type() {
		return conf_type;
	}
	public void setConf_type(int conf_type) {
		this.conf_type = conf_type;
	}
	public int getGuid_a() {
		return guid_a;
	}
	public void setGuid_a(int guid_a) {
		this.guid_a = guid_a;
	}
	public int getGuid_b() {
		return guid_b;
	}
	public void setGuid_b(int guid_b) {
		this.guid_b = guid_b;
	}
	public int getGuid_c() {
		return guid_c;
	}
	public void setGuid_c(int guid_c) {
		this.guid_c = guid_c;
	}
	public int getGuid_d() {
		return guid_d;
	}
	public void setGuid_d(int guid_d) {
		this.guid_d = guid_d;
	}
	public int getGuid_e() {
		return guid_e;
	}
	public void setGuid_e(int guid_e) {
		this.guid_e = guid_e;
	}
	public int getConflict_sing_airport() {
		return conflict_sing_airport;
	}
	public void setConflict_sing_airport(int conflict_sing_airport) {
		this.conflict_sing_airport = conflict_sing_airport;
	}
	public int getConflict_time_gap() {
		return conflict_time_gap;
	}
	public void setConflict_time_gap(int conflict_time_gap) {
		this.conflict_time_gap = conflict_time_gap;
	}
	public int getSer_open() {
		return ser_open;
	}
	public void setSer_open(int ser_open) {
		this.ser_open = ser_open;
	}
	public int getSer_start_minute() {
		return ser_start_minute;
	}
	public void setSer_start_minute(int ser_start_minute) {
		this.ser_start_minute = ser_start_minute;
	}
	public int getSer_end_minute() {
		return ser_end_minute;
	}
	public void setSer_end_minute(int ser_end_minute) {
		this.ser_end_minute = ser_end_minute;
	}
	public double getSer_reduce_price() {
		return ser_reduce_price;
	}
	public void setSer_reduce_price(double ser_reduce_price) {
		this.ser_reduce_price = ser_reduce_price;
	}
	public class UrgentInfo{
		private CarClassTypeEnum carClassTypeEnum;
		private double hour;
		private double userPriceFloat;
		private double guidePriceFloat;
		public CarClassTypeEnum getCarClassTypeEnum() {
			return carClassTypeEnum;
		}
		public void setCarClassTypeEnum(CarClassTypeEnum carClassTypeEnum) {
			this.carClassTypeEnum = carClassTypeEnum;
		}
		public double getHour() {
			return hour;
		}
		public void setHour(double hour) {
			this.hour = hour;
		}
		public double getUserPriceFloat() {
			return userPriceFloat;
		}
		public void setUserPriceFloat(double userPriceFloat) {
			this.userPriceFloat = userPriceFloat;
		}
		public double getGuidePriceFloat() {
			return guidePriceFloat;
		}
		public void setGuidePriceFloat(double guidePriceFloat) {
			this.guidePriceFloat = guidePriceFloat;
		}
	}
	
	public UrgentInfo getUrgentInfo(CarClassTypeEnum carClassTypeEnum){
		String urgentstr = CarClassTypeEnum.getConfUrgent(carClassTypeEnum,this);
		
		String[] urgentcfs = urgentstr.split("/");
		
		UrgentInfo urgentInfo = new UrgentInfo();
		urgentInfo.setHour(Double.parseDouble(urgentcfs[0]));
		urgentInfo.setUserPriceFloat(Double.parseDouble(urgentcfs[1]));
		urgentInfo.setGuidePriceFloat(Double.parseDouble(urgentcfs[2]));
		urgentInfo.setCarClassTypeEnum(carClassTypeEnum);
		
		return urgentInfo;
	}
	/**
	 * 获取导游价格浮动
	 * @return
	 */
	public double guidePriceFloat(GuideLevelEnums guideLevelEnums){
		switch(guideLevelEnums){
		case A:
			return this.guid_a*0.01d;
		case B:
			return this.guid_b*0.01d;
		case C:
			return this.guid_c*0.01d;
		case D:
			return this.guid_d*0.01d;
		case E:
			return this.guid_e*0.01d;
		default:
			return 0d;
		}
	}

}
