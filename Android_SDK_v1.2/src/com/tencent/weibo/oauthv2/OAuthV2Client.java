package com.tencent.weibo.oauthv2;

import android.util.Log;

import com.tencent.weibo.constants.OAuthConstants;
import com.tencent.weibo.exceptions.OAuthClientException;
import com.tencent.weibo.utils.QHttpClient;
import com.tencent.weibo.utils.QStrOperate;

/**
 * 工具类 OAuth version 2 认证授权以及签名相关<br>
 * 如需自定制http管理器请使用 <pre>OAuthV2Client.setQHttpClient(QHttpClient qHttpClient)</pre> <br>
 * 为本工具类指定http管理器
 */
public class OAuthV2Client{

    private static String TAG="OAuthV2Client.class";
    
    private static QHttpClient Q_HTTP_CLIENT=new QHttpClient();

    private OAuthV2Client(){
    }
    
    /**
     * 使用Authorization code方式鉴权时，合成转向授权页面的url
     * 
     * @param oAuth
     * @return
     */
    public  static  String generateAuthorizationURL(OAuthV2 oAuth) {

        oAuth.setResponseType("code");
        String queryString = QStrOperate.getQueryString(oAuth.getAuthorizationParamsList());
        Log.i(TAG,"authorization queryString = "+queryString);
        
        String urlWithQueryString=OAuthConstants.OAUTH_V2_AUTHORIZE_URL + "?"+queryString;
        Log.i(TAG,"url with queryString = "+ urlWithQueryString);
        
        return urlWithQueryString;
    }
    
    /**
     * 使用Authorization code方式鉴权时，直接将授权码的参数记录到OAuth类中
     * @param authorizeCode
     * @param openid
     * @param openkey
     * @param oAuth
     * @return
     */
    public  static boolean setAuthorization(String authorizeCode,String openid,String openkey,OAuthV2 oAuth){
        if ((!QStrOperate.hasValue(authorizeCode)) ||
                (!QStrOperate.hasValue(openid)) ||
                (!QStrOperate.hasValue(openkey))) {
            return false;
        }
        oAuth.setAuthorizeCode(authorizeCode);
        oAuth.setOpenid(openid);
        oAuth.setOpenkey(openkey);
        return true;
    }
    
    /**
     * 使用Authorization code方式鉴权时，请求用户授权后，解析开放平台返回的参数是否包含授权码等信息
     * 
     * @param responseData 格式：code=CODE&openid=OPENID&openkey=OPENKEY
     * @param oAuth
     * @return
     */
     public  static boolean parseAuthorization(String responseData, OAuthV2 oAuth) {
         oAuth.setStatus(2);//假设出错
         if (!QStrOperate.hasValue(responseData)) {
            return false;
        }

        oAuth.setMsg(responseData);
        String[] tokenArray = responseData.split("&");
        
        Log.i(TAG, "parseToken response=>> tokenArray.length = "+tokenArray.length);
        
        if (tokenArray.length < 3) {
            return false;
        }

        String strAuthorizeCode = tokenArray[0];
        String strOpenid = tokenArray[1];
        String strOpenkey = tokenArray[2];

        String[] authorizeCode = strAuthorizeCode.split("=");
        if (authorizeCode.length < 2) {
            return false;
        }
        oAuth.setAuthorizeCode(authorizeCode[1]);

        String[] openid = strOpenid.split("=");
        if (openid.length < 2) {
            return false;
        }
        oAuth.setOpenid(openid[1]);
         
        String[] openkey = strOpenkey.split("=");
        if (openkey.length < 2) {
            return false;
        }
        oAuth.setOpenkey(openkey[1]);
        oAuth.setStatus(0);//没有出错
        return true;
    }
    
