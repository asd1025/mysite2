package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.GuestbookVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	public void writeContent(BoardVo vo) {
		boardDao.insert(vo);
	}
 

	public List<BoardVo> getList() {
		return boardDao.getList();
	}

}
