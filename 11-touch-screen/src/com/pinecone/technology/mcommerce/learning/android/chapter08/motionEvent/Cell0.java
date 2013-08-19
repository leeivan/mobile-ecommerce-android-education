package com.pinecone.technology.mcommerce.learning.android.chapter08.motionEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class Cell0 extends LinearLayout {
	private static final String TAG = "Cell0";

	public Cell0(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, "onInterceptTouchEvent-------->ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "onInterceptTouchEvent-------->ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAG, "onInterceptTouchEvent-------->ACTION_UP");
			break;
		case MotionEvent.ACTION_CANCEL:
			Log.d(TAG, "onInterceptTouchEvent-------->ACTION_CANCEL");
			break;
		}
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, "onTouchEvent-------->ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "onTouchEvent-------->ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAG, "onTouchEvent-------->ACTION_UP");
			break;
		case MotionEvent.ACTION_CANCEL:
			Log.d(TAG, "onTouchEvent-------->ACTION_CANCEL");
			break;
		}
		return true;
	}
}
