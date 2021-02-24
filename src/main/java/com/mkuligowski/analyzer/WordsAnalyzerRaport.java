package com.mkuligowski.analyzer;

import java.util.HashMap;
import java.util.Map;

public class WordsAnalyzerRaport implements FileAnalyzerReport {

    private final String file;
    private final Long words;

    public WordsAnalyzerRaport(String file, Long words) {
        this.file = file;
        this.words = words;
    }

    @Override
    public void printReport() {
        System.out.printf("File path: %s - words count: %d %n", file, words);
    }

    @Override
    public Map<ReportEntry, Long> getReportMetaData() {
        Map<ReportEntry, Long> map = new HashMap<>();
        map.put(ReportEntry.WORD, words);
        return map;
    }
}
