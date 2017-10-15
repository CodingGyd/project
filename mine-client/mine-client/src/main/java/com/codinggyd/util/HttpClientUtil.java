package com.codinggyd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	private static ObjectMapper mapper = new ObjectMapper();

	public static String requestServer(String serviceId,Object...args) {
		List<Object> params = new ArrayList<>();
		
		if (null != args) {
			for (int i=0;i<args.length;i++) {
				params.add(args[i]);
			}
		}
		
		Map<String,Object> dataMap = new HashMap<>();
		dataMap.put("serviceId", serviceId);
		dataMap.put("params", params);
		
		String requestJson = null;
		try {
			requestJson = mapper.writeValueAsString(dataMap);
		} catch (JsonProcessingException e1) {
			logger.error("格式化请求参数出错,");
			return null; 
		}
		return HttpClientUtil.sendPost(SysConstant.SERVER_URL, requestJson);
	}
	/**
	 * 向指定 URL 发送POST方法的请求C_DEPT_MANAGER_LIST
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url,String content) {
		// PrintWriter out = null;
		BufferedReader in = null;
		String password = "2346781234568905"; 
		String result = "";
		try {
			byte[] zipdata = ZipUtils.gzip1(content);
			byte[] encryptData = AESUtils.encrypt(zipdata, password, 16); 
			// HttpsURLConnection
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			// 提交内容压缩
			conn.setRequestProperty("Encoding", "GZIP");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			OutputStream os = conn.getOutputStream();
			os.write(encryptData);
			os.flush();
			conn.connect();
			Map<String, List<String>> headerFields = conn.getHeaderFields();
			List<String> list = headerFields.get("Encoding");
			byte[] data = IOUtils.toByteArray(conn.getInputStream());
			new String(data);
			data = AESUtils.decrypt(data, password, 16);
			final List<String> _list = new ArrayList<String>(1);
			_list.add("GZIP");
			if (list!=null&&CollectionUtils.containsAny(list, _list)) {
				data = ZipUtils.gunzip(data);
			}
			result = new String(data);
		} catch (Exception e) {
			logger.debug("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}
