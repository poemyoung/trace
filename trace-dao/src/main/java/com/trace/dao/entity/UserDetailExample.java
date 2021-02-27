package com.trace.dao.entity;

import java.util.ArrayList;
import java.util.List;

public class UserDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserDetailExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        public Criteria andIduserDetailIsNull() {
            addCriterion("iduser_detail is null");
            return (Criteria) this;
        }

        public Criteria andIduserDetailIsNotNull() {
            addCriterion("iduser_detail is not null");
            return (Criteria) this;
        }

        public Criteria andIduserDetailEqualTo(Integer value) {
            addCriterion("iduser_detail =", value, "iduserDetail");
            return (Criteria) this;
        }

        public Criteria andIduserDetailNotEqualTo(Integer value) {
            addCriterion("iduser_detail <>", value, "iduserDetail");
            return (Criteria) this;
        }

        public Criteria andIduserDetailGreaterThan(Integer value) {
            addCriterion("iduser_detail >", value, "iduserDetail");
            return (Criteria) this;
        }

        public Criteria andIduserDetailGreaterThanOrEqualTo(Integer value) {
            addCriterion("iduser_detail >=", value, "iduserDetail");
            return (Criteria) this;
        }

        public Criteria andIduserDetailLessThan(Integer value) {
            addCriterion("iduser_detail <", value, "iduserDetail");
            return (Criteria) this;
        }

        public Criteria andIduserDetailLessThanOrEqualTo(Integer value) {
            addCriterion("iduser_detail <=", value, "iduserDetail");
            return (Criteria) this;
        }

        public Criteria andIduserDetailIn(List<Integer> values) {
            addCriterion("iduser_detail in", values, "iduserDetail");
            return (Criteria) this;
        }

        public Criteria andIduserDetailNotIn(List<Integer> values) {
            addCriterion("iduser_detail not in", values, "iduserDetail");
            return (Criteria) this;
        }

        public Criteria andIduserDetailBetween(Integer value1, Integer value2) {
            addCriterion("iduser_detail between", value1, value2, "iduserDetail");
            return (Criteria) this;
        }

        public Criteria andIduserDetailNotBetween(Integer value1, Integer value2) {
            addCriterion("iduser_detail not between", value1, value2, "iduserDetail");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andAddrIdIsNull() {
            addCriterion("addr_id is null");
            return (Criteria) this;
        }

        public Criteria andAddrIdIsNotNull() {
            addCriterion("addr_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddrIdEqualTo(Integer value) {
            addCriterion("addr_id =", value, "addrId");
            return (Criteria) this;
        }

        public Criteria andAddrIdNotEqualTo(Integer value) {
            addCriterion("addr_id <>", value, "addrId");
            return (Criteria) this;
        }

        public Criteria andAddrIdGreaterThan(Integer value) {
            addCriterion("addr_id >", value, "addrId");
            return (Criteria) this;
        }

        public Criteria andAddrIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("addr_id >=", value, "addrId");
            return (Criteria) this;
        }

        public Criteria andAddrIdLessThan(Integer value) {
            addCriterion("addr_id <", value, "addrId");
            return (Criteria) this;
        }

        public Criteria andAddrIdLessThanOrEqualTo(Integer value) {
            addCriterion("addr_id <=", value, "addrId");
            return (Criteria) this;
        }

        public Criteria andAddrIdIn(List<Integer> values) {
            addCriterion("addr_id in", values, "addrId");
            return (Criteria) this;
        }

        public Criteria andAddrIdNotIn(List<Integer> values) {
            addCriterion("addr_id not in", values, "addrId");
            return (Criteria) this;
        }

        public Criteria andAddrIdBetween(Integer value1, Integer value2) {
            addCriterion("addr_id between", value1, value2, "addrId");
            return (Criteria) this;
        }

        public Criteria andAddrIdNotBetween(Integer value1, Integer value2) {
            addCriterion("addr_id not between", value1, value2, "addrId");
            return (Criteria) this;
        }

        public Criteria andRiskFlagIsNull() {
            addCriterion("risk_flag is null");
            return (Criteria) this;
        }

        public Criteria andRiskFlagIsNotNull() {
            addCriterion("risk_flag is not null");
            return (Criteria) this;
        }

        public Criteria andRiskFlagEqualTo(Integer value) {
            addCriterion("risk_flag =", value, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagNotEqualTo(Integer value) {
            addCriterion("risk_flag <>", value, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagGreaterThan(Integer value) {
            addCriterion("risk_flag >", value, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("risk_flag >=", value, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagLessThan(Integer value) {
            addCriterion("risk_flag <", value, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagLessThanOrEqualTo(Integer value) {
            addCriterion("risk_flag <=", value, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagIn(List<Integer> values) {
            addCriterion("risk_flag in", values, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagNotIn(List<Integer> values) {
            addCriterion("risk_flag not in", values, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagBetween(Integer value1, Integer value2) {
            addCriterion("risk_flag between", value1, value2, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andRiskFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("risk_flag not between", value1, value2, "riskFlag");
            return (Criteria) this;
        }

        public Criteria andTempIsNull() {
            addCriterion("temp is null");
            return (Criteria) this;
        }

        public Criteria andTempIsNotNull() {
            addCriterion("temp is not null");
            return (Criteria) this;
        }

        public Criteria andTempEqualTo(String value) {
            addCriterion("temp =", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempNotEqualTo(String value) {
            addCriterion("temp <>", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempGreaterThan(String value) {
            addCriterion("temp >", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempGreaterThanOrEqualTo(String value) {
            addCriterion("temp >=", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempLessThan(String value) {
            addCriterion("temp <", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempLessThanOrEqualTo(String value) {
            addCriterion("temp <=", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempLike(String value) {
            addCriterion("temp like", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempNotLike(String value) {
            addCriterion("temp not like", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempIn(List<String> values) {
            addCriterion("temp in", values, "temp");
            return (Criteria) this;
        }

        public Criteria andTempNotIn(List<String> values) {
            addCriterion("temp not in", values, "temp");
            return (Criteria) this;
        }

        public Criteria andTempBetween(String value1, String value2) {
            addCriterion("temp between", value1, value2, "temp");
            return (Criteria) this;
        }

        public Criteria andTempNotBetween(String value1, String value2) {
            addCriterion("temp not between", value1, value2, "temp");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

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