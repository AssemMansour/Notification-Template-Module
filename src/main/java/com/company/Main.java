package com.company;

import com.api.model.Template;
import com.company.util.TemplateUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int choice;
        Scanner scanner = new Scanner(System.in);

        Template t1;
        ArrayList<Template> t2;
        String content;
        int numberOfUnknowns, templateType;
        boolean language;

        while (true) {
            System.out.println("1- Create Template\n2- View Templates\n3- Update Template\n4- Delete Template\n5- Send Notification \n6-View Notifications\n7-Exit\n");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter Content of Template");
                    content = scanner.nextLine();
                    System.out.println("Enter Number of Unknowns");
                    numberOfUnknowns = scanner.nextInt();
                    System.out.println("Choose one of the following templates \n" + "1-PASSWORD\n" + "2-VERIFICATION\n" + "3-LATE\n" + "4-ORDERS\n" + "5-ANNOUNCEMENT\n" + "5-PROMOTION\n");
                    do {
                        templateType = scanner.nextInt();
                        System.out.println("choose one of the following templates \n" + "1-PASSWORD\n" + "2-VERIFICATIO\n" + "3-LATE\n" + "4-ORDERS\n" + "5-ANNOUNCEMENT\n" + "5-PROMOTION\n");
                    }
                    while (templateType > 5 || templateType < 1);

                    System.out.println("Choose one of the following language \n" + "1-Arabic\n" + "2-English\n");
                    do {
                        choice = scanner.nextInt();
                        System.out.println("Choose one of the following language \n" + "1-English\n" + "2-Arabic\n");
                    }
                    while (choice > 2 || choice < 1);

                    language = choice == 1;
                    t1 = new Template(content, numberOfUnknowns, templateType, language);
                    TemplateUtil.createTemplate(t1);
                    break;
                case 2:
                    t2 = TemplateUtil.getAllTemplates();
                    for (Template template : t2) {
                        t1 = template;
                        TemplateUtil.printTemplateInfo(t1);
                    }
                    break;
                case 3:
                    System.out.println("Enter Template ID");
                    long id;
                    id = scanner.nextLong();
                    t1 = TemplateUtil.getTemplateById(id);
                    TemplateUtil.updateTemplate(t1);
                    break;
                case 4:
                    System.out.println("Enter Template ID");
                    id = scanner.nextLong();
                    t1 = TemplateUtil.getTemplateById(id);
                    TemplateUtil.deleteTemplate(t1);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice!");
                    break;

            }
        }

    }
}