package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Template {

    /** private constructor so our template won't get instantiated */
    public Template(){}

    private static final ArrayList<TemplateAttribute> templates = new ArrayList<>();

    /** create new template and add it to our list */
    public static void create()
    {
        Scanner input=new Scanner(System.in);
        String cont;
        int num;
        System.out.println("enter the content with placeholder '{x}' ");
        cont=input.nextLine();
        System.out.println("enter the number of placeholder '{x}' ");
        num=input.nextInt();
        System.out.println("1-PASSWORD, 2-VERIFICATION, 3-LATE, 4-ORDERS,\n" + "5-ANNOUNCEMENT,6- PROMOTION");
        int choice;
        choice=input.nextInt();
        while (choice>6 || choice<1)
        {
            System.out.println("invalid choice");
            System.out.println("1-PASSWORD, 2-VERIFICATION, 3-LATE, 4-ORDERS,\n" + "5-ANNOUNCEMENT,6- PROMOTION");
            choice=input.nextInt();
        }
        TemplateAttribute t =new TemplateAttribute(cont,choice,num);
        templates.add(t);
    }


    /* our read methods */

    /** read all templates of the requested type and return them */
    public static void read(String temptype){

       for (TemplateAttribute t : templates)
           if (temptype.equalsIgnoreCase(t.getTemptype()))
           {
               System.out.println("TempID: "+t.getId()+" TEMPTYPE: "+t.getTemptype());
               System.out.println("CONTENT: "+t.getContent());
           }
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
     * @param tempType element to replace the old one
     */
    public static void update(String tempType){
        read(tempType);
        int id=-1;
        Scanner input=new Scanner(System.in);
        System.out.println("enter template ID");
        id=input.nextInt();
        TemplateAttribute oldTemp = get(id);
        create();
        delete(id);
    }

    /** delete element with the provided id */
    public static void delete(int id){ templates.removeIf(t -> t.getId() == id); }


}
