package com.api.model;

import com.api.enums.NotificationType;
import com.api.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Notification {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private String sender;
    private String receiver;
    private String content;

    private String unknowns;
    private Long templateId;

    @Transient
    private Template template;

    public Notification(){
        status = Status.TO_BE_SENT;
    }

    protected Notification(Notification notification) {
        status = notification.getStatus();
        sender = notification.getSender();
        receiver = notification.getReceiver();
        content = notification.getContent();
        unknowns = notification.getUnknowns();
        template = notification.getTemplate();
        templateId = notification.getTemplateId();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    public String getReceiver() { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getUnknowns() { return unknowns; }
    public void setUnknowns(String unknowns) { this.unknowns = unknowns; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @JsonIgnore
    public Long getTemplateId() { return templateId; }
    @JsonSetter
    public void setTemplateId(Long templateId) { this.templateId = templateId; }

    @JsonIgnore
    public Template getTemplate() { return template; }
    public void setTemplate(Template template) { this.template = template; }

    public void prepareContent() {
        List<String> unknownsList = Arrays.asList(unknowns.split(",", -1));
        this.content = processTemplate(unknownsList);
    }

    private String processTemplate(List<String> unknowns) {
        String content = template.getContent();
        content = content.replaceAll("\\{.*?\\}", "#PH");
        for (int i = 0; i < template.getNumberOfUnknowns(); i++) {

            if (unknowns.get(i) == null)
                break;
            content = content.replaceFirst("#PH", unknowns.get(i));
        }
        return content;
    }
}
