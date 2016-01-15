package com.hbc.api.trade.order.service.deliver;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideGrade;
import com.hbc.api.trade.order.enums.order.GoodType;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.mapping.gen.TradeGuideAttitudinalMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeGuideAttitudinal;
import com.hbc.api.trade.order.mapping.gen.bean.TradeGuideAttitudinalExample;
import com.hbc.api.trade.third.LControllerService;

@Component
public class OrderPkService {

	private final static Logger log = LoggerFactory.getLogger(OrderPkService.class);
	@Autowired
	TradeGuideAttitudinalMapper tradeGuideAttitudinalMapper;
	@Autowired
	LControllerService lControllerService;

	/**
	 * 获取导游PK平衡因子
	 * 
	 * @param guideIds
	 * @return
	 */

	public List<TradeGuideAttitudinal> getGuideAttitudinals(List<String> guideIds) {
		TradeGuideAttitudinalExample tradeGuideAttitudinalExample = new TradeGuideAttitudinalExample();
		TradeGuideAttitudinalExample.Criteria criteria = tradeGuideAttitudinalExample.createCriteria();
		if (guideIds != null && guideIds.size() > 0) {
			criteria.andGuideIdIn(guideIds);
		}
		return tradeGuideAttitudinalMapper.selectByExample(tradeGuideAttitudinalExample);
	}

	/**
	 * 根据服务等级获取最优导游
	 * 
	 * @param guideIds
	 * @return
	 */
	public String getPKGuideByServiceGrade(List<TradeDeliverGuide> tradeDeliverGuides) {

		List<String> guideIds = new ArrayList<String>();

		for (TradeDeliverGuide tradeDeliverGuide : tradeDeliverGuides) {
			guideIds.add(tradeDeliverGuide.getGuideId());
		}
		// log.info(" getPKGuideByServiceGrade [" + JSON.toJSONString(guideIds)
		// + "]");
		String guideId = "";
		if (guideIds.size() > 0) {
			guideId = guideIds.get(0);
			// 获取导游评分list
			// List<GuideGrade> guideGrades =
			// lControllerService.getGuideGradeByGuideIds(guideIds);
			// if (guideGrades.size() == 0) {
			// log.error(JSON.toJSONString(guideIds) + " 无导游评分信息");
			// }
			// 获取导游平衡list
			List<TradeGuideAttitudinal> guideAttitudinals = getGuideAttitudinals(guideIds);
			// 最高分
			float maxPoint = 0.0f;
			// 等级
			int guideLevel = 100;
			Date acceptTime = new Date();
			for (TradeDeliverGuide tradeDeliverGuide : tradeDeliverGuides) {
				String guideIdGd = tradeDeliverGuide.getGuideId();

				// check 导游等级，高等级 直接秒杀低等级
				GuideBean guideBean = lControllerService.getGuidByGuideId(tradeDeliverGuide.getGuideId());
				// 导游等级不存在
				if (guideBean.getGuideLevel() == null) {
					continue;
				}
				if (guideBean.getGuideLevel().intValue() == 0) {// 未评级导游不允许接单
					continue;
				}
				// 导游等级低 直接被秒杀
				if (guideBean.getGuideLevel().intValue() > guideLevel) {
					continue;
				}
				GuideGrade guideGrade = lControllerService.getGuideGradeByGuideId(guideIdGd);
				float guidePoint = 0.0f;
				for (TradeGuideAttitudinal guideAttitudinal : guideAttitudinals) {
					if (guideAttitudinal.getGuideId().equals(guideIdGd)) {
						guidePoint = getAttitudinalPoint(guideAttitudinal);
						break;
					}
				}
				if (guideGrade != null && (guideGrade.getSysAssessment() == null || guideGrade.getSysAssessment() == 0)) {
					if (guideGrade.getPreAssessment() == 0) {
						guidePoint += 4.5f;
					} else {
						guidePoint += guideGrade.getPreAssessment();
					}
				} else if (guideGrade != null) {
					guidePoint += guideGrade.getSysAssessment();
				}
				// 导游等级高
				if (guideBean.getGuideLevel().intValue() < guideLevel) {
					maxPoint = guidePoint;
					guideId = guideIdGd;
					acceptTime = tradeDeliverGuide.getAcceptTime();
					guideLevel = guideBean.getGuideLevel().intValue();
				} else {// 导游等级相同
					if (maxPoint == 0f || guidePoint > maxPoint) {
						maxPoint = guidePoint;
						guideId = guideIdGd;
						acceptTime = tradeDeliverGuide.getAcceptTime();
						guideLevel = guideBean.getGuideLevel().intValue();
					} else if (guidePoint == maxPoint) {
						// 表态时间早的优先
						if (tradeDeliverGuide.getAcceptTime() != null && acceptTime.after(tradeDeliverGuide.getAcceptTime())) {
							guideId = guideIdGd;
							acceptTime = tradeDeliverGuide.getAcceptTime();
							guideLevel = guideBean.getGuideLevel().intValue();
						}
					}
				}

			}

		}
		// log.info(" getPKGuideByServiceGrade 最终GuideId " + guideId);
		return guideId;
	}

