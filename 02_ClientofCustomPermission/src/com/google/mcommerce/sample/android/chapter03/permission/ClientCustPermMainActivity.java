package com.google.mcommerce.sample.android.chapter03.permission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClientCustPermMainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void doClick(View view) {
		Intent intent = new Intent();
		intent.setClassName("com.google.mcommerce.sample.android",
				"com.google.mcommerce.sample.android.chapter03.permission.PrivActivity");
		startActivity(intent);
	}
}
