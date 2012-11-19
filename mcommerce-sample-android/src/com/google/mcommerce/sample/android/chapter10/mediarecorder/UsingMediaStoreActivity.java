package com.google.mcommerce.sample.android.chapter10.mediarecorder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.mcommerce.sample.android.R;

public class UsingMediaStoreActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.c11_media_using_media_store_layout);

		Button btn = (Button) findViewById(R.id.recordBtn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				startRecording();

			}
		});
	}

	public void startRecording() {
		Intent intt = new Intent("android.provider.MediaStore.RECORD_SOUND");
		startActivityForResult(intt, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
		case 0:
			if (resultCode == RESULT_OK) {
				Uri recordedAudioPath = data.getData();
				Log.v("Demo", recordedAudioPath.toString());
			}
		}
	}
}
