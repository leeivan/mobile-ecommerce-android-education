package com.pinecone.technology.mcommerce.learning.android.chapter07.musicstore;

import java.text.DateFormat;
import java.util.Date;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.pinecone.technology.mcommerce.learning.android.chapter07.example.dao.Customer;
import com.pinecone.technology.mcommerce.learning.android.chapter07.example.dao.CustomerDao;
import com.pinecone.technology.mcommerce.learning.android.chapter07.example.dao.DaoMaster;
import com.pinecone.technology.mcommerce.learning.android.chapter07.example.dao.DaoMaster.DevOpenHelper;
import com.pinecone.technology.mcommerce.learning.android.chapter07.example.dao.DaoSession;

public class MainActivity extends ListActivity {

	private SQLiteDatabase db;

	private EditText editText;

	private DaoMaster daoMaster;
	private DaoSession daoSession;
	private CustomerDao customerDao;

	private Cursor cursor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db",
				null);
		db = helper.getWritableDatabase();
		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
		customerDao = daoSession.getCustomerDao();
		addCustomer();

		String nameColumn = CustomerDao.Properties.FirstName.columnName;
		String orderBy = nameColumn + " COLLATE LOCALIZED ASC";
		cursor = db.query(customerDao.getTablename(),
				customerDao.getAllColumns(), null, null, null, null, orderBy);
		String[] from = { nameColumn,
				CustomerDao.Properties.LastName.columnName };
		int[] to = { android.R.id.text1, android.R.id.text2 };

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, cursor, from, to);
		setListAdapter(adapter);

		editText = (EditText) findViewById(R.id.editTextNote);
		addUiListeners();
	}

	protected void addUiListeners() {
		editText.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					addCustomer();
					return true;
				}
				return false;
			}
		});

		final View button = findViewById(R.id.buttonAdd);
		button.setEnabled(false);
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				boolean enable = s.length() != 0;
				button.setEnabled(enable);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	public void onMyButtonClick(View view) {
		addCustomer();
	}

	private void addCustomer() {
//		String noteText = editText.getText().toString();
//		editText.setText("");

		final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM);
		String comment = "Added on " + df.format(new Date());
		Customer note = new Customer(null, "ivan", comment, comment, comment,
				comment, comment, comment, comment, comment, comment, comment,
				null);
		customerDao.insert(note);
		Log.d("DaoExample", "Inserted new note, ID: " + note.getId());

		cursor.requery();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		customerDao.deleteByKey(id);
		Log.d("DaoExample", "Deleted note, ID: " + id);
		cursor.requery();
	}

}
