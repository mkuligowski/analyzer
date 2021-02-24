package com.mkuligowski.analyzer;

public enum ReportEntry {
    WORD ("Total words:"), CHAR ("Total chars:");

    ReportEntry(String display) {
        this.display = display;
    }

    private String display;

    public String getDisplay(){
        return display;
    }
}
