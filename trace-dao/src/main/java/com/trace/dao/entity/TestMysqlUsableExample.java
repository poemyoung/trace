package com.trace.dao.entity;

import java.util.ArrayList;
import java.util.List;

public class TestMysqlUsableExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TestMysqlUsableExample() {
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

        public Criteria andIdtestTableIsNull() {
            addCriterion("idtest_table is null");
            return (Criteria) this;
        }

        public Criteria andIdtestTableIsNotNull() {
            addCriterion("idtest_table is not null");
            return (Criteria) this;
        }

        public Criteria andIdtestTableEqualTo(Integer value) {
            addCriterion("idtest_table =", value, "idtestTable");
            return (Criteria) this;
        }

        public Criteria andIdtestTableNotEqualTo(Integer value) {
            addCriterion("idtest_table <>", value, "idtestTable");
            return (Criteria) this;
        }

        public Criteria andIdtestTableGreaterThan(Integer value) {
            addCriterion("idtest_table >", value, "idtestTable");
            return (Criteria) this;
        }

        public Criteria andIdtestTableGreaterThanOrEqualTo(Integer value) {
            addCriterion("idtest_table >=", value, "idtestTable");
            return (Criteria) this;
        }

        public Criteria andIdtestTableLessThan(Integer value) {
            addCriterion("idtest_table <", value, "idtestTable");
            return (Criteria) this;
        }

        public Criteria andIdtestTableLessThanOrEqualTo(Integer value) {
            addCriterion("idtest_table <=", value, "idtestTable");
            return (Criteria) this;
        }

        public Criteria andIdtestTableIn(List<Integer> values) {
            addCriterion("idtest_table in", values, "idtestTable");
            return (Criteria) this;
        }

        public Criteria andIdtestTableNotIn(List<Integer> values) {
            addCriterion("idtest_table not in", values, "idtestTable");
            return (Criteria) this;
        }

        public Criteria andIdtestTableBetween(Integer value1, Integer value2) {
            addCriterion("idtest_table between", value1, value2, "idtestTable");
            return (Criteria) this;
        }

        public Criteria andIdtestTableNotBetween(Integer value1, Integer value2) {
            addCriterion("idtest_table not between", value1, value2, "idtestTable");
            return (Criteria) this;
        }

        public Criteria andDataVarcharIsNull() {
            addCriterion("data_varchar is null");
            return (Criteria) this;
        }

        public Criteria andDataVarcharIsNotNull() {
            addCriterion("data_varchar is not null");
            return (Criteria) this;
        }

        public Criteria andDataVarcharEqualTo(String value) {
            addCriterion("data_varchar =", value, "dataVarchar");
            return (Criteria) this;
        }

        public Criteria andDataVarcharNotEqualTo(String value) {
            addCriterion("data_varchar <>", value, "dataVarchar");
            return (Criteria) this;
        }

        public Criteria andDataVarcharGreaterThan(String value) {
            addCriterion("data_varchar >", value, "dataVarchar");
            return (Criteria) this;
        }

        public Criteria andDataVarcharGreaterThanOrEqualTo(String value) {
            addCriterion("data_varchar >=", value, "dataVarchar");
            return (Criteria) this;
        }

        public Criteria andDataVarcharLessThan(String value) {
            addCriterion("data_varchar <", value, "dataVarchar");
            return (Criteria) this;
        }

        public Criteria andDataVarcharLessThanOrEqualTo(String value) {
            addCriterion("data_varchar <=", value, "dataVarchar");
            return (Criteria) this;
        }

        public Criteria andDataVarcharLike(String value) {
            addCriterion("data_varchar like", value, "dataVarchar");
            return (Criteria) this;
        }

        public Criteria andDataVarcharNotLike(String value) {
            addCriterion("data_varchar not like", value, "dataVarchar");
            return (Criteria) this;
        }

        public Criteria andDataVarcharIn(List<String> values) {
            addCriterion("data_varchar in", values, "dataVarchar");
            return (Criteria) this;
        }

        public Criteria andDataVarcharNotIn(List<String> values) {
            addCriterion("data_varchar not in", values, "dataVarchar");
            return (Criteria) this;
        }

        public Criteria andDataVarcharBetween(String value1, String value2) {
            addCriterion("data_varchar between", value1, value2, "dataVarchar");
            return (Criteria) this;
        }

        public Criteria andDataVarcharNotBetween(String value1, String value2) {
            addCriterion("data_varchar not between", value1, value2, "dataVarchar");
            return (Criteria) this;
        }

        public Criteria andDataIntIsNull() {
            addCriterion("data_int is null");
            return (Criteria) this;
        }

        public Criteria andDataIntIsNotNull() {
            addCriterion("data_int is not null");
            return (Criteria) this;
        }

        public Criteria andDataIntEqualTo(Integer value) {
            addCriterion("data_int =", value, "dataInt");
            return (Criteria) this;
        }

        public Criteria andDataIntNotEqualTo(Integer value) {
            addCriterion("data_int <>", value, "dataInt");
            return (Criteria) this;
        }

        public Criteria andDataIntGreaterThan(Integer value) {
            addCriterion("data_int >", value, "dataInt");
            return (Criteria) this;
        }

        public Criteria andDataIntGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_int >=", value, "dataInt");
            return (Criteria) this;
        }

        public Criteria andDataIntLessThan(Integer value) {
            addCriterion("data_int <", value, "dataInt");
            return (Criteria) this;
        }

        public Criteria andDataIntLessThanOrEqualTo(Integer value) {
            addCriterion("data_int <=", value, "dataInt");
            return (Criteria) this;
        }

        public Criteria andDataIntIn(List<Integer> values) {
            addCriterion("data_int in", values, "dataInt");
            return (Criteria) this;
        }

        public Criteria andDataIntNotIn(List<Integer> values) {
            addCriterion("data_int not in", values, "dataInt");
            return (Criteria) this;
        }

        public Criteria andDataIntBetween(Integer value1, Integer value2) {
            addCriterion("data_int between", value1, value2, "dataInt");
            return (Criteria) this;
        }

        public Criteria andDataIntNotBetween(Integer value1, Integer value2) {
            addCriterion("data_int not between", value1, value2, "dataInt");
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