package com.pinecone.technology.mcommerce.learning.android.chapter04.text;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import com.pinecone.technology.mcommerce.learning.android.chapter04.R;

public class AutoCompleteTextViewActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c04_textview_01);

		AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.actv);

		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, new String[] {
						"English", "Hebrew", "Hindi", "Spanish", "German",
						"Greek" ,"Chinese"});

		actv.setAdapter(aa);

		MultiAutoCompleteTextView mactv = (MultiAutoCompleteTextView) findViewById(R.id.mactv);
		ArrayAdapter<String> aa2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, new String[] {
						"English", "Hebrew", "Hindi", "Spanish", "German",
						"Greek" ,"Chinese"});

		mactv.setAdapter(aa2);

		mactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

	}
}