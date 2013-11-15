package com.pinecone.technology.mcommerce.learning.android.chapter06;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ThreadActivity extends Activity {
	private static final String TAG = "ThreadActivity";
	private Button button1;
	private Button button2;
	private TextView textView1;
	private Button button3;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c06_thread);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateUI("开始一个耗时操作");
				updateUI(doLongOperation());
			}
		});

		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateUI("开始一个耗时操作");
				(new Thread(new Runnable() {

					@Override
					public void run() {
						updateUI(doLongOperation());
					}
				})).start();
			}
		});

		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateUI("开始一个耗时操作");
				(new Thread(new Runnable() {

					private String result;

					@Override
					public void run() {
						// TODO Auto-generated method stub
						result = doLongOperation();
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								updateUI(result);
							}

						});
					}

				})).start();
			}
		});
	}

	public void updateUI(String str) {
		textView = (TextView) findViewById(R.id.textView1);
		textView.setText(str);
	}

	public String doLongOperation() {

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return "结束操作";
	}

	private void log(String msg) {
		Log.d(TAG, msg);
	}
}
