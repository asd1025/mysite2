package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@Autowired
	private GuestbookService guestBookService;
	
	@RequestMapping("")
	public String  getList(Model model) {
		model.addAttribute("list",guestBookService.getList());
		return "/guestbook/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String  insert(GuestbookVo vo) {
		System.out.println(vo);
		guestBookService.writeContent(vo);
		return "redirect:/guestbook";
	}
	@RequestMapping("/deleteform/{no}")
	public String  deleteform(Model model,@PathVariable("no") Long no) {
		model.addAttribute("no",no);
		return "/guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String  delete(GuestbookVo vo) {
		guestBookService.deleteContent(vo);
		return "redirect:/guestbook";
	}
	
}
