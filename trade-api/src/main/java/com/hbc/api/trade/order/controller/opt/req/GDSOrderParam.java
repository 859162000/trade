/*
 * Copyright (c) 2015-2016, CCLX.COM. All rights reserved.
 * WANDA GROUP PROPRIETARY/CONFIDENTIAL. 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is private property; you can't redistribute it and/or modify it
 * under the terms of the LICENSE you obtained from
 *
 *    http://www.cclx.com/
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * Author: Jongly Ran
 * Revision: 1.0
 */
package com.hbc.api.trade.order.controller.opt.req;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;

/**
 * @author Jongly Ran
 */
public class GDSOrderParam extends OrderBean {
	private String serviceDate;			// 服务时间[2015-10-03]
	private String serviceEndDate;		// 服务终止时间[2015-10-03]
	private String serviceRecTime;		// 服务时间的时分秒
	
    /**
	 * @return the serviceEndDate
	 */
	public String getServiceEndDate() {
		return serviceEndDate;
	}

	/**
	 * @param serviceEndDate the serviceEndDate to set
	 */
	public void setServiceEndDate(String serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	/**
	 * @return the serviceDate
	 */
	public String getServiceDate() {
		return serviceDate;
	}

	/**
	 * @param serviceDate the serviceDate to set
	 */
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	/**
     *  服务时间，服务开始时间  日租默认8点
     *  所属表字段为trade_order.service_time
     */
    private String serviceTimes;
    /**
     *  日租结束时间
     *  所属表字段为trade_order.service_end_time
     */
    private String serviceEndTimes;
    /**
     *  导游被指定时间
     *  所属表字段为trade_order.guide_assign_time
     */
    private String guideAssignTimes;
    /**
     *  航班计划起飞时间
     *  所属表字段为trade_order.flight_fly_time
     */
    private String flightFlyTimes;

    /**
     *  航班计划到达时间
     *  所属表字段为trade_order.flight_arrive_time
     */
    private String flightArriveTimes;
    /**
     *  订单取消时间
     *  所属表字段为trade_order.cancel_time
     */
    private String cancelTimes;

    /**
     *  订单完成时间
     *  所属表字段为trade_order.complete_time
     */
    private String completeTimes;

    /**
     *  下单时间
     *  所属表字段为trade_order.create_time
     */
    private String createTimes;
    

    /**
     *  
     *  所属表字段为trade_order.update_time
     */
    private String updateTimes;
    
    

	/**
	 * @return the serviceRecTime
	 */
	public String getServiceRecTime() {
		return serviceRecTime;
	}

	/**
	 * @param serviceRecTime the serviceRecTime to set
	 */
	public void setServiceRecTime(String serviceRecTime) {
		this.serviceRecTime = serviceRecTime;
	}

	/**
	 * @return the serviceTimes
	 */
	public String getServiceTimes() {
		return serviceTimes;
	}

	/**
	 * @return the serviceEndTimes
	 */
	public String getServiceEndTimes() {
		return serviceEndTimes;
	}

	/**
	 * @param serviceEndTimes the serviceEndTimes to set
	 */
	public void setServiceEndTimes(String serviceEndTimes) {
		this.serviceEndTimes = serviceEndTimes;
	}

	/**
	 * @return the guideAssignTimes
	 */
	public String getGuideAssignTimes() {
		return guideAssignTimes;
	}

	/**
	 * @param guideAssignTimes the guideAssignTimes to set
	 */
	public void setGuideAssignTimes(String guideAssignTimes) {
		this.guideAssignTimes = guideAssignTimes;
	}

	/**
	 * @return the flightFlyTimes
	 */
	public String getFlightFlyTimes() {
		return flightFlyTimes;
	}

	/**
	 * @param flightFlyTimes the flightFlyTimes to set
	 */
	public void setFlightFlyTimes(String flightFlyTimes) {
		this.flightFlyTimes = flightFlyTimes;
	}

	/**
	 * @return the flightArriveTimes
	 */
	public String getFlightArriveTimes() {
		return flightArriveTimes;
	}

	/**
	 * @param flightArriveTimes the flightArriveTimes to set
	 */
	public void setFlightArriveTimes(String flightArriveTimes) {
		this.flightArriveTimes = flightArriveTimes;
	}

	/**
	 * @return the cancelTimes
	 */
	public String getCancelTimes() {
		return cancelTimes;
	}

	/**
	 * @param cancelTimes the cancelTimes to set
	 */
	public void setCancelTimes(String cancelTimes) {
		this.cancelTimes = cancelTimes;
	}

	/**
	 * @return the completeTimes
	 */
	public String getCompleteTimes() {
		return completeTimes;
	}

	/**
	 * @param completeTimes the completeTimes to set
	 */
	public void setCompleteTimes(String completeTimes) {
		this.completeTimes = completeTimes;
	}

	/**
	 * @return the createTimes
	 */
	public String getCreateTimes() {
		return createTimes;
	}

	/**
	 * @param createTimes the createTimes to set
	 */
	public void setCreateTimes(String createTimes) {
		this.createTimes = createTimes;
	}

	/**
	 * @return the updateTimes
	 */
	public String getUpdateTimes() {
		return updateTimes;
	}

	/**
	 * @param updateTimes the updateTimes to set
	 */
	public void setUpdateTimes(String updateTimes) {
		this.updateTimes = updateTimes;
	}

	/**
	 * @param serviceTimes the serviceTimes to set
	 */
	public void setServiceTimes(String serviceTimes) {
		this.serviceTimes = serviceTimes;
	}

}
