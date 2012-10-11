package com.google.mcommerce.sample.android.chapter12;

// This file is TrueButton.java
import android.content.Context;
import android.util.AttributeSet;

public class TrueButton01 extends BooleanButton {
    protected boolean myValue() {
    	return true;
    }
    
	public TrueButton01(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
}
