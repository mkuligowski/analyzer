package com.mkuligowski.analyzer;

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
}
