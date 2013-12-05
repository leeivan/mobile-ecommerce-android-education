package com.pinecone.technology.mcommerce.learning.android.chapter06.sqlite;

import android.content.Context;

public class Student {

	int _id;
	String _name;
	int _age;
	int _dept;

	public Student(String name, int age, int dept) {

		this._name = name;
		this._age = age;
		this._dept = dept;
	}

	public Student(String name, int age) {
		this._name = name;
		this._age = age;
	}

	public int getID() {
		return this._id;
	}

	public void SetID(int id) {
		this._id = id;
	}

	public String getName() {
		return this._name;
	}

	public int getAge() {
		return this._age;
	}

	public void setName(String name) {
		this._name = name;
	}

	public void setAge(int age) {
		this._age = age;
	}

	public void setDept(int dept) {
		this._dept = dept;
	}

	public String getDeptName(Context con, int dept) {
		return new DatabaseHelper(con).getDept(dept);
	}

	public int getDept() {
		return this._dept;
	}
}
