package com.api.model;


import com.api.enums.Language;
import com.api.enums.TemplateType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "template")
public class Template {

    public Template(){}

    public Template(String content, int numberOfUnknowns, TemplateType templateType, Language language) {
        this.content = content;
        this.numberOfUnknowns = numberOfUnknowns;
        this.templateType = templateType;
        this.language = language;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String content;

    private int numberOfUnknowns;

    @Enumerated(EnumType.ORDINAL)
    private TemplateType templateType;

    @Enumerated(EnumType.ORDINAL)
    private Language language;

    @OneToMany(mappedBy = "id")
    @JsonIgnore
    private Set<Notification> notifications;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getNumberOfUnknowns() { return numberOfUnknowns; }
    public void setNumberOfUnknowns(int numberOfUnknowns) { this.numberOfUnknowns = numberOfUnknowns; }

    public TemplateType getTemplateType() { return templateType; }
    public void setTemplateType(TemplateType templateType) { this.templateType = templateType; }

    public Language getLanguage() { return language; }
    public void setLanguage(Language language) { this.language = language; }


}