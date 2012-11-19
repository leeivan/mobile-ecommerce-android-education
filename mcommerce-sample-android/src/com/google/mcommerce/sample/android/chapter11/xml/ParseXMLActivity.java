package com.google.mcommerce.sample.android.chapter11.xml;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.mcommerce.sample.android.R;
import com.google.mcommerce.sample.android.chapter11.entity.User;

public class ParseXMLActivity extends Activity {

	private TextView textViewSingleCity;
	private TextView textViewSingleNick;
	private TextView textViewMultiCity;
	private TextView textViewMultiNick;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private String userUrl;
	private String usersUrl;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c11_parse_xml_layout);
		textViewSingleCity = (TextView) findViewById(R.id.textView1);
		textViewSingleNick = (TextView) findViewById(R.id.textView4);
		textViewMultiCity = (TextView) findViewById(R.id.textView6);
		textViewMultiNick = (TextView) findViewById(R.id.textView8);
		textView = (TextView) findViewById(R.id.textView9);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		// 返回单个用户的Url
		userUrl = "http://gw.api.taobao.com/router/rest?sign=05BD06221FC4BE98C0EAF71811A2EE3C&timestamp=2012-08-26+17%3A33%3A23&v=2.0&app_key=12129701&method=taobao.user.get&partner_id=top-apitools&format=xml&nick=andyy_tan&fields=user_id,uid,nick,sex,buyer_credit,seller_credit,location,created,last_visit,birthday,type,status,alipay_no,alipay_account,alipay_account,email,consumer_protection,alipay_bind";
		// 返回多个用户的Url
		usersUrl = "http://gw.api.taobao.com/router/rest?sign=28F5C6C7CD64F4101F79009B28E76355&timestamp=2012-08-26+22%3A22%3A28&v=2.0&app_key=12129701&method=taobao.users.get&partner_id=top-apitools&format=xml&nicks=andyy_tan,lihaifeng555&fields=user_id,nick,sex,buyer_credit,seller_credit,location,created,last_visit";
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("ParseJSONActivity", userUrl);
				User user = XMLUtil.readSingleUserByDOM(userUrl);
				textViewSingleNick.setText(user.getNick());
				textViewSingleCity.setText(user.getCity());
			}
		});
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textViewMultiNick.setText("");
				textViewMultiCity.setText("");
				textView.setText("使用DOM解析多个用户");
				String usersString = XMLUtil.getStringByUrl(usersUrl);
				ArrayList<User> users = XMLUtil.readMultiUserByDOM(usersUrl);
				for (User u : users) {
					textViewMultiNick.append(u.getNick() + ";");
					textViewMultiCity.append(u.getCity() + ";");
				}
			}
		});
		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textViewMultiNick.setText("");
				textViewMultiCity.setText("");
				textView.setText("使用SAX解析多个用户");
				ArrayList<User> users = XMLUtil.readMultiUserBySAX(usersUrl);
				for (User u : users) {
					textViewMultiNick.append(u.getNick() + ";");
					textViewMultiCity.append(u.getCity() + ";");
				}
			}
		});
		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textViewMultiNick.setText("");
				textViewMultiCity.setText("");
				textView.setText("使用PULL解析多个用户");
				ArrayList<User> users = XMLUtil.readMultiUserByPULL(usersUrl);
				for (User u : users) {
					textViewMultiNick.append(u.getNick() + ";");
					textViewMultiCity.append(u.getCity() + ";");
				}
			}
		});
	}
}
