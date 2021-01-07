package com.api.model;

import com.api.enums.Status;

import javax.persistence.*;

@MappedSuperclass
public class Notification {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private String sender;
    private String receiver;
    private String content;
    private Long templateId;

    public Notification(){
        status = Status.TO_BE_SENT;
    }

    public Notification(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    public String getReceiver() { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Long getTemplateId() { return templateId; }
    public void setTemplateId(Long templateId) { this.templateId = templateId; }
}
