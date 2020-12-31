package com.company;

import com.api.model.Notification;
import com.api.model.Template;
import com.company.util.NotificationUtil;
import com.company.util.TemplateUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int choice;
        Scanner scanner = new Scanner(System.in);
        Template template = new Template();
        Notification notification;
        ArrayList<Template> templates;
        long id;

        while (true) {
            System.out.println("\n1- Create Template\n" +
                    "2- View Templates\n" +
                    "3- Update Template\n" +
                    "4- Delete Template\n" +
                    "5- Add Notification \n" +
                    "6-View Notifications\n" +
                    "7-Send Next Notifications\n" +
                    "8-Exit\n");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // Create Template
                    template = getTemplateInfo(template);
                    TemplateUtil.createTemplate(template);
                    break;
                case 2: // View Templates
                    templates = TemplateUtil.getAllTemplates();
                    if (templates.size() > 0) {
                        for (Template t : templates) {
                            template = t;
                            TemplateUtil.printTemplateInfo(template);
                        }
                    } else
                        System.out.println("NO TEMPLATES");
                    break;
                case 3: // Update Template
                    System.out.println("Enter Template ID");
                    id = scanner.nextLong();
                    template = TemplateUtil.getTemplateById(id);
                    if (template == null)
                        System.out.println("Template with id " + id + " doesn't exist");
                    else {
                        template = getTemplateInfo(template);
                        TemplateUtil.updateTemplate(template);
                    }
                    break;
                case 4: // Delete Template
                    System.out.println("Enter Template ID");
                    id = scanner.nextLong();
                    template = TemplateUtil.getTemplateById(id);
                    if (template == null)
                        System.out.println("Template with id " + id + " doesn't exist");
                    else
                        TemplateUtil.deleteTemplate(template);
                    break;
                case 5: // Add Notification
                    String sender;
                    String receiver;

                    System.out.println("Enter Sender");
                    sender = scanner.nextLine();

                    System.out.println("Enter Receiver");
                    receiver = scanner.nextLine();

                    System.out.println("Enter Template ID");
                    id = scanner.nextInt();
                    scanner.nextLine();

                    template = TemplateUtil.getTemplateById(id);
                    if (template == null) {
                        System.out.println("This Template Not Exist");
                        break;
                    }

                    int num = template.getNumberOfUnknowns();
                    ArrayList<String> replace = new ArrayList<>();
                    String unknown;
                    System.out.println("Enter Placeholders");
                    for (int i = 0; i < num; i++) {
                        unknown = scanner.nextLine();
                        replace.add(unknown);
                    }
                    System.out.println("Send by: 1-SMS, 2-E-MAIL");
                    int notificationType = scanner.nextInt();
                    unknown = NotificationUtil.processTemp(template, replace);
                    notification = new Notification(sender, receiver, unknown, notificationType);

                    NotificationUtil.createNotification(notification);
                    break;
                case 6: // View Notification
                    ArrayList<Notification> notifications;
                    notifications = NotificationUtil.getAllNotifications();

                    if (!notifications.isEmpty()) {
                        for (Notification n : notifications) {
                            notification = n;
                            System.out.println("Sender: " + notification.getSender());
                            System.out.println("Receiver: " + notification.getReceiver());
                            System.out.println("Content: " + notification.getContent());
                            System.out.println("Type: " + NotificationUtil.processType(notification.getNotificationType()).name());
                        }
                    } else
                        System.out.println("NO NOTIFICATION");
                    break;
                case 7: // Send Next Notification
                    NotificationUtil.sendNextNotifications();
                    System.out.println("Notification Sent!");
                    break;
                case 8: // Exit
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice!");
                    break;

            }
        }

    }

    private static Template getTemplateInfo(Template template) {

        Scanner scanner = new Scanner(System.in);
        int choice;
        Long id = template.getId();

        String content;
        int numberOfUnknowns, templateType;
        boolean language;
        System.out.println("Enter Content of Template");
        content = scanner.nextLine();

        System.out.println("Enter Number of Unknowns");
        numberOfUnknowns = scanner.nextInt();
        do {
            System.out.println("Choose one of the following templates \n"
                    + "1-PASSWORD\n"
                    + "2-VERIFICATION\n"
                    + "3-LATE\n"
                    + "4-ORDERS\n"
                    + "5-ANNOUNCEMENT\n"
                    + "6-PROMOTION\n");

            templateType = scanner.nextInt();
        }
        while (templateType > 5 || templateType < 1);

        do {
            System.out.println("Choose one of the following language \n"
                    + "1-English\n"
                    + "2-Arabic\n");

            choice = scanner.nextInt();
        }
        while (choice > 2 || choice < 1);

        language = choice == 1;
        template = new Template(content, numberOfUnknowns, templateType, language);
        template.setId(id);
        return template;
    }
}