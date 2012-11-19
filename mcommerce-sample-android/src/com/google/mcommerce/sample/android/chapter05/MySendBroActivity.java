package com.google.mcommerce.sample.android.chapter05;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.mcommerce.sample.android.R;

public class MySendBroActivity extends Activity {
	/** Called when the activity is first created. */
	private Button btn;
	private TextView tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c09_simple_broadcast_receiver);
		btn = (Button) findViewById(R.id.broadcast_receiver_button);
		tv = (TextView) findViewById(R.id.broadcast_receriver_textView1);
		tv.setText("id is:" + android.os.Process.myPid());
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("com.google.mcommerce.sample.android.chapter09.mybroadcast");
				intent.putExtra("msg", "Message");
				Toast.makeText(MySendBroActivity.this, "My intent  !",
						Toast.LENGTH_SHORT).show();
				sendBroadcast(intent);
			}
		});
	}
}
