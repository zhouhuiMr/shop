package com.clientuser.model;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Data.Mapper.clientUserDao;
import com.Object.clientUser;
import com.Object.resultEnum;
import com.Object.resultObject;
import com.clientuser.clientUserLoginAbstract;
import com.common.tool.JWTOperate;

@Service
public class clientUserByTelCodeModel extends clientUserLoginAbstract{
	
	@Autowired
	private clientUserDao userDao;

	@Override
	public resultObject clientUserLogin(clientUser clientUser, HttpServletResponse res) {
		resultObject resultObject = new resultObject();
		String tel = clientUser.getTelphone();
		clientUser user = userDao.userLoginByTelCode(tel);
		if(user == null) {
			resultObject.setResult(resultEnum.LOGINERROR);
			resultObject.setData("");
		}else {
			HashMap<String, String> option = TokenOption(user);
			String token = JWTOperate.getToken(option);
			setCookie(res, "token", token);
			setCookie(res, "tel", urlEncode(user.getUsername()));
			setCookie(res, "localtime", localDateTime());
			resultObject.setResult(resultEnum.SUCCESS);
			resultObject.setData("");
		}
		return resultObject;
	}
}
