package com.overpathz.bbhomework.context.exception;

public class NoSuchBeanException extends RuntimeException {

    public NoSuchBeanException() {
        this("Bean not found");
    }

    public NoSuchBeanException(String message) {
        super(message);
    }
}
