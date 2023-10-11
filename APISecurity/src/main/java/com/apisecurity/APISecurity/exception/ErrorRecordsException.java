package com.apisecurity.APISecurity.exception;

import java.util.Map;

public class ErrorRecordsException extends Exception {
    private Map<String, String> exceptions;
    public ErrorRecordsException(Map<String, String> exceptions) {
        this.exceptions = exceptions;
    }
    public Map<String, String> getExceptions() {
        return exceptions;
    }
}