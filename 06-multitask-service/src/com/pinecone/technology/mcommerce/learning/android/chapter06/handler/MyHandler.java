package com.pinecone.technology.mcommerce.learning.android.chapter06.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.pinecone.technology.mcommerce.learning.android.chapter06.R;

public class MyHandler extends Activity {
	static final String TAG = "Handler";
	static final int HANDLER_TEST = 1;
	Handler h = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case HANDLER_TEST:
				Log.d(TAG, "The handler thread id = "
						+ Thread.currentThread().getId() + " ");
				break;
			}
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "The main thread id = " + Thread.currentThread().getId()
				+ " ");
		new myThread().start();
		setContentView(R.layout.c06_main);
	}

	class myThread extends Thread {
		public void run() {
			Message msg = new Message();
			msg.what = HANDLER_TEST;
			h.sendMessage(msg);
			Log.d(TAG, "The worker thread id = "
					+ Thread.currentThread().getId() + " ");
		}
	}
}
