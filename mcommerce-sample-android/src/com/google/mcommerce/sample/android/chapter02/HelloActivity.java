package com.google.mcommerce.sample.android.chapter02;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author ivan
 * 
 */
public class HelloActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		tv.setText("您好，移动世界！");
		setContentView(tv);
	}

	public static void main(String[] paramArrayOfString) {
		System.out.println("Hello world");
	}

}
