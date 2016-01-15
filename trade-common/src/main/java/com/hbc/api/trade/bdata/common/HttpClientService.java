package com.hbc.api.trade.bdata.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.exception.TradeSDKException;
import com.hbc.api.trade.bdata.exception.TradeSdkReturnCodeEnum;

@Component
public class HttpClientService {
	private Logger log = LoggerFactory.getLogger(HttpClientService.class);
	@Autowired
	ClientHttpRequestFactory clientHttpRequestFactory ;   
	public String transit(URI uri) throws IOException{
		ClientHttpRequest httpclient = clientHttpRequestFactory.createRequest(uri, HttpMethod.GET);
		ClientHttpResponse response = httpclient.execute();
		BufferedReader read = new BufferedReader(new InputStreamReader(response.getBody(),Charset.forName("UTF-8")));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while((line = read.readLine()) != null){
			sb.append(line);
		}
		
		read.close();
	
		log.debug(sb.toString());
		return sb.toString();
	}
	
	public String sendReq(String requrl) throws IOException{
		int windex = requrl.indexOf("?");
		if(windex>0){
			String parstr = requrl.substring(windex+1, requrl.length());
			String qstr = URIUtil.encodeQuery(parstr);
			
			requrl = requrl.substring(0, windex+1)+qstr;
		}
		log.info("Request URL: " + requrl);
		URI uri =  URI.create(requrl);
		long curtime = System.currentTimeMillis();
		ClientHttpRequest httpclient = clientHttpRequestFactory.createRequest(uri, HttpMethod.GET);
		ClientHttpResponse response = httpclient.execute();
		BufferedReader read = new BufferedReader(new InputStreamReader(response.getBody(),Charset.forName("UTF-8")));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while((line = read.readLine()) != null){
			sb.append(line);
		}
		
		read.close();
	
		log.info("cost ["+(System.currentTimeMillis()-curtime)+"] req  ["+requrl+"] rsp is ["+sb+"]");
		return sb.toString();
	}
	
	public String sendPostMapReq(String requrl,Map<String,String> reqPars) throws IOException{
		
		Set<String> keySet = reqPars.keySet();
		StringBuffer parstr = new StringBuffer();
		for(String keyStr : keySet){
			String keyValue = reqPars.get(keyStr);
			if(StringUtils.isEmpty(keyValue)) continue;
			String objstr = keyValue.toString();
			parstr.append(keyStr+"="+objstr+"&");
		}
		String qstr = URIUtil.encodeQuery(parstr.toString());
		requrl = requrl+"?"+qstr;
		log.info("Request URL: " + requrl);
		URI uri =  URI.create(requrl);
		long curtime = System.currentTimeMillis();
		ClientHttpRequest httpclient = clientHttpRequestFactory.createRequest(uri, HttpMethod.POST);
		ClientHttpResponse response = httpclient.execute();
		BufferedReader read = new BufferedReader(new InputStreamReader(response.getBody(),Charset.forName("UTF-8")));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while((line = read.readLine()) != null){
			sb.append(line);
		}
		
		read.close();
	
		log.info("cost ["+(System.currentTimeMillis()-curtime)+"] req  ["+requrl+"] rsp is ["+sb+"]");
		return sb.toString();
	}
	
	public String sendReqPost(String requrl) throws IOException{
		int windex = requrl.indexOf("?");
		if(windex>0){
			String parstr = requrl.substring(windex+1, requrl.length());
			String qstr = URIUtil.encodeQuery(parstr);
			
			requrl = requrl.substring(0, windex+1)+qstr;
		}
		log.info("Request URL: " + requrl);
		URI uri =  URI.create(requrl);
		long curtime = System.currentTimeMillis();
		ClientHttpRequest httpclient = clientHttpRequestFactory.createRequest(uri, HttpMethod.POST);
		ClientHttpResponse response = httpclient.execute();
		BufferedReader read = new BufferedReader(new InputStreamReader(response.getBody(),Charset.forName("UTF-8")));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while((line = read.readLine()) != null){
			sb.append(line);
		}
		
		read.close();
	
		log.info("cost ["+(System.currentTimeMillis()-curtime)+"] req  ["+requrl+"] rsp is ["+sb+"]");
		return sb.toString();
	}
	
	public String postJSON(String requrl, String jsonBody) throws IOException {
		int windex = requrl.indexOf("?");
		if(windex>0){
			String parstr = requrl.substring(windex+1, requrl.length());
			String qstr = URIUtil.encodeQuery(parstr);
			
			requrl = requrl.substring(0, windex+1)+qstr;
		}
		log.info("Request URL: " + requrl + ", RequestBody:" + jsonBody);
        if (jsonBody != null && !"".equals(jsonBody.trim())) {
            try {  
            	HttpPost method = new HttpPost(requrl);
                method.addHeader("Content-type","application/json;charset=UTF-8");  
                method.setEntity(new StringEntity(jsonBody, Charset.forName("UTF-8")));  
                @SuppressWarnings("deprecation")
				HttpClient httpClient = new DefaultHttpClient();
				HttpResponse response = httpClient .execute(method);  
                int statusCode = response.getStatusLine().getStatusCode();  
                if (statusCode == HttpStatus.SC_OK) {
                	return EntityUtils.toString(response.getEntity()); 
                }  
            } catch (IOException e) { 
            	log.error("", e);
            } finally {  
            }  
  
        }  
        return null;  
    }  
	
	public String sendReqPost(String requrl,String data) throws IOException{

		log.info("Request URL: " + requrl + "?"+data);
		long curtime = System.currentTimeMillis();
		
		HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(requrl);
        StringEntity entity = new StringEntity(data,Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        System.out.println("响应状态码：" + response.getStatusLine().getStatusCode());
        InputStream is = response.getEntity().getContent();
        
		BufferedReader read = new BufferedReader(new InputStreamReader(is,Charset.forName("UTF-8")));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while((line = read.readLine()) != null){
			sb.append(line);
		}
		read.close();
	
		log.info("cost ["+(System.currentTimeMillis()-curtime)+"] req  ["+requrl+"] rsp is ["+sb+"]");
		return sb.toString();
	}
	
	public String sendReqNoEncode(String requrl) {
		log.info("Request URL: " + requrl);
		URI uri =  URI.create(requrl);
		long curtime = System.currentTimeMillis();
		
		ClientHttpRequest httpclient = null;
		BufferedReader read = null;
		try {
			httpclient = clientHttpRequestFactory.createRequest(uri, HttpMethod.GET);
			ClientHttpResponse response = httpclient.execute();
			 read = new BufferedReader(new InputStreamReader(response.getBody(),Charset.forName("UTF-8")));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while((line = read.readLine()) != null){
				sb.append(line);
			}
			log.info("cost ["+(System.currentTimeMillis()-curtime)+"] req  ["+requrl+"] rsp is ["+sb+"]");
			return sb.toString();
		} catch (IOException e) {
			log.error("IOException: URL=" + uri);
			throw new TradeSDKException(TradeSdkReturnCodeEnum.HTTPCLIENT_NOT_VALID);
		} finally {
			try {
				if(read != null) read.close();
			} catch (IOException e) {
				log.error("BufferedReader无法关闭");
				throw new TradeSDKException(TradeSdkReturnCodeEnum.HTTPCLIENT_NOT_VALID);
			}
		}
	}
}
