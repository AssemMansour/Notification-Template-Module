package com.api.model;

import com.company.Language;
import com.company.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "template")
public class Template {

    public Template(){}

    public Template(String content, int numberOfUnknowns, int templateType, boolean language) {
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

    private boolean language;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getNumberOfUnknowns() { return numberOfUnknowns; }
    public void setNumberOfUnknowns(int numberOfUnknowns) { this.numberOfUnknowns = numberOfUnknowns; }

    public int getTemplateType() { return templateType; }
    public void setTemplateType(int templateType) { this.templateType = templateType; }

    public boolean getLanguage() { return language; }
    public void setLanguage(boolean language) { this.language = language; }

    public String toString() {
        return String.format("{\"id\":%d\"content\":%s,\"numberOfUnknowns\":%d,\"templateType\":%d,\"language\":%b}",
                id, content, numberOfUnknowns, templateType, language);
    }
}