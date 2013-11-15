package com.pinecone.technology.mcommerce.learning.android.chapter06.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pinecone.technology.mcommerce.learning.android.chapter06.R;

public class HandlerActivity extends Activity implements OnClickListener {
	private static final String TAG = "HandlerActivity";
	private Handler countHandler = new Handler();
	private TextView tvCount;
	private ProgressBar mProgressBar;
	private int count = 0;

	private Runnable mRunToast = new Runnable() {
		@Override
		public void run() {
			Toast.makeText(HandlerActivity.this, "15秒后显示Toast提示信息",
					Toast.LENGTH_LONG).show();
		}
	};

	private Runnable mRunCount = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			tvCount.setText("Count：" + String.valueOf(++count));
			countHandler.postDelayed(this, 1000);
		}

	};
	// 使用匿名内部类来复写Handler当中的handleMessage方法
	private Handler updateProgressBarHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			mProgressBar.setProgress(msg.arg1);
			updateProgressBarHandler.post(mUpateProgressBarThread);
		}

	};
	private Runnable mUpateProgressBarThread = new Runnable() {

		int i = 0;

		@Override
		public void run() {
			log("Begin Thread");
			i = i + 10;
			// 得到一个消息对象，Message类是有Android操作系统提供
			Message msg = updateProgressBarHandler.obtainMessage();
			// 将msg对象的arg1参数的值设置为i,用arg1和arg2这两个成员变量传递消息，优点是系统性能消耗较少
			msg.arg1 = i;
			try {
				// 设置当前显示睡眠1秒
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 将msg对象加入到消息队列当中
			updateProgressBarHandler.sendMessage(msg);
			if (i == 100) {
				// 如果当i的值为100时，就将线程对象从handler当中移除
				updateProgressBarHandler
						.removeCallbacks(mUpateProgressBarThread);
			}
		}
	};

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnStart:
			countHandler.postDelayed(mRunCount, 1000);
			break;
		case R.id.btnStop:
			countHandler.removeCallbacks(mRunCount);
			break;
		case R.id.btnShowToast:
			countHandler.postAtTime(mRunToast,
					android.os.SystemClock.uptimeMillis() + 15 * 1000);
			break;
		case R.id.btnUpdateProgressBar:
			mProgressBar.setVisibility(View.VISIBLE);
			updateProgressBarHandler.post(mUpateProgressBarThread);
			break;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c06_handler_test01);
		((Button) findViewById(R.id.btnStart)).setOnClickListener(this);
		((Button) findViewById(R.id.btnStop)).setOnClickListener(this);
		((Button) findViewById(R.id.btnShowToast)).setOnClickListener(this);
		((Button) findViewById(R.id.btnUpdateProgressBar))
				.setOnClickListener(this);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
		tvCount = (TextView) findViewById(R.id.tvCount);
	}

	private void log(String msg) {
		Log.d(TAG, msg);
	}
}