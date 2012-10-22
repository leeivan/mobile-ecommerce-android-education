package com.google.mcommerce.sample.android.chapter04.permission;

import com.google.mcommerce.sample.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClientCustPermMainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void doClick(View view) {
        Intent intent = new Intent();
        intent.setClassName("com.cust.perm","com.cust.perm.PrivActivity");
        startActivity(intent);
    }
}
