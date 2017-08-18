package com.lmt.admin.util;

import com.lmt.common.util.ConfigUtil;

/**
 * 
 * @author ducx
 * @date 2017-08-18
 * 全局变量
 *
 */
public class GlobalVar {

	/**
	 * 上传文件保存路径
	 */
	public static final String UPLOAD_PATH = ConfigUtil.get("system", "upload.path");
	
	/**
	 * 上传文件访问路径
	 */
	public static final String CDN_URL = ConfigUtil.get("system", "cdn.url");
	
}
