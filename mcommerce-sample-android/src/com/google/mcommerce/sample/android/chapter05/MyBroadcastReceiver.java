package com.google.mcommerce.sample.android.chapter05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		Toast.makeText(
				context,
				"InentAction is: \n" + action + "\nmsg is:\n"
						+ intent.getStringExtra("msg") + "\nid is:\n"
						+ android.os.Process.myPid(), Toast.LENGTH_SHORT)
				.show();
	}
}
