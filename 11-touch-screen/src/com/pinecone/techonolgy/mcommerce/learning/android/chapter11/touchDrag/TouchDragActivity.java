package com.pinecone.techonolgy.mcommerce.learning.android.chapter11.touchDrag;

// This file is MainActivity.java
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.pinecone.technology.mcommerce.learning.android.chapter11.R;

public class TouchDragActivity extends Activity {
	public LinearLayout counterLayout;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drag_touch_demo);

		counterLayout = (LinearLayout) findViewById(R.id.counters);
	}
}