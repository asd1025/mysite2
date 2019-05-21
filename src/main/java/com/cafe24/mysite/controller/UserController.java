package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

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
	public String join() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "email", required = true, defaultValue = "") String email,
			@RequestParam(value = "password", required = true, defaultValue = "") String password, HttpSession session,
			Model model) {

		UserVo authUser = userService.getUser(new UserVo(email, password));
		if (authUser == null) {
			model.addAttribute("result", "fail");
			return "user/login";
		}
		
		// session 처리
		session.setAttribute("authUser", authUser);
		return "redirect:/";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(UserVo userVo) {
		userService.update(userVo);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser==null) return "redirect:/";
		model.addAttribute("vo", authUser);
		return "user/update";
	}
	
}
