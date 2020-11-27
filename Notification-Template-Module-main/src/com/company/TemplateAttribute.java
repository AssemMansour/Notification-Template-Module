package com.company;

import java.util.ArrayList;

enum Type {
    PASSWORD, VERIFICATION, LATE, ORDERS,
    ANNOUNCEMENT, PROMOTION
}

public class TemplateAttribute {
    String content;
    Type temptype;
    int numberofunknowns;
    static int id=0;

    TemplateAttribute (String content,int type,int numberofunknowns)
    {
        this.setContent(content);
        this.setNumberofunknowns(numberofunknowns);
        this.setId(++id);
        switch (type)
        {
            case (1):
                this.temptype=Type.values()[0];
            case (2):
                this.temptype=Type.values()[1];
            case (3):
                this.temptype=Type.values()[2];
            case (4):
                this.temptype=Type.values()[3];
            case (5):
                this.temptype=Type.values()[4];
            case (6):
                this.temptype=Type.values()[5];
                break;
        }

    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumberofunknowns(int numberofunknowns) {
        this.numberofunknowns = numberofunknowns;
    }

    public void setTemptype(Type temptype) {
        this.temptype = temptype;
    }

    public int getId() {
        return id;
    }

    public int getNumberofunknowns() {
        return numberofunknowns;
    }

    public String getContent() {
        return content;
    }

    public String getTemptype() {
        return temptype.toString();
    }

    /**public String processtemp (TemplateAttribute temp, ArrayList <String> replace)
    {
        int c1=temp.getNumberofunknowns(),r=0;
        String val,val1="";
        while (c1>0)
        {
            val=temp.getContent();
            for (int i=0;i<val.length();i++) {
                if (val.charAt(i) == '{') {
                    while (val.charAt(i) != '}') {
                        i++;
                    }
                    i++;
                    val1 += replace.get(r);
                    r++;
                    c1--;
                } else {
                    val1 += val.charAt(i);
                }
            }
        }
        return val1;
    }**/
}