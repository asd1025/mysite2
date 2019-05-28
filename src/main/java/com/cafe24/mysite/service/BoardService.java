package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.dto.Paging;
import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.GuestbookVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	private Paging paging;
	
	
	public void writeContent(BoardVo vo) {
		boardDao.insert(vo);
	}
	 
	
	public Paging getPaging() {
		return paging;
	}
	public Paging initialPaging() {
		return new Paging(boardDao.getTotalCount());
	}
	

	public List<BoardVo> getList(Paging page,String kwd) {
		 paging=initialPaging();
		 paging.setPrevGroupNo(page.getPrevGroupNo());
		 paging.setPageNo(page.getPageNo());
			System.out.println(paging);
  		return boardDao.getList(paging);
	}

	public BoardVo view(int no) {
		return boardDao.get(no);
	}


	public void update(BoardVo vo) {
		  boardDao.update(vo);
	}


	public void writeReplyContent(BoardVo vo) {
		boardDao.insertReply(vo);
	}


	public void delete(BoardVo vo) {
		//삭제 처리 
		boardDao.delete(vo);
		
	}

}
