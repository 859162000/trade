package com.hbc.api.trade.bdata.mapper.guide.gen;

import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideComment;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCommentExample.Criteria;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCommentExample.Criterion;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCommentExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class GuideCommentSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_comment`
     *
     * @mbggenerated
     */
    public String countByExample(GuideCommentExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("`guide_comment`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_comment`
     *
     * @mbggenerated
     */
    public String deleteByExample(GuideCommentExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("`guide_comment`");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_comment`
     *
     * @mbggenerated
     */
    public String insertSelective(GuideComment record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`guide_comment`");
        
        if (record.getGuideCommentId() != null) {
            sql.VALUES("guide_comment_id", "#{guideCommentId,jdbcType=INTEGER}");
        }
        
        if (record.getOrderNo() != null) {
            sql.VALUES("order_no", "#{orderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderType() != null) {
            sql.VALUES("order_type", "#{orderType,jdbcType=TINYINT}");
        }
        
        if (record.getSceneryNarrate() != null) {
            sql.VALUES("scenery_narrate", "#{sceneryNarrate,jdbcType=DECIMAL}");
        }
        
        if (record.getServiceAttitude() != null) {
            sql.VALUES("service_attitude", "#{serviceAttitude,jdbcType=DECIMAL}");
        }
        
        if (record.getRouteFamiliar() != null) {
            sql.VALUES("route_familiar", "#{routeFamiliar,jdbcType=DECIMAL}");
        }
        
        if (record.getFromUid() != null) {
            sql.VALUES("from_uid", "#{fromUid,jdbcType=VARCHAR}");
        }
        
        if (record.getFromUname() != null) {
            sql.VALUES("from_uname", "#{fromUname,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideId() != null) {
            sql.VALUES("guide_id", "#{guideId,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideName() != null) {
            sql.VALUES("guide_name", "#{guideName,jdbcType=VARCHAR}");
        }
        
        if (record.getCommentType() != null) {
            sql.VALUES("comment_type", "#{commentType,jdbcType=TINYINT}");
        }
        
        if (record.getKpi1() != null) {
            sql.VALUES("kpi1", "#{kpi1,jdbcType=TINYINT}");
        }
        
        if (record.getKpi2() != null) {
            sql.VALUES("kpi2", "#{kpi2,jdbcType=TINYINT}");
        }
        
        if (record.getKpi3() != null) {
            sql.VALUES("kpi3", "#{kpi3,jdbcType=TINYINT}");
        }
        
        if (record.getKpi4() != null) {
            sql.VALUES("kpi4", "#{kpi4,jdbcType=TINYINT}");
        }
        
        if (record.getKpi5() != null) {
            sql.VALUES("kpi5", "#{kpi5,jdbcType=TINYINT}");
        }
        
        if (record.getKpi6() != null) {
            sql.VALUES("kpi6", "#{kpi6,jdbcType=TINYINT}");
        }
        
        if (record.getTotalScore() != null) {
            sql.VALUES("total_score", "#{totalScore,jdbcType=DECIMAL}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_comment`
     *
     * @mbggenerated
     */
    public String selectByExampleWithBLOBs(GuideCommentExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("guide_comment_id");
        } else {
            sql.SELECT("guide_comment_id");
        }
        sql.SELECT("order_no");
        sql.SELECT("order_type");
        sql.SELECT("scenery_narrate");
        sql.SELECT("service_attitude");
        sql.SELECT("route_familiar");
        sql.SELECT("from_uid");
        sql.SELECT("from_uname");
        sql.SELECT("guide_id");
        sql.SELECT("guide_name");
        sql.SELECT("comment_type");
        sql.SELECT("kpi1");
        sql.SELECT("kpi2");
        sql.SELECT("kpi3");
        sql.SELECT("kpi4");
        sql.SELECT("kpi5");
        sql.SELECT("kpi6");
        sql.SELECT("total_score");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
        sql.SELECT("content");
        sql.FROM("`guide_comment`");
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
     * This method corresponds to the database table `guide_comment`
     *
     * @mbggenerated
     */
    public String selectByExample(GuideCommentExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("guide_comment_id");
        } else {
            sql.SELECT("guide_comment_id");
        }
        sql.SELECT("order_no");
        sql.SELECT("order_type");
        sql.SELECT("scenery_narrate");
        sql.SELECT("service_attitude");
        sql.SELECT("route_familiar");
        sql.SELECT("from_uid");
        sql.SELECT("from_uname");
        sql.SELECT("guide_id");
        sql.SELECT("guide_name");
        sql.SELECT("comment_type");
        sql.SELECT("kpi1");
        sql.SELECT("kpi2");
        sql.SELECT("kpi3");
        sql.SELECT("kpi4");
        sql.SELECT("kpi5");
        sql.SELECT("kpi6");
        sql.SELECT("total_score");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
        sql.FROM("`guide_comment`");
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
     * This method corresponds to the database table `guide_comment`
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        GuideComment record = (GuideComment) parameter.get("record");
        GuideCommentExample example = (GuideCommentExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("`guide_comment`");
        
        if (record.getGuideCommentId() != null) {
            sql.SET("guide_comment_id = #{record.guideCommentId,jdbcType=INTEGER}");
        }
        
        if (record.getOrderNo() != null) {
            sql.SET("order_no = #{record.orderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderType() != null) {
            sql.SET("order_type = #{record.orderType,jdbcType=TINYINT}");
        }
        
        if (record.getSceneryNarrate() != null) {
            sql.SET("scenery_narrate = #{record.sceneryNarrate,jdbcType=DECIMAL}");
        }
        
        if (record.getServiceAttitude() != null) {
            sql.SET("service_attitude = #{record.serviceAttitude,jdbcType=DECIMAL}");
        }
        
        if (record.getRouteFamiliar() != null) {
            sql.SET("route_familiar = #{record.routeFamiliar,jdbcType=DECIMAL}");
        }
        
        if (record.getFromUid() != null) {
            sql.SET("from_uid = #{record.fromUid,jdbcType=VARCHAR}");
        }
        
        if (record.getFromUname() != null) {
            sql.SET("from_uname = #{record.fromUname,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideId() != null) {
            sql.SET("guide_id = #{record.guideId,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideName() != null) {
            sql.SET("guide_name = #{record.guideName,jdbcType=VARCHAR}");
        }
        
        if (record.getCommentType() != null) {
            sql.SET("comment_type = #{record.commentType,jdbcType=TINYINT}");
        }
        
        if (record.getKpi1() != null) {
            sql.SET("kpi1 = #{record.kpi1,jdbcType=TINYINT}");
        }
        
        if (record.getKpi2() != null) {
            sql.SET("kpi2 = #{record.kpi2,jdbcType=TINYINT}");
        }
        
        if (record.getKpi3() != null) {
            sql.SET("kpi3 = #{record.kpi3,jdbcType=TINYINT}");
        }
        
        if (record.getKpi4() != null) {
            sql.SET("kpi4 = #{record.kpi4,jdbcType=TINYINT}");
        }
        
        if (record.getKpi5() != null) {
            sql.SET("kpi5 = #{record.kpi5,jdbcType=TINYINT}");
        }
        
        if (record.getKpi6() != null) {
            sql.SET("kpi6 = #{record.kpi6,jdbcType=TINYINT}");
        }
        
        if (record.getTotalScore() != null) {
            sql.SET("total_score = #{record.totalScore,jdbcType=DECIMAL}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{record.content,jdbcType=LONGVARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_comment`
     *
     * @mbggenerated
     */
    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("`guide_comment`");
        
        sql.SET("guide_comment_id = #{record.guideCommentId,jdbcType=INTEGER}");
        sql.SET("order_no = #{record.orderNo,jdbcType=VARCHAR}");
        sql.SET("order_type = #{record.orderType,jdbcType=TINYINT}");
        sql.SET("scenery_narrate = #{record.sceneryNarrate,jdbcType=DECIMAL}");
        sql.SET("service_attitude = #{record.serviceAttitude,jdbcType=DECIMAL}");
        sql.SET("route_familiar = #{record.routeFamiliar,jdbcType=DECIMAL}");
        sql.SET("from_uid = #{record.fromUid,jdbcType=VARCHAR}");
        sql.SET("from_uname = #{record.fromUname,jdbcType=VARCHAR}");
        sql.SET("guide_id = #{record.guideId,jdbcType=VARCHAR}");
        sql.SET("guide_name = #{record.guideName,jdbcType=VARCHAR}");
        sql.SET("comment_type = #{record.commentType,jdbcType=TINYINT}");
        sql.SET("kpi1 = #{record.kpi1,jdbcType=TINYINT}");
        sql.SET("kpi2 = #{record.kpi2,jdbcType=TINYINT}");
        sql.SET("kpi3 = #{record.kpi3,jdbcType=TINYINT}");
        sql.SET("kpi4 = #{record.kpi4,jdbcType=TINYINT}");
        sql.SET("kpi5 = #{record.kpi5,jdbcType=TINYINT}");
        sql.SET("kpi6 = #{record.kpi6,jdbcType=TINYINT}");
        sql.SET("total_score = #{record.totalScore,jdbcType=DECIMAL}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("content = #{record.content,jdbcType=LONGVARCHAR}");
        
        GuideCommentExample example = (GuideCommentExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_comment`
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("`guide_comment`");
        
        sql.SET("guide_comment_id = #{record.guideCommentId,jdbcType=INTEGER}");
        sql.SET("order_no = #{record.orderNo,jdbcType=VARCHAR}");
        sql.SET("order_type = #{record.orderType,jdbcType=TINYINT}");
        sql.SET("scenery_narrate = #{record.sceneryNarrate,jdbcType=DECIMAL}");
        sql.SET("service_attitude = #{record.serviceAttitude,jdbcType=DECIMAL}");
        sql.SET("route_familiar = #{record.routeFamiliar,jdbcType=DECIMAL}");
        sql.SET("from_uid = #{record.fromUid,jdbcType=VARCHAR}");
        sql.SET("from_uname = #{record.fromUname,jdbcType=VARCHAR}");
        sql.SET("guide_id = #{record.guideId,jdbcType=VARCHAR}");
        sql.SET("guide_name = #{record.guideName,jdbcType=VARCHAR}");
        sql.SET("comment_type = #{record.commentType,jdbcType=TINYINT}");
        sql.SET("kpi1 = #{record.kpi1,jdbcType=TINYINT}");
        sql.SET("kpi2 = #{record.kpi2,jdbcType=TINYINT}");
        sql.SET("kpi3 = #{record.kpi3,jdbcType=TINYINT}");
        sql.SET("kpi4 = #{record.kpi4,jdbcType=TINYINT}");
        sql.SET("kpi5 = #{record.kpi5,jdbcType=TINYINT}");
        sql.SET("kpi6 = #{record.kpi6,jdbcType=TINYINT}");
        sql.SET("total_score = #{record.totalScore,jdbcType=DECIMAL}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        
        GuideCommentExample example = (GuideCommentExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_comment`
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(GuideComment record) {
        SQL sql = new SQL();
        sql.UPDATE("`guide_comment`");
        
        if (record.getOrderNo() != null) {
            sql.SET("order_no = #{orderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderType() != null) {
            sql.SET("order_type = #{orderType,jdbcType=TINYINT}");
        }
        
        if (record.getSceneryNarrate() != null) {
            sql.SET("scenery_narrate = #{sceneryNarrate,jdbcType=DECIMAL}");
        }
        
        if (record.getServiceAttitude() != null) {
            sql.SET("service_attitude = #{serviceAttitude,jdbcType=DECIMAL}");
        }
        
        if (record.getRouteFamiliar() != null) {
            sql.SET("route_familiar = #{routeFamiliar,jdbcType=DECIMAL}");
        }
        
        if (record.getFromUid() != null) {
            sql.SET("from_uid = #{fromUid,jdbcType=VARCHAR}");
        }
        
        if (record.getFromUname() != null) {
            sql.SET("from_uname = #{fromUname,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideId() != null) {
            sql.SET("guide_id = #{guideId,jdbcType=VARCHAR}");
        }
        
        if (record.getGuideName() != null) {
            sql.SET("guide_name = #{guideName,jdbcType=VARCHAR}");
        }
        
        if (record.getCommentType() != null) {
            sql.SET("comment_type = #{commentType,jdbcType=TINYINT}");
        }
        
        if (record.getKpi1() != null) {
            sql.SET("kpi1 = #{kpi1,jdbcType=TINYINT}");
        }
        
        if (record.getKpi2() != null) {
            sql.SET("kpi2 = #{kpi2,jdbcType=TINYINT}");
        }
        
        if (record.getKpi3() != null) {
            sql.SET("kpi3 = #{kpi3,jdbcType=TINYINT}");
        }
        
        if (record.getKpi4() != null) {
            sql.SET("kpi4 = #{kpi4,jdbcType=TINYINT}");
        }
        
        if (record.getKpi5() != null) {
            sql.SET("kpi5 = #{kpi5,jdbcType=TINYINT}");
        }
        
        if (record.getKpi6() != null) {
            sql.SET("kpi6 = #{kpi6,jdbcType=TINYINT}");
        }
        
        if (record.getTotalScore() != null) {
            sql.SET("total_score = #{totalScore,jdbcType=DECIMAL}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=LONGVARCHAR}");
        }
        
        sql.WHERE("guide_comment_id = #{guideCommentId,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_comment`
     *
     * @mbggenerated
     */
    protected void applyWhere(SQL sql, GuideCommentExample example, boolean includeExamplePhrase) {
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