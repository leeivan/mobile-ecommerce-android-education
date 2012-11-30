package com.google.mcommerce.sample.android.chapter15.weibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.mcommerce.sample.android.R;
import com.tencent.weibo.api.UserAPI;
import com.tencent.weibo.constants.OAuthConstants;
import com.tencent.weibo.oauthv2.OAuthV2;
import com.tencent.weibo.webview.OAuthV2AuthorizeWebView;

public class TecentWeiboActivity extends Activity {
    private OAuthV2 oAuth;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        oAuth=new OAuthV2("http://www.tencent.com/zh-cn/index.shtml");
        oAuth.setClientId("801115505");
        oAuth.setClientSecret("be1dd1410434a9f7d5a2586bab7a6829");

        Intent intent = new Intent(TecentWeiboActivity.this, OAuthV2AuthorizeWebView.class);
        intent.putExtra("oauth", oAuth);
        startActivityForResult(intent,1);   
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data)   {
        if (requestCode==1) {
            if (resultCode==OAuthV2AuthorizeWebView.RESULT_CODE)    {
                oAuth=(OAuthV2) data.getExtras().getSerializable("oauth");
                
                //调用API获取用户信息
                UserAPI userAPI=new UserAPI(OAuthConstants.OAUTH_VERSION_2_A);
                try {
                    String response=userAPI.info(oAuth, "json");//获取用户信息
                    ((TextView)findViewById(R.id.textView)).setText(response+"\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                userAPI.shutdownConnection();
                
            }
        }
    }
}