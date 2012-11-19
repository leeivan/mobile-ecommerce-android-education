package com.google.mcommerce.sample.android.chapter12;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.mcommerce.sample.android.AppConstants;
import com.google.mcommerce.sample.android.R;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.UserGetRequest;
import com.taobao.api.response.UserGetResponse;

public class UserInfoActivity extends Activity {
	private static final String TAG = "UserInfoActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c08_user_info_layout);
		TaobaoClient client = new DefaultTaobaoClient(AppConstants.URL,
				AppConstants.APP_KEY, AppConstants.SECRET);
		UserGetRequest req = new UserGetRequest();
		req.setFields("user_id,uid,nick,sex,buyer_credit,seller_credit,location,created,last_visit,birthday,type,status,alipay_no,alipay_account,alipay_account,email,consumer_protection,alipay_bind");
		try {
			Bundle extras = getIntent().getExtras();
			String accessToken = extras.getString("accessToken");
			log(accessToken);
			UserGetResponse response = client.execute(req, accessToken); //
			// 执行API请求并打印结果
			((TextView) findViewById(R.id.user_result)).setText(String
					.valueOf(response.getUser().getLocation().getCity()));
			((TextView) findViewById(R.id.user_nick)).setText(response
					.getUser().getNick());
		} catch (ApiException e) {
			// deal error
		}

	}

	private void log(String msg) {
		Log.d(TAG, msg);
	}
}
