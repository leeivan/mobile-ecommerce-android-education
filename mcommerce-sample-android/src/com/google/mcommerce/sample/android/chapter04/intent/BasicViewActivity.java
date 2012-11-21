package com.google.mcommerce.sample.android.chapter04.intent;

import android.app.Activity;
import android.os.Bundle;

import com.google.mcommerce.sample.android.R;

public class BasicViewActivity extends Activity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c04_basic_view);
    }
}//eof-class
