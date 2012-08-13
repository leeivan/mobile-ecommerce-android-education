package com.google.mcommerce.sample.android.chapter03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.mcommerce.sample.android.R;

public class ParseJSONActivity extends Activity {

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		String readTwitterFeed = readTwitterFeed();
		try {
			JSONArray jsonArray = new JSONArray(readTwitterFeed);
			Log.i(ParseJSONActivity.class.getName(), "Number of entries "
					+ jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Log.i(ParseJSONActivity.class.getName(),
						jsonObject.getString("text"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String readTwitterFeed() {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		// HttpGet httpGet = new HttpGet(
		// "http://twitter.com/statuses/user_timeline/vogella.json");
		HttpGet httpGet = new HttpGet(
				"http://gw.api.taobao.com/router/rest?sign=9E66FFFDE58D484F56B22B03802AF48C&timestamp=2012-08-13+11%3A04%3A57&v=2.0&app_key=12380481&method=taobao.user.get&partner_id=top-apitools&format=json&nick=lihaifeng555&fields=user_id,uid,nick,sex,buyer_credit,seller_credit,location,created,last_visit,birthday,type,status,alipay_no,alipay_account,alipay_account,email,consumer_protection,alipay_bind");
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(ParseJSONActivity.class.toString(),
						"Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
}