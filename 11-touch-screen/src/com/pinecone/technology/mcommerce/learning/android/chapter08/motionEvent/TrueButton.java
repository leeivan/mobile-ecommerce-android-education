package com.pinecone.technology.mcommerce.learning.android.chapter08.motionEvent;

// This file is TrueButton.java
import android.content.Context;
import android.util.AttributeSet;

public class TrueButton extends BooleanButton {
	protected boolean myValue() {
		return true;
	}

	public TrueButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
}
