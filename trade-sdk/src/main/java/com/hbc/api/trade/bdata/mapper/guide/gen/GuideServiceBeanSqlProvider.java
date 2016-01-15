package com.hbc.api.trade.bdata.mapper.guide.gen;

import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideServiceBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideServiceBeanExample.Criteria;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideServiceBeanExample.Criterion;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideServiceBeanExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class GuideServiceBeanSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    public String countByExample(GuideServiceBeanExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("`guide_service`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    public String deleteByExample(GuideServiceBeanExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("`guide_service`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    public String insertSelective(GuideServiceBean record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`guide_service`");
        
        if (record.getGuideServiceId() != null) {
            sql.VALUES("guide_service_id", "#{guideServiceId,jdbcType=INTEGER}");
        }
        
        if (record.getGuideId() != null) {
            sql.VALUES("guide_id", "#{guideId,jdbcType=VARCHAR}");
        }
        
        if (record.getLangCodes() != null) {
            sql.VALUES("lang_codes", "#{langCodes,jdbcType=VARCHAR}");
        }
        
        if (record.getExpfamily() != null) {
            sql.VALUES("expFamily", "#{expfamily,jdbcType=TINYINT}");
        }
        
        if (record.getExpbusiness() != null) {
            sql.VALUES("expBusiness", "#{expbusiness,jdbcType=TINYINT}");
        }
        
        if (record.getExpcross() != null) {
            sql.VALUES("expCross", "#{expcross,jdbcType=TINYINT}");
        }
        
        if (record.getStyle() != null) {
            sql.VALUES("style", "#{style,jdbcType=TINYINT}");
        }
        
        if (record.getExpspot() != null) {
            sql.VALUES("expSpot", "#{expspot,jdbcType=TINYINT}");
        }
        
        if (record.getExpschool() != null) {
            sql.VALUES("expSchool", "#{expschool,jdbcType=TINYINT}");
        }
        
        if (record.getExpshopping() != null) {
            sql.VALUES("expShopping", "#{expshopping,jdbcType=TINYINT}");
        }
        
        if (record.getExphistory() != null) {
            sql.VALUES("expHistory", "#{exphistory,jdbcType=TINYINT}");
        }
        
        if (record.getExptraffic() != null) {
            sql.VALUES("expTraffic", "#{exptraffic,jdbcType=TINYINT}");
        }
        
        if (record.getExpfood() != null) {
            sql.VALUES("expFood", "#{expfood,jdbcType=TINYINT}");
        }
        
        if (record.getExplaw() != null) {
            sql.VALUES("expLaw", "#{explaw,jdbcType=TINYINT}");
        }
        
        if (record.getExpculture() != null) {
            sql.VALUES("expCulture", "#{expculture,jdbcType=TINYINT}");
        }
        
        if (record.getExphotel() != null) {
            sql.VALUES("expHotel", "#{exphotel,jdbcType=TINYINT}");
        }
        
        if (record.getExppoi() != null) {
            sql.VALUES("expPoi", "#{exppoi,jdbcType=TINYINT}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    public String selectByExample(GuideServiceBeanExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("guide_service_id");
        } else {
            sql.SELECT("guide_service_id");
        }
        sql.SELECT("guide_id");
        sql.SELECT("lang_codes");
        sql.SELECT("expFamily");
        sql.SELECT("expBusiness");
        sql.SELECT("expCross");
        sql.SELECT("style");
        sql.SELECT("expSpot");
        sql.SELECT("expSchool");
        sql.SELECT("expShopping");
        sql.SELECT("expHistory");
        sql.SELECT("expTraffic");
        sql.SELECT("expFood");
        sql.SELECT("expLaw");
        sql.SELECT("expCulture");
        sql.SELECT("expHotel");
        sql.SELECT("expPoi");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
        sql.FROM("`guide_service`");
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
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        GuideServiceBean record = (GuideServiceBean) parameter.get("record");
        GuideServiceBeanExample example = (GuideServiceBeanExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("`guide_service`");
        
        if (record.getGuideServiceId() != null) {
            sql.SET("guide_service_id = #{record.guideServiceId,jdbcType=INTEGER}");
        }
        
        if (record.getGuideId() != null) {
            sql.SET("guide_id = #{record.guideId,jdbcType=VARCHAR}");
        }
        
        if (record.getLangCodes() != null) {
            sql.SET("lang_codes = #{record.langCodes,jdbcType=VARCHAR}");
        }
        
        if (record.getExpfamily() != null) {
            sql.SET("expFamily = #{record.expfamily,jdbcType=TINYINT}");
        }
        
        if (record.getExpbusiness() != null) {
            sql.SET("expBusiness = #{record.expbusiness,jdbcType=TINYINT}");
        }
        
        if (record.getExpcross() != null) {
            sql.SET("expCross = #{record.expcross,jdbcType=TINYINT}");
        }
        
        if (record.getStyle() != null) {
            sql.SET("style = #{record.style,jdbcType=TINYINT}");
        }
        
        if (record.getExpspot() != null) {
            sql.SET("expSpot = #{record.expspot,jdbcType=TINYINT}");
        }
        
        if (record.getExpschool() != null) {
            sql.SET("expSchool = #{record.expschool,jdbcType=TINYINT}");
        }
        
        if (record.getExpshopping() != null) {
            sql.SET("expShopping = #{record.expshopping,jdbcType=TINYINT}");
        }
        
        if (record.getExphistory() != null) {
            sql.SET("expHistory = #{record.exphistory,jdbcType=TINYINT}");
        }
        
        if (record.getExptraffic() != null) {
            sql.SET("expTraffic = #{record.exptraffic,jdbcType=TINYINT}");
        }
        
        if (record.getExpfood() != null) {
            sql.SET("expFood = #{record.expfood,jdbcType=TINYINT}");
        }
        
        if (record.getExplaw() != null) {
            sql.SET("expLaw = #{record.explaw,jdbcType=TINYINT}");
        }
        
        if (record.getExpculture() != null) {
            sql.SET("expCulture = #{record.expculture,jdbcType=TINYINT}");
        }
        
        if (record.getExphotel() != null) {
            sql.SET("expHotel = #{record.exphotel,jdbcType=TINYINT}");
        }
        
        if (record.getExppoi() != null) {
            sql.SET("expPoi = #{record.exppoi,jdbcType=TINYINT}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("`guide_service`");
        
        sql.SET("guide_service_id = #{record.guideServiceId,jdbcType=INTEGER}");
        sql.SET("guide_id = #{record.guideId,jdbcType=VARCHAR}");
        sql.SET("lang_codes = #{record.langCodes,jdbcType=VARCHAR}");
        sql.SET("expFamily = #{record.expfamily,jdbcType=TINYINT}");
        sql.SET("expBusiness = #{record.expbusiness,jdbcType=TINYINT}");
        sql.SET("expCross = #{record.expcross,jdbcType=TINYINT}");
        sql.SET("style = #{record.style,jdbcType=TINYINT}");
        sql.SET("expSpot = #{record.expspot,jdbcType=TINYINT}");
        sql.SET("expSchool = #{record.expschool,jdbcType=TINYINT}");
        sql.SET("expShopping = #{record.expshopping,jdbcType=TINYINT}");
        sql.SET("expHistory = #{record.exphistory,jdbcType=TINYINT}");
        sql.SET("expTraffic = #{record.exptraffic,jdbcType=TINYINT}");
        sql.SET("expFood = #{record.expfood,jdbcType=TINYINT}");
        sql.SET("expLaw = #{record.explaw,jdbcType=TINYINT}");
        sql.SET("expCulture = #{record.expculture,jdbcType=TINYINT}");
        sql.SET("expHotel = #{record.exphotel,jdbcType=TINYINT}");
        sql.SET("expPoi = #{record.exppoi,jdbcType=TINYINT}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        
        GuideServiceBeanExample example = (GuideServiceBeanExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(GuideServiceBean record) {
        SQL sql = new SQL();
        sql.UPDATE("`guide_service`");
        
        if (record.getGuideId() != null) {
            sql.SET("guide_id = #{guideId,jdbcType=VARCHAR}");
        }
        
        if (record.getLangCodes() != null) {
            sql.SET("lang_codes = #{langCodes,jdbcType=VARCHAR}");
        }
        
        if (record.getExpfamily() != null) {
            sql.SET("expFamily = #{expfamily,jdbcType=TINYINT}");
        }
        
        if (record.getExpbusiness() != null) {
            sql.SET("expBusiness = #{expbusiness,jdbcType=TINYINT}");
        }
        
        if (record.getExpcross() != null) {
            sql.SET("expCross = #{expcross,jdbcType=TINYINT}");
        }
        
        if (record.getStyle() != null) {
            sql.SET("style = #{style,jdbcType=TINYINT}");
        }
        
        if (record.getExpspot() != null) {
            sql.SET("expSpot = #{expspot,jdbcType=TINYINT}");
        }
        
        if (record.getExpschool() != null) {
            sql.SET("expSchool = #{expschool,jdbcType=TINYINT}");
        }
        
        if (record.getExpshopping() != null) {
            sql.SET("expShopping = #{expshopping,jdbcType=TINYINT}");
        }
        
        if (record.getExphistory() != null) {
            sql.SET("expHistory = #{exphistory,jdbcType=TINYINT}");
        }
        
        if (record.getExptraffic() != null) {
            sql.SET("expTraffic = #{exptraffic,jdbcType=TINYINT}");
        }
        
        if (record.getExpfood() != null) {
            sql.SET("expFood = #{expfood,jdbcType=TINYINT}");
        }
        
        if (record.getExplaw() != null) {
            sql.SET("expLaw = #{explaw,jdbcType=TINYINT}");
        }
        
        if (record.getExpculture() != null) {
            sql.SET("expCulture = #{expculture,jdbcType=TINYINT}");
        }
        
        if (record.getExphotel() != null) {
            sql.SET("expHotel = #{exphotel,jdbcType=TINYINT}");
        }
        
        if (record.getExppoi() != null) {
            sql.SET("expPoi = #{exppoi,jdbcType=TINYINT}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("guide_service_id = #{guideServiceId,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_service`
     *
     * @mbggenerated
     */
    protected void applyWhere(SQL sql, GuideServiceBeanExample example, boolean includeExamplePhrase) {
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