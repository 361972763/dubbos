package com.sojson.common.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class HttpUtils {

	public static String getData(String appid, String appkey, String url, Map<String, Object> params) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
		String timestamp = df.format(new Date());

		Set<?> set = params.entrySet();
		Iterator<?> iterator = set.iterator();
		StringBuilder sbu = new StringBuilder(url + "?");
		StringBuilder param = new StringBuilder();
		int count = 0;
		while (iterator.hasNext()) {
			if (count > 0) {
				sbu.append("&");
				param.append("&");
			}
			Object t = iterator.next();
			sbu.append(t);
			param.append(t);
			count++;
		}
		System.out.println("参数"+param.toString());
		// 构造请求对象
		ClientHttpRequest request;
		try {
			System.out.println("接口Url=" + sbu.toString());
			request = new SimpleClientHttpRequestFactory().createRequest(new URI(sbu.toString()), HttpMethod.GET);
			// 设置请求头
			ClientHttpResponse res = request.execute();
			InputStream is = res.getBody(); // 获得返回数据,注意这里是个流
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String str = "";
			String restr = "";
			while ((str = br.readLine()) != null) {
				restr = str;
			}
			System.out.println("return结果----------------------"+restr);
			return restr;
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return "";

	}
	
	public static String postDsata(String url, Map<String, Object> params) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
		String timestamp = df.format(new Date());

		Set<?> set = params.entrySet();
		Iterator<?> iterator = set.iterator();
		StringBuilder sbu = new StringBuilder(url + "?");
		StringBuilder param = new StringBuilder();
		int count = 0;
		while (iterator.hasNext()) {
			if (count > 0) {
				sbu.append("&");
				param.append("&");
			}
			Object t = iterator.next();
			sbu.append(t);
			param.append(t);
			count++;
		}
		System.out.println("参数"+param.toString());
		// 构造请求对象
		ClientHttpRequest request;
		try {
			System.out.println("接口Url=" + sbu.toString());
			request = new SimpleClientHttpRequestFactory().createRequest(new URI(sbu.toString()), HttpMethod.POST);
			// 设置请求头
			// 发送请求
			if (params.get("page")!= null && (Integer.valueOf(params.get("page")+"") )> 20) {
				System.out.println(">20页");
				return "";
			}
			ClientHttpResponse res = request.execute();
			InputStream is = res.getBody(); // 获得返回数据,注意这里是个流
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String str = "";
			String restr = "";
			while ((str = br.readLine()) != null) {
				restr = str;
			}
			System.out.println("return结果----------------------"+restr);
			return restr;
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return "";

	}
	
	public static String getDataForSafe(String appid, String appkey, String url, String id) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
		String timestamp = df.format(new Date());
		
		StringBuilder sbu = new StringBuilder(url+"/"+id);
		// 构造请求对象
		ClientHttpRequest request;
		try {
			System.out.println("接口Url=" + sbu.toString());
			request = new SimpleClientHttpRequestFactory().createRequest(new URI(URLEncoder.encode(sbu.toString())), HttpMethod.GET);
			// 设置请求头
			// 发送请求
			ClientHttpResponse res = request.execute();
			InputStream is = res.getBody(); // 获得返回数据,注意这里是个流
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String str = "";
			String restr = "";
			while ((str = br.readLine()) != null) {
				restr = str;
			}
			return restr;
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return "";

	}
	
	/**
	 * get请求平台接口(httpClient实现)
	 * @param appid
	 * @param appkey
	 * @param url
	 * @param params
	 * @return
	 */
	public static String getQueryString(String appid, String appkey, String url, Map<String, Object> params) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
		String timestamp = df.format(new Date());

		// 构造请求对象
		HttpClient httpClient = new HttpClient();
		httpClient.setConnectionTimeout(CONNECTION_TIMEOUT);
		httpClient.setTimeout(READ_DATA_TIMEOUT);
		GetMethod getMethod = new GetMethod(url);
		if (params != null && !params.isEmpty()) {
			NameValuePair[] data = new NameValuePair[params.size()];
			Iterator iter = params.entrySet().iterator();
			int i = 0;
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				data[i] = new NameValuePair((String) entry.getKey(), (String) entry.getValue());
				++i;
			}
			getMethod.setQueryString(data);
		}
		getMethod.setRequestHeader("Content-Type", "application/json");
		int statusCode;
		try {
			statusCode = httpClient.executeMethod(getMethod);
			// 读取内容
			InputStream resStream = getMethod.getResponseBodyAsStream();
			String charset = getMethod.getResponseCharSet();
			BufferedReader br = new BufferedReader(new InputStreamReader(resStream, charset));
			StringBuffer resBuffer = new StringBuffer();
			String resTemp = "";
			while ((resTemp = br.readLine()) != null) {
				resBuffer.append(resTemp);
			}
			br.close();
			if (statusCode != HttpStatus.SC_OK) {
				System.out.println(resBuffer.toString());
				return "{\"errCode\":"+statusCode+",\"errMsg\":\"Query Error\"}";
			}
			return resBuffer.toString();
		} catch (IOException e) {

		} finally {
			// 释放链接
			getMethod.releaseConnection();
		}
		return "";
	}

	/**
	 * post请求平台接口(参数形式,编码UTF-8)
	 * @param appid
	 * @param appkey
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postData(String appid, String appkey, String url, Map<String, Object> params) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
		String timestamp = df.format(new Date());
		// 构造请求对象
		HttpClient httpClient = new HttpClient();
		httpClient.setConnectionTimeout(CONNECTION_TIMEOUT);
		httpClient.setTimeout(READ_DATA_TIMEOUT);
		PostMethod postMethod = new UTF8PostMethod(url);
		if (params != null && !params.isEmpty()) {
			NameValuePair[] data = new NameValuePair[params.size()];
			Iterator iter = params.entrySet().iterator();
			int i = 0;
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				data[i] = new NameValuePair((String) entry.getKey(), (String) entry.getValue());
				++i;
			}
			postMethod.setRequestBody(data);
		}
		postMethod.setRequestHeader("Content-Type", "application/json");
		int statusCode;
		try {
			statusCode = httpClient.executeMethod(postMethod);
			// 读取内容
			InputStream resStream = postMethod.getResponseBodyAsStream();
			String charset = postMethod.getResponseCharSet();
			BufferedReader br = new BufferedReader(new InputStreamReader(resStream, charset));
			StringBuffer resBuffer = new StringBuffer();
			String resTemp = "";
			while ((resTemp = br.readLine()) != null) {
				resBuffer.append(resTemp);
			}
			br.close();
			if (statusCode != HttpStatus.SC_OK) {
				System.out.println(resBuffer.toString());
				return "{\"errCode\":"+statusCode+",\"errMsg\":\"Query Error\"}";
			}
			return resBuffer.toString();
		} catch (IOException e) {
			
		} finally {
			// 释放链接
			postMethod.releaseConnection();
		}
		return "";
	}
	
	

	// 连接超时时间
	private static final int CONNECTION_TIMEOUT = 30000;

	// 读数据超时时间
	private static final int READ_DATA_TIMEOUT = 30000;
	
	private static class UTF8PostMethod extends PostMethod {
		public UTF8PostMethod(String url) {
		 super(url);
		}

		@Override
		public String getRequestCharSet() {
		return "UTF-8";
		}
	}
}
