package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestBookDao;
	
	public List<GuestbookVo> getList() {
		return guestBookDao.getList();
	}

	public void writeContent(GuestbookVo vo) {
		guestBookDao.insert(vo);
	}

	public void deleteContent(GuestbookVo vo) {
		guestBookDao.delete(vo);
	}

}
