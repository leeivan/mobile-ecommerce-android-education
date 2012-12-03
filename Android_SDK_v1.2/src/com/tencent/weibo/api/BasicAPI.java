package com.tencent.weibo.api;

import com.tencent.weibo.constants.APIConstants;
import com.tencent.weibo.constants.OAuthConstants;
import com.tencent.weibo.oauthv1.OAuthV1Request;
import com.tencent.weibo.oauthv2.OAuthV2Request;
import com.tencent.weibo.utils.QHttpClient;

/**
 * API类的通用部分
 */
public abstract class BasicAPI {
    
    protected RequestAPI requestAPI;
    protected String apiBaseUrl=null;

    public BasicAPI(String OAuthVersion){
        if (OAuthVersion == OAuthConstants.OAUTH_VERSION_1 ) {
            requestAPI = new OAuthV1Request();
            apiBaseUrl=APIConstants.API_V1_BASE_URL;
        }else if(OAuthVersion == OAuthConstants.OAUTH_VERSION_2_A){
            requestAPI = new OAuthV2Request();
            apiBaseUrl=APIConstants.API_V2_BASE_URL;
        }
    }
    
    public BasicAPI(String OAuthVersion, QHttpClient qHttpClient){
        if (OAuthVersion == OAuthConstants.OAUTH_VERSION_1  ) {
            requestAPI = new OAuthV1Request(qHttpClient);
            apiBaseUrl=APIConstants.API_V1_BASE_URL;
        }else if(OAuthVersion == OAuthConstants.OAUTH_VERSION_2_A){
            requestAPI = new OAuthV2Request(qHttpClient);
            apiBaseUrl=APIConstants.API_V2_BASE_URL;
        }
    }
    
    public void shutdownConnection(){
        requestAPI.shutdownConnection();
    }

    public String getAPIBaseUrl() {
        return apiBaseUrl;
    }

    public abstract  void setAPIBaseUrl(String apiBaseUrl);
    
}
