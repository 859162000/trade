package com.hbc.data.trade.transfer.service.hfinal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.data.trade.transfer.enums.hfinal.FOrderStatus;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderBeanMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBeanCriteria;
import com.hbc.data.trade.transfer.mapping.hbcfinal.genx.GexOrderMapper;
import com.hbc.data.trade.transfer.util.Page;

@Component
public class FOrderService {
	Logger log = Logger.getLogger(FOrderService.class);
	@Autowired
	FinalOrderBeanMapper finalOrderBeanMapper;
	@Autowired
	GexOrderMapper gexOrderMapper;

	public List<FinalOrderBean> getIncreaseFinalOrderBean(int limit, int offset) {
		FinalOrderBeanCriteria finalOrderBeanCriteria = new FinalOrderBeanCriteria();

		Page page = new Page(offset, limit);
		finalOrderBeanCriteria.setPage(page);
		List<String> orderIdList = new ArrayList<String>();
		orderIdList.add("150919005228744857");
		orderIdList.add("150924112655185750");
//		orderIdList.add("150908005829748918");
//		orderIdList.add("150908005842749638");
//		orderIdList.add("150908005844749519");
//		orderIdList.add("150908005846749472");
//		orderIdList.add("150908005846749631");
//		orderIdList.add("150908005904749425");
//		orderIdList.add("150908005912749663");
//		orderIdList.add("150908005916749902");
//		orderIdList.add("150908005929749645");
//		orderIdList.add("150428100828130150");
//		orderIdList.add("150512113838193138");
//		orderIdList.add("150514140630296540");
//		orderIdList.add("150519111756179219");
//		orderIdList.add("150520124137237329");
//		orderIdList.add("150521115024201120");
//		orderIdList.add("150526131101257809");
//		orderIdList.add("150528114728199743");
//		orderIdList.add("150610102107139840");
//		orderIdList.add("150610102402141729");
//		orderIdList.add("150115214640615612");
//		orderIdList.add("150127175107452670");
//		orderIdList.add("150128160527378519");
//		orderIdList.add("150128161841387992");
//		orderIdList.add("150128173818443255");
//		orderIdList.add("150130103520149747");
//		orderIdList.add("150202092939103831");
//		orderIdList.add("150202134206279523");
//		orderIdList.add("150202175925457309");
//		orderIdList.add("150203164246404235");
//		orderIdList.add("150213144656324556");
//		orderIdList.add("150213145043326240");
//		orderIdList.add("150215102736144171");
//		orderIdList.add("150215103759151507");
//		orderIdList.add("150215161938388836");
//		orderIdList.add("150215163607400928");
//		orderIdList.add("150215164106403300");
//		orderIdList.add("150216150935339505");
//		orderIdList.add("150225133140271149");
//		orderIdList.add("150303192526517857");
//		orderIdList.add("150612174438447689");
//		orderIdList.add("150613114028194869");
//		orderIdList.add("150615211332592357");
//		orderIdList.add("150623084041069947");
//		orderIdList.add("150703100110125111");
//		orderIdList.add("150703221315634354");
//		orderIdList.add("150704115333203456");
//		orderIdList.add("150713195306536831");
//		orderIdList.add("150714212755602562");
//		orderIdList.add("150714230421669120");
//		orderIdList.add("151120211834596659");
//		orderIdList.add("151120221355634871");
//		orderIdList.add("151120221915638646");
//		orderIdList.add("151120223249647875");
//		orderIdList.add("151120225407662572");
//		orderIdList.add("151120225845665391");
//		orderIdList.add("151120230826672152");
//		orderIdList.add("151120231735678583");
//		orderIdList.add("151120231738678595");
//		orderIdList.add("151120232940687878");
//		orderIdList.add("150421213744609141");
//		orderIdList.add("150422090947090387");
//		orderIdList.add("150423131550260122");
//		orderIdList.add("150423134908284392");
//		orderIdList.add("150423135906291222");
//		orderIdList.add("150423140929298805");
//		orderIdList.add("150423142928312795");
//		orderIdList.add("150427171820429459");
//		orderIdList.add("150427171956430276");
//		orderIdList.add("150427174347447212");
//		orderIdList.add("150522210901589991");
//		orderIdList.add("150523202126556161");
//		orderIdList.add("150528101452135721");
//		orderIdList.add("150619092839103971");
//		orderIdList.add("150622164259404601");
//		orderIdList.add("150625000816714444");
//		orderIdList.add("150713173734442562");
//		orderIdList.add("150802225726664783");
//		orderIdList.add("150810132903270676");
//		orderIdList.add("150813172958437345");
//		orderIdList.add("150423132915270369");
//		orderIdList.add("150814184848492147");
//		orderIdList.add("150815125629247132");
//		orderIdList.add("150914053155664358");
//		orderIdList.add("151018075208036416");
//		orderIdList.add("151021203955569544");
//		orderIdList.add("151104145202327153");
//		orderIdList.add("151106184801491378");
//		orderIdList.add("151106231429676180");
//		orderIdList.add("151109103529149487");

		
//		orderIdList.add("150125210934589693");
//		finalOrderBeanCriteria.createCriteria().andOrderidIn(orderIdList);

		FinalOrderBeanCriteria.Criteria criteria  = finalOrderBeanCriteria.createCriteria();
		List<Integer> list = new ArrayList<Integer>();
		list.add(FOrderStatus.CANCEL_BYUSER.value); 
		list.add(FOrderStatus.CANCEL_BYGUIDE.value);
		list.add(FOrderStatus.SUCCESS.value);
		criteria.andStatusNotIn(list);
		
		List<FinalOrderBean> orders = finalOrderBeanMapper.selectByExample(finalOrderBeanCriteria);

		return orders;
	}

	
	public List<FinalOrderBean> getAllFinalOrderBean(int limit, int offset) {
		FinalOrderBeanCriteria finalOrderBeanCriteria = new FinalOrderBeanCriteria();
		Page page = new Page(offset, limit);
		finalOrderBeanCriteria.setPage(page);
		List<FinalOrderBean> orders = finalOrderBeanMapper.selectByExample(finalOrderBeanCriteria);
		return orders;
	}
	
