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
    public static Type processType(Integer choice) {
        switch (choice) {
            case (1):
                return Type.values()[0];
            case (2):
                return Type.values()[1];
            case (3):
                return Type.values()[2];
            case (4):
                return Type.values()[3];
            case (5):
                return Type.values()[4];
            case (6):
                return Type.values()[5];
        }
        return Type.values()[0];
    }
    public void print ()
    {
        System.out.println("content:"+getContent());
        System.out.println("numberofunknowns:"+getNumberOfUnknowns());
        Type t1=processType(getTemplateType());
        System.out.println("type:"+t1.name());
        if (language)
         System.out.println("language: English");
        else
            System.out.println("language: Arabic");
    }
}