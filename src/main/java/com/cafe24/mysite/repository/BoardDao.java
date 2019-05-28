package com.cafe24.mysite.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.dto.Paging;
import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;

	public boolean insert(BoardVo vo) {
		int count=sqlSession.insert("board.insert",vo);
		return 1==count;
	}

	public List<BoardVo> getList(Paging paging) {
 		List<BoardVo> list=sqlSession.selectList("board.getList",paging);
		return list;
	}

	public BoardVo get(int no) {
		return sqlSession.selectOne("board.getbyNo",no);
	}

	public void update(BoardVo boardVo) {
		sqlSession.update("board.update",boardVo);
	}

	public void insertReply(BoardVo vo) {
		sqlSession.update("board.updateOrderNo",vo);
		sqlSession.insert("board.insertReply",vo);
	}

	public void delete(BoardVo vo) {
		sqlSession.update("board.deleteUpdate",vo);
	}

	public int getTotalCount() {
		return sqlSession.selectOne("board.getTotalCount");
	}

}
