package com.hbc.api.trade.bdata.mapper.guide.gen.bean;

import com.hbc.api.trade.bdata.common.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GuideProhibitExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    public GuideProhibitExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andGuideProhibitIdIsNull() {
            addCriterion("guide_prohibit_id is null");
            return (Criteria) this;
        }

        public Criteria andGuideProhibitIdIsNotNull() {
            addCriterion("guide_prohibit_id is not null");
            return (Criteria) this;
        }

        public Criteria andGuideProhibitIdEqualTo(String value) {
            addCriterion("guide_prohibit_id =", value, "guideProhibitId");
            return (Criteria) this;
        }

        public Criteria andGuideProhibitIdNotEqualTo(String value) {
            addCriterion("guide_prohibit_id <>", value, "guideProhibitId");
            return (Criteria) this;
        }

        public Criteria andGuideProhibitIdGreaterThan(String value) {
            addCriterion("guide_prohibit_id >", value, "guideProhibitId");
            return (Criteria) this;
        }

        public Criteria andGuideProhibitIdGreaterThanOrEqualTo(String value) {
            addCriterion("guide_prohibit_id >=", value, "guideProhibitId");
            return (Criteria) this;
        }

        public Criteria andGuideProhibitIdLessThan(String value) {
            addCriterion("guide_prohibit_id <", value, "guideProhibitId");
            return (Criteria) this;
        }

        public Criteria andGuideProhibitIdLessThanOrEqualTo(String value) {
            addCriterion("guide_prohibit_id <=", value, "guideProhibitId");
            return (Criteria) this;
        }

        public Criteria andGuideProhibitIdLike(String value) {
            addCriterion("guide_prohibit_id like", value, "guideProhibitId");
            return (Criteria) this;
        }

        public Criteria andGuideProhibitIdNotLike(String value) {
            addCriterion("guide_prohibit_id not like", value, "guideProhibitId");
            return (Criteria) this;
        }

        public Criteria andGuideProhibitIdIn(List<String> values) {
            addCriterion("guide_prohibit_id in", values, "guideProhibitId");
            return (Criteria) this;
        }

        public Criteria andGuideProhibitIdNotIn(List<String> values) {
            addCriterion("guide_prohibit_id not in", values, "guideProhibitId");
            return (Criteria) this;
        }

        public Criteria andGuideProhibitIdBetween(String value1, String value2) {
            addCriterion("guide_prohibit_id between", value1, value2, "guideProhibitId");
            return (Criteria) this;
        }

        public Criteria andGuideProhibitIdNotBetween(String value1, String value2) {
            addCriterion("guide_prohibit_id not between", value1, value2, "guideProhibitId");
            return (Criteria) this;
        }

        public Criteria andGuideIdIsNull() {
            addCriterion("guide_id is null");
            return (Criteria) this;
        }

        public Criteria andGuideIdIsNotNull() {
            addCriterion("guide_id is not null");
            return (Criteria) this;
        }

        public Criteria andGuideIdEqualTo(String value) {
            addCriterion("guide_id =", value, "guideId");
            return (Criteria) this;
        }

        public Criteria andGuideIdNotEqualTo(String value) {
            addCriterion("guide_id <>", value, "guideId");
            return (Criteria) this;
        }

        public Criteria andGuideIdGreaterThan(String value) {
            addCriterion("guide_id >", value, "guideId");
            return (Criteria) this;
        }

        public Criteria andGuideIdGreaterThanOrEqualTo(String value) {
            addCriterion("guide_id >=", value, "guideId");
            return (Criteria) this;
        }

        public Criteria andGuideIdLessThan(String value) {
            addCriterion("guide_id <", value, "guideId");
            return (Criteria) this;
        }

        public Criteria andGuideIdLessThanOrEqualTo(String value) {
            addCriterion("guide_id <=", value, "guideId");
            return (Criteria) this;
        }

        public Criteria andGuideIdLike(String value) {
            addCriterion("guide_id like", value, "guideId");
            return (Criteria) this;
        }

        public Criteria andGuideIdNotLike(String value) {
            addCriterion("guide_id not like", value, "guideId");
            return (Criteria) this;
        }

        public Criteria andGuideIdIn(List<String> values) {
            addCriterion("guide_id in", values, "guideId");
            return (Criteria) this;
        }

        public Criteria andGuideIdNotIn(List<String> values) {
            addCriterion("guide_id not in", values, "guideId");
            return (Criteria) this;
        }

        public Criteria andGuideIdBetween(String value1, String value2) {
            addCriterion("guide_id between", value1, value2, "guideId");
            return (Criteria) this;
        }

        public Criteria andGuideIdNotBetween(String value1, String value2) {
            addCriterion("guide_id not between", value1, value2, "guideId");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterionForJDBCDate("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterionForJDBCDate("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andOpAdminIdIsNull() {
            addCriterion("op_admin_id is null");
            return (Criteria) this;
        }

        public Criteria andOpAdminIdIsNotNull() {
            addCriterion("op_admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpAdminIdEqualTo(String value) {
            addCriterion("op_admin_id =", value, "opAdminId");
            return (Criteria) this;
        }

        public Criteria andOpAdminIdNotEqualTo(String value) {
            addCriterion("op_admin_id <>", value, "opAdminId");
            return (Criteria) this;
        }

        public Criteria andOpAdminIdGreaterThan(String value) {
            addCriterion("op_admin_id >", value, "opAdminId");
            return (Criteria) this;
        }

        public Criteria andOpAdminIdGreaterThanOrEqualTo(String value) {
            addCriterion("op_admin_id >=", value, "opAdminId");
            return (Criteria) this;
        }

        public Criteria andOpAdminIdLessThan(String value) {
            addCriterion("op_admin_id <", value, "opAdminId");
            return (Criteria) this;
        }

        public Criteria andOpAdminIdLessThanOrEqualTo(String value) {
            addCriterion("op_admin_id <=", value, "opAdminId");
            return (Criteria) this;
        }

        public Criteria andOpAdminIdLike(String value) {
            addCriterion("op_admin_id like", value, "opAdminId");
            return (Criteria) this;
        }

        public Criteria andOpAdminIdNotLike(String value) {
            addCriterion("op_admin_id not like", value, "opAdminId");
            return (Criteria) this;
        }

        public Criteria andOpAdminIdIn(List<String> values) {
            addCriterion("op_admin_id in", values, "opAdminId");
            return (Criteria) this;
        }

        public Criteria andOpAdminIdNotIn(List<String> values) {
            addCriterion("op_admin_id not in", values, "opAdminId");
            return (Criteria) this;
        }

        public Criteria andOpAdminIdBetween(String value1, String value2) {
            addCriterion("op_admin_id between", value1, value2, "opAdminId");
            return (Criteria) this;
        }

        public Criteria andOpAdminIdNotBetween(String value1, String value2) {
            addCriterion("op_admin_id not between", value1, value2, "opAdminId");
            return (Criteria) this;
        }

        public Criteria andOpAdminNameIsNull() {
            addCriterion("op_admin_name is null");
            return (Criteria) this;
        }

        public Criteria andOpAdminNameIsNotNull() {
            addCriterion("op_admin_name is not null");
            return (Criteria) this;
        }

        public Criteria andOpAdminNameEqualTo(String value) {
            addCriterion("op_admin_name =", value, "opAdminName");
            return (Criteria) this;
        }

        public Criteria andOpAdminNameNotEqualTo(String value) {
            addCriterion("op_admin_name <>", value, "opAdminName");
            return (Criteria) this;
        }

        public Criteria andOpAdminNameGreaterThan(String value) {
            addCriterion("op_admin_name >", value, "opAdminName");
            return (Criteria) this;
        }

        public Criteria andOpAdminNameGreaterThanOrEqualTo(String value) {
            addCriterion("op_admin_name >=", value, "opAdminName");
            return (Criteria) this;
        }

        public Criteria andOpAdminNameLessThan(String value) {
            addCriterion("op_admin_name <", value, "opAdminName");
            return (Criteria) this;
        }

        public Criteria andOpAdminNameLessThanOrEqualTo(String value) {
            addCriterion("op_admin_name <=", value, "opAdminName");
            return (Criteria) this;
        }

        public Criteria andOpAdminNameLike(String value) {
            addCriterion("op_admin_name like", value, "opAdminName");
            return (Criteria) this;
        }

        public Criteria andOpAdminNameNotLike(String value) {
            addCriterion("op_admin_name not like", value, "opAdminName");
            return (Criteria) this;
        }

        public Criteria andOpAdminNameIn(List<String> values) {
            addCriterion("op_admin_name in", values, "opAdminName");
            return (Criteria) this;
        }

        public Criteria andOpAdminNameNotIn(List<String> values) {
            addCriterion("op_admin_name not in", values, "opAdminName");
            return (Criteria) this;
        }

        public Criteria andOpAdminNameBetween(String value1, String value2) {
            addCriterion("op_admin_name between", value1, value2, "opAdminName");
            return (Criteria) this;
        }

        public Criteria andOpAdminNameNotBetween(String value1, String value2) {
            addCriterion("op_admin_name not between", value1, value2, "opAdminName");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterionForJDBCDate("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table `guide_prohibit`
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}