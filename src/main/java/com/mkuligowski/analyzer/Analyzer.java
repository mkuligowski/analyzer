package com.mkuligowski.analyzer;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;


@Command(name = "analyzer", version = "1.0", description = "Count words and chars from text files")
public class Analyzer implements Callable<Integer> {

    private static final String TXT_EXTESION = ".TXT";

    @Option(
            names = {"-S"},
            description = "Words you want to skip during analysis. If more then one, please separate by comma"
    )
    private String stopWords = "";

    @Option(
            names = {"-F"},
            description = "File to analyse. You can put multiple files like \"F file1.txt -F file2.txt\""
    )
    private List<String> files = new ArrayList<>();

    @Option(
            names = {"-C"},
            description = "Count characters. If this argument provided, report will also include charater count"
    )
    private boolean countCharacters = false;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Analyzer()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {

        if (files.isEmpty()){
            System.out.println("Please provide at least one file");
            return 1;
        }

        if (textFilesHasIncorrectFormat()){
            System.out.println("Only .txt files can be analyzed");
            return 1;
        }
        // Converting stopWords to uppercase, in order to be case insensitive
        List<String> stopWordsList = Arrays.asList(stopWords.toUpperCase().split(","));

        FileAnalyzer fileAnalyzer = new FileAnalyzer(stopWordsList);
        SummaryReport summaryRaport = countCharacters
                ? fileAnalyzer.countWordsAndChars(files)
                : fileAnalyzer.countWords(files);

        summaryRaport.print();

        return 0;
    }

    private boolean textFilesHasIncorrectFormat() {
        return files.stream().anyMatch(s -> !s.toUpperCase().endsWith(TXT_EXTESION));
    }
}
