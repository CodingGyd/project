package com.gyd.rookiepalmspace.base.network.neverUse;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.gyd.rookiepalmspace.base.constant.Constant;

/**
 * 所有业务逻辑处理类的父类
 * @author gyd
 *
 * 2014-11-7
 */
public class AbstractNet<T> {
 
	
	/**
	 * 与服务器进行交互,获得返回的数据流信息
	 * @return
	 * @throws Exception 
	 */
	protected InputStream contactToServiceByPost(Map<String,Object> params,String path) throws Exception{
		//得到请求发送置服务器的数据
				StringBuilder data = new StringBuilder();
				 
				if(params != null){
					for(Entry<String,Object> entry:params.entrySet()){
						data .append(entry.getKey())
							.append("=")
							.append(entry.getValue())
							.append("&");
					}
				}
				data.deleteCharAt(data.length()-1);
				byte[] buffer = data.toString().getBytes();
				
				//向服务器发起连接
				System.out.println("本机:"+path);
				URL url = new URL(path);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				
				//设置请求方式为POST
				connection.setRequestMethod(Constant.POST);
				//设置请求参数
				connection.setRequestProperty(Constant.CONTENT_TYPE_NAME, Constant.CONTENT_TYPE_VALUE);
				connection.setRequestProperty(Constant.CONTENT_LENGTH, buffer.length+"");
				
				connection.setDoOutput(true);
				
				OutputStream outputStream = connection.getOutputStream();
				outputStream.write(buffer);
				
				//设置请求超时时间
				connection.setConnectTimeout(5000);
				if(connection.getResponseCode() == 200){
					InputStream is = connection.getInputStream();
					return is;
				}
				
				return null;
	}
	
	/**
	 * 通过doGet方式与服务器进行交互
	 * @param params
	 * @param path
	 * @return
	 * @throws Exception 
	 */
	protected InputStream contactServiceByGet(Map<String,Object> params,String path) throws Exception{
		//拼凑url以及请求参数
		
		if(params != null){
			StringBuilder data = new StringBuilder();
			for(Entry<String,Object> entry:params.entrySet()){
				data .append(entry.getKey())
					.append("=")
					.append(entry.getValue())
					.append("&");
			}
			data.deleteCharAt(data.length()-1);
			
			path = "?"+data.toString();
		}
		
		URL url = new URL(path);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(Constant.GET);
		connection.setConnectTimeout(5000);
		if(connection.getResponseCode() == 200){
			InputStream is = connection.getInputStream();
			return is;
		}
		
		return null;
		
	}
	
	/**
	 * 与服务器交互
	 * @param params 请求参数
	 * @param path 请求路径
	 * @return 数据集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected List<T> getInfoList(Map<String,Object> params,String path,JsonParseListInter jsonParse) throws Exception{
		
		InputStream is = contactToServiceByPost(params, path);
		if(is != null){
			List<T> data = (List<T>) jsonParse.parseToList(is);
			return data;
		}
		return null;
	}

	//-------------------------------
	
//	
//	/**
//	 * 与服务器交互,获得T类型的单个数据对象,用了泛型技术
//	 * @param params 请求参数
//	 * @param path 请求路径
//	 * @return T类型的单个对象
//	 * @throws Exception
//	 */
//	protected T getInfo(Map<String,Object> params,String path,Class<?> clazz) throws Exception{
//		
//		InputStream is = contactToServiceByPost(params, path);
//		if(is != null){
//			T data = parseObject(is, clazz);
//			return data;
//		}
//		return null;
//	}
//
//	
//	/**
//	 * 用了泛型技术,解析T类型的json字符串对象为一个T类型的对象
//	 * @param is
//	 * @param token 
//	 * @return 解析出来的单个T类型的对象
//	 */
//	protected static T parseObject(InputStream is,Class<?> clazz){
//		Gson gson = new Gson();
//		T data = gson.fromJson(
//				new InputStreamReader(is), clazz);
//		return data;
//	}
	
	
	
}
