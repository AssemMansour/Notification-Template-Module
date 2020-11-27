package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Template {

	/** private constructor so our template won't get instantiated */
	public Template() {
	}

	public static final ArrayList<TemplateAttribute> templates = new ArrayList<>();

	/** create new template and add it to our list */
	public static void create() {
		Pattern pattern = Pattern.compile("\\{.*?\\}");
		int count = 0;
		int num = -1;
		Scanner input = new Scanner(System.in);
		String cont;
		System.out.println(
				"enter the content with placeholder. Example \" Dear {x} , your booking of the {y} is confirmed. thanks for using our store :) \"");
		cont = input.nextLine();
		Matcher matcher = pattern.matcher(cont);
		while (matcher.find()) {
			count++;
		}
		while (count != num) {
			System.out.println("Your Content: " + cont);
			System.out.println("enter the number of placeholders.");
			num = input.nextInt();
		}
		for (int i = 0; i < Type.values().length; i++) {
			System.out.println((i + 1) + "-" + Type.values()[i]);
		}
		int choice;
		choice = input.nextInt();
		while (choice > 6 || choice < 1) {
			System.out.println("invalid choice");
			for (int i = 0; i < Type.values().length; i++) {
				System.out.println((i + 1) + "-" + Type.values()[i]);
			}
			choice = input.nextInt();
		}
		TemplateAttribute t = new TemplateAttribute(cont, choice, num);
		templates.add(t);
	}

	/* our read methods */

	/** read all templates of the requested type and return them */
	public static void read() {
		int choice;
		Type matcherType;
		Scanner input = new Scanner(System.in);
		System.out.println("Available Types:");
		for (int i = 0; i < Type.values().length; i++) {
			System.out.println((i + 1) + "-" + Type.values()[i]);
		}
		choice = input.nextInt();
		while (choice > 6 || choice < 1) {
			System.out.println("Invalid choice");
			for (int i = 0; i < Type.values().length; i++) {
				System.out.println((i + 1) + "-" + Type.values()[i]);
			}
			choice = input.nextInt();
		}

		matcherType = TemplateAttribute.processType(choice);

		for (TemplateAttribute t : templates)
			if (matcherType.toString() == t.getTemptype()) {
				System.out.println("TempID: " + t.getId() + " TEMPTYPE: " + t.getTemptype());
				System.out.println("CONTENT: " + t.getContent());
			}
	}

	public static void readAll() {
		for (TemplateAttribute t : templates) {
			System.out.println("TempID: " + t.getId() + "\nTEMPTYPE: " + t.getTemptype());
			System.out.println("CONTENT: " + t.getContent());
		}
	}

	/** get template by id and returns it */
	public static TemplateAttribute get(int id) {
		for (TemplateAttribute t : templates)
			if (t.getId() == id)
				return t;

		return null;
	}

	/**
	 * update old element's content with new content
	 */
	public static void update() {
		delete();
		create();
	}

	/** delete element with the provided id */
	public static void delete() {
		Scanner input = new Scanner(System.in);
		Integer id;
		readAll();
		System.out.println("enter template ID");
		id = input.nextInt();
		templates.removeIf(t -> t.getId() == id);
	}

}
