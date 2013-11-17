package com.pinecone.technology.mcommerce.learning.android.chaptor09.dataloader;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class Utilities {
	static public void ManageDeptSpinner(Context context, Spinner view) {
		DatabaseHelper dbHelper = new DatabaseHelper(context);
		Cursor c = dbHelper.getAllDepts();
		// context.startManagingCursor(c);

		// SimpleCursorAdapter ca=new
		// SimpleCursorAdapter(this,android.R.layout.simple_spinner_item, c, new
		// String [] {DatabaseHelper.colDeptName}, new int
		// []{android.R.id.text1});
		SimpleCursorAdapter ca = new SimpleCursorAdapter(context,
				R.layout.deptspinnerrow, c, new String[] {
						DatabaseHelper.colDeptName, "_id" },
				new int[] { R.id.txtDeptName }, 0);
		view.setAdapter(ca);

	}
}
