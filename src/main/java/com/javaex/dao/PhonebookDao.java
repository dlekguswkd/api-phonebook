package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository  //컨트롤러에 필드에 넣은거 사용을 위해 넣어주기 저장소의 의미
public class PhonebookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 필드
//	private Connection conn = null;
//	private PreparedStatement pstmt = null;
//	private ResultSet rs = null;
//
//	private String driver = "com.mysql.cj.jdbc.Driver";
//	private String url = "jdbc:mysql://localhost:3306/phonebook_db";
//	private String id = "phonebook";
//	private String pw = "phonebook";
//	
	//생성자
	//기본생성자 사용(그래서 생략)  
	
	//메소드 gs
	//필드값을 외부에서 사용하면 안됨(그래서 생략)
	
	//메소드 일반
	// DB연결 메소드
//	private void getConnection() {
//		try {
//			// 1. JDBC 드라이버 (Oracle) 로딩
//			Class.forName(driver);
//
//			// 2. Connection 얻어오기
//			conn = DriverManager.getConnection(url, id, pw);
//
//		} catch (ClassNotFoundException e) {
//			System.out.println("error: 드라이버 로딩 실패 - " + e);
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//	}

	// 자원정리 메소드
//	private void close() {
//		// 5. 자원정리
//		try {
//			if (rs != null) {
//				rs.close();
//			}
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (conn != null) {
//				conn.close();
//			}
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//	}

	public int deletePerson(int no) {
		
		//System.out.println("deletePerson()");
		//System.out.println(no);
		
		System.out.println("PhonebookDao.deletePerson()");
		int count = sqlSession.delete("phonebook.delete", no);
		
		return count;
		
//		int count = -1;
//		
//		this.getConnection();
//		
//		try {
//			// 3. SQL문 준비 / 바인딩 / 실행
//			//*SQL문 준비
//			String query ="";
//			query += " delete from person ";
//			query += " where person_id = ? ";
//			
//			//*바인딩
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, no);
//			
//			//*실행
//			count = pstmt.executeUpdate();
//			
//			// 4.결과처리
//			System.out.println(count+ "건 삭제");
//			
//		   
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		
//		this.close();
		
	}
	
	
	//사람 정보 수정하기 1명 (수정)
	public int updatePerson(PersonVo personVo) {
		
		System.out.println("PhonebookDao.updatePerson()");
		
		int count = sqlSession.update("phonebook.update", personVo);
	
		return count;
		
//		int count = -1;
//		
//		this.getConnection();
//		
//		try {
//			// 3. SQL문 준비 / 바인딩 / 실행
//			//*SQL문 준비
//			String query = "";
//			query += " update person ";
//			query += " set name = ?, ";
//			query += "	   hp = ?, ";
//			query += "     company = ? ";
//			query += " where person_id = ? ";
//			
//			//*바인딩
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, personVo.getName());
//			pstmt.setString(2, personVo.getHp());
//			pstmt.setString(3, personVo.getCompany());
//			pstmt.setInt(4, personVo.getPersonId());
//			
//			//*실행
//			count = pstmt.executeUpdate();
//			
//			// 4.결과처리
//			System.out.println("dao 결과" + count);
//		   
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		
//		this.close();
		
	}
	
	
	//사람 1명 정보 가져오기 (수정폼)
	public PersonVo getPersonOne(int no) {

		System.out.println("PhonebookDao.getPersonOne()");
		
		//원본버전
		PersonVo personVo = sqlSession.selectOne("phonebook.selectOne", no);
		//Map<String, Object> personMap = sqlSession.selectOne("phonebook.selectOne", no);
		
		System.out.println("----------");
		System.out.println(personVo);
		//System.out.println(personMap);
		//System.out.println(personMap.get("name"));
		System.out.println("----------");
		
		return personVo;
		//return null;
		
//		PersonVo personVo = null;
//		
//		this.getConnection();
//		
//		try {
//			// 3. SQL문 준비 / 바인딩 / 실행
//			//*SQL문 준비
//			String query = "";
//			query += " select 	person_id, ";
//			query += "		    name, ";
//			query += "          hp, ";
//			query += "          company ";
//			query += " from person ";
//			query += " where person_id = ? ";
//			
//			//*바인딩
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, no);
//			
//			//*실행
//			rs = pstmt.executeQuery();
//			
//			// 4.결과처리
//			rs.next();
//			int personId = rs.getInt("person_id");
//			String name = rs.getString("name");
//			String hp = rs.getString("hp");
//			String company = rs.getString("company");
//			
//			personVo = new PersonVo(personId, name, hp, company);
//			
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		
//		
//		this.close();
		
	}
	
	//사람 1명 정보 가져오기 (수정폼2  DB에서 MAP으로 데이터 보내줌)
	public Map<String, Object> getPersonOne2(int no) {
		System.out.println("PhonebookDao.getPersonOne2()");
		//System.out.println(no);
		
		Map<String, Object> personMap = sqlSession.selectOne("phonebook.selectOneMap", no);
		//System.out.println(personMap);
		return personMap;
	}
	
	
	//사람정보 저장
	public int insertPerson(PersonVo personVo) {
		
		System.out.println("PhonebookDao.insertPerson()");
		int count = sqlSession.insert("phonebook.insert", personVo);
		
//		//가정 map  저장2
//		String name = "정우성";
//		String hp = "111";
//		String company = "222";
//		Map<String, String> pMap = new HashMap<String, String>();
//		pMap.put("name", name);  //(별명, 값)
//		pMap.put("hp", hp);
//		pMap.put("company", company);
//		
//		int count = sqlSession.insert("phonebook.insert2", pMap);
			
		return count;
		
//		int count = -1;
//		
//		this.getConnection();
//				
//		try {
//			// 3. SQL문 준비 / 바인딩 / 실행
//			//*SQL문 준비
//			String query = "";
//			query += " insert into person ";
//			query += " values(null, ?, ?, ?) ";
//			
//			//*바인딩
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, personVo.getName());
//			pstmt.setString(2, personVo.getHp());
//			pstmt.setString(3, personVo.getCompany());
//			
//			//*실행
//			count = pstmt.executeUpdate();
//			 // 4.결과처리
//		   
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		
//		this.close();
//		System.out.println(count);
//			
	
	}
	
	
	//리스트 가져오기
	public List<PersonVo> getPersonList() {
		
		System.out.println("PhonebookDao.getPersonList()");
		
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");
		
		System.out.println("------------------");
		System.out.println(personList);
		System.out.println("------------------");
		
		return personList;
		
//		List<PersonVo> personList = new ArrayList<PersonVo>();
//		
//		this.getConnection();
//		
//		try {
//			// 3. SQL문 준비 / 바인딩 / 실행
//			//*SQL문 준비 
//		    String query = "";
//		    query += " select 	person_id, ";
//		    query += "		    name, ";
//		    query += "          hp, ";
//		    query += "          company ";
//		    query += " from person ";
//			
//		    //바인딩
//		    pstmt = conn.prepareStatement(query);
//		    
//		    //실행
//		    rs = pstmt.executeQuery();
//		    
//		    // 4.결과처리
//		    while(rs.next()) {
//		    	int id = rs.getInt("person_id");
//		    	String name = rs.getString("name");
//		    	String hp = rs.getString("hp");
//		    	String company = rs.getString("company");
//		    	
//		    	PersonVo personVo = new PersonVo(id, name, hp, company);
//		    	
//		    	personList.add(personVo);
//		    }
//		    
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		
//		this.close();
		
		
	}
	
	
}
