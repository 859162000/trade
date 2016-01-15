/**
 * @Author lukangle
 * @2015年12月28日@下午11:13:28
 */
package com.hbc.data.trade.transfer.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.fund.account.mapping.gen.FundAccountLogMapper;
import com.hbc.api.fund.account.mapping.gen.FundAccountMapper;
import com.hbc.api.fund.biz.mapping.gen.FundWithdrawalMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderBeanMapper;

@Component
public class MozhangService {
	private final static Logger log = LoggerFactory.getLogger(StatusUpdateService.class);
	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	FinalOrderBeanMapper finalOrderBeanMapper;
	@Autowired
	FundAccountLogMapper fundAccountLogMapper;
	@Autowired
	FundAccountMapper fundAccountMapper;
	@Autowired
	FundWithdrawalMapper fundWithdrawalMapper;

	
	public List<OrderBean> getAllOrder(){
		List<String> ilist = new ArrayList<>();
		ilist.add("J15080502569");
		ilist.add("R15081909306");
		ilist.add("J15082703927");
		ilist.add("J15090506295");
		ilist.add("J15091109659");
		ilist.add("J15091908029");
		ilist.add("R15092109638");
		ilist.add("J15092909705");
		ilist.add("J15111205532");
		ilist.add("R15122001976");
		ilist.add("J15122205265");
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		orderBeanExample.createCriteria().andOrderNoIn(ilist).andOrderStatusEqualTo(OrderStatus.SETTLEMENT.value);
		List<OrderBean> blist = orderBeanMapper.selectByExample(orderBeanExample);
		return blist;
	}
	@Autowired
	LControllerService controllerService;
	public void print(OrderBean orderBean){
		GuideBean guideBean  = controllerService.getGuidByGuideId(orderBean.getGuideId());
		System.out.println(orderBean.getOrderNo() + "区号 【" + guideBean.getAreaCode() + "】导游电话【"+guideBean.getMobile()+"】" + "  订单金额为【" + orderBean.getPriceGuide() + "】导游名称为 【" + orderBean.getGuideMobile() + "】  导游名称为【"
				+ orderBean.getGuideNo() + "】导游姓名为【" + orderBean.getGuideName() + "】");
		log.info(orderBean.getOrderNo() + "区号 【" + guideBean.getAreaCode() + "】导游电话【"+guideBean.getMobile()+"】" + "  订单金额为【" + orderBean.getPriceGuide() + "】导游名称为 【" + orderBean.getGuideMobile() + "】  导游名称为【"
				+ orderBean.getGuideNo() + "】导游姓名为【" + orderBean.getGuideName() + "】");
	}
//	public void update(OrderBean orderBean) {
//		/**
//		 * J15080502569 已经由 被导游 【200000003233】提现【423.0】 amount 402 R15081909306
//		 * 已经由 被导游 【200100004484】提现【1851.0】 已提款 J15082703927 已经由 被导游
//		 * 【200100006038】提现【229.0】 已提款 J15090506295 已经由 被导游
//		 * 【200100006038】提现【199.0】已提款 J15091109659 已经由 被导游
//		 * 【200100004610】提现【576.0】 amount 2640 J15091908029 已经由 被导游
//		 * 【200100008896】提现【189.0】 amount 206 R15092109638 已经由 被导游
//		 * 【200000002113】提现【2206.0】 干掉 drawno = 914999282132 J15092909705 已经由
//		 * 被导游 【200100003768】提现【870.0】 已提款 J15111205532 已经由 被导游
//		 * 【200000001777】提现【270.0】 amount 1835 R15122001976 已经由 被导游
//		 * 【200000003666】提现【1800.0】 amount 6650 J15122205265 已经由 被导游
//		 * 【200100015089】提现【545.0】
//		 */
//
//
//		FundAccount fundAccount = fundAccountMapper.selectByPrimaryKey(orderBean.getGuideAccountNo());
//		Date date = TimeConverter.toDate("2015-12-23", TimeConverter.END_WITH_DATE);
//		if (fundAccount.getAmount() >= orderBean.getPriceGuide()) {
//			FundAccount record = new FundAccount();
//			record.setAccountNo(fundAccount.getAccountNo());
//			record.setAmount(DoubleUtil.subtractionDouble(fundAccount.getAmount(), orderBean.getPriceGuide()));
//
//			int optnum = fundAccountMapper.updateByPrimaryKeySelective(record);
//			log.info("帐号【" + fundAccount.getAccountNo() + "】 已经减去金额【" + orderBean.getPriceGuide() + "】...........");
//
//			if (optnum != 1) {
//				throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单号" + fundAccount.getAccountNo() + " 订单SN不存在");
//			}
//			FundAccountLogExample fundAccountLogExample = new FundAccountLogExample();
//			fundAccountLogExample.createCriteria().andAccountNoEqualTo(fundAccount.getAccountNo()).andChangAmountEqualTo(orderBean.getPriceGuide());
//			List<FundAccountLog> aslit = fundAccountLogMapper.selectByExample(fundAccountLogExample);
//
//			String logNo = null;
//			if (aslit.size() == 2) {
//				if (aslit.get(0).getCreateTime().before(aslit.get(1).getCreateTime())) {
//					if(aslit.get(1).getCreateTime().after(date)){
//						logNo = aslit.get(1).getLogNo();
//					}
//				} else {
//					if(aslit.get(0).getCreateTime().after(date)){
//						logNo = aslit.get(0).getLogNo();
//					}
//				}
//				
//				if(logNo!=null){
//					optnum = fundAccountLogMapper.deleteByPrimaryKey(logNo);
//					if (optnum != 1) {
//						throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, logNo + " 已经删除");
//					}else{
//						log.info("导游"+orderBean.getGuideName()+" 【"+orderBean.getGuideAccountNo()+"】 【"+orderBean.getPriceGuide()+"】成功");
//						System.out.println("导游"+orderBean.getGuideName()+" 【"+orderBean.getGuideAccountNo()+"】 【"+orderBean.getPriceGuide()+"】成功");
//					}
//				}else{
//					throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "资金帐号 无法找到 对应流水" + fundAccount.getAccountNo());
//				}
//			}else{
//				log.info("导游"+orderBean.getGuideName()+" 【"+orderBean.getGuideAccountNo()+"】 【"+orderBean.getPriceGuide()+"】失败");
//				System.out.println("导游"+orderBean.getGuideName()+" 【"+orderBean.getGuideAccountNo()+"】 【"+orderBean.getPriceGuide()+"】失败");
//			}
//		} else {
//			FundWithdrawalExample fundWithdrawalExample = new FundWithdrawalExample();
//			fundWithdrawalExample.createCriteria().andGuideIdEqualTo(orderBean.getGuideId())
//			.andCreateTimeGreaterThan(date);
//			List<FundWithdrawal> flist = fundWithdrawalMapper.selectByExample(fundWithdrawalExample);
//			
//			boolean isT = false;
//			for(FundWithdrawal fundWithdrawal : flist){
//				if(fundWithdrawal.getPrice().equals(orderBean.getPriceGuide())){
//					if(fundWithdrawal.getDrawStatus()!=1){
//						FundWithdrawal fwd = new FundWithdrawal();
//						fwd.setDrawStatus(FundDrawStatus.DELETE.value);
//						fwd.setDrawNo(fundWithdrawal.getDrawNo());
//						fwd.setDrawComment("系统迁移故障，该笔交易额直接删除");
//						log.info("开始逻辑删除 withdraw..........."+JSON.toJSONString(fundWithdrawal));
//						System.out.println("开始逻辑删除 withdraw..........."+JSON.toJSONString(fundWithdrawal));
//						int optnum = fundWithdrawalMapper.updateByPrimaryKeySelective(fwd);
//						if (optnum != 1) {
//							throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, fundWithdrawal.getDrawNo() + " 删除失败");
//						}else{
//							log.info("删除 withdraw..........."+JSON.toJSONString(fundWithdrawal)+"成功");
//							System.out.println("删除 withdraw..........."+JSON.toJSONString(fundWithdrawal)+"成功");
//							isT = true;
//							break;
//						}
//					}
//				}
//			}
//			
//			if(isT){
//				log.info("导游"+orderBean.getGuideName()+" 【"+orderBean.getGuideAccountNo()+"】 【"+orderBean.getPriceGuide()+"】成功");
//				System.out.println("导游"+orderBean.getGuideName()+" 【"+orderBean.getGuideAccountNo()+"】 【"+orderBean.getPriceGuide()+"】成功");
//			}
//		}
//	}
}
