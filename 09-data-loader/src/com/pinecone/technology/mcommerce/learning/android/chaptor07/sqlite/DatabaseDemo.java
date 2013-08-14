package com.pinecone.technology.mcommerce.learning.android.chaptor07.sqlite;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

public class DatabaseDemo extends Activity {
	DatabaseHelper dbHelper;
	GridView grid;
	TextView txtTest;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Notice that setContentView() is not used, because we use the root
		// android.R.id.content as the container for each fragment

		// setup action bar for tabs
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);

		Tab tab = actionBar
				.newTab()
				.setText(R.string.add_employee)
				.setTabListener(
						new TabListener<AddEmployee>(this, "add",
								AddEmployee.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText(R.string.list_employee)
				.setTabListener(
						new TabListener<EmployeesList>(this, "list",
								EmployeesList.class));
		actionBar.addTab(tab);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 1, "Add Employee");
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Add employee
		case 1:
			Intent addIntent = new Intent(this, AddEmployee.class);
			startActivity(addIntent);
			break;
		}
		super.onOptionsItemSelected(item);
		return false;
	}

}