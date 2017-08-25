package com.sram.util;

import java.security.MessageDigest;
import sun.misc.BASE64Encoder;

/**
 * 用户的密码加解密
 * @author 赵小东
 * @date 2015-04-30 17:26
 *
 */
public class PasswordUtils {
	/**
	 * 密码加密,使用sha-256算法加密，进行5000加密
	 * @return
	 */
	public static String encodePassword(String password,String salt){
		String  mergePasswordAndSalt=password+"{"+salt+"}";
		String  strPassword="";
		try {
			MessageDigest digest = MessageDigest.getInstance("sha-256");
			byte[] hash = digest.digest(mergePasswordAndSalt.getBytes());
			 for(int i=1;i<5000;i++){
				 digest.update(hash);
				hash = digest.digest(mergePasswordAndSalt.getBytes());
			 }
			 strPassword=new BASE64Encoder().encode(hash);
		} catch (Exception e) {
			new Exception("密码加密失败。").printStackTrace();
		}
		return strPassword;
	}
}
