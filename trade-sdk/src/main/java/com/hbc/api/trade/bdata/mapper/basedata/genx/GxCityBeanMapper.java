/**
 * @Author lukangle
 * @2015年11月13日@下午6:31:34
 */
package com.hbc.api.trade.bdata.mapper.basedata.genx;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBean;

public interface GxCityBeanMapper {
	@Select("select * from basedata_cities")
	List<CityBean> getAllCitys();
}
