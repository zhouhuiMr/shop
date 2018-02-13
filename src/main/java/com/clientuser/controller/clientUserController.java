package com.clientuser.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Object.clientUser;
import com.Object.resultEnum;
import com.Object.resultObject;
import com.clientuser.clientUserLoginAbstract;
import com.config.initConfig;

@Controller
@RequestMapping(path="/action/clientuser")
public class clientUserController {
	private final static Object codeLock = new Object();
	
	@Autowired
	private clientUserLoginAbstract userModel;
	
	@Autowired
	private HttpServletRequest req;
	
	@Autowired
	private HttpServletResponse res;
	
	
	@RequestMapping(path="/login",method=RequestMethod.GET)
	@ResponseBody
	public resultObject clientUserLoginByTelPass(
			@RequestParam("telphone") String telphone,
			@RequestParam("password") String password,
			@RequestParam("authcode") String authcode) {
		resultObject result = new resultObject();
		authcode = authcode.toLowerCase();
		String sessionCode = "";
		synchronized (codeLock) {
			sessionCode = (String) req.getSession().getAttribute(initConfig.picAuthCodeName);
			req.getSession().removeAttribute(initConfig.picAuthCodeName);
		}
		if(authcode.equals(sessionCode)) {
			clientUser clientUser = new clientUser();
			clientUser.setTelphone(telphone);
			clientUser.setPassword(password);
			result = userModel.clientUserLogin(clientUser,res);
		}else {
			result.setResult(resultEnum.CODEERROR);
			result.setData("");
		}
		return result;
	}
}
