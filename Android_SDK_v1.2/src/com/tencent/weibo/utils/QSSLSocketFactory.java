package com.tencent.weibo.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.tencent.weibo.constants.ErrorCodeConstants;

public class QSSLSocketFactory implements SocketFactory,
		LayeredSocketFactory {

	private SSLContext sslcontext = null;

	private  SSLContext getEasySSLContext() throws IOException {
		try {
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, new TrustManager[] { new X509TrustManager(){
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    if(null==chain){
                        throw new CertificateException(ErrorCodeConstants.getErrmsg("2001"));
                    }
                    boolean check=false;
                    for(X509Certificate x509Certificate:chain){
                        if(x509Certificate.getSubjectDN().toString().startsWith("CN=open.t.qq.com,")){
                            check=true;
                            break;
                        }
                    }
                    if(check==false){
                        throw new CertificateException(ErrorCodeConstants.getErrmsg("2002"));
                    }
                }
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
			}}, null);
			return context;
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	private SSLContext getSSLContext() throws IOException {
		if (this.sslcontext == null) {
			this.sslcontext = getEasySSLContext();
		}
		return this.sslcontext;
	}

	public Socket connectSocket(Socket sock, String host, int port,
			InetAddress localAddress, int localPort, HttpParams params)
			throws IOException, UnknownHostException, ConnectTimeoutException {
		int connTimeout = HttpConnectionParams.getConnectionTimeout(params);
		int soTimeout = HttpConnectionParams.getSoTimeout(params);

		InetSocketAddress remoteAddress = new InetSocketAddress(host, port);
		SSLSocket sslsock = (SSLSocket) ((sock != null) ? sock : createSocket());

		if ((localAddress != null) || (localPort > 0)) {
			if (localPort < 0) {
				localPort = 0; 
			}
			InetSocketAddress isa = new InetSocketAddress(localAddress,
					localPort);
			sslsock.bind(isa);
		}

		sslsock.connect(remoteAddress, connTimeout);
		sslsock.setSoTimeout(soTimeout);
		return sslsock;

	}

	public Socket createSocket() throws IOException {
		return getSSLContext().getSocketFactory().createSocket();
	}

	public boolean isSecure(Socket socket) throws IllegalArgumentException {
		return true;
	}

	public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
            throws IOException, UnknownHostException {
    return getSSLContext().getSocketFactory().createSocket(socket, host, port, autoClose);
}

	public boolean equals(Object obj) {
		return ((obj != null) && obj.getClass().equals(
				QSSLSocketFactory.class));
	}

	public int hashCode() {
		return QSSLSocketFactory.class.hashCode();
	}

}
