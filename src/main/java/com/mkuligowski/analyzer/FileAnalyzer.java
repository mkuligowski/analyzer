package com.mkuligowski.analyzer;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class FileAnalyzer {

    private static final int THREAD_COUNT = 10;

    private final List<String> stopWords;

    public FileAnalyzer(List<String> stopWords) {
        this.stopWords = stopWords;
    }

    public SummaryReport countWords(List<String> filePaths) {
        return analyzeFiles(filePaths, CountWordTaskMode.COUNT_WORDS);
    }

    public SummaryReport countWordsAndChars(List<String> filePaths) {
        return analyzeFiles(filePaths, CountWordTaskMode.COUNT_WORDS_AND_CHARS);
    }

    private SummaryReport analyzeFiles(List<String> filePaths, CountWordTaskMode mode){
        List<CountWordTask> tasks = prepareTasks(filePaths, mode);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        try {
            List<FileAnalyzerReport> reports = executorService.invokeAll(tasks)
                    .stream()
                    .map(this::getFileAnalyzerFuture)
                    .collect(Collectors.toList());

            executorService.shutdown();

            return new SummaryReport(reports);
        } catch (InterruptedException e) {
            return new SummaryReport(
                    Collections.singletonList(new ErrorReport("General error occurred: " + e.getMessage()))
            );
        }
    }

    private List<CountWordTask> prepareTasks(List<String> filePaths, CountWordTaskMode mode) {
        return filePaths
                .stream()
                .map(path -> new CountWordTask(path, stopWords, mode))
                .collect(Collectors.toList());
    }

    private FileAnalyzerReport getFileAnalyzerFuture(Future<FileAnalyzerReport> fileAnalyzerReportFuture) {
        try {
            return fileAnalyzerReportFuture.get();
        } catch (InterruptedException | ExecutionException e) {
           return new ErrorReport(e.getMessage());
        }
    }

}
