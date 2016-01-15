package com.hbc.api.trade.bdata.mapper.coup.gen.bean;

import com.hbc.api.trade.bdata.common.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponDistrictExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table `coupon_district`
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table `coupon_district`
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table `coupon_district`
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table `coupon_district`
     *
     * @mbggenerated
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_district`
     *
     * @mbggenerated
     */
    public CouponDistrictExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_district`
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_district`
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_district`
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_district`
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_district`
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_district`
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_district`
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
     * This method corresponds to the database table `coupon_district`
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
     * This method corresponds to the database table `coupon_district`
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_district`
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
     * This method corresponds to the database table `coupon_district`
     *
     * @mbggenerated
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_district`
     *
     * @mbggenerated
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table `coupon_district`
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

        public Criteria andCouponDistirctIdIsNull() {
            addCriterion("coupon_distirct_id is null");
            return (Criteria) this;
        }

        public Criteria andCouponDistirctIdIsNotNull() {
            addCriterion("coupon_distirct_id is not null");
            return (Criteria) this;
        }

        public Criteria andCouponDistirctIdEqualTo(Integer value) {
            addCriterion("coupon_distirct_id =", value, "couponDistirctId");
            return (Criteria) this;
        }

        public Criteria andCouponDistirctIdNotEqualTo(Integer value) {
            addCriterion("coupon_distirct_id <>", value, "couponDistirctId");
            return (Criteria) this;
        }

        public Criteria andCouponDistirctIdGreaterThan(Integer value) {
            addCriterion("coupon_distirct_id >", value, "couponDistirctId");
            return (Criteria) this;
        }

        public Criteria andCouponDistirctIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_distirct_id >=", value, "couponDistirctId");
            return (Criteria) this;
        }

        public Criteria andCouponDistirctIdLessThan(Integer value) {
            addCriterion("coupon_distirct_id <", value, "couponDistirctId");
            return (Criteria) this;
        }

        public Criteria andCouponDistirctIdLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_distirct_id <=", value, "couponDistirctId");
            return (Criteria) this;
        }

        public Criteria andCouponDistirctIdIn(List<Integer> values) {
            addCriterion("coupon_distirct_id in", values, "couponDistirctId");
            return (Criteria) this;
        }

        public Criteria andCouponDistirctIdNotIn(List<Integer> values) {
            addCriterion("coupon_distirct_id not in", values, "couponDistirctId");
            return (Criteria) this;
        }

        public Criteria andCouponDistirctIdBetween(Integer value1, Integer value2) {
            addCriterion("coupon_distirct_id between", value1, value2, "couponDistirctId");
            return (Criteria) this;
        }

        public Criteria andCouponDistirctIdNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_distirct_id not between", value1, value2, "couponDistirctId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdIsNull() {
            addCriterion("coupon_batch_id is null");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdIsNotNull() {
            addCriterion("coupon_batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdEqualTo(Integer value) {
            addCriterion("coupon_batch_id =", value, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdNotEqualTo(Integer value) {
            addCriterion("coupon_batch_id <>", value, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdGreaterThan(Integer value) {
            addCriterion("coupon_batch_id >", value, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_batch_id >=", value, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdLessThan(Integer value) {
            addCriterion("coupon_batch_id <", value, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_batch_id <=", value, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdIn(List<Integer> values) {
            addCriterion("coupon_batch_id in", values, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdNotIn(List<Integer> values) {
            addCriterion("coupon_batch_id not in", values, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdBetween(Integer value1, Integer value2) {
            addCriterion("coupon_batch_id between", value1, value2, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_batch_id not between", value1, value2, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andDistrictTypeIsNull() {
            addCriterion("district_type is null");
            return (Criteria) this;
        }

        public Criteria andDistrictTypeIsNotNull() {
            addCriterion("district_type is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictTypeEqualTo(Integer value) {
            addCriterion("district_type =", value, "districtType");
            return (Criteria) this;
        }

        public Criteria andDistrictTypeNotEqualTo(Integer value) {
            addCriterion("district_type <>", value, "districtType");
            return (Criteria) this;
        }

        public Criteria andDistrictTypeGreaterThan(Integer value) {
            addCriterion("district_type >", value, "districtType");
            return (Criteria) this;
        }

        public Criteria andDistrictTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("district_type >=", value, "districtType");
            return (Criteria) this;
        }

        public Criteria andDistrictTypeLessThan(Integer value) {
            addCriterion("district_type <", value, "districtType");
            return (Criteria) this;
        }

        public Criteria andDistrictTypeLessThanOrEqualTo(Integer value) {
            addCriterion("district_type <=", value, "districtType");
            return (Criteria) this;
        }

        public Criteria andDistrictTypeIn(List<Integer> values) {
            addCriterion("district_type in", values, "districtType");
            return (Criteria) this;
        }

        public Criteria andDistrictTypeNotIn(List<Integer> values) {
            addCriterion("district_type not in", values, "districtType");
            return (Criteria) this;
        }

        public Criteria andDistrictTypeBetween(Integer value1, Integer value2) {
            addCriterion("district_type between", value1, value2, "districtType");
            return (Criteria) this;
        }

        public Criteria andDistrictTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("district_type not between", value1, value2, "districtType");
            return (Criteria) this;
        }

        public Criteria andDistrictIdIsNull() {
            addCriterion("district_id is null");
            return (Criteria) this;
        }

        public Criteria andDistrictIdIsNotNull() {
            addCriterion("district_id is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictIdEqualTo(Integer value) {
            addCriterion("district_id =", value, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdNotEqualTo(Integer value) {
            addCriterion("district_id <>", value, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdGreaterThan(Integer value) {
            addCriterion("district_id >", value, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("district_id >=", value, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdLessThan(Integer value) {
            addCriterion("district_id <", value, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdLessThanOrEqualTo(Integer value) {
            addCriterion("district_id <=", value, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdIn(List<Integer> values) {
            addCriterion("district_id in", values, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdNotIn(List<Integer> values) {
            addCriterion("district_id not in", values, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdBetween(Integer value1, Integer value2) {
            addCriterion("district_id between", value1, value2, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdNotBetween(Integer value1, Integer value2) {
            addCriterion("district_id not between", value1, value2, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictNameIsNull() {
            addCriterion("district_name is null");
            return (Criteria) this;
        }

        public Criteria andDistrictNameIsNotNull() {
            addCriterion("district_name is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictNameEqualTo(String value) {
            addCriterion("district_name =", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameNotEqualTo(String value) {
            addCriterion("district_name <>", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameGreaterThan(String value) {
            addCriterion("district_name >", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameGreaterThanOrEqualTo(String value) {
            addCriterion("district_name >=", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameLessThan(String value) {
            addCriterion("district_name <", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameLessThanOrEqualTo(String value) {
            addCriterion("district_name <=", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameLike(String value) {
            addCriterion("district_name like", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameNotLike(String value) {
            addCriterion("district_name not like", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameIn(List<String> values) {
            addCriterion("district_name in", values, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameNotIn(List<String> values) {
            addCriterion("district_name not in", values, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameBetween(String value1, String value2) {
            addCriterion("district_name between", value1, value2, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameNotBetween(String value1, String value2) {
            addCriterion("district_name not between", value1, value2, "districtName");
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
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table `coupon_district`
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
     * This class corresponds to the database table `coupon_district`
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