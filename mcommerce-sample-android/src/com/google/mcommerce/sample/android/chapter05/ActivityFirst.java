package com.google.mcommerce.sample.android.chapter05;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.mcommerce.sample.android.R;

public class ActivityFirst extends Activity {
	private static final int REQUEST_CODE = 10;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c05_activity_first_layout);
	}

	public void onClick(View view) {
		// 实例化Intent  
		Intent i = new Intent();  
		//设置Intent的Action属性  
		i.setAction("com.android.activity.MY_ACTION");  
		i.putExtra("Value1", "This value one for ActivityTwo ");
		i.putExtra("Value2", "This value two ActivityTwo");
		// Set the request code to any code you like, you can identify the
		// callback via this code
		startActivityForResult(i, REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
			if (data.hasExtra("returnKey1")) {
				Toast.makeText(this, data.getExtras().getString("returnKey1"),
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
