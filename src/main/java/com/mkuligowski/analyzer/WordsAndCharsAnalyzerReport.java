package com.mkuligowski.analyzer;

import java.util.HashMap;
import java.util.Map;

public class WordsAndCharsAnalyzerReport implements FileAnalyzerReport {

    private final String filePath;
    private final long wordsCount;
    private final long charCount;

    public WordsAndCharsAnalyzerReport(String filePath, long wordsCount, long charCount) {
        this.filePath = filePath;
        this.wordsCount = wordsCount;
        this.charCount = charCount;
    }

    @Override
    public void printReport() {
        System.out.printf("File path: %s - words count: %d - char count: %d %n", filePath, wordsCount, charCount);
    }

    @Override
    public Map<ReportEntry, Long> getReportMetaData() {
        Map<ReportEntry, Long> map = new HashMap<>();
        map.put(ReportEntry.WORD, wordsCount);
        map.put(ReportEntry.CHAR, charCount);
        return map;
    }
}
