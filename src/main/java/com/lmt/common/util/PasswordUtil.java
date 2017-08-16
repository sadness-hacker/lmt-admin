package com.lmt.common.util;

/**
 * 
 * @author ducx
 * @date 2017-08-16
 * 密码加密工具类
 *
 */
public class PasswordUtil {
	
	private static final String salts = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-=[]\\;',./`~!@#$%^&*()_+{}|:\"<>?";
	
	private static final char [] arr = salts.toCharArray();
	/**
	 * 生成6位随机盐
	 * @return
	 */
	public static String generateSalt(){
		return generateSalt(6);
	}
	/**
	 * 生成n位随机盐
	 * @return
	 */
	public static String generateSalt(int n){
		String s = "";
		while(s.length() < n){
			int i = (int) (Math.random()*arr.length);
			s += arr[i];
		}
		return s;
	}
	
	/**
	 * 密码加密
	 * @param username
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String encode(String username,String password,String salt){
		password = MD5.md5(username) + salt + MD5.md5(password) + username + MD5.md5(salt);
		password = MD5.md5(salt + password + username);
		return password;
	}

	public static void main(String [] args){
		System.out.println(generateSalt());
		System.out.println(encode("ducx", "0809", "0809"));
	}
}
