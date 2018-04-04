package cn.gnetop.pde.foundation;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import cn.gnetop.pde.foundation.log.InterfaceThirdLog;
import net.sf.json.JSONObject;

public class HttpUtils {
	public static final String ENCODED = "utf-8";
	public static final String POST = "POST";
	public static final String HTTPSPOST = "HTTPSPOST";
	public static final String DELETE = "DELETE";
	public static final String PUT = "PUT";
	public static final String GET = "GET";

	protected static Logger logger = Logger.getLogger(HttpUtils.class);

	/**
	 * 重写X509TrustManager
	 */
	private static TrustManager myX509TrustManager = new X509TrustManager() {
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

		}
	};

	/**
	 * HttpClient GET请求
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url, Map<String, String> header) {
		String resStr = null;
		HttpClient htpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		if (MapUtils.isNotEmpty(header)) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				getMethod.setRequestHeader(key, value);
			}
		}
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			int statusCode = htpClient.executeMethod(getMethod);
			// logger.info(statusCode);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed: " + getMethod.getStatusLine());
				// return resStr;
			}
			byte[] responseBody = getMethod.getResponseBody();
			resStr = new String(responseBody, "utf-8");
		} catch (HttpException e) {
			logger.error("Please check your provided http address!"); // 发生致命的异常，可能是协议不对或者返回的内容有问题
		} catch (IOException e) {
			logger.error("Network anomaly"); // 发生网络异常
		} finally {
			getMethod.releaseConnection(); // 释放连接
		}
		return resStr;
	}

	/**
	 * HttpClient POST请求
	 * 
	 * @param url
	 * @param jsonObj
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String doPost(String url, Map<String, String> header, String jsonObj) {
		String resStr = null;
		HttpClient htpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.addRequestHeader("Content-Type", "application/json");
		if (MapUtils.isNotEmpty(header)) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				postMethod.setRequestHeader(key, value);
			}
		}
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, ENCODED);
		postMethod.setRequestBody(jsonObj);
		try {
			int statusCode = htpClient.executeMethod(postMethod);
			// logger.info(statusCode);
			if (statusCode != HttpStatus.SC_OK) {
				// post和put不能自动处理转发 301：永久重定向，告诉客户端以后应从新地址访问 302：Moved
				// Temporarily
				if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
					Header locationHeader = postMethod.getResponseHeader("location");
					String location = null;
					if (locationHeader != null) {
						location = locationHeader.getValue();
						logger.info("The page was redirected to :" + location);
					} else {
						logger.info("Location field value is null");
					}
				} else {
					logger.error("Method failed: " + postMethod.getStatusLine());
				}
				// return resStr;
			}
			byte[] responseBody = postMethod.getResponseBody();
			resStr = new String(responseBody, ENCODED);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return resStr;
	}

	/**
	 * HttpClient PUT请求
	 * 
	 * @param url
	 * @param jsonObj
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String doPut(String url, Map<String, String> header, String jsonObj) {
		String resStr = null;
		HttpClient htpClient = new HttpClient();
		PutMethod putMethod = new PutMethod(url);
		putMethod.addRequestHeader("Content-Type", "application/json");
		if (MapUtils.isNotEmpty(header)) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				putMethod.setRequestHeader(key, value);
			}
		}
		putMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, ENCODED);
		putMethod.setRequestBody(jsonObj);
		try {
			int statusCode = htpClient.executeMethod(putMethod);
			// logger.info(statusCode);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed: " + putMethod.getStatusLine());
				// return null;
			}
			byte[] responseBody = putMethod.getResponseBody();
			resStr = new String(responseBody, ENCODED);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			putMethod.releaseConnection();
		}
		return resStr;
	}

	/**
	 * HttpClient DELETE请求
	 * 
	 * @param url
	 * @return
	 */
	public static String doDelete(String url, Map<String, String> header) {
		String resStr = null;
		HttpClient htpClient = new HttpClient();
		DeleteMethod deleteMethod = new DeleteMethod(url);
		deleteMethod.addRequestHeader("Content-Type", "application/json");
		if (MapUtils.isNotEmpty(header)) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				deleteMethod.setRequestHeader(key, value);
			}
		}
		deleteMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, ENCODED);
		try {
			int statusCode = htpClient.executeMethod(deleteMethod);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed: " + deleteMethod.getStatusLine());
				// return null;
			}
			byte[] responseBody = deleteMethod.getResponseBody();
			resStr = new String(responseBody, ENCODED);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			deleteMethod.releaseConnection();
		}
		return resStr;
	}

	public static String doHttpsPost(String requestUrl, String plat, String jsonObject) {
		String uuid = UUID.randomUUID().toString();
		// 记录请求报文日志
		InterfaceThirdLog.log(uuid, requestUrl, HTTPSPOST, plat, "req", jsonObject);
		
		String result = doHttps(requestUrl, jsonObject, POST);
		
		// 记录响应报文日志
		InterfaceThirdLog.log(uuid, requestUrl, HTTPSPOST, plat, "rsp", result);
		return result;
	}

	/**
	 * 发送请求
	 * 
	 * @param url
	 * @param strings
	 * @return
	 */
	private static String doHttps(String url, String jsonObject, String method) {
		if (url.isEmpty()) {
			return null;
		}
		try {
			// 设置SSLContext
			SSLContext ssl = SSLContext.getInstance("SSL");
			ssl.init(null, new TrustManager[] { myX509TrustManager }, null);

			// 打开连接
			HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();
			// 设置套接工厂
			conn.setSSLSocketFactory(ssl.getSocketFactory());
			// 加入数据
			conn.setRequestMethod(method);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-type", "application/json");

			BufferedOutputStream buffOutStr = new BufferedOutputStream(conn.getOutputStream());
			if (StringUtils.isNotBlank(jsonObject)) {
				buffOutStr.write(jsonObject.getBytes());
			}
			buffOutStr.flush();
			buffOutStr.close();

			// 获取输入流
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();

		} catch (Exception e) {
			return null;
		}
	}

	public static String sendHttpRequest(String requestUrl, String method, String plat, Map<String, String> header,
			String jsonReq) {
		String uuid = CommonUtils.getUUID();
		String jsonResult = "";
		// 记录请求报文日志
		InterfaceThirdLog.log(uuid, requestUrl, method, plat, "req", jsonReq);
		System.out.println(jsonReq);
		if (HttpUtils.POST.equals(method)) {
			jsonResult = HttpUtils.doPost(requestUrl, header, jsonReq);
		} else if (HttpUtils.DELETE.equals(method)) {
			jsonResult = HttpUtils.doDelete(requestUrl, header);
		} else if (HttpUtils.PUT.equals(method)) {
			jsonResult = HttpUtils.doPut(requestUrl, header, jsonReq);
		} else if (HttpUtils.GET.equals(method)) {
			jsonResult = HttpUtils.doGet(requestUrl, header);
		} else {
			logger.error("method is wrong");
			// 记录响应报文日志
			InterfaceThirdLog.log(uuid, requestUrl, method, plat, "rsp", jsonResult);
			return null;
		}
		System.out.println(jsonResult);
		// 记录响应报文日志
		InterfaceThirdLog.log(uuid, requestUrl, method, plat, "rsp", jsonResult);

		return jsonResult;
	}

	public static String doGet(String url, String plat, Map<String, String> header, Map<String, String> paramMap) {
		StringBuffer paramsb = new StringBuffer();
		paramsb.append(url);
		if (MapUtils.isNotEmpty(paramMap)) {
			paramsb.append('?');
			for (Map.Entry<String, String> entry : paramMap.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				paramsb.append(key).append('=').append(value).append('&');
			}
			paramsb.substring(0, paramsb.length() - 1);
		}
		String requestUrl = paramsb.toString();
		return HttpUtils.sendHttpRequest(requestUrl, HttpUtils.GET, plat, header, null);
	}

	public static String doPost(String url, String plat, Map<String, String> header, String jsonBody) {
		return HttpUtils.sendHttpRequest(url, HttpUtils.POST, plat, header, jsonBody);
	}
}
