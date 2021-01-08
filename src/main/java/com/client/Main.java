package com.client;

import com.api.enums.NotificationType;
import com.client.util.DataUtil;

import javax.xml.crypto.Data;
import java.util.Scanner;

public class Main {

    private static final String NOTIFICATION_URL = "http://localhost:8080/notifications/";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1) Send Next Notification 2) Exit");

            int choice = scanner.nextInt();

            if (choice == 2)
                return;

            System.out.println("1) By SMS\t2) By E-MAIL");
            choice = scanner.nextInt();

            if (choice == 1) {
                DataUtil.handleSendRequest("", NOTIFICATION_URL + "send?type=" + NotificationType.SMS.name(), "POST");

            } else if (choice == 2) {
                DataUtil.handleSendRequest("", NOTIFICATION_URL + "send?type=" + NotificationType.EMAIL.name(), "POST");

            } else
                System.out.println("Invalid input");

        }

    }
}
