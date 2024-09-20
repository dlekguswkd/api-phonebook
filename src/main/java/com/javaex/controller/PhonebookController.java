package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.PhonebookService;
import com.javaex.vo.PersonVo;

@Controller
public class PhonebookController {
	
	@Autowired
	private PhonebookService phonebookService;

	// 리스트
	// http://localhost:9000/api/persons	- spring boot
	// http://localhost:3000/list		- vsc
	@ResponseBody		// 글자 그냥 내보내기위해서				  가져오는거
	@RequestMapping(value="/api/persons", method=RequestMethod.GET)
	public List<PersonVo>  getList() {
		System.out.println("PhonebookController.getList()");
		
		List<PersonVo> personList = phonebookService.exeGetPerson();
		System.out.println(personList);
		
		return personList;	
	}
	
	
	// 저장(등록)
	// http://localhost:9000/api/persons
	// http://localhost:3000/writeform
	@ResponseBody		// json으로 보내주세요~ 하는거임  -> 저장할 데이타는 json
	@RequestMapping(value="/api/persons", method=RequestMethod.POST)
	// @RequestBody 는 화면에 있는걸 가져오라는 의미
	public int addPerson(@RequestBody PersonVo personVo) { //@ModelAttribute는 파라미터로 보낸걸 갖고오는(post는 그게없음)
		System.out.println("PhonebookController.addPerson()");
		
		// System.out.println(personVo);
		int count = phonebookService.exeWritePerson(personVo);
		System.out.println(count);
		return count;
	}
	
	
	// 삭제
	// http://localhost:9000/api/persons/~ (삭제할번호)	delete 삭제
	@ResponseBody
	// 									변수가 올거다 pathVariable에 있는 {no}를 int no값에 넣어주세요
	@RequestMapping(value="/api/persons/{no}", method=RequestMethod.DELETE)
	public int delPerson(@PathVariable(value="no") int no) {
		System.out.println("PhonebookController.delPerson()");
		System.out.println(no);
		
		int count = phonebookService.exeDeletePerson(no);
		
		return count;
	}
	
	
	
	// 
	// http://localhost:9000/api/persons/~ (가져올번호) get 가져와
	
	
	// 수정
	// http://localhost:9000/api/persons/~ (수정할번호) put 수정 -> 수정할 데이타는 json
	
	
	
}
