package com.pinecone.technology.mcommerce.learning.android.chaptor09.dataloader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	static final String dbName = "demoDB";
	static final String employeeTable = "Employees";
	static final String colID = "EmployeeID";
	static final String colName = "EmployeeName";
	static final String colAge = "Age";
	static final String colDept = "Dept";

	static final String deptTable = "Dept";
	static final String colDeptID = "DeptID";
	static final String colDeptName = "DeptName";

	static final String viewEmps = "ViewEmps";

	public DatabaseHelper(Context context) {
		super(context, dbName, null, 33);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL("CREATE TABLE " + deptTable + " (" + colDeptID
				+ " INTEGER PRIMARY KEY , " + colDeptName + " TEXT)");

		db.execSQL("CREATE TABLE " + employeeTable + " (" + colID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + colName + " TEXT, "
				+ colAge + " Integer, " + colDept
				+ " INTEGER NOT NULL ,FOREIGN KEY (" + colDept
				+ ") REFERENCES " + deptTable + " (" + colDeptID + "));");

		db.execSQL("CREATE TRIGGER fk_empdept_deptid " + " BEFORE INSERT "
				+ " ON " + employeeTable +

				" FOR EACH ROW BEGIN" + " SELECT CASE WHEN ((SELECT "
				+ colDeptID + " FROM " + deptTable + " WHERE " + colDeptID
				+ "=new." + colDept + " ) IS NULL)"
				+ " THEN RAISE (ABORT,'Foreign Key Violation') END;" + "  END;");

		db.execSQL("CREATE VIEW " + viewEmps + " AS SELECT " + employeeTable
				+ "." + colID + " AS _id," + " " + employeeTable + "."
				+ colName + "," + " " + employeeTable + "." + colAge + ","
				+ " " + deptTable + "." + colDeptName + "" + " FROM "
				+ employeeTable + " JOIN " + deptTable + " ON " + employeeTable
				+ "." + colDept + " =" + deptTable + "." + colDeptID);
		// Inserts pre-defined departments
		InsertDepts(db);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		db.execSQL("DROP TABLE IF EXISTS " + employeeTable);
		db.execSQL("DROP TABLE IF EXISTS " + deptTable);

		db.execSQL("DROP TRIGGER IF EXISTS dept_id_trigger");
		db.execSQL("DROP TRIGGER IF EXISTS dept_id_trigger22");
		db.execSQL("DROP TRIGGER IF EXISTS fk_empdept_deptid");
		db.execSQL("DROP VIEW IF EXISTS " + viewEmps);
		onCreate(db);
	}

	void AddEmployee(Employee emp) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();

		cv.put(colName, emp.getName());
		cv.put(colAge, emp.getAge());
		cv.put(colDept, emp.getDept());
		// cv.put(colDept,2);

		db.insert(employeeTable, colName, cv);
		db.close();

	}

	int getEmployeeCount() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cur = db.rawQuery("Select * from " + employeeTable, null);
		int x = cur.getCount();
		cur.close();
		return x;
	}

	Cursor getAllEmployees() {
		SQLiteDatabase db = this.getWritableDatabase();

		// Cursor cur=
		// db.rawQuery("Select "+colID+" as _id , "+colName+", "+colAge+" from "+employeeTable,
		// new String [] {});
		Cursor cur = db.rawQuery("SELECT * FROM " + viewEmps, null);
		return cur;

	}

	Cursor getAllDepts() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cur = db.rawQuery("SELECT " + colDeptID + " as _id, "
				+ colDeptName + " from " + deptTable, new String[] {});

		return cur;
	}

	void InsertDepts(SQLiteDatabase db) {
		ContentValues cv = new ContentValues();
		cv.put(colDeptID, 1);
		cv.put(colDeptName, "Sales");
		db.insert(deptTable, colDeptID, cv);
		cv.put(colDeptID, 2);
		cv.put(colDeptName, "IT");
		db.insert(deptTable, colDeptID, cv);
		cv.put(colDeptID, 3);
		cv.put(colDeptName, "HR");
		db.insert(deptTable, colDeptID, cv);
		db.insert(deptTable, colDeptID, cv);

	}

	public String GetDept(int ID) {
		SQLiteDatabase db = this.getReadableDatabase();

		String[] params = new String[] { String.valueOf(ID) };
		Cursor c = db.rawQuery("SELECT " + colDeptName + " FROM" + deptTable
				+ " WHERE " + colDeptID + "=?", params);
		c.moveToFirst();
		int index = c.getColumnIndex(colDeptName);
		return c.getString(index);
	}

	public Cursor getEmpByDept(String Dept) {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] columns = new String[] { "_id", colName, colAge, colDeptName };
		Cursor c = db.query(viewEmps, columns, colDeptName + "=?",
				new String[] { Dept }, null, null, null);
		return c;
	}

	public int GetDeptID(String Dept) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(deptTable, new String[] { colDeptID + " as _id",
				colDeptName }, colDeptName + "=?", new String[] { Dept }, null,
				null, null);
		// Cursor
		// c=db.rawQuery("SELECT "+colDeptID+" as _id FROM "+deptTable+" WHERE "+colDeptName+"=?",
		// new String []{Dept});
		c.moveToFirst();
		return c.getInt(c.getColumnIndex("_id"));

	}

	public int UpdateEmp(Employee emp) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(colName, emp.getName());
		cv.put(colAge, emp.getAge());
		cv.put(colDept, emp.getDept());
		return db.update(employeeTable, cv, colID + "=?",
				new String[] { String.valueOf(emp.getID()) });

	}

	public void DeleteEmp(Employee emp) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(employeeTable, colID + "=?",
				new String[] { String.valueOf(emp.getID()) });
		db.close();

	}

}
