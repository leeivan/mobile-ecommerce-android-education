package com.pinecome.technology.mcommerce.learning.android.chaper08;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.pinecone.technology.mcommerce.learning.android.chapter07.sqlite.DatabaseHelper;

public class StudentsProvider extends ContentProvider {

	private static final String STUDENT_TABLE = "students";
	private static final String DEPARTMENT_TABLE = "departments";

	private static final int STUDENTS = 1;
	private static final int STUDENT_ID = 2;
	private static final int DEPARTMENTS = 3;
	private static final int DEPARTMENT_ID = 4;
	private static final UriMatcher MATCHER;
	private static final String TAG = "StudentsProvider";
	private DatabaseHelper dbHelper = null;

	static {
		MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		MATCHER.addURI(StudentsContract.AUTHORITY, "students", STUDENTS);
		MATCHER.addURI(StudentsContract.AUTHORITY, "students/#", STUDENT_ID);
		MATCHER.addURI(StudentsContract.AUTHORITY, "departments", DEPARTMENTS);
		MATCHER.addURI(StudentsContract.AUTHORITY, "departments/#",
				DEPARTMENT_ID);
	}
	private static HashMap<String, String> sStudentProjectionMap;
	static {
		sStudentProjectionMap = new HashMap<String, String>();
		sStudentProjectionMap.put(StudentsContract.Student._ID,
				StudentsContract.Student._ID);

		sStudentProjectionMap.put(StudentsContract.Student.NAME,
				StudentsContract.Student.NAME);
		sStudentProjectionMap.put(StudentsContract.Student.AGE,
				StudentsContract.Student.AGE);
		sStudentProjectionMap.put(StudentsContract.Department.NAME,
				StudentsContract.Department.NAME);
	}

	@Override
	public boolean onCreate() {
		Log.d("Provider", "onCreate");
		dbHelper = new DatabaseHelper(getContext());
		return ((dbHelper == null) ? false : true);
	}

	/**
	 * Return the MIME type of the data at the given URI. This should start with
	 * "vnd.android.cursor.item" for a single record, or
	 * "vnd.android.cursor.dir" for multiple items. This method can be called
	 * from multiple threads, as described in
	 */
	@Override
	public String getType(Uri url) {
		final int match = MATCHER.match(url);
		switch (match) {
		case STUDENTS:
			return StudentsContract.Student.CONTENT_TYPE;
		case STUDENT_ID:
			return StudentsContract.Student.CONTENT_ITEM_TYPE;
		case DEPARTMENTS:
			return StudentsContract.Department.CONTENT_TYPE;
		case DEPARTMENT_ID:
			return StudentsContract.Department.CONTENT_ITEM_TYPE;
		default:
			throw new IllegalArgumentException("Unsupported URI: " + url);
		}
	}

	@Override
	public Cursor query(Uri url, String[] projection, String selection,
			String[] selectionArgs, String sort) {
		Log.d(TAG, "query");
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		Cursor c = null;
		String orderBy = null;
		switch (MATCHER.match(url)) {
		case STUDENTS:
			qb.setTables(STUDENT_TABLE);
			qb.setProjectionMap(sStudentProjectionMap);
			if (TextUtils.isEmpty(sort)) {
				orderBy = StudentsContract.Student.DEFAULT_STUDENT_SORT_ORDER;
			} else {
				orderBy = sort;
			}
			break;
		case STUDENT_ID:
			qb.setTables(STUDENT_TABLE);
			qb.setProjectionMap(sStudentProjectionMap);
			qb.appendWhere(StudentsContract.Student._ID + "="
					+ url.getPathSegments().get(1));
			break;
		case DEPARTMENTS:
			qb.setTables(DEPARTMENT_TABLE);
			qb.setProjectionMap(sStudentProjectionMap);
			if (TextUtils.isEmpty(sort)) {
				orderBy = StudentsContract.Department.DEFAULT_DEPARTMENT_SORT_ORDER;
			} else {
				orderBy = sort;
			}

			break;
		case DEPARTMENT_ID:
			qb.setTables(DEPARTMENT_TABLE);
			qb.setProjectionMap(sStudentProjectionMap);
			qb.appendWhere(StudentsContract.Department._ID + "="
					+ url.getPathSegments().get(1));
			break;
		}
		c = qb.query(dbHelper.getReadableDatabase(), projection, selection,
				selectionArgs, null, null, orderBy);
		c.setNotificationUri(getContext().getContentResolver(), url);
		return (c);
	}

	@Override
	public Uri insert(Uri url, ContentValues initialValues) {
		if (MATCHER.match(url) != STUDENTS) {
			throw new IllegalArgumentException("Unknown URI " + url);
		}
		if (initialValues.containsKey(StudentsContract.Student.NAME) == false) {
			throw new SQLException(
					"Failed to insert row because Book Name is needed " + url);
		}
		long rowID = dbHelper.getWritableDatabase().insert(STUDENT_TABLE,
				StudentsContract.Student.NAME, initialValues);
		if (rowID > 0) {
			Uri uri = ContentUris.withAppendedId(
					StudentsContract.Student.CONTENT_URI, rowID);
			getContext().getContentResolver().notifyChange(uri, null);
			return (uri);
		}
		throw new SQLException("Failed to insert row into " + url);
	}

	@Override
	public int delete(Uri url, String where, String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;
		switch (MATCHER.match(url)) {
		case STUDENTS:
			count = db.delete(STUDENT_TABLE, where, whereArgs);
			break;
		case STUDENT_ID:
			String rowId = url.getPathSegments().get(1);
			count = db.delete(
					STUDENT_TABLE,
					StudentsContract.Student._ID
							+ "="
							+ rowId
							+ (!TextUtils.isEmpty(where) ? " AND (" + where
									+ ')' : ""), whereArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + url);
		}
		getContext().getContentResolver().notifyChange(url, null);
		return (count);
	}

	@Override
	public int update(Uri url, ContentValues values, String where,
			String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;
		switch (MATCHER.match(url)) {
		case STUDENTS:
			count = db.update(STUDENT_TABLE, values, where, whereArgs);
			break;

		case STUDENT_ID:
			String rowId = url.getPathSegments().get(1);
			count = db.update(
					STUDENT_TABLE,
					values,
					StudentsContract.Student._ID
							+ "="
							+ rowId
							+ (!TextUtils.isEmpty(where) ? " AND (" + where
									+ ')' : ""), whereArgs);
			break;

		default:
			throw new IllegalArgumentException("Unknown URI " + url);
		}
		count = dbHelper.getWritableDatabase().update(STUDENT_TABLE, values,
				where, whereArgs);
		getContext().getContentResolver().notifyChange(url, null);
		return (count);
	}

}