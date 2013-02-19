package com.google.mcommerce.sample.android.chapter08.motionEvent;

// This file is MainActivity.java
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.mcommerce.sample.android.R;

public class OnTouchListenerOnTouchEventActivity extends Activity implements OnTouchListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c08_motion_event_04_layout);

		RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.layout1);
		layout1.setOnTouchListener(this);
		Button trueBtn1 = (Button) findViewById(R.id.trueBtn1);
		trueBtn1.setOnTouchListener(this);
		Button falseBtn1 = (Button) findViewById(R.id.falseBtn1);
		falseBtn1.setOnTouchListener(this);

		RelativeLayout layout2 = (RelativeLayout) findViewById(R.id.layout2);
		layout2.setOnTouchListener(this);
		Button trueBtn2 = (Button) findViewById(R.id.trueBtn2);
		trueBtn2.setOnTouchListener(this);
		Button falseBtn2 = (Button) findViewById(R.id.falseBtn2);
		falseBtn2.setOnTouchListener(this);
	}

	public boolean onTouch(View v, MotionEvent event) {
		String myTag = v.getTag().toString();
		Log.v(myTag, "-----------------------------");
		Log.v(myTag, "Got view " + myTag + " in onTouch");
		Log.v(myTag, MotionEventLogUitl.describeEvent(v, event));
		if ("true".equals(myTag.substring(0, 4))) {
			Log.v(myTag, "and I'm returning true");
			return true;
		} else {
			Log.v(myTag, "and I'm returning false");
			return false;
		}
	}
}