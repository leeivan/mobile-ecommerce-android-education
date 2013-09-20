package com.pinecone.technology.mcommerce.learning.android.chapter11.trackhistory;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLayoutView extends LinearLayout {
	private final String TAG = "MyLayoutView";

	public MyLayoutView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.d(TAG, TAG);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG,"----------------------------");
			Log.d(TAG, printSamples(ev));
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_CANCEL:
			break;
		}
		return true;
	}

	private String printSamples(MotionEvent ev) {
		StringBuilder result = new StringBuilder(300);
		final int historySize = ev.getHistorySize();
		final int pointerCount = ev.getPointerCount();
		result.append("HistorySize:").append(historySize).append("\n");
		for (int h = 0; h < historySize; h++) {
			result.append("History MotionEvent time:")
					.append(ev.getHistoricalEventTime(h)).append("\n");
			for (int p = 0; p < pointerCount; p++) {
				result.append("Histroy Pointer:").append(ev.getPointerId(p))
						.append("(").append(ev.getHistoricalX(p, h))
						.append(",").append(ev.getHistoricalY(p, h))
						.append(")\n");
			}
		}
		result.append("Current MotionEvent time:").append(ev.getEventTime()).append("\n");
		for (int p = 0; p < pointerCount; p++) {
			result.append("Current pointer:").append(ev.getPointerId(p)).append("(")
					.append(ev.getX(p)).append(",").append(ev.getY(p))
					.append(")\n");
		}
		return result.toString();
	}
}