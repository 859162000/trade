package com.hbc.api.trade.pay.mapping.gen;

import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefundConf;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefundConfExample.Criteria;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefundConfExample.Criterion;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefundConfExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class TradeRefundConfSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund_conf`
     *
     * @mbggenerated
     */
    public String countByExample(TradeRefundConfExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("`trade_refund_conf`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund_conf`
     *
     * @mbggenerated
     */
    public String deleteByExample(TradeRefundConfExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("`trade_refund_conf`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund_conf`
     *
     * @mbggenerated
     */
    public String insertSelective(TradeRefundConf record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`trade_refund_conf`");
        
        if (record.getRefundConfNo() != null) {
            sql.VALUES("refund_conf_no", "#{refundConfNo,jdbcType=VARCHAR}");
        }
        
        if (record.getStartHour() != null) {
            sql.VALUES("start_hour", "#{startHour,jdbcType=INTEGER}");
        }
        
        if (record.getIsContainStart() != null) {
            sql.VALUES("is_contain_start", "#{isContainStart,jdbcType=TINYINT}");
        }
        
        if (record.getEndHour() != null) {
            sql.VALUES("end_hour", "#{endHour,jdbcType=INTEGER}");
        }
        
        if (record.getIsContainEnd() != null) {
            sql.VALUES("is_contain_end", "#{isContainEnd,jdbcType=TINYINT}");
        }
        
        if (record.getUserRatio() != null) {
            sql.VALUES("user_ratio", "#{userRatio,jdbcType=DOUBLE}");
        }
        
        if (record.getGuideRatio() != null) {
            sql.VALUES("guide_ratio", "#{guideRatio,jdbcType=DOUBLE}");
        }
        
        if (record.getRefundType() != null) {
            sql.VALUES("refund_type", "#{refundType,jdbcType=TINYINT}");
        }
        
        if (record.getRefundTypeName() != null) {
            sql.VALUES("refund_type_name", "#{refundTypeName,jdbcType=VARCHAR}");
        }
        
        if (record.getCityId() != null) {
            sql.VALUES("city_id", "#{cityId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund_conf`
     *
     * @mbggenerated
     */
    public String selectByExample(TradeRefundConfExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("refund_conf_no");
        } else {
            sql.SELECT("refund_conf_no");
        }
        sql.SELECT("start_hour");
        sql.SELECT("is_contain_start");
        sql.SELECT("end_hour");
        sql.SELECT("is_contain_end");
        sql.SELECT("user_ratio");
        sql.SELECT("guide_ratio");
        sql.SELECT("refund_type");
        sql.SELECT("refund_type_name");
        sql.SELECT("city_id");
        sql.SELECT("create_time");
        sql.SELECT("update_time");
        sql.FROM("`trade_refund_conf`");
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
     * This method corresponds to the database table `trade_refund_conf`
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        TradeRefundConf record = (TradeRefundConf) parameter.get("record");
        TradeRefundConfExample example = (TradeRefundConfExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("`trade_refund_conf`");
        
        if (record.getRefundConfNo() != null) {
            sql.SET("refund_conf_no = #{record.refundConfNo,jdbcType=VARCHAR}");
        }
        
        if (record.getStartHour() != null) {
            sql.SET("start_hour = #{record.startHour,jdbcType=INTEGER}");
        }
        
        if (record.getIsContainStart() != null) {
            sql.SET("is_contain_start = #{record.isContainStart,jdbcType=TINYINT}");
        }
        
        if (record.getEndHour() != null) {
            sql.SET("end_hour = #{record.endHour,jdbcType=INTEGER}");
        }
        
        if (record.getIsContainEnd() != null) {
            sql.SET("is_contain_end = #{record.isContainEnd,jdbcType=TINYINT}");
        }
        
        if (record.getUserRatio() != null) {
            sql.SET("user_ratio = #{record.userRatio,jdbcType=DOUBLE}");
        }
        
        if (record.getGuideRatio() != null) {
            sql.SET("guide_ratio = #{record.guideRatio,jdbcType=DOUBLE}");
        }
        
        if (record.getRefundType() != null) {
            sql.SET("refund_type = #{record.refundType,jdbcType=TINYINT}");
        }
        
        if (record.getRefundTypeName() != null) {
            sql.SET("refund_type_name = #{record.refundTypeName,jdbcType=VARCHAR}");
        }
        
        if (record.getCityId() != null) {
            sql.SET("city_id = #{record.cityId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund_conf`
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("`trade_refund_conf`");
        
        sql.SET("refund_conf_no = #{record.refundConfNo,jdbcType=VARCHAR}");
        sql.SET("start_hour = #{record.startHour,jdbcType=INTEGER}");
        sql.SET("is_contain_start = #{record.isContainStart,jdbcType=TINYINT}");
        sql.SET("end_hour = #{record.endHour,jdbcType=INTEGER}");
        sql.SET("is_contain_end = #{record.isContainEnd,jdbcType=TINYINT}");
        sql.SET("user_ratio = #{record.userRatio,jdbcType=DOUBLE}");
        sql.SET("guide_ratio = #{record.guideRatio,jdbcType=DOUBLE}");
        sql.SET("refund_type = #{record.refundType,jdbcType=TINYINT}");
        sql.SET("refund_type_name = #{record.refundTypeName,jdbcType=VARCHAR}");
        sql.SET("city_id = #{record.cityId,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        
        TradeRefundConfExample example = (TradeRefundConfExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund_conf`
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(TradeRefundConf record) {
        SQL sql = new SQL();
        sql.UPDATE("`trade_refund_conf`");
        
        if (record.getStartHour() != null) {
            sql.SET("start_hour = #{startHour,jdbcType=INTEGER}");
        }
        
        if (record.getIsContainStart() != null) {
            sql.SET("is_contain_start = #{isContainStart,jdbcType=TINYINT}");
        }
        
        if (record.getEndHour() != null) {
            sql.SET("end_hour = #{endHour,jdbcType=INTEGER}");
        }
        
        if (record.getIsContainEnd() != null) {
            sql.SET("is_contain_end = #{isContainEnd,jdbcType=TINYINT}");
        }
        
        if (record.getUserRatio() != null) {
            sql.SET("user_ratio = #{userRatio,jdbcType=DOUBLE}");
        }
        
        if (record.getGuideRatio() != null) {
            sql.SET("guide_ratio = #{guideRatio,jdbcType=DOUBLE}");
        }
        
        if (record.getRefundType() != null) {
            sql.SET("refund_type = #{refundType,jdbcType=TINYINT}");
        }
        
        if (record.getRefundTypeName() != null) {
            sql.SET("refund_type_name = #{refundTypeName,jdbcType=VARCHAR}");
        }
        
        if (record.getCityId() != null) {
            sql.SET("city_id = #{cityId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("refund_conf_no = #{refundConfNo,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund_conf`
     *
     * @mbggenerated
     */
    protected void applyWhere(SQL sql, TradeRefundConfExample example, boolean includeExamplePhrase) {
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