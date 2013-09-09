package com.pinecone.technology.mcommerce.learning.android.chapter11.motionEvent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.pinecone.technology.mcommerce.learning.android.chapter11.R;

public class InterceptTouchActivity extends Activity {
	static final String TAG = "InterceptTouchActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intercept_touchevent);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, "dispatchTouchEvent action:ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "dispatchTouchEvent action:ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAG, "dispatchTouchEvent action:ACTION_UP");
			break;
		case MotionEvent.ACTION_CANCEL:
			Log.d(TAG, "dispatchTouchEvent action:ACTION_CANCEL");
			break;
		}
		// Log.d(TAG, "dispatchTouchEvent: " + super.dispatchTouchEvent(ev));
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, "onTouchEvent action:ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "onTouchEvent action:ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAG, "onTouchEvent action:ACTION_UP");
			break;
		case MotionEvent.ACTION_CANCEL:
			Log.d(TAG, "onTouchEvent action:ACTION_CANCEL");
			break;
		}
		return false;
	}
}
