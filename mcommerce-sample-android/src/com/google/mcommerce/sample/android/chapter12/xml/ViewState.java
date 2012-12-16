package com.google.mcommerce.sample.android.chapter12.xml;

public class ViewState {

	private String name;
	private int id;
	private String text;

	public ViewState() {
		// to do
	}

	public ViewState(String name, String text, int id) {
		this.name = name;
		this.id = id;
		this.text = text;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}