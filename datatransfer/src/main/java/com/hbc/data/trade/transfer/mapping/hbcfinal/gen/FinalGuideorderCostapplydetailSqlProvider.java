package com.hbc.data.trade.transfer.mapping.hbcfinal.gen;

import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideorderCostapplydetail;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideorderCostapplydetailCriteria.Criteria;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideorderCostapplydetailCriteria.Criterion;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideorderCostapplydetailCriteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class FinalGuideorderCostapplydetailSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideordercostapplydetail`
     *
     * @mbggenerated
     */
    public String countByExample(FinalGuideorderCostapplydetailCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("`guideordercostapplydetail`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideordercostapplydetail`
     *
     * @mbggenerated
     */
    public String deleteByExample(FinalGuideorderCostapplydetailCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("`guideordercostapplydetail`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideordercostapplydetail`
     *
     * @mbggenerated
     */
    public String insertSelective(FinalGuideorderCostapplydetail record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`guideordercostapplydetail`");
        
        if (record.getGuideordercostapplydetailid() != null) {
            sql.VALUES("guideOrderCostApplyDetailId", "#{guideordercostapplydetailid,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideordercostapplyid() != null) {
            sql.VALUES("guideOrderCostApplyId", "#{guideordercostapplyid,jdbcType=VARCHAR}");
        }
        
        if (record.getDailydate() != null) {
            sql.VALUES("dailyDate", "#{dailydate,jdbcType=DATE}");
        }
        
        if (record.getOvertime() != null) {
            sql.VALUES("overTime", "#{overtime,jdbcType=INTEGER}");
        }
        
        if (record.getUnittimeprice() != null) {
            sql.VALUES("unitTimePrice", "#{unittimeprice,jdbcType=DECIMAL}");
        }
        
        if (record.getOverdistance() != null) {
            sql.VALUES("overDistance", "#{overdistance,jdbcType=DECIMAL}");
        }
        
        if (record.getUnitdistanceprice() != null) {
            sql.VALUES("unitDistancePrice", "#{unitdistanceprice,jdbcType=DECIMAL}");
        }
        
        if (record.getOverday() != null) {
            sql.VALUES("overDay", "#{overday,jdbcType=DECIMAL}");
        }
        
        if (record.getUnitdayprice() != null) {
            sql.VALUES("unitDayPrice", "#{unitdayprice,jdbcType=DECIMAL}");
        }
        
        if (record.getOtherfee1() != null) {
            sql.VALUES("otherFee1", "#{otherfee1,jdbcType=DECIMAL}");
        }
        
        if (record.getOtherfee2() != null) {
            sql.VALUES("otherFee2", "#{otherfee2,jdbcType=DECIMAL}");
        }
        
        if (record.getApplyfee() != null) {
            sql.VALUES("applyFee", "#{applyfee,jdbcType=DECIMAL}");
        }
        
        if (record.getCfmovertime() != null) {
            sql.VALUES("cfmOverTime", "#{cfmovertime,jdbcType=INTEGER}");
        }
        
        if (record.getCfmoverdistance() != null) {
            sql.VALUES("cfmOverDistance", "#{cfmoverdistance,jdbcType=DECIMAL}");
        }
        
        if (record.getCfmoverday() != null) {
            sql.VALUES("cfmOverDay", "#{cfmoverday,jdbcType=DECIMAL}");
        }
        
        if (record.getCfmotherfee1() != null) {
            sql.VALUES("cfmOtherFee1", "#{cfmotherfee1,jdbcType=DECIMAL}");
        }
        
        if (record.getCfmotherfee2() != null) {
            sql.VALUES("cfmOtherFee2", "#{cfmotherfee2,jdbcType=DECIMAL}");
        }
        
        if (record.getCfmfee() != null) {
            sql.VALUES("cfmFee", "#{cfmfee,jdbcType=DECIMAL}");
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
     * This method corresponds to the database table `guideordercostapplydetail`
     *
     * @mbggenerated
     */
    public String selectByExample(FinalGuideorderCostapplydetailCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("guideOrderCostApplyDetailId");
        } else {
            sql.SELECT("guideOrderCostApplyDetailId");
        }
        sql.SELECT("guideOrderCostApplyId");
        sql.SELECT("dailyDate");
        sql.SELECT("overTime");
        sql.SELECT("unitTimePrice");
        sql.SELECT("overDistance");
        sql.SELECT("unitDistancePrice");
        sql.SELECT("overDay");
        sql.SELECT("unitDayPrice");
        sql.SELECT("otherFee1");
        sql.SELECT("otherFee2");
        sql.SELECT("applyFee");
        sql.SELECT("cfmOverTime");
        sql.SELECT("cfmOverDistance");
        sql.SELECT("cfmOverDay");
        sql.SELECT("cfmOtherFee1");
        sql.SELECT("cfmOtherFee2");
        sql.SELECT("cfmFee");
        sql.SELECT("updated_at");
        sql.SELECT("created_at");
        sql.FROM("`guideordercostapplydetail`");
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
     * This method corresponds to the database table `guideordercostapplydetail`
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        FinalGuideorderCostapplydetail record = (FinalGuideorderCostapplydetail) parameter.get("record");
        FinalGuideorderCostapplydetailCriteria example = (FinalGuideorderCostapplydetailCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("`guideordercostapplydetail`");
        
        if (record.getGuideordercostapplydetailid() != null) {
            sql.SET("guideOrderCostApplyDetailId = #{record.guideordercostapplydetailid,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideordercostapplyid() != null) {
            sql.SET("guideOrderCostApplyId = #{record.guideordercostapplyid,jdbcType=VARCHAR}");
        }
        
        if (record.getDailydate() != null) {
            sql.SET("dailyDate = #{record.dailydate,jdbcType=DATE}");
        }
        
        if (record.getOvertime() != null) {
            sql.SET("overTime = #{record.overtime,jdbcType=INTEGER}");
        }
        
        if (record.getUnittimeprice() != null) {
            sql.SET("unitTimePrice = #{record.unittimeprice,jdbcType=DECIMAL}");
        }
        
        if (record.getOverdistance() != null) {
            sql.SET("overDistance = #{record.overdistance,jdbcType=DECIMAL}");
        }
        
        if (record.getUnitdistanceprice() != null) {
            sql.SET("unitDistancePrice = #{record.unitdistanceprice,jdbcType=DECIMAL}");
        }
        
        if (record.getOverday() != null) {
            sql.SET("overDay = #{record.overday,jdbcType=DECIMAL}");
        }
        
        if (record.getUnitdayprice() != null) {
            sql.SET("unitDayPrice = #{record.unitdayprice,jdbcType=DECIMAL}");
        }
        
        if (record.getOtherfee1() != null) {
            sql.SET("otherFee1 = #{record.otherfee1,jdbcType=DECIMAL}");
        }
        
        if (record.getOtherfee2() != null) {
            sql.SET("otherFee2 = #{record.otherfee2,jdbcType=DECIMAL}");
        }
        
        if (record.getApplyfee() != null) {
            sql.SET("applyFee = #{record.applyfee,jdbcType=DECIMAL}");
        }
        
        if (record.getCfmovertime() != null) {
            sql.SET("cfmOverTime = #{record.cfmovertime,jdbcType=INTEGER}");
        }
        
        if (record.getCfmoverdistance() != null) {
            sql.SET("cfmOverDistance = #{record.cfmoverdistance,jdbcType=DECIMAL}");
        }
        
        if (record.getCfmoverday() != null) {
            sql.SET("cfmOverDay = #{record.cfmoverday,jdbcType=DECIMAL}");
        }
        
        if (record.getCfmotherfee1() != null) {
            sql.SET("cfmOtherFee1 = #{record.cfmotherfee1,jdbcType=DECIMAL}");
        }
        
        if (record.getCfmotherfee2() != null) {
            sql.SET("cfmOtherFee2 = #{record.cfmotherfee2,jdbcType=DECIMAL}");
        }
        
        if (record.getCfmfee() != null) {
            sql.SET("cfmFee = #{record.cfmfee,jdbcType=DECIMAL}");
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
     * This method corresponds to the database table `guideordercostapplydetail`
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("`guideordercostapplydetail`");
        
        sql.SET("guideOrderCostApplyDetailId = #{record.guideordercostapplydetailid,jdbcType=VARCHAR}");
        sql.SET("guideOrderCostApplyId = #{record.guideordercostapplyid,jdbcType=VARCHAR}");
        sql.SET("dailyDate = #{record.dailydate,jdbcType=DATE}");
        sql.SET("overTime = #{record.overtime,jdbcType=INTEGER}");
        sql.SET("unitTimePrice = #{record.unittimeprice,jdbcType=DECIMAL}");
        sql.SET("overDistance = #{record.overdistance,jdbcType=DECIMAL}");
        sql.SET("unitDistancePrice = #{record.unitdistanceprice,jdbcType=DECIMAL}");
        sql.SET("overDay = #{record.overday,jdbcType=DECIMAL}");
        sql.SET("unitDayPrice = #{record.unitdayprice,jdbcType=DECIMAL}");
        sql.SET("otherFee1 = #{record.otherfee1,jdbcType=DECIMAL}");
        sql.SET("otherFee2 = #{record.otherfee2,jdbcType=DECIMAL}");
        sql.SET("applyFee = #{record.applyfee,jdbcType=DECIMAL}");
        sql.SET("cfmOverTime = #{record.cfmovertime,jdbcType=INTEGER}");
        sql.SET("cfmOverDistance = #{record.cfmoverdistance,jdbcType=DECIMAL}");
        sql.SET("cfmOverDay = #{record.cfmoverday,jdbcType=DECIMAL}");
        sql.SET("cfmOtherFee1 = #{record.cfmotherfee1,jdbcType=DECIMAL}");
        sql.SET("cfmOtherFee2 = #{record.cfmotherfee2,jdbcType=DECIMAL}");
        sql.SET("cfmFee = #{record.cfmfee,jdbcType=DECIMAL}");
        sql.SET("updated_at = #{record.updatedAt,jdbcType=TIMESTAMP}");
        sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        
        FinalGuideorderCostapplydetailCriteria example = (FinalGuideorderCostapplydetailCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideordercostapplydetail`
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(FinalGuideorderCostapplydetail record) {
        SQL sql = new SQL();
        sql.UPDATE("`guideordercostapplydetail`");
        
        if (record.getGuideordercostapplyid() != null) {
            sql.SET("guideOrderCostApplyId = #{guideordercostapplyid,jdbcType=VARCHAR}");
        }
        
        if (record.getDailydate() != null) {
            sql.SET("dailyDate = #{dailydate,jdbcType=DATE}");
        }
        
        if (record.getOvertime() != null) {
            sql.SET("overTime = #{overtime,jdbcType=INTEGER}");
        }
        
        if (record.getUnittimeprice() != null) {
            sql.SET("unitTimePrice = #{unittimeprice,jdbcType=DECIMAL}");
        }
        
        if (record.getOverdistance() != null) {
            sql.SET("overDistance = #{overdistance,jdbcType=DECIMAL}");
        }
        
        if (record.getUnitdistanceprice() != null) {
            sql.SET("unitDistancePrice = #{unitdistanceprice,jdbcType=DECIMAL}");
        }
        
        if (record.getOverday() != null) {
            sql.SET("overDay = #{overday,jdbcType=DECIMAL}");
        }
        
        if (record.getUnitdayprice() != null) {
            sql.SET("unitDayPrice = #{unitdayprice,jdbcType=DECIMAL}");
        }
        
        if (record.getOtherfee1() != null) {
            sql.SET("otherFee1 = #{otherfee1,jdbcType=DECIMAL}");
        }
        
        if (record.getOtherfee2() != null) {
            sql.SET("otherFee2 = #{otherfee2,jdbcType=DECIMAL}");
        }
        
        if (record.getApplyfee() != null) {
            sql.SET("applyFee = #{applyfee,jdbcType=DECIMAL}");
        }
        
        if (record.getCfmovertime() != null) {
            sql.SET("cfmOverTime = #{cfmovertime,jdbcType=INTEGER}");
        }
        
        if (record.getCfmoverdistance() != null) {
            sql.SET("cfmOverDistance = #{cfmoverdistance,jdbcType=DECIMAL}");
        }
        
        if (record.getCfmoverday() != null) {
            sql.SET("cfmOverDay = #{cfmoverday,jdbcType=DECIMAL}");
        }
        
        if (record.getCfmotherfee1() != null) {
            sql.SET("cfmOtherFee1 = #{cfmotherfee1,jdbcType=DECIMAL}");
        }
        
        if (record.getCfmotherfee2() != null) {
            sql.SET("cfmOtherFee2 = #{cfmotherfee2,jdbcType=DECIMAL}");
        }
        
        if (record.getCfmfee() != null) {
            sql.SET("cfmFee = #{cfmfee,jdbcType=DECIMAL}");
        }
        
        if (record.getUpdatedAt() != null) {
            sql.SET("updated_at = #{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("guideOrderCostApplyDetailId = #{guideordercostapplydetailid,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guideordercostapplydetail`
     *
     * @mbggenerated
     */
    protected void applyWhere(SQL sql, FinalGuideorderCostapplydetailCriteria example, boolean includeExamplePhrase) {
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