package com.pinecone.technology.mcommerce.learning.android.chapter04.button;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.pinecone.technology.mcommerce.learning.android.chapter04.R;

public class ButtonStyleActivity extends Activity implements
		CompoundButton.OnCheckedChangeListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c04_buttons_style);
		Switch s = (Switch) findViewById(R.id.monitored_switch);
		if (s != null) {
			s.setOnCheckedChangeListener(this);
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		Toast.makeText(this,
				"Monitored switch is " + (isChecked ? "on" : "off"),
				Toast.LENGTH_SHORT).show();
	}
}
