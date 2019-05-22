package com.rongke.web.ans.config;

import java.util.ResourceBundle;

/**
 * 
 * @author xiaohao@fuiou.com
 *
 */
public class SysReader
{
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("sys/key");
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	public  static String getString(String param)
	{
		return RESOURCE_BUNDLE.getString(param);
	}
	
	public static int getInt(String param)
	{
		return Integer.valueOf(RESOURCE_BUNDLE.getString(param));
	}
}
