package com.hbc.api.trade.pay.mapping.gen;

import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePaymentExample.Criteria;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePaymentExample.Criterion;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePaymentExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class TradePaymentSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_payment`
     *
     * @mbggenerated
     */
    public String countByExample(TradePaymentExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("`trade_payment`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_payment`
     *
     * @mbggenerated
     */
    public String deleteByExample(TradePaymentExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("`trade_payment`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_payment`
     *
     * @mbggenerated
     */
    public String insertSelective(TradePayment record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`trade_payment`");
        
        if (record.getPayNo() != null) {
            sql.VALUES("pay_no", "#{payNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderNo() != null) {
            sql.VALUES("order_no", "#{orderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getPayGetway() != null) {
            sql.VALUES("pay_getway", "#{payGetway,jdbcType=INTEGER}");
        }
        
        if (record.getPayGatewayName() != null) {
            sql.VALUES("pay_gateway_name", "#{payGatewayName,jdbcType=VARCHAR}");
        }
        
        if (record.getPayStatus() != null) {
            sql.VALUES("pay_status", "#{payStatus,jdbcType=INTEGER}");
        }
        
        if (record.getOrderPrice() != null) {
            sql.VALUES("order_price", "#{orderPrice,jdbcType=DOUBLE}");
        }
        
        if (record.getCoupId() != null) {
            sql.VALUES("coup_id", "#{coupId,jdbcType=VARCHAR}");
        }
        
        if (record.getCoupPay() != null) {
            sql.VALUES("coup_pay", "#{coupPay,jdbcType=DOUBLE}");
        }
        
        if (record.getCouponInfo() != null) {
            sql.VALUES("coupon_info", "#{couponInfo,jdbcType=VARCHAR}");
        }
        
        if (record.getPayShould() != null) {
            sql.VALUES("pay_should", "#{payShould,jdbcType=DOUBLE}");
        }
        
        if (record.getPayActual() != null) {
            sql.VALUES("pay_actual", "#{payActual,jdbcType=DOUBLE}");
        }
        
        if (record.getPayFee() != null) {
            sql.VALUES("pay_fee", "#{payFee,jdbcType=DOUBLE}");
        }
        
        if (record.getPaySource() != null) {
            sql.VALUES("pay_source", "#{paySource,jdbcType=INTEGER}");
        }
        
        if (record.getPayTime() != null) {
            sql.VALUES("pay_time", "#{payTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOrderExpireTime() != null) {
            sql.VALUES("order_expire_time", "#{orderExpireTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOrderCreateTime() != null) {
            sql.VALUES("order_create_time", "#{orderCreateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserAccountNo() != null) {
            sql.VALUES("user_account_no", "#{userAccountNo,jdbcType=VARCHAR}");
        }
        
        if (record.getUserPayAccount() != null) {
            sql.VALUES("user_pay_account", "#{userPayAccount,jdbcType=VARCHAR}");
        }
        
        if (record.getUserMobile() != null) {
            sql.VALUES("user_mobile", "#{userMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=VARCHAR}");
        }
        
        if (record.getUserName() != null) {
            sql.VALUES("user_name", "#{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getPaySubject() != null) {
            sql.VALUES("pay_subject", "#{paySubject,jdbcType=VARCHAR}");
        }
        
        if (record.getPayDesc() != null) {
            sql.VALUES("pay_desc", "#{payDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getThirdPayNo() != null) {
            sql.VALUES("third_pay_no", "#{thirdPayNo,jdbcType=VARCHAR}");
        }
        
        if (record.getThirdNotifyStatus() != null) {
            sql.VALUES("third_notify_status", "#{thirdNotifyStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getThirdNotifyLog() != null) {
            sql.VALUES("third_notify_log", "#{thirdNotifyLog,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_payment`
     *
     * @mbggenerated
     */
    public String selectByExampleWithBLOBs(TradePaymentExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("pay_no");
        } else {
            sql.SELECT("pay_no");
        }
        sql.SELECT("order_no");
        sql.SELECT("pay_getway");
        sql.SELECT("pay_gateway_name");
        sql.SELECT("pay_status");
        sql.SELECT("order_price");
        sql.SELECT("coup_id");
        sql.SELECT("coup_pay");
        sql.SELECT("coupon_info");
        sql.SELECT("pay_should");
        sql.SELECT("pay_actual");
        sql.SELECT("pay_fee");
        sql.SELECT("pay_source");
        sql.SELECT("pay_time");
        sql.SELECT("order_expire_time");
        sql.SELECT("order_create_time");
        sql.SELECT("user_account_no");
        sql.SELECT("user_pay_account");
        sql.SELECT("user_mobile");
        sql.SELECT("user_id");
        sql.SELECT("user_name");
        sql.SELECT("pay_subject");
        sql.SELECT("pay_desc");
        sql.SELECT("third_pay_no");
        sql.SELECT("third_notify_status");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
        sql.SELECT("third_notify_log");
        sql.FROM("`trade_payment`");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
         String sqlStr = sql.toString();
        if(example.getPage()!=null){
            sqlStr = sqlStr+" limit "+example.getPage().getOffset()+","+example.getPage().getLimit()+"";
             }
            return sqlStr;
        }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_payment`
     *
     * @mbggenerated
     */
    public String selectByExample(TradePaymentExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("pay_no");
        } else {
            sql.SELECT("pay_no");
        }
        sql.SELECT("order_no");
        sql.SELECT("pay_getway");
        sql.SELECT("pay_gateway_name");
        sql.SELECT("pay_status");
        sql.SELECT("order_price");
        sql.SELECT("coup_id");
        sql.SELECT("coup_pay");
        sql.SELECT("coupon_info");
        sql.SELECT("pay_should");
        sql.SELECT("pay_actual");
        sql.SELECT("pay_fee");
        sql.SELECT("pay_source");
        sql.SELECT("pay_time");
        sql.SELECT("order_expire_time");
        sql.SELECT("order_create_time");
        sql.SELECT("user_account_no");
        sql.SELECT("user_pay_account");
        sql.SELECT("user_mobile");
        sql.SELECT("user_id");
        sql.SELECT("user_name");
        sql.SELECT("pay_subject");
        sql.SELECT("pay_desc");
        sql.SELECT("third_pay_no");
        sql.SELECT("third_notify_status");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
        sql.FROM("`trade_payment`");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
         String sqlStr = sql.toString();
        if(example.getPage()!=null){
            sqlStr = sqlStr+" limit "+example.getPage().getOffset()+","+example.getPage().getLimit()+"";
             }
            return sqlStr;
        }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_payment`
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        TradePayment record = (TradePayment) parameter.get("record");
        TradePaymentExample example = (TradePaymentExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("`trade_payment`");
        
        if (record.getPayNo() != null) {
            sql.SET("pay_no = #{record.payNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderNo() != null) {
            sql.SET("order_no = #{record.orderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getPayGetway() != null) {
            sql.SET("pay_getway = #{record.payGetway,jdbcType=INTEGER}");
        }
        
        if (record.getPayGatewayName() != null) {
            sql.SET("pay_gateway_name = #{record.payGatewayName,jdbcType=VARCHAR}");
        }
        
        if (record.getPayStatus() != null) {
            sql.SET("pay_status = #{record.payStatus,jdbcType=INTEGER}");
        }
        
        if (record.getOrderPrice() != null) {
            sql.SET("order_price = #{record.orderPrice,jdbcType=DOUBLE}");
        }
        
        if (record.getCoupId() != null) {
            sql.SET("coup_id = #{record.coupId,jdbcType=VARCHAR}");
        }
        
        if (record.getCoupPay() != null) {
            sql.SET("coup_pay = #{record.coupPay,jdbcType=DOUBLE}");
        }
        
        if (record.getCouponInfo() != null) {
            sql.SET("coupon_info = #{record.couponInfo,jdbcType=VARCHAR}");
        }
        
        if (record.getPayShould() != null) {
            sql.SET("pay_should = #{record.payShould,jdbcType=DOUBLE}");
        }
        
        if (record.getPayActual() != null) {
            sql.SET("pay_actual = #{record.payActual,jdbcType=DOUBLE}");
        }
        
        if (record.getPayFee() != null) {
            sql.SET("pay_fee = #{record.payFee,jdbcType=DOUBLE}");
        }
        
        if (record.getPaySource() != null) {
            sql.SET("pay_source = #{record.paySource,jdbcType=INTEGER}");
        }
        
        if (record.getPayTime() != null) {
            sql.SET("pay_time = #{record.payTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOrderExpireTime() != null) {
            sql.SET("order_expire_time = #{record.orderExpireTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOrderCreateTime() != null) {
            sql.SET("order_create_time = #{record.orderCreateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserAccountNo() != null) {
            sql.SET("user_account_no = #{record.userAccountNo,jdbcType=VARCHAR}");
        }
        
        if (record.getUserPayAccount() != null) {
            sql.SET("user_pay_account = #{record.userPayAccount,jdbcType=VARCHAR}");
        }
        
        if (record.getUserMobile() != null) {
            sql.SET("user_mobile = #{record.userMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{record.userId,jdbcType=VARCHAR}");
        }
        
        if (record.getUserName() != null) {
            sql.SET("user_name = #{record.userName,jdbcType=VARCHAR}");
        }
        
        if (record.getPaySubject() != null) {
            sql.SET("pay_subject = #{record.paySubject,jdbcType=VARCHAR}");
        }
        
        if (record.getPayDesc() != null) {
            sql.SET("pay_desc = #{record.payDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getThirdPayNo() != null) {
            sql.SET("third_pay_no = #{record.thirdPayNo,jdbcType=VARCHAR}");
        }
        
        if (record.getThirdNotifyStatus() != null) {
            sql.SET("third_notify_status = #{record.thirdNotifyStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getThirdNotifyLog() != null) {
            sql.SET("third_notify_log = #{record.thirdNotifyLog,jdbcType=LONGVARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_payment`
     *
     * @mbggenerated
     */
    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("`trade_payment`");
        
        sql.SET("pay_no = #{record.payNo,jdbcType=VARCHAR}");
        sql.SET("order_no = #{record.orderNo,jdbcType=VARCHAR}");
        sql.SET("pay_getway = #{record.payGetway,jdbcType=INTEGER}");
        sql.SET("pay_gateway_name = #{record.payGatewayName,jdbcType=VARCHAR}");
        sql.SET("pay_status = #{record.payStatus,jdbcType=INTEGER}");
        sql.SET("order_price = #{record.orderPrice,jdbcType=DOUBLE}");
        sql.SET("coup_id = #{record.coupId,jdbcType=VARCHAR}");
        sql.SET("coup_pay = #{record.coupPay,jdbcType=DOUBLE}");
        sql.SET("coupon_info = #{record.couponInfo,jdbcType=VARCHAR}");
        sql.SET("pay_should = #{record.payShould,jdbcType=DOUBLE}");
        sql.SET("pay_actual = #{record.payActual,jdbcType=DOUBLE}");
        sql.SET("pay_fee = #{record.payFee,jdbcType=DOUBLE}");
        sql.SET("pay_source = #{record.paySource,jdbcType=INTEGER}");
        sql.SET("pay_time = #{record.payTime,jdbcType=TIMESTAMP}");
        sql.SET("order_expire_time = #{record.orderExpireTime,jdbcType=TIMESTAMP}");
        sql.SET("order_create_time = #{record.orderCreateTime,jdbcType=TIMESTAMP}");
        sql.SET("user_account_no = #{record.userAccountNo,jdbcType=VARCHAR}");
        sql.SET("user_pay_account = #{record.userPayAccount,jdbcType=VARCHAR}");
        sql.SET("user_mobile = #{record.userMobile,jdbcType=VARCHAR}");
        sql.SET("user_id = #{record.userId,jdbcType=VARCHAR}");
        sql.SET("user_name = #{record.userName,jdbcType=VARCHAR}");
        sql.SET("pay_subject = #{record.paySubject,jdbcType=VARCHAR}");
        sql.SET("pay_desc = #{record.payDesc,jdbcType=VARCHAR}");
        sql.SET("third_pay_no = #{record.thirdPayNo,jdbcType=VARCHAR}");
        sql.SET("third_notify_status = #{record.thirdNotifyStatus,jdbcType=VARCHAR}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("third_notify_log = #{record.thirdNotifyLog,jdbcType=LONGVARCHAR}");
        
        TradePaymentExample example = (TradePaymentExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_payment`
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("`trade_payment`");
        
        sql.SET("pay_no = #{record.payNo,jdbcType=VARCHAR}");
        sql.SET("order_no = #{record.orderNo,jdbcType=VARCHAR}");
        sql.SET("pay_getway = #{record.payGetway,jdbcType=INTEGER}");
        sql.SET("pay_gateway_name = #{record.payGatewayName,jdbcType=VARCHAR}");
        sql.SET("pay_status = #{record.payStatus,jdbcType=INTEGER}");
        sql.SET("order_price = #{record.orderPrice,jdbcType=DOUBLE}");
        sql.SET("coup_id = #{record.coupId,jdbcType=VARCHAR}");
        sql.SET("coup_pay = #{record.coupPay,jdbcType=DOUBLE}");
        sql.SET("coupon_info = #{record.couponInfo,jdbcType=VARCHAR}");
        sql.SET("pay_should = #{record.payShould,jdbcType=DOUBLE}");
        sql.SET("pay_actual = #{record.payActual,jdbcType=DOUBLE}");
        sql.SET("pay_fee = #{record.payFee,jdbcType=DOUBLE}");
        sql.SET("pay_source = #{record.paySource,jdbcType=INTEGER}");
        sql.SET("pay_time = #{record.payTime,jdbcType=TIMESTAMP}");
        sql.SET("order_expire_time = #{record.orderExpireTime,jdbcType=TIMESTAMP}");
        sql.SET("order_create_time = #{record.orderCreateTime,jdbcType=TIMESTAMP}");
        sql.SET("user_account_no = #{record.userAccountNo,jdbcType=VARCHAR}");
        sql.SET("user_pay_account = #{record.userPayAccount,jdbcType=VARCHAR}");
        sql.SET("user_mobile = #{record.userMobile,jdbcType=VARCHAR}");
        sql.SET("user_id = #{record.userId,jdbcType=VARCHAR}");
        sql.SET("user_name = #{record.userName,jdbcType=VARCHAR}");
        sql.SET("pay_subject = #{record.paySubject,jdbcType=VARCHAR}");
        sql.SET("pay_desc = #{record.payDesc,jdbcType=VARCHAR}");
        sql.SET("third_pay_no = #{record.thirdPayNo,jdbcType=VARCHAR}");
        sql.SET("third_notify_status = #{record.thirdNotifyStatus,jdbcType=VARCHAR}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        
        TradePaymentExample example = (TradePaymentExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_payment`
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(TradePayment record) {
        SQL sql = new SQL();
        sql.UPDATE("`trade_payment`");
        
        if (record.getOrderNo() != null) {
            sql.SET("order_no = #{orderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getPayGetway() != null) {
            sql.SET("pay_getway = #{payGetway,jdbcType=INTEGER}");
        }
        
        if (record.getPayGatewayName() != null) {
            sql.SET("pay_gateway_name = #{payGatewayName,jdbcType=VARCHAR}");
        }
        
        if (record.getPayStatus() != null) {
            sql.SET("pay_status = #{payStatus,jdbcType=INTEGER}");
        }
        
        if (record.getOrderPrice() != null) {
            sql.SET("order_price = #{orderPrice,jdbcType=DOUBLE}");
        }
        
        if (record.getCoupId() != null) {
            sql.SET("coup_id = #{coupId,jdbcType=VARCHAR}");
        }
        
        if (record.getCoupPay() != null) {
            sql.SET("coup_pay = #{coupPay,jdbcType=DOUBLE}");
        }
        
        if (record.getCouponInfo() != null) {
            sql.SET("coupon_info = #{couponInfo,jdbcType=VARCHAR}");
        }
        
        if (record.getPayShould() != null) {
            sql.SET("pay_should = #{payShould,jdbcType=DOUBLE}");
        }
        
        if (record.getPayActual() != null) {
            sql.SET("pay_actual = #{payActual,jdbcType=DOUBLE}");
        }
        
        if (record.getPayFee() != null) {
            sql.SET("pay_fee = #{payFee,jdbcType=DOUBLE}");
        }
        
        if (record.getPaySource() != null) {
            sql.SET("pay_source = #{paySource,jdbcType=INTEGER}");
        }
        
        if (record.getPayTime() != null) {
            sql.SET("pay_time = #{payTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOrderExpireTime() != null) {
            sql.SET("order_expire_time = #{orderExpireTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOrderCreateTime() != null) {
            sql.SET("order_create_time = #{orderCreateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserAccountNo() != null) {
            sql.SET("user_account_no = #{userAccountNo,jdbcType=VARCHAR}");
        }
        
        if (record.getUserPayAccount() != null) {
            sql.SET("user_pay_account = #{userPayAccount,jdbcType=VARCHAR}");
        }
        
        if (record.getUserMobile() != null) {
            sql.SET("user_mobile = #{userMobile,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=VARCHAR}");
        }
        
        if (record.getUserName() != null) {
            sql.SET("user_name = #{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getPaySubject() != null) {
            sql.SET("pay_subject = #{paySubject,jdbcType=VARCHAR}");
        }
        
        if (record.getPayDesc() != null) {
            sql.SET("pay_desc = #{payDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getThirdPayNo() != null) {
            sql.SET("third_pay_no = #{thirdPayNo,jdbcType=VARCHAR}");
        }
        
        if (record.getThirdNotifyStatus() != null) {
            sql.SET("third_notify_status = #{thirdNotifyStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getThirdNotifyLog() != null) {
            sql.SET("third_notify_log = #{thirdNotifyLog,jdbcType=LONGVARCHAR}");
        }
        
        sql.WHERE("pay_no = #{payNo,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_payment`
     *
     * @mbggenerated
     */
    protected void applyWhere(SQL sql, TradePaymentExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}