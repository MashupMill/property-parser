package com.mashupmill.property;

/**
 * Created 10/8/15 @ 1:59 PM
 *
 * @author brandencash
 */
public enum EscapeType {
    DOUBLE_QUOTE("\"", true), SINGLE_QUOTE("'", true), SLASH("\\", false);

    private String escapeChar;
    private boolean wrapper;

    EscapeType(String escapeChar, boolean wrapper) {
        this.escapeChar = escapeChar;
        this.wrapper = wrapper;
    }

    public String getEscapeChar() {
        return escapeChar;
    }

    public boolean isWrapper() {
        return wrapper;
    }
}
