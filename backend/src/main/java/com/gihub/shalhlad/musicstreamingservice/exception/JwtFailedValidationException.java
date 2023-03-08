package com.gihub.shalhlad.musicstreamingservice.exception;

public class JwtFailedValidationException extends RuntimeException {
    public JwtFailedValidationException(String message) {
        super(message);
    }
}
