package com.google.mcommerce.sample.android.chapter07.preferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.mcommerce.sample.android.R;

/**
 * Sub-activity that is executed by the redirection example when input is needed
 * from the user.
 */
public class SharedPreferencesGetter extends Activity {
	private String mTextPref;
	private EditText mText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.c07_redirect_getter);

		// Watch for button clicks.
		Button applyButton = (Button) findViewById(R.id.apply);
		applyButton.setOnClickListener(mApplyListener);

		// The text being set.
		mText = (EditText) findViewById(R.id.editText);

		// Display the stored values, or if not stored initialize with an empty
		// String
		loadPrefs();
	}

	private final void loadPrefs() {
		// Retrieve the current redirect values.
		// NOTE: because this preference is shared between multiple
		// activities, you must be careful about when you read or write
		// it in order to keep from stepping on yourself.
		SharedPreferences preferences = getSharedPreferences("RedirectData", 0);

		mTextPref = preferences.getString("text", null);
		if (mTextPref != null) {
			mText.setText(mTextPref);
		} else {
			mText.setText("");
		}
	}

	private OnClickListener mApplyListener = new OnClickListener() {
		public void onClick(View v) {
			SharedPreferences preferences = getSharedPreferences(
					"RedirectData", 0);
			SharedPreferences.Editor editor = preferences.edit();
			editor.putString("text", mText.getText().toString());

			if (editor.commit()) {
				setResult(RESULT_OK);
			}

			finish();
		}
	};
}
