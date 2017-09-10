package com.codinggyd.util;

import java.io.BufferedReader;
 
import java.io.IOException;
 
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @Title: HttpClientUtil.java
 * @Package: com.codinggyd.util
 * @Description:
 *
 * @author: guoyd
 * @Date: 2017年8月27日 下午12:02:12
 *
 *        Copyright @ 2017 Corpration Name
 */
public class HttpClientUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	private static String CHART_SET = "UTF-8";
	
	public static String sendGet(String url) {
		// 4.x版本貌似HttpClient就成了一个接口了
		// 现在都用这个来创建客户端连接,相当于打开了一个浏览器
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
	                .setConnectTimeout(10000)   //设置连接超时时间
	                .setConnectionRequestTimeout(20000) // 设置请求超时时间
	                .setSocketTimeout(5000)
	                .setRedirectsEnabled(true)//默认允许自动重定向
	                .build();
		// 以前的名字叫做GetMethod,现在改名为HttpGet
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		// 创建一个返回信息的对象
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			try {
				// 打印返回状态
				logger.debug("request url : {}", response.getStatusLine());
				// 实体
				HttpEntity httpEntity = response.getEntity();
				int code = response.getStatusLine().getStatusCode();
				logger.debug("request code: {}",code);
				if (code == 200) {
					if (httpEntity != null) {
						return EntityUtils.toString(httpEntity);
					} 
				}  
				return null;
			} catch (Exception e) {
				logger.error("response error ! {}", e);
			} finally {
				response.close();
			}
		} catch (IOException e) {
			logger.error("request error ! {}", e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				logger.error("httpClient close error ! {}", e);
			}
		}
		return null;
	}
	
	
	public static String sendPost(String url,String data) {
		// 4.x版本貌似HttpClient就成了一个接口了
		// 现在都用这个来创建客户端连接,相当于打开了一个浏览器
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
	                .setConnectTimeout(10000)   //设置连接超时时间
	                .setConnectionRequestTimeout(20000) // 设置请求超时时间
	                .setSocketTimeout(50000)
	                
	                .setRedirectsEnabled(true)//默认允许自动重定向
	                .build();
		HttpPost  httpPost = new HttpPost (url);
		httpPost.setConfig(requestConfig);
		httpPost.addHeader("Accept-Encoding", "identity");  //告诉服务器不要对数据进行压缩
		httpPost.addHeader("Content-type","application/json; charset="+CHART_SET);  
		httpPost.setHeader("Accept", "application/json");  

		//封装数据
		httpPost.setEntity(new StringEntity(data, Charset.forName(CHART_SET)));  
		// 创建一个返回信息的对象
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			try {
				// 打印返回状态
				logger.debug("request url : {}", response.getStatusLine());
				// 实体
				
				HttpEntity httpEntity = response.getEntity();
				int code = response.getStatusLine().getStatusCode();
				if (code == 200) {
					if (httpEntity != null) {
						long len = httpEntity.getContentLength();
						System.out.println("httpentity 长度："+len);
						 
//						if (len != -1 && len < 2048) {
							String resul = EntityUtils.toString(httpEntity);
							System.out.println(" EntityUtils.toString 长度："+resul.length());

//							} else {
								
//								InputStream is = httpEntity.getContent();
//								ByteArrayOutputStream result = new ByteArrayOutputStream();
//								byte[] buffer = new byte[1024];
//								int length;
//								while ((length = is.read(buffer)) != -1) {
//								    result.write(buffer, 0, length);
//								}
//								return result.toString("UTF-8");

//							}
					} 
				}  
				return null;
			} catch (Exception e) {
				logger.error("response error ! {}", e);
			} finally {
				response.close();
			}
		} catch (IOException e) {
			logger.error("request error ! {}", e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				logger.error("httpClient close error ! {}", e);
			}
		}
		return null;
	}
	
	
	
	   /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost2(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Accept-Encoding","identity");  
            conn.setRequestProperty("Content-type","application/json; charset="+CHART_SET);  
            conn.setRequestProperty("Accept", "application/json");  
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
        	logger.error("访问出错,{}",e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
  /*  public static void main(String[] args) {
		String requestJson = "{\"ServiceId\":\"MINE_LIST_LEARN_SITE\",\"Params\":[]}";
		String responseData = HttpClientUtil.sendPost2("http://180.76.134.57:8080/mine-server/api/func/", requestJson);
		System.out.println(responseData);
	}*/

}
