package com.google.mcommerce.sample.android.chapter03;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.mcommerce.sample.android.R;
import com.google.mcommerce.sample.android.chapter03.util.User;
import com.google.mcommerce.sample.android.chapter03.util.UserUtil;

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
		setContentView(R.layout.c03_parse_json_layout);
		textViewSingleCity = (TextView) findViewById(R.id.c03_textView1);
		textViewSingleNick = (TextView) findViewById(R.id.c03_textView4);
		textViewMultiCity = (TextView) findViewById(R.id.c03_textView6);
		textViewMultiNick = (TextView) findViewById(R.id.c03_textView8);
		// 返回单个用户
		String userUrl = "http://gw.api.taobao.com/router/rest?sign=EF87CC42B707AFF1234FF8782113CDFB&timestamp=2012-08-13+20%3A50%3A28&v=2.0&app_key=12129701&method=taobao.user.get&partner_id=top-apitools&format=json&nick=lihaifeng555&fields=user_id,uid,nick,sex,buyer_credit,seller_credit,location,created,last_visit,birthday,type,status,alipay_no,alipay_account,alipay_account,email,consumer_protection,alipay_bind";
		String userString = UserUtil.getStringByUrl(userUrl);
		Log.i("ParseJSONActivity", userString);
		// 返回多个用户
		User user = readSingleUser(userString);
		textViewSingleCity.setText(user.getCity());
		textViewSingleNick.setText(user.getNick());
		String usersUrl = "http://gw.api.taobao.com/router/rest?sign=BDD6F4A3DDBF2F42AB67530CBD6ACBCB&timestamp=2012-08-21+23%3A06%3A04&v=2.0&app_key=12129701&method=taobao.users.get&partner_id=top-apitools&format=json&nicks=lihaifeng555,andyy_tan&fields=user_id,nick,sex,buyer_credit,seller_credit,location,created,last_visit";
		String usersString = UserUtil.getStringByUrl(usersUrl);
		Log.i("ParseJSONActivity", usersString);
		ArrayList<User> users = readMultiUser(usersString);
		for (User u : users) {
			textViewMultiCity.append(u.getCity() + ";");
			textViewMultiNick.append(u.getNick() + ";");
		}
	}

	// 普通Json数据解析

	private User readSingleUser(String strResult) {
		User user = new User();
		try {
			// 获取用户对象
			JSONObject jsonUserObj = new JSONObject(strResult).getJSONObject(
					"user_get_response").getJSONObject("user");
			user.setNick(jsonUserObj.getString("nick"));
			JSONObject jsonLocationObj = jsonUserObj.getJSONObject("location");
			user.setCity(jsonLocationObj.getString("city"));
		} catch (JSONException e) {
			System.out.println("Json parse error");
			e.printStackTrace();
		}
		return user;
	}

	// 解析多个数据的Json
	private ArrayList<User> readMultiUser(String strResult) {
		ArrayList<User> users = new ArrayList<User>();
		try {
			JSONArray jsonObjs = new JSONObject(strResult)
					.getJSONObject("users_get_response").getJSONObject("users")
					.getJSONArray("user");
			for (int i = 0; i < jsonObjs.length(); i++) {
				JSONObject jsonUserObj = ((JSONObject) jsonObjs.opt(i));
				User user = new User();
				user.setNick(jsonUserObj.getString("nick"));
				JSONObject jsonLocationObj = jsonUserObj
						.getJSONObject("location");
				user.setCity(jsonLocationObj.getString("city"));
				users.add(user);
			}
		} catch (JSONException e) {
			System.out.println("Jsons parse error !");
			e.printStackTrace();
		}
		return users;
	}
}