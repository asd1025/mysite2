package com.cafe24.mysite.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mystie.dto.JSONResult;

@Controller("userAPIController")
@RequestMapping("/user/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public JSONResult checkEmail
	(@RequestParam(value="email",required = true, defaultValue = "")String email) {
	Boolean exist=	userService.existEmail(email);
	return   JSONResult.success(exist);
	
//	Map<String,Object> map=new HashMap<String, Object>();
//	map.put("result", "success");
//	map.put("data", exist);
	//map.put("message", "......");
	
	}
}
