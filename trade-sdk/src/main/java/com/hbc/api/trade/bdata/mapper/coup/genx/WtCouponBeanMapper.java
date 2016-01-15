/**
 * @Author lukangle
 * @2015年11月9日@上午10:16:29
 */
package com.hbc.api.trade.bdata.mapper.coup.genx;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface WtCouponBeanMapper {
	@Update({
        "update coupon",
        "set ",
          "status = ${status}",
        "where coupon_id = ${couponId} and status=${sstatus}"
    })
    int updateStatusByPrimaryKey(@Param("couponId") String couponId,@Param("sstatus") int sstatus,@Param("status") int status);
}
