package com.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "template")
public class Template {

    public Template(){}

    public Template(String content, int numberOfUnknowns, int templateType, int language) {
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
    private int templateType;

    private int language;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getNumberOfUnknowns() { return numberOfUnknowns; }
    public void setNumberOfUnknowns(int numberOfUnknowns) { this.numberOfUnknowns = numberOfUnknowns; }

    public int getTemplateType() { return templateType; }
    public void setTemplateType(int templateType) { this.templateType = templateType; }

    public int getLanguage() { return language; }
    public void setLanguage(int language) { this.language = language; }
}