package com.pinecone.technology.mcommerce.learning.android.chapter05.intent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.pinecone.technology.mcommerce.learning.android.chapter05.R;

public class ImagePickActivity extends Activity {
  private static final int REQUEST_CODE = 1;
  private Bitmap bitmap;
  private ImageView imageView;

  
/** Called when the activity is first created. */

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.c05_pick_image);
    imageView = (ImageView) findViewById(R.id.result);
  }

  public void pickImage(View View) {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    intent.addCategory(Intent.CATEGORY_OPENABLE);
    startActivityForResult(intent, REQUEST_CODE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    InputStream stream = null;
    if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
      try {
        // recyle unused bitmaps
        if (bitmap != null) {
          bitmap.recycle();
        }
        stream = getContentResolver().openInputStream(data.getData());
        bitmap = BitmapFactory.decodeStream(stream);

        imageView.setImageBitmap(bitmap);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } finally {
        if (stream != null)
          try {
            stream.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
      }
  }
} 