	/**
	 * 根据服务等级获取最优导游(排除导游等级)
	 * 
	 * @param tradeDeliverGuides
	 * @return
	 */
	public String getPKGuideByServiceGradeExGuideLevel(List<TradeDeliverGuide> tradeDeliverGuides) {

		List<String> guideIds = new ArrayList<String>();

		for (TradeDeliverGuide tradeDeliverGuide : tradeDeliverGuides) {
			guideIds.add(tradeDeliverGuide.getGuideId());
		}
		// log.info(" getPKGuideByServiceGrade [" + JSON.toJSONString(guideIds)
		// + "]");
		String guideId = "";
		if (guideIds.size() > 0) {
			guideId = guideIds.get(0);
			
			// 获取导游平衡list
			List<TradeGuideAttitudinal> guideAttitudinals = getGuideAttitudinals(guideIds);
			// 最高分
			float maxPoint = 0.0f;
			Date acceptTime = new Date();
			for (TradeDeliverGuide tradeDeliverGuide : tradeDeliverGuides) {
				String guideIdGd = tradeDeliverGuide.getGuideId();
				
				GuideBean guideBean = lControllerService.getGuidByGuideId(tradeDeliverGuide.getGuideId());

				if (guideBean.getGuideLevel().intValue() == 0) {// 未评级导游不允许接单
					continue;
				}
				GuideGrade guideGrade = lControllerService.getGuideGradeByGuideId(guideIdGd);
				float guidePoint = 0.0f;
				for (TradeGuideAttitudinal guideAttitudinal : guideAttitudinals) {
					if (guideAttitudinal.getGuideId().equals(guideIdGd)) {
						guidePoint = getAttitudinalPoint(guideAttitudinal);
						break;
					}
				}
				if (guideGrade != null && (guideGrade.getSysAssessment() == null || guideGrade.getSysAssessment() == 0)) {
					if (guideGrade.getPreAssessment() == 0) {
						guidePoint += 4.5f;
					} else {
						guidePoint += guideGrade.getPreAssessment();
					}
				} else if (guideGrade != null) {
					guidePoint += guideGrade.getSysAssessment();
				}
				if (maxPoint == 0f || guidePoint > maxPoint) {
					maxPoint = guidePoint;
					guideId = guideIdGd;
					acceptTime = tradeDeliverGuide.getAcceptTime();

				} else if (guidePoint == maxPoint) {
					// 表态时间早的优先
					if (tradeDeliverGuide.getAcceptTime() != null && acceptTime.after(tradeDeliverGuide.getAcceptTime())) {
						guideId = guideIdGd;
						acceptTime = tradeDeliverGuide.getAcceptTime();

					}
				}

			}

		}		
		return guideId;
	}

	/**
	 * 获取平衡分数
	 * 
	 * @param tradeGuideAttitudinal
	 * @return
	 */
	private float getAttitudinalPoint(TradeGuideAttitudinal tradeGuideAttitudinal) {
		float point = 0f;
		if (tradeGuideAttitudinal.getAccumulativeDealOrders() == 0) {// 从未接单成功
			point = tradeGuideAttitudinal.getSerialFailTimes() * 0.1f;
		} else if (tradeGuideAttitudinal.getAccumulativeDealOrders() < 6) {
			point = tradeGuideAttitudinal.getSerialFailTimes() * 0.08f;
		} else if (tradeGuideAttitudinal.getAccumulativeDealOrders() < 11) {
			point = tradeGuideAttitudinal.getSerialFailTimes() * 0.04f;
		} else if (tradeGuideAttitudinal.getAccumulativeDealOrders() < 21) {
			point = tradeGuideAttitudinal.getSerialFailTimes() * 0.02f;
		} else {
			point = tradeGuideAttitudinal.getSerialFailTimes() * 0.01f;
		}

		return point;
	}

	public String getPKGuideByIncome(List<TradeDeliverGuide> tradeDeliverGuides) {

		String guideId = "";
		double minGuidePrice = Double.MAX_VALUE;
		Timestamp acceptTime = new Timestamp(System.currentTimeMillis());
		for (TradeDeliverGuide tradeDeliverGuide : tradeDeliverGuides) {
			if (tradeDeliverGuide.getGuidePrice().doubleValue() < minGuidePrice) {
				minGuidePrice = tradeDeliverGuide.getGuidePrice().doubleValue();
				guideId = tradeDeliverGuide.getGuideId();
				if (tradeDeliverGuide.getAcceptTime() != null) {
					acceptTime = new Timestamp(tradeDeliverGuide.getAcceptTime().getTime());
				} else {
					acceptTime = new Timestamp(System.currentTimeMillis());
				}

			} else if (tradeDeliverGuide.getGuidePrice().doubleValue() == minGuidePrice) {
				Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
				if (tradeDeliverGuide.getAcceptTime() != null) {
					currentTimestamp = new Timestamp(tradeDeliverGuide.getAcceptTime().getTime());
				}
				if (currentTimestamp.before(acceptTime)) {
					guideId = tradeDeliverGuide.getGuideId();
					acceptTime = currentTimestamp;
				}
			}
		}

		return guideId;
	}

