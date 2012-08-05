package com.google.mcommerce.sample.android.chapter07;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.mcommerce.sample.android.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowProductImageActivity extends Activity {

	private ImageView productImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c07_show_product_image);
		productImage = (ImageView) findViewById(R.id.product_imageView);
		new LoadImageTask().execute(0);
	}

	class LoadImageTask extends AsyncTask<Integer, Void, Bitmap> {

		protected Bitmap doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			Bitmap bm = null;
			try {
				URL u = new URL("http://img04.taobaocdn.com/bao/uploaded/i4/T1O9aOXethXXXPxkLa_091741.jpg");
				URLConnection con = u.openConnection();
				InputStream input = con.getInputStream();
				bm = BitmapFactory.decodeStream(input);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bm;

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (null != result) {
				productImage.setImageBitmap(result);
			} else {
				productImage.setImageResource(R.drawable.icon);
			}
		}

	}
}