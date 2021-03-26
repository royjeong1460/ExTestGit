package com.lec.sts19_rest.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lec.sts19_rest.domain.WriteDAO;
import com.lec.sts19_rest.domain.WriteDTO;

// Service 단.
// 		JSP MVC model 2 의 Command 역활 비슷
// 			Controller -> Command -> DAO
// 		- Transaction 담당


// Spring
//	@Controller -> @Service -> DAO - > JdbcTemplate


@Service
public class BoardService {
	
	
	WriteDAO dao;
	
//	@Autowired
//	public void setDao(WriteDAO dao) {
//		this.dao = dao;
//	}
	
	// MyBatis
	private SqlSession sqlSession;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("SqlSession() 생성");
		this.sqlSession = sqlSession;
	}
	
	// 테스트 출력
	public BoardService() {
		super();
		System.out.println("BoardService() 생성");
	}
	
	public List<WriteDTO> list(){
		// MyBatis 가 만들어준 DAO
		dao = sqlSession.getMapper(WriteDAO.class);
		return dao.select();
	}
	
	public int write(WriteDTO dto) {
		dao = sqlSession.getMapper(WriteDAO.class);
		
		int result = dao.insert(dto);
		System.out.println("생성된 uid 는 " + dto.getUid());
		
		return result;
		
//		return dao.insert(dto.getSubject(), dto.getContent(), dto.getName());
	}
	
	// @transactional
	public List<WriteDTO> viewByUid(int uid){
		// *****사실, 트랜잭션은 여기서 발생해야 한다.
		// 1. 조회수 증가 : incViewCnt()
		// 2. 글 하나 읽어오기 : selectByUid()
		dao = sqlSession.getMapper(WriteDAO.class);
		dao.incViewCnt(uid);
		return dao.selectByUid(uid);
	}
	
	public List<WriteDTO> selectByUid(int uid){
		dao = sqlSession.getMapper(WriteDAO.class);
		return dao.selectByUid(uid);
	}
	
	public int update(WriteDTO dto) {
		dao = sqlSession.getMapper(WriteDAO.class);
//		return dao.update(dto);
		return dao.update(dto.getUid(),dto);
	}
	
	public int deleteByUid(int uid) {
		dao = sqlSession.getMapper(WriteDAO.class);
		return dao.deleteByUid(uid);
	}
}
