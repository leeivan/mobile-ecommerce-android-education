package com.google.mcommerce.sample.android.chapter04;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.mcommerce.sample.android.R;

public class CallIntentsActivity extends Activity {
	private Spinner spinner;
	private RadioGroup mRadioGroup;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c05_implicit_intent_layout);
		mRadioGroup = (RadioGroup) findViewById(R.id.action);
	}

	public void onClick(View view) {
		int position = mRadioGroup.getCheckedRadioButtonId();
		Intent intent = null;
		switch (position) {
		case R.id.RadioButton0:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://open.taobo.com"));
			break;
		case R.id.RadioButton1:
			intent = new Intent(Intent.ACTION_CALL,
					Uri.parse("tel:(+010)1234578"));
			break;
		case R.id.RadioButton2:
			intent = new Intent(Intent.ACTION_DIAL,
					Uri.parse("tel:(+010)1234578"));
			startActivity(intent);
			break;
		case R.id.RadioButton3:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("geo:50.123,7.1434"));
			break;
		case R.id.RadioButton4:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("geo:0,0?q=query"));
			break;
		case R.id.RadioButton5:
			intent = new Intent("android.media.action.IMAGE_CAPTURE");
			break;
		case R.id.RadioButton6:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("content://contacts/people/"));
			break;
		case R.id.RadioButton7:
			intent = new Intent(Intent.ACTION_EDIT,
					Uri.parse("content://contacts/people/1"));
			break;

		}
		if (intent != null) {
			startActivity(intent);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK && requestCode == 0) {
			String result = data.toURI();
			Toast.makeText(this, result, Toast.LENGTH_LONG);
		}
	}
}