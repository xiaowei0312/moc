package com.sram.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.codec.digest.DigestUtils;

public class TokenGeneratorUtils {

	private static Map<String, Date> tokenStore = new ConcurrentHashMap<String, Date>();

	/**
	 * 客户端发送请求得到token.
	 * 
	 * @param userName
	 * @return
	 */
	public static String generatorToken(String userName) {
		Date time = new Date();
		try {
			byte[] b = (time + DigestUtils.md5Hex(userName)).getBytes("utf-8");
			String token = DigestUtils.md5Hex(b);
			tokenStore.put(token, time);// 存储这个时间点的token
			return token;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userName;
	}

	/**
	 * 服务器在接收到请求时验证token,并把刚才的Token设置为失效
	 * 
	 * @param token
	 * @return
	 */
	public static  boolean validateToken(String token) {
		if(null == token){
			return false;
		}
		
		if (tokenStore.containsKey(token)) {
			Date time = tokenStore.get(token);
			Date normal = new Date();
			//超过5分钟移除token
			if (normal.getTime() - time.getTime() > 5 * 60 * 1000) {
				// 日志进退时
				return false;
			}
			tokenStore.remove(token);
		} else {
			// 日志，没有权限
			return false;
		}
		return true;
	}
}
