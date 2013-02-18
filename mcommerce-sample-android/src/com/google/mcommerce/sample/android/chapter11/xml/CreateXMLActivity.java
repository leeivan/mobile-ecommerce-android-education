package com.google.mcommerce.sample.android.chapter11.xml;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.mcommerce.sample.android.R;

public class CreateXMLActivity extends Activity {
	private Button btn_createXML = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c11_create_xml_layout);
		btn_createXML = (Button) findViewById(R.id.button01);
		btn_createXML.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.button01:
					try {
						createXML();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				default:
					break;
				}
			}
		});
	}

	/**
	 * 创建xml文件
	 * 
	 * @throws Exception
	 */
	public void createXML() throws Exception {
		// 创建文件保存路径：data/data/app/files/uistate.xml
		File file = new File(getFilesDir(), "uistate.xml");

		ArrayList<ViewState> vState = new ArrayList<ViewState>();
		// 添加数据
		vState.add(new ViewState("button", "send", 10));
		vState.add(new ViewState("textview", "this is a demo!", 11));

		// 方式1
		/*
		 * FileOutputStream outStream = new FileOutputStream(file);
		 * PullXMLUtils.createXML(vState, outStream);
		 */

		// 方式2
		FileWriter writer = new FileWriter(file);
		PullXMLUtils.createXML(vState, writer);

		// 方式3:可用于显示
		/*
		 * StringWriter sWriter = new StringWriter(); Log.d("mark",
		 * sWriter.toString());
		 */
	}
}