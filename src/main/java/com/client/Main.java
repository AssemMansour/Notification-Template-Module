package com.client;

import com.api.enums.NotificationType;
import com.client.util.DataUtil;

import javax.xml.crypto.Data;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean hasColumns = true;

        while (true) {
            System.out.println("1) Send Next Notification 2) Exit");

            int choice = scanner.nextInt();

            if (choice == 2)
                return;

            System.out.println("1) By SMS\t2) By E-MAIL");
            choice = scanner.nextInt();

            if (choice == 1) {
                hasColumns = DataUtil.sendNextNotification(NotificationType.SMS);

            } else if (choice == 2) {
                hasColumns = DataUtil.sendNextNotification(NotificationType.EMAIL);

            } else
                System.out.println("Invalid input");


            if (!hasColumns)
                System.out.println("All notifications have been sent");

        }

    }
}