    /**
     * 使用Authorization code方式鉴权时，用授权码换取Access Token
     * 
     * @param oAuth
     * @return
     * @throws Exception
     */
    public  static boolean accessToken(OAuthV2 oAuth) throws Exception {
        if(null==Q_HTTP_CLIENT){
            throw new OAuthClientException("1001");
        }
        Log.i(TAG, "AuthorizeCode = "+oAuth.getAuthorizeCode()+
                "\nOpenid = "+oAuth.getOpenid()+ "\nOpenkey ="+oAuth.getOpenkey());
        
        String url = OAuthConstants.OAUTH_V2_GET_ACCESS_TOKEN_URL;

        String queryString = QStrOperate.getQueryString(oAuth.getAccessTokenByCodeParamsList());
        Log.i(TAG,"authorization queryString = "+queryString);
        
        String responseData = Q_HTTP_CLIENT.httpGet(url, queryString);
        Log.i(TAG,"authorization responseData = "+responseData);
        
        if (!parseAccessToken(responseData, oAuth)) {// Access Token 授权不通过
            oAuth.setStatus(3);
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * 使用Implicit grant方式鉴权时，合成转向授权页面的url
     * @param oAuth
     * @return 
     */
    public static String generateImplicitGrantUrl(OAuthV2 oAuth){
        oAuth.setResponseType("token");

      String queryString = QStrOperate.getQueryString(oAuth.getAuthorizationParamsList());
      Log.i(TAG,"authorization queryString = "+queryString);
      
      String urlWithQueryString=OAuthConstants.OAUTH_V2_AUTHORIZE_URL + "?"+queryString;
      Log.i(TAG,"url with queryString = "+ urlWithQueryString);
      
      return urlWithQueryString;
    }
    
    
    /**
     * 得到服务器返回的包含access token等的回应包后，解析存储到OAuth类中
     * 
     * @param responseData 格式：access_token=ACCESS_TOKEN&expires_in=60&name=NAME
     * @param oAuth
     * @return
     */
     public  static boolean parseAccessToken(String responseData, OAuthV2 oAuth){
        if (!QStrOperate.hasValue(responseData)) {
            return false;
        }

        oAuth.setMsg(responseData);
        String[] tokenArray = responseData.split("&");
        
        Log.i(TAG, "parseToken response=>> tokenArray.length = "+tokenArray.length);

        if (tokenArray.length < 2) {
            return false;
        }

        String strAccessToken = tokenArray[0];
        String strExpiresIn = tokenArray[1];

        String[] accessToken = strAccessToken.split("=");
        if (accessToken.length < 2) {
            return false;
        }
        oAuth.setAccessToken(accessToken[1]);
         
        String[] expiresIn = strExpiresIn.split("=");
        if (expiresIn.length < 2) {
            return false;
        }
        oAuth.setExpiresIn(expiresIn[1]);
        
        return true;
    }

    /**
     * 得到服务器返回的包含access token等的回应包后，解析存储到OAuth类中
     * 
     * @param responseData 格式：access_token=ACCESS_TOKEN&expires_in=60&openid= OPENID &openkey= OPENKEY
     * @param oAuth
     * @return
     */
     public  static boolean parseAccessTokenAndOpenId(String responseData, OAuthV2 oAuth){
         oAuth.setStatus(3);//假设出错
         
         if (!QStrOperate.hasValue(responseData)) {
            return false;
        }

        oAuth.setMsg(responseData);
        String[] tokenArray = responseData.split("&");
        
        Log.i(TAG, "parseToken response=>> tokenArray.length = "+tokenArray.length);

        if (tokenArray.length < 4) {
            return false;
        }

        String strAccessToken = tokenArray[0];
        String strExpiresIn = tokenArray[1];
        String  strOpenid = tokenArray[2];
        String  strOpenkey = tokenArray[3];

        String[] accessToken = strAccessToken.split("=");
        if (accessToken.length < 2) {
            return false;
        }
        oAuth.setAccessToken(accessToken[1]);
         
        String[] expiresIn = strExpiresIn.split("=");
        if (expiresIn.length < 2) {
            return false;
        }
        oAuth.setExpiresIn(expiresIn[1]);

        String[] openid = strOpenid.split("=");
        if (openid.length < 2) {
            return false;
        }
        oAuth.setOpenid(openid[1]);
        
        String[] openkey = strOpenkey.split("=");
        if (openkey.length < 2) {
            return false;
        }
        oAuth.setOpenkey(openkey[1]);
        oAuth.setStatus(0);
        return true;
    }

    public static QHttpClient getQHttpClient() {
        return Q_HTTP_CLIENT;
    }

    public static void setQHttpClient(QHttpClient qHttpClient) {
        Q_HTTP_CLIENT = qHttpClient;
    }


}
