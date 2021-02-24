package com.mkuligowski.analyzer;

import java.util.List;

public class SummaryReport {
    private final List<FileAnalyzerReport> sourceReports;

    public SummaryReport(List<FileAnalyzerReport> sourceReports) {
        this.sourceReports = sourceReports;
    }

    public void print(){
        sourceReports.forEach(FileAnalyzerReport::printReport);
    }
}
