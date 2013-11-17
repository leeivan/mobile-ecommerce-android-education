package com.pinecone.technology.mcommerce.learning.android.chapter10.contentprovider;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

	private static final String TAG = "MainActivity";
	private Cursor mCurser;
	private SimpleCursorAdapter mCursorAdapter;
	private ContentValues values;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		values = new ContentValues();
		values.put(StudentsContract.Student.NAME, "学生1");
		values.put(StudentsContract.Student.AGE, "30");
		values.put(StudentsContract.Student.DEPT, "3");
		getContentResolver().insert(StudentsContract.Student.CONTENT_URI,
				values);
		String[] mProjection = { StudentsContract.Student._ID,
				StudentsContract.Student.NAME, StudentsContract.Student.AGE };
		String mSelectionClause = null;

		// Initializes an array to contain selection arguments
		String[] mSelectionArgs = null;
		mCurser = getContentResolver().query(
				StudentsContract.Student.CONTENT_URI, mProjection,
				mSelectionClause, mSelectionArgs, null);
		if (null == mCurser) {
			Log.i(TAG, "Curser is null");
		} else {
			mCursorAdapter = new SimpleCursorAdapter(this,
					android.R.layout.simple_list_item_2, mCurser, new String[] {
							StudentsContract.Student.NAME,
							StudentsContract.Student.AGE }, new int[] {
							android.R.id.text1, android.R.id.text2 }, 0);
		}
		setListAdapter(mCursorAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
