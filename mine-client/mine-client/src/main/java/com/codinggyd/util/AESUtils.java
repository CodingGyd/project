package com.codinggyd.util;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.codinggyd.log.Log;


public class AESUtils {

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @param keySize
	 *            密钥长度16,24,32
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(String content, String password, int keySize) throws Exception {
		SecretKeySpec key = new SecretKeySpec(ZeroPadding(password.getBytes("GBK"), keySize), "AES");
		Cipher cipher =  getCipher();//Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
		byte[] byteContent = content.getBytes("UTF-8");
		cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(byteContent);
		return result; // 加密
	}

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @param keySize
	 *            密钥长度16,24,32
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] content, String password, int keySize) throws Exception {
		SecretKeySpec key = new SecretKeySpec(ZeroPadding(password.getBytes("GBK"), keySize), "AES");
		Cipher cipher =  getCipher();//Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
		byte[] byteContent = content;// content.getBytes("UTF-8");
		cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(byteContent);
		return result; // 加密
	}

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @param keySize
	 *            密钥长度16,24,32
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] content, byte[] keys, int keySize) throws Exception {
		SecretKeySpec key = new SecretKeySpec(ZeroPadding(keys, keySize), "AES");
		Cipher cipher = getCipher();//Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
		byte[] byteContent = content;// content.getBytes("UTF-8");
		cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(byteContent);
		return result; // 加密
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @param keySize
	 *            密钥长度16,24,32
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws ServiceException
	 */
	public static byte[] decrypt(byte[] content, String password, int keySize) throws Exception {
		return decrypt(content, password.getBytes("GBK"), keySize);

	}

	static ThreadLocal<Cipher> threadLocal = new ThreadLocal<>();

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @param keySize
	 *            密钥长度16,24,32
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws ServiceException
	 */
	public static byte[] decrypt(byte[] content, byte[] keys, int keySize) throws Exception {
		SecretKeySpec key = new SecretKeySpec(ZeroPadding(keys, keySize), "AES");
//		Log.debug("创建key:[{}]", System.currentTimeMillis() - start);
		Cipher cipher = getCipher();//Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
//		Log.debug("初始化:[{}]", System.currentTimeMillis() - start);
		byte[] result = cipher.doFinal(content);
//		Log.debug("解密:[{}]", System.currentTimeMillis() - start);
		return result; // 加密
	}
	static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException{
		Cipher cipher=threadLocal.get();
		if(cipher==null){
			long start = System.currentTimeMillis();
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			threadLocal.set(cipher);
			Log.debug("创建密码器:[{}]", System.currentTimeMillis() - start);
		}
		return cipher;
	}
	public static byte[] ZeroPadding(byte[] in, Integer blockSize) {
		Integer copyLen = in.length;
		if (copyLen > blockSize) {
			copyLen = blockSize;
		}
		byte[] out = new byte[blockSize];
		System.arraycopy(in, 0, out, 0, copyLen);
		return out;
	}

}
