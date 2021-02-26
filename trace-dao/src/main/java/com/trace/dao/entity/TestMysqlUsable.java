package com.trace.dao.entity;

public class TestMysqlUsable {
    private Integer idtestTable;

    private String dataVarchar;

    private Integer dataInt;

    public Integer getIdtestTable() {
        return idtestTable;
    }

    public void setIdtestTable(Integer idtestTable) {
        this.idtestTable = idtestTable;
    }

    public String getDataVarchar() {
        return dataVarchar;
    }

    public void setDataVarchar(String dataVarchar) {
        this.dataVarchar = dataVarchar == null ? null : dataVarchar.trim();
    }

    public Integer getDataInt() {
        return dataInt;
    }

    public void setDataInt(Integer dataInt) {
        this.dataInt = dataInt;
    }
}