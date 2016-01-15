package com.hbc.data.trade.transfer.mapping.pay.gen;

import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayConsumeOrder;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayConsumeOrderCriteria.Criteria;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayConsumeOrderCriteria.Criterion;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayConsumeOrderCriteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class PayConsumeOrderSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_consume_order`
     *
     * @mbggenerated
     */
    public String countByExample(PayConsumeOrderCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("`pay_consume_order`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_consume_order`
     *
     * @mbggenerated
     */
    public String deleteByExample(PayConsumeOrderCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("`pay_consume_order`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_consume_order`
     *
     * @mbggenerated
     */
    public String insertSelective(PayConsumeOrder record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`pay_consume_order`");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountId() != null) {
            sql.VALUES("account_id", "#{accountId,jdbcType=VARCHAR}");
        }
        
        if (record.getChannel() != null) {
            sql.VALUES("channel", "#{channel,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=INTEGER}");
        }
        
        if (record.getPayTime() != null) {
            sql.VALUES("pay_time", "#{payTime,jdbcType=INTEGER}");
        }
        
        if (record.getRechargeId() != null) {
            sql.VALUES("recharge_id", "#{rechargeId,jdbcType=VARCHAR}");
        }
        
        if (record.getConsumeAmount() != null) {
            sql.VALUES("consume_amount", "#{consumeAmount,jdbcType=INTEGER}");
        }
        
        if (record.getRechargeAmount() != null) {
            sql.VALUES("recharge_amount", "#{rechargeAmount,jdbcType=INTEGER}");
        }
        
        if (record.getSubject() != null) {
            sql.VALUES("subject", "#{subject,jdbcType=VARCHAR}");
        }
        
        if (record.getBody() != null) {
            sql.VALUES("body", "#{body,jdbcType=VARCHAR}");
        }
        
        if (record.getBusiConsumeNo() != null) {
            sql.VALUES("busi_consume_no", "#{busiConsumeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getBusiShowUrl() != null) {
            sql.VALUES("busi_show_url", "#{busiShowUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCityId() != null) {
            sql.VALUES("city_id", "#{cityId,jdbcType=INTEGER}");
        }
        
        if (record.getRefundCount() != null) {
            sql.VALUES("refund_count", "#{refundCount,jdbcType=INTEGER}");
        }
        
        if (record.getExpireTime() != null) {
            sql.VALUES("expire_time", "#{expireTime,jdbcType=INTEGER}");
        }
        
        if (record.getRefundAmount() != null) {
            sql.VALUES("refund_amount", "#{refundAmount,jdbcType=INTEGER}");
        }
        
        if (record.getRefundTime() != null) {
            sql.VALUES("refund_time", "#{refundTime,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=INTEGER}");
        }
        
        if (record.getPlat() != null) {
            sql.VALUES("plat", "#{plat,jdbcType=INTEGER}");
        }
        
        if (record.getPlatExt() != null) {
            sql.VALUES("plat_ext", "#{platExt,jdbcType=VARCHAR}");
        }
        
        if (record.getReturnUrl() != null) {
            sql.VALUES("return_url", "#{returnUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCallbackUrl() != null) {
            sql.VALUES("callback_url", "#{callbackUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCallbackStatus() != null) {
            sql.VALUES("callback_status", "#{callbackStatus,jdbcType=INTEGER}");
        }
        
        if (record.getCallbackCount() != null) {
            sql.VALUES("callback_count", "#{callbackCount,jdbcType=INTEGER}");
        }
        
        if (record.getCallbackTime() != null) {
            sql.VALUES("callback_time", "#{callbackTime,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_consume_order`
     *
     * @mbggenerated
     */
    public String selectByExample(PayConsumeOrderCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("user_id");
        sql.SELECT("account_id");
        sql.SELECT("channel");
        sql.SELECT("status");
        sql.SELECT("create_time");
        sql.SELECT("pay_time");
        sql.SELECT("recharge_id");
        sql.SELECT("consume_amount");
        sql.SELECT("recharge_amount");
        sql.SELECT("subject");
        sql.SELECT("body");
        sql.SELECT("busi_consume_no");
        sql.SELECT("busi_show_url");
        sql.SELECT("city_id");
        sql.SELECT("refund_count");
        sql.SELECT("expire_time");
        sql.SELECT("refund_amount");
        sql.SELECT("refund_time");
        sql.SELECT("update_time");
        sql.SELECT("plat");
        sql.SELECT("plat_ext");
        sql.SELECT("return_url");
        sql.SELECT("callback_url");
        sql.SELECT("callback_status");
        sql.SELECT("callback_count");
        sql.SELECT("callback_time");
        sql.FROM("`pay_consume_order`");
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
     * This method corresponds to the database table `pay_consume_order`
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        PayConsumeOrder record = (PayConsumeOrder) parameter.get("record");
        PayConsumeOrderCriteria example = (PayConsumeOrderCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("`pay_consume_order`");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{record.userId,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountId() != null) {
            sql.SET("account_id = #{record.accountId,jdbcType=VARCHAR}");
        }
        
        if (record.getChannel() != null) {
            sql.SET("channel = #{record.channel,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=INTEGER}");
        }
        
        if (record.getPayTime() != null) {
            sql.SET("pay_time = #{record.payTime,jdbcType=INTEGER}");
        }
        
        if (record.getRechargeId() != null) {
            sql.SET("recharge_id = #{record.rechargeId,jdbcType=VARCHAR}");
        }
        
        if (record.getConsumeAmount() != null) {
            sql.SET("consume_amount = #{record.consumeAmount,jdbcType=INTEGER}");
        }
        
        if (record.getRechargeAmount() != null) {
            sql.SET("recharge_amount = #{record.rechargeAmount,jdbcType=INTEGER}");
        }
        
        if (record.getSubject() != null) {
            sql.SET("subject = #{record.subject,jdbcType=VARCHAR}");
        }
        
        if (record.getBody() != null) {
            sql.SET("body = #{record.body,jdbcType=VARCHAR}");
        }
        
        if (record.getBusiConsumeNo() != null) {
            sql.SET("busi_consume_no = #{record.busiConsumeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getBusiShowUrl() != null) {
            sql.SET("busi_show_url = #{record.busiShowUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCityId() != null) {
            sql.SET("city_id = #{record.cityId,jdbcType=INTEGER}");
        }
        
        if (record.getRefundCount() != null) {
            sql.SET("refund_count = #{record.refundCount,jdbcType=INTEGER}");
        }
        
        if (record.getExpireTime() != null) {
            sql.SET("expire_time = #{record.expireTime,jdbcType=INTEGER}");
        }
        
        if (record.getRefundAmount() != null) {
            sql.SET("refund_amount = #{record.refundAmount,jdbcType=INTEGER}");
        }
        
        if (record.getRefundTime() != null) {
            sql.SET("refund_time = #{record.refundTime,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=INTEGER}");
        }
        
        if (record.getPlat() != null) {
            sql.SET("plat = #{record.plat,jdbcType=INTEGER}");
        }
        
        if (record.getPlatExt() != null) {
            sql.SET("plat_ext = #{record.platExt,jdbcType=VARCHAR}");
        }
        
        if (record.getReturnUrl() != null) {
            sql.SET("return_url = #{record.returnUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCallbackUrl() != null) {
            sql.SET("callback_url = #{record.callbackUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCallbackStatus() != null) {
            sql.SET("callback_status = #{record.callbackStatus,jdbcType=INTEGER}");
        }
        
        if (record.getCallbackCount() != null) {
            sql.SET("callback_count = #{record.callbackCount,jdbcType=INTEGER}");
        }
        
        if (record.getCallbackTime() != null) {
            sql.SET("callback_time = #{record.callbackTime,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_consume_order`
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("`pay_consume_order`");
        
        sql.SET("id = #{record.id,jdbcType=VARCHAR}");
        sql.SET("user_id = #{record.userId,jdbcType=VARCHAR}");
        sql.SET("account_id = #{record.accountId,jdbcType=VARCHAR}");
        sql.SET("channel = #{record.channel,jdbcType=INTEGER}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("create_time = #{record.createTime,jdbcType=INTEGER}");
        sql.SET("pay_time = #{record.payTime,jdbcType=INTEGER}");
        sql.SET("recharge_id = #{record.rechargeId,jdbcType=VARCHAR}");
        sql.SET("consume_amount = #{record.consumeAmount,jdbcType=INTEGER}");
        sql.SET("recharge_amount = #{record.rechargeAmount,jdbcType=INTEGER}");
        sql.SET("subject = #{record.subject,jdbcType=VARCHAR}");
        sql.SET("body = #{record.body,jdbcType=VARCHAR}");
        sql.SET("busi_consume_no = #{record.busiConsumeNo,jdbcType=VARCHAR}");
        sql.SET("busi_show_url = #{record.busiShowUrl,jdbcType=VARCHAR}");
        sql.SET("city_id = #{record.cityId,jdbcType=INTEGER}");
        sql.SET("refund_count = #{record.refundCount,jdbcType=INTEGER}");
        sql.SET("expire_time = #{record.expireTime,jdbcType=INTEGER}");
        sql.SET("refund_amount = #{record.refundAmount,jdbcType=INTEGER}");
        sql.SET("refund_time = #{record.refundTime,jdbcType=INTEGER}");
        sql.SET("update_time = #{record.updateTime,jdbcType=INTEGER}");
        sql.SET("plat = #{record.plat,jdbcType=INTEGER}");
        sql.SET("plat_ext = #{record.platExt,jdbcType=VARCHAR}");
        sql.SET("return_url = #{record.returnUrl,jdbcType=VARCHAR}");
        sql.SET("callback_url = #{record.callbackUrl,jdbcType=VARCHAR}");
        sql.SET("callback_status = #{record.callbackStatus,jdbcType=INTEGER}");
        sql.SET("callback_count = #{record.callbackCount,jdbcType=INTEGER}");
        sql.SET("callback_time = #{record.callbackTime,jdbcType=INTEGER}");
        
        PayConsumeOrderCriteria example = (PayConsumeOrderCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_consume_order`
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(PayConsumeOrder record) {
        SQL sql = new SQL();
        sql.UPDATE("`pay_consume_order`");
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountId() != null) {
            sql.SET("account_id = #{accountId,jdbcType=VARCHAR}");
        }
        
        if (record.getChannel() != null) {
            sql.SET("channel = #{channel,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=INTEGER}");
        }
        
        if (record.getPayTime() != null) {
            sql.SET("pay_time = #{payTime,jdbcType=INTEGER}");
        }
        
        if (record.getRechargeId() != null) {
            sql.SET("recharge_id = #{rechargeId,jdbcType=VARCHAR}");
        }
        
        if (record.getConsumeAmount() != null) {
            sql.SET("consume_amount = #{consumeAmount,jdbcType=INTEGER}");
        }
        
        if (record.getRechargeAmount() != null) {
            sql.SET("recharge_amount = #{rechargeAmount,jdbcType=INTEGER}");
        }
        
        if (record.getSubject() != null) {
            sql.SET("subject = #{subject,jdbcType=VARCHAR}");
        }
        
        if (record.getBody() != null) {
            sql.SET("body = #{body,jdbcType=VARCHAR}");
        }
        
        if (record.getBusiConsumeNo() != null) {
            sql.SET("busi_consume_no = #{busiConsumeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getBusiShowUrl() != null) {
            sql.SET("busi_show_url = #{busiShowUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCityId() != null) {
            sql.SET("city_id = #{cityId,jdbcType=INTEGER}");
        }
        
        if (record.getRefundCount() != null) {
            sql.SET("refund_count = #{refundCount,jdbcType=INTEGER}");
        }
        
        if (record.getExpireTime() != null) {
            sql.SET("expire_time = #{expireTime,jdbcType=INTEGER}");
        }
        
        if (record.getRefundAmount() != null) {
            sql.SET("refund_amount = #{refundAmount,jdbcType=INTEGER}");
        }
        
        if (record.getRefundTime() != null) {
            sql.SET("refund_time = #{refundTime,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=INTEGER}");
        }
        
        if (record.getPlat() != null) {
            sql.SET("plat = #{plat,jdbcType=INTEGER}");
        }
        
        if (record.getPlatExt() != null) {
            sql.SET("plat_ext = #{platExt,jdbcType=VARCHAR}");
        }
        
        if (record.getReturnUrl() != null) {
            sql.SET("return_url = #{returnUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCallbackUrl() != null) {
            sql.SET("callback_url = #{callbackUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCallbackStatus() != null) {
            sql.SET("callback_status = #{callbackStatus,jdbcType=INTEGER}");
        }
        
        if (record.getCallbackCount() != null) {
            sql.SET("callback_count = #{callbackCount,jdbcType=INTEGER}");
        }
        
        if (record.getCallbackTime() != null) {
            sql.SET("callback_time = #{callbackTime,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_consume_order`
     *
     * @mbggenerated
     */
    protected void applyWhere(SQL sql, PayConsumeOrderCriteria example, boolean includeExamplePhrase) {
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