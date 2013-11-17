package com.pinecone.technology.mcommerce.learning.android.chapter10.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class StudentsContract {
	public static final String AUTHORITY = "com.pinecone.technology.studentprovider";

	private StudentsContract() {
	}

	// inner class describing columns and their types
	public static final class Student implements BaseColumns {
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/students");
		// Expose a content URI for this provider. This URI will be used to
		// access the Content Provider
		// from within application components using a ContentResolver
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/student";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/student";
		/**
		 * SQL table columns
		 */
		public static final String DEFAULT_STUDENT_SORT_ORDER = "DeptId";
		public static final String NAME = "StdName";
		public static final String AGE = "Age";
		public static final String DEPT = "DeptId";

	}

	public static final class Department implements BaseColumns {
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/departments");
		// Expose a content URI for this provider. This URI will be used to
		// access the Content Provider
		// from within application components using a ContentResolver
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/department";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/department";
		/**
		 * SQL table columns
		 */
		public static final String NAME = "DeptName";
		public static final String DEFAULT_DEPARTMENT_SORT_ORDER = "DeptName";
	}
}