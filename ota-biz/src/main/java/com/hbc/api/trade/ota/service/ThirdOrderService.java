/**
 * @brief 渠道订单service
 * @author Ivan Huang <ivanhuang@huangbaoche.com>
 * @since 2015-11-05
 * @copyright Copyright (c) www.huangbaoche.com
 */
package com.hbc.api.trade.ota.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.bdata.exception.TradeSDKException;
import com.hbc.api.trade.bdata.mapper.basedata.gen.CityBeanMapper;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBean;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBeanExample;
import com.hbc.api.trade.order.enums.CarClassTypeEnum;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.OrderService;
import com.hbc.api.trade.ota.enums.ThirdOrderStatus;
import com.hbc.api.trade.ota.mapping.gen.TradeThirdOrderMapper;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrderExample;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrderLogWithBLOBs;
import com.hbc.api.trade.ota.mapping.genx.UpdateTradeThirdOrderMapper;
import com.hbc.api.trade.ota.po.CityPo;
import com.hbc.api.trade.ota.po.CtripPriceListPo;
import com.hbc.api.trade.pay.service.RefundService;

@Component
public class ThirdOrderService {

	private final static Logger log = LoggerFactory.getLogger(ThirdOrderService.class);

	@Autowired CityBeanMapper cityBeanMapper ;
	@Autowired TradeThirdOrderMapper tradeThirdOrderMapper;
	@Autowired UpdateTradeThirdOrderMapper updateTradeThirdOrderMapper;
    @Autowired OrderService orderService ;
    @Autowired RefundService refundService;
	@Autowired ThirdOrderLogService thirdOrderLogService;
	@Autowired ThirdOrderQueueService thirdOrderQueueService;
	@Autowired HttpClientService httpClientService;

	public TradeThirdOrder getThirdOrderBythirdTradeNo(int partner, String thirdTradeNo){
		TradeThirdOrderExample tradeThirdOrderExample = new TradeThirdOrderExample();
		TradeThirdOrderExample.Criteria criteria = tradeThirdOrderExample.createCriteria();
		criteria.andThirdPartnerEqualTo(partner);
		criteria.andThirdTradeNoEqualTo(thirdTradeNo);
		List<TradeThirdOrder> tOrders = tradeThirdOrderMapper.selectByExample(tradeThirdOrderExample);
		if(tOrders.size()==0){
			return null;
		}else{
			return tOrders.get(0);
		}
	}

