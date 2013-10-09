package com.pinecone.technology.mcommerce.learning.android.chapter04.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pinecone.technology.mcommerce.learning.android.chapter04.R;

public class ToastCustomActivity extends Activity {

	private Button button;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.c04_toast);

		button = (Button) findViewById(R.id.mainbutton);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				// get your toast.xml layout
				LayoutInflater inflater = getLayoutInflater();

				View layout = inflater.inflate(R.layout.c04_toast_custom,
						(ViewGroup) findViewById(R.id.toast_layout_id));

				// set a message
				TextView text = (TextView) layout.findViewById(R.id.text);
				text.setText("This is a Custom Toast Message");

				// Toast configuration
				Toast toast = new Toast(getApplicationContext());
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setView(layout);
				toast.show();
			}
		});
	}
}