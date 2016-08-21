package com.codinggyd.RookiePalmSpaceServer.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;


/**
 * 
 * @author guoyading
 *
 * create at 2016年8月21日 下午8:18:46
 */
@Service
public class STSService{

	public static final String REGION_CN_HANGZHOU = "cn-hangzhou";
	public static final String STS_API_VERSION = "2015-04-01";
	protected AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn,
			String roleSessionName, String policy, ProtocolType protocolType, long durationSeconds) throws ClientException 
	{
		try {
			 
			IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
			DefaultAcsClient client = new DefaultAcsClient(profile);

			 
			final AssumeRoleRequest request = new AssumeRoleRequest();
			request.setVersion(STS_API_VERSION);
			request.setMethod(MethodType.POST);
			request.setProtocol(protocolType);

			request.setRoleArn(roleArn);
			request.setRoleSessionName(roleSessionName);
			request.setPolicy(policy);
			request.setDurationSeconds(durationSeconds);

		 
			final AssumeRoleResponse response = client.getAcsResponse(request);

			return response;
		} catch (ClientException e) {
			throw e;
		}
	}

	public static String ReadJson(InputStream is){
	 
        BufferedReader reader = null;
       
        StringBuffer data = new StringBuffer();
        //
        try {
            reader = new BufferedReader(new InputStreamReader(is));
          
            String temp = null;
            while((temp = reader.readLine()) != null){
                data.append(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
          
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }
  
	
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		InputStream configIs = request.getSession().getServletContext().getResourceAsStream("/sts/config.json");
		InputStream configIs = getClass().getResourceAsStream("/sts/config.json");
		String data = ReadJson(configIs);
		if (data.equals(""))
		{
			return response(request, response, "./config.json is empty or not found");
		}
		 JSONObject jsonObj = new JSONObject(data);
	 
	 
		String accessKeyId = jsonObj.getString("AccessKeyID");
		String accessKeySecret = jsonObj.getString("AccessKeySecret");
		
		 
		String roleArn = jsonObj.getString("RoleArn");
		long durationSeconds = jsonObj.getLong("TokenExpireTime");
//		String policy = ReadJson(request.getSession().getServletContext().getRealPath(jsonObj.getString("PolicyFile")));
		String policy = ReadJson(getClass().getResourceAsStream(jsonObj.getString("PolicyFile")));
		String roleSessionName = "alice-001";

		 
		ProtocolType protocolType = ProtocolType.HTTPS;

		try {
			final AssumeRoleResponse stsResponse = assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName,
					policy, protocolType, durationSeconds);
			
			Map<String, String> respMap = new LinkedHashMap<String, String>();
			respMap.put("status", "200");
            respMap.put("AccessKeyId", stsResponse.getCredentials().getAccessKeyId());
            respMap.put("AccessKeySecret", stsResponse.getCredentials().getAccessKeySecret());
            respMap.put("SecurityToken", stsResponse.getCredentials().getSecurityToken());
            respMap.put("Expiration", stsResponse.getCredentials().getExpiration());
               
            String ja1 = JSONObject.wrap(respMap).toString();
           return  response(request, response, ja1);
            
		} catch (ClientException e) {

			Map<String, String> respMap = new LinkedHashMap<String, String>();
			respMap.put("status", e.getErrCode());
            respMap.put("AccessKeyId", "");
            respMap.put("AccessKeySecret", "");
            respMap.put("SecurityToken", "");
            respMap.put("Expiration", "");         
            String ja1 = JSONObject.wrap(respMap).toString();
          return  response(request, response, ja1);
		}
		
	}
	private String response(HttpServletRequest request, HttpServletResponse response, String results) throws IOException {
		String callbackFunName = request.getParameter("callback");
		if (callbackFunName==null || callbackFunName.equalsIgnoreCase(""))
			return results;
		else
			return callbackFunName + "( "+results+" )";
	 
	}
}
