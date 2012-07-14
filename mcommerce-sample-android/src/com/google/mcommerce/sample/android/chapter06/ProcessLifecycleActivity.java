package com.google.mcommerce.sample.android.chapter06;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class ProcessLifecycleActivity extends Activity {
	private static final String TAG = "ProcessLifecycleActivity";
	/**
	 * 当前进程信息实例﻿
	 */
	RunningAppProcessInfo curRunningProcessInfo;

	/**
	 * 初始化并且得到当前进程信息
	 */
	private void initCurProcess() {
		ActivityManager am = (ActivityManager) this
				.getSystemService(ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> processList = am.getRunningAppProcesses();
		for (int i = 0; i < processList.size(); i++) {
			if (processList.get(i).processName.equals("com.taobao.mcommerce.sample")) {
				curRunningProcessInfo = processList.get(i);
				return;
			}
		}
	}

	/**
	 * 转换进程当前Level显示方式，将int转为String
	 * 
	 * @param imp
	 * @return
	 */
	protected String convertImportance(int imp) {
		String returnStr = null;
		switch (imp) {
		case RunningAppProcessInfo.IMPORTANCE_FOREGROUND:
			returnStr = "IMPORTANCE_FOREGROUND";
			break;
		case RunningAppProcessInfo.IMPORTANCE_VISIBLE:
			returnStr = "IMPORTANCE_VISIBLE";
			break;
		case RunningAppProcessInfo.IMPORTANCE_SERVICE:
			returnStr = "IMPORTANCE_SERVICE";
			break;
		case RunningAppProcessInfo.IMPORTANCE_BACKGROUND:
			returnStr = "IMPORTANCE_BACKGROUND";
			break;
		case RunningAppProcessInfo.IMPORTANCE_EMPTY:
			returnStr = "IMPORTANCE_EMPTY";
			break;
		default:
			break;

		}
		return returnStr;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initCurProcess();
		log(convertImportance(curRunningProcessInfo.importance));
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		if (id == 1) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you sure you want to exit?")
					.setCancelable(false)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									ProcessLifecycleActivity.this.finish();
								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			// AlertDialog alert = builder.create();
		}

		return super.onCreateDialog(id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 0, 1, "退出Activity");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == 0) {
			this.showDialog(1);
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		log(convertImportance(curRunningProcessInfo.importance));
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		log(convertImportance(curRunningProcessInfo.importance));
	}

	private void log(String msg) {
		Log.d(TAG, msg);
	}
}