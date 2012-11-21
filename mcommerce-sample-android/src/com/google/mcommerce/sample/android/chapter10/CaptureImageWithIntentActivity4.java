package com.google.mcommerce.sample.android.chapter10;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.google.mcommerce.sample.android.R;

public class CaptureImageWithIntentActivity4 extends Activity {

	private static final String TAG = "CameraWithIntent";
	Uri myPicture = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c10_capture_image_with_intent_layout);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}

	public void captureImage(View view) {
		Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(i, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
			Bitmap myBitmap = (Bitmap) data.getExtras().get("data");
		}
	}
}
