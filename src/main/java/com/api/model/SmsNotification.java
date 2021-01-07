package com.api.model;

import com.api.enums.Status;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sms_notification")
public class SmsNotification extends Notification {

    public SmsNotification(){
        super();
    }
}
