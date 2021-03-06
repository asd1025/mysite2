package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(UserVo vo) {
		int count=sqlSession.insert("user.insert",vo);
		return 1==count;
	}
	 
	public   UserVo  get (Long no) {
		return sqlSession.selectOne("user.getByNo",no);
	}
	public   UserVo  get (String email) {
		return sqlSession.selectOne("user.getByEmail",email);
	}
	
	public   UserVo  get (String email, String password) throws UserDaoException  {
	  Map<String, String> map=new HashMap<String, String>();
	  map.put("email", email);
	  map.put("password", password);
	return	sqlSession.selectOne("user.getByEmailAndPassword",map);
	}
	public void update(UserVo userVo) {
		sqlSession.update("user.update",userVo);
	}
}
