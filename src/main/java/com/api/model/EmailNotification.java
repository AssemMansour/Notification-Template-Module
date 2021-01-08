package com.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "email_notification")
public class EmailNotification extends Notification {

    public EmailNotification(){
        super();
    }
}
