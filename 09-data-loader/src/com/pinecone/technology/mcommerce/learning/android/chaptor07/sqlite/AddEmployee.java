package com.pinecone.technology.mcommerce.learning.android.chaptor07.sqlite;

import android.app.Dialog;
import android.app.Fragment;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.content.Loader.ForceLoadContentObserver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.OperationCanceledException;
import android.text.Spannable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class AddEmployee extends Fragment implements LoaderCallbacks<Cursor> {
	private static final String TAG = "AddEmployee";
	EditText txtName;
	EditText txtAge;
	TextView txtEmps;
	DatabaseHelper dbHelper;
	Spinner spinDept;
	private SimpleCursorAdapter mAdapter;
	private Button btnAdd;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		dbHelper = new DatabaseHelper(getActivity());
		return inflater.inflate(R.layout.addemployee, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		txtName = (EditText) getActivity().findViewById(R.id.txtName);
		txtAge = (EditText) getActivity().findViewById(R.id.txtAge);
		txtEmps = (TextView) getActivity().findViewById(R.id.txtEmps);
		spinDept = (Spinner) getActivity().findViewById(R.id.spinDept);
		btnAdd = (Button) getActivity().findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean ok = true;
				try {
					Spannable spn = txtAge.getText();
					String name = txtName.getText().toString();
					int age = Integer.valueOf(spn.toString());
					int deptID = Integer.valueOf((int) spinDept
							.getSelectedItemId());
					Employee emp = new Employee(name, age, deptID);
					dbHelper.AddEmployee(emp);

				} catch (Exception ex) {
					ok = false;
					CatchError(ex.toString());
				} finally {
					if (ok) {
						// NotifyEmpAdded();
						Alerts.ShowEmpAddedAlert(getActivity());
						txtEmps.setText("Number of employees "
								+ String.valueOf(dbHelper.getEmployeeCount()));
					}
				}
			}
		});
		mAdapter = new SimpleCursorAdapter(getActivity(),
				R.layout.deptspinnerrow, null, new String[] {
						DatabaseHelper.colDeptName, "_id" },
				new int[] { R.id.txtDeptName }, 0);
		spinDept.setAdapter(mAdapter);
		getLoaderManager().initLoader(1, null, this);
	}

	void CatchError(String Exception) {
		Dialog diag = new Dialog(getActivity());
		diag.setTitle("Add new Employee");
		TextView txt = new TextView(getActivity());
		txt.setText(Exception);
		diag.setContentView(txt);
		diag.show();
	}

	void NotifyEmpAdded() {
		Dialog diag = new Dialog(getActivity());
		diag.setTitle("Add new Employee");
		TextView txt = new TextView(getActivity());
		txt.setText("Employee Added Successfully");
		diag.setContentView(txt);
		diag.show();
		try {
			diag.wait(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			CatchError(e.toString());
		}
		diag.notify();
		diag.dismiss();
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return new DeptCursorLoader(getActivity(), dbHelper);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
		// TODO Auto-generated method stub
		if (mAdapter != null && cursor != null)
			mAdapter.swapCursor(cursor); // swap the new cursor in.
		else
			Log.v(TAG, "OnLoadFinished: mAdapter is null");
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		if (mAdapter != null)
			mAdapter.swapCursor(null);
		else
			Log.v(TAG, "OnLoadFinished: mAdapter is null");
	}

}
