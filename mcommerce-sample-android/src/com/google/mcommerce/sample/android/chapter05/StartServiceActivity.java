package com.google.mcommerce.sample.android.chapter05;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.mcommerce.sample.android.R;

public class StartServiceActivity extends Activity {
	private Button button1;
	private Button button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c09_started_service_layout);
		System.out.println("hooooooo");

		button1 = (Button) findViewById(R.id.service_button1);
		button2 = (Button) findViewById(R.id.service_button2);
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
	}
}
