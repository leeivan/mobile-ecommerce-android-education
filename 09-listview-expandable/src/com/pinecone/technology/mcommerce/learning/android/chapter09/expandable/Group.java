package com.pinecone.technology.mcommerce.learning.android.chapter09.expandable;
import java.util.ArrayList;
import java.util.List;

public class Group {

	public String string;
	public final List<String> children = new ArrayList<String>();

	public Group(String string) {
		this.string = string;
	}

}