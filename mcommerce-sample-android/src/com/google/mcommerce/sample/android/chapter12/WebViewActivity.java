package com.google.mcommerce.sample.android.chapter12;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.mcommerce.sample.android.R;

public class WebViewActivity extends Activity {
	private static final String TAG = "JavaScriptBrowserActivity";
	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c08_javascript_browser);
		mWebView = (WebView) findViewById(R.id.js_webview);
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		mWebView.setWebViewClient(new MyWebViewClient());
		mWebView.addJavascriptInterface(new JavaScriptInterface(this),
				"Android");
		mWebView.loadUrl("file:///android_asset/index.html");
	}

	private class JavaScriptInterface {
		Context mContext;

		/** Instantiate the interface and set the context */
		JavaScriptInterface(Context c) {
			mContext = c;
		}

		/** Show a toast from the web page */
		public void showToast(String toast) {
			Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
		}
	}

	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			log(url);
			if (Uri.parse(url).getScheme().equals("tel")) {
				String page = "<html><body><a href=#>" + url
						+ "</a></body></html>";
				view.loadData(page, "text/html", "UTF-8");
				return true;
			}
			return false;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Check if the key event was the Back button and if there's history
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		}
		// If it wasn't the Back key or there's no web page history, bubble up
		// to the default
		// system behavior (probably exit the activity)
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

	private void log(String msg) {
		Log.d(TAG, msg);
	}
}
