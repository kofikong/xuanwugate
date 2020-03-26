package com.xuanwugate.rpc.http;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
 
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.xuanwugate.rpc.IProxy;
import com.xuanwugate.rpc.IRequestParams;
import com.xuanwugate.rpc.RPCProxyResponse;

/**
 * HttpProxy
 */
public class HttpProxy implements IProxy {
    private static final class DefaultTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
 
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
 
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
 
    private static HttpsURLConnection getHttpsURLConnection(IRequestParams params, String method) throws IOException {
        String uri = params.getUri();
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SSLSocketFactory ssf = ctx.getSocketFactory();
 
        URL url = new URL(uri);
        HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
        httpsConn.setSSLSocketFactory(ssf);
        Map<String,String> headers = params.getHeaders();
        for (Entry<String, String> entry : headers.entrySet()) {
            httpsConn.setRequestProperty(entry.getKey(),entry.getValue());
        }
        // httpsConn.setRequestProperty("Content-Type","text/plain;charset=UTF-8");
        // String encoding = DatatypeConverter.printBase64Binary("blockdaemon:blockdaemon".getBytes("UTF-8"));
        // httpsConn.setRequestProperty("Authorization", "Basic " + encoding);
        // httpsConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        httpsConn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
        httpsConn.setRequestMethod(method);
        httpsConn.setDoInput(true);
        httpsConn.setDoOutput(true);
        return httpsConn;
    }
 
    private static HttpURLConnection getHttpURLConnection(IRequestParams params, String method) throws IOException {
        String uri = params.getUri();
        URL url = new URL(uri);
        HttpURLConnection httpsConn = (HttpURLConnection) url.openConnection();
        // httpsConn.setSSLSocketFactory(ssf);
        Map<String,String> headers = params.getHeaders();
        for (Entry<String, String> entry : headers.entrySet()) {
            httpsConn.setRequestProperty(entry.getKey(),entry.getValue());
        }
        // httpsConn.setRequestProperty("Content-Type","text/plain;charset=UTF-8");
        // String encoding = DatatypeConverter.printBase64Binary("blockdaemon:blockdaemon".getBytes("UTF-8"));
        // httpsConn.setRequestProperty("Authorization", "Basic " + encoding);
        // httpsConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        
        httpsConn.setRequestMethod(method);
        httpsConn.setDoInput(true);
        httpsConn.setDoOutput(true);
        return httpsConn;
    }

    private static byte[] getBytesFromStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] kb = new byte[1024];
        int len;
        while ((len = is.read(kb)) != -1) {
            baos.write(kb, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        baos.close();
        is.close();
        return bytes;
    }
 
    private static void setBytesToStream(OutputStream os, byte[] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        byte[] kb = new byte[1024];
        int len;
        while ((len = bais.read(kb)) != -1) {
            os.write(kb, 0, len);
        }
        os.flush();
        os.close();
        bais.close();
    }
 
    // public static String doGet(IRequestParams params) throws IOException {
    //     HttpsURLConnection httpsConn = getHttpsURLConnection(params, "GET");
    //     byte[] bytes = getBytesFromStream(httpsConn.getInputStream());
    //     return new String(bytes);
    // }
 
    public static RPCProxyResponse doPost(IRequestParams params) {
        String data = params.getBody();
        int responseCode = 0;
        byte[] bytes = null;
        HttpURLConnection conn = null;

        try{
            if(params.getUri().startsWith("https")){
                HttpsURLConnection httpsConn = getHttpsURLConnection(params, "POST");
                conn = httpsConn;
            }
            else{
                HttpURLConnection httpsConn = getHttpURLConnection(params, "POST");
                conn = httpsConn;
            }
            
            setBytesToStream(conn.getOutputStream(), data.getBytes());
            responseCode = conn.getResponseCode();

            if(responseCode >=100 && responseCode < 400){
                bytes = getBytesFromStream(conn.getInputStream());
            }
            else{
                bytes = getBytesFromStream(conn.getErrorStream());	
            }
        }catch( IOException err){
            try {
                responseCode = conn.getResponseCode();	
                bytes = getBytesFromStream(conn.getErrorStream());	
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        return RPCProxyResponse.create(responseCode, new String(bytes));
    }

	@Override
	public RPCProxyResponse run(IRequestParams params){
		return doPost(params);
	}
}