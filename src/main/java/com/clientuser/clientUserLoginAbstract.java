package com.clientuser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.Object.clientUser;
import com.Object.resultObject;
import com.config.initConfig;

public abstract class clientUserLoginAbstract {
	
	public abstract resultObject clientUserLogin(clientUser clientUser,HttpServletResponse res);
	
	/**
	 * @param clientUser 用户的信息
	 * 
	 * 生成token字符串
	 * 
	 * @return HashMap<String, String> 生成token的参数
	 * */
	protected HashMap<String, String> TokenOption(clientUser user){
		HashMap<String, String> option = new HashMap<String, String>();
		option.put("clientID", user.getId()+"");
		option.put("telphone", user.getTelphone());
		option.put("loginStart", localDateTime());
		option.put("expires", initConfig.USERLOGINEXPIRES);
		return option;
	}
	
	/**
	 * @param HttpServletResponse 请求的返回值
	 * @param String cookie的名称
	 * @param String cookie的值
	 * 
	 * 将有效数据添加到请求的返回中
	 * 
	 * */
	protected void setCookie(HttpServletResponse res,String name,String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		res.addCookie(cookie);
	}
	
	/**
	 * @param String
	 * 
	 * 将字符串进行URLEncode编码
	 * 
	 * @return String
	 * */
	protected String urlEncode(String str) {
		String encodeStr = "";
		try {
			encodeStr =  URLEncoder.encode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeStr;
	}
	
	/**
	 * @return 返回指定格式的时间
	 * */
	protected String localDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm:ss");
		return formatter.format(LocalDateTime.now());
	}
}
