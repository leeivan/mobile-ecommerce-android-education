package com.pinecone.technology.mcommerce.learning.android.chapter04.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.pinecone.technology.mcommerce.learning.android.chapter04.R;
import com.pinecone.technology.mcommerce.learning.android.chapter04.dialog.ConfirmationDialogFragment.ConfirmationDialogFragmentListener;

public class SimpleConfirmationDialogFragmentActivity extends Activity
		implements OnClickListener, ConfirmationDialogFragmentListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c04_dialog_fragment);
		Button buttonPostDialog = (Button) findViewById(R.id.button_post_dialog);
		buttonPostDialog.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		ConfirmationDialogFragment confirmationDialog = ConfirmationDialogFragment
				.newInstance(R.string.dialog_format_title);
		confirmationDialog.setConfirmationDialogFragmentListener(this);
		confirmationDialog.show(getFragmentManager(), null);
	}

	@Override
	public void onPositiveClick() {
		Toast.makeText(this, android.R.string.ok, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onNegativeClick() {
		Toast.makeText(this, android.R.string.cancel, Toast.LENGTH_LONG).show();
	}
}
