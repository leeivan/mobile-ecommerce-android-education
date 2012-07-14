package com.google.mcommerce.sample.android.chapter06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SimpleListViewActivity extends ListActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayList<Map<String, String>> list = buildData();
		String[] from = { "name", "age" };
		int[] to = { android.R.id.text1, android.R.id.text2 };

		SimpleAdapter adapter = new SimpleAdapter(this, list,
				android.R.layout.simple_list_item_2, from, to);
		setListAdapter(adapter);
	}

	private ArrayList<Map<String, String>> buildData() {
		ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list.add(putData("Tom", "18"));
		list.add(putData("John", "19"));
		list.add(putData("Marry", "20"));
		return list;
	}

	private HashMap<String, String> putData(String name, String age) {
		HashMap<String, String> item = new HashMap<String, String>();
		item.put("name", name);
		item.put("age", age);
		return item;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) l
				.getItemAtPosition(position);
		String name = (String) map.get("name");
		String age = (String) map.get("age");
		Toast.makeText(this.getApplicationContext(), name + " is " + age,
				Toast.LENGTH_SHORT).show();
	}

}