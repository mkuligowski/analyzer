package com.mkuligowski.analyzer;

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
}
