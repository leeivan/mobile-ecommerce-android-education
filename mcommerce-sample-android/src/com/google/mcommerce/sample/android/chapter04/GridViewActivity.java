package com.google.mcommerce.sample.android.chapter04;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

import com.google.mcommerce.sample.android.R;

public class GridViewActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c05_gridview);

		GridView gv = (GridView) findViewById(R.id.gridview);

		Cursor c = managedQuery(Contacts.CONTENT_URI, null, null, null,
				Contacts.DISPLAY_NAME);

		String[] cols = new String[] { Contacts.DISPLAY_NAME };
		int[] views = new int[] { android.R.id.text1 };

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, c, cols, views);
		gv.setAdapter(adapter);
	}
}
