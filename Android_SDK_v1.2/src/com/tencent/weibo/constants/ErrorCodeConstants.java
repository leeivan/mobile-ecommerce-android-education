package com.tencent.weibo.constants;

import java.util.HashMap;

/**
 * 本类针对 QweiboSDK， 设定了一系列的 errcode 以及对应的 errmsg
 */
public class ErrorCodeConstants{
    
    private static MyErrorCodeHashMap myErrorCodeHashMap =new MyErrorCodeHashMap();
    
    public static String getErrmsg(String errcode){
        return myErrorCodeHashMap.get(errcode);
    }
}

class MyErrorCodeHashMap extends HashMap<String,String>{
    private static final long serialVersionUID = 2427025312680000207L;
    public MyErrorCodeHashMap(){
        //TODO errcode尚未确定
        put("1", "connect out of time");
        
        
        //OAuthClient错误
        put("1001","qHttpClient not specified");
        
        //证书验证错误
        put("2001","Can not receive the certificates from server.");
        put("2002","The name on the security certificate is invalid or does not match  \"open.t.qq.com\".");
        
    }
}

