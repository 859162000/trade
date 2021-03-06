package com.hbc.data.trade.transfer.mapping.hbcbasedata.gen;

import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgencyguide;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgencyguideCriteria.Criteria;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgencyguideCriteria.Criterion;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgencyguideCriteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class FinalAgencyguideSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agencyguide`
     *
     * @mbggenerated
     */
    public String countByExample(FinalAgencyguideCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("`agencyguide`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agencyguide`
     *
     * @mbggenerated
     */
    public String deleteByExample(FinalAgencyguideCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("`agencyguide`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agencyguide`
     *
     * @mbggenerated
     */
    public String insertSelective(FinalAgencyguide record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`agencyguide`");
        
        if (record.getAgencyguideid() != null) {
            sql.VALUES("agencyGuideId", "#{agencyguideid,jdbcType=VARCHAR}");
        }
        
        if (record.getAgencyid() != null) {
            sql.VALUES("agencyId", "#{agencyid,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getAreacode() != null) {
            sql.VALUES("areaCode", "#{areacode,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            sql.VALUES("mobile", "#{mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getIsprc() != null) {
            sql.VALUES("isPrc", "#{isprc,jdbcType=INTEGER}");
        }
        
        if (record.getServicecount() != null) {
            sql.VALUES("serviceCount", "#{servicecount,jdbcType=INTEGER}");
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
     * This method corresponds to the database table `agencyguide`
     *
     * @mbggenerated
     */
    public String selectByExample(FinalAgencyguideCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("agencyGuideId");
        } else {
            sql.SELECT("agencyGuideId");
        }
        sql.SELECT("agencyId");
        sql.SELECT("name");
        sql.SELECT("areaCode");
        sql.SELECT("mobile");
        sql.SELECT("isPrc");
        sql.SELECT("serviceCount");
        sql.SELECT("status");
        sql.SELECT("updated_at");
        sql.SELECT("created_at");
        sql.FROM("`agencyguide`");
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
     * This method corresponds to the database table `agencyguide`
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        FinalAgencyguide record = (FinalAgencyguide) parameter.get("record");
        FinalAgencyguideCriteria example = (FinalAgencyguideCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("`agencyguide`");
        
        if (record.getAgencyguideid() != null) {
            sql.SET("agencyGuideId = #{record.agencyguideid,jdbcType=VARCHAR}");
        }
        
        if (record.getAgencyid() != null) {
            sql.SET("agencyId = #{record.agencyid,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getAreacode() != null) {
            sql.SET("areaCode = #{record.areacode,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            sql.SET("mobile = #{record.mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getIsprc() != null) {
            sql.SET("isPrc = #{record.isprc,jdbcType=INTEGER}");
        }
        
        if (record.getServicecount() != null) {
            sql.SET("serviceCount = #{record.servicecount,jdbcType=INTEGER}");
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
     * This method corresponds to the database table `agencyguide`
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("`agencyguide`");
        
        sql.SET("agencyGuideId = #{record.agencyguideid,jdbcType=VARCHAR}");
        sql.SET("agencyId = #{record.agencyid,jdbcType=INTEGER}");
        sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        sql.SET("areaCode = #{record.areacode,jdbcType=VARCHAR}");
        sql.SET("mobile = #{record.mobile,jdbcType=VARCHAR}");
        sql.SET("isPrc = #{record.isprc,jdbcType=INTEGER}");
        sql.SET("serviceCount = #{record.servicecount,jdbcType=INTEGER}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("updated_at = #{record.updatedAt,jdbcType=TIMESTAMP}");
        sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        
        FinalAgencyguideCriteria example = (FinalAgencyguideCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agencyguide`
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(FinalAgencyguide record) {
        SQL sql = new SQL();
        sql.UPDATE("`agencyguide`");
        
        if (record.getAgencyid() != null) {
            sql.SET("agencyId = #{agencyid,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getAreacode() != null) {
            sql.SET("areaCode = #{areacode,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            sql.SET("mobile = #{mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getIsprc() != null) {
            sql.SET("isPrc = #{isprc,jdbcType=INTEGER}");
        }
        
        if (record.getServicecount() != null) {
            sql.SET("serviceCount = #{servicecount,jdbcType=INTEGER}");
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
        
        sql.WHERE("agencyGuideId = #{agencyguideid,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agencyguide`
     *
     * @mbggenerated
     */
    protected void applyWhere(SQL sql, FinalAgencyguideCriteria example, boolean includeExamplePhrase) {
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