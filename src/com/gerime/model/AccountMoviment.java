package com.gerime.model;

public class AccountMoviment {
	private static final String OUT_STRING = "(-)";
	private static final String IN_STRING = "(+)";
	public static final int IN = 1;
	public static final int OUT = 0;
	private long id;
	private long account;
	private String description;
	private float value = 0;
	private String date;
	private int type;

	public long getAccount() {
		return account;
	}

	public void setAccount(long account) {
		this.account = account;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String typeToString() {
		return isIn() ? IN_STRING : OUT_STRING;
	}

	public boolean isIn() {
		return !isOut();
	}

	public boolean isOut() {
		return getType() == OUT;
	}

	public String toString() {
		return typeToString() + " " + description + " R$ " + getValue();
	}

};
