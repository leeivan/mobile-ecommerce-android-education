package com.pinecone.technology.mcommerce.learning.android.chapter11.dragdrop;

// This file is Palette.java
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pinecone.technology.mcommerce.learning.android.chapter11.R;

public class Palette extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle icicle) {
		View v = inflater.inflate(R.layout.drag_drop_palette, container,
				false);
		return v;
	}
}
