/**
 * @Author lukangle
 * @2015年11月29日@上午11:29:35
 */
package com.hbc.api.trade.third;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.common.cache.CacheFinal;
import com.hbc.api.trade.bdata.mapper.basedata.gen.CarMapper;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.Car;

@Component
public class CarService {
	@Autowired
	CarMapper carmapper;
	public Car getCar(Integer carId){
		return carmapper.selectByPrimaryKey(carId);
	}
}
