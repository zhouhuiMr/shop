package com.clientuser.model;

import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.Data.Mapper.clientUserDao;
import com.Object.clientUser;
import com.Object.resultEnum;
import com.Object.resultObject;
import com.clientuser.clientUserLoginAbstract;
import com.common.tool.JWTOperate;
import com.common.tool.SHA256Str;

@Service
public class clientUserByTelPassModel extends clientUserLoginAbstract{
	
	@Autowired
	private clientUserDao clientUserDao;
	
	/**
	 * @param String 用户手机号
	 * @param password 用户的密码
	 * @param HttpServletResponse 请求的返回
	 *  
	 * 用户使用手机号和密码登录
	 *  
	 * @return resultObject 返回json数据
	 * */
	public resultObject clientUserLogin(clientUser clientUser,HttpServletResponse res) {
		resultObject resultObject = new resultObject();
		String encodePassword = SHA256Str.encodeStrBySHA256(clientUser.getPassword());
		clientUser clientuser = clientUserDao.userLoginByTelPass(clientUser.getTelphone(), encodePassword);
		if(clientuser == null) {
			resultObject.setResult(resultEnum.LOGINERROR);
			resultObject.setData("");
		}else {
			HashMap<String, String> option = TokenOption(clientuser);
			String token = JWTOperate.getToken(option);
			setCookie(res, "token", token);
			setCookie(res, "tel", urlEncode(clientuser.getUsername()));
			setCookie(res, "localtime", localDateTime());
			resultObject.setResult(resultEnum.SUCCESS);
			resultObject.setData("");
		}
		return resultObject;
	}
}
