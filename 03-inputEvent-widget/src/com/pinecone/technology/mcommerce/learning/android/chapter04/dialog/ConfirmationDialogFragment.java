package com.pinecone.technology.mcommerce.learning.android.chapter04.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class ConfirmationDialogFragment extends DialogFragment implements
		DialogInterface.OnClickListener {
	private ConfirmationDialogFragmentListener listener;

	public static ConfirmationDialogFragment newInstance(int title) {
		ConfirmationDialogFragment frag = new ConfirmationDialogFragment();
		Bundle args = new Bundle();
		args.putInt("title", title);
		frag.setArguments(args);
		return frag;
	}

	public interface ConfirmationDialogFragmentListener {
		public void onPositiveClick();

		public void onNegativeClick();
	}

	public void setConfirmationDialogFragmentListener(
			ConfirmationDialogFragmentListener listener) {
		this.listener = listener;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int title = getArguments().getInt("title");
		return new AlertDialog.Builder(getActivity())
				.setIcon(android.R.drawable.ic_dialog_alert).setTitle(title)
				.setPositiveButton(android.R.string.ok, this)
				.setNegativeButton(android.R.string.cancel, this).create();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if (listener != null) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				listener.onPositiveClick();
			default:
				listener.onNegativeClick();
			}
		}
	}
}