package com.hbc.api.trade.order.mapping.genx.xbean;

/**
 * @author Jongly Ran
 */
public class AdditionalCostDetail {
	
		/**
	     *  包车日期
	     *  所属表字段为trade_additional_detail.daily_date
	     */
	    private String dailyDate;
	
	    /**
	     *  超时 包车以小时计数，接机以分钟计数
	     *  所属表字段为trade_additional_detail.over_time
	     */
	    private Integer overTime;
	    
	    private Double overTimePrice;
	
	    /**
	     *  超时单价  包车是一小时的价格，接机是一分钟的价格（一个overTime的价格）
	     *  所属表字段为trade_additional_detail.unit_time_price
	     */
	    private Double unitTimePrice;
	
	    /**
	     *  超公里数
	     *  所属表字段为trade_additional_detail.over_distance
	     */
	    private Double overDistance;
	    
	    private Double overDistancePrice;
	
	    /**
	     *  一公里单价
	     *  所属表字段为trade_additional_detail.unit_distance_price
	     */
	    private Double unitDistancePrice;
	
	    /**
	     *  超天数
	     *  所属表字段为trade_additional_detail.over_day
	     */
	    private Double overDay;
	    private Double overDayPrice;
	
	    /**
	     *  一天的报价
	     *  所属表字段为trade_additional_detail.unit_day_price
	     */
	    private Double unitDayPrice;
	
	    /**
	     *  其他费用1
	     *  所属表字段为trade_additional_detail.other_fee1
	     */
	    private Double otherFee1;
	
	    /**
	     *  申请总费用
	     *  所属表字段为trade_additional_detail.applyFee
	     */
	    private Double applyfee;
	    private Double overWaitTimeCost;	// 超等待时间（仅限接送机，次租）
	
	
	    /**
		 * @return the overWaitTimeCost
		 */
		public Double getOverWaitTimeCost() {
			return overWaitTimeCost;
		}

		/**
		 * @param overWaitTimeCost the overWaitTimeCost to set
		 */
		public void setOverWaitTimeCost(Double overWaitTimeCost) {
			this.overWaitTimeCost = overWaitTimeCost;
		}

