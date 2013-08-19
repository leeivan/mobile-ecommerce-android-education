package com.pinecone.technology.mcommerce.learning.android.chapter08.motionEvent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.pinecone.technology.mcommerce.learning.android.chapter11.R;

public class InterceptTouchStudyActivity extends Activity {
	static final String TAG = "ITSActivity";
	TextView tv;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c08_layers_touch_pass_test);
	}

}
