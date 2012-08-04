package com.google.mcommerce.sample.android.chapter06;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.google.mcommerce.sample.android.R;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.request.TaobaokeItemsGetRequest;
import com.taobao.api.response.TaobaokeItemsGetResponse;

public class SearchProductsAPIActivity extends Activity {
	protected static String url = "http://gw.api.taobao.com/router/rest";// 沙箱环境调用地址
	// 正式环境需要设置为:http://gw.api.taobao.com/router/rest
	private String appkey = "12380481";
	private String appSecret = "7a1a232f80d05ea956e494f9e3100dc1";
	private TextView productTitle;
	private TextView productPrice;

	private static String format = "json";
	private static String signMethod = "md5";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c06_search_product);
		productTitle = (TextView) findViewById(R.id.product_title);
		productPrice = (TextView) findViewById(R.id.product_price);
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret,
				format, 0, 0, signMethod);
		TaobaokeItemsGetRequest req = new TaobaokeItemsGetRequest();
		req.setFields("num_iid,title,nick,pic_url,price,click_url,commission,commission_rate,commission_num,commission_volume,shop_click_url,seller_credit_score,item_location,volume");
		req.setNick("leeivan");
		req.setKeyword("mp3");
		try {
			TaobaokeItemsGetResponse response = client.execute(req);
			if (response != null) {
				TaobaokeItem taobaoitem = response.getTaobaokeItems().get(0);
				productTitle.setText(Html.fromHtml(taobaoitem.getTitle()));
				productPrice.setText(taobaoitem.getPrice());
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
