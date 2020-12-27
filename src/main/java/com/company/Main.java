package com.company;

import com.api.model.Template;
import com.company.util.DataUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int choice;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1- Create Template\n2- View Templates\n3- Update Template\n4- Delete Template\n5- Exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //DataUtil.createTemplate();
                    break;
                case 2:
                    //DataUtil.viewTemplates();
                    break;
                case 3:
                    //DataUtil.updateTemplate();
                    break;
                case 4:
                   // DataUtil.deleteTemplate();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice!");
                    break;

            }
        }

    }
}