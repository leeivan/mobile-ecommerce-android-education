package com.google.mcommerce.sample.android.chapter04.text;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.google.mcommerce.sample.android.R;

public class EditTextSearchActivity extends Activity {
	private EditText editText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c05_edittext_search);
		editText = (EditText) findViewById(R.id.edit_text);
		editText.setOnEditorActionListener(new OnEditorActionListener() {
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				switch (actionId) {
				case EditorInfo.IME_ACTION_SEARCH:
					Toast.makeText(EditTextSearchActivity.this,
							String.valueOf("开始搜索：") + v.getText(),
							Toast.LENGTH_SHORT).show();
					break;
				}

				return true;
			}
		});
		// 获取EditText文本
		Button getValue = (Button) findViewById(R.id.btn_get_value);
		getValue.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(EditTextSearchActivity.this,
						editText.getText().toString(), Toast.LENGTH_SHORT)
						.show();
			}
		});
		// 让EditText全选
		Button all = (Button) findViewById(R.id.btn_all);
		all.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editText.selectAll();
			}
		});
		// 从第2个字符开始选择EditText文本
		Button select = (Button) findViewById(R.id.btn_select);
		select.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Editable editable = editText.getText();
				Selection.setSelection(editable, 1, editable.length());
			}
		});
		// 获取选中的文本
		Button getSelect = (Button) findViewById(R.id.btn_get_select);
		getSelect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int start = editText.getSelectionStart();
				int end = editText.getSelectionEnd();
				CharSequence selectText = editText.getText().subSequence(start,
						end);
				Toast.makeText(EditTextSearchActivity.this, selectText,
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
