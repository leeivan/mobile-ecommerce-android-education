package com.pinecone.technology.mcommerce.learning.android.chapter05.intent;

import com.pinecone.technology.mcommerce.learning.android.chapter05.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivitySecond extends Activity {

	private static final String TAG = "ActivitySecond";

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.c05_activity_second_layout);
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		String value = extras.getString("Value");
		if (value != null) {
			EditText text = (EditText) findViewById(R.id.input);
			text.setText(value);
		}
	}

	public void onClick(View view) {
		finish();
	}

	@Override
	public void finish() {
		Intent data = new Intent();
		// Return some hard-coded values
		data.putExtra("returnKey", "You could be better then you are. ");
		setResult(RESULT_OK, data);
		super.finish();
	}
}
