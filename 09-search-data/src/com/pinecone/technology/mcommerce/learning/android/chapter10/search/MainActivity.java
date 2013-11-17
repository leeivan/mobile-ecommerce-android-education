package com.pinecone.technology.mcommerce.learning.android.chapter10.search;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements
		SearchView.OnQueryTextListener, SearchView.OnCloseListener {

	private ListView myList;
	private SearchView searchView;
	private SearchHelper mDbHelper;
	private MyCustomAdapter defaultAdapter;
	private ArrayList<String> nameList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		nameList = new ArrayList<String>();

		// for simplicity we will add the same name for 20 times to populate the
		// nameList view
		for (int i = 0; i < 20; i++) {
			nameList.add("Diana" + i);
		}

		// relate the listView from java to the one created in xml
		myList = (ListView) findViewById(R.id.list);

		// show the ListView on the screen
		// The adapter MyCustomAdapter is responsible for maintaining the data
		// backing this nameList and for producing
		// a view to represent an item in that data set.
		defaultAdapter = new MyCustomAdapter(MainActivity.this, nameList);
		myList.setAdapter(defaultAdapter);

		// prepare the SearchView
		searchView = (SearchView) findViewById(R.id.search);

		// Sets the default or resting state of the search field. If true, a
		// single search icon is shown by default and
		// expands to show the text field and other buttons when pressed. Also,
		// if the default state is iconified, then it
		// collapses to that state when the close button is pressed. Changes to
		// this property will take effect immediately.
		// The default value is true.
		searchView.setIconifiedByDefault(false);

		searchView.setOnQueryTextListener(this);
		searchView.setOnCloseListener(this);

		mDbHelper = new SearchHelper(this);
		mDbHelper.open();

		// Clear all names
		mDbHelper.deleteAllNames();

		// Create the list of names which will be displayed on search
		for (String name : nameList) {
			mDbHelper.createList(name);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (mDbHelper != null) {
			mDbHelper.close();
		}
	}

	@Override
	public boolean onClose() {
		myList.setAdapter(defaultAdapter);
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		displayResults(query + "*");
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		if (!newText.isEmpty()) {
			displayResults(newText + "*");
		} else {
			myList.setAdapter(defaultAdapter);
		}

		return false;
	}

	/**
	 * Method used for performing the search and displaying the results. This
	 * method is called every time a letter is introduced in the search field.
	 * 
	 * @param query
	 *            Query used for performing the search
	 */
	private void displayResults(String query) {

		Cursor cursor = mDbHelper.searchByInputText((query != null ? query
				: "@@@@"));

		if (cursor != null) {

			String[] from = new String[] { SearchHelper.COLUMN_NAME };

			// Specify the view where we want the results to go
			int[] to = new int[] { R.id.search_result_text_view };

			// Create a simple cursor adapter to keep the search data
			SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
					R.layout.result_search_item, cursor, from, to);
			myList.setAdapter(cursorAdapter);

			// Click listener for the searched item that was selected
			myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					// Get the cursor, positioned to the corresponding row in
					// the result set
					Cursor cursor = (Cursor) myList.getItemAtPosition(position);

					// Get the state's capital from this row in the database.
					String selectedName = cursor.getString(cursor
							.getColumnIndexOrThrow("name"));
					Toast.makeText(MainActivity.this, selectedName, 0).show();

					// Set the default adapter
					myList.setAdapter(defaultAdapter);

					// Find the position for the original list by the selected
					// name from search
					for (int pos = 0; pos < nameList.size(); pos++) {
						if (nameList.get(pos).equals(selectedName)) {
							position = pos;
							break;
						}
					}

					// Create a handler. This is necessary because the adapter
					// has just been set on the list again and
					// the list might not be finished setting the adapter by the
					// time we perform setSelection.
					Handler handler = new Handler();
					final int finalPosition = position;
					handler.post(new Runnable() {
						@Override
						public void run() {

							myList.setSelection(finalPosition);
						}
					});

					searchView.setQuery("", true);
				}
			});

		}
	}
}