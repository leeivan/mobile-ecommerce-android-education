package com.google.mcommerce.sample.android.chapter03;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.mcommerce.sample.android.R;

public class ParseXMLActivity extends Activity {

	private TextView textViewSingleCity;
	private TextView textViewSingleNick;
	private TextView textViewMultiCity;
	private TextView textViewMultiNick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c03_parse_json_xml_layout);
		textViewSingleCity = (TextView) findViewById(R.id.c03_textView1);
		textViewSingleNick = (TextView) findViewById(R.id.c03_textView4);
		textViewMultiCity = (TextView) findViewById(R.id.c03_textView6);
		textViewMultiNick = (TextView) findViewById(R.id.c03_textView8);
		String url = "http://gw.api.taobao.com/router/rest?sign=05BD06221FC4BE98C0EAF71811A2EE3C&timestamp=2012-08-26+17%3A33%3A23&v=2.0&app_key=12129701&method=taobao.user.get&partner_id=top-apitools&format=xml&nick=andyy_tan&fields=user_id,uid,nick,sex,buyer_credit,seller_credit,location,created,last_visit,birthday,type,status,alipay_no,alipay_account,alipay_account,email,consumer_protection,alipay_bind";
		User user = Util.readSingleUserByDOM(url);
		textViewSingleNick.setText(user.getNick());
		textViewSingleCity.setText(user.getCity());
		url = "http://gw.api.taobao.com/router/rest?sign=28F5C6C7CD64F4101F79009B28E76355&timestamp=2012-08-26+22%3A22%3A28&v=2.0&app_key=12129701&method=taobao.users.get&partner_id=top-apitools&format=xml&nicks=andyy_tan,lihaifeng555&fields=user_id,nick,sex,buyer_credit,seller_credit,location,created,last_visit";
		ArrayList<User> users = Util.readMultiUserByDOM(url);
		for (User u : users) {
			textViewMultiNick.append(u.getNick() + ";");
			textViewMultiCity.append(u.getCity() + ";");
		}
	}

}
