package com.pinecone.technology.mcommerce.learning.android.chapter04.broadcastReceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.pinecone.technology.mcommerce.learning.android.chapter04.R;

public class MySendBroActivity extends Activity {

	// 定义Action常量
	protected static final String ACTION = "com.pinecone.technology.mcommerce.learning.android.chapter05.REGISTER_ACTION";
	private Button btnBroadcast;
	private Button registerReceiver;
	private Button unregisterReceiver;
	private TestReceiver receiver;
	private Button btnBroadcastSticky;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c05_register);
		btnBroadcast = (Button) findViewById(R.id.btnBroadcast);
		// 创建事件监听器
		btnBroadcast.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(ACTION);
				intent.putExtra("message", "Hello mobile world");
				sendBroadcast(intent);
			}
		});
		btnBroadcastSticky = (Button) findViewById(R.id.btnBroadcast_sticky);
		// 创建事件监听器
		btnBroadcastSticky.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(ACTION);
				intent.putExtra("message", "Hello mobile world");
				sendStickyBroadcast(intent);
			}
		});
		registerReceiver = (Button) findViewById(R.id.btnregisterReceiver);
		// 创建事件监听器
		registerReceiver.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				receiver = new TestReceiver();
				IntentFilter filter = new IntentFilter();
				filter.addAction(ACTION);
				// 动态注册BroadcastReceiver
				registerReceiver(receiver, filter);
			}
		});
		unregisterReceiver = (Button) findViewById(R.id.btnunregisterReceiver);
		// 创建事件监听器
		unregisterReceiver.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 注销BroadcastReceiver
				unregisterReceiver(receiver);
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unregisterReceiver(receiver);
	}
}