	public int countAllFinalOrders() {
		FinalOrderBeanCriteria finalOrderBeanCriteria = new FinalOrderBeanCriteria();
		return finalOrderBeanMapper.countByExample(finalOrderBeanCriteria);
	}
	
	public int countIncreaseFinalOrders() {
		FinalOrderBeanCriteria finalOrderBeanCriteria = new FinalOrderBeanCriteria();
		FinalOrderBeanCriteria.Criteria criteria  = finalOrderBeanCriteria.createCriteria();
		List<Integer> list = new ArrayList<Integer>();
		list.add(FOrderStatus.CANCEL_BYUSER.value); 
		list.add(FOrderStatus.CANCEL_BYGUIDE.value);
		list.add(FOrderStatus.SUCCESS.value);
		criteria.andStatusNotIn(list);
		return finalOrderBeanMapper.countByExample(finalOrderBeanCriteria);
	}

	
	public List<FinalOrderBean> getCompleteFinalOrderBean(int limit, int offset) {
		FinalOrderBeanCriteria finalOrderBeanCriteria = new FinalOrderBeanCriteria();
		FinalOrderBeanCriteria.Criteria criteria  = finalOrderBeanCriteria.createCriteria();
		List<Integer> list = new ArrayList<Integer>();
		list.add(FOrderStatus.CANCEL_BYUSER.value); 
		list.add(FOrderStatus.CANCEL_BYGUIDE.value);
		list.add(FOrderStatus.SUCCESS.value);
		criteria.andStatusIn(list);

		Page page = new Page(offset, limit);
		finalOrderBeanCriteria.setPage(page);
		
		List<FinalOrderBean> orders = finalOrderBeanMapper.selectByExample(finalOrderBeanCriteria);

		return orders;
	}
	
	public List<FinalOrderBean> getCompleteTMPFinalOrderBean(int limit, int offset) {
		FinalOrderBeanCriteria finalOrderBeanCriteria = new FinalOrderBeanCriteria();
		FinalOrderBeanCriteria.Criteria criteria  = finalOrderBeanCriteria.createCriteria();
		List<Integer> list = new ArrayList<Integer>();
		list.add(2); 
		list.add(3);
		list.add(4);
		list.add(5);
		criteria.andStatusIn(list);
		criteria.andOrdertypeEqualTo(3);
		Page page = new Page(offset, limit);
		finalOrderBeanCriteria.setPage(page);
		
		List<FinalOrderBean> orders = finalOrderBeanMapper.selectByExample(finalOrderBeanCriteria);

		return orders;
	}
	
	public int countCompleteFinalOrders() {
		FinalOrderBeanCriteria finalOrderBeanCriteria = new FinalOrderBeanCriteria();
		FinalOrderBeanCriteria.Criteria criteria  = finalOrderBeanCriteria.createCriteria();
		List<Integer> list = new ArrayList<Integer>();
		list.add(FOrderStatus.CANCEL_BYUSER.value); 
		list.add(FOrderStatus.CANCEL_BYGUIDE.value);
		list.add(FOrderStatus.SUCCESS.value);
		criteria.andStatusIn(list);
		
		return finalOrderBeanMapper.countByExample(finalOrderBeanCriteria);
	}
}
