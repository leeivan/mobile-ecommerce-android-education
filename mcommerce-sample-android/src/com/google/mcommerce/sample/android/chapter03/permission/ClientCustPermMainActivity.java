package com.google.mcommerce.sample.android.chapter03.permission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.mcommerce.sample.android.R;

public class ClientCustPermMainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void doClick(View view) {
		Intent intent = new Intent();
		intent.setClassName("com.cust.perm", "com.cust.perm.PrivActivity");
		startActivity(intent);
	}
}
