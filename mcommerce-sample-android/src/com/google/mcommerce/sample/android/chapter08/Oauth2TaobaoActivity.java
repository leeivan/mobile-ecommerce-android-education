package com.google.mcommerce.sample.android.chapter08;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.mcommerce.sample.android.AppConstants;
import com.google.mcommerce.sample.android.R;

public class Oauth2TaobaoActivity extends Activity {
	private static final String TAG = "Oauth2TaobaoActivity";
	private WebView mWebView;
	private Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c08_login_oauth_view);
		mWebView = (WebView) findViewById(R.id.web_view);
		mWebView.getSettings().setJavaScriptEnabled(true);
		// 页面缩放设置
		mWebView.getSettings().setBuiltInZoomControls(true);
		// 使页面获得焦点
		mWebView.requestFocus();
		String authorizationUrl = "https://oauth.taobao.com/authorize?response_type=token&"
				+ "client_id="
				+ AppConstants.APP_KEY
				+ "&scope=item&state=1212" + "&view=wap";
		mWebView.loadUrl(authorizationUrl);
		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public void onPageFinished(WebView view, String url) {
				log(url);
				if (url.startsWith(AppConstants.OAUTH_CALLBACK_URL)) {
					String[] params = Uri.parse(url).getFragment().split("\\&");
					for (String param : params) {
						String[] parts = param.split("\\=");
						if (parts[0].equals("access_token")) {
							log(parts[0]);
							i = new Intent(Oauth2TaobaoActivity.this,
									UserInfoActivity.class);
							i.putExtra("accessToken", parts[1]);
							startActivity(i);
						}
					}
				}
			}
		});

	}

	private void log(String msg) {
		Log.d(TAG, msg);
	}
}
