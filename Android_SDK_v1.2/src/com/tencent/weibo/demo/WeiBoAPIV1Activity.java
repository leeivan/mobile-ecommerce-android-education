package com.tencent.weibo.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.tencent.weibo.R;
import com.tencent.weibo.api.TAPI;
import com.tencent.weibo.api.UserAPI;
import com.tencent.weibo.constants.OAuthConstants;
import com.tencent.weibo.oauthv1.OAuthV1;

/**
 * 测试OAuth Version 1.0的API封装接口，本类中只演示三个代表性接口调用（分别采用Get方法发送消息，post方法发送消息和post方法发送文件）
 */
public class WeiBoAPIV1Activity extends Activity {

	private Button btnGetUsrInfo;
	private Button btnSendMsg;
	private Button btnSendMsgWithPic;
	private TextView textResponse;
	private OAuthV1 oAuthV1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 /*
         * 设置界面元素，并添加对各按钮的监听
         */
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weibo);
		Intent intent = this.getIntent();
		btnGetUsrInfo=(Button)findViewById(R.id.btnGetUsrInfo);
		btnSendMsg=(Button)findViewById(R.id.btnSendMsg);
		btnSendMsgWithPic=(Button)findViewById(R.id.btnSendMsgWithPic);
		
		textResponse=(TextView)findViewById(R.id.textResponse);
		
		OnClickListener listener= new OnClickListener(){
		    String response;
		    UserAPI userAPI;
		    TAPI tAPI;
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.btnGetUsrInfo:
                        userAPI=new UserAPI(OAuthConstants.OAUTH_VERSION_1);
                        try {
                            response=userAPI.info(oAuthV1, "json");//获取用户信息
                            textResponse.append(response+"\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        userAPI.shutdownConnection();
                        break;
                        
                    case R.id.btnSendMsg:
                        tAPI= new TAPI(OAuthConstants.OAUTH_VERSION_1);
                        try {
                            response=tAPI.add(oAuthV1, "json", "Android客户端文字微博1", "127.0.0.1");
                            textResponse.append(response+"\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        tAPI.shutdownConnection();
                        break;
                        
                    case R.id.btnSendMsgWithPic:
                        tAPI= new TAPI(OAuthConstants.OAUTH_VERSION_1);
                        try {
                            File fileDir=new File("/sdcard/qweibosdk2");
                            if(!fileDir.exists()) fileDir.mkdirs();
                            File file=new File("/sdcard/qweibosdk2/logo_QWeibo.jpg");
                            if(!file.exists()){
                                file.createNewFile();
                                InputStream inputStream=WeiBoAPIV1Activity.class.getResourceAsStream("/res/drawable-hdpi/logo_qweibo.jpg");
                                FileOutputStream fileOutputStream=new FileOutputStream(file);
                                byte[] buf=new byte[1024];
                                int ins;
                                while ((ins=inputStream.read(buf))!=-1) {
                                    fileOutputStream.write(buf,0,ins);
                                }
                                inputStream.close();
                                fileOutputStream.close();
                            }
                            String picPath="/sdcard/qweibosdk2/logo_QWeibo.jpg";
                            response=tAPI.addPic(oAuthV1,  "json",  "Android客户端带图的文字微博1", "127.0.0.1", picPath);
                            textResponse.append(response+"\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        tAPI.shutdownConnection();
                        break;
                }
            }
        };
		
		btnGetUsrInfo.setOnClickListener(listener);
		btnSendMsg.setOnClickListener(listener);
		btnSendMsgWithPic.setOnClickListener(listener);
		
		//接收用Intent传来的App信息及之前通过了Oauth鉴权的信息
		oAuthV1=(OAuthV1) intent.getExtras().getSerializable("oauth");
	}
	
    public void onBackPressed() {
        finish();
    }
    
}
