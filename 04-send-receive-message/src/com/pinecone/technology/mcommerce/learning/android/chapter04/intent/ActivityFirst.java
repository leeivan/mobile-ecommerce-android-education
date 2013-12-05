package com.pinecone.technology.mcommerce.learning.android.chapter04.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pinecone.technology.mcommerce.learning.android.chapter04.R;

public class ActivityFirst extends Activity {
	private static final int REQUEST_CODE_1 = 1;
	private static final int REQUEST_CODE_2 = 2;
	private static final String TAG = "ActivityFirst";
	private Intent i;
	private EditText edittext01;
	private EditText edittext02;
	private Button button01;
	private Button button02;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c05_activity_first_layout);
		i = new Intent();
		// 设置Intent的Action属性
		// i.setClass(this, ActivitySecond.class);
		// i.setComponent(new ComponentName(this, ActivitySecond.class));
		i.setComponent(new ComponentName(
				"com.pinecone.technology.mcommerce.learning.android.chapter05",
				"com.pinecone.technology.mcommerce.learning.android.chapter05.intent.ActivitySecond"));
		edittext01 = (EditText) findViewById(R.id.edittext01);
		edittext02 = (EditText) findViewById(R.id.edittext02);
		button01 = (Button) findViewById(R.id.button01);
		button01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i.putExtra("Value", edittext01.getText().toString());
				startActivityForResult(i, REQUEST_CODE_1);
			}
		});
		button02 = (Button) findViewById(R.id.button02);
		button02.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i.putExtra("Value", edittext02.getText().toString());
				startActivityForResult(i, REQUEST_CODE_2);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i(TAG, "requestCode=" + requestCode);
		Log.i(TAG, "resultCode=" + resultCode);
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case REQUEST_CODE_1:
				if (data.hasExtra("returnKey")) {
					Toast.makeText(
							this,
							"通过第一个按钮返回的结果："
									+ data.getExtras().getString("returnKey"),
							Toast.LENGTH_SHORT).show();
					break;
				}
			case REQUEST_CODE_2:
				if (data.hasExtra("returnKey")) {
					Toast.makeText(
							this,
							"通过第二个按钮返回的结果："
									+ data.getExtras().getString("returnKey"),
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}
	}
}
