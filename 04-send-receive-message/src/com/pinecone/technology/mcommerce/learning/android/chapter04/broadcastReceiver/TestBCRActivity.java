package com.pinecone.technology.mcommerce.learning.android.chapter04.broadcastReceiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.pinecone.technology.mcommerce.learning.android.chapter04.R;

public class TestBCRActivity extends Activity {
	public static final String tag = "TestBCRActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c05_broadcast_receiver_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater(); // from activity
		inflater.inflate(R.menu.c05_broadcast_receiver_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		appendMenuItemText(item);
		if (item.getItemId() == R.id.menu_clear) {
			this.emptyText();
			return true;
		}
		if (item.getItemId() == R.id.menu_send_broadcast) {
			this.testSendBroadcast();
			return true;
		}
		return true;
	}

	private TextView getTextView() {
		return (TextView) this.findViewById(R.id.text1);
	}

	private void appendMenuItemText(MenuItem menuItem) {
		String title = menuItem.getTitle().toString();
		TextView tv = getTextView();
		tv.setText(tv.getText() + "\n" + title);
	}

	private void emptyText() {
		TextView tv = getTextView();
		tv.setText("");
	}

	private void testSendBroadcast() {
		// Print out what your running thread id is
		Utils.logThreadSignature(tag);

		// Create an intent with an action
		Intent broadcastIntent = new Intent(
				"com.pinecone.technology.mcommerce.learning.android.chapter05.RECEIVER_ACTION");
		// load up the intent with a message
		// you want to broadcast
		broadcastIntent.putExtra("message", "Hello mobile world");

		// send out the broadcast
		// there may be multiple receivers receiving it
//		this.sendBroadcast(broadcastIntent);
		this.sendOrderedBroadcast(broadcastIntent,null);

		// Log a message after sending the broadcast
		// This message should appear first in the log file
		// before the log messages from the broadcast
		// because they all run on the same thread
		Log.d(tag, "after send broadcast from main menu");
	}
}