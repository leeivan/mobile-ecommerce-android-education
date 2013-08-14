package com.pinecone.technology.mcommerce.learning.android.chaptor07.sqlite;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.os.CancellationSignal;
import android.os.OperationCanceledException;
import android.util.Log;

public class EmpCursorLoader extends CursorLoader {

	private DatabaseHelper dbHelper;
	private Cursor mCursor;
	private String mDept;
	private CancellationSignal mCancellationSignal;

	public EmpCursorLoader(Context context, String dept) {
		super(context);
		mDept = dept;
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
			dbHelper = new DatabaseHelper(getContext());
			mCursor = dbHelper.getEmpByDept(mDept);
			Log.v("EmployeesList", 22222+mDept);
			Log.v("EmployeesList", mCursor.getCount() + "");

			return mCursor;
		} finally {
			synchronized (this) {
				mCancellationSignal = null;
			}
		}
	}

}
