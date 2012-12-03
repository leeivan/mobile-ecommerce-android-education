package com.tencent.weibo.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tencent.weibo.R;

/**
 * 授权方式选择界面
 */
public class MainActivity extends Activity {
    
    private static String TAG="MainActivity.class"; 
    private Button btnOAuthV1; 
    private Button btnOAuthV2ImplicitGrant;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //设置界面元素，并添加对各按钮的监听
        btnOAuthV1=(Button)findViewById(R.id.btnOAuthV1);
        btnOAuthV2ImplicitGrant=(Button)findViewById(R.id.btnOAuthV2ImplicitGrant);
        
        btnOAuthV1.setOnClickListener(listener);
        btnOAuthV2ImplicitGrant.setOnClickListener(listener);
    }
    
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
    
    private OnClickListener listener=new OnClickListener()
    {
        public void onClick(View v){
            Intent intent;
            switch (v.getId())
            {
                case R.id.btnOAuthV1:
                    Log.i(TAG, "---------Test OAuth V1 Webview--------");
                    intent= new Intent(MainActivity.this, OAuthV1Activity.class);
                    startActivity(intent);
                    break;
                case R.id.btnOAuthV2ImplicitGrant:
                    Log.i(TAG, "---------Test OAuth V2 ImplicitGrant--------");
                    intent= new Intent(MainActivity.this, OAuthV2ImplicitGrantActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}