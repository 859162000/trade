package com.hbc.data.trade.transfer.mapping.hbcfinal.gen;

import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideaccountdetail;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideaccountdetailCriteria.Criteria;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideaccountdetailCriteria.Criterion;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideaccountdetailCriteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class FinalGuideaccountdetailSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideaccountdetail`
     *
     * @mbggenerated
     */
    public String countByExample(FinalGuideaccountdetailCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("`guideaccountdetail`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideaccountdetail`
     *
     * @mbggenerated
     */
    public String deleteByExample(FinalGuideaccountdetailCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("`guideaccountdetail`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideaccountdetail`
     *
     * @mbggenerated
     */
    public String insertSelective(FinalGuideaccountdetail record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`guideaccountdetail`");
        
        if (record.getGuideaccountdetailid() != null) {
            sql.VALUES("guideAccountDetailId", "#{guideaccountdetailid,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideaccountsn() != null) {
            sql.VALUES("guideAccountSN", "#{guideaccountsn,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideid() != null) {
            sql.VALUES("guideId", "#{guideid,jdbcType=INTEGER}");
        }
        
        if (record.getOrderid() != null) {
            sql.VALUES("orderId", "#{orderid,jdbcType=VARCHAR}");
        }
        
        if (record.getOrdersn() != null) {
            sql.VALUES("orderSN", "#{ordersn,jdbcType=VARCHAR}");
        }
        
        if (record.getGuidefinanceid() != null) {
            sql.VALUES("guideFinanceId", "#{guidefinanceid,jdbcType=INTEGER}");
        }
        
        if (record.getBiztype() != null) {
            sql.VALUES("bizType", "#{biztype,jdbcType=INTEGER}");
        }
        
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            sql.VALUES("price", "#{price,jdbcType=INTEGER}");
        }
        
        if (record.getPaytype() != null) {
            sql.VALUES("payType", "#{paytype,jdbcType=INTEGER}");
        }
        
        if (record.getBizstatus() != null) {
            sql.VALUES("bizStatus", "#{bizstatus,jdbcType=INTEGER}");
        }
        
        if (record.getBizcomment() != null) {
            sql.VALUES("bizComment", "#{bizcomment,jdbcType=VARCHAR}");
        }
        
        if (record.getGuidedrawid() != null) {
            sql.VALUES("guideDrawId", "#{guidedrawid,jdbcType=INTEGER}");
        }
        
        if (record.getUpdatedAt() != null) {
            sql.VALUES("updated_at", "#{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideaccountdetail`
     *
     * @mbggenerated
     */
    public String selectByExample(FinalGuideaccountdetailCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("guideAccountDetailId");
        } else {
            sql.SELECT("guideAccountDetailId");
        }
        sql.SELECT("guideAccountSN");
        sql.SELECT("guideId");
        sql.SELECT("orderId");
        sql.SELECT("orderSN");
        sql.SELECT("guideFinanceId");
        sql.SELECT("bizType");
        sql.SELECT("content");
        sql.SELECT("price");
        sql.SELECT("payType");
        sql.SELECT("bizStatus");
        sql.SELECT("bizComment");
        sql.SELECT("guideDrawId");
        sql.SELECT("updated_at");
        sql.SELECT("created_at");
        sql.FROM("`guideaccountdetail`");
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
     * This method corresponds to the database table `guideaccountdetail`
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        FinalGuideaccountdetail record = (FinalGuideaccountdetail) parameter.get("record");
        FinalGuideaccountdetailCriteria example = (FinalGuideaccountdetailCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("`guideaccountdetail`");
        
        if (record.getGuideaccountdetailid() != null) {
            sql.SET("guideAccountDetailId = #{record.guideaccountdetailid,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideaccountsn() != null) {
            sql.SET("guideAccountSN = #{record.guideaccountsn,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideid() != null) {
            sql.SET("guideId = #{record.guideid,jdbcType=INTEGER}");
        }
        
        if (record.getOrderid() != null) {
            sql.SET("orderId = #{record.orderid,jdbcType=VARCHAR}");
        }
        
        if (record.getOrdersn() != null) {
            sql.SET("orderSN = #{record.ordersn,jdbcType=VARCHAR}");
        }
        
        if (record.getGuidefinanceid() != null) {
            sql.SET("guideFinanceId = #{record.guidefinanceid,jdbcType=INTEGER}");
        }
        
        if (record.getBiztype() != null) {
            sql.SET("bizType = #{record.biztype,jdbcType=INTEGER}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{record.content,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            sql.SET("price = #{record.price,jdbcType=INTEGER}");
        }
        
        if (record.getPaytype() != null) {
            sql.SET("payType = #{record.paytype,jdbcType=INTEGER}");
        }
        
        if (record.getBizstatus() != null) {
            sql.SET("bizStatus = #{record.bizstatus,jdbcType=INTEGER}");
        }
        
        if (record.getBizcomment() != null) {
            sql.SET("bizComment = #{record.bizcomment,jdbcType=VARCHAR}");
        }
        
        if (record.getGuidedrawid() != null) {
            sql.SET("guideDrawId = #{record.guidedrawid,jdbcType=INTEGER}");
        }
        
        if (record.getUpdatedAt() != null) {
            sql.SET("updated_at = #{record.updatedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideaccountdetail`
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("`guideaccountdetail`");
        
        sql.SET("guideAccountDetailId = #{record.guideaccountdetailid,jdbcType=VARCHAR}");
        sql.SET("guideAccountSN = #{record.guideaccountsn,jdbcType=VARCHAR}");
        sql.SET("guideId = #{record.guideid,jdbcType=INTEGER}");
        sql.SET("orderId = #{record.orderid,jdbcType=VARCHAR}");
        sql.SET("orderSN = #{record.ordersn,jdbcType=VARCHAR}");
        sql.SET("guideFinanceId = #{record.guidefinanceid,jdbcType=INTEGER}");
        sql.SET("bizType = #{record.biztype,jdbcType=INTEGER}");
        sql.SET("content = #{record.content,jdbcType=VARCHAR}");
        sql.SET("price = #{record.price,jdbcType=INTEGER}");
        sql.SET("payType = #{record.paytype,jdbcType=INTEGER}");
        sql.SET("bizStatus = #{record.bizstatus,jdbcType=INTEGER}");
        sql.SET("bizComment = #{record.bizcomment,jdbcType=VARCHAR}");
        sql.SET("guideDrawId = #{record.guidedrawid,jdbcType=INTEGER}");
        sql.SET("updated_at = #{record.updatedAt,jdbcType=TIMESTAMP}");
        sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        
        FinalGuideaccountdetailCriteria example = (FinalGuideaccountdetailCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideaccountdetail`
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(FinalGuideaccountdetail record) {
        SQL sql = new SQL();
        sql.UPDATE("`guideaccountdetail`");
        
        if (record.getGuideaccountsn() != null) {
            sql.SET("guideAccountSN = #{guideaccountsn,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideid() != null) {
            sql.SET("guideId = #{guideid,jdbcType=INTEGER}");
        }
        
        if (record.getOrderid() != null) {
            sql.SET("orderId = #{orderid,jdbcType=VARCHAR}");
        }
        
        if (record.getOrdersn() != null) {
            sql.SET("orderSN = #{ordersn,jdbcType=VARCHAR}");
        }
        
        if (record.getGuidefinanceid() != null) {
            sql.SET("guideFinanceId = #{guidefinanceid,jdbcType=INTEGER}");
        }
        
        if (record.getBiztype() != null) {
            sql.SET("bizType = #{biztype,jdbcType=INTEGER}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            sql.SET("price = #{price,jdbcType=INTEGER}");
        }
        
        if (record.getPaytype() != null) {
            sql.SET("payType = #{paytype,jdbcType=INTEGER}");
        }
        
        if (record.getBizstatus() != null) {
            sql.SET("bizStatus = #{bizstatus,jdbcType=INTEGER}");
        }
        
        if (record.getBizcomment() != null) {
            sql.SET("bizComment = #{bizcomment,jdbcType=VARCHAR}");
        }
        
        if (record.getGuidedrawid() != null) {
            sql.SET("guideDrawId = #{guidedrawid,jdbcType=INTEGER}");
        }
        
        if (record.getUpdatedAt() != null) {
            sql.SET("updated_at = #{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("guideAccountDetailId = #{guideaccountdetailid,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideaccountdetail`
     *
     * @mbggenerated
     */
    protected void applyWhere(SQL sql, FinalGuideaccountdetailCriteria example, boolean includeExamplePhrase) {
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