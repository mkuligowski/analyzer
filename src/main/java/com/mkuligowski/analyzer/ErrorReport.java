package com.mkuligowski.analyzer;

public class ErrorReport implements FileAnalyzerReport {

    private String path;
    private final String message;

    public ErrorReport(String message) {
        this.message = message;
    }

    public ErrorReport(String filePath, String message) {
        this.path = filePath;
        this.message = message;
    }

    @Override
    public void printReport() {
        if (path != null)
            System.out.printf("file path: %s - error: %s %n", path, message );
        else
            System.out.printf("general error occurred: %s", message);
    }
}
