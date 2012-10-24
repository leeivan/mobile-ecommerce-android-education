package com.google.mcommerce.sample.android.chapter06.asyncTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.mcommerce.sample.android.R;

public class AsyncTaskActivity extends Activity implements OnClickListener {
	private Button Btn;
	private TextView txt;
	private int count = 0;
	private boolean isRunning = false;
	private ProgressBar progressBar;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c06_async_task_test01);
		Btn = (Button) findViewById(R.id.button1);
		txt = (TextView) findViewById(R.id.textView1);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		Btn.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		TimeTickLoad timetick = new TimeTickLoad();
		timetick.execute(1000);
	}

	private class TimeTickLoad extends AsyncTask<Integer, Integer, String> {
		// 后面尖括号内分别是参数（例子里是线程休息时间），进度(publishProgress用到)，返回值 类型

		@Override
		protected void onPreExecute() {
			// 第一个执行方法
			super.onPreExecute();
			txt.setText("开始执行后台操作...");
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(Integer... params) {
			// 第二个执行方法,onPreExecute()执行完后执行
			for (int i = 0; i <= 10; i++) {
				publishProgress(i * 10);
				try {
					Thread.sleep(params[0]);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return "执行完毕";
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			super.onProgressUpdate(progress);
			// 这个函数在doInBackground调用publishProgress时触发，虽然调用时只有一个参数
			// 但是这里取到的是一个数组,所以要用progesss[0]来取值
			// 第n个参数就用progress[n]来取值
			progressBar.setProgress(progress[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			// doInBackground返回时触发，换句话说，就是doInBackground执行完后触发
			// 这里的result就是上面doInBackground执行后的返回值，所以这里是"执行完毕"
			super.onPostExecute(result);
			txt.setText(result);
		}

	}
}
