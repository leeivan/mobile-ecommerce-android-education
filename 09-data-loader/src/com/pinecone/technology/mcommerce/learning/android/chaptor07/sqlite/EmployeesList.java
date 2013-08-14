package com.pinecone.technology.mcommerce.learning.android.chaptor07.sqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class EmployeesList extends Fragment implements LoaderCallbacks<Cursor> {
	private static final String TAG = "EmployeesList";
	private DatabaseHelper dbHelper;
	static public GridView grid;
	private TextView txtTest;
	private Spinner spinDept1;
	private String dept = "";
	private SimpleCursorAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.gridview, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		final DatabaseHelper db = new DatabaseHelper(getView().getContext());
		txtTest = (TextView) getActivity().findViewById(R.id.txtTest);
		grid = (GridView) getActivity().findViewById(R.id.grid);
		spinDept1 = (Spinner) getActivity().findViewById(R.id.spinDept1);
		TextView txt = (TextView) spinDept1.getSelectedView().findViewById(
				R.id.txtDeptName);
		dept = String.valueOf(txt.getText());
		Utilities.ManageDeptSpinner(getActivity(), spinDept1);
		String[] from = new String[] { DatabaseHelper.colName,
				DatabaseHelper.colAge, DatabaseHelper.colDeptName };
		int[] to = new int[] { R.id.colName, R.id.colAge, R.id.colDept };
		mAdapter = new SimpleCursorAdapter(getView().getContext(),
				R.layout.gridrow, null, from, to, 0);
		grid.setAdapter(mAdapter);
		spinDept1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
//				View v = spinDept1.getSelectedView();
//				TextView txt = (TextView) v.findViewById(R.id.txtDeptName);
//				dept = String.valueOf(txt.getText());
				onActivityCreated(getActivity().get);
				Log.v(TAG, 111111 + dept);
//				grid.setAdapter(mAdapter);
				// sca.notifyDataSetChanged();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				try {

					SQLiteCursor cr = (SQLiteCursor) parent
							.getItemAtPosition(position);
					String name = cr.getString(cr
							.getColumnIndex(DatabaseHelper.colName));
					int age = cr.getInt(cr
							.getColumnIndex(DatabaseHelper.colAge));
					String Dept = cr.getString(cr
							.getColumnIndex(DatabaseHelper.colDeptName));
					Employee emp = new Employee(name, age, db.GetDeptID(Dept));
					emp.SetID((int) id);
					AlertDialog diag = Alerts.ShowEditDialog(getView()
							.getContext(), emp);
					diag.setOnDismissListener(new OnDismissListener() {

						@Override
						public void onDismiss(DialogInterface dialog) {
							// TODO Auto-generated method stub
							txtTest.setText("dismissed");
							// ((SimpleCursorAdapter)grid.getAdapter()).notifyDataSetChanged();
							String[] from = new String[] {
									DatabaseHelper.colName,
									DatabaseHelper.colAge,
									DatabaseHelper.colDeptName };
							int[] to = new int[] { R.id.colName, R.id.colAge,
									R.id.colDept };
							mAdapter = new SimpleCursorAdapter(getView()
									.getContext(), R.layout.gridrow, null,
									from, to, 0);
							grid.setAdapter(mAdapter);
						}
					});
					diag.show();
				} catch (Exception ex) {
					Alerts.CatchError(getView().getContext(), ex.toString());
				}
			}

		});
		getLoaderManager().initLoader(1, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new EmpCursorLoader(getActivity(), dept);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// TODO Auto-generated method stub
		if (mAdapter != null && cursor != null)
			mAdapter.swapCursor(cursor); // swap the new cursor in.
		else
			Log.v(TAG, "OnLoadFinished: mAdapter is null");
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		if (mAdapter != null)
			mAdapter.swapCursor(null);
		else
			Log.v(TAG, "OnLoadFinished: mAdapter is null");
	}

}
