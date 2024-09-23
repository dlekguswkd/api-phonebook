package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.PhonebookService;
import com.javaex.uti.JsonResult;
import com.javaex.vo.PersonVo;

@RestController					// @Controller + @ResponseBody 
								// 여기는 다 json으로 응답해야하기때문에 ResponseBody 이걸 다 넣어줘야하는걸 한번에
public class PhonebookController {
	
	@Autowired
	private PhonebookService phonebookService;

	// 리스트
	// http://localhost:9000/api/persons	- spring boot
	// http://localhost:3000/list		- vsc
	// @ResponseBody		// 글자 그냥 내보내기위해서				  가져오는거
	// @RequestMapping(value="/api/persons", method=RequestMethod.GET)
	@GetMapping("/api/persons")
	public JsonResult getList() {
		System.out.println("PhonebookController.getList()");
		
		List<PersonVo> personList = phonebookService.exeGetPerson();
		// System.out.println(personList);
		
		// return personList;	
		return JsonResult.success(personList);
	}
	
	
	// 저장(등록)
	// http://localhost:9000/api/persons
	// http://localhost:3000/writeform
	// @ResponseBody		// json으로 보내주세요~ 하는거임  -> 저장할 데이타는 json
	// @RequestMapping(value="/api/persons", method=RequestMethod.POST)
	@PostMapping("/api/persons")
	// @RequestBody 는 화면에 있는걸 가져오라는 의미
	public JsonResult addPerson(@RequestBody PersonVo personVo) { //@ModelAttribute는 파라미터로 보낸걸 갖고오는(post는 그게없음)
		System.out.println("PhonebookController.addPerson()");
		
		// System.out.println(personVo);
		int count = phonebookService.exeWritePerson(personVo);
		// System.out.println(count);
		if(count != 1) { //등록안됨
			return JsonResult.fail("등록에 실패했습니다.");
		}else { //등록됨
			return JsonResult.success(count);
		}
		// return count;
	}
	
	
	// 삭제
	// http://localhost:9000/api/persons/~ (삭제할번호)	delete 삭제
	// @ResponseBody
	// 									변수가 올거다 pathVariable에 있는 {no}를 int no값에 넣어주세요
	// @RequestMapping(value="/api/persons/{no}", method=RequestMethod.DELETE)
	@DeleteMapping("/api/persons/{no}")
	public JsonResult delPerson(@PathVariable(value="no") int no) {
		System.out.println("PhonebookController.delPerson()");
		// System.out.println(no);
		
		int count = phonebookService.exeDeletePerson(no);
		
//		JsonResult jsonResult = new JsonResult();
//		jsonResult.success(count);
		if(count != 1) {	// 실패 (삭제안됨)
			return JsonResult.fail("해당번호가 없습니다.");
			
		}else {				// 성공 (삭제됨) 
			return JsonResult.success(count);
		}
		
		// return count;
	}
	
	
	// 수정폼
	// http://localhost:3000/editform/~
	// http://localhost:9000/api/persons/~ (가져올번호) get 가져와
	@GetMapping("/api/persons/{no}")
	public JsonResult getPerson(@PathVariable(value="no") int personId) {
		System.out.println("PhonebookController.getPerson()");
		// System.out.println(personId);
		
		PersonVo personVo = phonebookService.exeEditForm(personId);
		// System.out.println(personVo);
		
		if(personVo == null) {
			return JsonResult.fail("번호가 없습니다.");
			
		}else {
			return JsonResult.success(personVo);
		}
		
	}
	
	
	// 수정
	// http://localhost:9000/api/persons/~ (수정할번호) put 수정 -> 수정할 데이타는 json
	@PutMapping("/api/persons/{no}")		// 옆에랑 이거랑 똑같은거   여기에 담아줘
	public JsonResult updatePerson(@PathVariable(value="no") int personId,
								   @RequestBody PersonVo personVo) { 	// param으로 오는게 아님 주소자체
		System.out.println("PhonebookController.updatePerson()");
		// System.out.println(personId);
		// System.out.println(personVo);
		personVo.setPersonId(personId);
		// System.out.println(personVo);
		
		int count = phonebookService.exeEditPerson(personVo);
		
		if(count == 0) {
			return JsonResult.fail("수정할 데이터가 없습니다.");
			
		}else {
			return JsonResult.success(count);
		}
		
	}
	
	
}
