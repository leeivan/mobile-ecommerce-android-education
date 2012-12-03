package com.tencent.weibo.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.weibo.R;
import com.tencent.weibo.oauthv2.OAuthV2;
import com.tencent.weibo.oauthv2.OAuthV2Client;
import com.tencent.weibo.webview.OAuthV2AuthorizeWebView;

/**
 * OAuth Version 2.a授权示例（使用WebView方式请求用户授权）
 */
public class OAuthV2ImplicitGrantActivity extends Activity {

    private static String TAG="OAuthV2ImplicitGrantActivity.class";
    
    /*
     * 申请APP KEY的具体介绍，可参见 
     * http://wiki.open.t.qq.com/index.php/应用接入指引
     * http://wiki.open.t.qq.com/index.php/腾讯微博移动应用接入规范#.E6.8E.A5.E5.85.A5.E6.B5.81.E7.A8.8B
     */
    //!!!请根据您的实际情况修改!!!      认证成功后浏览器会被重定向到这个url中  必须与注册时填写的一致
    private String redirectUri="http://www.qq.com";                   
    //!!!请根据您的实际情况修改!!!      换为您为自己的应用申请到的APP KEY
    private String clientId = "801279287"; 
    //!!!请根据您的实际情况修改!!!      换为您为自己的应用申请到的APP SECRET
    private String clientSecret="99224bf0ece4db768c21b70875da5dd8";
    
    private OAuthV2 oAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oauthv2);
        
        oAuth=new OAuthV2(redirectUri);
        oAuth.setClientId(clientId);
        oAuth.setClientSecret(clientSecret);
        
        //关闭OAuthV2Client中的默认开启的QHttpClient。
        OAuthV2Client.getQHttpClient().shutdownConnection();
        
        OnClickListener listener=new OnClickListener()
        {
            public void onClick(View v){
                Intent intent;
                switch (v.getId()){
                    case R.id.btnImplicitGrant:
                        Log.i(TAG, "-------------Step1: Implicit Grant--------------");
                        intent = new Intent(OAuthV2ImplicitGrantActivity.this, OAuthV2AuthorizeWebView.class);//创建Intent，使用WebView让用户授权
                        intent.putExtra("oauth", oAuth);
                        startActivityForResult(intent,2);   
                        break;
                    case R.id.btnAPItest3:
                        Log.i(TAG, "-------------Step2: Test API V2--------------");
                        intent= new Intent(OAuthV2ImplicitGrantActivity.this, WeiBoAPIV2Activity.class);//创建Intent，转到调用Qweibo API的Activity
                        intent.putExtra("oauth",oAuth);
                        startActivity(intent);
                        break;
                }
            }
        };
        
        Button btnImplicitGrant=(Button)findViewById(R.id.btnImplicitGrant);
        Button btnAPItest3=(Button)findViewById(R.id.btnAPItest3);
        btnImplicitGrant.setOnClickListener(listener);
        btnAPItest3.setOnClickListener(listener);
        
    }
    
    public void onBackPressed() {
        finish();
    }
    
    /*
     * 通过读取OAuthV2AuthorizeWebView返回的Intent，获取用户授权信息
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data)   {
        if (requestCode==2) {
            if (resultCode==OAuthV2AuthorizeWebView.RESULT_CODE)    {
                oAuth=(OAuthV2) data.getExtras().getSerializable("oauth");
                if(oAuth.getStatus()==0)
                    Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
