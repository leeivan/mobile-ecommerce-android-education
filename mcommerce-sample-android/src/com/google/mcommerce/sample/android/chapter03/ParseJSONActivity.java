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
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.mcommerce.sample.android.R;

public class ParseJSONActivity extends Activity {

	/** Called when the activity is first created. */
	private static final String TAG = "ParseJSONActivity";
	private TextView textViewSingleCity;
	private TextView textViewSingleNick;
	private TextView textViewMultiCity;
	private TextView textViewMultiNick;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c03_parse_json_xml_layout);
		textViewSingleCity = (TextView) findViewById(R.id.c03_textView1);
		textViewSingleNick = (TextView) findViewById(R.id.c03_textView4);
		textViewMultiCity = (TextView) findViewById(R.id.c03_textView6);
		textViewMultiNick = (TextView) findViewById(R.id.c03_textView8);
		// 返回单个用户
		String userUrl = "http://gw.api.taobao.com/router/rest?sign=EF87CC42B707AFF1234FF8782113CDFB&timestamp=2012-08-13+20%3A50%3A28&v=2.0&app_key=12129701&method=taobao.user.get&partner_id=top-apitools&format=json&nick=lihaifeng555&fields=user_id,uid,nick,sex,buyer_credit,seller_credit,location,created,last_visit,birthday,type,status,alipay_no,alipay_account,alipay_account,email,consumer_protection,alipay_bind";
		String userString = getUsers(userUrl);
		Log.i("ParseJSONActivity", userString);
		// 返回多个用户
		parseJson(userString);
		String usersUrl = "http://gw.api.taobao.com/router/rest?sign=BDD6F4A3DDBF2F42AB67530CBD6ACBCB&timestamp=2012-08-21+23%3A06%3A04&v=2.0&app_key=12129701&method=taobao.users.get&partner_id=top-apitools&format=json&nicks=lihaifeng555,andyy_tan&fields=user_id,nick,sex,buyer_credit,seller_credit,location,created,last_visit";
		String usersString = getUsers(usersUrl);
		Log.i("ParseJSONActivity", usersString);
		parseJsonMulti(usersString);
	}

	public String getUsers(String userUrl) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(userUrl);
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
				Log.e(TAG, "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	// 普通Json数据解析

	private void parseJson(String strResult) {
		try {
			// 获取用户对象
			JSONObject jsonUserObj = new JSONObject(strResult).getJSONObject(
					"user_get_response").getJSONObject("user");
			String name = jsonUserObj.getString("nick");
			JSONObject jsonLocationObj = jsonUserObj.getJSONObject("location");
			String city = jsonLocationObj.getString("city");
			textViewSingleCity.setText(city);
			textViewSingleNick.setText(name);
		} catch (JSONException e) {
			System.out.println("Json parse error");
			e.printStackTrace();
		}
	}

	// 解析多个数据的Json
	private void parseJsonMulti(String strResult) {
		String nick = "";
		String city = "";
		try {
			JSONArray jsonObjs = new JSONObject(strResult)
					.getJSONObject("users_get_response").getJSONObject("users")
					.getJSONArray("user");
			for (int i = 0; i < jsonObjs.length(); i++) {
				JSONObject jsonUserObj = ((JSONObject) jsonObjs.opt(i));
				nick = jsonUserObj.getString("nick") + ";" + nick;
				JSONObject jsonLocationObj = jsonUserObj
						.getJSONObject("location");
				city = jsonLocationObj.getString("city") + ";" + city;
			}
			textViewMultiCity.setText(city);
			textViewMultiNick.setText(nick);
		} catch (JSONException e) {
			System.out.println("Jsons parse error !");
			e.printStackTrace();
		}
	}
}