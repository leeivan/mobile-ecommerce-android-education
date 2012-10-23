package com.google.mcommerce.sample.android.chapter07.preferences;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.google.mcommerce.sample.android.R;

public class FlightPreferenceSingle extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.flightoptions_single);
	}
}
