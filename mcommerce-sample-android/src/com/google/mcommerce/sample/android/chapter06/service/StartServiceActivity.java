package com.google.mcommerce.sample.android.chapter06.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.mcommerce.sample.android.R;

public class StartServiceActivity extends Activity {
	private static final String TAG = "StartServiceActivity";
	private int counter = 1;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c06_started_service_layout);
		System.out.println("hooooooo");

		button1 = (Button) findViewById(R.id.service_button1);
		button2 = (Button) findViewById(R.id.service_button2);
		button3 = (Button) findViewById(R.id.service_button3);
		button4 = (Button) findViewById(R.id.service_button4);
		/*
		 * 增加事件响应
		 */
		button1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(StartServiceActivity.this,
						HelloIntentService.class);
				startService(intent);
			}
		});
		button2.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(StartServiceActivity.this,
						HelloService.class);
				startService(intent);
			}
		});
		button3.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.v(TAG, "Starting service... counter = " + counter);
				Intent intent = new Intent(StartServiceActivity.this,
						BackgroundService.class);
				intent.putExtra("counter", counter++);
				startService(intent);
			}
		});
		button4.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				stopService();
			}
		});
	}

	@Override
	public void onDestroy() {
		stopService();
		super.onDestroy();
	}

	private void stopService() {
		Log.v(TAG, "Stopping service...");
		if (stopService(new Intent(StartServiceActivity.this,
				BackgroundService.class)))
			Log.v(TAG, "stopService was successful");
		else
			Log.v(TAG, "stopService was unsuccessful");
	}
}
