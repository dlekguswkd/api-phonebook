package com.javaex.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PhonebookDao;
import com.javaex.vo.PersonVo;

@Service
public class PhonebookService {
	
	@Autowired
	private PhonebookDao phonebookDao;
	
	// 메소드 이름에 exe를 붙이면 서비스라는뜻 (그냥 우리가 알기쉽게)
	
	/* 전화번호 등록 */
	public int exeWritePerson(PersonVo personVo) {	 
		System.out.println("PhonebookService.exeWritePerson()");
		
		int count = phonebookDao.insertPerson(personVo);
		
		return count;
		
	}
	
	
	/* 전화번호 전체 가져오기 */
	public List<PersonVo> exeGetPerson() {
		System.out.println("PhonebookService.exeGetPerson()");
		
		List<PersonVo> personList = phonebookDao.getPersonList();
		
		return personList;
		
	}
	
	
	/* 전화번호 삭제 */
	public int exeDeletePerson(int no) {
		System.out.println("PhonebookService.exeDeletePerson()");
		
		int count = phonebookDao.deletePerson(no);
		
		return count;
		
	}
	

	/* 전화번호 수정폼 */
	public PersonVo exeEditForm(int no) {
		System.out.println("PhonebookService.exeEditForm()");
		
		PersonVo personVo = phonebookDao.getPersonOne(no);
	
		return personVo;
		
	}
	
	/* 전화번호 수정폼2  DB에서 MAP으로 데이터 보내줌 */
	public Map<String, Object> exeEditForm2(int no) {
		System.out.println("PhonebookService.exeEditForm2()");
		//System.out.println(no);
		
		Map<String, Object> personMap = phonebookDao.getPersonOne2(no);
		
		return personMap;
	}
	
	
	
	
	/* 전화번호 수정 */
	public int exeEditPerson(PersonVo personVo) {
		System.out.println("PhonebookService.exeEditPerson()");
		
		int count = phonebookDao.updatePerson(personVo);
	
		return count;
		
	}
	
	
}
