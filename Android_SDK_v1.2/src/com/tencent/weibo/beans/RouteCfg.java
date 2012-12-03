package com.tencent.weibo.beans;

/**
 * 针对特定路由允许连接数的配置
 */
public class RouteCfg {
    private String host;
    private int port;
    private int maxConnetions;
    
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public int getMaxConnetions() {
        return maxConnetions;
    }
    public void setMaxConnetions(int maxConnetions) {
        this.maxConnetions = maxConnetions;
    }

}
