package com.pinecone.technology.mcommerce.learning.android.chaptor07.sqlite;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.os.CancellationSignal;
import android.os.OperationCanceledException;
import android.util.Log;

public class DeptCursorLoader extends CursorLoader {

	private CancellationSignal mCancellationSignal;
	private DatabaseHelper dbHelper;
	private Cursor mCursor;

	public DeptCursorLoader(Context context, DatabaseHelper dbh) {
		super(context);
		dbHelper = dbh;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cursor loadInBackground() {
		// TODO Auto-generated method stub
		synchronized (this) {
			if (isLoadInBackgroundCanceled()) {
				throw new OperationCanceledException();
			}
			mCancellationSignal = new CancellationSignal();
		}
		try {
			// txtEmps.setText(txtEmps.getText()
			// + String.valueOf(dbHelper.getEmployeeCount()));

			mCursor = dbHelper.getAllDepts();
			return mCursor;
		} finally {
			synchronized (this) {
				mCancellationSignal = null;
			}
		}
	}

}
