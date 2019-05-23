package com.cafe24.mysite.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.security.Auth;


// 이거 과제임 ㅠㅠ ............................
// 여기 접근하려면 권한을 받아야하는 데 ADMIN이어야함
//@Auth(role=Auth.Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping({"","/main"})
	public String main() {
		return "admin/main";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	
	
}
