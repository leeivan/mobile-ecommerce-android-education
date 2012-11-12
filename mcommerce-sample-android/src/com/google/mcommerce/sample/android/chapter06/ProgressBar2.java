/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.mcommerce.sample.android.chapter06;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.google.mcommerce.sample.android.R;

/**
 * Demonstrates the use of indeterminate progress bars as widgets and in the
 * window's title bar. The widgets show the 3 different sizes of circular
 * progress bars that can be used.
 */
public class ProgressBar2 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 请求窗口特色风格，这里设置成圆形进度条风格
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		setContentView(R.layout.c06_progressbar_2);

		// 设置标题栏中的圆形进度条是否可以显示
		setProgressBarIndeterminateVisibility(true);
	}
}
