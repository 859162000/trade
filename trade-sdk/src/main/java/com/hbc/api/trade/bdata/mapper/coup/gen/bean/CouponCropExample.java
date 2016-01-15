package com.hbc.api.trade.bdata.mapper.coup.gen.bean;

import com.hbc.api.trade.bdata.common.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponCropExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table `coupon_crop`
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table `coupon_crop`
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table `coupon_crop`
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table `coupon_crop`
     *
     * @mbggenerated
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_crop`
     *
     * @mbggenerated
     */
    public CouponCropExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_crop`
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_crop`
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_crop`
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_crop`
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_crop`
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_crop`
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_crop`
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
     * This method corresponds to the database table `coupon_crop`
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
     * This method corresponds to the database table `coupon_crop`
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_crop`
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
     * This method corresponds to the database table `coupon_crop`
     *
     * @mbggenerated
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `coupon_crop`
     *
     * @mbggenerated
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table `coupon_crop`
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

        public Criteria andCouponCropIdIsNull() {
            addCriterion("coupon_crop_id is null");
            return (Criteria) this;
        }

        public Criteria andCouponCropIdIsNotNull() {
            addCriterion("coupon_crop_id is not null");
            return (Criteria) this;
        }

        public Criteria andCouponCropIdEqualTo(String value) {
            addCriterion("coupon_crop_id =", value, "couponCropId");
            return (Criteria) this;
        }

        public Criteria andCouponCropIdNotEqualTo(String value) {
            addCriterion("coupon_crop_id <>", value, "couponCropId");
            return (Criteria) this;
        }

        public Criteria andCouponCropIdGreaterThan(String value) {
            addCriterion("coupon_crop_id >", value, "couponCropId");
            return (Criteria) this;
        }

        public Criteria andCouponCropIdGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_crop_id >=", value, "couponCropId");
            return (Criteria) this;
        }

        public Criteria andCouponCropIdLessThan(String value) {
            addCriterion("coupon_crop_id <", value, "couponCropId");
            return (Criteria) this;
        }

        public Criteria andCouponCropIdLessThanOrEqualTo(String value) {
            addCriterion("coupon_crop_id <=", value, "couponCropId");
            return (Criteria) this;
        }

        public Criteria andCouponCropIdLike(String value) {
            addCriterion("coupon_crop_id like", value, "couponCropId");
            return (Criteria) this;
        }

        public Criteria andCouponCropIdNotLike(String value) {
            addCriterion("coupon_crop_id not like", value, "couponCropId");
            return (Criteria) this;
        }

        public Criteria andCouponCropIdIn(List<String> values) {
            addCriterion("coupon_crop_id in", values, "couponCropId");
            return (Criteria) this;
        }

        public Criteria andCouponCropIdNotIn(List<String> values) {
            addCriterion("coupon_crop_id not in", values, "couponCropId");
            return (Criteria) this;
        }

        public Criteria andCouponCropIdBetween(String value1, String value2) {
            addCriterion("coupon_crop_id between", value1, value2, "couponCropId");
            return (Criteria) this;
        }

        public Criteria andCouponCropIdNotBetween(String value1, String value2) {
            addCriterion("coupon_crop_id not between", value1, value2, "couponCropId");
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

        public Criteria andCouponBatchIdEqualTo(String value) {
            addCriterion("coupon_batch_id =", value, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdNotEqualTo(String value) {
            addCriterion("coupon_batch_id <>", value, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdGreaterThan(String value) {
            addCriterion("coupon_batch_id >", value, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_batch_id >=", value, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdLessThan(String value) {
            addCriterion("coupon_batch_id <", value, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdLessThanOrEqualTo(String value) {
            addCriterion("coupon_batch_id <=", value, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdLike(String value) {
            addCriterion("coupon_batch_id like", value, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdNotLike(String value) {
            addCriterion("coupon_batch_id not like", value, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdIn(List<String> values) {
            addCriterion("coupon_batch_id in", values, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdNotIn(List<String> values) {
            addCriterion("coupon_batch_id not in", values, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdBetween(String value1, String value2) {
            addCriterion("coupon_batch_id between", value1, value2, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCouponBatchIdNotBetween(String value1, String value2) {
            addCriterion("coupon_batch_id not between", value1, value2, "couponBatchId");
            return (Criteria) this;
        }

        public Criteria andCropTypeIsNull() {
            addCriterion("crop_type is null");
            return (Criteria) this;
        }

        public Criteria andCropTypeIsNotNull() {
            addCriterion("crop_type is not null");
            return (Criteria) this;
        }

        public Criteria andCropTypeEqualTo(Integer value) {
            addCriterion("crop_type =", value, "cropType");
            return (Criteria) this;
        }

        public Criteria andCropTypeNotEqualTo(Integer value) {
            addCriterion("crop_type <>", value, "cropType");
            return (Criteria) this;
        }

        public Criteria andCropTypeGreaterThan(Integer value) {
            addCriterion("crop_type >", value, "cropType");
            return (Criteria) this;
        }

        public Criteria andCropTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("crop_type >=", value, "cropType");
            return (Criteria) this;
        }

        public Criteria andCropTypeLessThan(Integer value) {
            addCriterion("crop_type <", value, "cropType");
            return (Criteria) this;
        }

        public Criteria andCropTypeLessThanOrEqualTo(Integer value) {
            addCriterion("crop_type <=", value, "cropType");
            return (Criteria) this;
        }

        public Criteria andCropTypeIn(List<Integer> values) {
            addCriterion("crop_type in", values, "cropType");
            return (Criteria) this;
        }

        public Criteria andCropTypeNotIn(List<Integer> values) {
            addCriterion("crop_type not in", values, "cropType");
            return (Criteria) this;
        }

        public Criteria andCropTypeBetween(Integer value1, Integer value2) {
            addCriterion("crop_type between", value1, value2, "cropType");
            return (Criteria) this;
        }

        public Criteria andCropTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("crop_type not between", value1, value2, "cropType");
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
     * This class corresponds to the database table `coupon_crop`
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
     * This class corresponds to the database table `coupon_crop`
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