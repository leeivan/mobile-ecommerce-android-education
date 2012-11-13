package com.google.mcommerce.sample.android.chapter05;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import com.google.mcommerce.sample.android.R;

public class RadioGroupActivity extends Activity {
	protected static final String TAG = "RadioGroupActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c05_radiogroup);

		RadioGroup radGrp = (RadioGroup) findViewById(R.id.radGrp);

		int checkedRadioButtonID = radGrp.getCheckedRadioButtonId();

		radGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup arg0, int id) {
				switch (id) {
				case -1:
					Log.v(TAG, "Choices cleared!");
					break;
				case R.id.chRBtn:
					Log.v(TAG, "Chose Chicken");
					break;
				case R.id.fishRBtn:
					Log.v(TAG, "Chose Fish");
					break;
				case R.id.stkRBtn:
					Log.v(TAG, "Chose Steak");
					break;
				default:
					Log.v(TAG, "Huh?");
					break;
				}
			}
		});
	}
}
