package com.pinecone.technology.mcommerce.learning.android.chapter10.popupmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button = (Button) findViewById(R.id.button);
		TextView text = (TextView) findViewById(R.id.text);
		ImageView image = (ImageView) findViewById(R.id.image);

		button.setOnClickListener(viewClickListener);
		text.setOnClickListener(viewClickListener);
		image.setOnClickListener(viewClickListener);

	}

	OnClickListener viewClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			showPopupMenu(v);
		}

	};

	private void showPopupMenu(View v) {
		PopupMenu popupMenu = new PopupMenu(Main.this, v);
		popupMenu.getMenuInflater().inflate(R.menu.popupmenu,
				popupMenu.getMenu());

		popupMenu
				.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Toast.makeText(Main.this, item.toString(),
								Toast.LENGTH_LONG).show();
						return true;
					}
				});

		popupMenu.show();
	}
}