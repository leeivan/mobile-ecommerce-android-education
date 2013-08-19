package com.pinecone.technology.mcommerce.learning.android.chapter08.motionEvent;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.pinecone.technology.mcommerce.learning.android.chapter11.R;

public class Test02 extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c08_test);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.c12_test_02, menu);
		return true;
	}

}
