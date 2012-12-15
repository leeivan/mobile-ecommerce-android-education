package com.google.mcommerce.sample.android.chapter06.download;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.mcommerce.sample.android.R;

public class DownloadManagerActivity extends Activity {
	private DownloadManager downmanager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c06_download_manager);

		BroadcastReceiver receiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();
				if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
					long downloadId = intent.getLongExtra(
							DownloadManager.EXTRA_DOWNLOAD_ID, 0);
					Query query = new Query();
					query.setFilterById(downloadId);
					Cursor c = downmanager.query(query);
					if (c.moveToFirst()) {
						int columnIndex = c
								.getColumnIndex(DownloadManager.COLUMN_STATUS);
						if (DownloadManager.STATUS_SUCCESSFUL == c
								.getInt(columnIndex)) {

							ImageView view = (ImageView) findViewById(R.id.imageView1);
							String uriString = c
									.getString(c
											.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
							view.setImageURI(Uri.parse(uriString));
						}
					}
				}
			}
		};

		registerReceiver(receiver, new IntentFilter(
				DownloadManager.ACTION_DOWNLOAD_COMPLETE));
	}

	public void onClick(View view) {
		downmanager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
		Request request = new Request(
				Uri.parse("http://mobile-ecommerce-android-education.googlecode.com/files/android-jellybean.png"));
		request.addRequestHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		request.addRequestHeader("Accept-Language", "en-us,en;q=0.5");
		request.addRequestHeader("Accept-Encoding", "gzip, deflate");
		request.addRequestHeader("Accept-Charset",
				"ISO-8859-1,utf-8;q=0.7,*;q=0.7");
		request.addRequestHeader("Cache-Control", "max-age=0");
		request.setTitle("JellyBean Logo");
		request.setDescription("Testing DownloadManager");
		request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
		downmanager.enqueue(request);

	}

	public void showDownload(View view) {
		Intent i = new Intent();
		i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
		startActivity(i);
	}
}