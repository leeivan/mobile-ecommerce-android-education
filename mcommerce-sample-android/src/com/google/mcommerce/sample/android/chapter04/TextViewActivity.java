package com.google.mcommerce.sample.android.chapter04;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

import com.google.mcommerce.sample.android.R;

public class TextViewActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c04_textview);// 设置内容显示的xml布局文件
		TextView textView = (TextView) findViewById(R.id.textView);// 取得我们的TextView组件
		textView.setTextColor(Color.RED);// 设置成红色
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f);// 设置成24sp
		textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));// 加粗
	}
}
