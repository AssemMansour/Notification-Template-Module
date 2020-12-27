package com.company;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "template")
public class Template {

    public Template(String content, int numberOfUnknowns, Type templateType, Language language) {
        this.content = content;
        this.numberOfUnknowns = numberOfUnknowns;
        this.templateType = templateType;
        this.language = language;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String content;

    @NotBlank
    private int numberOfUnknowns;

    @NotBlank
    @Enumerated(EnumType.ORDINAL)
    private Type templateType;

    @Enumerated(EnumType.ORDINAL)
    private Language language;

    @Transient
    private Notification toSendQueue;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getNumberOfUnknowns() { return numberOfUnknowns; }
    public void setNumberOfUnknowns(int numberOfUnknowns) { this.numberOfUnknowns = numberOfUnknowns; }

    public Type getTemplateType() { return templateType; }
    public void setTemplateType(Type templateType) { this.templateType = templateType; }

    public Language getLanguage() { return language; }
    public void setLanguage(Language language) { this.language = language; }

    public Notification getToSendQueue() { return toSendQueue; }
    public void setToSendQueue(Notification toSendQueue) { this.toSendQueue = toSendQueue; }
}