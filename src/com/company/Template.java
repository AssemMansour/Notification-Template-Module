package com.company;

import java.util.ArrayList;

public class Template {

    /** private constructor so our template won't get instantiated */
    private Template(){}

    private static final ArrayList<TemplateAttribute> templates = new ArrayList<>();

    /** create new template and add it to our list */
    public static void create(TemplateAttribute t){ templates.add(t); }


    /* our read methods */

    /** read all templates of the requested type and return them */
    public static ArrayList<TemplateAttribute> read(Type tempType){
        ArrayList<TemplateAttribute> typeTempList = new ArrayList<>();

       for (TemplateAttribute t : templates)
           if (t.getType() == tempType)
               typeTempList.add(t);

       return typeTempList;
    }

    /** get template by id and returns it */
    public static TemplateAttribute get(int id){
        for (TemplateAttribute t : templates)
            if (t.getId() == id)
                return t;

        return null;
    }

    /**
     * update old element's content with new content
     * @param id the id of the old element
     * @param newTemp element to replace the old one
     */
    public static void update(int id, TemplateAttribute newTemp){
        TemplateAttribute oldTemp = get(id);
        int index = templates.indexOf(oldTemp);

        templates.set(index, newTemp);
    }

    /** delete element with the provided id */
    public static void delete(int id){ templates.removeIf(t -> t.getId() == id); }


}
