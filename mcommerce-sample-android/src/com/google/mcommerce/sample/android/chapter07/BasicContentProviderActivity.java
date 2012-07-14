package com.google.mcommerce.sample.android.chapter07;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.mcommerce.sample.android.R;

/**
 * @author ivan
 * 
 */
public class BasicContentProviderActivity extends Activity implements
		OnClickListener {
	private static final String TAG = "BasicContentProviderActivity";
	static final String[] mProjection = { UserDictionary.Words._ID, // Contract
																	// class
			// constant for the
			// _ID column name
			UserDictionary.Words.WORD, // Contract class constant for the
										// word column name
			UserDictionary.Words.LOCALE // Contract class constant for the
										// locale column name
	};
	private EditText mSearchWord;
	// Initializes an array to contain selection arguments
	/*
	 * This defines a one-element String array to contain the selection
	 * argument.
	 */
	private String mSortOrder = UserDictionary.Words.WORD + " ASC";

	private ListView mWordList;
	private Button mButtonInsert;
	private Button mButtonUpdate;
	private Button mButtonDelete;
	private Button mButtonSearch;
	private SimpleCursorAdapter mCursorAdapter;
	private Cursor mCursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.c07_basic_content_provider);
		mSearchWord = (EditText) findViewById(R.id.search_word);
		mButtonInsert = (Button) findViewById(R.id.button_insert);
		mButtonInsert.setOnClickListener(this);
		mButtonUpdate = (Button) findViewById(R.id.button_update);
		mButtonUpdate.setOnClickListener(this);
		mButtonDelete = (Button) findViewById(R.id.button_delete);
		mButtonDelete.setOnClickListener(this);
		mButtonSearch = (Button) findViewById(R.id.button_search);
		mButtonSearch.setOnClickListener(this);
		mWordList = (ListView) findViewById(R.id.listView);
		// A "projection" defines the columns that will be returned for each row
		// Defines a string to contain the selection clause
		// Remember to insert code here to check for invalid or malicious input.
		// If the word is the empty string, gets everything
		mCursor = getAllUserDictionary(null);

		// Some providers return null if an error occurs, others throw an
		// exception
		if (null == mCursor) {
			/*
			 * Insert code here to handle the error. Be sure not to use the
			 * cursor! You may want to call android.util.Log.e() to log this
			 * error.
			 */
			// If the Cursor is empty, the provider found no matches
			log("获取数据失败");
		} else if (mCursor.getCount() < 1) {

			/*
			 * Insert code here to notify the user that the search was
			 * unsuccessful. This isn't necessarily an error. You may want to
			 * offer the user the option to insert a new row, or re-type the
			 * search term.
			 */
			Toast.makeText(this, "没有数据，请插入数据", Toast.LENGTH_LONG).show();
		} else {
			// Insert code here to do something with the results
			// Defines a list of columns to retrieve from the Cursor and load
			// into an output row
			String[] mWordListColumns = { UserDictionary.Words.WORD, // Contract
																		// class
																		// constant
																		// containing
																		// the
																		// word
																		// column
																		// name
					UserDictionary.Words.LOCALE // Contract class constant
												// containing the locale column
												// name
			};

			// Defines a list of View IDs that will receive the Cursor columns
			// for each row
			int[] mWordListItems = { R.id.dictWord, R.id.locale };

			// Creates a new SimpleCursorAdapter
			mCursorAdapter = new SimpleCursorAdapter(getApplicationContext(), // The
																				// application's
																				// Context
																				// object
					R.layout.c07_wordlistrow, // A layout in XML for one row in
												// the ListView
					mCursor, // The result from the query
					mWordListColumns, // A string array of column names in the
										// cursor
					mWordListItems, // An integer array of view IDs in the row
									// layout
					0); // Flags (usually none are needed)

			// Sets the adapter for the ListView
			mCursorAdapter.notifyDataSetChanged();
			mWordList.setAdapter(mCursorAdapter);
		}
	}

	private Cursor getAllUserDictionary(String mSearchString) {
		String mSelectionClause;
		String[] mSelectionArgs = { "" };
		if (TextUtils.isEmpty(mSearchString)) {
			// Setting the selection clause to null will return all words
			mSelectionClause = null;
			mSelectionArgs = null;
		} else {
			// Constructs a selection clause that matches the word that the user
			// entered.
			mSelectionClause = UserDictionary.Words.WORD + " = ?";
			// Moves the user's input string to the selection arguments.
			mSelectionArgs[0] = mSearchString;
		}
		// Does a query against the table and returns a Cursor object
		Cursor mCursor = getContentResolver().query(
				UserDictionary.Words.CONTENT_URI, // The content URI of the
													// words table
				mProjection, // The columns to return for each row
				mSelectionClause, // Either null, or the word the user entered
				mSelectionArgs, // Either empty, or the string the user entered
				mSortOrder); // The sort order for the returned rows
		return mCursor;
	}

	private void log(String msg) {
		Log.d(TAG, msg);
	}

	@Override
	public void onClick(View v) {
		String mSelectionClause;
		String[] mSelectionArgs = new String[1];
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_insert:
			// Defines a new Uri object that receives the result of the
			// insertion
			Uri mNewUri;

			// Defines an object to contain the new values to insert
			ContentValues mNewValues = new ContentValues();

			/*
			 * Sets the values of each column and inserts the word. The
			 * arguments to the "put" method are "column name" and "value"
			 */
			mNewValues.put(UserDictionary.Words.LOCALE, "en_US");
			mNewValues.put(UserDictionary.Words.WORD, "insert");

			mNewUri = getContentResolver().insert(
					UserDictionary.Words.CONTENT_URI, // the user dictionary
														// content URI
					mNewValues // the values to insert
					);
			mCursor = getAllUserDictionary(null);
			mCursorAdapter.changeCursor(mCursor);
			Toast.makeText(this, "插入数据为" + ContentUris.parseId(mNewUri),
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.button_update:
			// Defines an object to contain the updated values
			ContentValues mUpdateValues = new ContentValues();

			// Defines selection criteria for the rows you want to update
			mSelectionClause = UserDictionary.Words.WORD + " LIKE ?";
			mSelectionArgs[0] = "in%";

			// Defines a variable to contain the number of updated rows
			int mRowsUpdated;
			/*
			 * Sets the updated value and updates the selected words.
			 */
			mUpdateValues.put(UserDictionary.Words.WORD, "update");

			mRowsUpdated = getContentResolver().update(
					UserDictionary.Words.CONTENT_URI, // the user dictionary
														// content URI
					mUpdateValues, // the columns to update
					mSelectionClause, // the column to select on
					mSelectionArgs // the value to compare to
					);
			mCursor = getAllUserDictionary(null);
			mCursorAdapter.changeCursor(mCursor);
			Toast.makeText(this, "更新行数为" + mRowsUpdated, Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.button_delete:
			// Defines selection criteria for the rows you want to delete
			mSelectionClause = UserDictionary.Words.WORD + " LIKE ?";
			mSelectionArgs[0] = "in%";

			// Defines a variable to contain the number of rows deleted
			int mRowsDeleted;

			// Deletes the words that match the selection criteria
			mRowsDeleted = getContentResolver().delete(
					UserDictionary.Words.CONTENT_URI, // the user dictionary
														// content URI
					mSelectionClause, // the column to select on
					mSelectionArgs // the value to compare to
					);
			mCursor = getAllUserDictionary(null);
			mCursorAdapter.changeCursor(mCursor);
			Toast.makeText(this, "删除行数为" + mRowsDeleted, Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.button_search:
			// Gets a word from the UI
			String mSearchString = mSearchWord.getText().toString();
			mCursor = getAllUserDictionary(mSearchString);
			mCursorAdapter.changeCursor(mCursor);
		}
	}
}
