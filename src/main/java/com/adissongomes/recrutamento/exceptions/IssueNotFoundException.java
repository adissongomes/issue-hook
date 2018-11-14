package com.adissongomes.recrutamento.exceptions;

public class IssueNotFoundException extends RuntimeException {
    public IssueNotFoundException(Long issueId) {
        this(String.format("Issue '%d' não encontrada", issueId));
    }

    public IssueNotFoundException(String message) {
        super(message);
    }

    public IssueNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
