package com.mkuligowski.analyzer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountWordTask implements Callable<FileAnalyzerReport> {

    private final String filePath;
    private final List<String> stopWords;
    private final CountWordTaskMode mode;

    public CountWordTask(String filePath, List<String> stopWords, CountWordTaskMode mode) {
        this.filePath = filePath;
        this.stopWords = stopWords;
        this.mode = mode;
    }

    @Override
    public FileAnalyzerReport call() {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            return mode == CountWordTaskMode.COUNT_WORDS ?
                    analyzeOnlyWords(stream) : analyzeWordsAndChars(stream);
        } catch (Exception e) {
            return new ErrorReport(filePath, e.toString());
        }
    }

    private FileAnalyzerReport analyzeOnlyWords(Stream<String> stream) {
        long wordCount = stream.mapToLong(this::countWords).sum();
        return new WordsAnalyzerRaport(filePath, wordCount);
    }

    private FileAnalyzerReport analyzeWordsAndChars(Stream<String> stream) {
        List<WordsAndCharsPair> wordsAndCharsPairs = stream.map(s -> new WordsAndCharsPair(countWords(s), s.length()))
                .collect(Collectors.toList());

        long wordCount = wordsAndCharsPairs.stream().mapToLong(wordsAndCharsPair -> wordsAndCharsPair.wordCount).sum();
        long charCount = calculateCharCount(wordsAndCharsPairs);
        return new WordsAndCharsAnalyzerReport(filePath, wordCount, charCount);
    }

    private long calculateCharCount(List<WordsAndCharsPair> wordsAndCharsPairs) {
        return wordsAndCharsPairs.stream().mapToLong(wordsAndCharsPair -> wordsAndCharsPair.charCount).sum()
                // we should also add all new lines characters
          + Math.max(wordsAndCharsPairs.size() - 1, 0);
    }

    private long countWords(String s){
        String trim = s.trim();
        if (trim.isEmpty())
            return 0;

        return Stream.of(trim.split("\\s+")).filter(s1 -> !stopWords.contains(s1.toUpperCase())).count();
    }


    static class WordsAndCharsPair {

        WordsAndCharsPair(long wordCount, long charCount) {
            this.wordCount = wordCount;
            this.charCount = charCount;
        }

        long wordCount;
        long charCount;
    }
}
