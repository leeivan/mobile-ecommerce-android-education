package com.google.mcommerce.sample.android.chapter09.location;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GPSSettingsActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivityForResult(
            new Intent(
                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
                ), 0);
    }
}