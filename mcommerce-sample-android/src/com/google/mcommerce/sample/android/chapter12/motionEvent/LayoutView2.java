package com.google.mcommerce.sample.android.chapter12.motionEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class LayoutView2 extends LinearLayout {

	private final String TAG = "LayoutView2";

	public LayoutView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.d(TAG, TAG);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, "onInterceptTouchEvent action:ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "onInterceptTouchEvent action:ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAG, "onInterceptTouchEvent action:ACTION_UP");
			break;
		case MotionEvent.ACTION_CANCEL:
			Log.d(TAG, "onInterceptTouchEvent action:ACTION_CANCEL");
			break;
		}
		return false;

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
		return true;
	}

}