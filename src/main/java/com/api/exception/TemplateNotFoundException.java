package com.api.exception;

public class TemplateNotFoundException extends Exception {
    private int id;
    public TemplateNotFoundException(Long id) {
        super(String.format("Template with id : '%s' doesn't exist", id));
    }
}