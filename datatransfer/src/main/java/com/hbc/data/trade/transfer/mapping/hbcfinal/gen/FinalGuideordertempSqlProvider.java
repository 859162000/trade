package com.hbc.data.trade.transfer.mapping.hbcfinal.gen;

import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideordertemp;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideordertempCriteria.Criteria;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideordertempCriteria.Criterion;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideordertempCriteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class FinalGuideordertempSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideordertemp`
     *
     * @mbggenerated
     */
    public String countByExample(FinalGuideordertempCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("`guideordertemp`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideordertemp`
     *
     * @mbggenerated
     */
    public String deleteByExample(FinalGuideordertempCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("`guideordertemp`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideordertemp`
     *
     * @mbggenerated
     */
    public String insertSelective(FinalGuideordertemp record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`guideordertemp`");
        
        if (record.getGuideordertempid() != null) {
            sql.VALUES("guideOrderTempId", "#{guideordertempid,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderid() != null) {
            sql.VALUES("orderId", "#{orderid,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideid() != null) {
            sql.VALUES("guideId", "#{guideid,jdbcType=INTEGER}");
        }
        
        if (record.getPriority() != null) {
            sql.VALUES("priority", "#{priority,jdbcType=INTEGER}");
        }
        
        if (record.getFlag() != null) {
            sql.VALUES("flag", "#{flag,jdbcType=INTEGER}");
        }
        
        if (record.getDelivertime() != null) {
            sql.VALUES("deliverTime", "#{delivertime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
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
     * This method corresponds to the database table `guideordertemp`
     *
     * @mbggenerated
     */
    public String selectByExample(FinalGuideordertempCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("guideOrderTempId");
        } else {
            sql.SELECT("guideOrderTempId");
        }
        sql.SELECT("orderId");
        sql.SELECT("guideId");
        sql.SELECT("priority");
        sql.SELECT("flag");
        sql.SELECT("deliverTime");
        sql.SELECT("status");
        sql.SELECT("updated_at");
        sql.SELECT("created_at");
        sql.FROM("`guideordertemp`");
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
     * This method corresponds to the database table `guideordertemp`
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        FinalGuideordertemp record = (FinalGuideordertemp) parameter.get("record");
        FinalGuideordertempCriteria example = (FinalGuideordertempCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("`guideordertemp`");
        
        if (record.getGuideordertempid() != null) {
            sql.SET("guideOrderTempId = #{record.guideordertempid,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderid() != null) {
            sql.SET("orderId = #{record.orderid,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideid() != null) {
            sql.SET("guideId = #{record.guideid,jdbcType=INTEGER}");
        }
        
        if (record.getPriority() != null) {
            sql.SET("priority = #{record.priority,jdbcType=INTEGER}");
        }
        
        if (record.getFlag() != null) {
            sql.SET("flag = #{record.flag,jdbcType=INTEGER}");
        }
        
        if (record.getDelivertime() != null) {
            sql.SET("deliverTime = #{record.delivertime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=INTEGER}");
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
     * This method corresponds to the database table `guideordertemp`
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("`guideordertemp`");
        
        sql.SET("guideOrderTempId = #{record.guideordertempid,jdbcType=VARCHAR}");
        sql.SET("orderId = #{record.orderid,jdbcType=VARCHAR}");
        sql.SET("guideId = #{record.guideid,jdbcType=INTEGER}");
        sql.SET("priority = #{record.priority,jdbcType=INTEGER}");
        sql.SET("flag = #{record.flag,jdbcType=INTEGER}");
        sql.SET("deliverTime = #{record.delivertime,jdbcType=TIMESTAMP}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("updated_at = #{record.updatedAt,jdbcType=TIMESTAMP}");
        sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        
        FinalGuideordertempCriteria example = (FinalGuideordertempCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideordertemp`
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(FinalGuideordertemp record) {
        SQL sql = new SQL();
        sql.UPDATE("`guideordertemp`");
        
        if (record.getOrderid() != null) {
            sql.SET("orderId = #{orderid,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideid() != null) {
            sql.SET("guideId = #{guideid,jdbcType=INTEGER}");
        }
        
        if (record.getPriority() != null) {
            sql.SET("priority = #{priority,jdbcType=INTEGER}");
        }
        
        if (record.getFlag() != null) {
            sql.SET("flag = #{flag,jdbcType=INTEGER}");
        }
        
        if (record.getDelivertime() != null) {
            sql.SET("deliverTime = #{delivertime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getUpdatedAt() != null) {
            sql.SET("updated_at = #{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("guideOrderTempId = #{guideordertempid,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideordertemp`
     *
     * @mbggenerated
     */
    protected void applyWhere(SQL sql, FinalGuideordertempCriteria example, boolean includeExamplePhrase) {
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