		/**
	     *包车日期
	     *trade_additional_detail.daily_date
	     *
	     * @return the value of trade_additional_detail.daily_date
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public String getDailyDate() {
	        return dailyDate;
	    }
	
	    /**
	     *包车日期
	     *trade_additional_detail.daily_date
	     *
	     * @param dailyDate the value for trade_additional_detail.daily_date
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public void setDailyDate(String dailyDate) {
	        this.dailyDate = dailyDate;
	    }
	
	    /**
	     *超时 包车以小时计数，接机以分钟计数
	     *trade_additional_detail.over_time
	     *
	     * @return the value of trade_additional_detail.over_time
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public Integer getOverTime() {
	        return overTime;
	    }
	
	    /**
	     *超时 包车以小时计数，接机以分钟计数
	     *trade_additional_detail.over_time
	     *
	     * @param overTime the value for trade_additional_detail.over_time
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public void setOverTime(Integer overTime) {
	        this.overTime = overTime;
	    }
	
	    /**
	     *超时单价  包车是一小时的价格，接机是一分钟的价格（一个overTime的价格）
	     *trade_additional_detail.unit_time_price
	     *
	     * @return the value of trade_additional_detail.unit_time_price
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public Double getUnitTimePrice() {
	        return unitTimePrice;
	    }
	
	    /**
	     *超时单价  包车是一小时的价格，接机是一分钟的价格（一个overTime的价格）
	     *trade_additional_detail.unit_time_price
	     *
	     * @param unitTimePrice the value for trade_additional_detail.unit_time_price
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public void setUnitTimePrice(Double unitTimePrice) {
	        this.unitTimePrice = unitTimePrice;
	    }
	
	    /**
	     *超公里数
	     *trade_additional_detail.over_distance
	     *
	     * @return the value of trade_additional_detail.over_distance
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public Double getOverDistance() {
	        return overDistance;
	    }
	
	    /**
	     *超公里数
	     *trade_additional_detail.over_distance
	     *
	     * @param overDistance the value for trade_additional_detail.over_distance
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public void setOverDistance(Double overDistance) {
	        this.overDistance = overDistance;
	    }
	
	    /**
	     *一公里单价
	     *trade_additional_detail.unit_distance_price
	     *
	     * @return the value of trade_additional_detail.unit_distance_price
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public Double getUnitDistancePrice() {
	        return unitDistancePrice;
	    }
	
	    /**
	     *一公里单价
	     *trade_additional_detail.unit_distance_price
	     *
	     * @param unitDistancePrice the value for trade_additional_detail.unit_distance_price
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public void setUnitDistancePrice(Double unitDistancePrice) {
	        this.unitDistancePrice = unitDistancePrice;
	    }
	
	    /**
	     *超天数
	     *trade_additional_detail.over_day
	     *
	     * @return the value of trade_additional_detail.over_day
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public Double getOverDay() {
	        return overDay;
	    }
	
	    /**
	     *超天数
	     *trade_additional_detail.over_day
	     *
	     * @param overDay the value for trade_additional_detail.over_day
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public void setOverDay(Double overDay) {
	        this.overDay = overDay;
	    }
	
	    /**
	     *一天的报价
	     *trade_additional_detail.unit_day_price
	     *
	     * @return the value of trade_additional_detail.unit_day_price
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public Double getUnitDayPrice() {
	        return unitDayPrice;
	    }
	
	    /**
	     *一天的报价
	     *trade_additional_detail.unit_day_price
	     *
	     * @param unitDayPrice the value for trade_additional_detail.unit_day_price
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public void setUnitDayPrice(Double unitDayPrice) {
	        this.unitDayPrice = unitDayPrice;
	    }
	
	    /**
	     *其他费用1
	     *trade_additional_detail.other_fee1
	     *
	     * @return the value of trade_additional_detail.other_fee1
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public Double getOtherFee1() {
	        return otherFee1;
	    }
	
	    /**
	     *其他费用1
	     *trade_additional_detail.other_fee1
	     *
	     * @param otherFee1 the value for trade_additional_detail.other_fee1
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public void setOtherFee1(Double otherFee1) {
	        this.otherFee1 = otherFee1;
	    }
	
	
	    /**
	     *申请总费用
	     *trade_additional_detail.applyFee
	     *
	     * @return the value of trade_additional_detail.applyFee
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public Double getApplyfee() {
	        return applyfee;
	    }
	
	    /**
	     *申请总费用
	     *trade_additional_detail.applyFee
	     *
	     * @param applyfee the value for trade_additional_detail.applyFee
	     *
	     * @mbggenerated Thu Nov 05 15:50:54 CST 2015
	     */
	    public void setApplyfee(Double applyfee) {
	        this.applyfee = applyfee;
	    }

		/**
		 * @return the overTimePrice
		 */
		public Double getOverTimePrice() {
			return overTimePrice;
		}

		/**
		 * @param overTimePrice the overTimePrice to set
		 */
		public void setOverTimePrice(Double overTimePrice) {
			this.overTimePrice = overTimePrice;
		}

		/**
		 * @return the overDistancePrice
		 */
		public Double getOverDistancePrice() {
			return overDistancePrice;
		}

		/**
		 * @param overDistancePrice the overDistancePrice to set
		 */
		public void setOverDistancePrice(Double overDistancePrice) {
			this.overDistancePrice = overDistancePrice;
		}

		/**
		 * @return the overDayPrice
		 */
		public Double getOverDayPrice() {
			return overDayPrice;
		}

		/**
		 * @param overDayPrice the overDayPrice to set
		 */
		public void setOverDayPrice(Double overDayPrice) {
			this.overDayPrice = overDayPrice;
		}
	}