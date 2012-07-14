package com.google.mcommerce.sample.android.chapter07.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ContactsDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	public static final String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_EMAIL };

	public ContactsDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Cursor insertContact(String name, String email) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NAME, name);
		values.put(MySQLiteHelper.COLUMN_EMAIL, email);
		database.insert(MySQLiteHelper.TABLE_CONTACTS, null, values);
		Cursor cursor = getAllContacts();
		return cursor;
	}

	public Cursor deleteContact(Cursor cursor) {
		long id = cursor.getLong(0);
		database.delete(MySQLiteHelper.TABLE_CONTACTS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
		cursor = getAllContacts();
		return cursor;
	}

	public Cursor getAllContacts() {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CONTACTS,
				allColumns, null, null, null, null, null);
		return cursor;
	}
}