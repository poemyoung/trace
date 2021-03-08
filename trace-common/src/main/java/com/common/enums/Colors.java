package com.common.enums;

/**
 * @author xzp
 * Created on 2021/3/7
 */
public enum Colors {
    GREEN("29a766"),
    BLUE("9cc0f8"),
    YELLOW("fcd989"),
    RED("f17a6f"),
    DEFAULT("000000");


    Colors(String s) {
        this.cStr = s;
    }
    private String cStr;

    public String getcStr() {
        return cStr;
    }

    public void setcStr(String cStr) {
        this.cStr = cStr;
    }
}
