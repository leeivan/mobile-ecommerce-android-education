package com.google.mcommerce.sample.android.chapter14.weibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import com.google.mcommerce.sample.android.R;
import com.tencent.weibo.api.UserAPI;
import com.tencent.weibo.constants.OAuthConstants;
import com.tencent.weibo.oauthv2.OAuthV2;
import com.tencent.weibo.webview.OAuthV2AuthorizeWebView;

public class TecentWeiboActivity extends Activity {
	private OAuthV2 oAuth;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c15_weibo_main);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());
		oAuth = new OAuthV2("http://www.qq.com");
		oAuth.setClientId("801279287");
		oAuth.setClientSecret("99224bf0ece4db768c21b70875da5dd8");

		Intent intent = new Intent(TecentWeiboActivity.this,
				OAuthV2AuthorizeWebView.class);
		intent.putExtra("oauth", oAuth);
		startActivityForResult(intent, 1);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if (resultCode == OAuthV2AuthorizeWebView.RESULT_CODE) {
				oAuth = (OAuthV2) data.getExtras().getSerializable("oauth");

				// 调用API获取用户信息
				UserAPI userAPI = new UserAPI(OAuthConstants.OAUTH_VERSION_2_A);
				try {
					String response = userAPI.info(oAuth, "json");// 获取用户信息
					((TextView) findViewById(R.id.textViewId)).setText(response
							+ "\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
				userAPI.shutdownConnection();

			}
		}
	}
}