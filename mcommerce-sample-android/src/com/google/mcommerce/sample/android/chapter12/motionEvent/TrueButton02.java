package com.google.mcommerce.sample.android.chapter12.motionEvent;

// This file is TrueButton.java

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

public class TrueButton02 extends Button {

	public TrueButton02(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		String myTag = this.getTag().toString();
		Log.v(myTag, "-----------------------------------");
		Log.v(myTag, TouchDemo02Activity.describeEvent(this, event));
		Log.v(myTag,
				"super onTouchEvent() returns " + super.onTouchEvent(event));
		Log.v(myTag, "and I'm returning true");
		return (true);
	}
}