    @Transactional
	public TradeThirdOrder addThirdOrder(OrderBean orderBean, TradeThirdOrder tradeThirdOrder, TradeThirdOrderLogWithBLOBs tradeThirdOrderLogWithBLOBs) {
    	TradeThirdOrder orderIsExists = this.getThirdOrderBythirdTradeNo(tradeThirdOrder.getThirdPartner(), tradeThirdOrder.getThirdTradeNo()) ;
		if (orderIsExists != null) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_ISEXISTS, tradeThirdOrder.getThirdTradeNo());
		}
		String orderNo = orderService.addOrder(orderBean);
		if(orderNo==null){
			throw new TradeException(TradeReturnCodeEnum.TRADE_ADDORDER_FAILED);
		}
		tradeThirdOrder.setOrderNo(orderNo);
		tradeThirdOrder.setOrderStatus(ThirdOrderStatus.PAYSUCCESS.value);
		tradeThirdOrderMapper.insert(tradeThirdOrder);
		log.info("insert trade_trird_order [" + tradeThirdOrder + "] orderNo is [" + orderBean.getOrderNo() + "]");

		tradeThirdOrderLogWithBLOBs.setOrderNo(orderNo);
		tradeThirdOrderLogWithBLOBs.setResponseData(JSON.toJSONString(orderBean));
		thirdOrderLogService.addOrderLog(tradeThirdOrderLogWithBLOBs);
		return tradeThirdOrder;
	}
	
	@Transactional
	public void orderConfirm(String orderNo) {
		Timestamp curtime = new Timestamp(System.currentTimeMillis());
		this.updateThirdOrderStatus(orderNo, ThirdOrderStatus.GUIDE_CONFIRM);
		this.updateThirdOrderConfirmTime(orderNo, curtime);
		thirdOrderQueueService.addThirdConfirmQueue(orderNo, "", 0);
	}
	
	@Transactional
	public void orderComplete(String orderNo) {
		Timestamp curtime = new Timestamp(System.currentTimeMillis());
		String tcurtime = TimeConverter.formatDate(curtime);
    	this.updateThirdOrderStatus(orderNo, ThirdOrderStatus.ORDER_COMPLETE);
		thirdOrderQueueService.addThirdCompleteQueue(orderNo, "http://pay.huangbaoche.com/test/callback", tcurtime, 0);
	}

    @Transactional
    public void cancelThirdOrder(TradeThirdOrder tradeThirdOrder, OrderBean orderBean) {
        if (!tradeThirdOrder.getOrderNo().equals(orderBean.getOrderNo())) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, orderBean.getOrderNo());
        }
    	this.updateThirdOrderStatus(tradeThirdOrder.getOrderNo(), ThirdOrderStatus.ORDER_CANCEL);
    	refundService.refundOrder(orderBean.getOrderNo(),"");
    }
    
    @Transactional
    private void updateThirdOrderConfirmTime(String orderNo, Timestamp confirmTime) {
		TradeThirdOrder tradeThirdOrder = updateTradeThirdOrderMapper.forupdateById(orderNo);
		if (tradeThirdOrder == null) {
			TradeException tradeException = new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, "订单Id:" + orderNo);
			log.error(tradeException.getMessage());
			throw tradeException;
		}

		int affectedRows = 0;
		tradeThirdOrder.setGuideConfirmTime(confirmTime);
		affectedRows = updateTradeThirdOrderMapper.updateConfirmTimeByPrimaryKey(tradeThirdOrder);

		if (affectedRows == 0) {
			TradeException tradeException = new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, "订单号:" + tradeThirdOrder.getOrderNo());
			log.error(tradeException.getMessage());
			throw tradeException;
		}
		log.info("update [" + tradeThirdOrder.getOrderNo() + "] [" + JSON.toJSONString(tradeThirdOrder) + "] to [" + JSON.toJSONString(tradeThirdOrder) + "] ");
    }

    @Transactional
	private void updateThirdOrderStatus(String orderNo, ThirdOrderStatus orderStatus) {
		TradeThirdOrder tradeThirdOrder = updateTradeThirdOrderMapper.forupdateById(orderNo);
		if (tradeThirdOrder == null) {
			log.error("订单不存在，订单Id：" + orderNo);
			throw new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, orderNo);
		}
		if (tradeThirdOrder.getOrderStatus() .equals(orderStatus.value)) {
			log.error("DB 订单状态 已经是 ["+ThirdOrderStatus.getStatus(tradeThirdOrder.getOrderStatus()).name+ "] 订单状态异常，订单号：" + orderNo);
			throw new TradeException(TradeReturnCodeEnum.ORDER_NONEED_UPDATE);
		}
		tradeThirdOrder.setOrderStatus(orderStatus.value);
		int ors = updateTradeThirdOrderMapper.updateStatusByPrimaryKey(tradeThirdOrder);
		if (ors > 0) {
			log.info("订单更新成功，where条件：orderNo：" + orderNo + ", oldOrderStatus: " + tradeThirdOrder.getOrderStatus() + ", newOrderStatus:" + orderStatus.value);
		} else {
			log.error("订单未更新，where条件：orderNo：" + orderNo + ", oldOrderStatus: " + tradeThirdOrder.getOrderStatus() + ", newOrderStatus:" + orderStatus.value);
			throw new TradeException(TradeReturnCodeEnum.ORDER_UPDATE_FAILED);
		}
		log.info(orderNo + " end to update orderstatus to [" + orderStatus.value + "]");
    }

    public List<CityPo> getServiceCitys(int partner) {
        CityBeanExample cityBeanExample = new CityBeanExample();
        List<CityBean> cityBeanList = cityBeanMapper.selectByExample(cityBeanExample);
        List<CityPo> cityList = new ArrayList<CityPo>();
        for (int i=0;i<cityBeanList.size();i++){
        	CityBean cityBean = cityBeanList.get(i);
        	
        	CityPo cityPo = new CityPo();
        	cityPo.setServiceCityId(cityBean.getCityId().toString()) ;
        	cityPo.setServiceCityName(cityBean.getCityName()) ;
        	cityPo.setServiceCountryId(cityBean.getCountryId().toString());
        	cityPo.setServiceCountryName(cityBean.getCountryName());
        	cityList.add(cityPo);
        }
        return cityList ;
    }
    
    /*public JSONObject getPrice(String reqData) {
    	JSONObject data = this.getPriceFromApi(reqData);
		JSONArray cars = data.getJSONArray("cars");
		List<PriceListPo> priceListPos = new ArrayList<PriceListPo>();
		for (int i=0;i<cars.size();i++) {
			JSONObject jode = cars.getJSONObject(i);
			int carType = jode.getIntValue("carType");
			int seatCategory = jode.getIntValue("seatCategory");
			CarClassTypeEnum thirdCarType = CarClassTypeEnum.getCarCls(carType, seatCategory);

			PriceListPo pricePo = new PriceListPo() ;
			pricePo.setPrice(jode.getDoubleValue("price"));
			pricePo.setCarDesc(jode.getString("carDesc"));
			pricePo.setModels(jode.getString("models"));
			pricePo.setCarType(thirdCarType.thirdCarType);
			priceListPos.add(pricePo);
		}
		JSONObject ret = new JSONObject();
		ret.put("expectedCompTime", data.getIntValue("estTime"));
		ret.put("distance", data.getDoubleValue("distance"));
		ret.put("priceMark", data.getString("pricemark"));
		ret.put("pricelist", priceListPos) ;
		return ret ;

    }*/
    
    public JSONObject getCtripPrice(String reqData) {
    	JSONObject data = this.getPriceFromApi(reqData);
		JSONArray cars = data.getJSONArray("cars");
		List<CtripPriceListPo> ctripPricePo = new ArrayList<CtripPriceListPo>();
		for (int i=0;i<cars.size();i++) {
			JSONObject jode = cars.getJSONObject(i);
			int carType = jode.getIntValue("carType");
			int seatCategory = jode.getIntValue("seatCategory");
			CarClassTypeEnum thirdCarType = CarClassTypeEnum.getCarCls(carType, seatCategory);

			CtripPriceListPo pricePo = new CtripPriceListPo() ;
			pricePo.setPrice(jode.getDoubleValue("price"));
			pricePo.setVehicleType(thirdCarType.thirdCarType);
			ctripPricePo.add(pricePo);
		}
		JSONObject ret = new JSONObject();
		ret.put("MsgCode", "OK") ;
		ret.put("PriceMark", data.getString("pricemark"));
		ret.put("QueryResultList", ctripPricePo) ;
		return ret ;
    }

    /*public JSONObject getQunarPrice(String reqData) {
    	JSONObject data = this.getPriceFromApi(reqData);
		JSONArray cars = data.getJSONArray("cars");
		List<QunarPriceListPo> qunarPricePo = new ArrayList<QunarPriceListPo>();
		for (int i=0;i<cars.size();i++) {
			JSONObject jode = cars.getJSONObject(i);
			int carType = jode.getIntValue("carType");
			int seatCategory = jode.getIntValue("seatCategory");
			QunarCarTypeEnum thirdCarType = QunarCarTypeEnum.getCar(carType, seatCategory);

			QunarPriceDetailPo detailPo = new QunarPriceDetailPo();
			detailPo.setBasePrice(jode.getDoubleValue("price"));

			QunarPriceTypePo typePo = new QunarPriceTypePo();
			typePo.setCarType(thirdCarType.thirdCarType);
			typePo.setPrice(jode.getDoubleValue("price"));
			typePo.setDetail(detailPo);

			QunarPriceListPo pricePo = new QunarPriceListPo() ;
			pricePo.setLowPrice(jode.getDoubleValue("price"));
			pricePo.setTypes(typePo);
			qunarPricePo.add(pricePo);
		}
    	
		JSONObject ret = new JSONObject();
		ret.put("errorCode", 0) ;
		ret.put("distance", data.getDoubleValue("distance")) ;
		ret.put("currency", "CNY");
		ret.put("price", qunarPricePo) ;
		return ret ;
    }*/

	@Value("${trade.price.calprice}")
	String calpriceUrl;
	private JSONObject getPriceFromApi(String reqData){
		String rspstr;
		String reqUrl = calpriceUrl + "?" + reqData ;
		log.info("calpriceUrl", reqUrl);
		try {
			rspstr = httpClientService.sendReqNoEncode(reqUrl) ;
			log.info(rspstr);
		} catch (TradeSDKException e) {
			throw new TradeException(TradeReturnCodeEnum.TRADE_PRICE_ERROR, e) ;
		}
		JSONObject object = JSON.parseObject(rspstr);
		int status = object.getInteger("status");
		JSONObject data = object.getJSONObject("data");
		if (status != 200 || data == null || data.size() == 0 || data.getJSONObject("cars").size() == 0) {
			throw new TradeException(TradeReturnCodeEnum.TRADE_PRICE_EMPTY) ;
		}
		return data ;
	}
	
}
