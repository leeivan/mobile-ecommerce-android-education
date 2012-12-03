package com.tencent.weibo.webview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.tencent.weibo.oauthv2.OAuthV2;
import com.tencent.weibo.oauthv2.OAuthV2Client;

/**
 * 使用Webview显示OAuth Version 2.a ImplicitGrant方式授权的页面<br>
 * (移动终端不建议使用Authorize code grant方式授权）<br>
 * <p>本类使用方法：</p>
 * <li>需要调用本类的地方请添加如下代码
 * <pre>
 * //请将OAuthV2Activity改为所在类的类名
 * Intent intent = new Intent(OAuthV2Activity.this, OAuthV2AuthorizeWebView.class);   
 * intent.putExtra("oauth", oAuth);  //oAuth为OAuthV2类的实例，存放授权相关信息
 * startActivityForResult(intent, myRrequestCode);  //请设置合适的requsetCode
 * </pre>
 * <li>重写接收回调信息的方法
 * <pre>
 * if (requestCode==myRrequestCode) {  //对应之前设置的的myRequsetCode
 *     if (resultCode==OAuthV2AuthorizeWebView.RESULT_CODE) {
 *         //取得返回的OAuthV2类实例oAuth
 *         oAuth=(OAuthV2) data.getExtras().getSerializable("oauth");
 *     }
 * }
 * <pre>
 * @see android.app.Activity#onActivityResult(int requestCode, int resultCode,  Intent data)
 */
public class OAuthV2AuthorizeWebView extends Activity {
    public final static int RESULT_CODE = 2;
    private static final String TAG = "OAuthV2AuthorizeWebView";
    private OAuthV2 oAuth;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        WebView webView = new WebView(this);
        linearLayout.addView(webView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        setContentView(linearLayout);
        Intent intent = this.getIntent();
        oAuth = (OAuthV2) intent.getExtras().getSerializable("oauth");
        String urlStr = OAuthV2Client.generateImplicitGrantUrl(oAuth);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webView.requestFocus();
        webView.loadUrl(urlStr);
        System.out.println(urlStr.toString());
        Log.i(TAG, "WebView Starting....");
        WebViewClient client = new WebViewClient() {
            /**
             * 回调方法，当页面开始加载时执行
             */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.i(TAG, "WebView onPageStarted...");
                Log.i(TAG, "URL = " + url);
                if (url.indexOf("access_token=") != -1) {
                    int start=url.indexOf("access_token=");
                    String responseData=url.substring(start);
                    OAuthV2Client.parseAccessTokenAndOpenId(responseData, oAuth);
                    Intent intent = new Intent();
                    intent.putExtra("oauth", oAuth);
                    setResult(RESULT_CODE, intent);
                    view.destroyDrawingCache();
                    view.destroy();
                    finish();
                }
                super.onPageStarted(view, url, favicon);
            }

            /*
             * TODO Android2.2及以上版本才能使用该方法 
             * 目前https://open.t.qq.com中存在http资源会引起sslerror，待网站修正后可去掉该方法
             */
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if ((null != view.getUrl()) && (view.getUrl().startsWith("https://open.t.qq.com"))) {
                    handler.proceed();// 接受证书
                } else {
                    handler.cancel(); // 默认的处理方式，WebView变成空白页
                }
                // handleMessage(Message msg); 其他处理
            }
        };
        webView.setWebViewClient(client);
    }

}
