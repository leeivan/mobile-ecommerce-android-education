package com.google.mcommerce.sample.android.chapter04.button;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.mcommerce.sample.android.R;

public class CheckBoxActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c04_checkbox);
	}

	public void onCheckboxClicked(View v) {
		// Perform action on clicks, depending on whether it's now checked
		if (((CheckBox) v).isChecked()) {
			Toast.makeText(CheckBoxActivity.this, "选中", Toast.LENGTH_SHORT)
					.show();
		} else {
			Toast.makeText(CheckBoxActivity.this, "没选中", Toast.LENGTH_SHORT)
					.show();
		}
	}
}
