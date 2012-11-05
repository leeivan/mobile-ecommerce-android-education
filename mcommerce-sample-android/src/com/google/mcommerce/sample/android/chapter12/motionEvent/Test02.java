package com.google.mcommerce.sample.android.chapter12.motionEvent;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.mcommerce.sample.android.R;

public class Test02 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c12_test);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.c12_test_02, menu);
        return true;
    }

    
}
