package com.pinecone.technology.mcommerce.learning.android.chaptor07.sqlite;

import android.content.Context;

public class Employee {

	int _id;
	String _name;
	int _age;
	int _dept;

	public Employee(String Name, int Age, int Dept) {

		this._name = Name;
		this._age = Age;
		this._dept = Dept;
	}

	public Employee(String Name, int Age) {
		this._name = Name;
		this._age = Age;
	}

	public int getID() {
		return this._id;
	}

	public void SetID(int ID) {
		this._id = ID;
	}

	public String getName() {
		return this._name;
	}

	public int getAge() {
		return this._age;
	}

	public void setName(String Name) {
		this._name = Name;
	}

	public void setAge(int Age) {
		this._age = Age;
	}

	public void setDept(int Dept) {
		this._dept = Dept;
	}

	public String getDeptName(Context con, int Dept) {
		return new DatabaseHelper(con).GetDept(Dept);
	}

	public int getDept() {
		return this._dept;
	}
}
