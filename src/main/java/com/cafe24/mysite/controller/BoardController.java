package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.dto.Paging;
import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.Auth.Role;

@Controller
@RequestMapping("/board")
public class BoardController {
//	private Paging paging;
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session,Model model,@RequestParam(value="no")int no) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser==null) return "redirect:/board";
		BoardVo vo=boardService.view(no);
		model.addAttribute("vo",vo);
		return "board/modify";
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute BoardVo boardVo, Model model) {
		boardService.update(boardVo);
		return "redirect:/board/view?no="+boardVo.getNo();
	}
	
	@RequestMapping("/view")
	public String view(@RequestParam int no,Model model ) {
		BoardVo vo=boardService.view(no);
		model.addAttribute("vo",vo);
		return "board/view";
	}
	@RequestMapping("/delete")
	public String delete(@ModelAttribute BoardVo boardVo ) {
		boardService.delete(boardVo);
		return "redirect:/board";
	}
	
	
	// 권한은 접근 권한
	@Auth(role=Role.USER)
	@RequestMapping("/write")
	public String write(@ModelAttribute BoardVo boardVo,Model model) {
//		UserVo authUser= (UserVo) session.getAttribute("authUser");
//		if(authUser==null|| boardVo==null) {
//			return "redirect:/board";
//		}
		model.addAttribute("vo",boardVo);
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
			boardService.writeReplyContent(vo);
		}
		return "redirect:/board";
	}
	@RequestMapping("")
	public String  getList(Model model,@ModelAttribute Paging paging
			,@RequestParam(value="kwd", defaultValue="")String kwd) {
		model.addAttribute("list",boardService.getList(paging,kwd));
 		model.addAttribute("paging",boardService.getPaging());
		return "/board/list";
	}
	
	
}
