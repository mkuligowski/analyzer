package com.mkuligowski.analyzer;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

public class SummaryReport {
    private final List<FileAnalyzerReport> sourceReports;

    public SummaryReport(List<FileAnalyzerReport> sourceReports) {
        this.sourceReports = sourceReports;
    }

    public void print(){
        sourceReports.forEach(FileAnalyzerReport::printReport);
        printSummary();
    }

    private void printSummary() {
        System.out.println("******[SUMMARY]******");
        Map<ReportEntry, Long> summaryMap = sourceReports.stream()
                .map(FileAnalyzerReport::getReportMetaData)
                .flatMap(m -> m.entrySet().stream())
                .collect(groupingBy(Map.Entry::getKey, summingLong(Map.Entry::getValue)));

        summaryMap.forEach((reportEntry, aLong) -> System.out.printf("%s %d %n",reportEntry.getDisplay(), aLong));
    }
}
