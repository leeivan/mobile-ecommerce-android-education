package com.google.mcommerce.sample.android.chapter12.touchDrag;

// This file is MainActivity.java
import com.google.mcommerce.sample.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class TouchDragActivity extends Activity {
	public LinearLayout counterLayout;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c12_drag_touch_demo);

        counterLayout = (LinearLayout)findViewById(R.id.counters);
	}
}