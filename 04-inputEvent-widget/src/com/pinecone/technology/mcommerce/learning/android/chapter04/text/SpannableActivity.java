package com.pinecone.technology.mcommerce.learning.android.chapter04.text;

import com.pinecone.technology.mcommerce.learning.android.chapter04.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;


public class SpannableActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c04_stlye_spannable);
		TextView tv3 = (TextView) this.findViewById(R.id.tv3);
		tv3.setText("默认、35像素、默认一半、默认两倍\n" + "前景色、背景色\n" + "正常、粗体、斜体、粗斜体\n"
				+ "下划线、删除线\n" + "下标、上标\n" + "项目符号",
				TextView.BufferType.SPANNABLE);
		Spannable spn = (Spannable) tv3.getText();
		// 设置字体(default,default-bold,monospace,serif,sans-serif)
		spn.setSpan(new TypefaceSpan("monospace"), 0, spn.length(),
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// 设置字体大小（绝对值,单位：像素）第二个参数boolean
		// dip，如果为true，表示前面的字体大小单位为dip，否则为像素，同上
		spn.setSpan(new AbsoluteSizeSpan(35), 3, 7,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// 设置字体大小（相对值,单位：像素） 参数表示为默认字体大小的多少倍
		spn.setSpan(new RelativeSizeSpan(0.5f), 8, 12,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 0.5f表示默认字体大小的一半
		spn.setSpan(new RelativeSizeSpan(2.0f), 13, 17,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 2.0f表示默认字体大小的两倍

		// // 设置字体前景色
		spn.setSpan(new ForegroundColorSpan(Color.MAGENTA), 18, 21,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 设置前景色为洋红色

		// 设置字体背景色
		spn.setSpan(new BackgroundColorSpan(Color.CYAN), 22, 25,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 设置背景色为青色

		// 设置字体样式正常，粗体，斜体，粗斜体
		spn.setSpan(new StyleSpan(android.graphics.Typeface.NORMAL), 26, 28,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 正常
		spn.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 29, 31,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 粗体
		spn.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 32, 34,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 斜体
		spn.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 35,
				38, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 粗斜体

		// 设置下划线
		spn.setSpan(new UnderlineSpan(), 39, 42,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 设置删除线
		spn.setSpan(new StrikethroughSpan(), 43, 46,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 设置上下标
		spn.setSpan(new SubscriptSpan(), 47, 49,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 下标
		spn.setSpan(new SuperscriptSpan(), 50, 52,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 上标

		// // 超级链接（需要添加setMovementMethod方法附加响应）
		// spn.setSpan(new URLSpan("tel:7028023379"), 53, 55,
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 电话
		// spn.setSpan(new URLSpan("mailto:leeivan2008@gmail.com"), 56, 58,
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 邮件
		// spn.setSpan(new URLSpan("http://www.google.com"), 59, 61,
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 网址

		// 设置项目符号
		spn.setSpan(new BulletSpan(
				android.text.style.BulletSpan.STANDARD_GAP_WIDTH, Color.GREEN),
				53, spn.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// tv3.setMovementMethod(movement)
	}
}