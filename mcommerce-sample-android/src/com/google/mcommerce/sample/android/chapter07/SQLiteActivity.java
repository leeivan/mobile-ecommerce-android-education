package com.google.mcommerce.sample.android.chapter07;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;

import com.google.mcommerce.sample.android.chapter07.sqlite.ContactsDataSource;
import com.google.mcommerce.sample.android.chapter07.sqlite.MySQLiteHelper;
import com.google.mcommerce.sample.android.R;

public class SQLiteActivity extends ListActivity {
	private ContactsDataSource datasource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c07_sqlite);

		datasource = new ContactsDataSource(this);
		datasource.open();

		Cursor values = datasource.getAllContacts();

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		String[] from = { MySQLiteHelper.COLUMN_NAME,
				MySQLiteHelper.COLUMN_EMAIL };
		int[] to = { android.R.id.text1, android.R.id.text2 };
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this.getApplicationContext(),
				android.R.layout.simple_list_item_2, values, from, to);
		// ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this,
		// android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	// Will be called via the onClick attribute
	// of the buttons in main.xml
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		SimpleCursorAdapter adapter = (SimpleCursorAdapter) getListAdapter();
		Cursor cursor = null;
		switch (view.getId()) {
		case R.id.add:
			cursor = datasource.insertContact("Tom", "tom@gmail.com");
			break;
		case R.id.delete:
			if (getListAdapter().getCount() > 0) {
				cursor = (Cursor) adapter.getItem(0);
				cursor = datasource.deleteContact(cursor);
			}
			break;
		}
		adapter.changeCursor(cursor);
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

}