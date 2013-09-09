package com.pinecone.technology.mcommerce.learning.android.chapter11.motionEvent;

// This file is MainActivity.java
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.pinecone.technology.mcommerce.learning.android.chapter11.R;

public class OnTouchListenerOnTouchEventActivity extends Activity implements
		OnTouchListener, OnClickListener, OnLongClickListener {
	private static final String TAG = "OnTouchListenerOnTouchEventActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.motion_event);

		Button myBtn = (Button) findViewById(R.id.MyBtn);
//		myBtn.setOnTouchListener(this);
		myBtn.setOnClickListener(this);
		myBtn.setOnLongClickListener(this);
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.v(TAG, "-----------------------------");
		Log.v(TAG, "Got view in onTouch");
		Log.v(TAG, MotionEventLogUitl.describeEvent(v, event));
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.v(TAG, "Got view in onClick");
	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		Log.v(TAG, "Got view in onLongClick");
		return false;
	}
}