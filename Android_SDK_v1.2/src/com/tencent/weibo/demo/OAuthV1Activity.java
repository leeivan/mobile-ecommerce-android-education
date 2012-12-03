package com.tencent.weibo.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.tencent.weibo.R;
import com.tencent.weibo.oauthv1.OAuthV1;
import com.tencent.weibo.oauthv1.OAuthV1Client;
import com.tencent.weibo.utils.QHttpClient;
import com.tencent.weibo.webview.OAuthV1AuthorizeWebView;

/**
 * OAuth Version 1.0授权示例（使用WebView方式获取授权码）
 */
public class OAuthV1Activity extends Activity{

    private static String TAG="OAuthV1Activity.class";

    /*
     * 申请APP KEY的具体介绍，可参见 
     * http://wiki.open.t.qq.com/index.php/应用接入指引
     * http://wiki.open.t.qq.com/index.php/腾讯微博移动应用接入规范#.E6.8E.A5.E5.85.A5.E6.B5.81.E7.A8.8B
     */
    //!!!请根据您的实际情况修改!!!      认证成功后浏览器会被重定向到这个url中   本例子中不需改动
	private String oauthCallback = "null"; 
    //!!!请根据您的实际情况修改!!!      换为您为自己的应用申请到的APP KEY
	private String oauthConsumeKey = "801279287"; 
    //!!!请根据您的实际情况修改!!!      换为您为自己的应用申请到的APP SECRET
	private String oauthConsumerSecret="99224bf0ece4db768c21b70875da5dd8";
	
	private Button btnRequest;                        //"获取Request_token"按钮
	private Button btnAuthorize;                     //"用WebView请求用户授权"按钮
	private Button btnAccess;	                          //"获取Access_token"按钮
	private Button btnAPItest;                         //"微博API测试"按钮
	private TextView textRequest;                    //用于显示获取到的Request_token
	private TextView textVerifier;                     //用于显示腾讯微博开放平台返回的验证码
	private TextView textAccess;                      //用于显示获取到的Access_token
	private OAuthV1 oAuth;                                 //Oauth鉴权所需及所得信息的封装存储单元
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oauthv1);

        oAuth=new OAuthV1(oauthCallback);
        oAuth.setOauthConsumerKey(oauthConsumeKey);
        oAuth.setOauthConsumerSecret(oauthConsumerSecret);
        
        //设置界面元素，并添加对各按钮的监听
        btnRequest=(Button)findViewById(R.id.btnRequest);
        textRequest=(TextView)findViewById(R.id.textRequest);
        btnAuthorize=(Button)findViewById(R.id.btnAuthorize);
        textVerifier=(TextView)findViewById(R.id.textVerifier);
        btnAccess=(Button)findViewById(R.id.btnAccess);
        textAccess=(TextView)findViewById(R.id.textAccess);
        btnAPItest=(Button)findViewById(R.id.btnAPItest);
		btnRequest.setOnClickListener(listener);
		btnAuthorize.setOnClickListener(listener);
		btnAccess.setOnClickListener(listener);
		btnAPItest.setOnClickListener(listener);
		
		//关闭OAuthV1Client中的默认开启的QHttpClient。
		OAuthV1Client.getQHttpClient().shutdownConnection();
		
		//为OAuthV1Client配置自己定义QHttpClient。
		OAuthV1Client.setQHttpClient(new QHttpClient());
    }

    private OnClickListener listener=new OnClickListener()
	{
		public void onClick(View v){
			Intent intent;
			switch (v.getId())
			{
    			case R.id.btnRequest:
    				Log.i(TAG, "---------Step1: Get requestToken--------");
					try {
						//向腾讯微博开放平台请求获得未授权的Request_Token
						 oAuth=OAuthV1Client.requestToken(oAuth);
					} catch (Exception e) {
						e.printStackTrace();
					}
					textRequest.append("\nrequest_token:\n"+oAuth.getOauthToken()+
							"\nrequest_token_secret:\n"+oAuth.getOauthTokenSecret());
    				break;
    			case R.id.btnAuthorize:
    				Log.i(TAG, "---------Step2: authorization--------");
    				Log.i(TAG, "start WebView intent");
			        intent = new Intent(OAuthV1Activity.this, OAuthV1AuthorizeWebView.class);//创建Intent，使用WebView让用户授权
			        intent.putExtra("oauth", oAuth);
					startActivityForResult(intent,1);	
    				break;
    			case R.id.btnAccess:
    				Log.i(TAG, "---------Step3: getAccessToken--------");
					try {
						oAuth=OAuthV1Client.accessToken(oAuth);
						/*
						 * 注意：此时oauth中的Oauth_token和Oauth_token_secret将发生变化，用新获取到的
						 * 已授权的access_token和access_token_secret替换之前存储的未授权的request_token
						 * 和request_token_secret.
						 */
					} catch (Exception e) {
						e.printStackTrace();
					}
					textAccess.append("\naccess_token:\n"+oAuth.getOauthToken()+
							"\naccess_token_secret:\n"+oAuth.getOauthTokenSecret());
    				break;
    			case R.id.btnAPItest:
    				Log.i(TAG, "---------Step4: Test API V1--------");
    				intent= new Intent(OAuthV1Activity.this, WeiBoAPIV1Activity.class);//创建Intent，转到调用Qweibo API的Activity
    				intent.putExtra("oauth",oAuth);
    				startActivity(intent);
                    break;
			}
		}
	};
	
    public void onBackPressed() {
      //关闭OAuthV1Client中的自定义的QHttpClient。
        OAuthV1Client.getQHttpClient().shutdownConnection();
        finish();
    }
    
	/*
	 * 通过读取OAuthV1AuthorizeWebView返回的Intent，获取用户授权后的验证码
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data)	{
		if (requestCode==1)	{
			if (resultCode==OAuthV1AuthorizeWebView.RESULT_CODE)	{
				//从返回的Intent中获取验证码
				oAuth=(OAuthV1) data.getExtras().getSerializable("oauth");
				textVerifier.append("\nverifier:\n"+oAuth.getOauthVerifier());
			}
		}
	}
}
