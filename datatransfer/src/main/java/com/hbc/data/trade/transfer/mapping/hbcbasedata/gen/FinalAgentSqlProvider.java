package com.hbc.data.trade.transfer.mapping.hbcbasedata.gen;

import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgent;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgentCriteria.Criteria;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgentCriteria.Criterion;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgentCriteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class FinalAgentSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agent`
     *
     * @mbggenerated
     */
    public String countByExample(FinalAgentCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("`agent`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agent`
     *
     * @mbggenerated
     */
    public String deleteByExample(FinalAgentCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("`agent`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agent`
     *
     * @mbggenerated
     */
    public String insertSelective(FinalAgent record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`agent`");
        
        if (record.getAgentid() != null) {
            sql.VALUES("agentId", "#{agentid,jdbcType=INTEGER}");
        }
        
        if (record.getAgentname() != null) {
            sql.VALUES("agentName", "#{agentname,jdbcType=VARCHAR}");
        }
        
        if (record.getAgentadminid() != null) {
            sql.VALUES("agentAdminId", "#{agentadminid,jdbcType=CHAR}");
        }
        
        if (record.getAgentkey() != null) {
            sql.VALUES("agentKey", "#{agentkey,jdbcType=VARCHAR}");
        }
        
        if (record.getToken() != null) {
            sql.VALUES("token", "#{token,jdbcType=VARCHAR}");
        }
        
        if (record.getEpwd() != null) {
            sql.VALUES("epwd", "#{epwd,jdbcType=VARCHAR}");
        }
        
        if (record.getProvince() != null) {
            sql.VALUES("province", "#{province,jdbcType=INTEGER}");
        }
        
        if (record.getCity() != null) {
            sql.VALUES("city", "#{city,jdbcType=INTEGER}");
        }
        
        if (record.getCounty() != null) {
            sql.VALUES("county", "#{county,jdbcType=INTEGER}");
        }
        
        if (record.getIndustry() != null) {
            sql.VALUES("industry", "#{industry,jdbcType=TINYINT}");
        }
        
        if (record.getBiztype() != null) {
            sql.VALUES("bizType", "#{biztype,jdbcType=INTEGER}");
        }
        
        if (record.getAddress() != null) {
            sql.VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getTel() != null) {
            sql.VALUES("tel", "#{tel,jdbcType=VARCHAR}");
        }
        
        if (record.getContactname() != null) {
            sql.VALUES("contactName", "#{contactname,jdbcType=VARCHAR}");
        }
        
        if (record.getContactmobile() != null) {
            sql.VALUES("contactMobile", "#{contactmobile,jdbcType=VARCHAR}");
        }
        
        if (record.getComment() != null) {
            sql.VALUES("comment", "#{comment,jdbcType=VARCHAR}");
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
     * This method corresponds to the database table `agent`
     *
     * @mbggenerated
     */
    public String selectByExample(FinalAgentCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("agentId");
        } else {
            sql.SELECT("agentId");
        }
        sql.SELECT("agentName");
        sql.SELECT("agentAdminId");
        sql.SELECT("agentKey");
        sql.SELECT("token");
        sql.SELECT("epwd");
        sql.SELECT("province");
        sql.SELECT("city");
        sql.SELECT("county");
        sql.SELECT("industry");
        sql.SELECT("bizType");
        sql.SELECT("address");
        sql.SELECT("tel");
        sql.SELECT("contactName");
        sql.SELECT("contactMobile");
        sql.SELECT("comment");
        sql.SELECT("status");
        sql.SELECT("updated_at");
        sql.SELECT("created_at");
        sql.FROM("`agent`");
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
     * This method corresponds to the database table `agent`
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        FinalAgent record = (FinalAgent) parameter.get("record");
        FinalAgentCriteria example = (FinalAgentCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("`agent`");
        
        if (record.getAgentid() != null) {
            sql.SET("agentId = #{record.agentid,jdbcType=INTEGER}");
        }
        
        if (record.getAgentname() != null) {
            sql.SET("agentName = #{record.agentname,jdbcType=VARCHAR}");
        }
        
        if (record.getAgentadminid() != null) {
            sql.SET("agentAdminId = #{record.agentadminid,jdbcType=CHAR}");
        }
        
        if (record.getAgentkey() != null) {
            sql.SET("agentKey = #{record.agentkey,jdbcType=VARCHAR}");
        }
        
        if (record.getToken() != null) {
            sql.SET("token = #{record.token,jdbcType=VARCHAR}");
        }
        
        if (record.getEpwd() != null) {
            sql.SET("epwd = #{record.epwd,jdbcType=VARCHAR}");
        }
        
        if (record.getProvince() != null) {
            sql.SET("province = #{record.province,jdbcType=INTEGER}");
        }
        
        if (record.getCity() != null) {
            sql.SET("city = #{record.city,jdbcType=INTEGER}");
        }
        
        if (record.getCounty() != null) {
            sql.SET("county = #{record.county,jdbcType=INTEGER}");
        }
        
        if (record.getIndustry() != null) {
            sql.SET("industry = #{record.industry,jdbcType=TINYINT}");
        }
        
        if (record.getBiztype() != null) {
            sql.SET("bizType = #{record.biztype,jdbcType=INTEGER}");
        }
        
        if (record.getAddress() != null) {
            sql.SET("address = #{record.address,jdbcType=VARCHAR}");
        }
        
        if (record.getTel() != null) {
            sql.SET("tel = #{record.tel,jdbcType=VARCHAR}");
        }
        
        if (record.getContactname() != null) {
            sql.SET("contactName = #{record.contactname,jdbcType=VARCHAR}");
        }
        
        if (record.getContactmobile() != null) {
            sql.SET("contactMobile = #{record.contactmobile,jdbcType=VARCHAR}");
        }
        
        if (record.getComment() != null) {
            sql.SET("comment = #{record.comment,jdbcType=VARCHAR}");
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
     * This method corresponds to the database table `agent`
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("`agent`");
        
        sql.SET("agentId = #{record.agentid,jdbcType=INTEGER}");
        sql.SET("agentName = #{record.agentname,jdbcType=VARCHAR}");
        sql.SET("agentAdminId = #{record.agentadminid,jdbcType=CHAR}");
        sql.SET("agentKey = #{record.agentkey,jdbcType=VARCHAR}");
        sql.SET("token = #{record.token,jdbcType=VARCHAR}");
        sql.SET("epwd = #{record.epwd,jdbcType=VARCHAR}");
        sql.SET("province = #{record.province,jdbcType=INTEGER}");
        sql.SET("city = #{record.city,jdbcType=INTEGER}");
        sql.SET("county = #{record.county,jdbcType=INTEGER}");
        sql.SET("industry = #{record.industry,jdbcType=TINYINT}");
        sql.SET("bizType = #{record.biztype,jdbcType=INTEGER}");
        sql.SET("address = #{record.address,jdbcType=VARCHAR}");
        sql.SET("tel = #{record.tel,jdbcType=VARCHAR}");
        sql.SET("contactName = #{record.contactname,jdbcType=VARCHAR}");
        sql.SET("contactMobile = #{record.contactmobile,jdbcType=VARCHAR}");
        sql.SET("comment = #{record.comment,jdbcType=VARCHAR}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("updated_at = #{record.updatedAt,jdbcType=TIMESTAMP}");
        sql.SET("created_at = #{record.createdAt,jdbcType=TIMESTAMP}");
        
        FinalAgentCriteria example = (FinalAgentCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agent`
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(FinalAgent record) {
        SQL sql = new SQL();
        sql.UPDATE("`agent`");
        
        if (record.getAgentname() != null) {
            sql.SET("agentName = #{agentname,jdbcType=VARCHAR}");
        }
        
        if (record.getAgentadminid() != null) {
            sql.SET("agentAdminId = #{agentadminid,jdbcType=CHAR}");
        }
        
        if (record.getAgentkey() != null) {
            sql.SET("agentKey = #{agentkey,jdbcType=VARCHAR}");
        }
        
        if (record.getToken() != null) {
            sql.SET("token = #{token,jdbcType=VARCHAR}");
        }
        
        if (record.getEpwd() != null) {
            sql.SET("epwd = #{epwd,jdbcType=VARCHAR}");
        }
        
        if (record.getProvince() != null) {
            sql.SET("province = #{province,jdbcType=INTEGER}");
        }
        
        if (record.getCity() != null) {
            sql.SET("city = #{city,jdbcType=INTEGER}");
        }
        
        if (record.getCounty() != null) {
            sql.SET("county = #{county,jdbcType=INTEGER}");
        }
        
        if (record.getIndustry() != null) {
            sql.SET("industry = #{industry,jdbcType=TINYINT}");
        }
        
        if (record.getBiztype() != null) {
            sql.SET("bizType = #{biztype,jdbcType=INTEGER}");
        }
        
        if (record.getAddress() != null) {
            sql.SET("address = #{address,jdbcType=VARCHAR}");
        }
        
        if (record.getTel() != null) {
            sql.SET("tel = #{tel,jdbcType=VARCHAR}");
        }
        
        if (record.getContactname() != null) {
            sql.SET("contactName = #{contactname,jdbcType=VARCHAR}");
        }
        
        if (record.getContactmobile() != null) {
            sql.SET("contactMobile = #{contactmobile,jdbcType=VARCHAR}");
        }
        
        if (record.getComment() != null) {
            sql.SET("comment = #{comment,jdbcType=VARCHAR}");
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
        
        sql.WHERE("agentId = #{agentid,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `agent`
     *
     * @mbggenerated
     */
    protected void applyWhere(SQL sql, FinalAgentCriteria example, boolean includeExamplePhrase) {
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