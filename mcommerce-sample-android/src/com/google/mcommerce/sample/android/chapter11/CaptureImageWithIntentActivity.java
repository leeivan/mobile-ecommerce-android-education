package com.google.mcommerce.sample.android.chapter11;

import java.io.File;

import com.google.mcommerce.sample.android.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;

public class CaptureImageWithIntentActivity extends Activity {
    
	private static final String TAG = "CameraWithIntent";
	Uri myPicture = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c11_capture_image_with_intent_layout);
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void captureImage(View view)
    {
    	ContentValues values = new ContentValues();
        values.put(Media.TITLE, "My demo image");
        values.put(Media.DESCRIPTION, "Image Captured by Camera via an Intent");

        myPicture = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, myPicture);

        startActivityForResult(i, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0 && resultCode==Activity.RESULT_OK)
        {
             Intent inn = new Intent(Intent.ACTION_VIEW);
             Log.v(TAG, "myPicture is " + myPicture.toString());
             inn.setData(myPicture);
             startActivity(inn);
        }
    }
}
