package com.company;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Integer choiceInteger = -1;
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println(
					"\n\n1- Create Template\n2- View Templates\n3- View All Templates\n4- Update Template\n5- Delete Template\n0- Exit");
			choiceInteger = scanner.nextInt();
			while (choiceInteger < 0 || choiceInteger > 5) {
				System.out.println("Enter a valid number");
				choiceInteger = scanner.nextInt();
			}
			switch (choiceInteger) {
			case 1:
				Template.create();
				break;
			case 2:
				if (Template.templates.size() > 0) {
					Template.read();
				} else {
					System.out.println("Please create template(s) first");
				}
				break;
			case 3:
				if (Template.templates.size() > 0) {
					Template.readAll();
				} else {
					System.out.println("Please create template(s) first");
				}
				break;
			case 4:
				if (Template.templates.size() > 0) {
					Template.update();
				} else {
					System.out.println("Please create template(s) first");
				}
				break;
			case 5:
				if (Template.templates.size() > 0) {
					Template.delete();
				} else {
					System.out.println("Please create template(s) first");
				}
				break;
			case 0:
				System.exit(0);
				break;

			default:
				break;
			}
		}
	}
}
