package com.hcc.exceptions;

public class CreateAssignmentException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CreateAssignmentException(String message, Exception e) {
        super(message);
    }
}
