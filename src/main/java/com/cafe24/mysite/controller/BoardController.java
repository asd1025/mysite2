package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	 
	@RequestMapping("/write")
	public String write(HttpSession session) {
		UserVo authUser= (UserVo) session.getAttribute("authUser");
		if(authUser==null ) {
			return "redirect:/board";
		}
		return "board/write";
	}
	@RequestMapping(value="/write",method = RequestMethod.POST)
	public String write(HttpSession session,BoardVo vo) {
		UserVo authUser= (UserVo) session.getAttribute("authUser");
		if(authUser==null ) {
			return "redirect:/board";
		}
		if(vo.getNo()==null) {// 원래 글
			boardService.writeContent(vo);
		}else { // 답글
			
			
		}
		return "redirect:/board";
	}
	@RequestMapping("")
	public String  getList(Model model) {
		model.addAttribute("list",boardService.getList());
		return "/board/list";
	}
	
	
}
