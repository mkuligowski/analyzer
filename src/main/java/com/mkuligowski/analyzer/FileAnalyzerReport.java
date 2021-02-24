package com.mkuligowski.analyzer;

import java.util.Map;

interface FileAnalyzerReport {
    void printReport();
    Map<ReportEntry, Long> getReportMetaData();
}
