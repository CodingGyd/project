package com.codinggyd.util;


import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codinggyd.utils.AESUtils;
import com.codinggyd.utils.ZipUtils;


public class FuncHttpUtils {
	private static final Logger logger = LoggerFactory.getLogger(FuncHttpUtils.class);

	public static void response(HttpServletRequest request, HttpServletResponse response, byte[] data, byte[] keys)
			throws Exception {
		OutputStream output = null;
		byte[] result = ArrayUtils.EMPTY_BYTE_ARRAY;
		try {
			output = response.getOutputStream();
			byte[] resultData = null;
			// if(resultData.length>1024){
			resultData = gzip(request, response, data);
			// }
			result = encrypt(request, response, resultData, keys);
			response.setStatus(200);
			response.setContentType("application/json");
//			Log.debug(logger, "[{},{},{}]", data.length,resultData.length,result.length);
//			response.setContentLengthLong(result.length);
			output.write(result);
			output.flush();
		} catch (IOException e) {
			logger.error("客户端终止响应:[{}]", e.getMessage());
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
			}
		}
	}

	static byte[] encrypt(HttpServletRequest request, HttpServletResponse response, byte[] data, byte[] keys)
			throws Exception {
		byte[] result = ArrayUtils.EMPTY_BYTE_ARRAY;
		result = AESUtils.encrypt(data, keys, 16);
		return result;
	}

	private static byte[] gzip(HttpServletRequest request, HttpServletResponse response, byte[] data)
			throws IOException {
		response.setHeader("Encoding", "GZIP");
		data = ZipUtils.gzip(data);
		return data;
	}
 
}
