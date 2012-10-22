package com.google.mcommerce.sample.android.chapter07.preferences;

// This file is MainActivity.java
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.mcommerce.sample.android.R;

public class PreferencesMultiActivity extends Activity {
	private Resources resources;
	private TextView tv = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		resources = this.getResources();

		tv = (TextView) findViewById(R.id.text1);

		setOptionText();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mainmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_prefs) {
			Intent intent = new Intent()
					.setClass(
							this,
							FlightPreferenceActivity02.class);
			this.startActivityForResult(intent, 0);
		}
		return true;
	}

	@Override
	public void onActivityResult(int reqCode, int resCode, Intent data) {
		super.onActivityResult(reqCode, resCode, data);
		setOptionText();
	}

	private void setOptionText() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		Set<String> option = prefs
				.getStringSet(resources
						.getString(R.string.selected_flight_sort_option), null);

		String[] optionText = resources
				.getStringArray(R.array.flight_sort_options);

		tv.setText("option value is " + option + ")");
	}
}