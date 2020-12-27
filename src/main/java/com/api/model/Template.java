package com.api.model;


import com.company.Type;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;

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
    private Long id;

    private String content;

    private int numberOfUnknowns;

    private int templateType;

    private boolean language;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getNumberOfUnknowns() { return numberOfUnknowns; }
    public void setNumberOfUnknowns(int numberOfUnknowns) { this.numberOfUnknowns = numberOfUnknowns; }

    public int getTemplateType() { return templateType; }
    public void setTemplateType(int templateType) { this.templateType = templateType; }

    public boolean getLanguage() { return language; }
    public void setLanguage(boolean language) { this.language = language; }

    public String toString() {

        return String.format("{\"id\":%d,\"content\":\"%s\",\"numberOfUnknowns\":%d,\"templateType\":%d,\"language\":%b}",
                id, content, numberOfUnknowns, templateType, language);
    }


}