	/**
	 * 新版收入PK
	 * 
	 * @param tradeDeliverGuides
	 * @param orderBean
	 * @return
	 */
	public String getPKGuideByIncome(List<TradeDeliverGuide> tradeDeliverGuides, OrderBean orderBean) {

		String guideId = "";
		double minGuidePrice = Double.MAX_VALUE;
		Timestamp acceptTime = new Timestamp(System.currentTimeMillis());
		TradeDeliverGuide currentTradeDeliverGuide = new TradeDeliverGuide();
		for (TradeDeliverGuide tradeDeliverGuide : tradeDeliverGuides) {
			if (tradeDeliverGuide.getGuidePrice().doubleValue() < minGuidePrice) {
				minGuidePrice = tradeDeliverGuide.getGuidePrice().doubleValue();
				guideId = tradeDeliverGuide.getGuideId();
				if (tradeDeliverGuide.getAcceptTime() != null) {
					acceptTime = new Timestamp(tradeDeliverGuide.getAcceptTime().getTime());
				} else {
					acceptTime = new Timestamp(System.currentTimeMillis());
				}
				currentTradeDeliverGuide = tradeDeliverGuide;

			} else if (tradeDeliverGuide.getGuidePrice().doubleValue() == minGuidePrice) {
				List<TradeDeliverGuide> guideList = new ArrayList<TradeDeliverGuide>();
				guideList.add(tradeDeliverGuide);
				guideList.add(currentTradeDeliverGuide);

				if (OrderType.DAILY.value.equals(orderBean.getOrderType())
						|| (OrderType.COMMENDATION.value.equals(orderBean.getOrderType()) && !GoodType.PICKUPORDER.value.equals(orderBean.getOrderGoodsType()) && !GoodType.TRANSFER.value.equals(orderBean.getOrderGoodsType()) && !GoodType.PERUSE.value.equals(orderBean
								.getOrderGoodsType()))) {
					GuideBean currentGuide = lControllerService.getGuidByGuideId(guideId);
					GuideBean guideBean = lControllerService.getGuidByGuideId(tradeDeliverGuide.getGuideId());
					if (guideBean == null || guideBean.getGuideId() == null || guideBean.getGuideId().equals("")) {
						continue;
					}
					if (currentGuide == null || currentGuide.getGuideId() == null || currentGuide.getGuideId().equals("")) {
						guideId = tradeDeliverGuide.getGuideId();
					} else {
						if (currentGuide.getCityId().equals(guideBean.getCityId())) {
							guideId = this.getPKGuideByServiceGradeExGuideLevel(guideList);

						} else if (orderBean.getServiceCityId().equals(currentGuide.getCityId())) {// 不需要处理

						} else if (orderBean.getServiceCityId().equals(guideBean.getCityId())) {
							guideId = tradeDeliverGuide.getGuideId();
						} else if (orderBean.getServiceEndCityid().equals(guideBean.getCityId())) {
							guideId = tradeDeliverGuide.getGuideId();
						} else if (orderBean.getServiceEndCityid().equals(currentGuide.getCityId())) {// 不需要处理

						} else if (orderBean.getServicePassCity().contains(guideBean.getCityId() + "_")) {// 途径城市
							if (orderBean.getServicePassCity().contains(currentGuide.getCityId() + "_")) {
								guideId = this.getPKGuideByServiceGradeExGuideLevel(guideList);
							} else {
								guideId = tradeDeliverGuide.getGuideId();
							}
						} else if (orderBean.getServicePassCity().contains(currentGuide.getCityId() + "_")) {// 途径城市
																												// 不需要处理

						} else {// 都不是途径城市
							guideId = this.getPKGuideByServiceGradeExGuideLevel(guideList);
						}
					}

					if (guideId == tradeDeliverGuide.getGuideId()) {
						if (tradeDeliverGuide.getAcceptTime() != null) {
							acceptTime = new Timestamp(tradeDeliverGuide.getAcceptTime().getTime());
						} else {
							acceptTime = new Timestamp(System.currentTimeMillis());
						}
						currentTradeDeliverGuide = tradeDeliverGuide;
					}
				} else {

					guideId = this.getPKGuideByServiceGradeExGuideLevel(guideList);
				}

				// Timestamp currentTimestamp = new
				// Timestamp(System.currentTimeMillis());
				// if (tradeDeliverGuide.getAcceptTime() != null) {
				// currentTimestamp = new
				// Timestamp(tradeDeliverGuide.getAcceptTime().getTime());
				// }
				// if (currentTimestamp.before(acceptTime)) {
				// guideId = tradeDeliverGuide.getGuideId();
				// acceptTime = currentTimestamp;
				// }
			}
		}
		return guideId;
	}

}
