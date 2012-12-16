package com.google.mcommerce.sample.android.chapter14.taobao;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.mcommerce.sample.android.R;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.UserGetRequest;
import com.taobao.api.response.UserGetResponse;

public class TaoBaoAPITestActivity extends Activity {
	protected static String url = "http://gw.api.taobao.com/router/rest";// 沙箱环境调用地址
	// 正式环境需要设置为:http://gw.api.taobao.com/router/rest
	private String appkey = "12380481";
	private String appSecret = "7a1a232f80d05ea956e494f9e3100dc1";
	private static String format = "json";
	private static int connectTimeout = 0;
	private static int readTimeout = 0;
	private static String signMethod = "md5";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c14_taobao_api_test_layout);
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret,
				format, connectTimeout, readTimeout, signMethod);
		UserGetRequest req = new UserGetRequest();
		req.setFields("user_id,uid,nick,sex,buyer_credit,seller_credit,location,created,last_visit,birthday,type,status,alipay_no,alipay_account,alipay_account,email,consumer_protection,alipay_bind");
		req.setNick("andyy_tan");
		try {

			UserGetResponse response = client.execute(req, null); //
			// 执行API请求并打印结果
			((TextView) findViewById(R.id.result)).setText(String
					.valueOf(response.getUser().getLocation().getCity()));
			((TextView) findViewById(R.id.nick)).setText(response.getUser()
					.getUid());
		} catch (ApiException e) {
			// deal error
		}
	}
}
