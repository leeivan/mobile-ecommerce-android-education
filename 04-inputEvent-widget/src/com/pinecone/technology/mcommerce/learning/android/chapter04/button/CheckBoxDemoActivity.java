package com.pinecone.technology.mcommerce.learning.android.chapter04.button;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.pinecone.technology.mcommerce.learning.android.chapter04.R;

public class CheckBoxDemoActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c04_checkbox_01);

		// No handling in here for the Chicken checkbox

		CheckBox fishCB = (CheckBox) findViewById(R.id.fishCB);

		if (fishCB.isChecked())
			fishCB.toggle(); // flips the checkbox to unchecked if it was
								// checked

		fishCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
				Toast.makeText(
						CheckBoxDemoActivity.this,
						"The fish checkbox is now "
								+ (isChecked ? "checked" : "not checked"),
						Toast.LENGTH_SHORT).show();

			}
		});
	}

	public void doClick(View view) {
		Toast.makeText(
				this,
				"The steak checkbox is now "
						+ (((CheckBox) view).isChecked() ? "checked"
								: "not checked"), Toast.LENGTH_SHORT).show();
	}
}
