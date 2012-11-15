package com.google.mcommerce.sample.android.chapter04;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class LifecycleActivitySecond extends Activity {

	private static final String TAG = "LifecycleActivityFirst";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {
		Log.d(TAG, "onSart");
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onRestart() {
		Log.d(TAG, "onRestart");
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "onResume");
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d(TAG, "onSaveInstanceState");
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "onPause");
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "onStop");
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "onDestroy");
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
