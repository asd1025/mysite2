package com.cafe24.mysite.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

	public List<BoardVo> getList() {
		List<BoardVo> list=sqlSession.selectList("board.getList");
		return list;
	}

}
