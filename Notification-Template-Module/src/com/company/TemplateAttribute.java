package com.company;

import java.util.ArrayList;

enum Type {
	PASSWORD, VERIFICATION, LATE, ORDERS, ANNOUNCEMENT, PROMOTION
}

public class TemplateAttribute {
	String content;
	Type temptype;
	int numberofunknowns;
	static int id = 0;

	TemplateAttribute(String content, int type, int numberofunknowns) {
		this.setContent(content);
		this.setNumberofunknowns(numberofunknowns);
		this.setId(++id);
		this.temptype = processType(type);
	}

	public static Type processType(Integer choice) {
		switch (choice) {
		case (1):
			return Type.values()[0];
		case (2):
			return Type.values()[1];
		case (3):
			return Type.values()[2];
		case (4):
			return Type.values()[3];
		case (5):
			return Type.values()[4];
		case (6):
			return Type.values()[5];
		}
		return Type.values()[0];
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

	public int getNumberOfUnknowns() {
		return numberofunknowns;
	}

	public String getContent() {
		return content;
	}

	public String getTemptype() {
		return temptype.toString();
	}

	public String processTemp(ArrayList<String> toReplace) {
		String toBeSent = this.content;
		toBeSent = toBeSent.replaceAll("\\{.*?\\}", "#");
		for (int i = 0; i < this.numberofunknowns; i++) {
			toBeSent = toBeSent.replaceFirst("#", toReplace.get(i));
		}
		return toBeSent;
	}
}