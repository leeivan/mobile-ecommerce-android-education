package com.google.mcommerce.sample.android.chapter04.adapterView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.google.mcommerce.sample.android.R;

public class ListDialogActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	private String[] provinces = new String[] { "福建", "广东", "浙江", "江苏", "上海",
			"北京" };
	private ButtonOnClick buttonOnClick = new ButtonOnClick(0);
	private ListView lv = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c05_alart_dialog);
		Button btnList = (Button) findViewById(R.id.list);
		Button btnSingleChoice = (Button) findViewById(R.id.singlechoice);
		Button btnMultiChoice = (Button) findViewById(R.id.multichoice);
		btnList.setOnClickListener(this);
		btnSingleChoice.setOnClickListener(this);
		btnMultiChoice.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.list: {
			showList();
			break;
		}
		case R.id.singlechoice: {
			showSingleChoice();
			break;
		}
		case R.id.multichoice: {
			showMultiChoice();
			break;
		}
		}
	}

	private void showMultiChoice() {
		// TODO Auto-generated method stub
		AlertDialog ad = new AlertDialog.Builder(this)
				.setIcon(R.drawable.ic_launcher)
				.setTitle("选择省份")
				.setMultiChoiceItems(
						provinces,
						new boolean[] { false, true, false, true, false, false },
						new DialogInterface.OnMultiChoiceClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which, boolean isChecked) {
								// TODO Auto-generated method stub

							}
						})
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						int count = lv.getCount();
						String s = "您选择了：";
						for (int i = 0; i < provinces.length; i++) {
							if (lv.getCheckedItemPositions().get(i))
								s += i + ":" + lv.getAdapter().getItem(i) + " ";
						}
						if (lv.getCheckedItemPositions().size() > 0) {
							new AlertDialog.Builder(ListDialogActivity.this)
									.setMessage(s).show();
						} else {
							new AlertDialog.Builder(ListDialogActivity.this)
									.setMessage("您未选择任何省份").show();
						}
					}
				}).setNegativeButton("取消", null).create();
		lv = ad.getListView();
		ad.show();
	}

	private void showSingleChoice() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(this).setTitle("选择省份")
				.setSingleChoiceItems(provinces, 0, buttonOnClick)
				.setPositiveButton("确定", buttonOnClick)
				.setNegativeButton("取消", buttonOnClick).show();
	}

	private void showList() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(this).setTitle("选择省份")
				.setItems(provinces, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						final AlertDialog ad = new AlertDialog.Builder(
								ListDialogActivity.this).setMessage(
								"您已选择了：" + which + ":" + provinces[which])
								.show();
						Handler hander = new Handler();
						hander.postDelayed(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								ad.dismiss();
							}
						}, 5000);
					}
				}).show();
	}

	private class ButtonOnClick implements DialogInterface.OnClickListener {
		private int index;

		public ButtonOnClick(int index) {
			this.index = index;
		}

		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			if (which >= 0) {
				index = which;
			} else {
				if (which == DialogInterface.BUTTON_POSITIVE) {
					new AlertDialog.Builder(ListDialogActivity.this)
							.setMessage(
									"您已选择了：" + index + ":" + provinces[index])
							.show();
				} else if (which == DialogInterface.BUTTON_NEGATIVE) {
					new AlertDialog.Builder(ListDialogActivity.this)
							.setMessage("您什么都未选择。").show();
				}
			}
		}

	}
}