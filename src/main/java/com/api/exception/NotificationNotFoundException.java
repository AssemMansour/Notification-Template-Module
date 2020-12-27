package com.api.exception;

public class NotificationNotFoundException extends Exception{
    private int id;
    public NotificationNotFoundException(Long id) {
        super(String.format("Notification with id : '%s' doesn't exist", id));
    }
}
