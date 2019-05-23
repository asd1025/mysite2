package com.cafe24.mysite.controller;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	//controller 가 처리하고 톰켓까지 안올라감..!! *****??????????????/
//	@ExceptionHandler(UserDaoException.class)
//	public String handlerUserDaoException() {
//		return "/error/500.jp";
//	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo
			,BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<ObjectError> list=result.getAllErrors() ;
			for(ObjectError error:list) {
				System.out.println(error);
			}
			model.addAllAttributes(result.getModel());
			return "/user/join";
		}
		userService.join(userVo);
		return "redirect:/user/joinSuccess";
	}

	@RequestMapping(value = "/joinSuccess", method = RequestMethod.GET)
	public String joinSuccess() {
		return "user/joinSuccess";
	}

	@RequestMapping(value = { "/login", "/loginform" }, method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(@RequestParam(value = "email", required = true, defaultValue = "") String email,
//			@RequestParam(value = "password", required = true, defaultValue = "") String password, HttpSession session,
//			Model model) {
//
//		UserVo authUser = userService.getUser(new UserVo(email, password));
//		if (authUser == null) {
//			model.addAttribute("result", "fail");
//			return "user/login";
//		}
//		
//		// session 처리
//		session.setAttribute("authUser", authUser);
//		return "redirect:/";
//	}

//	@RequestMapping(value = "/logout")
//	public String logout(HttpSession session) {
//		session.removeAttribute("authUser");
//		session.invalidate();
//		return "redirect:/";
//	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpSession session, @ModelAttribute UserVo userVo) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		userService.update(userVo);
		return "redirect:/";
	}
	
	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@AuthUser UserVo userVo,  Model model) {
		UserVo authUser = userService.getUser(userVo.getNo());
		model.addAttribute("vo", authUser);
		return "user/update";
	}
	
}
