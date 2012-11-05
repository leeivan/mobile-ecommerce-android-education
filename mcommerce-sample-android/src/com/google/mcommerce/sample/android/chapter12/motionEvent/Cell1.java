package com.google.mcommerce.sample.android.chapter12.motionEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;


public class Cell1 extends LinearLayout {
    private static final String TAG = "Cell1";

    public Cell1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:
            Log.d(TAG, "onInterceptTouchEvent-------->ACTION_DOWN");
            return false;

        case MotionEvent.ACTION_MOVE:
            Log.d(TAG, "onInterceptTouchEvent-------->ACTION_MOVE");
            return false;

        case MotionEvent.ACTION_UP:
            Log.d(TAG, "onInterceptTouchEvent-------->ACTION_UP");
            return false;

        case MotionEvent.ACTION_CANCEL:
            Log.d(TAG, "onInterceptTouchEvent-------->ACTION_CANCEL");
            return false;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            Log.d(TAG, "onTouchEvent-------->ACTION_DOWN");
            return true;

        case MotionEvent.ACTION_MOVE:
            Log.d(TAG, "onTouchEvent-------->ACTION_MOVE");
            return false;

        case MotionEvent.ACTION_UP:
            Log.d(TAG, "onTouchEvent-------->ACTION_UP");
            return false;

        case MotionEvent.ACTION_CANCEL:
            Log.d(TAG, "onTouchEvent-------->ACTION_CANCEL");
            return false;
        }
        return true;
    }
}
