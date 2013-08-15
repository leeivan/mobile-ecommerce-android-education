package com.pinecone.technology.mcommerce.learning.android.chaptor07.sqlite.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pinecone.technology.mcommerce.learning.android.chapter07.sqlite.DatabaseHelper;
import com.pinecone.technology.mcommerce.learning.android.chapter07.sqlite.Student;

public class DataStorageTest extends AndroidTestCase {

	private static final String TAG = "DataStorageTest";
	private DatabaseHelper dbHelper;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		dbHelper = new DatabaseHelper(this.getContext());
	}

	public void testSave() throws Exception {
		for (int i = 0; i < 10; i++) {
			dbHelper.addStudent(new Student("学生" + i, (short) (i + 10), 1));
		}
	}

	public void testDeptName() throws Exception {
		String deptName = dbHelper.getDept(1);
		Log.i(TAG, deptName);

	}

	public void testUpdate() throws Exception {
		Student std = new Student("学生1", 1, 1);
		dbHelper.UpdateStd(std);
	}

	public void testGetStudentCount() throws Exception {

		int stdCount = dbHelper.getStudentCount();
		Log.i(TAG, String.valueOf(stdCount));
	}

	public void testDelete() throws Exception {

		Student std = new Student("学生1", 1, 1);
		dbHelper.deleteStd(std);
	}
}
