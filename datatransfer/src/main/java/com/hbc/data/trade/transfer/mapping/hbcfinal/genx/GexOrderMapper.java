package com.hbc.data.trade.transfer.mapping.hbcfinal.genx;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;

public interface GexOrderMapper {
	@Select("select* from `order` limit ${offset}, ${limit} ")
	List<FinalOrderBean> selectOrder(@Param("offset") int offset,@Param("limit") int limit);
}
